package de.sollder1.chess.game.chessfigures;

import java.util.ArrayList;

import de.sollder1.chess.game.helpObjects.ArrayPoint;
import de.sollder1.chess.game.helpObjects.Point;
import de.sollder1.chess.game.helpObjects.Rochade;
import de.sollder1.chess.game.playground.ChessBoard;
import de.sollder1.chess.game.playground.ChessBoardTile;

public class King extends Figure {

    public King(int itemID, ArrayPoint position, int player) {

        super(itemID, position, player);

        if (player == 1) {
            getStyleClass().add("lightKing");
        } else if (player == 2) {
            getStyleClass().add("darkKing");
        }
    }

    public ArrayList<ArrayPoint> getPossibleCoordinates() {

        ArrayList<ArrayPoint> posMoves = new ArrayList<>();

        handleRochadeMoves(posMoves, currentPosition);

        //Mögliche Bewegungen die Performed werden könnten
        ArrayPoint[] potMoves = {new ArrayPoint(0, 1), new ArrayPoint(0, -1), new ArrayPoint(1, 0), new ArrayPoint(-1, 0),
                new ArrayPoint(1, 1), new ArrayPoint(1, -1), new ArrayPoint(-1, 1), new ArrayPoint(-1, -1)};


        //Hinreichendes Kriterium um zu bestimmen ob
        // Bewegungen durchgeführt werden können
        for (int i = 0; i < 8; i++) {

            ArrayPoint destinationToTry = new ArrayPoint(currentPosition.getI() + potMoves[i].getI(), currentPosition.getJ() + potMoves[i].getJ());

            if (destinationToTry.getI() < 0 || destinationToTry.getI() > 7
                    || destinationToTry.getJ() < 0 || destinationToTry.getJ() > 7) {

                continue;

            }

            //Wenn Feld leer adden und continue
            if (isTileEmpty(destinationToTry.getI(), destinationToTry.getJ())) {

                posMoves.add(new ArrayPoint(currentPosition.getI() + potMoves[i].getI(), currentPosition.getJ() + potMoves[i].getJ()));

            } else if (isTileEnemy(destinationToTry.getI(), destinationToTry.getJ())) {

                posMoves.add(new ArrayPoint(currentPosition.getI() + potMoves[i].getI(), currentPosition.getJ() + potMoves[i].getJ(), "red"));

            }


        }

        return posMoves;

    }


    private void handleRochadeMoves(ArrayList<ArrayPoint> posMoves, ArrayPoint position) {

        if (figureMoved) {
            return;
        }

        //Rochade Prüfen:
        if (ChessBoard.getFigure(0, position.getJ()) instanceof Rook && !ChessBoard.getFigure(0, position.getJ()).figureMoved) {
            //Weg frei?
            if (isTileEmpty(1, position.getJ()) && isTileEmpty(2, position.getJ()) && isTileEmpty(3, position.getJ())) {
                posMoves.add(new ArrayPoint(0, position.getJ(), "blue", Rochade.LONG));
            }
        }

        if (ChessBoard.getFigure(7, position.getJ()) instanceof Rook && !ChessBoard.getFigure(7, position.getJ()).figureMoved) {
            //Weg frei?
            if (isTileEmpty(5, position.getJ()) && isTileEmpty(6, position.getJ())) {
                posMoves.add(new ArrayPoint(7, position.getJ(), "blue", Rochade.SHORT));
            }
        }
    }

    @Override
    public String toString() {

        return "ki_" + super.toString();

    }

}
