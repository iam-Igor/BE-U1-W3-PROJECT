package igorgarofalo.entities;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Random;

@Entity
@DiscriminatorValue("book")
@NamedQuery(name = "search_by_author", query = "SELECT b FROM Book b WHERE b.author= :author_name")
public class Book extends LibraryItem {

    private String author;

    @Column(name = "book_genre")
    @Enumerated(EnumType.STRING)
    private BookGenre bookGenre;


    public Book() {
    }

    public Book(String title, LocalDate yearOfPubl, int pagesNumber, String author) {
        super(title, yearOfPubl, pagesNumber);
        this.author = author;
        this.bookGenre = setBookGenre();
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public BookGenre getBookGenre() {
        return bookGenre;
    }


    public BookGenre setBookGenre() {
        Random random = new Random();
        BookGenre[] values = BookGenre.values();
        int randomIndex = random.nextInt(values.length);
        return values[randomIndex];
    }

    @Override
    public String toString() {
        return "Book{" +
                "author='" + author + '\'' +
                "Title= " + this.getTitle() + ", " +
                ", bookGenre=" + bookGenre +
                '}';
    }
}
