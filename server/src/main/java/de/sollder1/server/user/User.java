package de.sollder1.server.user;

import java.net.InetAddress;

public class User {

    private int playerNumber;
    private int clientId;
    private String userName;
    private int clientPort;
    private InetAddress clientIp;

    User(int clientId, String userName, int clientPort, InetAddress clientIp) {
        this.clientId = clientId;
        this.userName = userName;
        this.clientPort = clientPort;
        this.clientIp = clientIp;
    }

    public int getPlayerNumber() {
        return playerNumber;
    }

    public void setPlayerNumber(int playerNumber) {
        this.playerNumber = playerNumber;
    }

    public int getClientId() {
        return clientId;
    }

    public void setClientId(int clientId) {
        this.clientId = clientId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public int getClientPort() {
        return clientPort;
    }

    public void setClientPort(int clientPort) {
        this.clientPort = clientPort;
    }

    public InetAddress getClientIp() {
        return clientIp;
    }

    public void setClientIp(InetAddress clientIp) {
        this.clientIp = clientIp;
    }
}
