package de.sollder1.chess.game.chessfigures;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import de.sollder1.chess.game.helpObjects.ArrayPoint;
import de.sollder1.chess.game.helpObjects.Point;
import de.sollder1.chess.game.helpObjects.Rochade;
import de.sollder1.chess.game.helpObjects.Utils;
import de.sollder1.chess.game.playground.ChessBoard;
import de.sollder1.chess.game.playground.ChessBoardTile;

public class King extends Figure {


    private boolean mustMove;

    public King(int itemID, ArrayPoint position, int player) {

        super(itemID, position, player);

        if (player == 1) {
            getStyleClass().add("lightKing");
        } else if (player == 2) {
            getStyleClass().add("darkKing");
        }
    }

    public List<ArrayPoint> getPossibleCoordinates() {

        return filterCriticalMoves(getPossibleCoordinatesWithoutFilter());

    }


    private void handleRochadeMoves(List<ArrayPoint> posMoves, ArrayPoint position) {

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

    public List<ArrayPoint> getPossibleCoordinatesWithoutFilter(){

        List<ArrayPoint> posMoves = new ArrayList<>();

        handleRochadeMoves(posMoves, currentPosition);

        //Mögliche Bewegungen die Performed werden könnten
        ArrayPoint[] potMoves = {new ArrayPoint(0, 1), new ArrayPoint(0, -1), new ArrayPoint(1, 0), new ArrayPoint(-1, 0),
                new ArrayPoint(1, 1), new ArrayPoint(1, -1), new ArrayPoint(-1, 1), new ArrayPoint(-1, -1)};


        //Hinreichendes Kriterium um zu bestimmen ob
        // Bewegungen durchgeführt werden können
        for (int i = 0; i < 8; i++) {

            ArrayPoint destinationToTry = new ArrayPoint(currentPosition.getI() + potMoves[i].getI(), currentPosition.getJ() + potMoves[i].getJ());

            //Wenn Feld leer adden und continue
            if (isTileEmpty(destinationToTry.getI(), destinationToTry.getJ())) {

                posMoves.add(new ArrayPoint(currentPosition.getI() + potMoves[i].getI(), currentPosition.getJ() + potMoves[i].getJ()));

            } else if (isTileEnemy(destinationToTry.getI(), destinationToTry.getJ())) {

                posMoves.add(new ArrayPoint(currentPosition.getI() + potMoves[i].getI(), currentPosition.getJ() + potMoves[i].getJ(), "red"));

            }

        }

        return filterCoordinates(posMoves);

    }

    public List<ArrayPoint> filterCriticalMoves(List<ArrayPoint> posMoves) {
        List<ArrayPoint> k = ChessBoard.getCriticalPaths(ChessBoard.getEnemyPaths(this.player, true), posMoves);
        return posMoves.stream().filter(move -> {
            for (ArrayPoint criticalMove : k) {
                if (criticalMove.equals(move)){
                    return false;
                }
            }
            return true;
        }).collect(Collectors.toList());
    }

    public void mustMoveNextTurn(){
        mustMove = true;
    }

    public boolean isMustMove() {
        return mustMove;
    }

    @Override
    protected void afterSuccessFullMoveAction() {
        super.afterSuccessFullMoveAction();
        mustMove = false;
    }

    @Override
    public String toString() {

        return "ki_" + super.toString();

    }

}
