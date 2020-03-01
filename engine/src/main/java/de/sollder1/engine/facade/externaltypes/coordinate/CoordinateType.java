package de.sollder1.engine.facade.externaltypes.coordinate;

/**
 * Mainly used for the Implementation of the get POSSIBLE MOVES Features.
 * These Types may be combined and used only by {@code CoordinateTyped}
 * This Typ may be expanded.
 */
public enum CoordinateType {
    MOVE,                   //Indicates an Normal move without any Sideeffects
    ROCHADE,                //Indicates an Rochade Move
    KILL,                   //Indicates an Move which could kill an enemies figure
    KING_NOT_ALLOWED,       //Indicates an Move which may not be taken by an King, because it directly endangers him.
    PAWN_CHANGE,            //OPTIONAL: Indicates that the move would give the Option of interchanging an Pawn (May be important for the AI)


}
