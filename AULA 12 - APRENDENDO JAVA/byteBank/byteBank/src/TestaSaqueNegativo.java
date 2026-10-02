public class TestaSaqueNegativo {
    public static void main(String[] args) {
        Conta conta  = new Conta();
        conta.deposita(100);
        conta.saca(300);
        System.out.println(conta.saca(300));

        conta.saca(101);
        // referência ao metodo do saldo por encapsulamento
        System.out.println(conta.getSaldo());

        /*proibido:
        conta.saldo -= 101;
        System.out.println(conta.saldo);*/
    }
}

