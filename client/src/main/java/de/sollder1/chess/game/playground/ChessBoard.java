package de.sollder1.chess.game.playground;

import de.sollder1.chess.game.chessfigures.Figure;
import de.sollder1.chess.game.chessfigures.King;
import de.sollder1.chess.game.chessfigures.Bishop;
import de.sollder1.chess.game.chessfigures.Knight;
import de.sollder1.chess.game.chessfigures.Pawn;
import de.sollder1.chess.game.chessfigures.Queen;
import de.sollder1.chess.game.chessfigures.Rook;
import de.sollder1.chess.game.gui.view.GameView;
import de.sollder1.chess.game.helpObjects.ArrayPoint;
import de.sollder1.chess.game.helpObjects.Point;
import de.sollder1.chess.game.helpObjects.Utils;
import de.sollder1.chess.starter.gui.settings.SettingsPojo;
import javafx.scene.layout.Pane;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ChessBoard extends Pane {

    private int size;
    private List<Figure> uiFigures = new ArrayList<>();
    private ChessBoardTile[][] tiles = new ChessBoardTile[8][8];

    private static ChessBoard instance;

    public static ChessBoard initInstance(int size) {

        ChessBoard.instance = new ChessBoard(size);
        instance.fillBoardWithTiles();
        instance.fillBoardWithFigures();

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

    public static List<Figure> getUiFigures() {
        return instance.uiFigures;
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

        //Pawns adden(black):
        for (int i = 0; i < 8; i++) {
            uiFigures.add(new Pawn(i, new ArrayPoint(i, 1), 1));
            getChildren().add(getFigure(i, 1));
        }

        //Pawns adden(white):
        for (int i = 0; i < 8; i++) {
            uiFigures.add(new Pawn(i, new ArrayPoint(i, 6), 2));
            getChildren().add(getFigure(i, 6));
        }

        //Rooks adden(black):
        uiFigures.add(new Rook(1, new ArrayPoint(0, 0), 1));
        getChildren().add(getFigure(0, 0));
        uiFigures.add(new Rook(2, new ArrayPoint(7, 0), 1));
        getChildren().add(getFigure(7, 0));

        //Rooks adden(white):
        uiFigures.add(new Rook(1, new ArrayPoint(0, 7), 2));
        getChildren().add(getFigure(0, 7));
        uiFigures.add(new Rook(2, new ArrayPoint(7, 7), 2));
        getChildren().add(getFigure(7, 7));

        //Bishops adden (black):
        uiFigures.add(new Bishop(1, new ArrayPoint(2, 0), 1));
        getChildren().add(getFigure(2, 0));
        uiFigures.add(new Bishop(2, new ArrayPoint(5, 0), 1));
        getChildren().add(getFigure(5, 0));

        //Bishops adden(white):
        uiFigures.add(new Bishop(1, new ArrayPoint(2, 7), 2));
        getChildren().add(getFigure(2, 7));
        uiFigures.add(new Bishop(2, new ArrayPoint(5, 7), 2));
        getChildren().add(getFigure(5, 7));

        //Knigths adden(black):
        uiFigures.add(new Knight(1, new ArrayPoint(1, 0), 1));
        getChildren().add(getFigure(1, 0));
        uiFigures.add(new Knight(2, new ArrayPoint(6, 0), 1));
        getChildren().add(getFigure(6, 0));

        //Knigths adden(white):
        uiFigures.add(new Knight(1, new ArrayPoint(1, 7), 2));
        getChildren().add(getFigure(1, 7));
        uiFigures.add(new Knight(2, new ArrayPoint(6, 7), 2));
        getChildren().add(getFigure(6, 7));

        //Queen adden(black)
        uiFigures.add(new Queen(1, new ArrayPoint(3, 0), 1));
        getChildren().add(getFigure(3, 0));
        //Queen adden(white)
        uiFigures.add(new Queen(1, new ArrayPoint(3, 7), 2));
        getChildren().add(getFigure(3, 7));

        //King adden(black)
        uiFigures.add(new King(1, new ArrayPoint(4, 0), 1));
        getChildren().add(getFigure(4, 0));
        //King adden(white)
        uiFigures.add(new King(1, new ArrayPoint(4, 7), 2));
        getChildren().add(getFigure(4, 7));
    }

    public static ChessBoard getInstance() {
        return instance;
    }

    public int getSize() {
        return size;
    }

    public ChessBoardTile[][] getTiles() {
        return tiles;
    }

    public static boolean isFigureUnderMe(Point position) {

        Figure underMe = getFigure(position);
        return underMe != null;

    }

    public static Figure getFigure(int i, int j) {
        return getFigure(new ArrayPoint(i, j));
    }

    public static Figure getFigurePx(int x, int y) {
        return getFigure(new Point(x, y));
    }


    public static Figure getFigure(ArrayPoint point) {
        for (Figure figure : instance.uiFigures) {
            if (figure.getPosition().equals(point) && !figure.isDeath()) {
                return figure;
            }
        }

        return null;
    }

    public static Figure getFigure(Point point) {
        for (Figure figure : instance.uiFigures) {
            if (figure.getPositionPx().equals(point) && !figure.isDeath()) {
                return figure;
            }
        }

        return null;
    }

    public static void removeFigure(Figure figure) {
        instance.uiFigures.remove(figure);
        instance.getChildren().remove(figure);
    }

    public static void addFigure(Figure figure) {
        instance.uiFigures.add(figure);
        instance.getChildren().add(figure);
    }

    //Prüfen ob der König im Matt steht.
    public static void postMoveProcessing(int player) {
        List<Figure> matts = new ArrayList<>();

        instance.uiFigures.stream().filter(figure -> figure.getPlayer() == player).forEach(
                figure -> {
                    for (ArrayPoint p : figure.getPossibleCoordinates()) {
                        if (getFigure(p) instanceof King) {
                            matts.add(figure);
                            ((King) getFigure(p)).mustMoveNextTurn();
                        }
                    }
                }
        );

        System.out.println(matts);

    }

    public static void markKingWays(int player, Figure king) {

        List<ArrayPoint> enemyPaths = getEnemyPaths(player, false);

        if(SettingsPojo.isActivateKingMarkings()){
            enemyPaths.stream().forEach(path -> {
                instance.tiles[path.getI()][path.getJ()].mark("yellow");
            });
        }

        //The King cannot go this paths!
        List<ArrayPoint> criticalPaths = getCriticalPaths(enemyPaths, ((King) (king)).getPossibleCoordinatesWithoutFilter());

        criticalPaths.stream().forEach(enemyPath -> {
            instance.tiles[enemyPath.getI()][enemyPath.getJ()].deMark();
            instance.tiles[enemyPath.getI()][enemyPath.getJ()].mark("orange");
        });


    }

    public static List<ArrayPoint> getEnemyPaths(int player, boolean ignoreKing) {
        List<ArrayPoint> enemyPaths = new ArrayList<>();

        instance.uiFigures.stream().filter(figure -> figure.getPlayer() != player).forEach(figure -> {

            if(figure instanceof King && ignoreKing) {
                //
            }else if (figure instanceof Pawn) {
                enemyPaths.addAll(((Pawn) figure).getKillingCoordinates());
            } else {
                enemyPaths.addAll(figure.getPossibleCoordinates().stream().filter(point -> point.getColorOfTheTile().equals("green")).collect(Collectors.toList()));
            }
        });

        return enemyPaths;
    }

    public static List<ArrayPoint> getCriticalPaths(List<ArrayPoint> enemyPaths, List<ArrayPoint> possibleCoordinates) {
        List<ArrayPoint> criticalPaths = new ArrayList<>();

        possibleCoordinates.forEach(kingPath -> {
            enemyPaths.stream().filter(enemyPath -> enemyPath.equals(kingPath)).forEach(criticalPaths::add);
        });

        return criticalPaths;
    }


    public static void deMarkEveryTile() {
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                instance.tiles[i][j].deMark();
            }
        }
    }

    public static boolean isAnKingToMove(int player) {
        return instance.uiFigures.stream().anyMatch(figure -> figure.getPlayer() == player && figure instanceof King && ((King) figure).isMustMove());
    }


    public static boolean gameOver() {

        return instance.uiFigures.stream().filter(figure -> figure instanceof King).anyMatch(figure -> {

            King king = (King) figure;
            List<ArrayPoint> moves = king.getPossibleCoordinates();
            if (moves.isEmpty() && king.isMustMove()) {
                System.err.println("Das Spiel Endet, König von Spieler " + king.getPlayer() + " ist Schachmatt!");
                Utils.showWinDialog(king.getPlayer() == 1 ? 2 : 1, true);
                return true;
            }
            return false;

        });
    }
}
