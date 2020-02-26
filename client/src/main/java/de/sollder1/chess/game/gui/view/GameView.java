package de.sollder1.chess.game.gui.view;

import de.sollder1.chess.App;
import de.sollder1.chess.game.Game;
import de.sollder1.chess.starter.Starter;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import de.sollder1.chess.game.playground.ChessBoard;

public class GameView extends Application {

    public static final int SIZE = 600;
    public static Pane mainPane; // The mainPane which gets initialized by the FXML-File

    @Override
    public void start(Stage mainStage) {

        try {
            //FXML File is getting loaded
            FXMLLoader loader = new FXMLLoader(GameView.class.getResource("/gui/game/GameView.fxml"));
            mainPane = loader.load();
            mainPane.getChildren().add(ChessBoard.initInstance(SIZE));

            Scene s = new Scene(mainPane);
		
			/*
			TODO: Code for choosing other Themes
			*/
            s.getStylesheets().add(getClass().getResource("/themes/standard/mainView.css").toExternalForm());

            mainStage.setOnCloseRequest(event -> {
                Starter.startStarterInstance();
                Game.stopGameInstance();
                //Im Multiplaxer eventuell ausloggen.
            });

            //Stage gets inizialised
            mainStage.setScene(s);
            mainStage.setTitle("FinalChess " + App.getVersion());
            mainStage.setResizable(false);
            mainStage.show();

        } catch (Exception e) {
            e.printStackTrace();
        }

    }


}
