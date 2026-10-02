package minhasmusicas.modelo;

//atributos do audio
public class Audio {
    private String titulo;
    private int totalReproducoes;
    private int totalCurtidas;
    private int classificacao;

    //métodos referentes ao objeto audio
    public int getTotalReproducoes() {
        return totalReproducoes;
    }

    public void setTotalReproducoes(int totalReproducoes) {
        this.totalReproducoes = totalReproducoes;
    }

    public int getTotalCurtidas() {
        return totalCurtidas;
    }

    public void setTotalCurtidas(int totalCurtidas) {
        this.totalCurtidas = totalCurtidas;
    }

    public int getClassificacao() {
        return classificacao;
    }

    public void setClassificacao(int classificacao) {
        this.classificacao = classificacao;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }
    public void curte () {
        this.totalCurtidas++;
    }
    public void reproduz () {
        this.totalReproducoes++;
    }
}


