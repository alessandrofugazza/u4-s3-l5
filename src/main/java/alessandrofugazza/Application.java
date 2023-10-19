package alessandrofugazza;

import com.github.javafaker.Faker;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class Application {
    private static final EntityManagerFactory emf = Persistence.createEntityManagerFactory("u4-s3-l5");
    public static void main(String[] args) {
        Logger log = LoggerFactory.getLogger(Application.class);
        Faker faker = new Faker();
        EntityManager em = emf.createEntityManager();



        em.close();
        emf.close();
    }
}
