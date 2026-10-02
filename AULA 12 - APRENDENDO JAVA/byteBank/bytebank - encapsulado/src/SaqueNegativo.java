public class SaqueNegativo {
    public static void main(String[] args) {
        Conta conta = new Conta(97,  77777);
        conta.deposita(100);;
        System.out.println(conta.saca(101));

        conta.saca(101);
        System.out.println(conta.getSaldo());


        System.out.println(conta.saca(101));

        conta.saca(101);
        System.out.println(conta.getSaldo());
    }
}
