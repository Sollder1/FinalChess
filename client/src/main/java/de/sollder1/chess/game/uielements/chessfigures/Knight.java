package de.sollder1.chess.game.uielements.chessfigures;

import java.util.ArrayList;
import java.util.List;

import de.sollder1.chess.game.helper.ArrayPoint;
import de.sollder1.chess.game.helper.FigureHelper;

public class Knight extends Figure {

    ArrayList<ArrayPoint> possibleCoordinates;

    public Knight(ArrayPoint position, int player) {

        super(position, player);

        if (player == 1) {
            getStyleClass().add("lightKnight");
        } else if (player == 2) {
            getStyleClass().add("darkKnight");
        }
    }


    public List<ArrayPoint> getPossibleCoordinates() {

        List<ArrayPoint> posMoves = new ArrayList<>();

        int[] mods1 = {1, -1, 1, -1};
        int[] mods2 = {2, 2, -2, -2};

        //Nach vorne und Hinten schauen
        for (int i = 0; i < 4; i++) {
            try {
                if (!checkForPossibleMoveKnight(posMoves, position, mods2, mods1, i)) {
					break;
				}
            } catch (ArrayIndexOutOfBoundsException ignored) {
				//ignored
            }

        }

        //Nach links und rechts schauen
        for (int i = 0; i < 4; i++) {
            try {
				if (!checkForPossibleMoveKnight(posMoves, position, mods1, mods2, i)) {
					break;
				}
            } catch (ArrayIndexOutOfBoundsException ignored) {
                //ignored
            }

        }

        return FigureHelper.filterInvalidMoves(posMoves);

    }

    private boolean checkForPossibleMoveKnight(List<ArrayPoint> posMoves, ArrayPoint position, int[] mods1, int[] mods2, int i) {

        ArrayPoint destinationToTry = new ArrayPoint(position.getI() + mods2[i], position.getJ() + mods1[i]);

        if (FigureHelper.isTileEmpty(destinationToTry.getI(), destinationToTry.getJ())) {
            posMoves.add(destinationToTry);
            return true;
        } else if (FigureHelper.isTileEnemy(destinationToTry.getI(), destinationToTry.getJ(), player)) {
            posMoves.add(new ArrayPoint(destinationToTry.getI(), destinationToTry.getJ(), "red"));
            return true;
        } else return FigureHelper.isTileFriend(destinationToTry.getI(), destinationToTry.getJ(), player );

    }

    @Override
    public String toString() {

        return "kn_" + super.toString();

    }

}
