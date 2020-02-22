package de.sollder1.chess.game.chessfigures;

import java.util.ArrayList;

import de.sollder1.chess.game.gui.view.GameView;
import de.sollder1.chess.game.helpObjects.ArrayPoint;
import de.sollder1.chess.game.helpObjects.Point;
import de.sollder1.chess.game.playground.ChessBoardTile;

public class Queen extends Figure {

	ArrayList<ArrayPoint> possibleCoordinates;
	
	public Queen(int size, int itemID, Point position, int player) {
		
		super(size, itemID, position, player);

		//Outsource to CSS File
	    if(player == 1) {
	    	
	    	setStyle("-fx-background-image: url('/gfx/blackQueen.png')");
	    	
	    }else if(player==2){
	    	
	    	setStyle("-fx-background-image: url('/gfx/whiteQueen.png')");
	    	
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
		
		
		//VErhalten des Bishops
		
		//Nach unten rechts
		for(int x = position.getX()+1, y = position.getY()+1; x<8 && y<8; x++, y++) {
			
			//Wenn Feld leer adden und continue
			if(isTileEmpty(x, y)) {
				
				posMoves.add(new ArrayPoint(x, y));
				
				continue;
				
			}
			
			//Wenn Feld Enemy adden und Schleife beenden(break)
			if(isTileEnemy(x, y)) {
				
				posMoves.add(new ArrayPoint(x, y, "red"));
				
				break;
				
			}
			
			//Wenn Feld Friend break
			if(isTileFriend(x, y)) {
				
				break;
				
			}
			
			
		}
		
		
		//Nach unten links
		for(int x = position.getX()-1, y = position.getY()+1; x>=0 && y<8; x--, y++) {
			
			//Wenn Feld leer adden und continue
			if(isTileEmpty(x, y)) {
				
				posMoves.add(new ArrayPoint(x, y));
				
				continue;
				
			}
			
			//Wenn Feld Enemy adden und Schleife beenden(break)
			if(isTileEnemy(x, y)) {
				
				posMoves.add(new ArrayPoint(x, y, "red"));
				
				break;
				
			}
			
			//Wenn Feld Friend break
			if(isTileFriend(x, y)) {
				
				break;
				
			}
			
			
		}
		
		//Nach oben rechts
		for(int x = position.getX()+1, y = position.getY()-1; x<8 && y>=0; x++, y--) {
			
			//Wenn Feld leer adden und continue
			if(isTileEmpty(x, y)) {
				
				posMoves.add(new ArrayPoint(x, y));
				
				continue;
				
			}
			
			//Wenn Feld Enemy adden und Schleife beenden(break)
			if(isTileEnemy(x, y)) {
				
				posMoves.add(new ArrayPoint(x, y, "red"));
				
				break;
				
			}
			
			//Wenn Feld Friend break
			if(isTileFriend(x, y)) {
				
				break;
				
			}
			
			
		}
		
		//Nach oben links
		for(int x = position.getX()-1, y = position.getY()-1; x>=0 && y>=0; x--, y--) {
			
			//Wenn Feld leer adden und continue
			if(isTileEmpty(x, y)) {
				
				posMoves.add(new ArrayPoint(x, y));
				
				continue;
				
			}
			
			//Wenn Feld Enemy adden und Schleife beenden(break)
			if(isTileEnemy(x, y)) {
				
				posMoves.add(new ArrayPoint(x, y, "red"));
				
				break;
				
			}
			
			//Wenn Feld Friend break
			if(isTileFriend(x, y)) {
				
				break;
				
			}
			
			
		}
		
		
		//VErhalten des Rooks
		
		
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

	@Override
	public void markPossibleWays() {
		
		possibleCoordinates = getPossibleCoordinates();
		
		ChessBoardTile tiles [][]= GameView.cb.tiles;
		
		for(ArrayPoint p : possibleCoordinates) {
			
			tiles[p.getX()][p.getY()].setStyle("-fx-background-color: " + p.ColorOfTheTile + "; -fx-border-color: black;");
			
		}

	}

	@Override
	public void deMarkPossibleWays() {

		ChessBoardTile tiles [][]= GameView.cb.tiles;
		
		for(ArrayPoint p : possibleCoordinates) {
			
			tiles[p.getX()][p.getY()].setStyle("-fx-background-color: " + tiles[p.getX()][p.getY()].standartColor + "; -fx-border-color: transparent;");
			
		}

	}
	
	public String toString(){
		
		return "qu_" + this.player;
		
	}

}
