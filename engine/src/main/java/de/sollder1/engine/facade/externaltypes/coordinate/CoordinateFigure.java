package de.sollder1.engine.facade.externaltypes.coordinate;

import de.sollder1.engine.internals.state.pojos.FigureId;

import java.util.Objects;

public class CoordinateFigure extends Coordinate{

    private FigureId figureId;

    public CoordinateFigure(int i, int j, FigureId figureId) {
        super(i, j);
        this.figureId = figureId;
    }

    public FigureId getFigureId() {
        return figureId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        CoordinateFigure that = (CoordinateFigure) o;
        return Objects.equals(figureId, that.figureId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), figureId);
    }
}
