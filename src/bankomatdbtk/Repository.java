/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bankomatdbtk;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Repository {

    Connection con;
    Statement stmt;
    List<Account> accounts;
    List<Customer> customers;
    List<Loan> loans;
    List<History> history;

    public Repository() {
        accounts = new ArrayList<>();
        customers = new ArrayList<>();
        loans = new ArrayList<>();
        history = new ArrayList<>();
        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/bankomat",
                    "root", "lukas0691");
            Statement stmt = con.createStatement();

            ResultSet customerRs = stmt.executeQuery("select * from customer;");

            while (customerRs.next()) {
                Customer tempCustomer = new Customer(customerRs.getInt("id"),
                        customerRs.getString("name"), customerRs.getInt("pincode"));
                customers.add(tempCustomer);
            }

            customerRs.close();

            ResultSet accountRs = stmt.executeQuery("select * from account;");

            while (accountRs.next()) {
                int customerId = accountRs.getInt("customerId");
                Customer tempCustomer = new Customer();
                for (Customer customer : customers) {
                    if (customer.getId() == customerId) {
                        tempCustomer = customer;
                    }
                }
                Account tempAccount = new Account(accountRs.getInt("id"), accountRs.getInt("balance"),
                        accountRs.getFloat("aInterest"), tempCustomer);
                accounts.add(tempAccount);
            }
            accountRs.close();

            ResultSet loanRs = stmt.executeQuery("select * from loan;");

            while (loanRs.next()) {
                int accountId = loanRs.getInt("accountId");
                Account tempAccount = new Account();
                for (Account account : accounts) {
                    if (account.getId() == accountId) {
                        tempAccount = account;
                    }
                }
                Loan tempLoan = new Loan(loanRs.getInt("id"), loanRs.getInt("balance"),
                        loanRs.getInt("amount"), loanRs.getFloat("lInterest"), loanRs.getInt("paymentPlan"),
                        tempAccount);
                loans.add(tempLoan);
            }
            loanRs.close();

            ResultSet historyRs = stmt.executeQuery("select * from history;");

            while (historyRs.next()) {
                int accountId = historyRs.getInt("accountId");
                Account tempAccount = new Account();
                for (Account account : accounts) {
                    if (account.getId() == accountId) {
                        tempAccount = account;
                    }
                }
                History tempHistory = new History(historyRs.getInt("id"), historyRs.getDate("date"),
                        historyRs.getInt("sum"), tempAccount);
                history.add(tempHistory);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Repository.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Repository.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void callAddCustomer(String name, int pin) {
        CallableStatement cStmt;
        try {
            cStmt = con.prepareCall("call AddCustomer(?, ?)");
            cStmt.setString(1, name);
            cStmt.setInt(2, pin);
            cStmt.execute();
        } catch (SQLException ex) {
            Logger.getLogger(Repository.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void callUpdateCustomer(int id, String name) {
        CallableStatement cStmt;
        try {
            cStmt = con.prepareCall("call UpdateCustomer(?, ?)");
            cStmt.setInt(1, id);
            cStmt.setString(2, name);
            cStmt.execute();
        } catch (SQLException ex) {
            Logger.getLogger(Repository.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void callDeleteCustomer(int id) {
        CallableStatement cStmt;
        try {
            cStmt = con.prepareCall("call DeleteCustomer(?)");
            cStmt.setInt(1, id);
            cStmt.execute();
        } catch (SQLException ex) {
            Logger.getLogger(Repository.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void callAddAccount(int customerId, int balance, float interest) {
        CallableStatement cStmt;
        try {
            cStmt = con.prepareCall("call AddAccount(?, ?, ?)");
            cStmt.setInt(1, customerId);
            cStmt.setInt(2, balance);
            cStmt.setFloat(3, interest);
            cStmt.execute();
        } catch (SQLException ex) {
            Logger.getLogger(Repository.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void callDeleteAccount(int id) {
        CallableStatement cStmt;
        try {
            cStmt = con.prepareCall("call DeleteAccount(?)");
            cStmt.setInt(1, id);
            cStmt.execute();
        } catch (SQLException ex) {
            Logger.getLogger(Repository.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void callAddFunds(int id, int amount) {
        CallableStatement cStmt;
        try {
            cStmt = con.prepareCall("call AddFunds(?, ?)");
            cStmt.setInt(1, id);
            cStmt.setInt(2, amount);
            cStmt.execute();
        } catch (SQLException ex) {
            Logger.getLogger(Repository.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void callRemoveFunds(int id, int amount) {
        CallableStatement cStmt;
        try {
            cStmt = con.prepareCall("call RemoveFunds(?, ?)");
            cStmt.setInt(1, id);
            cStmt.setInt(2, amount);
            cStmt.execute();
        } catch (SQLException ex) {
            Logger.getLogger(Repository.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void callChangeAccountInterest(int id, float interest) {
        CallableStatement cStmt;
        try {
            cStmt = con.prepareCall("call ChangeAccountInterest(?, ?)");
            cStmt.setInt(1, id);
            cStmt.setFloat(2, interest);
            cStmt.execute();
        } catch (SQLException ex) {
            Logger.getLogger(Repository.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void callCreateLoan(int accountId, int amount, float interest, int paymentPlan) {
        CallableStatement cStmt;
        try {
            cStmt = con.prepareCall("call CreateLoan(?, ?, ?, ?)");
            cStmt.setInt(1, accountId);
            cStmt.setInt(2, amount);
            cStmt.setFloat(3, interest);
            cStmt.setInt(2, paymentPlan);
            cStmt.execute();
        } catch (SQLException ex) {
            Logger.getLogger(Repository.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void callChangeLoanInterest(int id, float interest) {
        CallableStatement cStmt;
        try {
            cStmt = con.prepareCall("call ChangeAccountInterest(?, ?)");
            cStmt.setInt(1, id);
            cStmt.setFloat(2, interest);
            cStmt.execute();
        } catch (SQLException ex) {
            Logger.getLogger(Repository.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void callChangePlan(int id, int paymentPlan) {
        CallableStatement cStmt;
        try {
            cStmt = con.prepareCall("call ChangePlan(?, ?)");
            cStmt.setInt(1, id);
            cStmt.setInt(2, paymentPlan);
            cStmt.execute();
        } catch (SQLException ex) {
            Logger.getLogger(Repository.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
