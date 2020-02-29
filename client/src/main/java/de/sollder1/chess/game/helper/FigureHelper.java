package de.sollder1.chess.game.helper;

import de.sollder1.chess.game.gui.GameView;
import de.sollder1.chess.game.helper.ArrayPoint;
import de.sollder1.chess.game.helper.Point;
import de.sollder1.chess.game.uielements.chessboard.ChessBoard;
import de.sollder1.chess.game.uielements.chessfigures.Figure;
import javafx.scene.input.MouseEvent;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Diese Klasse enthält einige statische Merthoden, welche
 * von der Figure Klasse genutzt werden.
 *
 * Diese MEthoden sind Stateless.
 *
 */
public final class FigureHelper {

    private static final int FIGURE_SIZE = Figure.FIGURE_SIZE;
    private FigureHelper(){}

    public static Point computeNormalizedDestination(MouseEvent event) {

        //Computes the Location of the Mouse realive to the Chessboard
        double x = getLocationRelativeToPane(event).getX() + ((double) FIGURE_SIZE / 2);
        double y = getLocationRelativeToPane(event).getY() + ((double) FIGURE_SIZE / 2);

        //Gets the Correct Position on the Field
        double newX = x - (x % FIGURE_SIZE);
        double newY = y - (y % FIGURE_SIZE);

        //Corrects the values of the Placement if they
        //are out of the Borders of the Chessboard
        if (newX < 0) {
            newX = 0;
        }
        if (newY < 0) {
            newY = 0;
        }

        int chessBoardSize = ChessBoard.SIZE;

        if (newX > chessBoardSize - FIGURE_SIZE) {
            newX = (double) (chessBoardSize) - FIGURE_SIZE;
        }
        if (newY > chessBoardSize - FIGURE_SIZE) {
            newY = (double) (chessBoardSize) - FIGURE_SIZE;
        }

        return new Point(newX, newY);

    }

    //TODO: PROJECT SCALABLE!
    public static Point getLocationRelativeToPane(MouseEvent event) {

        double x = event.getSceneX() - ((GameView.getMainFrame().getPrefWidth() - 100) / 2) - FIGURE_SIZE / 2; //TODO: Skalierbar
        double y = event.getSceneY() - ((GameView.getMainFrame().getPrefHeight() - ChessBoard.SIZE) / 2) - FIGURE_SIZE / 2;

        return new Point(x, y);

    }

    public static boolean isTileEmpty(int i, int j) {

        return GameView.board().getFigure(i, j) == null;
    }

    public static boolean isTileEnemy(int i, int j, int figurePlayer) {

        if (isTileEmpty(i, j)) {
            return false;
        }

        return GameView.board().getFigure(i, j).getPlayer() != figurePlayer;

    }

    public static boolean isTileFriend(int i, int j, int figurePlayer) {

        if (isTileEmpty(i, j)) {
            return false;
        }

        return GameView.board().getFigure(i, j).getPlayer() == figurePlayer;

    }

    public static List<ArrayPoint> filterInvalidMoves(List<ArrayPoint> posMoves) {
        return posMoves.stream().filter(move -> move.getI() >= 0 && move.getI() < 8 && move.getJ() >= 0 && move.getJ() < 8)
                .collect(Collectors.toList());
    }

}
