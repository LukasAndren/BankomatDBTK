package bankomatdbtk;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import javafx.application.Application;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.event.EventType;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class BankomatDBTK extends Application {

    Customer testKund = new Customer();
    Account testKonto = new Account();
    List<Customer> customers = new ArrayList<>();
    List<Account> accounts = new ArrayList<>();
    List<History> histories = new ArrayList<>();
    List<Loan> loans = new ArrayList<>();

    private int pincode;

    private Button b1, b2, b3, b4, b5, b6, b7, b8, b9, b0, bc, bOK;
    private PasswordField display;
    private EnterCode enterCode;
    private Button bUttag, bSaldo, bHistorik;
    private Label lMeny;
    private Label lUttag;
    private Label lSaldo;
    private Label lHistorik;
    private TextArea tArea;
    private TextField tField;
    private Button bUttagOk;
    private BorderPane root;
    private VBox menyKnappar;
    Repository r;

    @Override
    public void start(Stage primaryStage) {

        r = new Repository();
        customers = r.getCustomers();
        accounts = r.getAccounts();
        loans = r.getLoans();
        histories = r.getHistory();

        enterCode = new EnterCode();

        b1 = new Button("1");
        b2 = new Button("2");
        b3 = new Button("3");
        b4 = new Button("4");
        b5 = new Button("5");
        b6 = new Button("6");
        b7 = new Button("7");
        b8 = new Button("8");
        b9 = new Button("9");
        bc = new Button("C");
        b0 = new Button("0");
        bOK = new Button("OK");

        bUttag = new Button("Uttag");
        bSaldo = new Button("Saldo");
        bHistorik = new Button("Historik");
        bUttag.setStyle("-fx-min-width:  200px;"
                + "-fx-font-size: 30px;");
        bSaldo.setStyle("-fx-min-width:  200px;"
                + "-fx-font-size: 30px;");
        bHistorik.setStyle("-fx-min-width:  200px;"
                + "-fx-font-size: 30px;");
        lMeny = new Label("Menyval");

        lUttag = new Label("Uttag");
        lSaldo = new Label("Saldo");
        lHistorik = new Label("Historik");
        tArea = new TextArea();
        tField = new TextField();
        bUttagOk = new Button("Ok");

        display = new PasswordField();

        b1.setOnAction((event) -> {
            enterCode.handle(event);
        });
        b2.setOnAction((event) -> {
            enterCode.handle(event);
        });
        b3.setOnAction((event) -> {
            enterCode.handle(event);
        });
        b4.setOnAction((event) -> {
            enterCode.handle(event);
        });
        b5.setOnAction((event) -> {
            enterCode.handle(event);
        });
        b6.setOnAction((event) -> {
            enterCode.handle(event);
        });
        b7.setOnAction((event) -> {
            enterCode.handle(event);
        });
        b8.setOnAction((event) -> {
            enterCode.handle(event);
        });
        b9.setOnAction((event) -> {
            enterCode.handle(event);
        });
        b0.setOnAction((event) -> {
            enterCode.handle(event);
        });

        bc.setOnAction((event) -> {
            display.setText("");
        });

        bOK.setOnAction((event) -> {
            pincode = Integer.parseInt(display.getText());

            for (Customer customer : customers) {
                if (customer.getPinCode() == pincode) {
                    testKund = customer;
                }
            }
            System.out.println(testKund.getName());

            menuScene(primaryStage);

        });

        bUttag.setOnAction((event) -> {
            uttagScene(primaryStage);
        });

        bSaldo.setOnAction((event) -> {
            saldoScene(primaryStage);
        });

        bHistorik.setOnAction((event) -> {
            historikScene(primaryStage);
        });

        bUttagOk.setOnAction((event) -> {

            int uttag = Integer.parseInt(tField.getText());
            for (Account account : accounts) {
                if (account.getCustomer().getId() == testKund.getId()) {
                    testKonto = account;
                }
            }
            r.callRemoveFunds(testKonto.getId(), uttag);
            System.exit(0);

        });

        root = new BorderPane();
        GridPane buttons = new GridPane();
        menyKnappar = new VBox();

        buttons.add(b1, 0, 0);
        buttons.add(b2, 1, 0);
        buttons.add(b3, 2, 0);
        buttons.add(b4, 0, 1);
        buttons.add(b5, 1, 1);
        buttons.add(b6, 2, 1);
        buttons.add(b7, 0, 2);
        buttons.add(b8, 1, 2);
        buttons.add(b9, 2, 2);
        buttons.add(bc, 0, 3);
        buttons.add(b0, 1, 3);
        buttons.add(bOK, 2, 3);

        menyKnappar.getChildren().addAll(bUttag, bSaldo, bHistorik);

        display.setStyle("-fx-min-height: 50px;"
                + "-fx-font-size: 30px;");

        root.setCenter(buttons);
        root.setTop(display);

        Scene scene = new Scene(root, 300, 465);

        scene.getStylesheets().add(BankomatDBTK.class.getResource("Mystyle.css").toExternalForm());

        primaryStage.setTitle("Hello World!");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public void menuScene(Stage primaryStage) {
        root = new BorderPane();
        root.setCenter(menyKnappar);
        root.setTop(lMeny);

        Scene scene = new Scene(root, 300, 465);

        scene.getStylesheets().add(BankomatDBTK.class.getResource("Mystyle.css").toExternalForm());

        primaryStage.setTitle("Hello World!");
        primaryStage.setScene(scene);
        primaryStage.show();

    }

    public void uttagScene(Stage primaryStage) {
        root = new BorderPane();
        root.setCenter(tField);
        root.setTop(lUttag);
        root.setBottom(bUttagOk);

        Scene scene = new Scene(root, 300, 465);

        scene.getStylesheets().add(BankomatDBTK.class.getResource("Mystyle.css").toExternalForm());

        primaryStage.setTitle("Hello World!");
        primaryStage.setScene(scene);
        primaryStage.show();

    }

    public void saldoScene(Stage primaryStage) {
        root = new BorderPane();
        String s = "";
        List<Account> activeAccounts = new ArrayList<>();
        List<Loan> activeLoans = new ArrayList<>();
        for (Account account : accounts) {
            if (account.getCustomer().getId() == testKund.getId()) {
                activeAccounts.add(account);
                for (Loan loan : loans) {
                    if (loan.getAccount().getId() == account.getId()) {
                        activeLoans.add(loan);
                    }
                }
            }
        }
        for (int i = 0; i < activeAccounts.size(); i++) {
            int k = i + 1;
            s = s + "Konto " + k + ": " + activeAccounts.get(i).getBalance() + "\n";
        }

        for (int i = 0; i < activeLoans.size(); i++) {
            int k = i + 1;
            s = s + "Lån " + k + ": " + activeLoans.get(i).getBalance() + "\n";
        }
        tArea.setText(s);
        root.setCenter(tArea);
        root.setTop(lSaldo);

        Scene scene = new Scene(root, 300, 465);

        scene.getStylesheets().add(BankomatDBTK.class.getResource("Mystyle.css").toExternalForm());

        primaryStage.setTitle("Hello World!");
        primaryStage.setScene(scene);
        primaryStage.show();

    }

    public void historikScene(Stage primaryStage) {
        Calendar c = Calendar.getInstance();
        String s = "";
        for (History history : histories) {
            if (history.getAccount().getCustomer().getId() == testKund.getId()) {
                
                if (history.getDate().toLocalDate().isAfter(history.getDate().toLocalDate().minusMonths(1))) {
                    s = s + history.getDate() + ": Konto: " + history.getAccount().getId() + " Summa: " + history.getSum() + "\n";
                }
            }
        }
        tArea.setText(s);
        root = new BorderPane();
        root.setCenter(tArea);
        root.setTop(lHistorik);

        Scene scene = new Scene(root, 300, 465);

        scene.getStylesheets().add(BankomatDBTK.class.getResource("Mystyle.css").toExternalForm());

        primaryStage.setTitle("Hello World!");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

    private class EnterCode implements EventHandler<Event> {

        @Override
        public void handle(Event event) {
            Button tempB = (Button) event.getSource();
            display.setText(display.getText() + tempB.getText());
        }
    }

}
