package igorgarofalo.Items_Generators;

import com.github.javafaker.Faker;
import igorgarofalo.dao.LIbraryItemDAO;
import igorgarofalo.entities.Book;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.time.LocalDate;
import java.util.Random;


//anche qui a grandi linee uso lo stesso metodo di  MagazineGenerator (vedi commenti nella classe MagazineGenerator)
public class BooksGenerator {


    private static final EntityManagerFactory emf = Persistence.createEntityManagerFactory("GestioneLibreria");

    public void booksGenerator() {

        EntityManager em = emf.createEntityManager();
        LIbraryItemDAO ld = new LIbraryItemDAO(em);

        for (int i = 0; i < 20; i++) {
            Faker faker = new Faker();
            Random rndm = new Random();
            int randomYear = rndm.nextInt(1900, 2023);
            int randomMonth = rndm.nextInt(1, 12);
            int randomDay = rndm.nextInt(1, 30);
            int randomPages = rndm.nextInt(30, 200);

            Book book = new Book(faker.book().title(), LocalDate.of(randomYear, randomMonth, randomDay), randomPages, faker.book().author());
            ld.save(book);
        }
    }
}
