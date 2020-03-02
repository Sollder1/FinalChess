package de.sollder1.engine.internals.state;

import de.sollder1.engine.facade.externaltypes.coordinate.Coordinate;

import java.util.Map;
import java.util.Set;

public class Player {

    private Number playerNumber;
    private Map<FigureId, Set<Coordinate>> invalidMoves;


    public Player(Number playerNumber) {
        this.playerNumber = playerNumber;
    }

    public enum Number{
        ONE, TWO
    }

    public Number getPlayerNumber() {
        return playerNumber;
    }

    /**
     * This underlying Map will be updated by the surrounding chessboard
     * each time a successful Move and thereby an Change of the User Context
     * is performed. It guaranties that each Figure may follow the Restrictions
     * set by the bigger Picture of the Chessfield only known to the Chessboard Object.
     * Therefor it is a way to for Example restrict movement of the player whose
     * King is matt to the King itself und other Moves who bring him out of the Matt.
     *
     * @param key
     * @return Returns an Set of Move Restrictions for the Given FigureId.
     */
    public Set<Coordinate> getInvalidMovesForFigure(FigureId key) {
        return invalidMoves.get(key);
    }

    public void setInvalidMovesForFigure(FigureId key, Set<Coordinate> invalidMovesForFigure) {
        invalidMoves.replace(key, invalidMovesForFigure);
    }

    public void setInvalidMoves(Map<FigureId, Set<Coordinate>> invalidMoves) {
        this.invalidMoves = invalidMoves;
    }
}
