package de.sollder1.chess.game.chessfigures;

import java.util.ArrayList;

import de.sollder1.chess.game.gui.view.GameView;
import de.sollder1.chess.game.helpObjects.ArrayPoint;
import de.sollder1.chess.game.helpObjects.Point;
import de.sollder1.chess.game.playground.ChessBoardTile;

public class King extends Figure {

    ArrayList<ArrayPoint> possibleCoordinates;

    public King(int size, int itemID, Point position, int player) {

        super(size, itemID, position, player);

        if (player == 1) {
            setStyle("-fx-background-image: url('/gfx/blackKing.png');");
        } else if (player == 2) {
            setStyle("-fx-background-image: url('/gfx/whiteKing.png');");
        }
    }

    public ArrayList<ArrayPoint> getPossibleCoordinates() {

        ArrayList<ArrayPoint> posMoves = new ArrayList<>();
        ArrayPoint position = new ArrayPoint((int) (locationBeforeDragDrop.getX() / size), (int) ((locationBeforeDragDrop.getY() / size)));

        //Mögliche Bewegungen die Performed werden könnten
        ArrayPoint[] potMoves = {new ArrayPoint(0, 1), new ArrayPoint(0, -1), new ArrayPoint(1, 0), new ArrayPoint(-1, 0),
                new ArrayPoint(1, 1), new ArrayPoint(1, -1), new ArrayPoint(-1, 1), new ArrayPoint(-1, -1)};


        //Hinreichendes Kriterium um zu bestimmen ob
        // Bewegungen durchgeführt werden können
        for (int i = 0; i < 8; i++) {

            try {
                //Wenn Feld leer adden und continue
                if (isTileEmpty(position.getX() + potMoves[i].getX(), position.getY() + potMoves[i].getY())) {

                    posMoves.add(new ArrayPoint(position.getX() + potMoves[i].getX(), position.getY() + potMoves[i].getY()));

                } else if (isTileEnemy(position.getX() + potMoves[i].getX(), position.getY() + potMoves[i].getY())) {

                    posMoves.add(new ArrayPoint(position.getX() + potMoves[i].getX(), position.getY() + potMoves[i].getY(), "red"));

                }
            } catch (IndexOutOfBoundsException ignore) {

            }

        }

        return posMoves;

    }

    @Override
    public String toString() {

        return "ki_" + super.toString();

    }

}
