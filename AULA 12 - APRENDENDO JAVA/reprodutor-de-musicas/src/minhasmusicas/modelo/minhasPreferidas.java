package minhasmusicas.modelo;

public class minhasPreferidas {

    public void inclui(Audio audio) {
        if(audio.getClassificacao() >=8) {
            System.out.println(audio.getTitulo()+" é sucesso absoluto na sua comunidade!!");
        } else {
            System.out.println(audio.getTitulo()+" é uma boa escolha para você");
        }
    }
}
