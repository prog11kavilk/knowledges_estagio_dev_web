import java.util.Scanner;

public class podeBrinquedo {
    public static void main(String[] args) {
        System.out.println("SEJA BEM VINDO À POPPYPLAY\n");
        Scanner requisitos = new Scanner(System.in);

        //pergunta e armazena o nome, altura e idade
        System.out.println("Como você chama?");
        String name = requisitos.nextLine();

        System.out.println(String.format("Qual a sua altura em cm, %s?", name));
        int height = requisitos.nextInt();

        System.out.println("e a sua idade?");
        int idade = requisitos.nextInt();

        if(height >= 120 || idade >=10) {
            System.out.println("Liberado! boa diversão"+name+"!");
        }
        else {
            if(height <1.20) {
                System.out.println("Sua altura é inferior à mínima do brinquedo");
            }
            if(idade < 10) {
                System.out.println("Você naõ tem idade para esse brinquedo");
            }
        }
    }
}




