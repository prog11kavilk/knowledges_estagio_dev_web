package br.com.alura.mitraSeeks.controller;

import br.com.alura.mitraSeeks.service.CatalogoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/catalogo/importar")
public class CatalogoController {

    @Autowired
    private CatalogoService servico;

    // Inicia a importação em segundo plano (se já estiver rodando, só devolve o andamento)
    @PostMapping
    public CatalogoService.StatusImportacao iniciar() {
        return servico.iniciarImportacao();
    }

    @GetMapping
    public CatalogoService.StatusImportacao andamento() {
        return servico.obterStatus();
    }
}
