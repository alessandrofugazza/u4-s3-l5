package alessandrofugazza;

import alessandrofugazza.dao.PublicationsDAO;
import alessandrofugazza.dao.UsersDAO;
import alessandrofugazza.entities.*;
import com.github.javafaker.Faker;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.time.ZoneId;
import java.util.function.Supplier;

public class Application {
    private static final EntityManagerFactory emf = Persistence.createEntityManagerFactory("u4-s3-l5");
    public static void main(String[] args) {
        Logger log = LoggerFactory.getLogger(Application.class);
        Faker faker = new Faker();
        EntityManager em = emf.createEntityManager();

        PublicationsDAO pd = new PublicationsDAO(em);
        UsersDAO ud = new UsersDAO(em);

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
        Supplier<User> userSupplier= () -> new User(
                faker.name().firstName(),
                faker.name().lastName(),
                faker.date().birthday().toInstant().atZone(ZoneId.systemDefault()).toLocalDate(),
                faker.number().numberBetween(1, 3000)
        );

        Book testBook = bookSupplier.get();
        Magazine testMagazine = magazineSupplier.get();
        User testUser = userSupplier.get();
        log.debug("--------------------------------------------------------------------------------------------------------");
//        ud.save(testUser);
//        pd.save(testBook);
//        pd.save(testMagazine);
//        pd.deleteByISBN(7809359);
//        System.out.println(pd.findByISBN(8330768));
//        pd.findByYear((short)1927).forEach(System.out::println);
//        pd.findByAuthor("you").forEach(System.out::println);
//        pd.findByPartialTitle("country");
//        System.out.println(pd.findByPartialTitle("country"));
        ud.getListOfBorrows(2850);
        log.debug("--------------------------------------------------------------------------------------------------------");
        em.close();
        emf.close();
    }
}
