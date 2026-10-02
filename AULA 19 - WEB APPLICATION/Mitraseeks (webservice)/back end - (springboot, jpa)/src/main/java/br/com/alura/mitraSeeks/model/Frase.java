package br.com.alura.mitraSeeks.model;

import jakarta.persistence.*;

/**
 * Citação clássica de um personagem, ligada a um filme ou série do catálogo.
 * Veio do projeto "java-Mitraseeks- citações" (desafio das frases) e agora mora aqui.
 * As frases são carregadas de resources/catalogo/frases.txt pelo FraseInicializador.
 */
@Entity
@Table(name = "frases")
public class Frase {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    // "filme" ou "serie": diz em qual tabela do catálogo está o título (ex.: Fargo existe nas duas)
    private String tipo;
    private String titulo;
    private String personagem;
    @Column(columnDefinition = "TEXT")
    private String frase;
    private String poster;
    // id do filme/série no catálogo, para o link da página de detalhes
    private Long tituloId;
    // Cores predominantes do cartaz em hex, separadas por vírgula (ex.: "#1a2b3c,#445566,#778899")
    private String cores;

    public Frase() {}

    public Frase(String tipo, String titulo, String personagem, String frase) {
        this.tipo = tipo;
        this.titulo = titulo;
        this.personagem = personagem;
        this.frase = frase;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getPersonagem() {
        return personagem;
    }

    public void setPersonagem(String personagem) {
        this.personagem = personagem;
    }

    public String getFrase() {
        return frase;
    }

    public void setFrase(String frase) {
        this.frase = frase;
    }

    public String getPoster() {
        return poster;
    }

    public void setPoster(String poster) {
        this.poster = poster;
    }

    public Long getTituloId() {
        return tituloId;
    }

    public void setTituloId(Long tituloId) {
        this.tituloId = tituloId;
    }

    public String getCores() {
        return cores;
    }

    public void setCores(String cores) {
        this.cores = cores;
    }

    @Override
    public String toString() {
        return "titulo='" + titulo + '\'' +
                ", personagem='" + personagem + '\'' +
                ", frase='" + frase + '\'';
    }
}
