package br.com.kaykvilela.alunos.util;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class JPAutil {
    private static final EntityManagerFactory FACTORY = Persistence.
            createEntityManagerFactory("aluno");

    public EntityManager getEntityManager(){
        return FACTORY.createEntityManager();
    }
}
