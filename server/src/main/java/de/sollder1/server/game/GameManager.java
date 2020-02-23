package de.sollder1.server.game;

import de.sollder1.server.user.User;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class GameManager {

    public static final int MAX_GAMES = 2;

    private static List<Game> gameList;
    private static int gameIdCounter;
    private static int currentlyRunningGames;

    static {
        gameList = new ArrayList<>();
        gameIdCounter = 0;
        addGame();
    }

    public static synchronized List<Game> getGameList() {
        return gameList;
    }

    //Add Empty game
    public static synchronized Game addGame() {
        if(currentlyRunningGames < MAX_GAMES){
            Game newGame = new Game(++gameIdCounter);
            currentlyRunningGames++;
            gameList.add(newGame);
            return newGame;
        }
        return null;
    }

    //Add Game with one user
    public static synchronized Optional<Game> addGame(String gameName, User creatingUser) {
        if(currentlyRunningGames < MAX_GAMES){
            Game newGame = new Game(++gameIdCounter, gameName, creatingUser);
            currentlyRunningGames++;
            gameList.add(newGame);
            return Optional.of(newGame);
        }
        return Optional.empty();
    }

}
