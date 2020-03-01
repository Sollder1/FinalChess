package de.sollder1.engine.facade.externaltypes.coordinate;

import java.util.Set;

public class CoordinateFigureTyped extends CoordinateTyped{

    String figureId;

    public CoordinateFigureTyped(int i, int j, CoordinateType type, String figureId) {
        super(i, j, type);
        this.figureId = figureId;
    }

    public CoordinateFigureTyped(int i, int j, Set<CoordinateType> types, String figureId) {
        super(i, j, types);
        this.figureId = figureId;
    }

    public String getFigureId() {
        return figureId;
    }
}
