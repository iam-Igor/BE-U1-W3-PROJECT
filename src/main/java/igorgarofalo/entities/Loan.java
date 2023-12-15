package igorgarofalo.entities;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "loan")
@NamedQuery(name = "search_loans_in_progress", query = "SELECT l FROM Loan l WHERE l.loanReturnDate = null")
@NamedQuery(name = "search_expired_loans", query = "SELECT l FROM Loan l WHERE ")
public class Loan {


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
