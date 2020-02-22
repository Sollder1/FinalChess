package de.sollder1.chess.game.chessfigures;


import java.util.ArrayList;
import java.util.List;

import de.sollder1.chess.game.Game;
import de.sollder1.chess.game.gui.view.GameView;
import de.sollder1.chess.game.helpObjects.ArrayPoint;
import de.sollder1.chess.game.helpObjects.Point;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;

public abstract class Figure extends Button {

    Boolean locked = false;
    Boolean death = false;
    public int size;
    public int player;

    public int itemID;

    public Figure figuresOnTheField[][];

    Point locationBeforeDragDrop;
    Point locationBeforeDeath;

    ArrayList<ArrayPoint> possibleCoordinates;


    //Player: 2: weißer pawn; 1: schwarzer pawn
    //Size as var for heigth and width
    //position The Position within the Pane
    public Figure(int size, int itemID, Point position, int player) {


        locationBeforeDragDrop = position;

        setPrefSize(size, size);
        setMinSize(Pane.USE_PREF_SIZE, Pane.USE_PREF_SIZE);
        setMaxSize(Pane.USE_PREF_SIZE, Pane.USE_PREF_SIZE);

        setLayoutX(position.getX());
        setLayoutY(position.getY());

        this.size = size;
        this.itemID = itemID;
        this.player = player;

        figuresOnTheField = GameView.cb.figuresOnTheField;

        dragDropActive();

        //Move to Ecplicit Implementation(Pawn usw.)
        setId("figure");

    }

    //Erlaubt die Programatische Verschiebung einer Figur.
    public void moveFigure(int x, int y) {

        locationBeforeDragDrop = new Point(this.getLayoutX(), this.getLayoutY());
        GameView.cb.figuresOnTheField[(int) (locationBeforeDragDrop.getX() / size)][(int) (locationBeforeDragDrop.getY() / size)] = null;


        setLayoutX(x * size);
        setLayoutY(y * size);

        if (isFigureUnderMe(x * size, y * size)) {

            //Returns the actual Figure under this Figure
            Figure underMe = getFigureUnderMe(x * size, y * size);
            underMe.setDeath(true);

        }

        GameView.cb.figuresOnTheField[(int) (getLayoutX() / size)][(int) (getLayoutY() / size)] = this;

        Game.changePlayer();


    }

    //Controlls Drag and Drop
    private void dragDropActive() {

        //Fires when the Figure gets Clicked
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

        //Fires when the Figure is dragged
        setOnMouseDragged(event -> {

            if (locked) {

                //Computes the new x and y of the Mouse
                //relativ to the de.sollder1.oldengine.engine.playground bzw. Chessboard
                double x = getLocationRealtiveToPane(event).getX();
                double y = getLocationRealtiveToPane(event).getY();

                //Sets the Figure to the
                setLayoutX(x);
                setLayoutY(y);

            }
        });

        //Fires when the Mouse is relased
        setOnMouseReleased(event -> {

            if (locked) {

                locked = false;

                //Computes the Location of the Mouse realive to the Chessboard
                double x = getLocationRealtiveToPane(event).getX() + (size / 2);
                double y = getLocationRealtiveToPane(event).getY() + (size / 2);

                //GEts the Correct Position on the Field
                double newx = x - (x % size);
                double newy = y - (y % size);

                //The Potential Figure under the new Position of this Figures new Location
                Figure underMe;

                //Corrects the values of the Placement if they
                //are out of the Borders of the Chessboard
                if (newx < 0) {
                    newx = 0;
                }
                if (newy < 0) {
                    newy = 0;
                }

                if (newx > GameView.cb.size - size) {
                    newx = GameView.cb.size - size;
                }
                if (newy > GameView.cb.size - size) {
                    newy = GameView.cb.size - size;
                }

                //Checks in the concrete Implementation if the
                //performed move is valid, if not restore the Position
                //before the DragDrop has started
                if (!checkIfMoveIsValid(newx, newy)) {

                    setLayoutX(locationBeforeDragDrop.getX());
                    setLayoutY(locationBeforeDragDrop.getY());

                    deMarkPossibleWays();

                    return;

                }

                //Checks if there is an other Figure under this Figure
                if (isFigureUnderMe(newx, newy)) {

                    //Returns the actual Figure under this Figure
                    underMe = getFigureUnderMe(newx, newy);

                    //If the Figure is owned by the current Player
                    //Or is the King
                    //Set the Figure back too the locationBeforeDragDrop Position
                    if (underMe.player == Game.getPlayer() || underMe instanceof King) {

                        setLayoutX(locationBeforeDragDrop.getX());
                        setLayoutY(locationBeforeDragDrop.getY());

                        deMarkPossibleWays();

                        return;

                        //If the Figure under this Figure is owned
                        //by the Enemy Player it is getting killed.
                    } else {

                        underMe.setDeath(true);

                    }

                }

                //Remove old entry in the figuresOnTheField Array
                GameView.cb.figuresOnTheField[(int) (locationBeforeDragDrop.getX() / size)][(int) (locationBeforeDragDrop.getY() / size)] = null;

                //Set the figure to the new Position
                setLayoutX(newx);
                setLayoutY(newy);

                //Add new entry in the figuresOnTheField Array
                GameView.cb.figuresOnTheField[(int) (newx / size)][(int) (newy / size)] = this;

                deMarkPossibleWays();

                //Change Player
                Game.changePlayer();

                GameView.cb.printField();

            }

        });

    }

