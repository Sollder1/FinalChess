package de.sollder1.engine.facade.exceptions;

public class NoSuchFigureIdRegisteredException extends Exception{
    public NoSuchFigureIdRegisteredException() {
        super("There was no Figure associated with the given Id found in this Session.");
    }
}
