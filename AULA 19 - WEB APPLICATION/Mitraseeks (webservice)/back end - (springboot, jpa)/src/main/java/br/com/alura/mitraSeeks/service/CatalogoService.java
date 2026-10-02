package br.com.alura.mitraSeeks.service;

import br.com.alura.mitraSeeks.model.DadosFilme;
import br.com.alura.mitraSeeks.model.DadosSerie;
import br.com.alura.mitraSeeks.model.DadosTemporada;
import br.com.alura.mitraSeeks.model.Episodio;
import br.com.alura.mitraSeeks.model.Filmes;
import br.com.alura.mitraSeeks.model.Serie;
import br.com.alura.mitraSeeks.repository.FilmeRepository;
import br.com.alura.mitraSeeks.repository.SerieRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * Importa em lote os títulos listados em resources/catalogo/filmes.txt e series.txt,
 * buscando cada um no OMDb (JSON -> DadosFilme/DadosSerie -> Filmes/Serie).
 * Roda em segundo plano e para sozinho quando a cota diária do OMDb acaba.
 */
@Service
public class CatalogoService {

    private static final Logger log = LoggerFactory.getLogger(CatalogoService.class);

    private final String ENDERECO = "https://www.omdbapi.com/?";
    private final String API_KEY = "&apikey=" + System.getenv().getOrDefault("OMDB_API_KEY", "");

    private ConsumoApi consumo = new ConsumoApi();
    private ConverteDados conversor = new ConverteDados();

    @Autowired
    private FilmeRepository filmeRepositorio;

    @Autowired
    private SerieRepository serieRepositorio;

    private final StatusImportacao status = new StatusImportacao();

    public StatusImportacao obterStatus() {
        return status;
    }

    public synchronized StatusImportacao iniciarImportacao() {
        if (!status.emAndamento) {
            status.reiniciar();
            new Thread(this::importar, "importador-catalogo").start();
        }
        return status;
    }

    private void importar() {
        try {
            completarFilmesSemAvaliacao();
            importarFilmes(lerLista("catalogo/filmes.txt"));
            importarSeries(lerLista("catalogo/series.txt"));
            status.mensagem = "Importação concluída";
        } catch (LimiteOmdbException e) {
            status.mensagem = "Parou: limite diário de consultas do OMDb atingido. Rode de novo amanhã para continuar.";
        } catch (Exception e) {
            log.error("Erro na importação do catálogo", e);
            status.mensagem = "Parou por erro: " + e.getMessage();
        } finally {
            status.etapa = "fim";
            status.fim = LocalDateTime.now();
            status.emAndamento = false;
            log.info("Importação do catálogo: {}", status.mensagem);
        }
    }

    private void completarFilmesSemAvaliacao() {
        status.etapa = "completando filmes já salvos";
        for (Filmes filme : filmeRepositorio.findByAvaliacaoIsNull()) {
            DadosFilme dados = buscarFilme(filme.getTitulo());
            if (dados != null) {
                filme.completarAvaliacaoEGenero(dados);
                filmeRepositorio.save(filme);
                status.filmesCompletados++;
            }
        }
    }

    private void importarFilmes(List<String> titulos) {
        status.etapa = "filmes";
        for (String titulo : titulos) {
            status.atual = titulo;
            if (filmeRepositorio.findByTituloIgnoreCase(titulo).isPresent()) {
                status.ignorados++;
                continue;
            }
            try {
                DadosFilme dados = buscarFilme(titulo);
                if (dados == null) {
                    continue;
                }
                if (filmeRepositorio.findByTituloIgnoreCase(dados.titulo()).isPresent()) {
                    status.ignorados++;
                    continue;
                }
                filmeRepositorio.save(new Filmes(dados));
                status.filmesNovos++;
            } catch (LimiteOmdbException e) {
                throw e;
            } catch (Exception e) {
                registrarFalha(titulo, e);
            }
        }
    }

