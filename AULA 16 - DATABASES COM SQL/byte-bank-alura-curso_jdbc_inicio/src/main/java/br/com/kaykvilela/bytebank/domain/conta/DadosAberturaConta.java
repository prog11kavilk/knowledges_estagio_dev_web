package br.com.kaykvilela.bytebank.domain.conta;

import br.com.kaykvilela.bytebank.domain.cliente.DadosCadastroCliente;

public record DadosAberturaConta(Integer numero, DadosCadastroCliente dadosCliente) {
}
