package de.sollder1.server.game;

import de.sollder1.server.user.User;

public class Game {

    String gameName;
    int playerCount;
    boolean running;
    int gameId;
    User[] users;

    public Game(int gameId) {
        this.gameName = "Autogenerated";
        this.playerCount = 0;
        this.running = false;
        this.gameId = gameId;
        this.users = new User[2];
    }

    public Game(int gameId, String gameName, User creatingUser) {
        this.gameName = gameName;
        this.playerCount = 1;
        this.running = false;
        this.gameId = gameId;
        this.users = new User[2];
        users[0] = creatingUser;

    }

    public String getGameName() {
        return gameName;
    }

    public int getPlayerCount() {
        return playerCount;
    }

    public boolean isRunning() {
        return running;
    }

    public int getGameId() {
        return gameId;
    }

    public String marshal(){
        return this.getGameName() + "!" + this.getPlayerCount() + "!" + (this.isRunning() ? "t" : "f") + "!" + this.getGameId();
    }

}
