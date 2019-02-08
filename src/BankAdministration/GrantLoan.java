package BankAdministration;

import bankomatdbtk.Account;
import bankomatdbtk.Customer;
import bankomatdbtk.Loan;
import bankomatdbtk.Repository;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class GrantLoan {
    public GrantLoan(Stage stage, Customer customer, Account account) {
        BorderPane root = new BorderPane();
        VBox vbox = new VBox();
        Label balanceLabel = new Label("Balance: ");
        TextField balanceField = new TextField();
        Label interestLabel = new Label("Interest: ");
        TextField interestField = new TextField();
        Label amountLabel = new Label("Amount");
        TextField amountField = new TextField();
        Label paymentPlanLabel = new Label("Paymentplan");
        TextField paymentPlanField = new TextField();
        
        
        Button bOK = new Button("OK");
        
        bOK.setOnAction((event) -> {
            int amount = Integer.parseInt(amountField.getText());
            float interest;
            interest = Float.valueOf(interestField.getText());
            int paymentPlan = Integer.parseInt(paymentPlanField.getText());
            
            Repository r = new Repository();
            r.callCreateLoan(account.getId(), amount, interest, paymentPlan);
            //System.exit(0);
        });
        
        vbox.getChildren().add(amountLabel);
        vbox.getChildren().add(amountField);
        vbox.getChildren().add(interestLabel);
        vbox.getChildren().add(interestField);
        vbox.getChildren().add(paymentPlanLabel);
        vbox.getChildren().add(paymentPlanField);
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
    
}
