package igorgarofalo.Items_Generators;

import com.github.javafaker.Faker;
import igorgarofalo.dao.UsersDAO;
import igorgarofalo.entities.User;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.time.LocalDate;
import java.util.Random;

public class UsersGenerator {

    private static final EntityManagerFactory emf = Persistence.createEntityManagerFactory("GestioneLibreria");
    EntityManager em = emf.createEntityManager();
    UsersDAO ud = new UsersDAO(em);


    public void usersGenerator() {


        for (int i = 0; i < 10; i++) {
            Faker faker = new Faker();
            Random rndm = new Random();
            int randomYear = rndm.nextInt(1980, 2015);
            int randomMonth = rndm.nextInt(1, 12);
            int randomDay = rndm.nextInt(1, 30);

            User user = new User(faker.name().firstName(), faker.name().lastName(), LocalDate.of(randomYear, randomMonth, randomDay));
            ud.save(user);
        }

    }


}



