/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bankomatdbtk;


public class Loan {
    protected int id;
    protected int balance;
    protected int amount;
    protected float interest;
    protected int paymantPlan;
    protected Account account;

    public Loan(int id, int balance, int amount, float interest, int paymantPlan, Account account) {
        this.id = id;
        this.balance = balance;
        this.amount = amount;
        this.interest = interest;
        this.paymantPlan = paymantPlan;
        this.account = account;
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

    public int getPaymantPlan() {
        return paymantPlan;
    }

    public void setPaymantPlan(int paymantPlan) {
        this.paymantPlan = paymantPlan;
    }
    
    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }
}
