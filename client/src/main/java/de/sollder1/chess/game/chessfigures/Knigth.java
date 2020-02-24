package de.sollder1.chess.game.chessfigures;

import java.util.ArrayList;

import de.sollder1.chess.game.gui.view.GameView;
import de.sollder1.chess.game.helpObjects.ArrayPoint;
import de.sollder1.chess.game.helpObjects.Point;
import de.sollder1.chess.game.playground.ChessBoardTile;

public class Knigth extends Figure {

    ArrayList<ArrayPoint> possibleCoordinates;

    public Knigth(int size, int itemID, Point position, int player) {

        super(size, itemID, position, player);

        if (player == 1) {
            setStyle("-fx-background-image: url('/gfx/blackKnigth.png');");
        } else if (player == 2) {
            setStyle("-fx-background-image: url('/gfx/whiteKnigth.png');");
        }
    }


    public ArrayList<ArrayPoint> getPossibleCoordinates() {

        ArrayList<ArrayPoint> posMoves = new ArrayList<>();
        ArrayPoint position = new ArrayPoint((int) (locationBeforeDragDrop.getX() / size), (int) ((locationBeforeDragDrop.getY() / size)));

        int[] mods1 = {1, -1, 1, -1};
        int[] mods2 = {2, 2, -2, -2};

        //Nach vorne und Hinten schauen
        for (int i = 0; i < 4; i++) {
            try {
                if (!checkForPossibleMove(posMoves, position, mods2, mods1, i)) {
					break;
				}
            } catch (ArrayIndexOutOfBoundsException ignored) {
				//ignored
            }

        }

        //Nach links und rechts schauen
        for (int i = 0; i < 4; i++) {
            try {
				if (!checkForPossibleMove(posMoves, position, mods1, mods2, i)) {
					break;
				}
            } catch (ArrayIndexOutOfBoundsException ignored) {
                //ignored
            }

        }

        return posMoves;

    }

    protected boolean checkForPossibleMove(ArrayList<ArrayPoint> posMoves, ArrayPoint position, int[] mods1, int[] mods2, int i) {

        if (isTileEmpty(position.getX() + mods2[i], position.getY() + mods1[i])) {
            posMoves.add(new ArrayPoint(position.getX() + mods2[i], position.getY() + mods1[i]));
            return true;
        } else if (isTileEnemy(position.getX() + mods2[i], position.getY() + mods1[i])) {
            posMoves.add(new ArrayPoint(position.getX() + mods2[i], position.getY() + mods1[i], "red"));
            return true;
        } else if (isTileFriend(position.getX() + mods2[i], position.getY() + mods1[i])) {
            return true;
        }
        return false;

    }





    @Override
    public String toString() {

        return "kn_" + super.toString();

    }

}
