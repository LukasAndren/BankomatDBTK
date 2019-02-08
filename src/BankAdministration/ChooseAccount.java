package BankAdministration;


import bankomatdbtk.Account;
import bankomatdbtk.Customer;
import bankomatdbtk.Repository;
import java.util.ArrayList;
import java.util.List;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class ChooseAccount {
    private ManageAccount manageAccount;
    private ManageCustomer manageCustomer;
    
    public ChooseAccount (Stage stage, Customer customer) {
        BorderPane root = new BorderPane();
        VBox vbox = new VBox();
        ScrollPane sp = new ScrollPane();
        Repository r = new Repository();
        List<Account> accounts = r.getAccounts();
        Button back = new Button("Back");
        for (Account account : accounts) {
            if(account.getCustomer().getId() == customer.getId()){
            String s = String.format("ID: %d.0 \nBalance: %d \nInterest: %f", account.getId(), account.getBalance(), account.getInterest());
            Button tempButton = new Button(s);
            tempButton.setStyle("-fx-min-height: 100px;\n"
                                  + "-fx-min-width: 275px; \n");
            tempButton.setAlignment(Pos.BASELINE_LEFT);
            
            tempButton.setOnAction((event) -> {
                manageAccount = new ManageAccount(stage, account, customer);
            });
            vbox.getChildren().add(tempButton);
            }
        }
        
        back.setOnAction((event) -> {
            manageCustomer = new ManageCustomer(stage, customer);
        });
        vbox.getChildren().add(back);
        sp.setContent(vbox);
        root.setCenter(sp);
        
        Scene scene = new Scene(root, 300, 300);
        stage.setScene(scene);
    }

    private ArrayList<Account> getAccounts(Customer customer) {
        Account a1 = new Account(1, 0, 0, customer);
        Account a2 = new Account(2, 0, 0, customer);
        Account a3 = new Account(3, 0, 0, customer);
        
        ArrayList<Account> accounts = new ArrayList();
        accounts.add(a1);
        accounts.add(a2);
        accounts.add(a3);
        
        return accounts;
    }
}