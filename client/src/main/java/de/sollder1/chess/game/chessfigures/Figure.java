package de.sollder1.chess.game.chessfigures;


import java.util.List;

import de.sollder1.chess.game.Game;
import de.sollder1.chess.game.gui.view.GameView;
import de.sollder1.chess.game.helpObjects.ArrayPoint;
import de.sollder1.chess.game.helpObjects.Point;
import de.sollder1.chess.game.playground.ChessBoard;
import de.sollder1.chess.game.playground.ChessBoardTile;
import de.sollder1.common.util.Logger;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.Region;

public abstract class Figure extends Button {

    private boolean locked = false;
    protected int size;
    protected int player;
    protected int itemID;

    Point locationBeforeDragDrop;

    //Player: 2: weißer pawn; 1: schwarzer pawn
    //Size as var for heigth and width
    //position The Position within the Pane
    public Figure(int size, int itemID, Point position, int player) {

        if (player != 1 && player != 2) {
            Logger.logE("Wrong playerId");
            System.exit(-1);
        }

        setId("figure"); //CSS

        setBackground(Background.EMPTY);

        locationBeforeDragDrop = position;

        setPrefSize(size, size);
        setMinSize(Region.USE_PREF_SIZE, Region.USE_PREF_SIZE);
        setMaxSize(Region.USE_PREF_SIZE, Region.USE_PREF_SIZE);

        setLayoutX(position.getX());
        setLayoutY(position.getY());

        this.size = size;
        this.itemID = itemID;
        this.player = player;

        initDragAndDrop();

    }

    //Erlaubt die Programatische Verschiebung einer Figur.
    public void moveFigure(int x, int y) {

        locationBeforeDragDrop = new Point(this.getLayoutX(), this.getLayoutY());
        ChessBoard.getInstance().getUiFigures()[(int) (locationBeforeDragDrop.getX() / size)][(int) (locationBeforeDragDrop.getY() / size)] = null;


        setLayoutX((double) x * size);
        setLayoutY((double) y * size);

        if (isFigureUnderMe((double) x * size, (double) y * size)) {

            //Returns the actual Figure under this Figure
            Figure underMe = getFigureUnderMe((double) x * size, (double) y * size);
            underMe.setDeath();

        }

        ChessBoard.getInstance().getUiFigures()[(int) (getLayoutX() / size)][(int) (getLayoutY() / size)] = this;

        Game.changePlayer();


    }

    //Controlls Drag and Drop
    private void initDragAndDrop() {

        //Fires when the Figure gets Clicked
        onMousePressedInitializer();

        //Fires when the Figure is dragged
        onMouseDraggedInitializer();

        //Fires when the Mouse is relased
        onMouseReleasedInitializer();

    }

    private void onMouseReleasedInitializer() {
        setOnMouseReleased(event -> {

            if (locked) {

                locked = false;

                //Computes the Location of the Mouse realive to the Chessboard
                double x = getLocationRelativeToPane(event).getX() + ((double) size / 2);
                double y = getLocationRelativeToPane(event).getY() + ((double) size / 2);

                //Gets the Correct Position on the Field
                double newX = x - (x % size);
                double newY = y - (y % size);

                //The Potential Figure under the new Position of this Figures new Location
                Figure underMe;

                //Corrects the values of the Placement if they
                //are out of the Borders of the Chessboard
                if (newX < 0) {
                    newX = 0;
                }
                if (newY < 0) {
                    newY = 0;
                }

                int chessBoardSize = ChessBoard.getInstance().getSize();

                if (newX > chessBoardSize - size) {
                    newX = (double) (chessBoardSize) - size;
                }
                if (newY > chessBoardSize - size) {
                    newY = (double) (chessBoardSize) - size;
                }

                //Checks in the concrete Implementation if the
                //performed move is valid, if not restore the Position
                //before the DragDrop has started
                if (!checkIfMoveIsValid(newX, newY)) {

                    setLayoutX(locationBeforeDragDrop.getX());
                    setLayoutY(locationBeforeDragDrop.getY());

                    deMarkPossibleWays();

                    return;

                }

                //Checks if there is an other Figure under this Figure
                if (isFigureUnderMe(newX, newY)) {

                    //Returns the actual Figure under this Figure
                    underMe = getFigureUnderMe(newX, newY);

                    if (underMe.player == Game.getPlayer() || underMe instanceof King) {

                        setLayoutX(locationBeforeDragDrop.getX());
                        setLayoutY(locationBeforeDragDrop.getY());
                        deMarkPossibleWays();
                        return;

                    } else {
                        underMe.setDeath();
                    }

                }

                //Remove old entry in the figuresOnTheField Array
                ChessBoard.getInstance().getUiFigures()[(int) (locationBeforeDragDrop.getX() / size)][(int) (locationBeforeDragDrop.getY() / size)] = null;

                //Set the figure to the new Position
                setLayoutX(newX);
                setLayoutY(newY);

                afterSuccessFullMoveAction();

                //Add new entry in the figuresOnTheField Array
                ChessBoard.getInstance().getUiFigures()[(int) (newX / size)][(int) (newY / size)] = this;
                deMarkPossibleWays();

                //Change Player
                Game.changePlayer();
            }

        });
    }

