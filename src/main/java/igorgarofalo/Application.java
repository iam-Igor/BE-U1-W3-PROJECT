package igorgarofalo;

import igorgarofalo.Items_Generators.BooksGenerator;
import igorgarofalo.Items_Generators.MagazineGenerator;
import igorgarofalo.Items_Generators.UsersGenerator;
import igorgarofalo.dao.LIbraryItemDAO;
import igorgarofalo.dao.LoanDAO;
import igorgarofalo.dao.UsersDAO;
import igorgarofalo.entities.*;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.time.LocalDate;
import java.util.List;

public class Application {

    private static final EntityManagerFactory emf = Persistence.createEntityManagerFactory("GestioneLibreria");


    public static void main(String[] args) {

        EntityManager em = emf.createEntityManager();
        LIbraryItemDAO ld = new LIbraryItemDAO(em);
        UsersDAO ud = new UsersDAO(em);
        LoanDAO lnd = new LoanDAO(em);


        //--------AGGIUNTA ELEMENTI AL CATALOGO-------

        // richiamo i metodi per generare e registrare nel db magazines, libri e users
        MagazineGenerator generatorOfRandomMagazines = new MagazineGenerator();
        BooksGenerator generatorOfRandomBooks = new BooksGenerator();
        UsersGenerator usersGenerator = new UsersGenerator();
//        generatorOfRandomMagazines.magazinesGenerator();
//        generatorOfRandomBooks.booksGenerator();
//        usersGenerator.usersGenerator();


        //-------------RICERCA PER UUID (USER CARD NUMBER)-------------------------
        User userfromDb = ud.findByUUID("46d2f498-089a-4e61-a8ce-c7fda69b80a7");
        User user2fromDb = ud.findByUUID("66e30d54-be41-41d3-8fee-eea25dd0f24e");
        User user3fromDb = ud.findByUUID("8226b9f5-8029-4ddd-bf0a-0399be03dc6e");
        User user4fromDb = ud.findByUUID("f0480444-2abf-4626-ae37-4047aa638433");
        User user5fromDb = ud.findByUUID("fa5554f8-825c-4053-9be0-ca51d63434db");


        //--------RIMUOVERE ELEMENTO DAL CATALOGO DATO UN ISBN-----
        //ld.findByIdAndDelete(31);


        //-------------RICERCA PER ISBN-------------------------
        Book bookFromDB = (Book) ld.findByISBN(32);
        Book book2FromDB = (Book) ld.findByISBN(39);
        Book book3FromDB = (Book) ld.findByISBN(45);
        Magazine magazineFromDb = (Magazine) ld.findByISBN(14);
        Magazine magazine2FromDb = (Magazine) ld.findByISBN(28);

        Loan loan1 = new Loan(userfromDb, bookFromDB, LocalDate.of(2023, 9, 10), LocalDate.of(2023, 10, 10));
        Loan loan2 = new Loan(user2fromDb, book2FromDB, LocalDate.of(2023, 8, 1), LocalDate.of(2023, 8, 10));
        Loan loan3 = new Loan(user3fromDb, book3FromDB, LocalDate.of(2021, 3, 6), LocalDate.of(2021, 4, 10));
        Loan loan4 = new Loan(user4fromDb, magazineFromDb, LocalDate.of(2020, 6, 6), LocalDate.of(2020, 8, 10));
        Loan loan5 = new Loan(user5fromDb, magazine2FromDb, LocalDate.of(2023, 12, 1), LocalDate.of(2023, 12, 10));

//        lnd.save(loan1);
//        lnd.save(loan2);
//        lnd.save(loan3);
//        lnd.save(loan4);
//        lnd.save(loan5);


        //-------------RICERCA PER ANNO DI PUBBLICAZIONE-------------------------

        System.out.println("-------FOUND ITEMS BY YEAR OF PUBLICATION------------------");
        List<LibraryItem> filteredByYearList = ld.getItemsByYear(1972);
        filteredByYearList.forEach(System.out::println);


        //-------------RICERCA PER AUTORE-------------------------

        System.out.println("-------FOUND BOOKS BY AUTHOR NAME------------------");
        List<Book> filteredBooksByAuthor = ld.getBooksByAuthor("Kenny West");
        filteredBooksByAuthor.forEach(System.out::println);


        //-------------RICERCA PER TITOLO O PARTE DI ESSO-------------------------

        System.out.println("-------FOUND ITEMS BY TITLE NAME------------------");
        List<LibraryItem> filteredByTitleName = ld.getItemsByTitle("The");
        filteredByTitleName.forEach(System.out::println);

        
    }


}
