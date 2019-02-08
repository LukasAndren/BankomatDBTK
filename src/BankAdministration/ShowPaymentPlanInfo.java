/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BankAdministration;

import bankomatdbtk.Account;
import bankomatdbtk.Customer;
import bankomatdbtk.Loan;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class ShowPaymentPlanInfo {
    private Account account;
    public ShowPaymentPlanInfo(Stage stage, Loan loan, Customer customer, Account account) {
        BorderPane root = new BorderPane();
        this.account = account;
        VBox vbox = new VBox();
        
        Label customerPaymentLabel = new Label("Customers next payment amount");
        Label customerPayment = new Label(getCustomerPayment(loan));
        Label bankEarningsLabel = new Label("Total earnings from loan");
        Label bankEarnings = new Label(getBankEarning(loan));
        
        customerPaymentLabel.setStyle("-fx-font-weight: bold;\n"
                + "-fx-font-size: 16;");
        bankEarningsLabel.setStyle("-fx-font-weight: bold;\n"
                + "-fx-font-size: 16;");
        
        vbox.getChildren().add(customerPaymentLabel);
        vbox.getChildren().add(customerPayment);
        vbox.getChildren().add(bankEarningsLabel);
        vbox.getChildren().add(bankEarnings);
        Button back = new Button("Back");
        vbox.getChildren().add(back);
        back.setOnAction((event) -> {
            ManageLoan ml = new ManageLoan(stage, loan, customer, account);
        });
        
        root.setCenter(vbox);
        Scene scene = new Scene(root, 300, 300);
        stage.setScene(scene);
    }
    
    private String getBankEarning(Loan loan) {
        return loan.getAmount() * (loan.getInterest() / 100) + "";
    }

    private String getCustomerPayment(Loan loan) {
        return loan.getAmount() / loan.getPaymantPlan() + "";
    } 
}
