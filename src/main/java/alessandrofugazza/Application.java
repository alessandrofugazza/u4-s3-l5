package alessandrofugazza;

import alessandrofugazza.dao.PublicationsDAO;
import alessandrofugazza.entities.Book;
import alessandrofugazza.entities.Magazine;
import alessandrofugazza.entities.Periodicity;
import alessandrofugazza.entities.Publication;
import com.github.javafaker.Faker;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.function.Supplier;

public class Application {
    private static final EntityManagerFactory emf = Persistence.createEntityManagerFactory("u4-s3-l5");
    public static void main(String[] args) {
        Logger log = LoggerFactory.getLogger(Application.class);
        Faker faker = new Faker();
        EntityManager em = emf.createEntityManager();

        PublicationsDAO pd = new PublicationsDAO(em);

        Supplier<Book> bookSupplier= () -> new Book(
                faker.number().numberBetween(1, 10000000),
                faker.book().title(),
                (short) faker.number().numberBetween(-2000, 2023),
                (short) faker.number().numberBetween(1, 1500),
                faker.name().fullName(),
                faker.book().genre()
        );
        Supplier<Magazine> magazineSupplier= () -> new Magazine(
                faker.number().numberBetween(1, 10000000),
                faker.book().title(),
                (short) faker.number().numberBetween(1900, 2023),
                (short) faker.number().numberBetween(1, 100),
                Periodicity.values()[faker.number().numberBetween(1, Periodicity.values().length)]
        );

        Book testBook = bookSupplier.get();
        Magazine testMagazine = magazineSupplier.get();
//        pd.save(testBook);
//        pd.save(testMagazine);
//        pd.deleteByISBN(7809359);
//        System.out.println(pd.findByISBN(8330768));
//        pd.findByYear((short)1927).forEach(System.out::println);
//        pd.findByAuthor("you").forEach(System.out::println);
        pd.findByPartialTitle("country").forEach(System.out::println);
        em.close();
        emf.close();
    }
}
