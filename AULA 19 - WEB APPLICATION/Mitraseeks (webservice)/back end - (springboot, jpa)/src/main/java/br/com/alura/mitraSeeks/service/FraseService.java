package br.com.alura.mitraSeeks.service;

import br.com.alura.mitraSeeks.dto.FraseDTO;
import br.com.alura.mitraSeeks.model.Frase;
import br.com.alura.mitraSeeks.repository.FilmeRepository;
import br.com.alura.mitraSeeks.repository.FraseRepository;
import br.com.alura.mitraSeeks.repository.SerieRepository;
import jakarta.persistence.EntityNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class FraseService {

    private static final Logger log = LoggerFactory.getLogger(FraseService.class);

    @Autowired
    private FraseRepository repositorio;

    @Autowired
    private FilmeRepository filmeRepositorio;

    @Autowired
    private SerieRepository serieRepositorio;

    public List<FraseDTO> obterTodas() {
        return repositorio.findAllByOrderByTituloAsc().stream()
                .map(this::converteDados)
                .collect(Collectors.toList());
    }

    // Sorteia uma frase diferente da que está na tela (se só existir uma, devolve ela mesma)
    public FraseDTO obterFraseAleatoria(Long exceto) {
        Frase frase = repositorio.sortearExceto(exceto)
                .or(() -> repositorio.sortearExceto(0L))
                .orElseThrow(() -> new EntityNotFoundException("Nenhuma frase cadastrada ainda"));
        return converteDados(frase);
    }

    public FraseDTO obterPorId(Long id) {
        return converteDados(repositorio.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Frase com id " + id + " não encontrada")));
    }

    /**
     * Lê resources/catalogo/frases.txt (tipo | título | personagem | frase) e grava as que ainda não existem.
     * Se o texto de uma frase mudar no arquivo, atualiza a do banco.
     */
    public int carregarArquivo(String caminho) throws IOException {
        var conteudo = new String(new ClassPathResource(caminho).getInputStream().readAllBytes(), StandardCharsets.UTF_8);
        int novas = 0;
        for (String linha : conteudo.lines().map(String::trim).filter(l -> !l.isEmpty() && !l.startsWith("#")).toList()) {
            String[] partes = Arrays.stream(linha.split("\\|", 4)).map(String::trim).toArray(String[]::new);
            if (partes.length < 4 || !(partes[0].equals("filme") || partes[0].equals("serie"))) {
                log.warn("Linha ignorada em {}: {}", caminho, linha);
                continue;
            }
            var existente = repositorio.findFirstByTipoAndTituloIgnoreCaseAndPersonagemIgnoreCase(partes[0], partes[1], partes[2]);
            if (existente.isEmpty()) {
                repositorio.save(new Frase(partes[0], partes[1], partes[2], partes[3]));
                novas++;
            } else if (!existente.get().getFrase().equals(partes[3])) {
                existente.get().setFrase(partes[3]);
                repositorio.save(existente.get());
            }
        }
        return novas;
    }

    /**
     * Completa o cartaz (buscando o título no catálogo de filmes/séries) e as cores predominantes.
     * Frases de títulos que ainda não foram importados ficam sem cartaz até a próxima vez.
     */
    public void completarCartazesECores() {
        for (Frase frase : repositorio.findByPosterIsNullOrCoresIsNull()) {
            if (frase.getPoster() == null) {
                preencherPoster(frase);
            }
            if (frase.getPoster() != null && frase.getCores() == null) {
                List<String> cores = CoresDoCartaz.predominantes(frase.getPoster());
                if (!cores.isEmpty()) {
                    frase.setCores(String.join(",", cores));
                }
            }
            repositorio.save(frase);
        }
    }

    private void preencherPoster(Frase frase) {
        if (frase.getTipo().equals("filme")) {
            filmeRepositorio.findByTituloIgnoreCase(frase.getTitulo()).ifPresent(f -> {
                frase.setTituloId(f.getId());
                frase.setPoster(posterValido(f.getPoster()));
            });
        } else {
            serieRepositorio.findFirstByTituloIgnoreCase(frase.getTitulo()).ifPresent(s -> {
                frase.setTituloId(s.getId());
                frase.setPoster(posterValido(s.getPoster()));
            });
        }
    }

    // O OMDb manda "N/A" quando não tem cartaz
    private String posterValido(String poster) {
        return poster == null || poster.isBlank() || poster.equals("N/A") ? null : poster;
    }

    private FraseDTO converteDados(Frase f) {
        List<String> cores = f.getCores() == null ? List.of() : List.of(f.getCores().split(","));
        return new FraseDTO(f.getId(), f.getTipo(), f.getTituloId(), f.getTitulo(), f.getPersonagem(),
                f.getFrase(), f.getPoster(), cores);
    }
}
