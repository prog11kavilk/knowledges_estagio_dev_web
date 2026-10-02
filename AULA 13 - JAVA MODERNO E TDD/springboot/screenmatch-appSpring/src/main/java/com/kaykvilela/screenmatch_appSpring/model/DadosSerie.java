package com.kaykvilela.screenmatch_appSpring.model;
import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

//@JsonIgnoreProperties -> anotação do spring que só apresenta os atributos declarados na série
//@JsonAlias -> apelida os atributos do arquivo Json para os nomes usados para a série

@JsonIgnoreProperties(ignoreUnknown = true)
public record DadosSerie(@JsonAlias("Title") String titulo, @JsonAlias
        ("totalSeasons") Integer totalTemporadas,@JsonAlias("imdbRating") Double avaliacao){
}
