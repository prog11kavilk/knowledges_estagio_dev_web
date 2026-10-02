package br.com.alura.mitraSeeks.controller;

import br.com.alura.mitraSeeks.dto.FraseDTO;
import br.com.alura.mitraSeeks.service.FraseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class FraseController {

    @Autowired
    private FraseService servico;

    @GetMapping("/frases")
    public List<FraseDTO> obterFrases() {
        return servico.obterTodas();
    }

    // Ex.: GET /frases/aleatoria?exceto=3 -> sorteia uma frase que não seja a 3.
    // "/series/frases" é a rota do desafio original (front 3356-java-desafio-front), mantida por compatibilidade
    @GetMapping({"/frases/aleatoria", "/series/frases"})
    public FraseDTO obterFraseAleatoria(@RequestParam(defaultValue = "0") Long exceto) {
        return servico.obterFraseAleatoria(exceto);
    }

    @GetMapping("/frases/{id}")
    public FraseDTO obterPorId(@PathVariable Long id) {
        return servico.obterPorId(id);
    }
}
