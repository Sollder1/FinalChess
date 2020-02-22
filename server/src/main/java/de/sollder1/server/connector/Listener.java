package de.sollder1.server.connector;


import de.hskl.beei0004.common.util.Logger;

import java.net.DatagramSocket;
import java.net.SocketException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

//Non Critical Area
public class Listener {

    private final int PORT;
    private final int MAX_THREADS;
    private boolean run = true;

    private static Listener obj;
    private DatagramSocket socket;
    private ExecutorService service;

    private Listener(int maxThreads, int port) {
        this.MAX_THREADS = maxThreads;
        this.PORT = port;
    }

    public static  void startListener(int maxThreads, int port){
        if(obj == null) {
            obj = new Listener(maxThreads, port);
            obj.init();
            obj.start();
        }
    }

    private void init() {
        try {
            socket = new DatagramSocket(PORT);
            service = Executors.newFixedThreadPool(MAX_THREADS);

        } catch (SocketException e) {
            Logger.logE("Error during Server initialization.");
            Logger.logE(e);
            System.exit(1);
        }
    }

    private void start(){

        while (run) {



        }


    }


}
