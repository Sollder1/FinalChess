package de.sollder1.engine.facade;

import de.sollder1.engine.facade.frontend.Game;
import de.sollder1.engine.facade.frontend.GamePvP;
import de.sollder1.engine.internals.state.Player;
import de.sollder1.engine.internals.state.Settings;

public final class EngineFrontend {

    private EngineFrontend(){}

    /**
     * Returns a Game Object with the necessary implementations
     * for an local Player vs. Player Game of Chess. <br>
     *
     * The Following Methods of the Game Class may not be implemented and will throw
     * an {@code UnsupportedOperationException} if called by an Instance of Game created by this
     * Method: <br>
     *
     * TODO: List them!
     *
     * @return An PvP Capable Instance of the Game Facade.
     */
    public static Game getGamePvP(Player.Number startingPlayer){
        return new GamePvP(Settings.DEFAULT, startingPlayer);
    }

    public static Game getGamePvP(Settings settings, Player.Number startingPlayer){
        return new GamePvP(settings, startingPlayer);
    }

    public static Settings getSettingsInstance(){
        return new Settings();
    }

    public static Settings getChessClock(){
        return new Settings();
    }

    public static Game getGameAivP(){
        throw new UnsupportedOperationException();
    }

    public static Game getPvpMulti(){
        throw new UnsupportedOperationException();
    }


}
