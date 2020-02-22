package de.sollder1.chess.starter.gui;

import javafx.beans.property.SimpleStringProperty;

public class GameListItem {

    private final SimpleStringProperty gameName;
    private final SimpleStringProperty playerCount;
    private final SimpleStringProperty isRunning;

    public GameListItem(String gameName, String playerCount, String isRunning) {
        this.gameName = new SimpleStringProperty(gameName);
        this.playerCount = new SimpleStringProperty(playerCount);
        this.isRunning = new SimpleStringProperty(isRunning);
    }

    public String getGameName() {
        return gameName.get();
    }

    public SimpleStringProperty gameNameProperty() {
        return gameName;
    }

    public void setGameName(String gameName) {
        this.gameName.set(gameName);
    }

    public String getPlayerCount() {
        return playerCount.get();
    }

    public SimpleStringProperty playerCountProperty() {
        return playerCount;
    }

    public void setPlayerCount(String playerCount) {
        this.playerCount.set(playerCount);
    }

    public String getIsRunning() {
        return isRunning.get();
    }

    public SimpleStringProperty isRunningProperty() {
        return isRunning;
    }

    public void setIsRunning(String isRunning) {
        this.isRunning.set(isRunning);
    }
}
