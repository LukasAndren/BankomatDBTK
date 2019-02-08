package BankAdministration;


import bankomatdbtk.Customer;
import java.util.ArrayList;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;


public class BankAdministration extends Application {
    private AddCustomer add;
    private ManageCustomer manage;
    private ChooseCustomer choose;
    
    VBox vbox;
    BorderPane root;
    Scene scene;
    
    Button addCustomer, manageCustomers;
    
    @Override
    public void start(Stage primaryStage) {
        vbox = new VBox();
        addCustomer = new Button("Add new Customer");
        addCustomer.setStyle("-fx-min-height: 149px; \n"
                + "-fx-min-width: 300px;");
        
        manageCustomers = new Button("Manage Customers");
        manageCustomers.setStyle("-fx-min-height: 149px; \n"
                + "-fx-min-width: 300px;");
        vbox.getChildren().add(addCustomer);
        vbox.getChildren().add(manageCustomers);
        
        addCustomer.setOnAction((event) -> {
            add = new AddCustomer(primaryStage);
        });
        manageCustomers.setOnAction((event) -> {
            choose = new ChooseCustomer(primaryStage);
        });
        
        root = new BorderPane();
        
        root.setCenter(vbox);
        
        scene = new Scene(root, 300, 300);
        
        primaryStage.setTitle("Bank Admin Tools");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    
    public static void main(String[] args) {
        launch(args);
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