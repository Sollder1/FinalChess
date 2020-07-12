package bench;

import de.sollder1.engine.facade.EngineFrontend;
import de.sollder1.engine.facade.exceptions.NoSuchFigureIdRegisteredException;
import de.sollder1.engine.facade.externaltypes.FigureCode;
import de.sollder1.engine.facade.externaltypes.coordinate.*;
import de.sollder1.engine.internals.state.pojos.FigureId;
import de.sollder1.engine.internals.state.pojos.Player;

import java.util.Set;

public class Main {

    public static void main(String[] args) throws NoSuchFigureIdRegisteredException {

        Set<CoordinateFigureTyped> moves = EngineFrontend.getGamePvP(Player.Number.ONE).getPossibleMovesForFigure(new FigureId(0, FigureCode.BI, Player.Number.ONE));
        print(moves);

        moves.forEach(System.out::println);

    }

    private static void print(Set<CoordinateFigureTyped> moves) {

        for (int j = 0; j < 8; j++) {
            I:
            for (int i = 0; i < 8; i++) {
                for (CoordinateFigureTyped move: moves) {
                    if (move.getJ() == j && move.getI() == i){
                        System.out.print(" " + move.getFigureId() + " ");
                        continue I;
                    }
                }
                System.out.print(" _[leer]_ ");
            }
            System.out.println();
        }

        System.out.println();
    }

}
