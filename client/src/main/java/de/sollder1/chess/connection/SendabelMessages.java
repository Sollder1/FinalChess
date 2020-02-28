package de.sollder1.chess.connection;

import de.sollder1.common.parsing.OwnParser;

public class SendabelMessages {

    private static final String SERVER_NOT_REACHABLE = "SERVER_NOT_REACHABLE";

    public static void login (int clientport, String username){

        String reply = Sender.sendMessage("login;" + clientport + ":" + username);
        MessageInterpreterReply.parse(reply, new OwnParser());

    }

    public static void currentGames (){

        String reply = Sender.sendMessage("currentGames;");
        MessageInterpreterReply.parse(reply, new OwnParser());

    }


    public static void createGame(String gameName, String clientId) {

        String reply = Sender.sendMessage("createGame;" + gameName + ":" + clientId);
        MessageInterpreterReply.parse(reply, new OwnParser());

    }
}