    private void importarSeries(List<String> titulos) {
        status.etapa = "séries";
        for (String titulo : titulos) {
            status.atual = titulo;
            if (serieRepositorio.findFirstByTituloIgnoreCase(titulo).isPresent()) {
                status.ignorados++;
                continue;
            }
            try {
                var json = consultarOmdb("type=series&t=" + codificar(titulo));
                DadosSerie dados = conversor.obterDados(json, DadosSerie.class);
                if (dados.titulo() == null || dados.totalTemporadas() == null) {
                    registrarFalha(titulo, "não encontrada no OMDb");
                    continue;
                }
                if (serieRepositorio.findFirstByTituloIgnoreCase(dados.titulo()).isPresent()) {
                    status.ignorados++;
                    continue;
                }

                Serie serie = new Serie(dados);
                List<Episodio> episodios = new ArrayList<>();
                for (int i = 1; i <= dados.totalTemporadas(); i++) {
                    var jsonTemporada = consultarOmdb("t=" + codificar(dados.titulo()) + "&season=" + i);
                    DadosTemporada temporada = conversor.obterDados(jsonTemporada, DadosTemporada.class);
                    if (temporada.episodios() != null) {
                        temporada.episodios().forEach(e -> episodios.add(new Episodio(temporada.numero(), e)));
                    }
                }
                serie.setEpisodios(episodios);
                serieRepositorio.save(serie);
                status.seriesNovas++;
                status.episodiosNovos += episodios.size();
            } catch (LimiteOmdbException e) {
                throw e;
            } catch (Exception e) {
                registrarFalha(titulo, e);
            }
        }
    }

    private DadosFilme buscarFilme(String titulo) {
        var json = consultarOmdb("type=movie&t=" + codificar(titulo));
        DadosFilme dados = conversor.obterDados(json, DadosFilme.class);
        if (!"True".equalsIgnoreCase(dados.resposta())) {
            registrarFalha(titulo, "não encontrado no OMDb");
            return null;
        }
        return dados;
    }

    private String consultarOmdb(String parametros) {
        var json = consumo.obterDados(ENDERECO + parametros + API_KEY);
        status.consultasOmdb++;
        if (json.contains("Request limit reached")) {
            throw new LimiteOmdbException();
        }
        return json;
    }

    private String codificar(String texto) {
        return URLEncoder.encode(texto, StandardCharsets.UTF_8);
    }

    private List<String> lerLista(String caminho) throws IOException {
        var conteudo = new String(new ClassPathResource(caminho).getInputStream().readAllBytes(), StandardCharsets.UTF_8);
        return conteudo.lines()
                .map(String::trim)
                .filter(l -> !l.isEmpty() && !l.startsWith("#"))
                .toList();
    }

    private void registrarFalha(String titulo, Exception e) {
        registrarFalha(titulo, e.getMessage());
    }

    private void registrarFalha(String titulo, String motivo) {
        status.falhas++;
        if (status.ultimasFalhas.size() < 30) {
            status.ultimasFalhas.add(titulo + ": " + motivo);
        }
    }

    private static class LimiteOmdbException extends RuntimeException {
    }

    public static class StatusImportacao {
        public volatile boolean emAndamento;
        public volatile String etapa = "parado";
        public volatile String atual;
        public volatile String mensagem;
        public int consultasOmdb;
        public int filmesCompletados;
        public int filmesNovos;
        public int seriesNovas;
        public int episodiosNovos;
        public int ignorados;
        public int falhas;
        public List<String> ultimasFalhas = new ArrayList<>();
        public LocalDateTime inicio;
        public LocalDateTime fim;

        private void reiniciar() {
            emAndamento = true;
            etapa = "iniciando";
            atual = null;
            mensagem = null;
            consultasOmdb = filmesCompletados = filmesNovos = seriesNovas = episodiosNovos = ignorados = falhas = 0;
            ultimasFalhas = new ArrayList<>();
            inicio = LocalDateTime.now();
            fim = null;
        }
    }
}
