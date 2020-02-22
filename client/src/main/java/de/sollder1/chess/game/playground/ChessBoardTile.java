package de.sollder1.chess.game.playground;

import de.sollder1.chess.game.helpObjects.Point;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;

//The Tiles on the Chessboard

public class ChessBoardTile extends Button{
	
	public String standartColor;
	
	//Color: as HEX-Code
	//Size as var for heigth and width
	//Position: the Coordinates of the Tile relativ 
	//to the Parent
	public ChessBoardTile(String color, int size, Point position) {
		
		setStyle("-fx-background-color: " + color);		
		
		standartColor = color;
		
		setLayoutX(position.getX());
		setLayoutY(position.getY());
		
		setPrefSize(size, size);
	    setMinSize(Pane.USE_PREF_SIZE, Pane.USE_PREF_SIZE);
	    setMaxSize(Pane.USE_PREF_SIZE, Pane.USE_PREF_SIZE);
	    
	    setId("tiles");
	    
	    clickListener();
		
	}
	
	//No use yet, but could handle the click event on a Tile
	private void clickListener() {
		
		setOnAction(event -> {
			
			System.out.println(event.getSource().getClass());
			
		});
		
	}
	
}
