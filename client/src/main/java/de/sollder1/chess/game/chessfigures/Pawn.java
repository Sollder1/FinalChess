package de.sollder1.chess.game.chessfigures;

import java.util.ArrayList;

import de.sollder1.chess.game.gui.view.GameView;
import de.sollder1.chess.game.helpObjects.ArrayPoint;
import de.sollder1.chess.game.helpObjects.Point;
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
	    if(player == 1) {
	    	
	    	setStyle("-fx-background-image: url('/gfx/blackPawn.png')");
	    	
	    }else if(player==2){
	    	
	    	setStyle("-fx-background-image: url('/gfx/whitePawn.png')");
	    	
	    }else {
	    	
	    	System.err.println("Wrong Color Input in Figure Super Class");
	    	
	    }
	    
	}

	@Override
	public boolean checkIfMoveIsValid(double newx, double newy) {

		//Hole die instanz figuresOnTheField
		Figure field [][]= GameView.cb.figuresOnTheField;
		
		Point nAP = new Point((int) (newx/size) , (int) (newy/size)); //nAP: New Array Position
		Point oAP = new Point((int) (locationBeforeDragDrop.getX()/size) , (int) (locationBeforeDragDrop.getY()/size)); //oAP: oldArrayPosition
		
		//mod = 1: Schwarzer Spieler(Spieler 1); mod = 2: Weißer Spieler(Spieler 1)
		int mod = 1; 
		if(player == 2) {mod = -1;}
		
		//Eins nach vorne:
		//Schaut zuerst ob X-Koordinate gleich geblieben ist
		//Dann ob Y bloß um eins nach Vorne Bewegt wurde, wobei Mod den Spieler indiziert
		//Als letztes Wird noc hgeschaut ob dass Feld vor dem Char frei ist
		//Dann und nur dann ist der Move valid.
		if(nAP.getX() == oAP.getX() && nAP.getY() == oAP.getY() + (1 * mod) && (field[(int) nAP.getX()][(int) (nAP.getY())] == null)) {
			
			firstMove = false;
			return true;
			
		}
		
		//Zwei nach vorne beim ersten Zug:
		//Schaut zuerst ob X-Koordinate gleich geblieben ist
		//Dann ob Y bloß um eins nach Vorne Bewegt wurde, wobei Mod den Spieler indiziert
		//Es wird geschut ob es sich um den ersten Move handelt
		//Als letztes Wird noch hgeschaut ob dass Feld vor dem Char frei ist
		//Dann und nur dann ist der Move valid.
		if(nAP.getX() == oAP.getX() && nAP.getY() == oAP.getY() + (2 * mod) && firstMove && (field[(int) nAP.getX()][(int) (nAP.getY())] == null)) {
			
			firstMove = false;
			return true;
			
		}
		
		
		//Links oder rechts vor ihm schlagen:
		//Schaut zuerst ob y um 1 oder -1 verändert wurde(je nach player-Value des Pawns)
		//Dann schaut es ob X-Koordinate +/- 1 ist 
		//Schließlich wird noch geschaut ob auf der neuen Position auch wirklich ein gegner steht
		if(nAP.getY() == oAP.getY() + (1 * mod) && (nAP.getX() == oAP.getX() + 1 || nAP.getX() == oAP.getX() - 1) && !(field[(int) nAP.getX()][(int) nAP.getY()] == null)) {
			
				firstMove = false;
				return true;
				
			}
		
		return false;
	}

	@Override
	public void markPossibleWays() {
		
		ChessBoardTile tiles [][]= GameView.cb.tiles;
		
		int mod = 1; 
		if(player == 2) {mod=-1;}
		
		int x = (int) (locationBeforeDragDrop.getX()/size);
		int y = (int) (locationBeforeDragDrop.getY()/size);
		
		
		//Try Catch um Akzeptabeler Overflows abzufangen
		try {
			
			if(isTileEmpty(x, y + (1*mod))) {
				
				tiles[x][y + (1*mod)].setStyle("-fx-background-color: Green; -fx-border-color: black;");
				
			}
			
			if(firstMove && isTileEmpty(x, y + (2*mod))) {
				
				tiles[x][y + (2*mod)].setStyle("-fx-background-color: Green; -fx-border-color: black;");
				
			}
			
			//Nach Links oder Recht schlagen
			if(isTileEnemy(x + 1, y + (1*mod))) {
				
				tiles[x + 1][y + (1*mod)].setStyle("-fx-background-color: Red; -fx-border-color: black;");
				
			}
			
			if(isTileEnemy(x - 1, y + (1*mod))) {
				
				tiles[x - 1][y + (1*mod)].setStyle("-fx-background-color: Red; -fx-border-color: black;"); 
				
			}

			
		}catch(Exception e) {
			
			
			
		}
		
		
	}

	@Override
	public void deMarkPossibleWays() {

		ChessBoardTile tiles [][]= GameView.cb.tiles;
		
		int mod = 1; 
		if(player == 2) {mod=-1;}
		
		int x = (int) (locationBeforeDragDrop.getX()/size);
		int y = (int) (locationBeforeDragDrop.getY()/size);
		
		try {
		
		tiles[x][y + (1*mod)].setStyle("-fx-background-color: "+ tiles[x][y + (1*mod)].standartColor +"; -fx-border-color: transparent;");
		tiles[x][y + (2*mod)].setStyle("-fx-background-color: " + tiles[x][y + (2*mod)].standartColor + "; -fx-border-color: transparent;");
		tiles[x + 1][y + (1*mod)].setStyle("-fx-background-color: " + tiles[x + 1][y + (1*mod)].standartColor + "; -fx-border-color: transparent;");
		tiles[x - 1][y + (1*mod)].setStyle("-fx-background-color: "+ tiles[x - 1][y + (1*mod)].standartColor + "; -fx-border-color: transparent;"); 
		
		}catch(Exception e) {
			
			
			
		}
		
	}


	@Override
	public ArrayList<ArrayPoint> getPossibleCoordinates() {
	
		int mod = 1; 
		if(player == 2) {mod = -1;}
		
		ArrayList <ArrayPoint> posMoves = new ArrayList<>();
		
		if(checkIfMoveIsValid(this.getLayoutX(), this.getLayoutY() + mod)) {
			
			posMoves.add(new ArrayPoint((int)(this.getLayoutX()/size), ((int)(this.getLayoutY()+mod)/size)));
			
		}
		
		if(checkIfMoveIsValid(this.getLayoutX(), this.getLayoutY() + (mod * 2))) {
			
			posMoves.add(new ArrayPoint((int)(this.getLayoutX()/size), ((int)(this.getLayoutY()+ (mod*2))/size)));
			
		}
		
		if(checkIfMoveIsValid(this.getLayoutX() + 1, this.getLayoutY() + mod)) {
			
			posMoves.add(new ArrayPoint((int)((this.getLayoutX() + size)/size), ((int)(this.getLayoutY())/size + mod)));
			
		}
		
		if(checkIfMoveIsValid(this.getLayoutX() - 1, this.getLayoutY() + mod)) {
			
			posMoves.add(new ArrayPoint((int)((this.getLayoutX() - size)/size), ((int)(this.getLayoutY())/size + mod)));
			
		}
		
		return posMoves;
		
	}

	public String toString(){

		return "pw_" + super.toString();

	}
	
	
}
