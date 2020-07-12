package de.sollder1.engine.internals.state.figures;

import de.sollder1.engine.facade.externaltypes.FigureCode;
import de.sollder1.engine.facade.externaltypes.coordinate.Coordinate;
import de.sollder1.engine.facade.externaltypes.coordinate.CoordinateFigureTyped;
import de.sollder1.engine.internals.state.ChessBoard;
import de.sollder1.engine.internals.state.pojos.Player;

import java.util.Set;

public class Knight extends Figure {

    public Knight(Coordinate figurePosition, int pieceId, ChessBoard board, Player player) {
        super(figurePosition, pieceId, board, player);
    }

    @Override
    public Set<CoordinateFigureTyped> getPossibleMoves() {
        return null;
    }

    @Override
    public FigureCode getFigureCode() {
        return FigureCode.KN;
    }
}
