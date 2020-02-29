package de.sollder1.chess.game.gui;

import de.sollder1.chess.App;
import de.sollder1.chess.game.Game;
import de.sollder1.chess.starter.Starter;
import de.sollder1.chess.starter.gui.settings.SettingsPojo;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import de.sollder1.chess.game.uielements.chessboard.ChessBoard;

public class GameView extends Application {

    public static final int SIZE = 600;
    private static Pane mainFrame;
    private static ChessBoard chessBoard;

    @Override
    public void start(Stage mainStage) {

        try {
            //FXML File is getting loaded
            FXMLLoader loader = new FXMLLoader(GameView.class.getResource("/gui/game/GameView.fxml"));
            mainFrame = loader.load();
            chessBoard = new ChessBoard();
            mainFrame.getChildren().add(chessBoard);

            Scene s = new Scene(mainFrame);

            s.getStylesheets().add(getClass().getResource(SettingsPojo.getCurrentTheme().getPathToCss()).toExternalForm());

            mainStage.setOnCloseRequest(event -> {
                GameView.close();
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

    public static void close(){
        Starter.startStarterInstance();
        Game.stopGameInstance();
    }

    public static Pane getMainFrame() {
        return mainFrame;
    }

    public static ChessBoard board(){
        return chessBoard;
    }
}
