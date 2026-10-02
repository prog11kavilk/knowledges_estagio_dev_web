public class Conta {
        //atributos da classe Conta:
        private double saldo;
        int agencia;
        int numero;
        Cliente titular = new Cliente();

        //MÉTODOS DA CONTA
        public void deposita(double valor) {
                this.saldo += valor;
        }
        public boolean saca(double valor) {
                if(this.saldo >= valor){
                        this.saldo -= valor;
                        return true;
                } else {
                        return false;
                }
        }
        public boolean transfere(double valor, Conta destino) {
                if(this.saldo >= valor) {
                        this.saldo -=valor;
                        destino.deposita(valor);
                        return true;
                }
                return false;
        }
        //encapsulamento do atributo privado
        public double getSaldo() {
                return this.saldo;
        };

}
