package de.sollder1.engine.internals.state.pojos;

import de.sollder1.engine.facade.externaltypes.FigureCode;

import java.util.Objects;

public class FigureId {

    int pieceId;
    FigureCode figureCode;
    Player.Number player;

    public FigureId(int pieceId, FigureCode figureCode, Player.Number player) {
        this.pieceId = pieceId;
        this.figureCode = figureCode;
        this.player = player;
    }

    public int getPieceId() {
        return pieceId;
    }

    public FigureCode getFigureCode() {
        return figureCode;
    }

    public Player.Number getPlayer() {
        return player;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FigureId figureId = (FigureId) o;
        return pieceId == figureId.pieceId &&
                figureCode == figureId.figureCode &&
                player == figureId.player;
    }

    @Override
    public int hashCode() {
        return Objects.hash(pieceId, figureCode, player);
    }

    @Override
    public String toString() {
        return getFigureCode() + "_" + getPieceId() + "_" + player;
    }
}
