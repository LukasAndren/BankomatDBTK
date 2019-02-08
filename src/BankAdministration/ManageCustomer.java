package BankAdministration;

import bankomatdbtk.Customer;
import bankomatdbtk.Repository;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class ManageCustomer {
    private AddAccount addAccount;
    private ChooseAccount chooseAccount;
    private GrantLoan grantLoan;
    private ChooseLoan chooseLoan;
    private ChooseCustomer chooseCustomer;
    private Customer customer;
    private Repository r = new Repository();
    
    public ManageCustomer(Stage stage, Customer customer) {
        this.customer = customer;
        BorderPane root = new BorderPane();
        VBox vbox = new VBox();
        Button changeCustomerInfoButton = new Button("Change Customer Info");
        Button addAccountButton = new Button("Add new Account");
        Button chooseAccountButton = new Button("Manage Accounts");
        Button back = new Button("Back");
        
        changeCustomerInfoButton.setStyle("-fx-min-width: 300px;"
                + "-fx-min-height: 50px;");
        addAccountButton.setStyle("-fx-min-width: 300px;"
                + "-fx-min-height: 50px;");
        chooseAccountButton.setStyle("-fx-min-width: 300px;"
                + "-fx-min-height: 50px;");


        
        
        changeCustomerInfoButton.setOnAction((event) -> {
            changeCustomerInfo(stage);
        });
        addAccountButton.setOnAction((event) -> {
            addAccount = new AddAccount(stage, customer);
        });
        chooseAccountButton.setOnAction((event) -> {
            chooseAccount = new ChooseAccount(stage, customer);
        });

        
        back.setOnAction((event) -> {
            chooseCustomer = new ChooseCustomer(stage);
        });
        
        vbox.getChildren().add(changeCustomerInfoButton);
        vbox.getChildren().add(addAccountButton);
        vbox.getChildren().add(chooseAccountButton);

        vbox.getChildren().add(back);
        root.setCenter(vbox);
        
        Scene scene = new Scene(root, 300, 300);
        stage.setScene(scene);
        
        System.out.println(customer.getName());
    }
    
    private void changeCustomerInfo(Stage stage) {
        VBox vbox = new VBox();
        BorderPane root = new BorderPane();
        
        Label nameLabel = new Label("Name");
        TextField nameField = new TextField();
        Label pincodeLabel = new Label("Pincode");
        TextField pincodeField = new TextField();
        
        Button bOK = new Button("OK");
        bOK.setOnAction((event) -> {
            r.callUpdateCustomer(customer.getId(), nameField.getText());
        });
        
        vbox.getChildren().add(nameLabel);
        vbox.getChildren().add(nameField);
        vbox.getChildren().add(pincodeLabel);
        vbox.getChildren().add(pincodeField);
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