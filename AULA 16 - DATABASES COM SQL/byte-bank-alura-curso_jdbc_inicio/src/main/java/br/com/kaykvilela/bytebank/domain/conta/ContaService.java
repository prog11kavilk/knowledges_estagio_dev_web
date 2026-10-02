package br.com.kaykvilela.bytebank.domain.conta;

import br.com.kaykvilela.bytebank.ConnectionFactory;
import br.com.kaykvilela.bytebank.domain.RegraDeNegocioException;
import br.com.kaykvilela.bytebank.domain.cliente.Cliente;
import com.mysql.cj.xdevapi.PreparableStatement;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;

public class ContaService {

    private ConnectionFactory connection;

    public ContaService(){
        this.connection = new ConnectionFactory();
        this.contas = listarContasAbertas();
    }

    private Set<Conta> contas = new HashSet<>();

    public Set<Conta> listarContasAbertas() {
        Connection conn = connection.recuperarConexao();
        try {
            return new ContaDAO(conn).listar();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public BigDecimal consultarSaldo(Integer numeroDaConta) {
        var conta = buscarContaPorNumero(numeroDaConta);
        return conta.getSaldo();
    }

    public void abrir(DadosAberturaConta dadosDaConta) {
       Connection conn = connection.recuperarConexao();
       new ContaDAO(conn).salvar(dadosDaConta);
       var cliente = new Cliente(dadosDaConta.dadosCliente());
       contas.add(new Conta(dadosDaConta.numero(), cliente));
    }

    public void realizarSaque(Integer numeroDaConta, BigDecimal valor) {
        var conta = buscarContaPorNumero(numeroDaConta);
        if (valor.compareTo(BigDecimal.ZERO) <= 0) {
            throw new RegraDeNegocioException("Valor do saque deve ser superior a zero!");
        }

        if (valor.compareTo(conta.getSaldo()) > 0) {
            throw new RegraDeNegocioException("Saldo insuficiente!");
        }

        conta.sacar(valor);
        alterar(conta, conta.getSaldo());
    }

    public void realizarDeposito(Integer numeroDaConta, BigDecimal valor) {
        var conta = buscarContaPorNumero(numeroDaConta);
        if (valor.compareTo(BigDecimal.ZERO) <= 0) {
            throw new RegraDeNegocioException("Valor do deposito deve ser superior a zero!");
        }

        conta.depositar(valor);
        alterar(conta, conta.getSaldo());
    }

    public void alterar(Conta conta, BigDecimal valor){
        Connection conn = connection.recuperarConexao();
        new ContaDAO(conn).alterar(conta.getNumero(), valor);
    }

    public void encerrar(Integer numeroDaConta) {
        var conta = buscarContaPorNumero(numeroDaConta);
        if (conta.possuiSaldo()) {
            throw new RegraDeNegocioException("Conta não pode ser encerrada pois ainda possui saldo!");
        }

        Connection conn = connection.recuperarConexao();
        new ContaDAO(conn).encerrar(conta.getNumero());
        contas.remove(conta);
    }

    private Conta buscarContaPorNumero(Integer numero) {
        return contas
                .stream()
                .filter(c -> c.getNumero().equals(numero))
                .findFirst()
                .orElseThrow(() -> new RegraDeNegocioException("Não existe conta cadastrada com esse número!"));
    }
}
