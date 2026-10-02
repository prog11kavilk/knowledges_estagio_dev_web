package com.kayk.carrinho;

import java.util.Objects;

/**
 * Representa um item de compra: nome, preço unitário e quantidade.
 *
 * Classe nasceu a partir do primeiro ciclo TDD do projeto (ver
 * ProdutoTest): primeiro escrevemos o teste que descrevia o
 * comportamento esperado (RED), depois o código mínimo para passar
 * (GREEN) e por fim organizamos os atributos como record (REFACTOR).
 */
public class Produto {

    private final String nome;
    private final double preco;
    private final int quantidade;

    public Produto(String nome, double preco, int quantidade) {
        if (nome == null || nome.isBlank()) {
            throw new ProdutoInvalidoException("O nome do produto não pode ser vazio.");
        }
        if (preco <= 0) {
            throw new ProdutoInvalidoException("O preço deve ser maior que zero.");
        }
        if (quantidade <= 0) {
            throw new ProdutoInvalidoException("A quantidade deve ser maior que zero.");
        }
        this.nome = nome;
        this.preco = preco;
        this.quantidade = quantidade;
    }

    public String getNome() {
        return nome;
    }

    public double getPreco() {
        return preco;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public double subtotal() {
        return preco * quantidade;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Produto produto)) return false;
        return Double.compare(preco, produto.preco) == 0
                && quantidade == produto.quantidade
                && nome.equals(produto.nome);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nome, preco, quantidade);
    }

    @Override
    public String toString() {
        return String.format("%s (%dx R$%.2f)", nome, quantidade, preco);
    }
}
