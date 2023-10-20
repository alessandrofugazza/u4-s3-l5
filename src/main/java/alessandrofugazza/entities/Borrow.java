package alessandrofugazza.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDate;
import java.util.UUID;

@Entity
@Table(name = "borrows")
public class Borrow {
    @Id
    @GeneratedValue
    private UUID id;
    private User user;
    private Publication publication;
    private LocalDate borrowStartDate;
    private LocalDate scheduledReturnDate;
    private LocalDate actualReturnDate;

//    @ManyToOne
//    @JoinColumn(name = "user_id", nullable = false)
//    private User user; // <-- Foreign Key
//
//    @ManyToMany
//    @JoinTable(name = "blogs_categories",
//            joinColumns = @JoinColumn(name = "blog_id"),
//            inverseJoinColumns = @JoinColumn(name = "category_id"))
//    private Set<Category> categories;

    public Borrow() {
    }

    public Borrow(User user, Publication publication, LocalDate borrowStartDate, LocalDate actualReturnDate) {
        this.user = user;
        this.publication = publication;
        this.borrowStartDate = borrowStartDate;
        this.scheduledReturnDate = this.borrowStartDate.plusDays(30);
        this.actualReturnDate = actualReturnDate;
    }

    public UUID getId() {
        return id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Publication getPublication() {
        return publication;
    }

    public void setPublication(Publication publication) {
        this.publication = publication;
    }

    public LocalDate getBorrowStartDate() {
        return borrowStartDate;
    }

    public void setBorrowStartDate(LocalDate borrowStartDate) {
        this.borrowStartDate = borrowStartDate;
    }

    public LocalDate getScheduledReturnDate() {
        return scheduledReturnDate;
    }

    public LocalDate getActualReturnDate() {
        return actualReturnDate;
    }

    public void setActualReturnDate(LocalDate actualReturnDate) {
        this.actualReturnDate = actualReturnDate;
    }

    @Override
    public String toString() {
        return "Borrow{" +
                "id=" + id +
                ", user=" + user +
                ", publication=" + publication +
                ", borrowStartDate=" + borrowStartDate +
                ", scheduledReturnDate=" + scheduledReturnDate +
                ", actualReturnDate=" + actualReturnDate +
                '}';
    }
}
