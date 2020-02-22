package de.sollder1.chess.game.ai;

import de.sollder1.chess.game.chessfigures.Figure;
import de.sollder1.chess.game.gui.view.GameView;

import java.util.ArrayList;

public abstract class AI {

    protected ArrayList<Figure> aiFigures = new ArrayList<>();

    public abstract void performMove(int player);



    protected void updateList(int player) {

        aiFigures.clear();

        for(Figure[] f : GameView.cb.figuresOnTheField) {
            for(Figure fig : f) {
                if(fig != null && fig.player == player) {
                    aiFigures.add(fig);
                }
            }
        }

    }

}