    //Computes the Position of the Mouse relativ to the Chessboard
    private Point getLocationRealtiveToPane(MouseEvent event) {

        double x = event.getSceneX() - ((GameView.mainPane.getPrefWidth() - 100) / 2) - size / 2; //TODO: Skalierbar
        double y = event.getSceneY() - ((GameView.mainPane.getPrefHeight() - GameView.cb.size) / 2) - size / 2;

        return new Point(x, y);

    }

    //Returns if(true) or if not(false) there is an
    //other Figure under This Figure
    private Boolean isFigureUnderMe(double newx, double newy) {

        Figure underMe = GameView.cb.figuresOnTheField[(int) (newx / size)][(int) (newy / size)];

        return underMe != null;

    }

    //Returns the Figure under This Figure
    private Figure getFigureUnderMe(double newx, double newy) {

        return GameView.cb.figuresOnTheField[(int) (newx / size)][(int) (newy / size)];

    }

    //Setzt die Figur bei true auf Death und bei false auf Alive(Alive setzten TODO)
    //For this reason the size gets schrunk down to 50 and it is removed from the Chessboard
    //and is added to the appropriate Graveyard
    private void setDeath(boolean b) {

        if (b) {

            setPrefSize(50.0, 50.0);

            if (player == 1) {

                GameView.cb.getChildren().remove(this);
                Game.graveyardBlack.getChildren().add(this);

                death = true;

            } else {

                GameView.cb.getChildren().remove(this);
                Game.graveyardWhite.getChildren().add(this);

                death = true;

            }

            setDisable(true);

            GameView.cb.figuresOnTheField[(int) (getPrefWidth() / size)][(int) (getPrefHeight() / size)] = null;


        } else {

            //TODO: Set Alive

        }

    }

    protected boolean isTileEmpty(int x, int y) {

        if (figuresOnTheField[x][y] == null) {

            return true;

        } else {

            return false;

        }

    }

    protected boolean isTileEnemy(int x, int y) {

        if (isTileEmpty(x, y)) {
            return false;
        }

        if (figuresOnTheField[x][y].player == Game.getPlayer()) {

            return false;

        } else {

            return true;

        }

    }

    protected boolean isTileFriend(int x, int y) {

        if (isTileEmpty(x, y)) {
            return false;
        }

        if (figuresOnTheField[x][y].player == Game.getPlayer()) {

            return true;

        } else {

            return false;

        }

    }

    //Must be Implemented in every explicit Implementation of this Class to
    //determine if or if not the certain Move was Valid.
    public abstract boolean checkIfMoveIsValid(double newx, double newy);

    //Die möglichen Positionen werden auf dem Schachbrett
    //Farblich markiert um Anfängern zu helfen
    //Auschließlich Kosmetischer Natur
    public abstract void markPossibleWays();

    //Die Farblichen Markierungen werden wieder entfernt
    //um einen normalen Spielfluss zu gewährleisten
    //Auschließlich Kosmetischer Natur
    public abstract void deMarkPossibleWays();

    public abstract List<ArrayPoint> getPossibleCoordinates();


}
