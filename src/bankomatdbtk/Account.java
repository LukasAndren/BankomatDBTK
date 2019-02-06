

package bankomatdbtk;

import java.util.ArrayList;
import java.util.List;


public class Account {
    protected int id;
    protected int balance;
    protected float interest;
    protected Customer customer;
    protected List<History> histories;
    protected List<Loan> loans;

    public Account(){
        
    }
    
    public Account(int id, int balance, float interest, Customer customer) {
        this.id = id;
        this.balance = balance;
        this.interest = interest;
        this.customer = customer;
        histories = new ArrayList<>();
        loans = new ArrayList<>();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getBalance() {
        return balance;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }

    public float getInterest() {
        return interest;
    }

    public void setInterest(float interest) {
        this.interest = interest;
    }

    public void addHistory(History history){
        histories.add(history);
    }
    
    public void addLoan(Loan loan){
        loans.add(loan);
    }
}
