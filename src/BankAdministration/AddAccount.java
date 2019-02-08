package BankAdministration;


import bankomatdbtk.Account;
import bankomatdbtk.Customer;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class AddAccount {
    private ManageCustomer manageCustomer;
    
    public AddAccount (Stage stage, Customer customer) {
        VBox vbox = new VBox();
        BorderPane root = new BorderPane();
        Label balanceLabel = new Label("Balance: ");
        TextField balanceField = new TextField();
        Label interestLabel = new Label("Interest: ");
        TextField interestField = new TextField();
        
        int id = 0;
        
        Button bOK = new Button("OK");
        
        bOK.setOnAction((event) -> {
            int balance = Integer.parseInt(balanceField.getText());
            float interest = Float.parseFloat(interestField.getText());
            
            Account tempAccount = new Account(id, balance, interest, customer);
            addNewAccount(tempAccount);
            manageCustomer = new ManageCustomer(stage, customer);
        });
        
        vbox.getChildren().add(balanceLabel);
        vbox.getChildren().add(balanceField);
        vbox.getChildren().add(interestLabel);
        vbox.getChildren().add(interestField);
        vbox.getChildren().add(bOK);
        Button back = new Button("Back");
        vbox.getChildren().add(back);
        back.setOnAction((event) -> {
            ManageCustomer mc = new ManageCustomer(stage, customer);
        });
        
        
        root.setCenter(vbox);
        Scene scene = new Scene(root, 300, 300);
        stage.setScene(scene);
    }
    
    private void addNewAccount(Account account) {
        System.out.println("Account added");
    }

    
    
}