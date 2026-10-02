package br.com.kaykvilela.alunos.modelo;
import br.com.kaykvilela.alunos.dao.AlunoDao;
import br.com.kaykvilela.alunos.util.JPAutil;

import javax.persistence.EntityManager;
import java.util.List;

public class CadastroAluno {
    public static void main(String[] args) {

        EntityManager em = new JPAutil().getEntityManager();
        AlunoDao dao = new AlunoDao(em);

        Categoria faculdade = new Categoria("Ciência de Dados");

        Aluno a1 = new Aluno();
        a1.setNome("Josemir Lima");
        a1.setIdade(21);
        a1.setCurso("Ciência de Dados");
        a1.setAproveitamento(0.95);

        //crie outros 5 alunos com atributos e APAGUE essa linha

        em.getTransaction().begin();
        dao.cadastrar(a1);
        em.getTransaction().commit();

        Aluno encontrado = dao.buscarPorNome("Josemir Lima");
        System.out.println("Busca por nome: " + encontrado.getNome());

        List<Aluno> alunosDoCurso = dao.buscarPorCurso("Ciência de Dados");
        System.out.println("Alunos no curso Ciência de Dados: " + alunosDoCurso.size());

        List<Aluno> todos = dao.listarTodos();
        System.out.println("Total de alunos cadastrados: " + todos.size());

        em.close();
    }
}
