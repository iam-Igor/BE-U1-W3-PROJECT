package igorgarofalo.dao;

import igorgarofalo.entities.Loan;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;
import java.util.List;
import java.util.UUID;

public class LoanDAO {

    private final EntityManager em;

    public LoanDAO(EntityManager em) {
        this.em = em;
    }


    public void save(Loan loan) {

        EntityTransaction transaction = em.getTransaction();
        transaction.begin();

        em.persist(loan);
        transaction.commit();

        System.out.println("Prestito: " + loan + " aggiunto alla sezione prestiti!");
    }


    public Loan findById(long id) {

        return em.find(Loan.class, id);
    }


    public void findByIdAndDelete(long id) {

        Loan found = this.findById(id);

        if (found != null) {


            EntityTransaction transaction = em.getTransaction();

            transaction.begin();

            em.remove(found);

            transaction.commit();

            System.out.println("Prestito: " + found.getItemOnLoan() + " eliminato correttamente!");

        } else {
            System.out.println("Il prestito  con l'id " + id + " non è stato trovato");
        }


    }


    public void searchLoansInProgressByCardNumber(String uuid) {
        TypedQuery<Loan> loans = em.createNamedQuery("search_loans_in_progress", Loan.class);
        //converto l'UUID in stringa e lo passo nel parametro del setter
        UUID uuidString = UUID.fromString(uuid);
        loans.setParameter("cardNumber", uuidString);
        if (loans.getResultList().size() == 0) {

            System.out.println("No active loans for this card number!");
        } else {
            loans.getResultList().forEach(System.out::println);
        }

    }

    public List<Loan> searchExpiredLoans() {
        TypedQuery<Loan> expiredLoans = em.createNamedQuery("search_expired_loans", Loan.class);
        return expiredLoans.getResultList();
    }


}
