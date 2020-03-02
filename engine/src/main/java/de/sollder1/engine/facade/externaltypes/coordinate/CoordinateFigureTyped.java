package de.sollder1.engine.facade.externaltypes.coordinate;

import de.sollder1.engine.internals.state.FigureId;
import de.sollder1.engine.internals.state.figures.Figure;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class CoordinateFigureTyped extends CoordinateTyped{

    FigureId figureId;

    public CoordinateFigureTyped(int i, int j, CoordinateType type, FigureId figureId) {
        super(i, j, type);
        this.figureId = figureId;
    }

    public CoordinateFigureTyped(int i, int j, Set<CoordinateType> types, FigureId figureId) {
        super(i, j, types);
        this.figureId = figureId;
    }

    public static Set<CoordinateFigureTyped> mapFigureToTypedCoordinates(Set<CoordinateTyped> typedCoordinates, Figure figure){
        Set<CoordinateFigureTyped> mapped = new HashSet<>();
        typedCoordinates.forEach(coordinateTyped -> {
                    mapped.add(new CoordinateFigureTyped(coordinateTyped.getI(), coordinateTyped.getJ(),
                            coordinateTyped.getTypes(), figure.getFigureId()));
                });

        return mapped;
    }

    public FigureId getFigureId() {
        return figureId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        CoordinateFigureTyped that = (CoordinateFigureTyped) o;
        return figureId.equals(that.figureId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), figureId);
    }
}
