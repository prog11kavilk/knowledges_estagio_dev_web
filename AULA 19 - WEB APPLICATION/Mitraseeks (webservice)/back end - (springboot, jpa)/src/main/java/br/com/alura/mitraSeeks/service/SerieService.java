package br.com.alura.mitraSeeks.service;

import br.com.alura.mitraSeeks.dto.EpisodioDTO;
import br.com.alura.mitraSeeks.dto.SerieDTO;
import br.com.alura.mitraSeeks.model.Categoria;
import br.com.alura.mitraSeeks.model.Serie;
import br.com.alura.mitraSeeks.repository.SerieRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SerieService {

    @Autowired
    private SerieRepository repositorio;

    public List<SerieDTO> obterTodasAsSeries() {
        return converteDados(repositorio.findAll());
    }

    public List<SerieDTO> obterTop5Series() {
        return converteDados(repositorio.findTop5ByOrderByAvaliacaoDesc());
    }

    public List<SerieDTO> obterTop10Series() {
        return converteDados(repositorio.findTop10ByOrderByAvaliacaoDesc());
    }

    public List<SerieDTO> buscarPorTitulo(String trecho) {
        return converteDados(repositorio.findTop20ByTituloContainingIgnoreCaseOrderByAvaliacaoDesc(trecho.trim()));
    }

    private List<SerieDTO> converteDados(List<Serie> series) {
      return series.stream()
              .map(s -> new SerieDTO(s.getId(), s.getTitulo(), s.getTotalTemporadas(), s.getAvaliacao(), s.getGenero(), s.getAtores(), s.getPoster(), s.getSinopse()))
              .collect(Collectors.toList());
    }

    public List<SerieDTO> obterLancamentos() {
        return converteDados(repositorio.lancamentosMaisRecentes());
    }

    public SerieDTO obterPorId(Long id) {
        Serie s = buscarSerie(id);
        return new SerieDTO(s.getId(), s.getTitulo(), s.getTotalTemporadas(), s.getAvaliacao(), s.getGenero(), s.getAtores(), s.getPoster(), s.getSinopse());
    }

    public List<EpisodioDTO> obterTodasTemporadas(Long id) {
        return buscarSerie(id).getEpisodios().stream()
                .map(e -> new EpisodioDTO(e.getTemporada(), e.getNumeroEpisodio(), e.getTitulo()))
                .collect(Collectors.toList());
    }

    public List<EpisodioDTO> obterTemporadasPorNumero(Long id, Long numero) {
        buscarSerie(id);
        return repositorio.obterEpisodiosPorTemporada(id, numero)
                .stream()
                .map(e -> new EpisodioDTO(e.getTemporada(), e.getNumeroEpisodio(), e.getTitulo()))
                .collect(Collectors.toList());
    }

    public List<SerieDTO> obterSeriesPorCategoria(String nomeGenero) {
        Categoria categoria = Categoria.fromPortugues(nomeGenero);
        return converteDados(repositorio.findByGenero(categoria));
    }

    public List<EpisodioDTO> obterTopEpisodios(Long id) {
        var serie = buscarSerie(id);
        return repositorio.topEpisodiosPorSerie(serie)
                .stream()
                .map(e -> new EpisodioDTO(e.getTemporada(), e.getNumeroEpisodio(), e.getTitulo()))
                .collect(Collectors.toList());
    }

    private Serie buscarSerie(Long id) {
        return repositorio.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Série com id " + id + " não encontrada"));
    }
}
