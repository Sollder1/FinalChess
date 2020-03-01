package de.sollder1.engine.internals.state;

import de.sollder1.engine.facade.externaltypes.FigureCode;
import de.sollder1.engine.internals.state.figures.Figure;

import java.util.HashMap;
import java.util.Map;

public class PieceIdCounter {

    private Map<FigureCode, Integer> pieceCounterOne;
    private Map<FigureCode, Integer> pieceCounterTwo;

    public PieceIdCounter() {

        pieceCounterOne = new HashMap<>();
        pieceCounterTwo = new HashMap<>();

        pieceCounterOne.put(FigureCode.PA, 0);
        pieceCounterOne.put(FigureCode.BI, 0);
        pieceCounterOne.put(FigureCode.KN, 0);
        pieceCounterOne.put(FigureCode.KI, 0);
        pieceCounterOne.put(FigureCode.QN, 0);
        pieceCounterOne.put(FigureCode.RO, 0);

        pieceCounterTwo.put(FigureCode.PA, 0);
        pieceCounterTwo.put(FigureCode.BI, 0);
        pieceCounterTwo.put(FigureCode.KN, 0);
        pieceCounterTwo.put(FigureCode.KI, 0);
        pieceCounterTwo.put(FigureCode.QN, 0);
        pieceCounterTwo.put(FigureCode.RO, 0);

    }

    public void increment(FigureCode key, Player.Number playerNumber) {
        increment(key, playerNumber, 1);
    }

    public void increment(FigureCode key, Player.Number playerNumber, int number) {
        if(playerNumber == Player.Number.ONE){
            pieceCounterOne.replace(key, pieceCounterOne.get(key) + number);
        }
    }

    public void incrementBoth(FigureCode key) {
        incrementBoth(key, 1);
    }

    public void incrementBoth(FigureCode key, int number) {
        increment(key, Player.Number.ONE, number);
        increment(key, Player.Number.TWO, number);
    }


    public int get(FigureCode key, Player.Number playerNumber) {
        return playerNumber == Player.Number.ONE ? pieceCounterOne.get(key) : pieceCounterTwo.get(key);
    }

    public int incrementAndGet(FigureCode key, Player.Number playerNumber) {
        increment(key, playerNumber, 1);
        return get(key, playerNumber);
    }


}
