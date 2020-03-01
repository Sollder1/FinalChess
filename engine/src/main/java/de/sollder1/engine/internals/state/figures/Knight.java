package de.sollder1.engine.internals.state.figures;

import de.sollder1.engine.facade.externaltypes.coordinate.Coordinate;
import de.sollder1.engine.facade.externaltypes.coordinate.CoordinateTyped;
import de.sollder1.engine.internals.state.ChessBoard;
import de.sollder1.engine.internals.state.Player;

import java.util.Set;

public class Knight extends Figure {


    public Knight(Coordinate figurePosition, int pieceId, ChessBoard board, Player player) {
        super(figurePosition, pieceId, board, player);
    }

    @Override
    public Set<CoordinateTyped> getPossibleMoves() {
        return null;
    }
}
