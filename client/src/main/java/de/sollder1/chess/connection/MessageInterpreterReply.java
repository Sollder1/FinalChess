package de.sollder1.chess.connection;

import de.sollder1.chess.starter.gui.serverbrowser.GameItem;
import de.sollder1.chess.starter.gui.serverbrowser.ServerSession;
import de.sollder1.chess.starter.gui.serverbrowser.ConnectionColor;
import de.sollder1.chess.starter.gui.serverbrowser.ServerBrowserController;
import de.sollder1.common.parsing.ParsedMessage;
import de.sollder1.common.parsing.Parser;
import de.sollder1.common.util.Logger;

import java.util.ArrayList;
import java.util.List;

public class MessageInterpreterReply {

    /**
     * Versucht die gegebene Nachricht message zu parsen und schließlich zu interpretieren.
     * @param message Die Nachricht, welche es zu verarbeiten gilt.
     * @param parser Die Parser Implementierung
     * @return
     */
    public static void parse(String message, Parser parser){

        try{
            interpret(parser.parseMessage(message), parser);
        }catch (Exception e){
            Logger.logE(e);
        }

    }


    private static void interpret(ParsedMessage parsedMessage, Parser parser){

        Logger.logI(parsedMessage.toString());

        if(parsedMessage.getCommand().equals("SERVER_NOT_REACHABLE")){
            serverNotReachable();
        }

        boolean success = parsedMessage.getCommand().split("_")[1].equals("Success");
        String type = parsedMessage.getCommand().split("_")[0];

        switch (type){

            case "login" : login(success, parsedMessage.getDataFields()); break;
            case "currentGames" : currentGames(success, parsedMessage, parser); break;
            case "createGame" : createGame(success, parsedMessage); break;
            //logoutSuccess

            default: break;

        }

    }

    private static void createGame(boolean success, ParsedMessage parsedMessage) {

        if(success){

            //TODO: Hier geht man später direkt in den Multiplayer hinein!
            SendabelMessages.currentGames();

        }else {
            Logger.logE("Error by Request 'createGame': " + parsedMessage.getDataFields()[0]);
        }

    }

    private static void serverNotReachable() {

        ServerBrowserController.setConnectionState("Server nicht erreichbar", ConnectionColor.RED);

    }

    /**
     * DIE HANDLERMETHODEN DÜRFEN KEINE WEITEREN AUFFRUFE MACHEN.
     */
    private static void login(boolean success, String[] params) {

        if(success){

            int maxGames = Integer.parseInt(params[1]);
            ServerBrowserController.setConnectionState("Verbunden", ConnectionColor.GREEN);
            ServerBrowserController.setServerSession(new ServerSession(params[0], maxGames));
            SendabelMessages.currentGames();

        }else {
            ServerBrowserController.setConnectionState("Login nicht erfolgreich", ConnectionColor.YELLOW);
            ServerBrowserController.setServerSession(new ServerSession());
        }

    }

    private static void currentGames(boolean success, ParsedMessage parsedMessage, Parser parser) {
        if(success){
            List<GameItem> gameItems = new ArrayList<>();
            for (String field: parsedMessage.getDataFields()) {
                gameItems.add(GameItem.parse(field));
            }
            ServerBrowserController.setCurrentGames(gameItems);

        }else {

        }
    }


}
