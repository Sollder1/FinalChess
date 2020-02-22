package de.sollder1.chess.starter.gui;

import de.sollder1.chess.connection.MessageInterpreterReply;
import de.sollder1.chess.connection.SendabelMessages;
import de.sollder1.chess.connection.Sender;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.InetAddress;
import java.net.URL;
import java.net.UnknownHostException;
import java.util.ResourceBundle;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ServerBrowserController implements Initializable {


    @FXML
    TableView<GameListItem> gameTable;

    @FXML
    TextField serverAddressTextField;
    @FXML
    TextField serverPortTextField;
    @FXML
    TextField userNameTextField;

    @FXML
    Button connectButton;

    @FXML
    Button newGameButton;

    @FXML
    Button refreshButton;

    @FXML
    ProgressIndicator loadingBar;


    private final ObservableList<GameListItem> tableData = FXCollections.observableArrayList();

    private ExecutorService service = Executors.newFixedThreadPool(1);


    @Override
    public void initialize(URL location, ResourceBundle resources) {

        loadingBar.setVisible(false);

        MessageInterpreterReply.init(this);

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

    //Ergebnis muss nciht durchgereicht werden, im Fehlerfall wird dem CLinet das einfach kenntlich gemnacht!
    private synchronized void startUITask(Callable<Boolean> task){

        connectButton.setDisable(true);
        newGameButton.setDisable(true);
        refreshButton.setDisable(true);
        loadingBar.setVisible(true);

        service.submit(() -> {
            boolean success = false;
            try {
                success = task.call();
            } catch (Exception e) {
                e.printStackTrace();
            }
            connectButton.setDisable(false);
            newGameButton.setDisable(false);
            refreshButton.setDisable(false);
            loadingBar.setVisible(false);

            return success;
        });

    }

    public void connectButtonAction(ActionEvent event) {

        try {
            Sender.init(InetAddress.getByName(serverAddressTextField.getText()), 1998);
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }

        startUITask(()->SendabelMessages.login(serverPortTextField.getText(), userNameTextField.getText()));


    }

    public void createGameButtonAction(ActionEvent event) {

    }

    public void refreshButton(ActionEvent event) {

    }

}
