package alessandrofugazza.dao;

import alessandrofugazza.entities.Book;
import alessandrofugazza.entities.Publication;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.util.List;

public class PublicationsDAO {
    private final EntityManager em;

    public PublicationsDAO(EntityManager em) {
        this.em = em;
    }

    public void save(Publication p) {
        EntityTransaction transaction = em.getTransaction();
        transaction.begin();
        em.persist(p);
        transaction.commit();
        System.out.println("New publication saved successfully.");
    }

    public Publication findByISBN(int isbn) {
        return em.find(Publication.class, isbn);
    }
//
//    public Dog findDogById(long id) {
//        return em.find(Dog.class, id);
//    }
//
//    public Cat findCatById(long id) {
//        return em.find(Cat.class, id);
//    }
//
//    public List<Publication> findByYear(int year) {
//        // SELECT * FROM animals
//        TypedQuery<Animal> getAllQuery = em.createQuery("SELECT a FROM Animal a", Animal.class); // Query JPQL
//        return getAllQuery.getResultList();
//    }
//
//    public List<Cat> findAllCats() {
//        // SELECT * FROM animals WHERE tipo_animale = 'Gatto'
//        TypedQuery<Cat> getAllQuery = em.createQuery("SELECT c FROM Cat c", Cat.class); // Query JPQL
//        return getAllQuery.getResultList();
//    }
//
//    public List<Dog> findAllDogs() {
//        // SELECT * FROM animals WHERE tipo_animale = 'Cane'
//        TypedQuery<Dog> getAllQuery = em.createQuery("SELECT d FROM Dog d", Dog.class); // Query JPQL
//        return getAllQuery.getResultList();
//    }
//
    public List<Publication> findByYear(short year) {
        TypedQuery<Publication> getByYearQuery = em.createQuery("SELECT p FROM Publication p WHERE p.publicationYear = :year", Publication.class);
        getByYearQuery.setParameter("year", year);
        return getByYearQuery.getResultList();
    }

    public List<Book> findByAuthor(String author) {
        TypedQuery<Book> getByAuthorQuery = em.createQuery("SELECT b FROM Book b WHERE b.author = :author", Book.class);
        getByAuthorQuery.setParameter("author", author);
        return getByAuthorQuery.getResultList();
    }

    public List<Publication> findByPartialTitle(String str) {
        TypedQuery<Publication> findByPartialTitleQ = em.createNamedQuery("findByPartialTitle", Publication.class);
        findByPartialTitleQ.setParameter("title", str);
        return findByPartialTitleQ.getResultList();
    }
//
//    public void findDogByNameAndUpdateName(String oldName, String newName) {
//        // UPDATE animals SET name = 'nuovonome' WHERE name = 'Fido' AND tipo_animale = 'Cane'
//        EntityTransaction transaction = em.getTransaction();
//        // 1. Inizio la transazione
//        transaction.begin();
//
//        Query modifyNameQuery = em.createQuery("UPDATE Dog d SET d.name = :newname WHERE d.name = :oldname"); // Query JPQL
//        modifyNameQuery.setParameter("oldname", oldName);
//        modifyNameQuery.setParameter("newname", newName);
//
//        // 2. Eseguo la query
//        int numeroModificati = modifyNameQuery.executeUpdate();
//
//        // 3. Termino la transazione col salvataggio effettivo di una nuova riga nella tabella students
//        transaction.commit();
//
//        if (numeroModificati > 0) {
//            System.out.println("Cani modificati");
//        } else {
//            System.out.println("Non ho trovato nessun cane di nome: " + oldName);
//        }
//    }
//
//
    public void deleteByISBN(int isbn) {
        EntityTransaction transaction = em.getTransaction();
        transaction.begin();
        Query deleteQuery = em.createNamedQuery("deleteByISBN");
        deleteQuery.setParameter("isbn", isbn);
        int deletedNumber = deleteQuery.executeUpdate();
        transaction.commit();
        if (deletedNumber > 0) {
            System.out.println("Number of publications deleted: " + deletedNumber);
        } else {
            System.out.println("No publications found for isbn: " + isbn);
        }
    }
//
//    /*
//
//    public void findByIdAndDelete(long id) {
//
//        // 1. Faccio una find per cercare lo studente
//        User found = em.find(User.class, id);
//
//        if (found != null) {
//            // 2. Se lo studente c'è, lo elimino
//
//            // 2.1 Ottengo la transazione
//            EntityTransaction transaction = em.getTransaction();
//
//            // 2.2 Faccio partire la transazione
//            transaction.begin();
//
//            // 2.3 Rimuovo l'oggetto dal persistence context
//            em.remove(found);
//
//            // 2.4 Faccio il commit della transazione per persistere a db l'operazione
//            transaction.commit();
//            System.out.println("Lo studente è stato cancellato correttamente");
//        } else {
//            // 3. Altrimenti --> "Student not found"
//            System.err.println("Lo studente con l'id " + id + " non è stato trovato");
//        }
}


//    }*/
//}
