public class desafios {
    public static void main(String[] arg) {
        int nota1 = 10;
        double nota2 = 7.8;
        double mediaNotas = (nota1 + nota2);
        //print da média já formatada em dois números após a vírgula
        System.out.println(String.format("A média de %d e %.2f = %.2f", nota1, nota2,mediaNotas));
    }
}