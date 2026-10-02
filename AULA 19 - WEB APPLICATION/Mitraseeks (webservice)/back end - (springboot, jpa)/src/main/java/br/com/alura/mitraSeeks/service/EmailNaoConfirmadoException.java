package br.com.alura.mitraSeeks.service;

// Login com senha certa, mas o email ainda não foi confirmado: o front usa o email para pedir o código
public class EmailNaoConfirmadoException extends RuntimeException {

    private final String email;

    public EmailNaoConfirmadoException(String email) {
        super("Confirme seu email para entrar. Enviamos um novo código para " + email);
        this.email = email;
    }

    public String getEmail() {
        return email;
    }
}
