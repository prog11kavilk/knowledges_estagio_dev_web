package com.kayk.carrinho;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Carrinho de compras: guarda produtos e calcula o total da compra,
 * com suporte a um cupom de desconto percentual simples.
 *
 * Cada método deste carrinho foi criado seguindo o ciclo TDD
 * (RED -> GREEN -> REFACTOR): veja CarrinhoTest para a sequência de
 * testes que guiou a implementação.
 */
public class Carrinho {

    private final List<Produto> produtos = new ArrayList<>();
    private double percentualDesconto = 0;

    public void adicionar(Produto produto) {
        if (produto == null) {
            throw new ProdutoInvalidoException("Não é possível adicionar um produto nulo ao carrinho.");
        }
        produtos.add(produto);
    }

    public void remover(Produto produto) {
        boolean removido = produtos.remove(produto);
        if (!removido) {
            throw new ProdutoNaoEncontradoException(
                    "O produto '" + produto.getNome() + "' não está no carrinho.");
        }
    }

    public void aplicarCupom(double percentual) {
        if (percentual < 0 || percentual > 100) {
            throw new ProdutoInvalidoException("O percentual de desconto deve estar entre 0 e 100.");
        }
        this.percentualDesconto = percentual;
    }

    public double total() {
        double subtotal = produtos.stream().mapToDouble(Produto::subtotal).sum();
        double desconto = subtotal * (percentualDesconto / 100);
        return subtotal - desconto;
    }

    public boolean estaVazio() {
        return produtos.isEmpty();
    }

    public int quantidadeDeItens() {
        return produtos.size();
    }

    public List<Produto> getProdutos() {
        return Collections.unmodifiableList(produtos);
    }
}
