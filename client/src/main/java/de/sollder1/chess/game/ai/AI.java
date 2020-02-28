package de.sollder1.chess.game.ai;


import de.sollder1.chess.game.Game;
import de.sollder1.chess.game.chessfigures.Figure;
import de.sollder1.chess.game.playground.ChessBoard;
import de.sollder1.chess.starter.gui.settings.SettingsPojo;

import java.util.List;

public abstract class AI {

    public static AI getImplementation(){
        switch (SettingsPojo.getCurrentAiImplementation()){
            case SIMPLE_AI: return new SimpleAI();

            default: return new SimpleAI();
        }
    }

    public void call(int player){
        performMove(player, ChessBoard.getUiFigures());
        Game.changePlayer();
    }

    /**
     * In dieser Funktion sollte der bestmögliche Move von der KI
     * berechnet und anschließend durchgeführt werden.
     *
     * Danach sollte die Funktion zurückkehren, die Runde IST NICHT zu wechseln!
     *
     * Diese Funktion sollte nicht direkt aufgerufen werden!
     *
     * Eine korrekte Implementierung muss in jedem Fall sicherstellen, das ein Zug gemacht wird,
     * da alles andere
     *
     * @param player Der KI spieler
     * @param currentState Der aktuelle zustand des Schachbretts
     */
    protected abstract void performMove(int player, List<Figure> currentState);

}
