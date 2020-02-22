package de.sollder1.chess.connection;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.nio.charset.StandardCharsets;

public class Sender {

    private static InetAddress serverIp;
    private static int serverPort;
    private static boolean initialized;

    public static void init(InetAddress serverIp, int serverPort) {
        Sender.serverIp = serverIp;
        Sender.serverPort = serverPort;
        initialized = true;
    }

    /**
     * Versendet die Nachricht message unter verwendung des UDP Protokolls
     * an den Server, welcher durch die kombination von IP und PORT identifiziert wird.
     *
     * @param message Die zu versendende Nachricht.
     * @return Antwort des Servers
     */
    public static String sendMessage(String message) {

        try {

            //Sende die Anfrage
            byte[] sendData = message.getBytes(StandardCharsets.UTF_16);
            DatagramSocket socket = new DatagramSocket();
            socket.setSoTimeout(1000);

            DatagramPacket output = new DatagramPacket(sendData, sendData.length, serverIp, serverPort);
            socket.send(output);


            //Verarbeite die Antwort:
            byte[] receivedData = new byte[16_384];
            DatagramPacket input = new DatagramPacket(receivedData, receivedData.length);
            socket.receive(input);

            String reply = new String(input.getData(), StandardCharsets.UTF_16).trim();
            socket.close();

            return reply;

        } catch (IOException e) {
            //Logger.logE(e);
        }

        return "SERVER_NOT_REACHABLE";

    }

    public static boolean isInitialized() {
        return initialized;
    }

}
