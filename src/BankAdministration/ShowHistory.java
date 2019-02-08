/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BankAdministration;

import bankomatdbtk.Account;
import bankomatdbtk.Customer;
import bankomatdbtk.History;
import bankomatdbtk.Repository;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class ShowHistory {

    private LocalDate startDate;
    private LocalDate endDate;
    private Label historyLabel;
    private Repository r = new Repository();

    public ShowHistory(Stage stage, Customer customer, Account account) {
        Label startLabel = new Label("Start date");
        DatePicker dpStart = new DatePicker();
        Label endLabel = new Label("End date");
        DatePicker dpEnd = new DatePicker();
        Button bOK = new Button("Show History");

        BorderPane root = new BorderPane();
        VBox topBox = new VBox();
        VBox vbox = new VBox();
        ScrollPane sp = new ScrollPane();
        historyLabel = new Label();

        dpStart.setStyle("-fx-min-width: 300px;");
        dpEnd.setStyle("-fx-min-width: 300px;");

        topBox.getChildren().add(startLabel);
        topBox.getChildren().add(dpStart);
        topBox.getChildren().add(endLabel);
        topBox.getChildren().add(dpEnd);
        topBox.getChildren().add(bOK);

        bOK.setOnAction((event) -> {
            startDate = dpStart.getValue();
            endDate = dpEnd.getValue();
            getHistory(account, startDate, endDate);

        });

        vbox.getChildren().add(historyLabel);

        Button back = new Button("Back");
        vbox.getChildren().add(back);
        back.setOnAction((event) -> {
            ManageAccount ma = new ManageAccount(stage, account, customer);
        });

        sp.setContent(vbox);
        root.setTop(topBox);
        root.setCenter(sp);

        Scene scene = new Scene(root, 300, 300);
        stage.setScene(scene);
    }

    private void getHistory(Account account, LocalDate startDate, LocalDate endDate) {
        List<History> histories = r.getHistory();

        histories = histories.stream().filter(h -> h.getAccount().getId() == account.getId())
                .filter(h -> h.getDate().toLocalDate().isAfter(startDate))
                .filter(h -> h.getDate().toLocalDate().isBefore(endDate)).collect(Collectors.toList());

        for (History history : histories) {
            historyLabel.setText(historyLabel.getText() + history.getDate() + ": " + history.getSum() + "\n");
        }

    }

}
