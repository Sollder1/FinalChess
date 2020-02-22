package de.sollder1.server.message;

import de.sollder1.common.parsing.ParsedMessage;
import de.sollder1.common.parsing.Parser;
import de.sollder1.common.util.Logger;
import de.sollder1.server.Server;
import de.sollder1.server.user.User;
import de.sollder1.server.user.UserManager;

import java.net.InetAddress;
import java.util.ArrayList;
import java.util.List;

public class MessageInterpreter {


    //private volatile List<QueuedMessage> toSend = new ArrayList<>();

    /**
     * Versucht die gegebene Nachricht message zu parsen und schließlich zu interpretieren.
     *
     * @param message Die Nachricht, welche es zu verarbeiten gilt.
     * @param parser  Die Parser Implementierung
     */
    public String parse(InetAddress ip, String message, Parser parser) {

        try {

            return interpreter(parser.parseMessage(message, ip));

        } catch (Throwable t) {
            Logger.logE(t);
            return "";
        }
    }

    /**
     * Sendet alle Serverbefehle, welche sich während der bearbeitung der Anfrage angesammelt haben.
     * An dieser Stele findet auch die Fehlerbehandlung im Falle eines Verbindungsabbruchs statt.
     */
        /*
        public void flushMessages() {

            for (QueuedMessage message : toSend) {

                boolean success = Sender.sendMessage(message);

                if (!success) {

                    Logger.logW("Client nicht erreichbar, Logge Client aus und Informiere den anderen!");
                    toSend.clear();

                    userNotReachable(message.getPlayer());
                    flushMessages();

                    return;
                }
            }

            toSend.clear();

        }
*/
    private String interpreter(ParsedMessage parsedMessage) {

        Logger.logI(parsedMessage.toString());

        switch (parsedMessage.getCommand()) {

            case "login":
                return login(parsedMessage);
            default:
                return "invalid;";

        }


    }

    private String login(ParsedMessage parsedMessage) {
        System.err.println(parsedMessage.toString());
        try{
            User user = UserManager.addUser(parsedMessage.getDataFields()[1],
                    Integer.parseInt(parsedMessage.getDataFields()[0]),
                    parsedMessage.getIp());

            return "login_Success;" + user.getClientId() + ":" + Server.MAX_PLAYERS;

        }catch (Exception e){
            Logger.logE(e);
            return "login_Failure;";
        }


    }


    private void sendToAll(String msg) {

        send(msg, 1);
        send(msg, 2);

    }

    private void sendToAll(String msg1, String msg2) {

        send(msg1, 1);
        send(msg2, 2);

    }

    private void send(String msg, int playerNumber) {

        //Player p = PlayerManager.getPlayer(playerNumber);
//
        //toSend.add(new QueuedMessage(msg, p));

    }


}
