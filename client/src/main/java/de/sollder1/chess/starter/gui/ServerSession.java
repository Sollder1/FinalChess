package de.sollder1.chess.starter.gui;

public class ServerSession {

    private final String clientId;
    private final int maxGames;
    private boolean loggedIn;

    public ServerSession() {
        clientId = "NO";
        maxGames = -1;
        loggedIn = false;
    }

    public ServerSession(String clientId, int maxGames) {
        this.clientId = clientId;
        this.loggedIn = true;
        this.maxGames = maxGames;
    }

    public String getClientId() {
        return clientId;
    }

    public int getMaxGames() {
        return maxGames;
    }

    public boolean isLoggedIn() {
        return loggedIn;
    }

    public void setLoggedIn(boolean loggedIn) {
        this.loggedIn = loggedIn;
    }
}
