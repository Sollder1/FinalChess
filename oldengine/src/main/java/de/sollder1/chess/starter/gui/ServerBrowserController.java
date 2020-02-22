package de.sollder1.chess.starter.gui;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.util.ResourceBundle;

public class ServerBrowserController implements Initializable {


    @FXML
    TableView<GameListItem> gameTable;

    @FXML
    TextField serverAddressTextField;

    final ObservableList<GameListItem> tableData = FXCollections.observableArrayList();

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        tableData.addAll(
                new GameListItem("The Game!", "1 / 2", "Nein"),
                new GameListItem("Not the Game!", "2 / 2", "Ja")
        );


        TableColumn<GameListItem, String> gameName = new TableColumn<>("Spielname");
        TableColumn<GameListItem, String> playerCount = new TableColumn("Spieleranzahl");
        TableColumn<GameListItem, String> isRunning = new TableColumn("Läuft bereits");


        gameName.prefWidthProperty().bind(gameTable.widthProperty().multiply(0.33));
        playerCount.prefWidthProperty().bind(gameTable.widthProperty().multiply(0.33));
        isRunning.prefWidthProperty().bind(gameTable.widthProperty().multiply(0.33));

        gameName.setCellValueFactory(
                new PropertyValueFactory<>("gameName")
        );
        playerCount.setCellValueFactory(
                new PropertyValueFactory<>("playerCount")
        );
        isRunning.setCellValueFactory(
                new PropertyValueFactory<>("isRunning")
        );

        gameTable.getColumns().addAll(gameName, playerCount, isRunning);
        gameTable.setItems(tableData);

    }

    public void connectButtonAction(ActionEvent event) {

    }

    public void createGameButtonAction(ActionEvent event) {

    }

    public void refreshButton(ActionEvent event) {

    }

}
