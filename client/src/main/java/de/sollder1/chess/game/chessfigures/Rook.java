package de.sollder1.chess.game.chessfigures;

import java.util.ArrayList;
import java.util.List;

import de.sollder1.chess.game.helpObjects.Point;
import de.sollder1.chess.game.helpObjects.ArrayPoint;

public class Rook extends Figure {

    public Rook(int itemID, ArrayPoint position, int player) {

        super(itemID, position, player);

        //Outsource to CSS File
        if (player == 1) {
            getStyleClass().add("lightRook");
        } else if (player == 2) {
            getStyleClass().add("darkRook");
        }

    }

    /*
     * Neues Konzept zur Validierung des Weges:
     *
     * Zuerst wird beim anklicken der Figur die getPossibleCoordinates Methode gerufen,
     * welche alle möglichen stellen zurückgibt auf die die Figur gehen kann.
     * Danach werden genau diese Tiles markiert(Beim bewegen grün, beim schlagen rot)
     *
     * Nachdem dann die Figur losgelassen wurde wird überprüft ob die neue Position
     * mit einer der ermittelten möglichen Positionen übereinstimmt und demensprechend
     * die Figur versetzt oder eben nicht.
     *
     * Zuletzt werden noch die Markierungen der Tiles aufgehoben
     *
     * */
    public ArrayList<ArrayPoint> getPossibleCoordinates() {

        ArrayList<ArrayPoint> posMoves = new ArrayList<>();

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

    public String toString() {

        return "rk_" + super.toString();

    }

}
