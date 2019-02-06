/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bankomatdbtk;


public class Loan {
    protected int id;
    protected int balance;
    protected float interest;
    protected int paymantPlan;

    public Loan(int id, int balance, float interest, int paymantPlan) {
        this.id = id;
        this.balance = balance;
        this.interest = interest;
        this.paymantPlan = paymantPlan;
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

}
