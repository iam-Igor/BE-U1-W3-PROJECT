package igorgarofalo.Items_Generators;

import igorgarofalo.dao.LIbraryItemDAO;
import igorgarofalo.entities.Magazine;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.time.LocalDate;
import java.util.Random;


//ho voluto creare una classe a parte che mi generi magazine in quanto volevo tenere il main piu pulito possibile
//il metodo itera un'array di titoli di magazines inventati che e li usa per settare il titolo del magazine
// stessa cosa per le date uso random per generare giorno mese annom, per quanto riguarda la periodicità
// ho impostato nel setter della classe magazine un ciclo che prenda randomicamente un value dei 3 disponibili e lo metta
// nel costruttore.
public class MagazineGenerator {

    private static final EntityManagerFactory emf = Persistence.createEntityManagerFactory("GestioneLibreria");

    public void magazinesGenerator() {

        EntityManager em = emf.createEntityManager();
        LIbraryItemDAO ld = new LIbraryItemDAO(em);

        String[] titoliMagazine = {
                "Viaggio nel tempo: Le scoperte della fisica quantistica",
                "Esplorando le meraviglie dell'oceano",
                "Il futuro dell'intelligenza artificiale",
                "Gli effetti della musica sulla mente umana",
                "Scoperte archeologiche nell'antica Roma",
                "La rinascita dell'agricoltura sostenibile",
                "Nuove tecniche di cucina fusion",
                "Segreti per una vita sana ed equilibrata",
                "La psicologia dietro le decisioni di acquisto",
                "Avventure nel mondo della fotografia astratta",
                "I misteri dell'universo e le galassie lontane",
                "Arte moderna: Interpretazioni e controversie",
                "Il potere delle meditazioni quotidiane",
                "Nuove tendenze nel mondo della moda",
                "Innovazioni tecnologiche nel settore medico",
                "Stili di vita minimalisti per il benessere",
                "Le origini dei miti e delle leggende antiche",
                "Sopravvivere nella giungla urbana: Guida pratica",
                "La rivoluzione delle energie rinnovabili",
                "Le meraviglie della flora e fauna nell'Artide"
        };

        for (int i = 0; i < titoliMagazine.length; i++) {
            Random rndm = new Random();
            int randomYear = rndm.nextInt(1900, 2023);
            int randomMonth = rndm.nextInt(1, 12);
            int randomDay = rndm.nextInt(1, 30);
            int randomPages = rndm.nextInt(1, 50);

            Magazine magazine = new Magazine(titoliMagazine[i], LocalDate.of(randomYear, randomMonth, randomDay), randomPages);
            ld.save(magazine);
        }
    }
}
