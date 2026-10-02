package com.kayk.carrinho;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Módulo 2 (JUnit) e 5 (Lidando com Exceptions) do curso: anotações
 * básicas, asserts e assertThrows.
 */
class ProdutoTest {

    @Test
    @DisplayName("Deve criar um produto com nome, preço e quantidade válidos")
    void deveCriarProdutoValido() {
        Produto produto = new Produto("Caneta", 2.5, 3);

        assertEquals("Caneta", produto.getNome());
        assertEquals(2.5, produto.getPreco());
        assertEquals(3, produto.getQuantidade());
    }

    @Test
    @DisplayName("Deve calcular o subtotal como preço vezes quantidade")
    void deveCalcularSubtotal() {
        Produto produto = new Produto("Caderno", 10.0, 2);

        assertEquals(20.0, produto.subtotal());
    }

    @Test
    @DisplayName("Não deve aceitar nome vazio")
    void naoDeveAceitarNomeVazio() {
        ProdutoInvalidoException excecao = assertThrows(ProdutoInvalidoException.class,
                () -> new Produto("   ", 5.0, 1));

        assertEquals("O nome do produto não pode ser vazio.", excecao.getMessage());
    }

    @ParameterizedTest(name = "não deve aceitar preço = {0}")
    @DisplayName("Não deve aceitar preço zero ou negativo")
    @ValueSource(doubles = {0, -1, -10.5})
    void naoDeveAceitarPrecoInvalido(double precoInvalido) {
        assertThrows(ProdutoInvalidoException.class,
                () -> new Produto("Caneta", precoInvalido, 1));
    }

    @ParameterizedTest(name = "não deve aceitar quantidade = {0}")
    @DisplayName("Não deve aceitar quantidade zero ou negativa")
    @ValueSource(ints = {0, -1, -5})
    void naoDeveAceitarQuantidadeInvalida(int quantidadeInvalida) {
        assertThrows(ProdutoInvalidoException.class,
                () -> new Produto("Caneta", 2.5, quantidadeInvalida));
    }
}
