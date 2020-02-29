package de.sollder1.chess.game;

import de.sollder1.chess.game.ai.AI;
import de.sollder1.chess.game.uielements.other.ChessClock;
import de.sollder1.chess.game.gui.GameView;
import de.sollder1.chess.game.helper.GameMode;
import de.sollder1.chess.game.uielements.chessboard.ChessBoard;
import de.sollder1.chess.starter.gui.settings.SettingsPojo;
import javafx.scene.control.Label;
import javafx.scene.layout.TilePane;
import javafx.stage.Stage;

public class Game {

    //New StartingPoint for the Game

    private static int player = 1; //Current Player
    private static int myPlayer = 1;

    //Controlls from the Controller Class mainController.java
    private static Label roundLabel;
    private static TilePane graveyardWhite, graveyardBlack;
    private static ChessClock whiteClock, blackClock;

    private static GameMode currentGameMode = GameMode.SINGLE_PVP;
    private static boolean gameInstanceRunning = false;
    private static GameView gameView;

    private static AI ai;

    public static void startGameInstance(GameMode currentGameMode) {

        if(!gameInstanceRunning){
            gameInstanceRunning = true;

            Game.currentGameMode = currentGameMode;
            gameView = new GameView();
            try {
                gameView.start(new Stage());
                if(SettingsPojo.isUseChessClock()){
                    blackClock.start();
                    whiteClock.start();
                    blackClock.resume();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public static void stopGameInstance() {
        try {
            player = 1;
            myPlayer = 1;
            Game.gameView.stop();
        } catch (Exception e) {
            e.printStackTrace();
        }
        Game.gameInstanceRunning = false;

    }

    public static void changePlayer() {

        //Siegbedingung prüfen:
        if(ChessBoard.gameOver()){
            ((Stage) roundLabel.getScene().getWindow()).close();
        }


        if(player == myPlayer) {

            player = myPlayer == 1 ? 2 : 1;
            roundLabel.setText("Spieler 2: Weiß");

            if(SettingsPojo.isUseChessClock()){
                blackClock.stop();
                whiteClock.resume();
            }

            if(currentGameMode == GameMode.MULTI){
                //Sende den Zug (Server liefert zurück ob er erfolgreich war)
                //a. Wenn zug nicht erfolgreich war wird vom Server der korrekte Stand angefordert und gesetzt
                //   Der User kann ihn dann wiederholen und bekommt auch einene entsprehende MEldung!
                //b. Wenn er erfolgreich war wird die Runde beendet und der andere Spieler ist an der Reihe (Impliziet)!

                //Wird vom Listener wieder aufgerufen wenn der andere Spieler seinen Zug beendet hat

                //Hier

            }

            if(currentGameMode == GameMode.SINGLE_AI){
                if(ai == null){
                    ai = AI.getImplementation();
                }
                ai.call(player);
            }

        }else {
            player = myPlayer == 1 ? 1 : 2;
            roundLabel.setText("Spieler 1: Schwarz");

            if(SettingsPojo.isUseChessClock()){
                blackClock.resume();
                whiteClock.stop();
            }

        }
    }

    public static int getPlayer() {
        return player;
    }

    public static void setPlayer(int player) {
        Game.player = player;
    }

    public static int getMyPlayer() {
        return myPlayer;
    }

    public static void setMyPlayer(int myPlayer) {
        Game.myPlayer = myPlayer;
    }

    public static Label getRoundLabel() {
        return roundLabel;
    }

    public static void setRoundLabel(Label roundLabel) {
        Game.roundLabel = roundLabel;
    }

    public static TilePane getGraveyardWhite() {
        return graveyardWhite;
    }

    public static void setGraveyardWhite(TilePane graveyardWhite) {
        Game.graveyardWhite = graveyardWhite;
    }

    public static TilePane getGraveyardBlack() {
        return graveyardBlack;
    }

    public static void setGraveyardBlack(TilePane graveyardBlack) {
        Game.graveyardBlack = graveyardBlack;
    }

    public static ChessClock getWhiteClock() {
        return whiteClock;
    }

    public static void setWhiteClock(ChessClock whiteClock) {
        Game.whiteClock = whiteClock;
    }

    public static ChessClock getBlackClock() {
        return blackClock;
    }

    public static void setBlackClock(ChessClock blackClock) {
        Game.blackClock = blackClock;
    }

    public static GameView getGameView() {
        return gameView;
    }
}
