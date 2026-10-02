public class TestaCliente {
    public static void main(String[] args) {
        Cliente cliente  = new Cliente();
        cliente.nome = "GuestHuman";
        cliente.cpf = "255.433.900-01";
        cliente.profissao = "programador de softwares";

        //instancia uma conta e executa nela o método deposita
        Conta contaHuman= new Conta();
        contaHuman.deposita(600);

        contaHuman.titular = cliente;
        System.out.println(String.format("Nome: %s\nProfissão: %s\nCPF: %s\nsaldo da conta: ", contaHuman.titular.nome, cliente.profissao, cliente.cpf, contaHuman.getSaldo()));
    }
}
