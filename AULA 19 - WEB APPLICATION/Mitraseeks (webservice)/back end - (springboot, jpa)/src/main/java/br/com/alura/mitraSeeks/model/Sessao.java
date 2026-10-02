package br.com.alura.mitraSeeks.model;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "sessoes")
public class Sessao {

    @Id
    private String token;
    @ManyToOne(optional = false)
    private Usuario usuario;
    private LocalDateTime criadaEm;
    private LocalDateTime expiraEm;

    public Sessao() {}

    public Sessao(String token, Usuario usuario, LocalDateTime expiraEm) {
        this.token = token;
        this.usuario = usuario;
        this.criadaEm = LocalDateTime.now();
        this.expiraEm = expiraEm;
    }

    public boolean expirada() {
        return expiraEm.isBefore(LocalDateTime.now());
    }

    public String getToken() {
        return token;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public LocalDateTime getCriadaEm() {
        return criadaEm;
    }

    public LocalDateTime getExpiraEm() {
        return expiraEm;
    }
}
