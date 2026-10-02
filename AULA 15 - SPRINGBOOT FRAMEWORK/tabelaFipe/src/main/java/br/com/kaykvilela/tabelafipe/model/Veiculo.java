package br.com.kaykvilela.tabelafipe.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

//apelida os atributos do Json excluindo os que não usamos
@JsonIgnoreProperties(ignoreUnknown = true)
public record Veiculo(
        @JsonAlias("Valor") String valor, @JsonAlias("Marca") String marca,
        @JsonAlias("Modelo") String modelo, @JsonAlias("AnoModelo")Integer ano, @JsonAlias ("Combustivel") String combustivel) {
}
