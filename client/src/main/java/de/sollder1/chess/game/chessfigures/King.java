package de.sollder1.chess.game.chessfigures;

import java.util.ArrayList;

import de.sollder1.chess.game.helpObjects.ArrayPoint;
import de.sollder1.chess.game.helpObjects.Point;
import de.sollder1.chess.game.helpObjects.Rochade;
import de.sollder1.chess.game.playground.ChessBoard;
import de.sollder1.chess.game.playground.ChessBoardTile;

public class King extends Figure {

    public King(int size, int itemID, Point position, int player) {

        super(itemID, position, player);

        if (player == 1) {
            getStyleClass().add("lightKing");
        } else if (player == 2) {
            getStyleClass().add("darkKing");
        }
    }

    public ArrayList<ArrayPoint> getPossibleCoordinates() {

        ArrayList<ArrayPoint> posMoves = new ArrayList<>();
        ArrayPoint position = new ArrayPoint((int) (locationBeforeDragDrop.getX() / FIGURE_SIZE), (int) ((locationBeforeDragDrop.getY() / FIGURE_SIZE)));

        handleRochadeMoves(posMoves, position);

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

    private void handleRochadeMoves(ArrayList<ArrayPoint> posMoves, ArrayPoint position) {
        Figure[][] figures = ChessBoard.getInstance().getUiFigures();
        ChessBoardTile[][] tiles = ChessBoard.getInstance().getTiles();

        if (figureMoved) {
            return;
        }

        //Rochade Prüfen:
        if (figures[0][position.getY()] instanceof Rook && !figures[0][position.getY()].figureMoved) {
            //Weg frei?
            if (isTileEmpty(1, position.getY()) && isTileEmpty(2, position.getY()) && isTileEmpty(3, position.getY())) {
                posMoves.add(new ArrayPoint(0, position.getY(), "blue", Rochade.LONG));
            }
        }

        if (figures[7][position.getY()] instanceof Rook && !figures[7][position.getY()].figureMoved) {
            //Weg frei?
            if (isTileEmpty(5, position.getY()) && isTileEmpty(6, position.getY())) {
                posMoves.add(new ArrayPoint(7, position.getY(), "blue", Rochade.SHORT));
            }
        }
    }

    @Override
    public String toString() {

        return "ki_" + super.toString();

    }

}
