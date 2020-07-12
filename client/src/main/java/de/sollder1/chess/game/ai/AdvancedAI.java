package de.sollder1.chess.game.ai;

import de.sollder1.chess.game.helper.ArrayPoint;
import de.sollder1.chess.game.uielements.chessboard.ChessBoard;
import de.sollder1.chess.game.uielements.chessfigures.Figure;

import java.util.ArrayList;
import java.util.List;

/**
 * Funktioniert so nicht ganz, benötig seine eigne Kopie des Schachbretts um darauf tatsächliche Operationen auszuführen.
 * Das beste wäre es wenn eine strikte Trennung zwischen UI und Engine da wäre.
 *
 * Für Paraleliersung wäre es auch nötig mehrere Kopie des Schachbretts zu haben, für jeden Thread eine.
 */
public class AdvancedAI extends AI {

    private int depth;
    private int player;

    private FigureMoveRelation bestMove;

    public AdvancedAI(int depth) {
        this.depth = depth;

    }

    @Override
    protected void performMove(int player, ChessBoard currentState) {

        miniMax(player, depth, new ChessBoard(currentState.getUiFigures()));

        bestMove.getFigure().moveFigure(bestMove.getMove());
    }

    //TODO: Paralleisierung, dazu müssten allerdings für jeden der ersten Züge eine Tiefe Kopie des Chessboard erstellt werden, die auch funktioniert!

    int miniMax(int currentPlayer, int currentDepth, ChessBoard currentState) {

        List<FigureMoveRelation> possibleMoves = getPossibleMoves(currentState.getUiFigures(), currentPlayer);
        //TODO: BEstimmen ob der König bewegt werden muss.

        if (currentDepth == 0 || possibleMoves.isEmpty()) {
            return evaluateEndSituation(currentState.getUiFigures(), player);
        }

        FigureMoveRelation bestMove = null;
        int bestValue = Integer.MIN_VALUE;

        for (FigureMoveRelation possibleMove : possibleMoves) {

            int moveEvaluation = evaluateMove(currentState.getUiFigures(), possibleMove, player);
            ChessBoard stateCopy = new ChessBoard(currentState.getUiFigures());

            //Execute Move:
            stateCopy.getFigure(possibleMove.getFigure().getPosition()).moveFigure(possibleMove.getMove());

            int value = (currentPlayer == player ? miniMax(currentPlayer, currentDepth - 1, stateCopy)
                    : miniMax(currentPlayer, currentDepth - 1, stateCopy) * -1) + moveEvaluation;

            if(value > bestValue){
                bestValue = value;
                bestMove = possibleMove;
            }

        }

        //In der Obersten Ebenen, wenn alle BErehcnungen vollendet sind
        //Muss der Move nur noch gesetzt werden.
        if (currentDepth == depth) {
            setBestMove(bestMove);
        }

        return bestValue;

    }

    private void setBestMove(FigureMoveRelation bestMove) {
        this.bestMove = bestMove;
    }

    private int evaluateEndSituation(List<Figure> currentState, int player) {

        /*Mögliche Metriken:
            1. wie viele Figuren wurden verloren bzw. so gar welche und diesen dann einen Wert zumessen.
            2. Wie viele Figuren hat der Gegner verloren bzw. so gar welche und diesen dann einen Wert zumessen.
            3. 
         */

        return 0;
    }

    private int evaluateMove(List<Figure> currentState, FigureMoveRelation move, int player) {
        return 0;
    }


    private List<FigureMoveRelation> getPossibleMoves(List<Figure> currentState, int player) {
        List<FigureMoveRelation> possibleMoves = new ArrayList<>();
        currentState.stream().filter(figure -> figure.getPlayer() == player)
                .forEach(figure -> figure.getPossibleCoordinates().forEach(move -> possibleMoves.add(new FigureMoveRelation(move, figure))));

        return possibleMoves;
    }

    private class FigureMoveRelation {
        ArrayPoint move;
        Figure figure;

        public FigureMoveRelation(ArrayPoint move, Figure figure) {
            this.move = move;
            this.figure = figure;
        }

        public ArrayPoint getMove() {
            return move;
        }

        public void setMove(ArrayPoint move) {
            this.move = move;
        }

        public Figure getFigure() {
            return figure;
        }

        public void setFigure(Figure figure) {
            this.figure = figure;
        }
    }
}
