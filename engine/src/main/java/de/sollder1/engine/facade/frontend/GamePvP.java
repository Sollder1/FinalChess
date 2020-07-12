package de.sollder1.engine.facade.frontend;

import de.sollder1.engine.facade.externaltypes.FigureCode;
import de.sollder1.engine.facade.externaltypes.coordinate.CoordinateFigureTyped;
import de.sollder1.engine.internals.state.pojos.Player;
import de.sollder1.engine.internals.state.pojos.Settings;
import de.sollder1.engine.internals.updates.Update;

public class GamePvP extends Game{

    public GamePvP(Settings settings, Player.Number startingPlayer) {
        super(settings, startingPlayer);
    }

    @Override
    public Update sendMove(CoordinateFigureTyped move) {
        return chessBoard.moveFigure(move);
    }

    @Override
    public Update interchangePawn(String figureId, FigureCode interChange) {
        return null;
    }
}
