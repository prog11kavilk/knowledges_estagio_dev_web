import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class testandoListas{
    public static void main(String[] args) {
        System.out.println(">>> SISTEMA DE CADASTRO ACADÊMICO <<<\n            SEJA BEM VINDO!");
        Scanner leitura = new Scanner(System.in);
        List<DisciplinasAtributos> listaDisciplinas = new ArrayList<>();

                //Tabela com os dados gerais
        System.out.println("\nQual seu nome completo? ");
        var nome = leitura.nextLine();
        System.out.println("Instituição de ensino: ");
        var faculdade = leitura.nextLine();
        System.out.println("Curso que faz ou fará: ");
        var curso = leitura.nextLine();
        System.out.println("Semestre atual: ");
        var semestre = leitura.nextInt();

        //lista de todas as disciplinas
        System.out.println("Quantas disciplinas realizará?");
        var totalDisc = leitura.nextInt();

        //verificacao de 1 ou mais disciplinas
        if(totalDisc > 1) {
            System.out.println("Ok, vamos efetuas suas matrículas nas "+totalDisc+" disciplinas :)");}
        else {
            System.out.println("Ok, vamos te matricular nessa disciplina!");
        }
            //inicializo os objetos no laço do array list

        for (int i = 0; i <= totalDisc - 1; i++) {
            if (i <= totalDisc) {
                System.out.println("Nome da " + (i + 1) + " ª disciplina: ");
                var nomeDisc = leitura.next();
                System.out.println("Professor: ");
                var professorDisc = leitura.next();
                System.out.println("Total de aulas p/semestre: ");
                var aulas = leitura.nextInt();
                int faltasPerm = (int) (aulas * 0.75);
                DisciplinasAtributos novaDisc = new DisciplinasAtributos(nomeDisc, professorDisc, faltasPerm);
                listaDisciplinas.add(novaDisc);
            }
        }

            //output do cabecalho geral
            System.out.println("***** " + faculdade.toUpperCase() + " *****\nCURSO: " + curso + "\n" + "SEMESTRE: "
                    + semestre + "°\nESTUDANTE: " + nome + "\n*********************\n\n" + listaDisciplinas.toString());
            //output da lista formatada com toString()
            System.out.println("");
            //output de possível alteração na lista de disciplinas
            System.out.println("Deseja adicionar ou remover disciplinas? (s/n");
            var alterar = leitura.nextLine();

            //lê a requisição para modificar a lista
            if (alterar == "s" || alterar == "S" || alterar == "sim") {
                System.out.println("1) Adicionar disciplina\n2)Remover disciplina");
                var guardaResposta = leitura.nextInt();
                if (guardaResposta == 1) {
                    System.out.println("Nome da disciplina: ");
                    var nomeDisc = leitura.next();
                    System.out.println("Professor: ");
                    var professorDisc = leitura.next();
                    System.out.println("Total de aulas p/semestre: ");
                    var aulas = leitura.nextInt();
                    int faltasPerm = (int) (aulas * 0.75);
                    DisciplinasAtributos novaDisc = new DisciplinasAtributos(nomeDisc, professorDisc, faltasPerm);

                } else if (guardaResposta == 2) {
                    System.out.println("Qual o número da disciplina que quer remover?");
                    var numRemocao = leitura.next();
                    listaDisciplinas.remove(numRemocao);
                } else {
                    System.out.println("Ops! essa disciplina não existe.");
                }
            } else if (alterar == "n" || alterar == "N" || alterar == "não") {
                System.out.println("Matrículas bem-sucedidas. Boas aulas, " + nome + " !");
            }





    }
}
