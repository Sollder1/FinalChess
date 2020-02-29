package de.sollder1.chess.game.uielements.chessfigures;

import java.util.ArrayList;
import java.util.List;

import de.sollder1.chess.game.helper.ArrayPoint;
import de.sollder1.chess.game.helper.FigureHelper;

public class Rook extends Figure {

    public Rook(ArrayPoint position, int player) {

        super(position, player);

        if (player == 1) {
            getStyleClass().add("lightRook");
        } else if (player == 2) {
            getStyleClass().add("darkRook");
        }

    }

    public List<ArrayPoint> getPossibleCoordinates() {

        List<ArrayPoint> posMoves = new ArrayList<>();

        //Nach Vorne schauen(schwarz)
        for (int y = position.getJ() + 1; y < 8; y++) {
            if (!checkForPossibleMove(posMoves, position.getI(), y)) {
                break;
            }
        }

        //Nach hinten schauen(schwarz)
        for (int y = position.getJ() - 1; y >= 0; y--) {
            if (!checkForPossibleMove(posMoves, position.getI(), y)) {
                break;
            }
        }

        //Nach links schauen(schwarz)
        for (int x = position.getI() - 1; x >= 0; x--) {
            if (!checkForPossibleMove(posMoves, x, position.getJ())) {
                break;
            }
        }

        //Nach links schauen(schwarz)
        for (int x = position.getI() + 1; x < 8; x++) {
            if (!checkForPossibleMove(posMoves, x, position.getJ())) {
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

    public String toString() {

        return "rk_" + super.toString();

    }

}
