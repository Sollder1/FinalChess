package de.sollder1.chess.game.chessfigures;

import java.util.ArrayList;
import java.util.List;

import de.sollder1.chess.game.helpObjects.ArrayPoint;
import de.sollder1.chess.game.helpObjects.Point;
import de.sollder1.chess.game.playground.ChessBoard;
import de.sollder1.chess.game.playground.ChessBoardTile;


//TODO: Wegfindung nach neuem Konzept aufziehen.
//Damit können die anderen Methoden in die abstrakte Figure Klasse wandern

public class Pawn extends Figure {

    public boolean firstMove = true;

    //itemID the ID within the Players figureSet applied to the figure
    //For others lock at the SuperConstructor
    public Pawn(int size, int itemID, Point position, int player) {

        super(size, itemID, position, player);

        //Outsource to CSS File
        if (player == 1) {
            setStyle("-fx-background-image: url('/gfx/blackPawn.png');");
        } else if (player == 2) {
            setStyle("-fx-background-image: url('/gfx/whitePawn.png');");
        }

    }

    @Override
    public List<ArrayPoint> getPossibleCoordinates() {

        return newFF();

    }

    @Override
	protected void afterSuccessFullMoveAction(){
		firstMove = false;
	}

    private  List<ArrayPoint> newFF(){

		//mod = 1: Schwarzer Spieler(Spieler 1); mod = 2: Weißer Spieler(Spieler 1)
		int mod = 1;
		if (player == 2) {
			mod = -1;
		}

		ArrayPoint position = new ArrayPoint((int) (locationBeforeDragDrop.getX() / size), (int) (locationBeforeDragDrop.getY() / size));
		ArrayList<ArrayPoint> posMoves = new ArrayList<>();

		if (isTileEmpty(position.getX(), position.getY()+ (1* mod))) {
			posMoves.add(new ArrayPoint(position.getX() , position.getY()+ (1* mod)));
		}

 		if (isTileEmpty(position.getX(), position.getY()+ (2* mod)) && firstMove) {
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
