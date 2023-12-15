package igorgarofalo.dao;

import igorgarofalo.entities.Loan;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

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
}
