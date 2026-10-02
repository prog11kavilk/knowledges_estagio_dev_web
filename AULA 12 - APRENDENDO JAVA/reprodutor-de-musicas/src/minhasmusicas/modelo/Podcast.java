package minhasmusicas.modelo;

public class Podcast extends Audio {
    //atributos da classe podcasts
    private String locutor;
    private String descricao;

    //métodos para a classe podcasts
    public String getLocutor() {
        return locutor;
    }

    public void setLocutor(String locutor) {
        this.locutor = locutor;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
    //sobrescreve o metodo classificacao, caso atenda a condicao:
    @Override
    public int getClassificacao() {
        if (this.getTotalCurtidas() > 500) {
            return (10);
        } else {
            return (2);
        }
    }
}
