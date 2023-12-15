package igorgarofalo.entities;


import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.time.LocalDate;
import java.util.Random;

@Entity
@DiscriminatorValue("magazine")
public class Magazine extends LibraryItem {

    @Enumerated(EnumType.STRING)
    private Periodicity periodicity;

    public Magazine(String title, LocalDate yearOfPubl, int pagesNumber) {
        super(title, yearOfPubl, pagesNumber);
        this.periodicity = setRandomPeriodicity();
    }

    public Magazine() {
    }

    public Periodicity getPeriodicity() {
        return periodicity;
    }

    public Periodicity setRandomPeriodicity() {
        Random random = new Random();
        Periodicity[] values = Periodicity.values();
        int randomIndex = random.nextInt(values.length);
        return values[randomIndex];
    }

    @Override
    public String toString() {
        return "Magazine{" +
                "Title= " + this.getTitle() + ", " +
                "periodicity=" + periodicity +
                '}';
    }
}
