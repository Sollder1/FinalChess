package de.sollder1.engine.internals.state.figures;

import de.sollder1.engine.facade.externaltypes.FigureCode;
import de.sollder1.engine.facade.externaltypes.coordinate.Coordinate;
import de.sollder1.engine.facade.externaltypes.coordinate.CoordinateFigureTyped;
import de.sollder1.engine.internals.state.ChessBoard;
import de.sollder1.engine.internals.state.pojos.FigureId;
import de.sollder1.engine.internals.state.pojos.Player;

import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * The Concrete Implementations of this Class may offer additional Functionality
 * (i.a King)
 */
public abstract class Figure {

    protected Coordinate position;
    protected FigureId figureId;
    protected ChessBoard board;
    protected Player player;
    //Death is implicit, by just removing the Figure from the Board

    public Figure(Coordinate position, int pieceId, ChessBoard board, Player player) {
        this.position = position;
        this.figureId = new FigureId(pieceId, getFigureCode(), player.getPlayerNumber());
        this.board = board;
        this.player = player;
    }

    /**
     * Computes the possible Moves of this Figure by analysing the Board,
     * also limited by the Context supplied threw player and board.
     *
     * @return An Set of Possible Moves for this Figure.
     */
    public abstract Set<CoordinateFigureTyped> getPossibleMoves();

    public abstract FigureCode getFigureCode();

    public Coordinate getPosition() {
        return position;
    }

    public void setPosition(Coordinate position) {
        this.position = position;
    }

    public FigureId getFigureId() {
        return figureId;
    }

    public ChessBoard getBoard() {
        return board;
    }

    public Player getPlayer() {
        return player;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Figure figure = (Figure) o;
        return figureId.equals(figure.figureId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(figureId);
    }

    /**
     * Filters all the Moves, whose fulfill one of the following : <br>
     * 1. The Indices are out of bound <br>
     * 2. The Move would violate the external conditions set by the invalidMoves Map in
     * the corresponding Player class to this Object. <br>
     * 3. The Move would directly endanger the own King. <br>
     * <p>
     * Therefore the resulting Moves of this Method shall be save to use.
     *
     * @param posMoves The Moves to Filter
     * @return the filtered Moves
     */
    protected Set<CoordinateFigureTyped> filterInvalidMoves(Set<CoordinateFigureTyped> posMoves) {
        Set<Coordinate> invalidMoves = player.getInvalidMovesForFigure(getFigureId());

        return posMoves.stream().filter(move -> invalidMoves.stream()
                .anyMatch(invalidMove -> move.getI() == invalidMove.getI() && move.getJ() == invalidMove.getJ()))
                .collect(Collectors.toSet());
    }

    protected enum MoveSearchState {
        FREE, TRANCIENT
    }

}
