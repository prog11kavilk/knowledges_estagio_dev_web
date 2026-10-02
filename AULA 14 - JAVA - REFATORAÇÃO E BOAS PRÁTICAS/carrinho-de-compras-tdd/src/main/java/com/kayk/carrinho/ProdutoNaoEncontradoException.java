package com.kayk.carrinho;

/**
 * Exceção lançada ao tentar remover do carrinho um produto que não
 * está presente nele.
 */
public class ProdutoNaoEncontradoException extends RuntimeException {

    public ProdutoNaoEncontradoException(String mensagem) {
        super(mensagem);
    }
}
