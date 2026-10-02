import java.util.Arrays;
import java.util.Scanner;

public class loop {
    public static void main(String[] args) {
        Scanner leitura = new Scanner(System.in);
        double mediaAvaliacao = 0;
        double nota = 0;

        for (int i = 0; i < 3; i++) {
            System.out.println("Diga sua avaliação para o filme (0 a 10):");
            nota = leitura.nextDouble();
            mediaAvaliacao += nota;
        }
        System.out.println("Nota do filme: " + mediaAvaliacao/3);
    }
}