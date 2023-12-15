package igorgarofalo.entities;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;


@Entity
@Table(name = "user")
public class User {

    @Column(name = "name")
    private String name;
    @Column(name = "lastName")
    private String lastName;


    @Column(name = "birth_date")
    private LocalDate birthDate;

    @Id
    @GeneratedValue
    @Column(name = "card_number")
    private UUID cardNumber;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Loan> loans;

    public User(String name, String lastName, LocalDate birthDate) {
        this.name = name;
        this.lastName = lastName;
        this.birthDate = birthDate;
    }

    public User() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public UUID getCardNumber() {
        return cardNumber;
    }


    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", lastName='" + lastName + '\'' +
                ", birthDate=" + birthDate +
                ", cardNumber=" + cardNumber +
                '}';
    }
}