    private void onMouseDraggedInitializer() {
        setOnMouseDragged(event -> {

            if (locked) {

                //Computes the new x and y of the Mouse
                //relativ to the de.sollder1.oldengine.engine.playground bzw. Chessboard
                double x = getLocationRelativeToPane(event).getX();
                double y = getLocationRelativeToPane(event).getY();

                //Sets the Figure to the
                setLayoutX(x);
                setLayoutY(y);

            }
        });
    }

    private void onMousePressedInitializer() {
        setOnMousePressed(event -> {

            //If the current Player is also the owner of the Figure
            //The Figure gets locked and the old Position is set
            if (player == Game.getPlayer()) {

                locked = true;
                locationBeforeDragDrop = new Point(getLayoutX(), getLayoutY());
                toFront();

                markPossibleWays();

            }

        });
    }

    //Computes the Position of the Mouse relativ to the Chessboard
    private Point getLocationRelativeToPane(MouseEvent event) {

        double x = event.getSceneX() - ((GameView.mainPane.getPrefWidth() - 100) / 2) - (double) size / 2; //TODO: Skalierbar
        double y = event.getSceneY() - ((GameView.mainPane.getPrefHeight() - ChessBoard.getInstance().getSize()) / 2) - size / 2;

        return new Point(x, y);

    }


    private boolean isFigureUnderMe(double newX, double newY) {

        Figure underMe = ChessBoard.getInstance().getUiFigures()[(int) (newX / size)][(int) (newY / size)];
        return underMe != null;

    }

    private Figure getFigureUnderMe(double newX, double newY) {

        return ChessBoard.getInstance().getUiFigures()[(int) (newX / size)][(int) (newY / size)];

    }

    private void setDeath() {

        ChessBoard.getInstance().getUiFigures()[(int) (getPrefWidth() / size)][(int) (getPrefHeight() / size)] = null;

        if (player == 1) {
            ChessBoard.getInstance().getChildren().remove(this);
            Game.graveyardBlack.getChildren().add(this);

        } else {
            ChessBoard.getInstance().getChildren().remove(this);
            Game.graveyardWhite.getChildren().add(this);
        }

        setPrefSize(25, 25);
        setStyle(getStyle() + "-fx-background-size: 25;" );
        setDisable(true);

    }

    protected boolean isTileEmpty(int x, int y) {
        return ChessBoard.getInstance().getUiFigures()[x][y] == null;
    }

    protected boolean isTileEnemy(int x, int y) {

        if (isTileEmpty(x, y)) {
            return false;
        }

        return ChessBoard.getInstance().getUiFigures()[x][y].player != Game.getPlayer();

    }

    protected boolean isTileFriend(int x, int y) {

        if (isTileEmpty(x, y)) {
            return false;
        }

        return ChessBoard.getInstance().getUiFigures()[x][y].player == Game.getPlayer();

    }

    @Override
    public String toString() {
        return player + "_" + itemID;
    }

    List<ArrayPoint> possibleCoordinatesBeforeMarking;

    public void markPossibleWays() {

        possibleCoordinatesBeforeMarking = getPossibleCoordinates();

        ChessBoardTile[][] tiles = ChessBoard.getInstance().getTiles();

        for (ArrayPoint p : possibleCoordinatesBeforeMarking) {

            tiles[p.getX()][p.getY()].setStyle("-fx-background-color: " + p.getColorOfTheTile() + "; -fx-border-color: black;");

        }

    }

    public void deMarkPossibleWays() {

        ChessBoardTile[][] tiles = ChessBoard.getInstance().getTiles();

        for (ArrayPoint p : possibleCoordinatesBeforeMarking) {
            tiles[p.getX()][p.getY()].setStyle("-fx-background-color: " + tiles[p.getX()][p.getY()].standartColor + "; -fx-border-color: transparent;");
        }

    }

    public boolean checkIfMoveIsValid(double newX, double newY) {

        for (ArrayPoint possibleCoordinate : possibleCoordinatesBeforeMarking) {
            if (possibleCoordinate.getX() == (int) (newX / size) && possibleCoordinate.getY() == (int) (newY / size)) {
                return true;
            }
        }

        return false;
    }

    protected void afterSuccessFullMoveAction(){
        //Standard is noop
    }

    public abstract List<ArrayPoint> getPossibleCoordinates();


    public int getPlayer() {
        return player;
    }
}
