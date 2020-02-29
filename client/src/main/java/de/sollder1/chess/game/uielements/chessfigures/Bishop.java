package de.sollder1.chess.game.uielements.chessfigures;

import java.util.ArrayList;
import java.util.List;

import de.sollder1.chess.game.helper.ArrayPoint;
import de.sollder1.chess.game.helper.FigureHelper;


public class Bishop extends Figure {

    public Bishop(ArrayPoint position, int player) {

        super(position, player);

        if (player == 1) {
            getStyleClass().add("lightBishop");
        } else {
            getStyleClass().add("darkBishop");
        }
    }

    public List<ArrayPoint> getPossibleCoordinates() {

        ArrayList<ArrayPoint> posMoves = new ArrayList<>();

        //Nach unten rechts
        for (int x = position.getI() + 1, y = position.getJ() + 1; x < 8 && y < 8; x++, y++) {
            if (!checkForPossibleMove(posMoves, x, y)) {
                break;
            }
        }

        //Nach unten links
        for (int x = position.getI() - 1, y = position.getJ() + 1; x >= 0 && y < 8; x--, y++) {
            if (!checkForPossibleMove(posMoves, x, y)) {
                break;
            }
        }

        //Nach oben rechts
        for (int x = position.getI() + 1, y = position.getJ() - 1; x < 8 && y >= 0; x++, y--) {
            if (!checkForPossibleMove(posMoves, x, y)) {
                break;
            }
        }

        //Nach oben links
        for (int x = position.getI() - 1, y = position.getJ() - 1; x >= 0 && y >= 0; x--, y--) {
            if (!checkForPossibleMove(posMoves, x, y)) {
                break;
            }
        }

        return FigureHelper.filterInvalidMoves(posMoves);

    }

    /*Adds an possible move to the Lsit and return true if
	it is still useful to iterate on.*/
    protected boolean checkForPossibleMove(List<ArrayPoint> posMoves, int x, int y) {

        if (FigureHelper.isTileEmpty(x, y)) {
            posMoves.add(new ArrayPoint(x, y));
            return true;
        } else if (FigureHelper.isTileEnemy(x, y, player)) {
            posMoves.add(new ArrayPoint(x, y, "red"));
            return false;
        } else if (FigureHelper.isTileFriend(x, y, player)) {
            return false;
        }

        return false;
    }

    @Override
    public String toString() {
        return "bi_" + super.toString();
    }

}
