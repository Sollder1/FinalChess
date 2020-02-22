package de.sollder1.server;

import de.sollder1.server.connector.Listener;

public class Server {

    public static final int MAX_PLAYERS = 16;

    public static void main(String[] args) {

        Listener.startListener(16, 1998);

        System.out.println("HELLO THERE!");

    }

}
