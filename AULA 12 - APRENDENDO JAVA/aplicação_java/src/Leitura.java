import java.util.Arrays;
import java.util.Scanner;

public class Leitura {
    public static void main(String[] args) {
        //leitura de dados com a classe 'scanner'
        Scanner leitura = new Scanner(System.in);

        System.out.println("Digite seu filme favorito: ");
        String filme = leitura.nextLine();
        System.out.println("Qual o ano de lançamento?");
        int anoDeLancamento = leitura.nextInt();
        System.out.println("Diga sua avaliação para o filme (0 a 10):");
        double notaFilme = leitura.nextDouble();
        int estrelasFilme = (int) (notaFilme / 2);
        //exibição das respostas
        System.out.println(String.format("Filme favorito - %s", filme));
        System.out.println(String.format("Ano de lançamento - %d", anoDeLancamento));
        System.out.println(String.format("Avaliação - %f | %d Estrelas", notaFilme, estrelasFilme));

    }
}
