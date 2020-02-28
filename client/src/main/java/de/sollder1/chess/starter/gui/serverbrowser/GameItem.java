package de.sollder1.chess.starter.gui.serverbrowser;

import javafx.beans.property.SimpleStringProperty;

public class GameItem {

    String gameName;
    int playerCount;
    boolean running;
    String gameId;

    public GameItem(String gameName, int playerCount, boolean running, String gameId) {
        this.gameName = gameName;
        this.playerCount = playerCount;
        this.running = running;
        this.gameId = gameId;
    }

    public static GameItem parse(String field) {
        String[] raw = field.split("!");

        int playerCount = Integer.parseInt(raw[1]);
        boolean running = raw[2].equals("t");

        return new GameItem(raw[0], playerCount, running, raw[3]);

    }


    public SimpleStringProperty gameNameProperty() {
        return new SimpleStringProperty(gameName);
    }

    public SimpleStringProperty playerCountProperty() {
        return new SimpleStringProperty(playerCount + "/2");
    }

    public SimpleStringProperty isRunningProperty() {
        return new SimpleStringProperty(running ? "Ja" : "Nein");
    }





}
