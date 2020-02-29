package de.sollder1.chess.game.uielements.chessfigures;

import java.util.List;
import de.sollder1.chess.game.Game;
import de.sollder1.chess.game.gui.GameView;
import de.sollder1.chess.game.helper.*;
import de.sollder1.chess.game.uielements.chessboard.ChessBoard;
import de.sollder1.chess.game.uielements.chessboard.ChessBoardTile;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;

public abstract class Figure extends Button implements Cloneable {

    public static final int FIGURE_SIZE = GameView.SIZE/8;

    private static int idCounter = 1;

    private boolean locked = false;
    private boolean death = false;

    protected Point positionPX;
    protected ArrayPoint position;
    protected int player;
    protected int itemID;
    protected boolean figureMoved = false;


    public Figure getClone(){
        try {
            return (Figure)clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        return null;
    }

    public Figure(ArrayPoint position, int player) {

        this.getStyleClass().add("figure"); //CSS
        this.setStyle("-fx-background-size: " + FIGURE_SIZE + "px;");
        this.setPrefSize(FIGURE_SIZE, FIGURE_SIZE);

        this.positionPX = new Point(position.getI() * FIGURE_SIZE, position.getJ() * FIGURE_SIZE);
        this.position = position;
        this.setPosition(positionPX);

        this.itemID = ++idCounter;
        this.player = player;

        this.initDragAndDrop();
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

    //nur für die AI!
    public void setInternalPosition(ArrayPoint move){
        this.position = move;
        this.positionPX = new Point((int) position.getI() * FIGURE_SIZE, (int) position.getJ() * FIGURE_SIZE);
    }


    private void onMousePressedInitializer() {
        setOnMousePressed(event -> {
            if (player == Game.getPlayer()) {
                if (!(this instanceof King) && GameView.board().isAnKingToMove(player)) {
                    locked = false;
                    return;
                }

                locked = true;
                toFront();
                markPossibleWays();
            }
        });
    }

    private void onMouseDraggedInitializer() {
        setOnMouseDragged(event -> {
            if (locked) {
                //Compute the new Position
                double x = FigureHelper.getLocationRelativeToPane(event).getX();
                double y = FigureHelper.getLocationRelativeToPane(event).getY();

                //Sets the Figure to the
                setLayoutX(x);
                setLayoutY(y);
            }
        });
    }

    private void onMouseReleasedInitializer() {
        setOnMouseReleased((MouseEvent event) -> {
            if (locked) {

                locked = false;
                Point normalizedDestination = FigureHelper.computeNormalizedDestination(event);
                GameView.board().deMarkEveryTile();
                ArrayPoint move = checkIfMoveIsValid(normalizedDestination);

                //Normaler Move:
                if(this.moveFigure(move)){
                    Game.changePlayer();
                }
            }
        });
    }

    public boolean moveFigure(ArrayPoint move) {
        //Wenn der ZUg nicht valide ist
        if (move == null) {
            setPosition(positionPX);
            return false;
        }

        //Rochade
        if (move.getRochade() != null) {
            if (move.getRochade() == Rochade.SHORT) {
                GameView.board().getFigure(7, move.getJ()).moveFigure(new ArrayPoint(5, move.getJ()));
                this.moveFigure(new ArrayPoint(6, move.getJ()));
            } else {
                GameView.board().getFigure(0, move.getJ()).moveFigure(new ArrayPoint(3, move.getJ()));
                this.moveFigure(new ArrayPoint(2, move.getJ()));
            }
            this.afterSuccessFullMoveAction();
            return true;
        }

        if (GameView.board().isFigureUnderMe(new Point(move.getI() * FIGURE_SIZE, move.getJ() * FIGURE_SIZE))) {
            Figure underMe = GameView.board().getFigure(move);
            if (underMe.player == Game.getPlayer() || underMe instanceof King) {
                setPosition(positionPX);
                return false;
            } else {
                underMe.setDeath();
            }
        }
        this.setPosition(new Point(move.getI() * FIGURE_SIZE, move.getJ() * FIGURE_SIZE));
        this.afterSuccessFullMoveAction();
        return true;
    }

    private void setDeath() {

        this.death = true;

        if (player == 1) {
            GameView.board().getChildren().remove(this);
            Game.getGraveyardBlack().getChildren().add(this);

        } else {
            GameView.board().getChildren().remove(this);
            Game.getGraveyardWhite().getChildren().add(this);
        }


        setPrefSize(50, 50);
        setStyle(getStyle() + "-fx-background-size: 50px;");
        setDisable(true);
        GameView.board().removeFigure(this);

    }

    private List<ArrayPoint> possibleCoordinatesBeforeMarking;

    public void markPossibleWays() {
        possibleCoordinatesBeforeMarking = getPossibleCoordinates();

        if (this instanceof King) {
            GameView.board().markDangerousPaths(this.getPlayer(), this);
        }
        ChessBoardTile[][] tiles = GameView.board().getTiles();

        for (ArrayPoint p : possibleCoordinatesBeforeMarking) {
            if (!tiles[p.getI()][p.getJ()].isAlreadyMarked()) {
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
        GameView.board().postMoveProcessing(this.player);

    }

    public int getPlayer() {
        return player;
    }

    public ArrayPoint getPosition() {
        return position;
    }

    public Point getPositionPx() {
        return positionPX;
    }

    public boolean isDeath() {
        return death;
    }

    private void setPosition(Point position) {
        setLayoutX(position.getX());
        setLayoutY(position.getY());
        positionPX = position;
        this.position = new ArrayPoint((int) position.getX() / FIGURE_SIZE, (int) position.getY() / FIGURE_SIZE);
    }

    @Override
    public String toString() {
        return player + "_" + itemID;
    }

    public abstract List<ArrayPoint> getPossibleCoordinates();

}