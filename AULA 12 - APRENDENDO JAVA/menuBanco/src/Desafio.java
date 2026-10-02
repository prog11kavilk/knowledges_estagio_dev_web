import java.sql.SQLOutput;
import java.util.Scanner;

public class Desafio {
    public static void main(String[] args) {
        String cliente = "Peter parkis";
        String tipoConta = "Corrente";
        double saldo = 0;
        System.out.println("************BANCO NEOTIPS************");
        System.out.println("\nNome do cliente > "+cliente);
        System.out.println("Tipo da conta > "+tipoConta);
        System.out.println("Saldo disponível > "+saldo);
        System.out.println("\n**********************************");
        //textBox do menu
        int opcao = 0;
        String menu = """
                *** Menu de Operações ***
                    1) Consultar saldo
                    2) Receber pix 
                    3) Enviar pix 
                    4) Sair do banco
                >> DIGITE A OPÇÃO DESEJADA: 
                """;
        Scanner leitura = new Scanner(System.in);

        while(opcao != 4) {
            System.out.println(menu);
            opcao = leitura.nextInt();

            if (opcao == 1) {
                System.out.println("Saldo atual: R$ " + saldo);
            }
            if (opcao == 2) {
                System.out.println("Qual o valor recebido?\n");
                double valor = leitura.nextDouble();
                saldo += valor;
                System.out.println("Pix recebido!\nSaldo atual: R$ " + saldo);
            }
            if (opcao == 3) {
                System.out.println("Qual o valor a pagar?\n ");
                double valor = leitura.nextDouble();
                if (saldo < valor) ;
                {
                    System.out.println("Saldo insuficiente para pix nesse valor.");
                }
                saldo -= valor;
                System.out.println("Pix realizado!\nSaldo disponível: R$ " + saldo);
            }
            else if (opcao != 1 || opcao !=2 || opcao !=3 || opcao !=4) {
                System.out.println("Ops...essa opção não existe!");
            }
        }
        if(opcao == 4) {
            System.out.println("Fechando o aplicativo. Até mais, "+cliente);
        }
    }
}


