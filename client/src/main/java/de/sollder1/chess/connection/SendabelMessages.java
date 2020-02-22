package de.sollder1.chess.connection;

import de.sollder1.common.parsing.OwnParser;
import de.sollder1.common.util.Logger;

public class SendabelMessages {

    private static final String SERVER_NOT_REACHABLE = "SERVER_NOT_REACHABLE";

    public static boolean login (String clientport, String username){

        int parsedPort;

        try{
            parsedPort = Integer.parseInt(clientport);
        }catch (Exception e){
            Logger.logE(e);
            return false;
        }

        String reply = Sender.sendMessage("login;" + parsedPort + ":" + username);

        return MessageInterpreterReply.parse(reply, new OwnParser());


    }

}
