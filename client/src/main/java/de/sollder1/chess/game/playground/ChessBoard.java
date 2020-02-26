package de.sollder1.chess.game.playground;

import de.sollder1.chess.game.chessfigures.Figure;
import de.sollder1.chess.game.chessfigures.King;
import de.sollder1.chess.game.chessfigures.Bishop;
import de.sollder1.chess.game.chessfigures.Knight;
import de.sollder1.chess.game.chessfigures.Pawn;
import de.sollder1.chess.game.chessfigures.Queen;
import de.sollder1.chess.game.chessfigures.Rook;
import de.sollder1.chess.game.gui.view.GameView;
import de.sollder1.chess.game.helpObjects.Point;
import javafx.scene.layout.Pane;

import java.util.Arrays;

public class ChessBoard extends Pane {

    private int size;
    private Figure[][] uiFigures = new Figure[8][8];
    private ChessBoardTile[][] tiles = new ChessBoardTile[8][8];

    private static ChessBoard instance;

    public static ChessBoard initInstance(int size) {

        ChessBoard.instance = new ChessBoard(size);
        instance.fillBoardWithTiles();
        instance.fillBoardWithFigures();
        System.out.println(instance.toString());
        return instance;
    }

    //Size as var for heigth and width
    public ChessBoard(int size) {

        //outsource to CSS
        setStyle("-fx-background-color: gray");

        //Size gets set
        setPrefSize(size, size);
        setMinSize(Pane.USE_PREF_SIZE, Pane.USE_PREF_SIZE);
        setMaxSize(Pane.USE_PREF_SIZE, Pane.USE_PREF_SIZE);

        //Position in the mainPane (scalable)
        setLayoutX((GameView.mainPane.getPrefWidth() - size) - 50);
        setLayoutY((GameView.mainPane.getPrefHeight() - size) / 2);

        this.size = size;

    }

    //Puts the Tiles on the Chessboard
    public void fillBoardWithTiles() {

        boolean lightColor = true;

        ChessBoardTile tilesOneDimArray[] = new ChessBoardTile[64];

        for (int i = 1; i <= 64; i++) {

            int x = ((i - 1) / 8) * (size / 8);
            int y = ((i - 1) % 8) * (size / 8);

            ChessBoardTile ncbt = (new ChessBoardTile(lightColor, size / 8, new Point(x, y)));

            getChildren().add(ncbt);
            tilesOneDimArray[i - 1] = ncbt;

			lightColor = !lightColor;

            if (i % 8 == 0) {
				lightColor = !lightColor;
            }
        }

        arrayToTwoDim(tilesOneDimArray);


    }

    private void arrayToTwoDim(ChessBoardTile[] oneDim) {

        for (int i = 0; i < 64; i++) {

            tiles[i / 8][i % 8] = oneDim[i];

        }
    }

