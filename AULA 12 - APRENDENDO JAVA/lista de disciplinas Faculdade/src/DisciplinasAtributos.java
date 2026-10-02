import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class DisciplinasAtributos {
    String nomeDisc;
    String professorDisc;
    int faltasPerm;


    public DisciplinasAtributos(String nomeDisc, String professorDisc, int faltasPerm) {
        this.nomeDisc = nomeDisc;
        this.professorDisc = professorDisc;
        this.faltasPerm = faltasPerm;
}

    @Override
    public String toString() {
        return "* Disciplina = " +nomeDisc+"| * Professor = "+professorDisc+" | * Faltas permitidas = "+faltasPerm+" * |\n";
    }
}