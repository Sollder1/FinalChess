package de.sollder1.common.parsing;

import java.net.InetAddress;

/**
 * Schnittstelle, welche ein Parser erfüllen muss.
 */
public interface Parser {

    //Wenn keine IP nötig ist
    ParsedMessage parseMessage(String message) throws Exception;

    //Wenn eine IP nötig ist
    ParsedMessage parseMessage(String message, InetAddress ip) throws Exception;

}
