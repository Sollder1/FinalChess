package de.sollder1.chess.game.playground;

import de.sollder1.chess.game.helpObjects.Point;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;

//The Tiles on the Chessboard
public class ChessBoardTile extends Button {

    private boolean lightColor;
    private boolean alreadyMarked = false;

    //Color: as HEX-Code
    //Size as var for heigth and width
    //Position: the Coordinates of the Tile relativ
    //to the Parent
    public ChessBoardTile(boolean lightColor, int size, Point position) {

        getStyleClass().add(lightColor ? "lightTile" : "darkTile");

        setLayoutX(position.getX());
        setLayoutY(position.getY());

        setPrefSize(size, size);
        setMinSize(Pane.USE_PREF_SIZE, Pane.USE_PREF_SIZE);
        setMaxSize(Pane.USE_PREF_SIZE, Pane.USE_PREF_SIZE);

        this.lightColor = lightColor;

        clickListener();

    }

    //No use yet, but could handle the click event on a Tile
    private void clickListener() {

        setOnAction(event -> {

            System.out.println(event.getSource().getClass());

        });

    }

    public void mark(String colorOfTheTile) {

        if (!alreadyMarked) {
            getStyleClass().clear();

            String classToAdd;

            switch (colorOfTheTile) {
                case "red":
                    classToAdd = "markedEnemyTile";
                    break;
                case "green":
                    classToAdd = "markedFriendTile";
                    break;
                case "blue":
                    classToAdd = "markedRochadeTile";
                    break;
                case "yellow":
                    classToAdd = "markedKingTile";
                    break;
                case "orange":
                    classToAdd = "markedKingDangerTile";
                    break;
                default:
                    classToAdd = "";
            }
			getStyleClass().add(classToAdd);
        }
        alreadyMarked = true;

    }

    public void deMark() {

        alreadyMarked = false;
        getStyleClass().clear();
        getStyleClass().add(lightColor ? "lightTile" : "darkTile");

    }

    public boolean isAlreadyMarked() {
        return alreadyMarked;
    }
}
