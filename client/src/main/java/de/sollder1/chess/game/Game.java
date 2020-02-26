package de.sollder1.chess.game;

import de.sollder1.chess.game.ai.SimpleAI;
import de.sollder1.chess.game.gui.view.GameView;
import de.sollder1.chess.game.helpObjects.GameMode;
import de.sollder1.chess.game.playground.ChessBoard;
import javafx.scene.control.Label;
import javafx.scene.layout.TilePane;
import javafx.stage.Stage;

public class Game {

    //New StartingPoint for the Game

    private static int player = 1; //Current Player
    private static int myPlayer = 1;


    //Controlls from the Controller Class mainController.java
    public static Label roundLabel;
    public static TilePane graveyardWhite, graveyardBlack;

    //de.sollder1.oldengine.engine.AI ist derzwit Spieler 2
    private static GameMode currentGameMode = GameMode.SINGLE_PVP;

    private static boolean gameInstanceRunning = false;

    public static void startGameInstance(GameMode currentGameMode) {

        if(!gameInstanceRunning){

            gameInstanceRunning = true;

            Game.currentGameMode = currentGameMode;
            GameView gameView = new GameView();
            try {
                gameView.start(new Stage());
            } catch (Exception e) {
                e.printStackTrace();
            }


        }
    }

    public static void stopGameInstance() {
        Game.gameInstanceRunning = false;

    }

    public static void changePlayer() {

        if(player == myPlayer) {

            player = myPlayer == 1 ? 2 : 1;
            roundLabel.setText("Spieler 2: Weiß");

            if(currentGameMode == GameMode.MULTI){
                //Sende den Zug (Server liefert zurück ob er erfolgreich war)
                //a. Wenn zug nicht erfolgreich war wird vom Server der korrekte Stand angefordert und gesetzt
                //   Der User kann ihn dann wiederholen und bekommt auch einene entsprehende MEldung!
                //b. Wenn er erfolgreich war wird die Runde beendet und der andere Spieler ist an der Reihe (Impliziet)!

                //Wird vom Listener wieder aufgerufen wenn der andere Spieler seinen Zug beendet hat

                //Hier

            }

            if(currentGameMode == GameMode.SINGLE_AI){
                SimpleAI.getInstance().performMove(player);
            }

        }else {
            player = myPlayer == 1 ? 1 : 2;
            roundLabel.setText("Spieler 1: Schwarz");
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
}
