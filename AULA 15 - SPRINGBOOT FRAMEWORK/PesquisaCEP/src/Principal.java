import java.io.IOException;
import java.util.Scanner;

public class Principal {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ConsultaCep consultaCep = new ConsultaCep();

        System.out.println("PESQUISA DE ENDEREÇOS\n\nDigite o CEP (ex: 98765-01) ");
        var cep = scanner.nextLine();

        try {
            Endereco novoEndereco = consultaCep.buscaEndereco(cep);
            System.out.println(novoEndereco);
            GeradorDeArquivo gerador = new GeradorDeArquivo();
            gerador.geraJson(novoEndereco);
        } catch(RuntimeException | InterruptedException | IOException e){
            System.out.println(e.getMessage());
        }
    }
}