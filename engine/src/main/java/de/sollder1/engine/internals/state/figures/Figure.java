package de.sollder1.engine.internals.state.figures;

import de.sollder1.engine.facade.externaltypes.coordinate.Coordinate;
import de.sollder1.engine.facade.externaltypes.coordinate.CoordinateTyped;
import de.sollder1.engine.internals.state.ChessBoard;
import de.sollder1.engine.internals.state.Player;
import java.util.Set;

/**
 * The Concrete Implementations of this Class may offer additional Functionality
 * (i.a King)
 */
public abstract class Figure {

    private Coordinate figurePosition;
    private String figureId;
    private ChessBoard board;
    private Player player;
    //Death is implicit, by just removing the Figure from the Board


    public Figure(Coordinate figurePosition, int pieceId, ChessBoard board, Player player) {
        this.figurePosition = figurePosition;
        this.figureId = "";
        this.board = board;
        this.player = player;
    }

    /**
     * Computes the possible Moves of this Figure by analysing the Board,
     * also limited by the Context supplied threw player and board.
     * @return An Set of Possible Moves for this Figure.
     */
    public abstract Set<CoordinateTyped> getPossibleMoves();

    public Coordinate getFigurePosition() {
        return figurePosition;
    }

    public void setFigurePosition(Coordinate figurePosition) {
        this.figurePosition = figurePosition;
    }

    public String getFigureId() {
        return figureId;
    }

    public ChessBoard getBoard() {
        return board;
    }

    public Player getPlayer() {
        return player;
    }

}
