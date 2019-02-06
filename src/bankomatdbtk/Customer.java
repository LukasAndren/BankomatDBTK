/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bankomatdbtk;

import java.util.ArrayList;
import java.util.List;


public class Customer {
    protected int id;
    protected String name;
    protected int pinCode;
    protected List<Account> accounts;

    public Customer(){
        
    }
    
    public Customer(int id, String name, int pinCode) {
        this.id = id;
        this.name = name;
        this.pinCode = pinCode;
        accounts = new ArrayList<>();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPinCode() {
        return pinCode;
    }

    public void setPinCode(int pinCode) {
        this.pinCode = pinCode;
    }
    
    public void addAccount(Account account){
        accounts.add(account);
    }
    
    
}