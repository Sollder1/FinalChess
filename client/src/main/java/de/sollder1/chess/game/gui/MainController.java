package de.sollder1.chess.game.gui;

import java.net.URL;

import java.util.ResourceBundle;

import de.sollder1.chess.game.Game;
import de.sollder1.chess.game.uielements.other.ChessClock;
import de.sollder1.chess.starter.gui.settings.SettingsPojo;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.VBox;

public class MainController implements Initializable{
	
	@FXML
	Label roundLabel;
	
	@FXML
	TilePane graveyardWhite, graveyardBlack;

	@FXML
	VBox toolBox;

	@FXML
	HBox clockContainerWhite, clockContainerBlack;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {

		//Mainly makes the Controlls from the FXML-File in the Main available
		Game.setRoundLabel(roundLabel);
		Game.setGraveyardBlack(graveyardBlack);
		Game.setGraveyardWhite(graveyardWhite);

		if(SettingsPojo.isUseChessClock()){
			Game.setWhiteClock(new ChessClock(SettingsPojo.getChessClockTime(), 2));
			Game.setBlackClock(new ChessClock(SettingsPojo.getChessClockTime(), 1));
			clockContainerWhite.getChildren().add(Game.getWhiteClock());
			clockContainerBlack.getChildren().add(Game.getBlackClock());
		}

	}

}
