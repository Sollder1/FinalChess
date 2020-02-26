package de.sollder1.chess.game.chessfigures;

import java.util.List;

import de.sollder1.chess.game.Game;
import de.sollder1.chess.game.gui.view.GameView;
import de.sollder1.chess.game.helpObjects.ArrayPoint;
import de.sollder1.chess.game.helpObjects.Point;
import de.sollder1.chess.game.helpObjects.Rochade;
import de.sollder1.chess.game.helpObjects.Utils;
import de.sollder1.chess.game.playground.ChessBoard;
import de.sollder1.chess.game.playground.ChessBoardTile;
import de.sollder1.common.util.Logger;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.Region;

public abstract class Figure extends Button {

    private boolean locked = false;
    public static final int FIGURE_SIZE = 75;
    protected int player;
    protected int itemID;
    protected boolean figureMoved = false;
    private boolean death = false;

    protected Point currentPositionPX;
    protected ArrayPoint currentPosition;

    //Player: 2: weißer pawn; 1: schwarzer pawn
    //Size as var for heigth and width
    //position The Position within the Pane
    public Figure(int itemID, ArrayPoint position, int player) {

        if (player != 1 && player != 2) {
            Logger.logE("Wrong playerId");
            System.exit(-1);
        }

        getStyleClass().add("figure"); //CSS

        setBackground(Background.EMPTY);

        this.currentPositionPX = new Point((int) position.getI() * FIGURE_SIZE, (int) position.getJ() * FIGURE_SIZE);;
        this.currentPosition = position;

        setPrefSize(FIGURE_SIZE, FIGURE_SIZE);
        setMinSize(Region.USE_PREF_SIZE, Region.USE_PREF_SIZE);
        setMaxSize(Region.USE_PREF_SIZE, Region.USE_PREF_SIZE);

        setLayoutX(currentPositionPX.getX());
        setLayoutY(currentPositionPX.getY());

        this.itemID = itemID;
        this.player = player;

        initDragAndDrop();

    }

    //Erlaubt die Programatische Verschiebung einer Figur.
    public void moveFigure(int i, int j) {

        Figure underMe;
        if (ChessBoard.isFigureUnderMe(new Point(i * FIGURE_SIZE, j * FIGURE_SIZE))) {

            underMe = ChessBoard.getFigure(new ArrayPoint(i , j));
            if (underMe.player == Game.getPlayer() || underMe instanceof King) {
                setPosition(currentPositionPX);
                return;
            } else {
                underMe.setDeath();
            }

        }

        setPosition(new Point(i * FIGURE_SIZE, j * FIGURE_SIZE));
        afterSuccessFullMoveAction();

        Game.changePlayer();


    }

