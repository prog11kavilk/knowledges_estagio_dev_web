public class TesteComAssociacao {
    public static void main(String[] args) {
        Conta contaDoNeymar = new Conta();
        contaDoNeymar.titular = new Cliente();

        contaDoNeymar.titular.nome = "Neymar peidão";
        System.out.println(contaDoNeymar.titular.nome);
    }
}
