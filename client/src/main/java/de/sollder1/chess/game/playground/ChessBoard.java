package de.sollder1.chess.game.playground;

import de.sollder1.chess.game.chessfigures.Figure;
import de.sollder1.chess.game.chessfigures.King;
import de.sollder1.chess.game.chessfigures.Bishop;
import de.sollder1.chess.game.chessfigures.Knigth;
import de.sollder1.chess.game.chessfigures.Pawn;
import de.sollder1.chess.game.chessfigures.Queen;
import de.sollder1.chess.game.chessfigures.Rook;
import de.sollder1.chess.game.gui.view.GameView;
import de.sollder1.chess.game.helpObjects.Point;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;

public class ChessBoard extends Pane{
	
	public int size; //The Size of the Pane
	
	Button b = new Button();//Unused
	
	public Figure figuresOnTheField[][] = new Figure[8][8]; //[x][y]
	public ChessBoardTile tiles [][]= new ChessBoardTile[8][8]; //[x][y]
	
	//Size as var for heigth and width
	public ChessBoard(int size){
		
		//outsource to CSS
		setStyle("-fx-background-color: gray");

		//Size gets set
		setPrefSize(size, size);
	    setMinSize(Pane.USE_PREF_SIZE, Pane.USE_PREF_SIZE);
	    setMaxSize(Pane.USE_PREF_SIZE, Pane.USE_PREF_SIZE);
	    
	    //Position in the mainPane (scalable)
	    setLayoutX((GameView.mainPane.getPrefWidth()-size)-50);
	    setLayoutY((GameView.mainPane.getPrefHeight()-size)/2);
	    
		this.size = size;
			
	}
	
	//Puts the Tiles on the Chessboard
	public void fillBoardWithTiles(){
		
		String color = "#cc9900";
		
		ChessBoardTile tilesOneDimArray [] = new ChessBoardTile[64];
		
		for(int i = 1; i<= 64; i++) {
			
			int x = ((i-1)/8) * (size/8);
			int y = ((i-1)%8) * (size/8);
			
			ChessBoardTile ncbt = (new ChessBoardTile(color, size/8, new Point(x,y)));
			
			getChildren().add(ncbt);
			tilesOneDimArray[i-1] = ncbt;
			
			
			if(color.equals("#cc9900")) {
					
				color = "#663300";
					
			}else {
					
				color = "#cc9900";
					
			}
				
			if(i % 8 == 0) {
					
				if(color.equals("#cc9900")) {
						
					color = "#663300";
						
				}else {
						
					color = "#cc9900";
						
				}
			}
		}
		
		//this.tilesOneDimArray = tilesOneDimArray;
		
		arrayToTwoDim(tilesOneDimArray);
		
		
	}
	
	private void arrayToTwoDim(ChessBoardTile[] oneDim) {

		for (int i = 0; i< 64; i++) {
			
				tiles[i/8][i%8] = oneDim[i];
				
		}
	}

	public void fillBoardWithFigures(){
		
		//Pawns adden(black):
		for(int i = 0; i< 8; i++) {
				
			figuresOnTheField[i][1] = new Pawn(size/8, i, new Point(i*(size/8), size/8), 1);
			getChildren().add(figuresOnTheField[i][1]);
				
		}
		
		//Pawns adden(white):
		for(int i = 0; i< 8; i++) {
			
			figuresOnTheField[i][6] = new Pawn(size/8, i, new Point(i*(size/8), 6*(size/8)), 2);
			getChildren().add(figuresOnTheField[i][6]);
				
		}

		//Rooks adden(black):
		figuresOnTheField[0][0] = new Rook(size/8, 1, new Point(0*(size/8), 0*(size/8)), 1);
		getChildren().add(figuresOnTheField[0][0]);
		figuresOnTheField[7][0] = new Rook(size/8, 1, new Point(7*(size/8), 0*(size/8)), 1);
		getChildren().add(figuresOnTheField[7][0]);
		
		//Rooks adden(white):
		figuresOnTheField[0][7] = new Rook(size/8, 1, new Point(0*(size/8), 7*(size/8)), 2);
		getChildren().add(figuresOnTheField[0][7]);
		figuresOnTheField[7][7] = new Rook(size/8, 1, new Point(7*(size/8), 7*(size/8)), 2);
		getChildren().add(figuresOnTheField[7][7]);

		
		//Bishops adden(black):
		figuresOnTheField[2][0] = new Bishop(size/8, 1, new Point(2*(size/8), 0*(size/8)), 1);
		getChildren().add(figuresOnTheField[2][0]);
		figuresOnTheField[5][0] = new Bishop(size/8, 1, new Point(5*(size/8), 0*(size/8)), 1);
		getChildren().add(figuresOnTheField[5][0]);
		
		//Bishops adden(white):
		figuresOnTheField[2][7] = new Bishop(size/8, 1, new Point(2*(size/8), 7*(size/8)), 2);
		getChildren().add(figuresOnTheField[2][7]);
		figuresOnTheField[5][7] = new Bishop(size/8, 1, new Point(5*(size/8), 7*(size/8)), 2);
		getChildren().add(figuresOnTheField[5][7]);
	
		
		//Knigths adden(black):
		figuresOnTheField[1][0] = new Knigth(size/8, 1, new Point(1*(size/8), 0*(size/8)), 1);
		getChildren().add(figuresOnTheField[1][0]);
		figuresOnTheField[6][0] = new Knigth(size/8, 1, new Point(6*(size/8), 0*(size/8)), 1);
		getChildren().add(figuresOnTheField[6][0]);
		
		//Knigths adden(white):
		figuresOnTheField[1][7] = new Knigth(size/8, 1, new Point(1*(size/8), 7*(size/8)), 2);
		getChildren().add(figuresOnTheField[1][7]);
		figuresOnTheField[6][7] = new Knigth(size/8, 1, new Point(6*(size/8), 7*(size/8)), 2);
		getChildren().add(figuresOnTheField[6][7]);
		
		
		//Queen adden(black)
		figuresOnTheField[3][0] = new Queen(size/8, 1, new Point(3*(size/8), 0*(size/8)), 1);
		getChildren().add(figuresOnTheField[3][0]);
		
		
		//Queen adden(white)
		figuresOnTheField[3][7] = new Queen(size/8, 1, new Point(3*(size/8), 7*(size/8)), 2);
		getChildren().add(figuresOnTheField[3][7]);
		
		
		//King adden(black)
		figuresOnTheField[4][0] = new King(size/8, 1, new Point(4*(size/8), 0*(size/8)), 1);
		getChildren().add(figuresOnTheField[4][0]);
		
		
		//King adden(white)
		figuresOnTheField[4][7] = new King(size/8, 1, new Point(4*(size/8), 7*(size/8)), 2);
		getChildren().add(figuresOnTheField[4][7]);
		
		
		printField();
		
	}

	//Helpful Method to Test the Array which represents 
	//the Position of the Figures in the Data
	public void printField() {

		for(int i = 0; i<figuresOnTheField.length; i++) {
			
			for(int j = 0; j<figuresOnTheField[0].length; j++) {
				
				System.out.print(figuresOnTheField[j][i] + " ");
				
			}
			
			System.out.println();
			
		}
		
		System.out.println();
		
	}
	
	
	
}
