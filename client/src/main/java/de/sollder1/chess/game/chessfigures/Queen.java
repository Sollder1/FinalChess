package de.sollder1.chess.game.chessfigures;

import java.util.ArrayList;
import java.util.List;
import de.sollder1.chess.game.helpObjects.ArrayPoint;
import de.sollder1.chess.game.helpObjects.Point;

public class Queen extends Figure {

    ArrayList<ArrayPoint> possibleCoordinates;

    public Queen(int itemID, ArrayPoint position, int player) {

        super(itemID, position, player);

        //Outsource to CSS File
        if (player == 1) {
            getStyleClass().add("lightQueen");
        } else if (player == 2) {
            getStyleClass().add("darkQueen");
        }
    }

    public ArrayList<ArrayPoint> getPossibleCoordinates() {

        ArrayList<ArrayPoint> posMoves = new ArrayList<>();

        //Nach unten rechts
        for (int x = currentPosition.getI() + 1, y = currentPosition.getJ() + 1; x < 8 && y < 8; x++, y++) {
            if (!checkForPossibleMove(posMoves, x, y)) {
                break;
            }
        }

        //Nach unten links
        for (int x = currentPosition.getI() - 1, y = currentPosition.getJ() + 1; x >= 0 && y < 8; x--, y++) {
            if (!checkForPossibleMove(posMoves, x, y)) {
                break;
            }
        }

        //Nach oben rechts
        for (int x = currentPosition.getI() + 1, y = currentPosition.getJ() - 1; x < 8 && y >= 0; x++, y--) {
            if (!checkForPossibleMove(posMoves, x, y)) {
                break;
            }
        }

        //Nach oben links
        for (int x = currentPosition.getI() - 1, y = currentPosition.getJ() - 1; x >= 0 && y >= 0; x--, y--) {
            if (!checkForPossibleMove(posMoves, x, y)) {
                break;
            }
        }

        //Verhalten des Rooks:
        //Nach Vorne schauen(schwarz)
        for (int y = currentPosition.getJ() + 1; y < 8; y++) {
            if (!checkForPossibleMove(posMoves, currentPosition.getI(), y)) {
                break;
            }
        }

        //Nach hinten schauen(schwarz)
        for (int y = currentPosition.getJ() - 1; y >= 0; y--) {
            if (!checkForPossibleMove(posMoves, currentPosition.getI(), y)) {
                break;
            }
        }

        //Nach links schauen(schwarz)
        for (int x = currentPosition.getI() - 1; x >= 0; x--) {
            if (!checkForPossibleMove(posMoves, x, currentPosition.getJ())) {
                break;
            }
        }

        //Nach links schauen(schwarz)
        for (int x = currentPosition.getI() + 1; x < 8; x++) {
            if (!checkForPossibleMove(posMoves, x, currentPosition.getJ())) {
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

        return "qu_" + super.toString();

    }

}