    public void fillBoardWithFigures() {

        ////Pawns adden(black):
        //for (int i = 0; i < 8; i++) {
//
        //    uiFigures[i][1] = new Pawn(size / 8, i, new Point(i * (size / 8), size / 8), 1);
        //    getChildren().add(uiFigures[i][1]);
//
        //}
//
        ////Pawns adden(white):
        //for (int i = 0; i < 8; i++) {
//
        //    uiFigures[i][6] = new Pawn(size / 8, i, new Point(i * (size / 8), 6 * (size / 8)), 2);
        //    getChildren().add(uiFigures[i][6]);
//
        //}

        //Rooks adden(black):
        uiFigures[0][0] = new Rook(size / 8, 1, new Point(0 * (size / 8), 0 * (size / 8)), 1);
        getChildren().add(uiFigures[0][0]);
        uiFigures[7][0] = new Rook(size / 8, 1, new Point(7 * (size / 8), 0 * (size / 8)), 1);
        getChildren().add(uiFigures[7][0]);

        //Rooks adden(white):
        uiFigures[0][7] = new Rook(size / 8, 1, new Point(0 * (size / 8), 7 * (size / 8)), 2);
        getChildren().add(uiFigures[0][7]);
        uiFigures[7][7] = new Rook(size / 8, 1, new Point(7 * (size / 8), 7 * (size / 8)), 2);
        getChildren().add(uiFigures[7][7]);


        //Bishops adden(black):
        //uiFigures[2][0] = new Bishop(size / 8, 1, new Point(2 * (size / 8), 0 * (size / 8)), 1);
        //getChildren().add(uiFigures[2][0]);
        //uiFigures[5][0] = new Bishop(size / 8, 1, new Point(5 * (size / 8), 0 * (size / 8)), 1);
        //getChildren().add(uiFigures[5][0]);
//
        ////Bishops adden(white):
        //uiFigures[2][7] = new Bishop(size / 8, 1, new Point(2 * (size / 8), 7 * (size / 8)), 2);
        //getChildren().add(uiFigures[2][7]);
        //uiFigures[5][7] = new Bishop(size / 8, 1, new Point(5 * (size / 8), 7 * (size / 8)), 2);
        //getChildren().add(uiFigures[5][7]);
//
//
        ////Knigths adden(black):
        //uiFigures[1][0] = new Knight(size / 8, 1, new Point(1 * (size / 8), 0 * (size / 8)), 1);
        //getChildren().add(uiFigures[1][0]);
        //uiFigures[6][0] = new Knight(size / 8, 1, new Point(6 * (size / 8), 0 * (size / 8)), 1);
        //getChildren().add(uiFigures[6][0]);
//
        ////Knigths adden(white):
        //uiFigures[1][7] = new Knight(size / 8, 1, new Point(1 * (size / 8), 7 * (size / 8)), 2);
        //getChildren().add(uiFigures[1][7]);
        //uiFigures[6][7] = new Knight(size / 8, 1, new Point(6 * (size / 8), 7 * (size / 8)), 2);
        //getChildren().add(uiFigures[6][7]);
//
//
        ////Queen adden(black)
        //uiFigures[3][0] = new Queen(size / 8, 1, new Point(3 * (size / 8), 0 * (size / 8)), 1);
        //getChildren().add(uiFigures[3][0]);


        //Queen adden(white)
        uiFigures[3][7] = new Queen(size / 8, 1, new Point(3 * (size / 8), 7 * (size / 8)), 2);
        getChildren().add(uiFigures[3][7]);


        //King adden(black)
        uiFigures[4][0] = new King(size / 8, 1, new Point(4 * (size / 8), 0 * (size / 8)), 1);
        getChildren().add(uiFigures[4][0]);


        //King adden(white)
        uiFigures[4][7] = new King(size / 8, 1, new Point(4 * (size / 8), 7 * (size / 8)), 2);
        getChildren().add(uiFigures[4][7]);

    }

    @Override
    public String toString() {

        for (int i = 0; i< uiFigures.length; i++) {
            for (int j = 0; j< uiFigures.length; j++) {
                System.out.print(uiFigures[j][i] == null ? "_null_" + " ; " : uiFigures[j][i] + " ; ");
            }
            System.out.println();
        }

        return "";
    }

    public static ChessBoard getInstance() {
        return instance;
    }

    public int getSize() {
        return size;
    }

    public Figure[][] getUiFigures() {
        return uiFigures;
    }

    public ChessBoardTile[][] getTiles() {
        return tiles;
    }

    public static boolean isFigureUnderMe(Point position) {

        Figure underMe = ChessBoard.getInstance().getUiFigures()[(int) (position.getX() / Figure.FIGURE_SIZE)][(int) (position.getY() / Figure.FIGURE_SIZE)];
        return underMe != null;

    }

    public static Figure getFigureUnderMe(Point position) {
        return ChessBoard.getInstance().getUiFigures()[(int) (position.getX() / Figure.FIGURE_SIZE)][(int) (position.getY() / Figure.FIGURE_SIZE)];
    }

    public static Figure getFigure(int i, int j){
        return instance.getUiFigures()[i][j];
    }

}
