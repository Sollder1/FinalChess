package de.sollder1.chess.game.gui.view;

//import application.Main;
import de.sollder1.chess.game.Game;
import de.sollder1.chess.starter.Starter;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import de.sollder1.chess.game.playground.ChessBoard;

public class GameView extends Application{
	
	public static ChessBoard cb; //The Chessboard Class
	public static Pane mainPane; // The mainPane which gets initialized by the FXML-File
	private String version;
	
	@Override
	public void start(Stage mainStage) throws Exception {
		
		try {
			//FXML File is getting loaded
			FXMLLoader loader = new FXMLLoader(GameView.class.getResource("/gui/game/mainView.fxml"));
			mainPane = loader.load();
			
			//Chessboard is getting inizialised
			cb = new ChessBoard(600);
			cb.fillBoardWithTiles();
			cb.fillBoardWithFigures();
			
			mainPane.getChildren().add(cb); //Add Chessboard to Mainpane
		
			Scene s = new Scene(mainPane); //Add Mainpaen to Scene
		
			//CSS File is loaded, here you can change the 
			//FIle to change the Layout of the BOard(Soon)
			s.getStylesheets().add(getClass().getResource("/gui/game/mainView.css").toExternalForm());

			mainStage.setOnCloseRequest( event -> {
				Starter.startStarterInstance();
				Game.stopGameInstance();
				//Im Multiplaxer eventuell ausloggen.
			});

			//Stage gets inizialised
			mainStage.setScene(s);
			mainStage.setTitle("Chess " + version);
			mainStage.setResizable(false);
			mainStage.show();
		
		 }catch(Exception e) {
			
			e.printStackTrace();
			
		}
		
	}

	

}
