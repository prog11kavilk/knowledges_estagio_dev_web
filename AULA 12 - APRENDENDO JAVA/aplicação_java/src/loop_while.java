import java.util.Arrays;
import java.util.Scanner;

public class loop_while {

    public static void main(String[] args) {
        //inicializa as variáveis
        Scanner leitura = new Scanner(System.in);
        double mediaAvaliacao = 0;
        double nota = 0;
        double k = 0;
        //gera um loop indeterminadamente
        while (nota != -1) {
            System.out.println("Diga sua avaliação para o filme ou -1 para encerrar:");
            nota = leitura.nextDouble();
            //
            if(nota != -1) {
                mediaAvaliacao += nota;
                k++;
            }
        }
        System.out.println("Nota do Filme: " +mediaAvaliacao / k);
    }
}