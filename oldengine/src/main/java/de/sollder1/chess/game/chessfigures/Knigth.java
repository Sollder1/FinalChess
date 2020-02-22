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
		
		//Outsource to CSS File
	    if(player == 1) {
	    	
	    	setStyle("-fx-background-image: url('/gfx/blackKnigth.png')");
	    	
	    }else if(player==2){
	    	
	    	setStyle("-fx-background-image: url('/gfx/whiteKnigth.png')");
	    	
	    }else {
	    	
	    	System.err.println("Wrong Color Input in Figure Super Class");
	    	
	    }
	}

	@Override
	public boolean checkIfMoveIsValid(double newx, double newy) {
		
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
		
		//int mod = 1;
		
		int mods1 [] = {1, -1, 1, -1};
		int mods2 [] = {2, 2, -2, -2};
		
		
		//Nach vorne und Hinten schauen
		for(int i = 0; i<4; i++) {
			
			//Um nervige outofBounds exceotions abzuwehren, da diese nur auftreten
			//wenn kein Weg verfügbar ist -> Irrelevant.
			try {
				
				if(isTileEmpty(position.getX() + mods1[i], position.getY() + mods2[i])) {
				
					posMoves.add(new ArrayPoint(position.getX() + mods1[i], position.getY() + mods2[i]));
				
					continue;
				
				}
			
				if(isTileEnemy(position.getX() + mods1[i], position.getY() + mods2[i])) {
				
					posMoves.add(new ArrayPoint(position.getX() + mods1[i], position.getY() + mods2[i], "red"));
				
					continue;
				
				}
			
				if(isTileFriend(position.getX() + mods1[i], position.getY() + mods2[i])) {
					
					continue;
				
				}
			
			}catch(Exception e) {
				
				
				
			}
			
		}
		
		
		//Nach links und rechts schauen
		for(int i = 0; i<4; i++) {
			
			//Um nervige outofBounds exceotions abzuwehren, da diese nur auftreten
			//wenn kein Weg verfügbar ist -> Irrelevant.
			try { 
				if(isTileEmpty(position.getX() + mods2[i], position.getY() + mods1[i])) {
				
					posMoves.add(new ArrayPoint(position.getX() + mods2[i], position.getY() + mods1[i]));
				
					continue;
				
				}
			
				if(isTileEnemy(position.getX() + mods2[i], position.getY() + mods1[i])) {
				
					posMoves.add(new ArrayPoint(position.getX() + mods2[i], position.getY() + mods1[i], "red"));
				
					continue;
				
				}
			
				if(isTileFriend(position.getX() + mods2[i], position.getY() + mods1[i])) {
					
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
		
		return "kn_" + this.player;
		
	}

}
