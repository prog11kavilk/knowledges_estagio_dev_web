package br.com.kaykvilela.spring_boot_myapplication.controller;

import br.com.kaykvilela.spring_boot_myapplication.dto.ProdutoDto;
import br.com.kaykvilela.spring_boot_myapplication.model.ProdutoModel;
import br.com.kaykvilela.spring_boot_myapplication.service.ProdutoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1//produtos")
@RequiredArgsConstructor       //construtor automático com todas os atributos final

public class ProdutoController {
    private final ProdutoService produtoService;


    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<ProdutoModel> findAll() {
        return produtoService.findAll();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ProdutoModel createProduct(@RequestBody ProdutoDto produtoDto){
        return produtoService.createProduto(produtoDto);
    }
    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public ProdutoModel updateProduct(@PathVariable Integer id,
                                      @RequestBody ProdutoDto produtoDto) {
        return produtoService.atualizarProduto(produtoDto, id);
    }
    @DeleteMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteProduct(@PathVariable Integer id) {
        ProdutoService.removerProduto(id);

    }
}
