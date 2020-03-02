package de.sollder1.engine.internals.state;

import de.sollder1.engine.facade.externaltypes.FigureCode;
import de.sollder1.engine.facade.externaltypes.coordinate.Coordinate;
import de.sollder1.engine.facade.externaltypes.coordinate.CoordinateFigure;
import de.sollder1.engine.facade.externaltypes.coordinate.CoordinateFigureTyped;
import de.sollder1.engine.facade.frontend.Game;
import de.sollder1.engine.internals.state.figures.*;
import de.sollder1.engine.internals.updates.Update;

import java.util.*;

public final class ChessBoard {

    private List<Figure> figures = new ArrayList<>();
    private Game game;
    private Player playerOne;
    private Player playerTwo;
    private Player currentPlayer;

    private PieceIdCounter pieceIdCounter;

    public ChessBoard(Game game, Player.Number startingPlayer) {

        playerOne = new Player(Player.Number.ONE);
        playerTwo = new Player(Player.Number.TWO);
        this.currentPlayer = startingPlayer == Player.Number.ONE ? playerOne : playerTwo;
        this.game = game;
        initFigures();

        this.pieceIdCounter = new PieceIdCounter();

    }

    private void initFigures() {

        figures.add(new King(new Coordinate(4, 0), 0, this, playerOne));
        figures.add(new King(new Coordinate(4, 7), 1, this, playerTwo));
        pieceIdCounter.incrementBoth(FigureCode.KI, 2);

        figures.add(new Queen(new Coordinate(3, 0), 1, this, playerOne));
        figures.add(new Queen(new Coordinate(3, 7), 1, this, playerTwo));
        pieceIdCounter.incrementBoth(FigureCode.QN, 2);

        figures.add(new Rook(new Coordinate(0, 0), 0, this, playerOne));
        figures.add(new Rook(new Coordinate(7, 0), 1, this, playerOne));
        figures.add(new Rook(new Coordinate(0, 7), 0, this, playerTwo));
        figures.add(new Rook(new Coordinate(7, 7), 1, this, playerTwo));
        pieceIdCounter.incrementBoth(FigureCode.RO, 2);

        figures.add(new Bishop(new Coordinate(2, 0), 0, this, playerOne));
        figures.add(new Bishop(new Coordinate(5, 0), 1, this, playerOne));
        figures.add(new Bishop(new Coordinate(2, 7), 0, this, playerTwo));
        figures.add(new Bishop(new Coordinate(5, 7), 1, this, playerTwo));
        pieceIdCounter.incrementBoth(FigureCode.BI, 2);

        figures.add(new Knight(new Coordinate(1, 0), 0, this, playerOne));
        figures.add(new Knight(new Coordinate(6, 0), 1, this, playerOne));
        figures.add(new Knight(new Coordinate(1, 7), 0, this, playerTwo));
        figures.add(new Knight(new Coordinate(6, 7), 1, this, playerTwo));
        pieceIdCounter.incrementBoth(FigureCode.KN, 2);

        for (int i = 0; i < 8; i++) {
            figures.add(new Pawn(new Coordinate(i, 1), i, this, playerOne));
            figures.add(new Pawn(new Coordinate(i, 6), i, this, playerTwo));
            pieceIdCounter.incrementBoth(FigureCode.PA);
        }

    }

    public Optional<Figure> getFigure(Coordinate position) {
        //There can only be one at a Certain Position at a certain Time.
        return figures.stream().filter(figure -> figure.getFigurePosition().equals(position)).findFirst();
    }

    public Optional<Figure> getFigure(FigureId toSearch) {
        return figures.stream().filter(figure -> figure.getFigureId().equals(toSearch)).findFirst();
    }

    public Set<CoordinateFigureTyped> getAllPossibleMoves(Player.Number player) {
        Set<CoordinateFigureTyped> result = new HashSet<>();
        figures.stream().filter(figure -> figure.getPlayer().getPlayerNumber() == player)
                .forEach(figure -> result.addAll(figure.getPossibleMoves()));

        return result;
    }

    public Update moveFigure(CoordinateFigure move) {
        //TODO: IMPLEMENTIEREN!
        return null;
    }

    //TODO: Not possible the King is Matt OR an Pawn was not Interchanged
    public boolean changePlayer() {



        currentPlayer = currentPlayer.getPlayerNumber() == Player.Number.ONE ? playerOne : playerTwo;

        return true;

        //TODO: PostMove Stuff like Move restrictions for the Player who will be an der Reihe after this MEthod.
    }


}
