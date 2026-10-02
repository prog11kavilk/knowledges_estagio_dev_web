public class Conta {

    //atributos da classe Conta:
    private double saldo;
    private int agencia;
    private int numero;
    private Cliente titular;
    private static int total;       //static: atributo da classe

    //CONSTRUTOR
    public Conta(int agencia, int numero) {
        Conta.total++;
        System.out.println("total de contas: "+total);
        this.agencia = agencia;
        this.numero = numero;
        this.saldo = 0;

        this.agencia = agencia;
        this.numero = numero;
        System.out.println("Estou criando uma conta de n° "+this.numero);
    }
    //MÉTODOS DA CONTA
    public void deposita(double valor) {
        this.saldo += valor;
    }

    public boolean saca(double valor) {
        if (this.saldo >= valor) {
            this.saldo -= valor;
            return true;
        } else {
            return false;
        }
    }

    public boolean transfere(double valor, Conta destino) {
        if (this.saldo >= valor) {
            this.saldo -= valor;
            destino.deposita(valor);
            return true;
        }
        return false;
    }

    //encapsulamento do atributo privado
    public double getSaldo() {
        return this.saldo;
    }

    //retorna o número privado da conta
    public int getNumero() {
        return this.numero;
    }

    public void setNumero(int numero) {
        if(numero <= 0) {
            System.out.println("Não pode valores negativos ou zerados");
            return;
        }
        this.numero = numero;
    }

    public int getAgencia() {
        return this.agencia;
    }

    public void setAgencia(int agencia) {
        if(agencia <= 0) {
            System.out.println("não pode valores negativos ou zerados");
            return;
        }
        this.agencia = agencia;

    }
    public void setTitular(Cliente titular) {
        this.titular = titular;
    }

    public Cliente getTitular() {
        return this.titular;
    }
    public static int getTotal() {
        return Conta.total;
    }
}