    public void moveFigure(ArrayPoint destination) {
        moveFigure(destination.getI(), destination.getJ());
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

    private void setPosition(Point position) {
        setLayoutX(position.getX());
        setLayoutY(position.getY());
        currentPositionPX = position;
        currentPosition = new ArrayPoint((int) position.getX() / FIGURE_SIZE, (int) position.getY() / FIGURE_SIZE);
    }

    public ArrayPoint getPosition(){
        return currentPosition;
    }

    public Point getPositionPx(){
        return currentPositionPX;
    }

    private void onMouseReleasedInitializer() {
        setOnMouseReleased((MouseEvent event) -> {
            if (locked) {

                locked = false;
                Point normalizedDestination = getNormalizedDestination(event);
                ChessBoard.deMarkEveryTile();
                ArrayPoint move = checkIfMoveIsValid(normalizedDestination);


                //Move not valid? -> Reset Position and return
                if (move == null) {
                    setPosition(currentPositionPX);
                    return;
                }

                //Rochade
                if (move.getRochade() != null) {

                    if (move.getRochade() == Rochade.SHORT) {
                        ChessBoard.getFigure(7, move.getJ()).moveFigure(5, move.getJ());
                        this.moveFigure(6, move.getJ());
                    } else {
                        ChessBoard.getFigure(0, move.getJ()).moveFigure(3, move.getJ());
                        this.moveFigure(2, move.getJ());
                    }
                    Game.changePlayer();
                    return;

                }

                //Normaler Move:
                this.moveFigure(move);
            }
        });
    }


    private Point getNormalizedDestination(MouseEvent event) {
        //Computes the Location of the Mouse realive to the Chessboard
        double x = getLocationRelativeToPane(event).getX() + ((double) FIGURE_SIZE / 2);
        double y = getLocationRelativeToPane(event).getY() + ((double) FIGURE_SIZE / 2);

        //Gets the Correct Position on the Field
        double newX = x - (x % FIGURE_SIZE);
        double newY = y - (y % FIGURE_SIZE);

        //Corrects the values of the Placement if they
        //are out of the Borders of the Chessboard
        if (newX < 0) {
            newX = 0;
        }
        if (newY < 0) {
            newY = 0;
        }

        int chessBoardSize = ChessBoard.getInstance().getSize();

        if (newX > chessBoardSize - FIGURE_SIZE) {
            newX = (double) (chessBoardSize) - FIGURE_SIZE;
        }
        if (newY > chessBoardSize - FIGURE_SIZE) {
            newY = (double) (chessBoardSize) - FIGURE_SIZE;
        }

        return new Point(newX, newY);

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

                if(this instanceof King){
                    ChessBoard.markKingWays(this.getPlayer(), this);
                }

                locked = true;
                toFront();

                markPossibleWays();

            }

        });
    }



    //TODO: Move to chessboard
    private Point getLocationRelativeToPane(MouseEvent event) {

        double x = event.getSceneX() - ((GameView.mainPane.getPrefWidth() - 100) / 2) - (double) FIGURE_SIZE / 2; //TODO: Skalierbar
        double y = event.getSceneY() - ((GameView.mainPane.getPrefHeight() - ChessBoard.getInstance().getSize()) / 2) - FIGURE_SIZE / 2;

        return new Point(x, y);

    }

    private void setDeath() {

        this.death = true;

        if (player == 1) {
            ChessBoard.getInstance().getChildren().remove(this);
            Game.graveyardBlack.getChildren().add(this);

        } else {
            ChessBoard.getInstance().getChildren().remove(this);
            Game.graveyardWhite.getChildren().add(this);
        }

        setPrefSize(50, 50);
        setStyle(getStyle() + "-fx-background-size: 50px;");
        setDisable(true);

    }

    protected boolean isTileEmpty(int i, int j) {

        return ChessBoard.getFigure(i, j) == null;
    }

    protected boolean isTileEnemy(int i, int j) {

        if (isTileEmpty(i, j)) {
            return false;
        }

        return ChessBoard.getFigure(i, j).getPlayer() != Game.getPlayer();

    }

    protected boolean isTileFriend(int i, int j) {

        if (isTileEmpty(i, j)) {
            return false;
        }

        return ChessBoard.getFigure(i, j).getPlayer() == Game.getPlayer();

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

            if(!tiles[p.getI()][p.getJ()].isAlreadyMarked()){
                tiles[p.getI()][p.getJ()].mark(p.getColorOfTheTile());
            }

        }

    }

    public ArrayPoint checkIfMoveIsValid(Point position) {

        for (ArrayPoint possibleCoordinate : possibleCoordinatesBeforeMarking) {
            if (possibleCoordinate.getI() == (int) (position.getX() / FIGURE_SIZE)
                    && possibleCoordinate.getJ() == (int) (position.getY() / FIGURE_SIZE)) {
                return possibleCoordinate;
            }
        }

        return null;
    }

    protected void afterSuccessFullMoveAction() {
        Utils.playMusic("/sfx/chess_move.wav", this.getClass());
        figureMoved = true;
        ChessBoard.postMoveProcessing(this.player);

    }

    public int getPlayer() {
        return player;
    }

    public boolean isDeath() {
        return death;
    }

    public abstract List<ArrayPoint> getPossibleCoordinates();

}