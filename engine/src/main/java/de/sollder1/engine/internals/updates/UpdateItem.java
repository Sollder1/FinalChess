package de.sollder1.engine.internals.updates;

import de.sollder1.engine.facade.externaltypes.coordinate.CoordinateFigure;

public class UpdateItem {

    private CoordinateFigure move;
    private UpdateType type;

    public UpdateItem(CoordinateFigure move, UpdateType type) {
        this.move = move;
        this.type = type;
    }

    public CoordinateFigure getMove() {
        return move;
    }

    public UpdateType getType() {
        return type;
    }
}
