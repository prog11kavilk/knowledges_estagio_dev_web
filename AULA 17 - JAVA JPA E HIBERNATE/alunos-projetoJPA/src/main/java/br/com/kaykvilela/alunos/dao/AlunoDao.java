package br.com.kaykvilela.alunos.dao;

import br.com.kaykvilela.alunos.modelo.Aluno;

import javax.persistence.EntityManager;
import java.util.List;

public class AlunoDao {

    private EntityManager em;

    //Construtor de acionamento do provedor do banco (EntityManager)
    public AlunoDao(EntityManager em) {
        this.em = em;
    }

    //metodo de inserção do nosso objeto na database
    public void cadastrar(Aluno aluno){
        this.em.persist(aluno);
    }

    //busca todos os alunos cadastrados
    public List<Aluno> listarTodos(){
        return em.createQuery("select a from Aluno a", Aluno.class).getResultList();
    }

    //busca um único aluno pelo nome exato
    public Aluno buscarPorNome(String nome){
        return em.createQuery("select a from Aluno a where a.nome = :nome", Aluno.class)
                .setParameter("nome", nome)
                .getSingleResult();
    }

    //busca todos os alunos matriculados em um curso
    public List<Aluno> buscarPorCurso(String curso){
        return em.createQuery("select a from Aluno a where a.curso = :curso", Aluno.class)
                .setParameter("curso", curso)
                .getResultList();
    }
}
