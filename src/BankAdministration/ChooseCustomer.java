package BankAdministration;


import bankomatdbtk.Customer;
import bankomatdbtk.Repository;
import java.util.ArrayList;
import java.util.List;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class ChooseCustomer {
    ManageCustomer manage;
    public ChooseCustomer(Stage stage) {
        BorderPane root = new BorderPane();
        VBox vbox = new VBox();
        ScrollPane sp = new ScrollPane();
        Repository r = new Repository();
        List<Customer> customers = r.getCustomers();
        
        for (Customer customer : customers) {
            Button tempButton = new Button("ID: " + customer.getId() + ": " + customer.getName());
            tempButton.setStyle("-fx-min-width: 300px;"
                    + "-fx-min-height: 50px;");
            
            tempButton.setOnAction((event) -> {
                manage = new ManageCustomer(stage, customer);
            });
            
            vbox.getChildren().add(tempButton);
        }
        Button back = new Button("Back");
        vbox.getChildren().add(back);
        back.setOnAction((event) -> {
            BankAdministration ba = new BankAdministration();
            ba.start(stage);
        });
        sp.setContent(vbox);
        root.setCenter(sp);
        
        Scene scene = new Scene(root, 300,300);
        stage.setScene(scene);
    }
    
    private ArrayList<Customer> getCustomer() {
        ArrayList<Customer> customers = new ArrayList();
        
        Customer c1 = new Customer(1, "kalle", 1234);
        Customer c2 = new Customer(2, "urban", 1234);
        
        customers.add(c1);
        customers.add(c2);
        
        return customers;
    }

}