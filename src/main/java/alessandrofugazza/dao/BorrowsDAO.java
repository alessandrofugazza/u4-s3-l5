package alessandrofugazza.dao;

import alessandrofugazza.entities.Borrow;
import alessandrofugazza.entities.User;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

public class BorrowsDAO {
    private final EntityManager em;

    public BorrowsDAO(EntityManager em) {
        this.em = em;
    }

    public List<Borrow> getExpiredBorrows() {
        TypedQuery<Borrow> getExpiredBorrows = em.createQuery("SELECT b FROM Borrow b WHERE b.actualReturnDate IS NOT NULL AND b.scheduledReturnDate < :currentdate", Borrow.class);
        getExpiredBorrows.setParameter("currentdate", LocalDate.now());
        return getExpiredBorrows.getResultList();
    }
}
