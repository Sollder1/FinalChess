package de.sollder1.chess.game.chessfigures;

import java.util.ArrayList;
import java.util.List;

import de.sollder1.chess.game.helpObjects.ArrayPoint;
import de.sollder1.chess.game.helpObjects.Point;

public class Knight extends Figure {

    ArrayList<ArrayPoint> possibleCoordinates;

    public Knight(int itemID, ArrayPoint position, int player) {

        super(itemID, position, player);

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
                if (!checkForPossibleMove(posMoves, currentPosition, mods2, mods1, i)) {
					break;
				}
            } catch (ArrayIndexOutOfBoundsException ignored) {
				//ignored
            }

        }

        //Nach links und rechts schauen
        for (int i = 0; i < 4; i++) {
            try {
				if (!checkForPossibleMove(posMoves, currentPosition, mods1, mods2, i)) {
					break;
				}
            } catch (ArrayIndexOutOfBoundsException ignored) {
                //ignored
            }

        }

        return filterCoordinates(posMoves);

    }

    protected boolean checkForPossibleMove(List<ArrayPoint> posMoves, ArrayPoint position, int[] mods1, int[] mods2, int i) {

        ArrayPoint destinationToTry = new ArrayPoint(position.getI() + mods2[i], position.getJ() + mods1[i]);

        if (isTileEmpty(destinationToTry.getI(), destinationToTry.getJ())) {
            posMoves.add(destinationToTry);
            return true;
        } else if (isTileEnemy(destinationToTry.getI(), destinationToTry.getJ())) {
            posMoves.add(new ArrayPoint(destinationToTry.getI(), destinationToTry.getJ(), "red"));
            return true;
        } else return isTileFriend(destinationToTry.getI(), destinationToTry.getJ());

    }





    @Override
    public String toString() {

        return "kn_" + super.toString();

    }

}
