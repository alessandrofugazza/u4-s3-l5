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

//    public void findByPartialTitle(String str) {
    public List<Publication> findByPartialTitle(String str) {
        TypedQuery<Publication> findByPartialTitleQ = em.createNamedQuery("findByPartialTitle", Publication.class);
        findByPartialTitleQ.setParameter("title", str);
        return findByPartialTitleQ.getResultList();
    }

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
}
