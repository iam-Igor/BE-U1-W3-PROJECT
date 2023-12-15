package igorgarofalo.entities;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "library_item")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "library_type")
@NamedQuery(name = "search_by_year_of_publiucation", query = "SELECT i FROM LibraryItem i WHERE YEAR(i.yearOfPubl) = :year")
@NamedQuery(name = "search_item_by_title", query = "SELECT i FROM LibraryItem i WHERE i.title LIKE :query")
public abstract class LibraryItem {

    @Id
    @GeneratedValue
    private long isbn;
    private String title;
    @Column(name = "year_of_publication")
    private LocalDate yearOfPubl;

    @Column(name = "number_of_pages")
    private int pagesNumber;

    public LibraryItem(String title, LocalDate yearOfPubl, int pagesNumber) {
        this.title = title;
        this.yearOfPubl = yearOfPubl;
        this.pagesNumber = pagesNumber;
    }

    public LibraryItem() {
    }

    public long getIsbn() {
        return isbn;
    }


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public LocalDate getYearOfPubl() {
        return yearOfPubl;
    }

    public void setYearOfPubl(LocalDate yearOfPubl) {
        this.yearOfPubl = yearOfPubl;
    }

    public int getPagesNumber() {
        return pagesNumber;
    }

    public void setPagesNumber(int pagesNumber) {
        this.pagesNumber = pagesNumber;
    }

    @Override
    public String toString() {
        return "LibraryItem{" +
                "isbn=" + isbn +
                ", title='" + title + '\'' +
                ", yearOfPubl=" + yearOfPubl +
                ", pagesNumber=" + pagesNumber +
                '}';
    }
}
