

package bankomatdbtk;

import java.util.ArrayList;
import java.util.List;


public class Account {
    protected int id;
    protected int balance;
    protected float interest;
    protected Customer customer;

    public Account(){
        
    }
    
    public Account(int id, int balance, float interest, Customer customer) {
        this.id = id;
        this.balance = balance;
        this.interest = interest;
        this.customer = customer;
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

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

}
