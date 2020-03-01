package de.sollder1.engine.facade.externaltypes.coordinate;

import java.util.HashSet;
import java.util.Set;

public class CoordinateTyped extends Coordinate{

    private Set<CoordinateType> types;

    public CoordinateTyped(int i, int j, CoordinateType type) {
        super(i, j);
        this.types = new HashSet<>();
        this.types.add(type);
    }

    public CoordinateTyped(int i, int j, Set<CoordinateType> types) {
        super(i, j);
        this.types = types;
    }

    public Set<CoordinateType> getTypes() {
        return types;
    }
}
