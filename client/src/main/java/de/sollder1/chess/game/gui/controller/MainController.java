package de.sollder1.chess.game.gui.controller;

import java.net.URL;

import java.util.ResourceBundle;

import de.sollder1.chess.game.Game;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.TilePane;

public class MainController implements Initializable{
	
	@FXML
	Label roundLabel;
	
	@FXML
	TilePane graveyardWhite, graveyardBlack;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
		//Mainly makes the Controlls from the FXML-File in the Main available
		Game.roundLabel = roundLabel;
		Game.graveyardBlack = graveyardBlack;
		Game.graveyardWhite = graveyardWhite;
		
	}
	
}
