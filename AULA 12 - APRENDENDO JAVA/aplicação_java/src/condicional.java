public class condicional {
    public static void main(String[] args) {
        int anoDeLancamento = 2026;
        boolean incluidoNoPlano = true;
        double notaDoFilme = 7.9;
        String tipoPlano = "grátis";

        if (anoDeLancamento >= 2025){
            System.out.println("Lançamento em alta!");
        } else {
            System.out.println("Filmes retrô que vale a pena ver!");
        }
        if(incluidoNoPlano && tipoPlano.equals("plus")) {
            System.out.println("Filme liberado!");
        }else {
            System.out.println("Necessário assinatura para assistir...");
        }
    }
}
