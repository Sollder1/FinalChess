package de.sollder1.engine.internals.state;

public class Player {

    Number playerNumber;

    public Player(Number playerNumber) {
        this.playerNumber = playerNumber;
    }

    public enum Number{
        ONE, TWO
    }

}
