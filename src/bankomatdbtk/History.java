/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bankomatdbtk;

import java.sql.Date;


public class History {
    protected int id;
    protected Date date;
    protected int sum;
    protected Account account;

    public History(int id, Date date, int sum, Account account) {
        this.id = id;
        this.date = date;
        this.sum = sum;
        this.account = account;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public double getSum() {
        return sum;
    }

    public void setSum(int sum) {
        this.sum = sum;
    }
}
