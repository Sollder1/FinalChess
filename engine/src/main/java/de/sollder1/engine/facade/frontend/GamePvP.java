package de.sollder1.engine.facade.frontend;

import de.sollder1.engine.facade.externaltypes.coordinate.CoordinateFigure;
import de.sollder1.engine.internals.state.Settings;
import de.sollder1.engine.internals.updates.Update;
import de.sollder1.engine.facade.externaltypes.coordinate.FigureCode;

public class GamePvP extends Game{

    public GamePvP(Settings settings, int startingPlayer) {
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
