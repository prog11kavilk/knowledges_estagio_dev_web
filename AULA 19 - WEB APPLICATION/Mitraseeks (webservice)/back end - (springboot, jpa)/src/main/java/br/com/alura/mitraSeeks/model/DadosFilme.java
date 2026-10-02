package br.com.alura.mitraSeeks.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record DadosFilme(@JsonAlias("Title") String titulo,
                         @JsonAlias("Runtime") String duracao,
                         @JsonAlias("Year") String anoLancamento,
                         @JsonAlias("imdbRating") String avaliacao,
                         @JsonAlias("Genre") String genero,
                         @JsonAlias("Actors") String atores,
                         @JsonAlias("Plot") String sinopse,
                         @JsonAlias("Poster") String poster,
                         @JsonAlias("Type") String tipo,
                         @JsonAlias("Response") String resposta,
                         @JsonAlias("Error") String erro) {
}
