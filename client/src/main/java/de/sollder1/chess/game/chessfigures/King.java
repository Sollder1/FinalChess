package de.sollder1.chess.game.chessfigures;

import java.util.ArrayList;

import de.sollder1.chess.game.gui.view.GameView;
import de.sollder1.chess.game.helpObjects.ArrayPoint;
import de.sollder1.chess.game.helpObjects.Point;
import de.sollder1.chess.game.playground.ChessBoardTile;

public class King extends Figure {
	
	ArrayList<ArrayPoint> possibleCoordinates;
	
	public King(int size, int itemID, Point position, int player) {
		
		super(size, itemID, position, player);
		
		//Outsource to CSS File
	    if(player == 1) {
	    	
	    	setStyle("-fx-background-image: url('/gfx/blackKing.png')");
	    	
	    }else if(player==2){
	    	
	    	setStyle("-fx-background-image: url('/gfx/whiteKing.png')");
	    	
	    }else {
	    	
	    	System.err.println("Wrong Color Input in Figure Super Class");
	    	
	    }
		
	}

	@Override
	public boolean checkIfMoveIsValid(double newx, double newy) {
		
		//Überprüft ob der Weg valide ist, anhand der zuvor in getPossibleCoordinates 
		//bestimmten Liste possibleCoordinates durch vergleich.
		for(int i = 0; i< possibleCoordinates.size(); i++) {
			
			if(possibleCoordinates.get(i).getX() == (int)(newx/size) &&  possibleCoordinates.get(i).getY() == (int)(newy/size)) {
				
				return true;
				
			}
			
		}
		
		
		return false;
	}
	
	public ArrayList<ArrayPoint> getPossibleCoordinates() {
		
		ArrayList <ArrayPoint> posMoves = new ArrayList<>();
		ArrayPoint position = new ArrayPoint((int) (locationBeforeDragDrop.getX()/size) , (int) ((locationBeforeDragDrop.getY()/size))); 
		
		//Mögliche Bewegungen die Performed werden könnten
		ArrayPoint[] potMoves = {new ArrayPoint(0,1), new ArrayPoint(0,-1), new ArrayPoint(1,0), new ArrayPoint(-1,0),
								 new ArrayPoint(1,1), new ArrayPoint(1,-1), new ArrayPoint(-1,1),new ArrayPoint(-1,-1)};
		
		
		//Hinreichendes Kriterium um zu bestimmen ob
		// Bewegungen durchgeführt werden können
		for(int i = 0; i < 8; i++) {
			
			try {
				
				//Wenn Feld leer adden und continue
				if(isTileEmpty(position.getX() + potMoves[i].getX(), position.getY() + potMoves[i].getY())) {
				
					posMoves.add(new ArrayPoint(position.getX() + potMoves[i].getX(), position.getY() + potMoves[i].getY()));
				
					continue;
				
				}
			
				//Wenn Feld Enemy adden und Schleife continuen
				if(isTileEnemy(position.getX() + potMoves[i].getX(), position.getY() + potMoves[i].getY())) {
				
					//Totes Konstrukt um herrauszufinden ob der Zug zum Tod des Königs
					//führen könnte und ihn deswegen nicht zuzulassen.
					if(true) {
						
						posMoves.add(new ArrayPoint(position.getX() + potMoves[i].getX(), position.getY() + potMoves[i].getY(), "red"));
						
					}
				
					continue;
				
				}
				
				//Wenn Feld Friend continue
				if(isTileFriend(position.getX() + potMoves[i].getX(), position.getY() + potMoves[i].getY())) {
				
					continue;
				
				}
				
			}catch(Exception e) {
				
				
				
			}
			
		}
		
		
		
		return posMoves;
		
	
	}

	//Entsprechende Felder markieren
	@Override
	public void markPossibleWays() {
		
		possibleCoordinates = getPossibleCoordinates();
		
		ChessBoardTile tiles [][]= GameView.cb.tiles;
		
		for(ArrayPoint p : possibleCoordinates) {
			
			tiles[p.getX()][p.getY()].setStyle("-fx-background-color: " + p.ColorOfTheTile + "; -fx-border-color: black;");
			
		}

	}

	//Entsprechende Felder demarkieren
	@Override
	public void deMarkPossibleWays() {

		ChessBoardTile tiles [][]= GameView.cb.tiles;
		
		for(ArrayPoint p : possibleCoordinates) {
			
			tiles[p.getX()][p.getY()].setStyle("-fx-background-color: " + tiles[p.getX()][p.getY()].standartColor + "; -fx-border-color: transparent;");
			
		}

	}
	
	public String toString(){
		
		return "ki_" + super.toString();
		
	}

}
