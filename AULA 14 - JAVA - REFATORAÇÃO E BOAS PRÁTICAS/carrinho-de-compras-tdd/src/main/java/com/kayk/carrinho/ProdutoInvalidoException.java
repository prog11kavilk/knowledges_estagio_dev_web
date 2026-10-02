package com.kayk.carrinho;

/**
 * Exceção lançada quando um {@link Produto} é criado com dados inválidos
 * (nome vazio, preço negativo ou quantidade menor ou igual a zero).
 *
 * Faz parte do módulo "Lidando com Exceptions" do curso: usamos uma
 * exceção não-verificada (unchecked) e específica do domínio, em vez de
 * lançar uma RuntimeException genérica sem contexto.
 */
public class ProdutoInvalidoException extends RuntimeException {

    public ProdutoInvalidoException(String mensagem) {
        super(mensagem);
    }
}
