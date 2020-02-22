package de.sollder1.chess.connection;

import de.sollder1.chess.starter.gui.ServerBrowserController;
import de.sollder1.common.parsing.ParsedMessage;
import de.sollder1.common.parsing.Parser;
import de.sollder1.common.util.Logger;

public class MessageInterpreterReply {

    private static ServerBrowserController serverBrowserController;

    public static void init(ServerBrowserController serverBrowserController){
        MessageInterpreterReply.serverBrowserController = serverBrowserController;
    }



    /**
     * Versucht die gegebene Nachricht message zu parsen und schließlich zu interpretieren.
     * @param message Die Nachricht, welche es zu verarbeiten gilt.
     * @param parser Die Parser Implementierung
     * @return
     */
    public static boolean parse(String message, Parser parser){

        try{
            interpret(parser.parseMessage(message));
        }catch (Exception e){
            Logger.logE(e);
        }

        return false;
    }


    private static boolean interpret(ParsedMessage parsedMessage){

        Logger.logI(parsedMessage.toString());

        boolean success = parsedMessage.getCommand().split("_")[1].equals("Success");
        String type = parsedMessage.getCommand().split("_")[0];

        switch (type){

            case "login" : return login(success);
            //logoutSuccess

            default: return false;

        }

    }

    /**
     * DIE HANDLERMETHODEN DÜRFEN KEINE WEITEREN AUFFRUFE MACHEN.
     */
    private static boolean login(boolean success) {

        if(success){
            Logger.logI("Login success");
            return true;
        }else {

            return false;
        }

    }


}
