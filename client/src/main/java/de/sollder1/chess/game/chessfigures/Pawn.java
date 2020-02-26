package de.sollder1.chess.game.chessfigures;

import java.util.ArrayList;
import java.util.List;

import de.sollder1.chess.game.helpObjects.ArrayPoint;
import de.sollder1.chess.game.helpObjects.Point;


public class Pawn extends Figure {

    //itemID the ID within the Players figureSet applied to the figure
    //For others lock at the SuperConstructor
    public Pawn(int size, int itemID, Point position, int player) {

        super(itemID, position, player);

        //Outsource to CSS File
		if (player == 1) {
			getStyleClass().add("lightPawn");
		} else if (player == 2) {
			getStyleClass().add("darkPawn");
		}

    }

    @Override
    public List<ArrayPoint> getPossibleCoordinates() {

        return newFF();

    }

    private  List<ArrayPoint> newFF(){

		//mod = 1: Schwarzer Spieler(Spieler 1); mod = 2: Weißer Spieler(Spieler 1)
		int mod = 1;
		if (player == 2) {
			mod = -1;
		}

		ArrayPoint position = new ArrayPoint((int) (locationBeforeDragDrop.getX() / FIGURE_SIZE), (int) (locationBeforeDragDrop.getY() / FIGURE_SIZE));
		ArrayList<ArrayPoint> posMoves = new ArrayList<>();

		if (isTileEmpty(position.getX(), position.getY()+ (1* mod))) {
			posMoves.add(new ArrayPoint(position.getX() , position.getY()+ (1* mod)));
		}

 		if (isTileEmpty(position.getX(), position.getY()+ (2* mod)) && !figureMoved) {
			posMoves.add(new ArrayPoint(position.getX(), position.getY()+ (2* mod)));
		}

 		try {
			if (isTileEnemy(position.getX() + (1* mod), position.getY() + (1* mod))) {

				posMoves.add(new ArrayPoint(position.getX() + (1* mod), position.getY() + (1* mod), "red"));
			}

			if (isTileEnemy(position.getX() + (-1* mod), position.getY() + (1* mod))) {

				posMoves.add(new ArrayPoint(position.getX() + (-1* mod), position.getY() + (1* mod), "red"));
			}

		}catch (IndexOutOfBoundsException ignore){

		}

		return posMoves;

	}



    @Override
    public String toString() {

        return "pw_" + super.toString();

    }


}
