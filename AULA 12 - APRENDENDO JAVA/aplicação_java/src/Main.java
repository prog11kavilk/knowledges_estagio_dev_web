//definição da classe base do programa
public class Main{

    //classe secundaria
    public static void main(String[] args) {
        System.out.println("Esse é o armexUltra");
        System.out.println("\nFilme: Odisséia - a história de Odisseu");

        //variavel de inteiro
        int anoDeLancamento = 2026;
        System.out.print("\nANO DE LANÇAMENTO: "+ anoDeLancamento);
        //variável booleana
        boolean incluidoNoPlano = true;
        //variável de decimal com uma casa
        double notaDoFilme = 7.9;
        System.out.print("\nNOTA DO FILME: "+ notaDoFilme);
        double mediaDasNotas = (6.5 + 9.2 + 8.1)/3;
        System.out.print(mediaDasNotas);
        //classe String engloba a variável sinopse
        String sinopse;
        sinopse = ("\nBaseado na obra Odisséia de Homero, o filme evidencia a vida do guerreiro Olisseu." +
                "\nIncluindo diversas reviravoltas, mitologia, guerra de Tróia e sua redenção.");
        System.out.println(sinopse);

        //calcula a classificação por estrelas desse filme
        int classificacao = (int)(mediaDasNotas /2);
        System.out.println(String.format("%d estrelas", classificacao));
    }
}