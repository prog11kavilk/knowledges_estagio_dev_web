package minhasmusicas.principal;            //declarando pacote destino da classe
import minhasmusicas.modelo.Musica;         //importando pacote para herdar recursos
import minhasmusicas.modelo.Podcast;
import minhasmusicas.modelo.minhasPreferidas;

public class main {
    public static void main(String[] args) {

        Musica minhamusica = new Musica();
        minhamusica.setTitulo("Fire Up");
        minhamusica.setCantor("Michael Jackson");
        for(int i = 0; i < 700; i++); {
            minhamusica.reproduz();
        }for( int i = 0; i < 50; i++){
            minhamusica.curte();
        }

        Podcast meuPodcast = new Podcast();
        meuPodcast.setTitulo("Como programar em Java - para iniciantes");
        meuPodcast.setLocutor("Seu Madruga");
        for(int i = 0; i < 500; i++); {
            meuPodcast.reproduz();
        }
        for( int i = 0; i < 300; i++){
            meuPodcast.curte();
        }

        minhasPreferidas preferidas = new minhasPreferidas();
        preferidas.inclui(meuPodcast);
        preferidas.inclui(minhamusica);
    }
}
