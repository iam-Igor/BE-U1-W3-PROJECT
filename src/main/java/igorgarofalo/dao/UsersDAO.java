package igorgarofalo.dao;

import igorgarofalo.entities.User;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import java.util.UUID;

public class UsersDAO {

    private final EntityManager em;

    public UsersDAO(EntityManager em) {
        this.em = em;
    }


    public void save(User user) {

        EntityTransaction transaction = em.getTransaction();
        transaction.begin();

        em.persist(user);
        transaction.commit();

        System.out.println("Lo user " + user + " è stato aggiunto alla libreria utenti!");
    }


    public User findByUUID(String id) {

        UUID uuid = UUID.fromString(id);

        return em.find(User.class, uuid);
    }


    public void findByIdAndDelete(String id) {
        User found = this.findByUUID(id);

        if (found != null) {


            EntityTransaction transaction = em.getTransaction();

            transaction.begin();

            em.remove(found);

            transaction.commit();

            System.out.println("Elemento: " + found.getName() + " eliminato correttamente!");

        } else {
            System.out.println("Lo user con l'id " + id + " non è stato trovato");
        }


    }
}
