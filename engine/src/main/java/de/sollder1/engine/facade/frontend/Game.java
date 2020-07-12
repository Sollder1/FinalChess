package de.sollder1.engine.facade.frontend;

import de.sollder1.engine.facade.exceptions.NoSuchFigureIdRegisteredException;
import de.sollder1.engine.facade.externaltypes.FigureCode;
import de.sollder1.engine.facade.externaltypes.coordinate.CoordinateFigure;
import de.sollder1.engine.facade.externaltypes.coordinate.CoordinateFigureTyped;
import de.sollder1.engine.facade.externaltypes.coordinate.CoordinateTyped;
import de.sollder1.engine.internals.state.ChessBoard;
import de.sollder1.engine.internals.state.pojos.FigureId;
import de.sollder1.engine.internals.state.pojos.Player;
import de.sollder1.engine.internals.state.pojos.Settings;
import de.sollder1.engine.internals.state.figures.Figure;
import de.sollder1.engine.internals.updates.Update;

import java.util.Optional;
import java.util.Set;

public abstract class Game {

    protected Settings settings;
    protected ChessBoard chessBoard;

    //Optional: Chessclocks (Depends on Settings)

    protected Game(Settings settings, Player.Number startingPlayer) {

        this.chessBoard = new ChessBoard(this, startingPlayer);
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
    public abstract Update sendMove(CoordinateFigureTyped move);

    //public abstract Update getMultiMove();

    //public abstract Update getAiMove();

    /**
     * Muss selbstständig aufgerufen werden, nachdem
     * sendMove signalisiert hat das ein Pawn am Rand steht.
     *
     */
    public abstract Update interchangePawn(String figureId, FigureCode interChange);

    public Set<CoordinateFigureTyped> getPossibleMovesForFigure(FigureId figureId) throws NoSuchFigureIdRegisteredException {

        Optional<Figure> correspondingFigure = chessBoard.getFigure(figureId);

        if(chessBoard.getFigure(figureId).isPresent()){

            return correspondingFigure.get().getPossibleMoves();
        }else {
            throw new NoSuchFigureIdRegisteredException();
        }

    }

    /**
     * Also Includes the Moves who go threw the King and Possible Pawn kill Moves.
     * The later is because these are hypothetical Moves. One may try to put his King to
     * such a Position, which would not be permitted!
     *
     *
     * @param player
     * @return
     */
    public Set<CoordinateFigureTyped> getAllPossibleMoves(Player.Number player) {
        return chessBoard.getAllPossibleMoves(player);
    }

    /**
     * Convenience Function the get the Moves whose are directly dangerous
     * to the King and are thereby not allowed to be performed.
     * TODO: Implement!
     * @param player The Player in Question
     * @return An Set of Coordinates the King is not allowed to take.
     */
    public Set<CoordinateTyped> getCriticalKingMoves(Player.Number player) {
        return null;
    }


}
