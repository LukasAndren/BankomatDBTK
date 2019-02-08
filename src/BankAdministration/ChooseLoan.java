package BankAdministration;


import bankomatdbtk.Account;
import bankomatdbtk.Customer;
import bankomatdbtk.Loan;
import bankomatdbtk.Repository;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class ChooseLoan {
    ManageLoan manageLoan;
    
    
    public ChooseLoan(Stage stage, Customer customer, Account account) {
        BorderPane root = new BorderPane();
        VBox vbox = new VBox();
        ScrollPane sp = new ScrollPane();
        Repository r = new Repository();
        List<Loan> loans = r.getLoans();
        
        loans = loans.stream().filter(l -> l.getAccount().getId() == account.getId()).collect(Collectors.toList());
        
        for (Loan loan : loans) {
            String s = String.format("ID: %d.0 \nBalance: %d \nInterest: %f \nPayment Plan: %d", loan.getId(), loan.getBalance(), loan.getInterest(), loan.getPaymantPlan());
            Button tempButton = new Button(s);
            tempButton.setStyle("-fx-min-height: 100px;\n"
                                  + "-fx-min-width: 275px; \n");
            tempButton.setAlignment(Pos.BASELINE_LEFT);
            
            tempButton.setOnAction((event) -> {
                manageLoan = new ManageLoan(stage, loan, customer, account);
            });
            vbox.getChildren().add(tempButton);
        }
        Button back = new Button("Back");
        vbox.getChildren().add(back);
        back.setOnAction((event) -> {
            ManageCustomer mc = new ManageCustomer(stage, customer);
        });
        
        sp.setContent(vbox);
        root.setCenter(sp);
        
        Scene scene = new Scene(root, 300, 300);
        stage.setScene(scene);
        
        
        
    }
    
    private ArrayList<Loan> getLoans(Customer customer) {
        Account account = new Account();
        
        Loan l1 = new Loan(1, 0, 0, 0, 0, account);
        Loan l2 = new Loan(2, 0, 0, 0, 0, account);
        Loan l3 = new Loan(3, 0, 0, 0, 0, account);
        Loan l4 = new Loan(4, 0, 0, 0, 0, account);
        
        ArrayList<Loan> loans = new ArrayList();
        loans.add(l1);
        loans.add(l2);
        loans.add(l3);
        loans.add(l4);
        
        
        return loans;
    }

}