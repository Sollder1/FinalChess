package de.sollder1.chess.connection;

import de.sollder1.common.parsing.ParsedMessage;
import de.sollder1.common.parsing.Parser;
import de.sollder1.common.util.Logger;

public class MessageInterpreterCommand {

    /**
     * Versucht die gegebene Nachricht message zu parsen und schließlich zu interpretieren.
     * @param message Die Nachricht, welche es zu verarbeiten gilt.
     * @param parser Die Parser Implementierung
     */
    public static void parse(String message, Parser parser){

        try{
            interpret(parser.parseMessage(message));
        }catch (Exception e){
            Logger.logE(e);
        }

    }


    private static void interpret(ParsedMessage parsedMessage){

        Logger.logI(parsedMessage.toString());

        switch (parsedMessage.getCommand()){

            //logoutSuccess

        }

    }



}
