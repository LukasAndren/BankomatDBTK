package bankomatdbtk;

import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class BankomatDBTK extends Application {

    private String pincode;

    private Button b1, b2, b3, b4, b5, b6, b7, b8, b9, b0, bc, bOK;
    private PasswordField display;
    private EnterCode enterCode;

    @Override
    public void start(Stage primaryStage) {
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
            pincode = display.getText();
            System.out.println(pincode);
        });

        BorderPane root = new BorderPane();
        GridPane buttons = new GridPane();

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
