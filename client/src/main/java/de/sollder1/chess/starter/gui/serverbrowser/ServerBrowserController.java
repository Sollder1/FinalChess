package de.sollder1.chess.starter.gui.serverbrowser;

import de.sollder1.chess.connection.SendabelMessages;
import de.sollder1.chess.connection.Sender;
import de.sollder1.common.util.Logger;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;

import java.net.InetAddress;
import java.net.URL;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ServerBrowserController implements Initializable {


    private static ServerBrowserController controllerInstance;

    @FXML
    TableView<GameItem> gameTable;

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
    @FXML
    Label connectionLabel;
    @FXML
    Circle connectionIndicator;


    private final ObservableList<GameItem> currentGames = FXCollections.observableArrayList();

    private ExecutorService service = Executors.newFixedThreadPool(1);

    private ServerSession serverSession = new ServerSession();


    @Override
    public void initialize(URL location, ResourceBundle resources) {

        loadingBar.setVisible(false);
        controllerInstance = this;

        TableColumn<GameItem, String> gameName = new TableColumn<>("Spielname");
        TableColumn<GameItem, String> playerCount = new TableColumn("Spieleranzahl");
        TableColumn<GameItem, String> isRunning = new TableColumn("Läuft bereits");


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
        gameTable.setItems(currentGames);

    }

    //Ergebnis muss nciht durchgereicht werden, im Fehlerfall wird dem CLinet das einfach kenntlich gemnacht!
    private synchronized void startUITask(Runnable task) {

        connectButton.setDisable(true);
        newGameButton.setDisable(true);
        refreshButton.setDisable(true);
        loadingBar.setVisible(true);

        service.submit(() -> {
            task.run();

            connectButton.setDisable(false);
            newGameButton.setDisable(false);
            refreshButton.setDisable(false);
            loadingBar.setVisible(false);
        });

    }

    public void connectButtonAction(ActionEvent event) {

        if (!serverSession.isLoggedIn()) {
            int clientport;

            try {
                Sender.init(InetAddress.getByName(serverAddressTextField.getText()), 1998);
                //Listener.init
                clientport = Integer.parseInt(serverPortTextField.getText());
            } catch (Exception e) {
                Logger.logE(e);
                ServerBrowserController.setConnectionState("Eingaben inkorrekt", ConnectionColor.YELLOW);
                return;
            }

            startUITask(() -> SendabelMessages.login(clientport, userNameTextField.getText()));
        }

    }

    public void createGameButtonAction(ActionEvent event) {

        TextInputDialog dialog = new TextInputDialog();
        dialog.setTitle("Neues Spiel erstellen");
        dialog.setHeaderText("Neues Spiel erstellen");
        dialog.setContentText("Name des Spiels: ");

        Optional<String> result = dialog.showAndWait();

        result.ifPresent(s -> startUITask(() -> SendabelMessages.createGame(s, serverSession.getClientId())));

    }

    public void refreshButton(ActionEvent event) {
        startUITask(SendabelMessages::currentGames);
    }

    public static void setConnectionState(String connectionState, ConnectionColor color) {
        Platform.runLater(() -> {
            controllerInstance.connectionLabel.setText(connectionState);
            controllerInstance.connectionIndicator.setFill(Paint.valueOf(color.toString()));
        });
    }

    public static void setServerSession(ServerSession serverSession) {
        controllerInstance.serverSession = serverSession;
    }

    public static void setCurrentGames(List<GameItem> currentGames) {
        controllerInstance.currentGames.clear();
        controllerInstance.currentGames.addAll(currentGames);
    }

}
