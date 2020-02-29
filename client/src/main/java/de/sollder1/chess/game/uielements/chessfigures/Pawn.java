package de.sollder1.chess.game.uielements.chessfigures;

import java.util.ArrayList;
import java.util.List;

import de.sollder1.chess.game.helper.ArrayPoint;
import de.sollder1.chess.game.helper.FigureHelper;
import de.sollder1.chess.game.helper.Utils;


public class Pawn extends Figure {

    public Pawn(ArrayPoint position, int player) {

        super(position, player);

        if (player == 1) {
            getStyleClass().add("lightPawn");
        } else if (player == 2) {
            getStyleClass().add("darkPawn");
        }
    }

    @Override
    public List<ArrayPoint> getPossibleCoordinates() {

        //mod = 1: Schwarzer Spieler(Spieler 1); mod = 2: Weißer Spieler(Spieler 1)
        int mod = 1;
        if (player == 2) {
            mod = -1;
        }

        List<ArrayPoint> posMoves = new ArrayList<>();

        if (FigureHelper.isTileEmpty(position.getI(), position.getJ() + (1 * mod))) {
            posMoves.add(new ArrayPoint(position.getI(), position.getJ() + (1 * mod)));
        }

        if (FigureHelper.isTileEmpty(position.getI(), position.getJ() + (2 * mod)) && !figureMoved) {
            posMoves.add(new ArrayPoint(position.getI(), position.getJ() + (2 * mod)));
        }

        if (FigureHelper.isTileEnemy(position.getI() + (1 * mod), position.getJ() + (1 * mod), player)) {

            posMoves.add(new ArrayPoint(position.getI() + (1 * mod), position.getJ() + (1 * mod), "red"));
        }

        if (FigureHelper.isTileEnemy(position.getI() + (-1 * mod), position.getJ() + (1 * mod), player)) {

            posMoves.add(new ArrayPoint(position.getI() + (-1 * mod), position.getJ() + (1 * mod), "red"));
        }

        return FigureHelper.filterInvalidMoves(posMoves);

    }

    public List<ArrayPoint> getKillingCoordinates() {
        int mod = 1;
        if (player == 2) {
            mod = -1;
        }
        ArrayList<ArrayPoint> posMoves = new ArrayList<>();

        if (FigureHelper.isTileEmpty(position.getI() + (1 * mod), position.getJ() + (1 * mod))) {

            posMoves.add(new ArrayPoint(position.getI() + (1 * mod), position.getJ() + (1 * mod), "red"));
        }

        if (FigureHelper.isTileEmpty(position.getI() + (-1 * mod), position.getJ() + (1 * mod))) {

            posMoves.add(new ArrayPoint(position.getI() + (-1 * mod), position.getJ() + (1 * mod), "red"));
        }


        return FigureHelper.filterInvalidMoves(posMoves);

    }

    @Override
    public void afterSuccessFullMoveAction(){
        if(position.getJ() == 0 || position.getJ() == 7){
            Utils.interchangePawnDialog(this);
        }
        super.afterSuccessFullMoveAction();
    }

    @Override
    public String toString() {

        return "pw_" + super.toString();

    }


}
