package de.sollder1.chess.starter.gui;

import de.sollder1.chess.App;
import de.sollder1.chess.game.Game;
import de.sollder1.chess.game.gui.view.GameView;
import de.sollder1.chess.game.helpObjects.GameMode;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.io.IOException;

public class StarterView extends Application {


    @Override
    public void start(Stage mainStage) {

        BorderPane startPage = new BorderPane();

        startPage.setCenter(buildStartPageCenter(mainStage));

        Scene s = new Scene(startPage);

        mainStage.setScene(s);
        mainStage.setTitle("FinalChess " + App.getVersion());
        mainStage.setWidth(800);
        mainStage.setHeight(500);
        mainStage.setResizable(false);
        mainStage.show();

    }

    private VBox buildStartPageCenter(Stage mainStage) {

        VBox startPageCenter = new VBox();

        Button singlePVPStart = buildMenuButton("Einzelspieler PVP");
        singlePVPStart.setOnAction( event -> {
            mainStage.close();
            Game.startGameInstance(GameMode.SINGLE_PVP);
        });

        Button singleAiStart = buildMenuButton("Einzelspieler KI");
        singleAiStart.setOnAction( event -> {
            mainStage.close();
            Game.startGameInstance(GameMode.SINGLE_AI);
        });

        Button multiStart = buildMenuButton("Multiplayer");
        multiStart.setOnAction( event -> {

            FXMLLoader loader = new FXMLLoader(GameView.class.getResource("/gui/starter/serverBrowserView.fxml"));
            try {
                Scene scene = new Scene(loader.load());
                mainStage.setScene(scene);
            } catch (IOException e) {
                e.printStackTrace();
            }

        });

        mainStage.setOnCloseRequest(event -> {
            System.exit(1);
            //TODO: Logout
        });

        Button settings = buildMenuButton("Einstellungen");
        settings.setOnAction( event -> {
            Alert a = new Alert(Alert.AlertType.INFORMATION, "No Settings YET!");
            a.show();
        });

        startPageCenter.setPadding(new Insets(50));

        startPageCenter.setSpacing(10);
        startPageCenter.setAlignment(Pos.CENTER);

        startPageCenter.getChildren().addAll(singlePVPStart, singleAiStart, multiStart, settings);

        return startPageCenter;
    }

    private Button buildMenuButton(String text) {
        Button button = new Button(text);
        button.setPrefWidth(800);
        button.setFont(Font.font(20));

        return button;
    }

    public static void externalLaunch(){
        launch();
    }

}
