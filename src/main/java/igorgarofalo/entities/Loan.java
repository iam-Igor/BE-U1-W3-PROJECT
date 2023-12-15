package igorgarofalo.entities;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "loan")
@NamedQuery(name = "search_loans_in_progress", query = "SELECT l FROM Loan l WHERE l.user.cardNumber = :cardNumber AND l.loanReturnDate IS NULL")
@NamedQuery(name = "search_expired_loans", query = "SELECT l FROM Loan l WHERE l.loanReturnDate > l.loanExpectedEndDate")
public class Loan {


    //la filosofia che ho seguito per relazionare loan e user è stata che: un utente può avere più prestiti, ed un prestito può avere un solo utente
    // per quanto riguarda gli Items della libreria, stesso ragionamento un item può avere più prestiti ma un prestito può essere associato ad un solo item

    @Id
    @GeneratedValue
    @Column(name = "loan_id")
    private long loanId;
    @ManyToOne
    private User user;
    @ManyToOne
    @JoinColumn(name = "item_on_loan_id")
    private LibraryItem itemOnLoan;
    @Column(name = "loan_start_date")
    private LocalDate loanStartDate;
    @Column(name = "loan_expected_end_date")
    private LocalDate loanExpectedEndDate;
    @Column(name = "loan_return_date")
    private LocalDate loanReturnDate;


    public Loan(User user, LibraryItem itemOnLoan, LocalDate loanStartDate, LocalDate loanReturnDate) {
        this.user = user;
        this.itemOnLoan = itemOnLoan;
        this.loanStartDate = loanStartDate;
        this.loanExpectedEndDate = setLoanExpectedEndDate();
        this.loanReturnDate = loanReturnDate;
    }

    public Loan() {
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public LibraryItem getItemOnLoan() {
        return itemOnLoan;
    }

    public void setItemOnLoan(LibraryItem itemOnLoan) {
        this.itemOnLoan = itemOnLoan;
    }

    public LocalDate getLoanStartDate() {
        return loanStartDate;
    }

    public void setLoanStartDate(LocalDate loanStartDate) {
        this.loanStartDate = loanStartDate;
    }

    public LocalDate getLoanExpectedEndDate() {
        return loanExpectedEndDate;
    }

    public LocalDate setLoanExpectedEndDate() {
        return loanStartDate.plusDays(30);
    }

    public LocalDate getLoanReturnDate() {
        return loanReturnDate;
    }

    public void setLoanReturnDate(LocalDate loanReturnDate) {
        this.loanReturnDate = loanReturnDate;
    }

    @Override
    public String toString() {
        return "Loan{" +
                "user=" + user +
                ", itemOnLoan=" + itemOnLoan +
                ", loanStartDate=" + loanStartDate +
                ", loanExpectedEndDate=" + loanExpectedEndDate +
                ", loanReturnDate=" + loanReturnDate +
                '}';
    }
}
