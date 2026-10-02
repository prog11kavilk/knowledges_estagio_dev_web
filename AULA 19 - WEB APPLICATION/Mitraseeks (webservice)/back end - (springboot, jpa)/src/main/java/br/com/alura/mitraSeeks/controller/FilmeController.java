package br.com.alura.mitraSeeks.controller;

import br.com.alura.mitraSeeks.dto.FilmeDTO;
import br.com.alura.mitraSeeks.service.FilmeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/filmes")
public class FilmeController {

    @Autowired
    private FilmeService servico;

    @GetMapping
    public List<FilmeDTO> obterFilmes() {
        return servico.obterTodosOsFilmes();
    }

    @GetMapping("/top10")
    public List<FilmeDTO> obterTop10Filmes() {
        return servico.obterTop10Filmes();
    }

    @GetMapping("/lancamentos")
    public List<FilmeDTO> obterLancamentos() {
        return servico.obterLancamentos();
    }

    @GetMapping("/categoria/{nomeGenero}")
    public List<FilmeDTO> obterFilmesPorCategoria(@PathVariable String nomeGenero) {
        return servico.obterFilmesPorCategoria(nomeGenero);
    }

    // Ex.: GET /filmes/buscar?titulo=matrix
    @GetMapping("/buscar")
    public List<FilmeDTO> buscarPorTitulo(@RequestParam String titulo) {
        return servico.buscarPorTitulo(titulo);
    }

    @GetMapping("/{id}")
    public FilmeDTO obterPorId(@PathVariable Long id) {
        return servico.obterPorId(id);
    }

    // Ex.: POST /filmes?titulo=Inception -> busca no OMDb e salva no banco
    @PostMapping
    public FilmeDTO buscarESalvar(@RequestParam String titulo) {
        return servico.buscarESalvar(titulo);
    }
}
