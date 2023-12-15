package igorgarofalo.dao;

import igorgarofalo.entities.Book;
import igorgarofalo.entities.LibraryItem;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;
import java.util.List;


public class LIbraryItemDAO {

    private final EntityManager em;

    public LIbraryItemDAO(EntityManager em) {
        this.em = em;
    }


    public void save(LibraryItem element) {

        EntityTransaction transaction = em.getTransaction();
        transaction.begin();

        em.persist(element);
        transaction.commit();

        System.out.println("Elemento " + element + " aggiunto alla libreria!");
    }


    public LibraryItem findByISBN(long id) {

        return em.find(LibraryItem.class, id);
    }


    public void findByISBNAndDelete(long id) {

        LibraryItem found = this.findByISBN(id);

        if (found != null) {


            EntityTransaction transaction = em.getTransaction();

            transaction.begin();

            em.remove(found);

            transaction.commit();

            System.out.println("Elemento: " + found.getTitle() + " eliminato correttamente!");

        } else {
            System.out.println("L'elemento con l'id " + id + " non è stato trovato");
        }


    }


    public List<LibraryItem> getItemsByYear(int year) {
        TypedQuery<LibraryItem> items = em.createNamedQuery("search_by_year_of_publiucation", LibraryItem.class);
        items.setParameter("year", year);
        return items.getResultList();
    }


    public List<Book> getBooksByAuthor(String authorName) {
        TypedQuery<Book> books = em.createNamedQuery("search_by_author", Book.class);
        books.setParameter("author_name", authorName);
        return books.getResultList();
    }


    public List<LibraryItem> getItemsByTitle(String title) {
        String searchTerm = title + "%";
        TypedQuery<LibraryItem> items = em.createNamedQuery("search_item_by_title", LibraryItem.class);
        items.setParameter("query", searchTerm);
        return items.getResultList();
    }
}
