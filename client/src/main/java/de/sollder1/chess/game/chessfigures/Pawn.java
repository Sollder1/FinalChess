package de.sollder1.chess.game.chessfigures;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import de.sollder1.chess.game.helpObjects.ArrayPoint;
import de.sollder1.chess.game.helpObjects.Point;
import de.sollder1.chess.game.helpObjects.Utils;


public class Pawn extends Figure {

    //itemID the ID within the Players figureSet applied to the figure
    //For others lock at the SuperConstructor
    public Pawn(int itemID, ArrayPoint position, int player) {

        super(itemID, position, player);

        //Outsource to CSS File
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

        if (isTileEmpty(currentPosition.getI(), currentPosition.getJ() + (1 * mod))) {
            posMoves.add(new ArrayPoint(currentPosition.getI(), currentPosition.getJ() + (1 * mod)));
        }

        if (isTileEmpty(currentPosition.getI(), currentPosition.getJ() + (2 * mod)) && !figureMoved) {
            posMoves.add(new ArrayPoint(currentPosition.getI(), currentPosition.getJ() + (2 * mod)));
        }

        if (isTileEnemy(currentPosition.getI() + (1 * mod), currentPosition.getJ() + (1 * mod))) {

            posMoves.add(new ArrayPoint(currentPosition.getI() + (1 * mod), currentPosition.getJ() + (1 * mod), "red"));
        }

        if (isTileEnemy(currentPosition.getI() + (-1 * mod), currentPosition.getJ() + (1 * mod))) {

            posMoves.add(new ArrayPoint(currentPosition.getI() + (-1 * mod), currentPosition.getJ() + (1 * mod), "red"));
        }

        return filterCoordinates(posMoves);

    }

    public List<ArrayPoint> getKillingCoordinates() {
        int mod = 1;
        if (player == 2) {
            mod = -1;
        }
        ArrayList<ArrayPoint> posMoves = new ArrayList<>();

        if (isTileEmpty(currentPosition.getI() + (1 * mod), currentPosition.getJ() + (1 * mod))) {

            posMoves.add(new ArrayPoint(currentPosition.getI() + (1 * mod), currentPosition.getJ() + (1 * mod), "red"));
        }

        if (isTileEmpty(currentPosition.getI() + (-1 * mod), currentPosition.getJ() + (1 * mod))) {

            posMoves.add(new ArrayPoint(currentPosition.getI() + (-1 * mod), currentPosition.getJ() + (1 * mod), "red"));
        }


        return filterCoordinates(posMoves);

    }

    @Override
    public void afterSuccessFullMoveAction(){
        if(currentPosition.getJ() == 0 || currentPosition.getJ() == 7){
            Utils.interchangePawnDialog(this);
        }
        super.afterSuccessFullMoveAction();
    }

    @Override
    public String toString() {

        return "pw_" + super.toString();

    }


}
