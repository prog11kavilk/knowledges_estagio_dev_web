package br.com.kaykvilela.tabelafipe.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

//representa a lista dos modelos apenas com o cósigo e nome
@JsonIgnoreProperties(ignoreUnknown = true)
public record Modelos(List<dadosVeiculo> modelos) {
}
