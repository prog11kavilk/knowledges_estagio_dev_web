import java.util.Scanner;

public class MultiplosRestritos {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        System.out.println("Olá, informe um número:");
        int num = scanner.nextInt();

        int atual = num;

        System.out.println(String.format("Qual número de parada dos múltiplos de (%d)?", num));
        int limite = scanner.nextInt();

        System.out.println(String.format("*** Múltiplos de %d até %d ***\n", num, limite));

        while(atual <= limite) {
            System.out.print(atual+", ");
            atual += num;

        }
    }
}
