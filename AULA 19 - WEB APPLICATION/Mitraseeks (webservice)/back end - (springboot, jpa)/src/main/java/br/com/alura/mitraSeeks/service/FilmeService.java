package br.com.alura.mitraSeeks.service;

import br.com.alura.mitraSeeks.dto.FilmeDTO;
import br.com.alura.mitraSeeks.model.Categoria;
import br.com.alura.mitraSeeks.model.DadosFilme;
import br.com.alura.mitraSeeks.model.Filmes;
import br.com.alura.mitraSeeks.repository.FilmeRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class FilmeService {

    private final String ENDERECO = "https://www.omdbapi.com/?type=movie&t=";
    private final String API_KEY = "&apikey=" + System.getenv().getOrDefault("OMDB_API_KEY", "");

    private ConsumoApi consumo = new ConsumoApi();
    private ConverteDados conversor = new ConverteDados();

    @Autowired
    private FilmeRepository repositorio;

    public List<FilmeDTO> obterTodosOsFilmes() {
        return converteDados(repositorio.findAllByOrderByTituloAsc());
    }

    public List<FilmeDTO> obterTop10Filmes() {
        return converteDados(repositorio.findTop10ByAvaliacaoNotNullOrderByAvaliacaoDesc());
    }

    public List<FilmeDTO> obterLancamentos() {
        return converteDados(repositorio.findTop10ByAnoLancamentoNotNullOrderByAnoLancamentoDescAvaliacaoDesc());
    }

    public List<FilmeDTO> obterFilmesPorCategoria(String nomeGenero) {
        Categoria categoria = Categoria.fromPortugues(nomeGenero);
        return converteDados(repositorio.findByGeneroOrderByAvaliacaoDesc(categoria));
    }

    public List<FilmeDTO> buscarPorTitulo(String trecho) {
        return converteDados(repositorio.findTop20ByTituloContainingIgnoreCaseOrderByAvaliacaoDesc(trecho.trim()));
    }

    public FilmeDTO obterPorId(Long id) {
        return converteDados(buscarFilme(id));
    }

    // Busca o filme no OMDb, converte o JSON em DadosFilme -> Filmes e salva; se já existir, devolve o salvo
    public FilmeDTO buscarESalvar(String titulo) {
        DadosFilme dados = buscarNoOmdb(titulo);
        Filmes filme = repositorio.findByTituloIgnoreCase(dados.titulo())
                .orElseGet(() -> repositorio.save(new Filmes(dados)));
        return converteDados(filme);
    }

    public DadosFilme buscarNoOmdb(String titulo) {
        var json = consumo.obterDados(ENDERECO + URLEncoder.encode(titulo, StandardCharsets.UTF_8) + API_KEY);
        DadosFilme dados = conversor.obterDados(json, DadosFilme.class);

        if (!"True".equalsIgnoreCase(dados.resposta())) {
            throw new EntityNotFoundException("Filme '" + titulo + "' não encontrado no OMDb"
                    + (dados.erro() != null ? " (" + dados.erro() + ")" : ""));
        }
        return dados;
    }

    private List<FilmeDTO> converteDados(List<Filmes> filmes) {
        return filmes.stream()
                .map(this::converteDados)
                .collect(Collectors.toList());
    }

    private FilmeDTO converteDados(Filmes f) {
        return new FilmeDTO(f.getId(), f.getTitulo(), f.getDuracao(), f.getAnoLancamento(), f.getAvaliacao(),
                f.getGenero(), f.getAtores(), f.getSinopse(), f.getPoster());
    }

    private Filmes buscarFilme(Long id) {
        return repositorio.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Filme com id " + id + " não encontrado"));
    }
}
