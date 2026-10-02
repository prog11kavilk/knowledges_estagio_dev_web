public class testaValores {

    public static void main(String[] args) {
        Conta conta = new Conta (1010, 48730);
        Conta conta2 = new Conta(1010, 43921);

        conta.setAgencia(403290);
        conta.deposita(200);
        System.out.println("saldo conta1: "+conta.getSaldo());

        System.out.println(Conta.getTotal());

    }
}
