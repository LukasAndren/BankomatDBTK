package BankAdministration;


import bankomatdbtk.Customer;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class AddCustomer {

    public AddCustomer(Stage stage) {
        BorderPane root = new BorderPane();
        Label nameLabel = new Label("Enter Customer Name");
        TextField nameField = new TextField();

        Label pincodeLabel = new Label("Enter Customer Pincode");
        PasswordField pincodeField = new PasswordField();

        Button ok = new Button("Add Customer");
        Button back = new Button("back");

        VBox vbox = new VBox();

        vbox.getChildren().add(nameLabel);
        vbox.getChildren().add(nameField);
        vbox.getChildren().add(pincodeLabel);
        vbox.getChildren().add(pincodeField);
        vbox.getChildren().add(ok);
        vbox.getChildren().add(back);

        ok.setOnAction((event) -> {
            String n = nameField.getText();
            int p = 0;
            try {
                p = Integer.parseInt(pincodeField.getText());
            } catch (Exception e) {
                System.out.println("Not a valid pincode");
                AddCustomer addCustomer = new AddCustomer(stage);
            }

            int ID = 666;

            Customer customer = new Customer(ID, n, p);
            BankAdministration ba = new BankAdministration();
        });
        
        back.setOnAction((event) -> { 
            BankAdministration ba = new BankAdministration();
            ba.start(stage);
        });

        root.setCenter(vbox);
        Scene scene = new Scene(root, 300, 300);
        stage.setScene(scene);
    }
}