package br.com.kaykvilela.spring_boot_myapplication.service;

import br.com.kaykvilela.spring_boot_myapplication.dto.ProdutoDto;
import br.com.kaykvilela.spring_boot_myapplication.model.ProdutoModel;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProdutoService {

    private static final List<ProdutoModel> Produtos = new ArrayList<>();

    static {
        Produtos.add(ProdutoModel.builder().
                        id(1).nome("Notebook").
                        preco(3200.0).
                        quantidade(10).
                        build(),
                ProdutoModel.builder().id(2).
                        nome("carregador USB-c").
                        preco(30.0).
                        quantidade(12)
                        .build(),
                ProdutoModel.builder().
                        id(3).nome("placa de vídeo").
                        preco(429.90).
                        quantidade(10).
                        build());
    }

    public List<ProdutoModel> findAll (){
        return new ArrayList<>(Produtos);
    }

    public ProdutoModel createProduto(ProdutoDto produtoDto) {

        Integer identificador = Produtos.stream().mapToInt(ProdutoModel::getId)
                .max().orElse(0) + 1;
        ProdutoModel novoProduto = ProdutoModel.builder().id(identificador)
                .nome(produtoDto.getNome())
                .preco(produtoDto.getPreco())
                .quantidade(produtoDto.getQuantidade())
                .build();

        Produtos.add(novoProduto);
        return novoProduto;

    }
    public ProdutoModel atualizarProduto(ProdutoDto produtoDto, Integer id) {
        ProdutoModel produto = Produtos.stream()
                .filter(p -> p.getId().equals(id))
                .findAny().orElseThrow(() -> new RuntimeException("Produto não encontrado!"));

        produto.setNome(produtoDto.getNome());
        produto.setPreco(produtoDto.getPreco());
        produto.setQuantidade(produtoDto.getQuantidade());

        return produto;
    }

        public static void removerProduto(Integer id) {
            Produtos.removeIf(p -> p.getId().equals(id));
        }


}

