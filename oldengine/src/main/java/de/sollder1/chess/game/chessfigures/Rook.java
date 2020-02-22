package de.sollder1.chess.game.chessfigures;

import java.util.ArrayList;

import de.sollder1.chess.game.gui.view.GameView;
import de.sollder1.chess.game.helpObjects.Point;
import de.sollder1.chess.game.playground.ChessBoardTile;
import de.sollder1.chess.game.helpObjects.ArrayPoint;

public class Rook extends Figure {
	
	public int itemID;
	ArrayList<ArrayPoint> possibleCoordinates;
	
	
	public Rook(int size, int itemID, Point position, int player) {
		
		super(size, itemID, position, player);
	    
	    //Outsource to CSS File
	    if(player == 1) {
	    	
	    	setStyle("-fx-background-image: url('/gfx/blackRook.png')");
	    	
	    }else if(player==2){
	    	
	    	setStyle("-fx-background-image: url('/gfx/whiteRook.png')");
	    	
	    }else {
	    	
	    	System.err.println("Wrong Color Input in Figure Super Class");
	    	
	    }
		
	}
	
	/*
	 * Neues Konzept zur Validierung des Weges:
	 * 
	 * Zuerst wird beim anklicken der Figur die getPossibleCoordinates Methode gerufen,
	 * welche alle möglichen stellen zurückgibt auf die die Figur gehen kann.
	 * Danach werden genau diese Tiles markiert(Beim bewegen grün, beim schlagen rot)
	 * 
	 * Nachdem dann die Figur losgelassen wurde wird überprüft ob die neue Position
	 * mit einer der ermittelten möglichen Positionen übereinstimmt und demensprechend 
	 * die Figur versetzt oder eben nicht.
	 * 
	 * Zuletzt werden noch die Markierungen der Tiles aufgehoben
	 * 
	 * */

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
		
		//Figure field [][]= Views.cb.figuresOnTheField;
		ArrayList <ArrayPoint> posMoves = new ArrayList<>();
		ArrayPoint position = new ArrayPoint((int) (locationBeforeDragDrop.getX()/size) , (int) ((locationBeforeDragDrop.getY()/size))); 
		
		//Nach Vorne schauen(schwarz)
		for(int i = position.getY()+1; i < 8; i++) {
			
			//Wenn Feld leer adden und continue
			if(isTileEmpty(position.getX(), i)) {
				
				posMoves.add(new ArrayPoint(position.getX(), i));
				
				continue;
				
			}
			
			//Wenn Feld Enemy adden und Schleife beenden(break)
			if(isTileEnemy(position.getX(), i)) {
				
				posMoves.add(new ArrayPoint(position.getX(), i, "red"));
				
				break;
				
			}
			
			//Wenn Feld Friend break
			if(isTileFriend(position.getX(), i)) {
				
				break;
				
			}
			
			
		}
		
		//Nach hinten schauen(schwarz)
		for(int i = position.getY()-1; i >= 0; i--) {
			
			//Wenn Feld leer adden und continue
			if(isTileEmpty(position.getX(), i)) {
				
				posMoves.add(new ArrayPoint(position.getX(), i));
				
				continue;
				
			}
			
			//Wenn Feld Enemy adden und Schleife beenden(break)
			if(isTileEnemy(position.getX(), i)) {
				
				posMoves.add(new ArrayPoint(position.getX(), i, "red"));
				
				break;
				
			}
			
			//Wenn Feld Friend break
			if(isTileFriend(position.getX(), i)) {
				
				break;
				
			}
			
			
		}
		
		//Nach links schauen(schwarz)
		for(int i = position.getX()-1; i >= 0; i--) {
					
			//Wenn Feld leer adden und continue
			if(isTileEmpty(i, position.getY())) {
				
				posMoves.add(new ArrayPoint(i, position.getY()));
						
				continue;
						
			}
					
			//Wenn Feld Enemy adden und Schleife beenden(break)
			if(isTileEnemy(i, position.getY())) {
						
				posMoves.add(new ArrayPoint(i, position.getY(), "red"));
					
				break;
						
			}
					
			//Wenn Feld Friend break
			if(isTileFriend(i, position.getY())) {
					
				break;
						
			}
					
					
		}
		
		//Nach links schauen(schwarz)
		for(int i = position.getX()+1; i < 8; i++) {
							
			//Wenn Feld leer adden und continue
			if(isTileEmpty(i, position.getY())) {
						
				posMoves.add(new ArrayPoint(i, position.getY()));
							
				continue;
								
			}
							
			//Wenn Feld Enemy adden und Schleife beenden(break)
			if(isTileEnemy(i, position.getY())) {
								
				posMoves.add(new ArrayPoint(i, position.getY(), "red"));
							
				break;
								
			}
							
			//Wenn Feld Friend break
			if(isTileFriend(i, position.getY())) {
							
				break;
								
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
		
		return "rk_" + this.player;
		
	}

}
