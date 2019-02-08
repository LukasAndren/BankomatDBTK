package BankAdministration;

import bankomatdbtk.Account;
import bankomatdbtk.Customer;
import bankomatdbtk.Repository;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class ManageAccount {

    private Button bChangeInterest, bDeleteAccount, bAddFounds, bRemoveFounds, bShowHistory;
    private Account account;
    private ShowHistory showHistory;
    private Customer customer;
    private GrantLoan grantLoan;
    private Repository r = new Repository();

    public ManageAccount(Stage stage, Account account, Customer customer) {
        this.customer = customer;
        this.account = account;
        BorderPane root = new BorderPane();
        VBox vbox = new VBox();
        Button back = new Button("Back");

        Label label = new Label("Manage Account");

        bShowHistory = new Button("Show History");
        bAddFounds = new Button("Add Founds");
        bRemoveFounds = new Button("Remove Founds");
        bChangeInterest = new Button("Change Interest");
        bDeleteAccount = new Button("Delete Account");
        Button grantLoanButton = new Button("Grant new Loan");
        Button manageLoansButton = new Button("Manage Loans");

        manageLoansButton.setStyle("-fx-min-width: 300px;"
                + "-fx-min-height: 50px;");
        manageLoansButton.setAlignment(Pos.BASELINE_LEFT);
        grantLoanButton.setStyle("-fx-min-width: 300px;"
                + "-fx-min-height: 50px;");
        grantLoanButton.setAlignment(Pos.BASELINE_LEFT);
        bShowHistory.setStyle("-fx-min-width: 300px;"
                + "-fx-min-height: 50px;");
        bShowHistory.setAlignment(Pos.BASELINE_LEFT);
        bAddFounds.setStyle("-fx-min-width: 300px;"
                + "-fx-min-height: 50px;");
        bAddFounds.setAlignment(Pos.BASELINE_LEFT);
        bRemoveFounds.setStyle("-fx-min-width: 300px;"
                + "-fx-min-height: 50px;");
        bRemoveFounds.setAlignment(Pos.BASELINE_LEFT);
        bChangeInterest.setStyle("-fx-min-width: 300px;"
                + "-fx-min-height: 50px;");
        bChangeInterest.setAlignment(Pos.BASELINE_LEFT);
        bDeleteAccount.setStyle("-fx-min-width: 300px;"
                + "-fx-min-height: 50px;");
        bDeleteAccount.setAlignment(Pos.BASELINE_LEFT);

        vbox.getChildren().add(bShowHistory);
        vbox.getChildren().add(bAddFounds);
        vbox.getChildren().add(bRemoveFounds);
        vbox.getChildren().add(bChangeInterest);
        vbox.getChildren().add(bDeleteAccount);
        vbox.getChildren().add(manageLoansButton);
        vbox.getChildren().add(grantLoanButton);
        vbox.getChildren().add(back);

        bShowHistory.setOnAction((event) -> {
            showHistory = new ShowHistory(stage, customer, account);
        });
        bAddFounds.setOnAction((event) -> {
            addFounds(stage);
        });
        bRemoveFounds.setOnAction((event) -> {
            removeFounds(stage);
        });
        bChangeInterest.setOnAction((event) -> {
            changeInterest(stage);
        });
        bDeleteAccount.setOnAction((event) -> {
            deleteAccount(stage);
        });
        back.setOnAction((event) -> {
            ChooseAccount ca = new ChooseAccount(stage, customer);
        });

        grantLoanButton.setOnAction((event) -> {
            grantLoan = new GrantLoan(stage, customer, account);
        });

        manageLoansButton.setOnAction((event) -> {
            ChooseLoan chooseLoan = new ChooseLoan(stage, customer, account);
        });

        root.setTop(label);
        root.setCenter(vbox);

        Scene scene = new Scene(root, 300, 500);
        stage.setScene(scene);

    }

    private void addFounds(Stage stage) {
        Label foundsLabel = new Label("Add Founds: ");
        TextField foundsField = new TextField();
        Button bOK = new Button("OK");

        bOK.setOnAction((event) -> {
            r.callAddFunds(account.getId(), Integer.parseInt(foundsField.getText()));
        });

        VBox vbox = new VBox();
        BorderPane root = new BorderPane();

        vbox.getChildren().add(foundsLabel);
        vbox.getChildren().add(foundsField);
        vbox.getChildren().add(bOK);
        Button back = new Button("Back");
        vbox.getChildren().add(back);
        back.setOnAction((event) -> {
            ManageAccount ma = new ManageAccount(stage, account, customer);
        });

        root.setCenter(vbox);

        Scene scene = new Scene(root, 300, 300);
        stage.setScene(scene);
    }

    private void changeInterest(Stage stage) {
        Label interestLabel = new Label("Change Interest to: ");
        TextField interestField = new TextField();
        Button bOK = new Button("OK");

        bOK.setOnAction((event) -> {
            r.callChangeAccountInterest(account.getId(), Float.parseFloat(interestField.getText()));
        });

        VBox vbox = new VBox();
        BorderPane root = new BorderPane();

        vbox.getChildren().add(interestLabel);
        vbox.getChildren().add(interestField);
        vbox.getChildren().add(bOK);
        Button back = new Button("Back");
        vbox.getChildren().add(back);
        back.setOnAction((event) -> {
            ManageAccount ma = new ManageAccount(stage, account, customer);
        });

        root.setCenter(vbox);

        Scene scene = new Scene(root, 300, 300);
        stage.setScene(scene);
    }

    private void removeFounds(Stage stage) {
        Label foundsLabel = new Label("Remove Founds:  ");
        TextField foundsField = new TextField();
        Button bOK = new Button("OK");

        bOK.setOnAction((event) -> {
            r.callRemoveFunds(account.getId(), Integer.parseInt(foundsField.getText()));
        });

        VBox vbox = new VBox();
        BorderPane root = new BorderPane();

        vbox.getChildren().add(foundsLabel);
        vbox.getChildren().add(foundsField);
        vbox.getChildren().add(bOK);
        Button back = new Button("Back");
        vbox.getChildren().add(back);
        back.setOnAction((event) -> {
            ManageAccount ma = new ManageAccount(stage, account, customer);
        });

        root.setCenter(vbox);

        Scene scene = new Scene(root, 300, 300);
        stage.setScene(scene);
    }

    private void deleteAccount(Stage stage) {
        r.callDeleteAccount(account.getId());
        ManageCustomer mc = new ManageCustomer(stage, customer);
    }

}
