package de.sollder1.chess.game.gui;

import de.sollder1.chess.game.Game;
import de.sollder1.chess.game.gui.view.GameView;
import de.sollder1.chess.game.helpObjects.Utils;
import javafx.application.Platform;
import javafx.scene.control.Label;
import javafx.scene.text.Font;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ChessClock extends Label {

    private static ExecutorService service = Executors.newFixedThreadPool(4);

    private boolean run = false;
    private TimeStamp stamp;
    private boolean started;
    private int player;

    public ChessClock(TimeStamp stamp, int player) {
        this.stamp = stamp;
        this.player = player;
        setText(stamp.toString());
        setFont(Font.font(24));
    }

    public synchronized void start() {
        if (!started) {
            started = true;
            service.submit(() -> {
                while (!stamp.ended()) {
                    if (run) {
                        stamp.decrement();
                        Platform.runLater(() -> setText(stamp.toString()));
                    }
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                timeUpFunction();
            });
        }
    }

    public synchronized void stop() {
        run = false;
    }

    public synchronized void resume() {
        run = true;
    }

    private void timeUpFunction() {
        System.err.println("Das Spiel endet, Spieler " + (player == 1 ? 2 : 1) + " ist die Zeit ausgegangen!");
        Platform.runLater(() -> {
            Utils.showWinDialog((player == 1 ? 2 : 1), false);
            Game.gameView.mainStage.close();
            GameView.close();
        });

    }

}
