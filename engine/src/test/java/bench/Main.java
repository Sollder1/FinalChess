package bench;

import de.sollder1.engine.facade.externaltypes.FigureCode;
import de.sollder1.engine.facade.externaltypes.coordinate.*;
import de.sollder1.engine.internals.state.FigureId;
import de.sollder1.engine.internals.state.Player;

public class Main {

    public static void main(String[] args) {
        CoordinateFigureTyped c1 = new CoordinateFigureTyped(1, 1, CoordinateType.KILL, new FigureId(0, FigureCode.RO, Player.Number.ONE));
        CoordinateFigureTyped c2 = new CoordinateFigureTyped(1, 1, CoordinateType.MOVE,  new FigureId(0, FigureCode.RO, Player.Number.ONE));

        System.out.println(c1.equals(c2));

    }

}
