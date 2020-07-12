package de.sollder1.engine.facade.externaltypes.coordinate;

/**
 * Mainly used for the Implementation of the get POSSIBLE MOVES Features.
 * These Types may be combined and used only by {@code CoordinateTyped}
 * This Typ may be expanded.
 */
public enum CoordinateType {
    MOVE,                   //Indicates an normal, immediately available move
    ROCHADE,                //Indicates an Rochade Move
    KILL,                   //Indicates an Move which could kill an enemies figure
    TRANCIENT,              //Indicates an Move only possible if there would not be an Enmy or Friend in the Way!
    KING_NOT_ALLOWED,       //Indicates an Move which may not be taken by an King, because it directly endangers him,
                            //but not Necessarily an move. This would be indicated by also setting the MOVE, ROCHADE or KILL Flag.
    PAWN_CHANGE,            //OPTIONAL: Indicates that the move would give the Option of interchanging an Pawn (May be important for the AI)
}
