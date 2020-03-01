package de.sollder1.engine.facade.frontend;

import de.sollder1.engine.facade.externaltypes.FigureCode;
import de.sollder1.engine.facade.externaltypes.coordinate.CoordinateFigure;
import de.sollder1.engine.facade.externaltypes.coordinate.CoordinateFigureTyped;
import de.sollder1.engine.facade.externaltypes.coordinate.CoordinateTyped;
import de.sollder1.engine.internals.state.ChessBoard;
import de.sollder1.engine.internals.state.Player;
import de.sollder1.engine.internals.state.Settings;
import de.sollder1.engine.internals.updates.Update;

import java.util.Set;

public abstract class Game {

    protected Settings settings;
    protected ChessBoard chessBoard;

    //Optional: Chessclocks (Depends on Settings)

    protected Game(Settings settings, int startingPlayer) {

        if(startingPlayer != 0 && startingPlayer != 1){
            throw new RuntimeException("This Player Number is not Supported");
        }

        this.chessBoard = new ChessBoard(this, startingPlayer == 1 ? Player.Number.ONE : Player.Number.TWO);
        this.settings = settings;

    }

    /**
     * Der Pawn Tausch wird hier in Update mitgeteilt,
     * die UI muss sich dann um eine Usereingabe kümmern und
     * sich darum kümmern die interchangePawn Methode zu rufen.
     * Vorher nimmt diese Methode keine weiteren Züge mehr an.
     * Dies wird im entsprechenden Palyer Objekt gespeichert.
     *
     * @return
     */
    public abstract Update sendMove(CoordinateFigure move);

    //public abstract Update getMultiMove();

    //public abstract Update getAiMove();

    /**
     * Muss selbstständig aufgerufen werden, nachdem
     * sendMove signalisiert hat das ein Pawn am Rand steht.
     *
     */
    public abstract Update interchangePawn(String figureId, FigureCode interChange);

    //TODO: IMPLEMENT
    public Set<CoordinateTyped> getPossibleMovesForFigure() {
        return null;
    }

    //TODO: IMPLEMENT
    public Set<CoordinateFigureTyped> getAllPossibleMoves(int playerNumber) {
        return null;
    }

    //TODO: IMPLEMENT
    public Set<CoordinateTyped> getCriticalKingMoves(int playerNumber) {
        return null;
    }


}
