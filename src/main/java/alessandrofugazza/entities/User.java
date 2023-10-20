package alessandrofugazza.entities;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue
    private UUID id;

    @Column(nullable = false, length = 30)
    private String firstName;
    @Column(nullable = false, length = 30)
    private String lastName;

    private LocalDate birthDate;
    private int cardId;

//    @OneToOne(mappedBy = "user") // 1 to 1 diventa così BIDIREZIONALE
//    private Document document; // Questa non sarà una colonna della tabella
//    // serve solo lato Java per permetterci di ottenere il documento
//    // di un certo utente
//
//    @OneToMany(mappedBy = "user")
//    @OrderBy("title ASC")
//    private List<BlogPost> blogPosts; // Non viene creato a DB, serve
//    // solo lato Java per permetterci di ottenere la lista di tutti i blog
//    // scritti da un certo utente

    public User() {
    }

    public User(String firstName, String lastName, LocalDate birthDate, int cardId) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthDate = birthDate;
        this.cardId = cardId;
    }

    public UUID getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public int getCardId() {
        return cardId;
    }

    public void setCardId(int cardId) {
        this.cardId = cardId;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", birthDate=" + birthDate +
                ", cardId=" + cardId +
                '}';
    }
}
