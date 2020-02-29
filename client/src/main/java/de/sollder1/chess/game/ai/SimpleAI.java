package de.sollder1.chess.game.ai;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.atomic.AtomicBoolean;

import de.sollder1.chess.game.uielements.chessboard.ChessBoard;
import de.sollder1.chess.game.uielements.chessfigures.Figure;
import de.sollder1.chess.game.helper.ArrayPoint;

public class SimpleAI extends AI {

    Random random = new Random();

    @Override
    public void performMove(int player, ChessBoard currentState) {

        List<FigureMoveRelation> possibleMoves = new ArrayList<>();


        currentState.getUiFigures().stream().filter(figure -> figure.getPlayer() == player)
                .forEach(figure -> figure.getPossibleCoordinates().forEach(move -> possibleMoves.add(new FigureMoveRelation(move, figure))));

        AtomicBoolean couldKill = new AtomicBoolean(false);

        //präfereriere Züge, die schlagen
        possibleMoves.stream().filter(move -> move.getMove().getColorOfTheTile().equals("red")).findAny().ifPresent(figureMoveRelation -> {
            if (!couldKill.get()) {
                figureMoveRelation.getFigure().moveFigure(figureMoveRelation.getMove());
                couldKill.getAndSet(true);
            }
        });

        if(!couldKill.get()){
			FigureMoveRelation relation = possibleMoves.get(random.nextInt(possibleMoves.size()));
			relation.getFigure().moveFigure(relation.getMove());
		}

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
