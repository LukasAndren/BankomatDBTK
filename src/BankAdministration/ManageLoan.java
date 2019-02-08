package BankAdministration;

import bankomatdbtk.Account;
import bankomatdbtk.Customer;
import bankomatdbtk.Loan;
import bankomatdbtk.Repository;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class ManageLoan {
    private Loan loan;
    private ShowPaymentPlanInfo paymentInfo;
    private Customer customer;
    private Repository r = new Repository();
    private Account account;
    Button bChangeInterest, bChangePaymentPlan, bShowPaymentPlanInfo;
    
    public ManageLoan(Stage stage, Loan loan, Customer customer, Account account) {
        this.customer = customer;
        this.account = account;
        this.loan = loan;
        BorderPane root = new BorderPane();
        VBox vbox = new VBox();
        
        Label label = new Label("Manage Loan");
        
        bChangeInterest = new Button("Change Interest");
        bChangePaymentPlan = new Button("Change Payment Plan");
        bShowPaymentPlanInfo = new Button("Show Payment Plan Info");
        
        
        bChangeInterest.setStyle("-fx-min-width: 300px;"
                + "-fx-min-height: 50px;");
        bChangeInterest.setAlignment(Pos.BASELINE_LEFT);
        
        bChangePaymentPlan.setStyle("-fx-min-width: 300px;"
                + "-fx-min-height: 50px;");
        bChangePaymentPlan.setAlignment(Pos.BASELINE_LEFT);
        
        bShowPaymentPlanInfo.setStyle("-fx-min-width: 300px;"
                + "-fx-min-height: 50px;");
        bShowPaymentPlanInfo.setAlignment(Pos.BASELINE_LEFT);
        
        
        bChangeInterest.setOnAction((event) -> {
            changeInterest(stage);
        });
        
        bChangePaymentPlan.setOnAction((event) -> {
            changePaymentPlan(stage);
        });
        
        bShowPaymentPlanInfo.setOnAction((event) -> {
            paymentInfo = new ShowPaymentPlanInfo(stage, loan, customer, account);
        });
        
        vbox.getChildren().add(bChangeInterest);
        vbox.getChildren().add(bChangePaymentPlan);
        vbox.getChildren().add(bShowPaymentPlanInfo);
        Button back = new Button("Back");
        vbox.getChildren().add(back);
        back.setOnAction((event) -> {
            ChooseLoan cl = new ChooseLoan(stage, customer, account);
        });
        
        root.setCenter(vbox);
        
        Scene scene = new Scene(root, 300, 300);
        stage.setScene(scene);
        
    }
    
    private void changeInterest(Stage stage) {
        
        Label interestLabel=  new Label("Change interest: ");
        TextField interestField = new TextField();
        Button bOK = new Button("OK");
        
        bOK.setOnAction((event) -> {
            r.callChangeLoanInterest(loan.getId(), Float.parseFloat(interestField.getText()));
        });
        
        
        VBox vbox = new VBox();
        BorderPane root = new BorderPane();
        
        vbox.getChildren().add(interestLabel);
        vbox.getChildren().add(interestField);
        vbox.getChildren().add(bOK);
        Button back = new Button("Back");
        vbox.getChildren().add(back);
        back.setOnAction((event) -> {
            ManageLoan ml = new ManageLoan(stage, loan, customer, account);
        });
        
        
        root.setCenter(vbox);
        
        Scene scene = new Scene(root, 300, 300);
        stage.setScene(scene);
        
    }
    
    private void changePaymentPlan(Stage stage) {
        Label paymentLabel=  new Label("Change interest: ");
        TextField paymentField = new TextField();
        Button bOK = new Button("OK");
        
        bOK.setOnAction((event) -> {
            r.callChangePlan(loan.getId(), Integer.parseInt(paymentField.getText()));
        });
        
        
        VBox vbox = new VBox();
        BorderPane root = new BorderPane();
        
        vbox.getChildren().add(paymentLabel);
        vbox.getChildren().add(paymentField);
        vbox.getChildren().add(bOK);
        Button back = new Button("Back");
        vbox.getChildren().add(back);
        back.setOnAction((event) -> {
            ManageLoan ml = new ManageLoan(stage, loan, customer, account);
        });
        
        
        root.setCenter(vbox);
        
        Scene scene = new Scene(root, 300, 300);
        stage.setScene(scene);
    }

}
