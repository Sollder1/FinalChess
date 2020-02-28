package de.sollder1.chess.game.helpObjects;

import de.sollder1.chess.game.Game;
import de.sollder1.chess.game.chessfigures.*;
import de.sollder1.chess.game.gui.view.GameView;
import de.sollder1.chess.game.playground.ChessBoard;
import de.sollder1.chess.starter.gui.settings.SettingsPojo;
import javafx.scene.control.Alert;
import javafx.scene.control.ChoiceDialog;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Popup;

import java.io.File;

public final class Utils {

    private Utils(){}

    public static void playMusic(String resourcePath, Class<?> clazz){
        String url = clazz.getResource(resourcePath).getFile();

        Media sound = new Media(new File(url).toURI().toString());
        MediaPlayer mediaPlayer = new MediaPlayer(sound);
        mediaPlayer.play();
    }

    public static void showWinDialog(int winner, boolean matt){
        if(SettingsPojo.isUseChessClock()){
            Game.blackClock.stop();
            Game.whiteClock.stop();
        }
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Game Over");
        alert.setHeaderText("Das Spiel ist vorbei");
        alert.setContentText("Gewonnen hat: Spieler " + winner
        + " aufgrund von: " + (matt ? " Schachmatt gesetzt!" : " Zeit abgelaufen!"));

        alert.showAndWait();
    }

    //TODO: ItemId counter einbauen!
    public static void interchangePawnDialog(Pawn origin){
        ChoiceDialog<String> dialog = new ChoiceDialog<>("Bishop", "Bishop", "Knight", "Queen", "Rook");
        dialog.showAndWait().ifPresent(choice ->{
            ChessBoard.removeFigure(origin);

            switch (choice){
                case "Bishop": ChessBoard.addFigure(new Bishop(3, origin.getPosition(), origin.getPlayer()));break;
                case "Knight": ChessBoard.addFigure(new Knight(3, origin.getPosition(), origin.getPlayer()));break;
                case "Queen": ChessBoard.addFigure(new Queen(3, origin.getPosition(), origin.getPlayer()));break;
                case "Rook": ChessBoard.addFigure(new Rook(3, origin.getPosition(), origin.getPlayer()));break;
            }
        });

    }


}
