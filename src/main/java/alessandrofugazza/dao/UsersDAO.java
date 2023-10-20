package alessandrofugazza.dao;

        import alessandrofugazza.entities.Book;
        import alessandrofugazza.entities.Borrow;
        import alessandrofugazza.entities.Publication;
        import alessandrofugazza.entities.User;

        import javax.persistence.EntityManager;
        import javax.persistence.EntityTransaction;
        import javax.persistence.Query;
        import javax.persistence.TypedQuery;
        import java.util.List;
        import java.util.UUID;

public class UsersDAO {
    private final EntityManager em;

    public UsersDAO(EntityManager em) {
        this.em = em;
    }

    public void save(User u) {
        EntityTransaction transaction = em.getTransaction();
        transaction.begin();
        em.persist(u);
        transaction.commit();
        System.out.println("New user saved successfully.");
    }

    public List<Borrow> getListOfBorrows(int cardId) {
        TypedQuery<UUID> getIdFromCardQ = em.createQuery("SELECT u.id FROM User u WHERE u.cardId = :cardid", UUID.class);
        getIdFromCardQ.setParameter("cardid", cardId);
        UUID userId = getIdFromCardQ.getSingleResult();
        TypedQuery<Borrow> getListOfBorrowsQ = em.createQuery("SELECT b FROM Borrow b WHERE b.user.id = :userid", Borrow.class);
        getListOfBorrowsQ.setParameter("userid", userId);
        return getListOfBorrowsQ.getResultList();
    }
}
