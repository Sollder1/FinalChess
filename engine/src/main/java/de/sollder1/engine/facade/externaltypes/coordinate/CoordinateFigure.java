package de.sollder1.engine.facade.externaltypes.coordinate;

public class CoordinateFigure extends Coordinate{

    private String figureId;

    public CoordinateFigure(int i, int j, String figureId) {
        super(i, j);
        this.figureId = figureId;
    }

    public String getFigureId() {
        return figureId;
    }

    public void setFigureId(String figureId) {
        this.figureId = figureId;
    }
}
