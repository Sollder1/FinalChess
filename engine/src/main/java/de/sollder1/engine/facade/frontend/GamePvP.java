package de.sollder1.engine.facade.frontend;

import de.sollder1.engine.facade.externaltypes.FigureCode;
import de.sollder1.engine.facade.externaltypes.coordinate.CoordinateFigure;
import de.sollder1.engine.internals.state.Player;
import de.sollder1.engine.internals.state.Settings;
import de.sollder1.engine.internals.updates.Update;
public class GamePvP extends Game{

    public GamePvP(Settings settings, Player.Number startingPlayer) {
        super(settings, startingPlayer);
    }

    @Override
    public Update sendMove(CoordinateFigure move) {
        return null;
    }

    @Override
    public Update interchangePawn(String figureId, FigureCode interChange) {
        return null;
    }
}
