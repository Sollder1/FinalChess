package de.sollder1.chess.game.chessfigures;

import java.util.ArrayList;
import java.util.List;

import de.sollder1.chess.game.helpObjects.ArrayPoint;
import de.sollder1.chess.game.helpObjects.Point;


public class Bishop extends Figure {

    public Bishop(int size, int itemID, Point position, int player) {

        super(size, itemID, position, player);

        if (player == 1) {
            setStyle("-fx-background-image: url('/gfx/blackBishop.png');");
        } else {
            setStyle("-fx-background-image: url('/gfx/whiteBishop.png');");
        }
    }

    public List<ArrayPoint> getPossibleCoordinates() {

        ArrayList<ArrayPoint> posMoves = new ArrayList<>();
        ArrayPoint position = new ArrayPoint((int) (locationBeforeDragDrop.getX() / size), (int) (locationBeforeDragDrop.getY() / size));

        //Nach unten rechts
        for (int x = position.getX() + 1, y = position.getY() + 1; x < 8 && y < 8; x++, y++) {
            if (!checkForPossibleMove(posMoves, x, y)) {
                break;
            }
        }

        //Nach unten links
        for (int x = position.getX() - 1, y = position.getY() + 1; x >= 0 && y < 8; x--, y++) {
            if (!checkForPossibleMove(posMoves, x, y)) {
                break;
            }
        }

        //Nach oben rechts
        for (int x = position.getX() + 1, y = position.getY() - 1; x < 8 && y >= 0; x++, y--) {
            if (!checkForPossibleMove(posMoves, x, y)) {
                break;
            }
        }

        //Nach oben links
        for (int x = position.getX() - 1, y = position.getY() - 1; x >= 0 && y >= 0; x--, y--) {
            if (!checkForPossibleMove(posMoves, x, y)) {
                break;
            }
        }

        return posMoves;

    }

    /*Adds an possible move to the Lsit and return true if
	it is still useful to iterate on.*/
    protected boolean checkForPossibleMove(List<ArrayPoint> posMoves, int x, int y) {

        if (isTileEmpty(x, y)) {
            posMoves.add(new ArrayPoint(x, y));
            return true;
        } else if (isTileEnemy(x, y)) {
            posMoves.add(new ArrayPoint(x, y, "red"));
            return false;
        } else if (isTileFriend(x, y)) {
            return false;
        }

        return false;
    }

    @Override
    public String toString() {
        return "bi_" + super.toString();
    }

}
