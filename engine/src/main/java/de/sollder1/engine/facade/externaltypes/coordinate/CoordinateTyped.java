package de.sollder1.engine.facade.externaltypes.coordinate;

import java.util.HashSet;
import java.util.Objects;
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

    public void addType(CoordinateType type){
        types.add(type);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        CoordinateTyped that = (CoordinateTyped) o;
        return types.equals(that.types);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), types);
    }
}
