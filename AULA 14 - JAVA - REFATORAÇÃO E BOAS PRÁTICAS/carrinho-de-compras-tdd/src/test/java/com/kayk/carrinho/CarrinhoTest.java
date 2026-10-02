package com.kayk.carrinho;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Módulos 1, 2, 3 e 4 do curso (testes automatizados, JUnit, asserts e TDD).
 *
 * O uso de @BeforeEach garante que cada teste começa com um carrinho
 * novo e vazio, sem que o resultado de um teste interfira no outro
 * (testes independentes é uma das boas práticas citadas no resumo).
 */
class CarrinhoTest {

    private Carrinho carrinho;

    @BeforeEach
    void criarCarrinhoVazio() {
        carrinho = new Carrinho();
    }

    @Test
    @DisplayName("Um carrinho novo deve começar vazio")
    void carrinhoNovoDeveComecarVazio() {
        assertTrue(carrinho.estaVazio());
        assertEquals(0, carrinho.quantidadeDeItens());
        assertEquals(0, carrinho.total());
    }

    @Test
    @DisplayName("Deve somar o subtotal de todos os produtos adicionados")
    void deveSomarSubtotalDosProdutos() {
        carrinho.adicionar(new Produto("Caneta", 2.5, 3));   // 7.5
        carrinho.adicionar(new Produto("Caderno", 10.0, 2)); // 20.0

        assertFalse(carrinho.estaVazio());
        assertEquals(2, carrinho.quantidadeDeItens());
        assertEquals(27.5, carrinho.total(), 0.0001);
    }

    @Test
    @DisplayName("Deve remover um produto do carrinho")
    void deveRemoverProdutoDoCarrinho() {
        Produto caneta = new Produto("Caneta", 2.5, 3);
        carrinho.adicionar(caneta);

        carrinho.remover(caneta);

        assertTrue(carrinho.estaVazio());
    }

    @Test
    @DisplayName("Não deve permitir remover um produto que não está no carrinho")
    void naoDevePermitirRemoverProdutoInexistente() {
        Produto caneta = new Produto("Caneta", 2.5, 3);

        ProdutoNaoEncontradoException excecao = assertThrows(
                ProdutoNaoEncontradoException.class,
                () -> carrinho.remover(caneta));

        assertTrue(excecao.getMessage().contains("Caneta"));
    }

    @Test
    @DisplayName("Deve aplicar desconto percentual ao total do carrinho")
    void deveAplicarDescontoAoTotal() {
        carrinho.adicionar(new Produto("Mouse", 100.0, 1));

        carrinho.aplicarCupom(10); // 10% de desconto

        assertEquals(90.0, carrinho.total(), 0.0001);
    }

    @Test
    @DisplayName("Não deve aceitar cupom de desconto fora do intervalo de 0 a 100")
    void naoDeveAceitarCupomInvalido() {
        assertThrows(ProdutoInvalidoException.class, () -> carrinho.aplicarCupom(150));
        assertThrows(ProdutoInvalidoException.class, () -> carrinho.aplicarCupom(-10));
    }

    @Test
    @DisplayName("Não deve adicionar um produto nulo ao carrinho")
    void naoDeveAdicionarProdutoNulo() {
        assertThrows(ProdutoInvalidoException.class, () -> carrinho.adicionar(null));
    }
}
