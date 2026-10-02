package br.com.alura.mitraSeeks.controller;

import br.com.alura.mitraSeeks.dto.EpisodioDTO;
import br.com.alura.mitraSeeks.dto.SerieDTO;
import br.com.alura.mitraSeeks.service.SerieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/series")
public class SerieController {

   @Autowired
   private SerieService servico;

    @GetMapping
    public List<SerieDTO> obterSeries() {
       return servico.obterTodasAsSeries();
    }

    @GetMapping("/inicio")
    public String retornarInicio() {
        return "Bem vindo ao MitraSeeks!";
    }

    @GetMapping("/top5")
    public List<SerieDTO> obterTop5Series() {
        return servico.obterTop5Series();
    }

    @GetMapping("/top10")
    public List<SerieDTO> obterTop10Series() {
        return servico.obterTop10Series();
    }

    // Ex.: GET /series/buscar?titulo=breaking
    @GetMapping("/buscar")
    public List<SerieDTO> buscarPorTitulo(@RequestParam String titulo) {
        return servico.buscarPorTitulo(titulo);
    }

    @GetMapping("/lancamentos")
    public List<SerieDTO> obterLancamentos() {
        return servico.obterLancamentos();
    }

    @GetMapping("/{id}")
    public SerieDTO obterPorId(@PathVariable Long id) {
        return servico.obterPorId(id);
    }

    @GetMapping("/{id}/temporadas/todas")
    public List<EpisodioDTO> obterTodasTemporadas(@PathVariable Long id){
        return servico.obterTodasTemporadas(id);
    }

    @GetMapping("/{id}/temporadas/{numero}")
    public List<EpisodioDTO> obterTemporadasPorNumero(@PathVariable Long id, @PathVariable Long numero){
        return servico.obterTemporadasPorNumero(id, numero);
    }

    @GetMapping("/{id}/temporadas/top")
    public List<EpisodioDTO> obterTopEpisodios(@PathVariable Long id){
        return servico.obterTopEpisodios(id);
    }

    @GetMapping("/categoria/{nomeGenero}")
    public List<SerieDTO> obterSeriesPorCategoria(@PathVariable String nomeGenero){
        return servico.obterSeriesPorCategoria(nomeGenero);
    }
}
