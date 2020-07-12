package de.sollder1.engine.internals.state.figures;

import de.sollder1.engine.facade.externaltypes.FigureCode;
import de.sollder1.engine.facade.externaltypes.coordinate.Coordinate;
import de.sollder1.engine.facade.externaltypes.coordinate.CoordinateFigureTyped;
import de.sollder1.engine.facade.externaltypes.coordinate.CoordinateType;
import de.sollder1.engine.internals.state.ChessBoard;
import de.sollder1.engine.internals.state.pojos.Player;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

public class Bishop extends Figure {

    public Bishop(Coordinate figurePosition, int pieceId, ChessBoard board, Player player) {
        super(figurePosition, pieceId, board, player);
    }

    Figure.MoveSearchState currentState = MoveSearchState.FREE;

    @Override
    public Set<CoordinateFigureTyped> getPossibleMoves() {

        Set<CoordinateFigureTyped> posMoves = new HashSet<>();

        //Nach unten rechts
        for (int x = position.getI() + 1, y = position.getJ() + 1; x < 8 && y < 8; x++, y++) {
            currentState = checkForPossibleMove(posMoves, x, y);
        }

        currentState = MoveSearchState.FREE;
        //Nach unten links
        for (int x = position.getI() - 1, y = position.getJ() + 1; x >= 0 && y < 8; x--, y++) {
            currentState = checkForPossibleMove(posMoves, x, y);
        }

        currentState = MoveSearchState.FREE;
        //Nach oben rechts
        for (int x = position.getI() + 1, y = position.getJ() - 1; x < 8 && y >= 0; x++, y--) {
            currentState = checkForPossibleMove(posMoves, x, y);
        }

        currentState = MoveSearchState.FREE;
        //Nach oben links
        for (int x = position.getI() - 1, y = position.getJ() - 1; x >= 0 && y >= 0; x--, y--) {
            currentState = checkForPossibleMove(posMoves, x, y);
        }

        return this.filterInvalidMoves(posMoves);

    }

    /*Adds an possible move to the Lsit and return true if
	it is still useful to iterate on.*/
    protected Figure.MoveSearchState checkForPossibleMove(Set<CoordinateFigureTyped> posMoves, int i, int j) {

        Coordinate toCheck = new Coordinate(i, j);
        Optional<Player> occupation = board.getTileOccupation(toCheck);

        if(occupation.isPresent()) {
            if(occupation.get() == this.player){
                posMoves.add(new CoordinateFigureTyped(i, j, CoordinateType.TRANCIENT, getFigureId()));
            }else {
                Set<CoordinateType> types = new HashSet<>();
                if(currentState == MoveSearchState.TRANCIENT){
                    types.add(CoordinateType.KILL);
                }else {
                    types.add(CoordinateType.KILL);
                    types.add(CoordinateType.TRANCIENT);
                }
                posMoves.add(new CoordinateFigureTyped(i, j, types, getFigureId()));
            }
            return MoveSearchState.TRANCIENT;
        }else {
            Set<CoordinateType> types = new HashSet<>();
            if(currentState == MoveSearchState.TRANCIENT){
                types.add(CoordinateType.TRANCIENT);
                posMoves.add(new CoordinateFigureTyped(i, j, types, getFigureId()));
                return MoveSearchState.TRANCIENT;
            }else {
                types.add(CoordinateType.MOVE);
                posMoves.add(new CoordinateFigureTyped(i, j, types, getFigureId()));
                return MoveSearchState.FREE;
            }
        }

    }

    @Override
    public FigureCode getFigureCode() {
        return FigureCode.BI;
    }

}
