package br.com.alura.mitraSeeks.model;

import br.com.alura.mitraSeeks.service.traducao.ConsultaMyMemory;
import jakarta.persistence.*;

@Entity
@Table(name = "filmes")
public class Filmes {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true)
    private String titulo;
    private Integer duracao;
    private Integer anoLancamento;
    private Double avaliacao;
    @Enumerated(EnumType.STRING)
    private Categoria genero;
    private String atores;
    @Column(columnDefinition = "TEXT")
    private String sinopse;
    private String poster;

    public Filmes() {}

    public Filmes(DadosFilme dadosFilme) {
        this.titulo = dadosFilme.titulo();
        this.duracao = extrairNumero(dadosFilme.duracao());
        this.anoLancamento = extrairNumero(dadosFilme.anoLancamento());
        completarAvaliacaoEGenero(dadosFilme);
        this.atores = dadosFilme.atores();
        this.sinopse = ConsultaMyMemory.obterTraducao(dadosFilme.sinopse()).trim();
        this.poster = dadosFilme.poster();
    }

    // Usado também para completar filmes salvos antes de existirem os campos de avaliação e gênero
    public void completarAvaliacaoEGenero(DadosFilme dadosFilme) {
        try {
            this.avaliacao = Double.valueOf(dadosFilme.avaliacao());
        } catch (NumberFormatException | NullPointerException ex) {
            this.avaliacao = null;
        }
        this.genero = Categoria.primeiraConhecida(dadosFilme.genero());
    }

    // O OMDb manda "148 min" e "2010"; guarda só o número, ou null quando vem "N/A"
    private static Integer extrairNumero(String texto) {
        if (texto == null) {
            return null;
        }
        var numero = texto.replaceAll("\\D.*", "");
        return numero.isEmpty() ? null : Integer.valueOf(numero);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public Integer getDuracao() {
        return duracao;
    }

    public void setDuracao(Integer duracao) {
        this.duracao = duracao;
    }

    public Integer getAnoLancamento() {
        return anoLancamento;
    }

    public void setAnoLancamento(Integer anoLancamento) {
        this.anoLancamento = anoLancamento;
    }

    public Double getAvaliacao() {
        return avaliacao;
    }

    public void setAvaliacao(Double avaliacao) {
        this.avaliacao = avaliacao;
    }

    public Categoria getGenero() {
        return genero;
    }

    public void setGenero(Categoria genero) {
        this.genero = genero;
    }

    public String getAtores() {
        return atores;
    }

    public void setAtores(String atores) {
        this.atores = atores;
    }

    public String getSinopse() {
        return sinopse;
    }

    public void setSinopse(String sinopse) {
        this.sinopse = sinopse;
    }

    public String getPoster() {
        return poster;
    }

    public void setPoster(String poster) {
        this.poster = poster;
    }

    @Override
    public String toString() {
        return
                "titulo='" + titulo + '\'' +
                        ", duracao=" + duracao + " min" +
                        ", anoLancamento=" + anoLancamento +
                        ", avaliacao=" + avaliacao +
                        ", genero=" + genero +
                        ", atores='" + atores + '\'' +
                        ", poster='" + poster + '\'' +
                        ", sinopse='" + sinopse + '\'';
    }
}
