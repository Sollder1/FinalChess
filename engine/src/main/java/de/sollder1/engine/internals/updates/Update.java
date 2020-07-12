package de.sollder1.engine.internals.updates;

import de.sollder1.engine.internals.state.pojos.GameState;

import java.util.List;

//Pojo Type for an Update.
public class Update {

    private boolean success;
    private UpdateError error; //Optional
    private List<UpdateItem> updates;
    private GameState gameStateAfterMove;
    private String interchangePawnFigureId;

    /**
     * The Call to the Game Facade Class was not Successful.
     * You may not Use any other fields if this Contructor was used
     * and thereby the success Flag was set to false.
     */
    public Update() {
        success = false;
        error = UpdateError.NOT_SPECIFIED;
    }

    /**
     * The Call to the Game Facade Class was not Successful.
     * You may not Use any other fields if this Contructor was used
     * and thereby the success Flag was set to false.
     * In this Case there is also an UpdateError specified.
     */
    public Update(UpdateError updateError) {
        success = false;
        error = updateError;
    }

    /**
     * The Call to the Game Facade Class was Successful,
     * but there was not need for a Pawn to be interchanged.
     *
     * @param updates The Updates the Caller has to Present
     * @param gameStateAfterMove THe Current GameState, after a successful move.
     */
    public Update(List<UpdateItem> updates, GameState gameStateAfterMove) {
        success = true;
        this.updates = updates;
        this.gameStateAfterMove = gameStateAfterMove;
    }

    /**
     * If an Pawn needs to be interchanged.
     * @param updates The Updates the Caller has to Present.
     * @param gameStateAfterMove THe Current GameState, after a successful move.
     * @param interchangePawnFigureId Identifies the Pawn to be interchanged.
     */
    public Update(List<UpdateItem> updates, GameState gameStateAfterMove, String interchangePawnFigureId) {
        success = true;
        this.updates = updates;
        this.gameStateAfterMove = gameStateAfterMove;
        this.interchangePawnFigureId = interchangePawnFigureId;
    }

    //Only Getters, because the Object is only used for Transportation in one Way.
    public boolean isSuccess() {
        return success;
    }

    public UpdateError getError() {
        return error;
    }

    public List<UpdateItem> getUpdates() {
        return updates;
    }

    public GameState getGameStateAfterMove() {
        return gameStateAfterMove;
    }

    public String getInterchangePawnFigureId() {
        return interchangePawnFigureId;
    }

}
