package alessandrofugazza.entities;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE) // uso single table perche' ci sono molti campi comuni a book e magazine ma pochi campi diversi
@Table(name = "publications")
@NamedQueries({
        @NamedQuery(name = "deleteByISBN", query = "DELETE FROM Publication p WHERE p.isbn = :isbn"),
        @NamedQuery(name = "findByPartialTitle", query = "SELECT p FROM Publication p WHERE LOWER(p.title) LIKE LOWER(CONCAT(:title, '%'))")
})
@DiscriminatorColumn(name = "publication_type")
public abstract class Publication {
    @Id
    protected int isbn;
    protected String title;
    protected short publicationYear;
    protected short numberOfPages;

    public Publication(){}

    public Publication(int isbn, String title, short publicationYear, short numberOfPages) {
        this.isbn = isbn;
        this.title = title;
        this.publicationYear = publicationYear;
        this.numberOfPages = numberOfPages;
    }

    public int getIsbn() {
        return isbn;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public short getPublicationYear() {
        return publicationYear;
    }

    public short getNumberOfPages() {
        return numberOfPages;
    }
}
