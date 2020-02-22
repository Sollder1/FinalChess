package de.sollder1.server.connector;



import de.sollder1.common.parsing.OwnParser;
import de.sollder1.common.util.Logger;
import de.sollder1.server.message.MessageInterpreter;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.nio.charset.StandardCharsets;
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

            try {
                byte[] receivedData = new byte[1024];

                DatagramPacket input = new DatagramPacket(receivedData, receivedData.length);
                socket.receive(input);
                String message = new String(input.getData(), StandardCharsets.UTF_16).trim();

                //Startet für jede eingehende Nachricht einen Seperaten Thread
                service.submit(() -> {

                    try {
                        String ip = input.getAddress().getHostAddress();
                        InetAddress ipAdress = InetAddress.getByName(ip);

                        MessageInterpreter interpreter = new MessageInterpreter();

                        String reply = interpreter.parse(ipAdress, message, new OwnParser());

                        byte[] sendData = reply.getBytes(StandardCharsets.UTF_16);
                        DatagramPacket output = new DatagramPacket(sendData, sendData.length, ipAdress, input.getPort());

                        socket.send(output);

                        //interpreter.flushMessages();
                    } catch (Exception e) {
                        Logger.logE(e);
                    }

                });

                //Workaround für mein Lokales netzt das aus irgendeienm Grund DNS NAmen vergibt...


            } catch (Exception e) {
                Logger.logE(e);
            }

        }


    }


}
