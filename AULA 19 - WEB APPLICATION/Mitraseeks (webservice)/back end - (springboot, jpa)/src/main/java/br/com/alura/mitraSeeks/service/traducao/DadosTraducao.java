package br.com.alura.mitraSeeks.service.traducao;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record DadosTraducao(@JsonAlias(value = "responseData")DadosResposta dadosResposta,
                            @JsonAlias(value = "responseStatus") String status,
                            @JsonAlias(value = "quotaFinished") Boolean cotaEsgotada) {
}
