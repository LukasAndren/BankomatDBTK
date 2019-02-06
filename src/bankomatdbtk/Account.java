

package bankomatdbtk;


public class Account {
    protected int id;
    protected int balance;
    protected float interest;

    public Account(int id, int balance, float interest) {
        this.id = id;
        this.balance = balance;
        this.interest = interest;
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

}
