package com.kaykvilela.screenmatch_appSpring.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties (ignoreUnknown = true)

public record DadosTemporada(@JsonAlias("season") Integer numeroTemporada, @JsonAlias("episodes")
List<DadosEpisodio> episodios) {
}
