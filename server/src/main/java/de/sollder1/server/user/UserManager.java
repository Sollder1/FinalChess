package de.sollder1.server.user;

import java.net.InetAddress;
import java.util.ArrayList;
import java.util.List;

public class UserManager {

    private UserManager(){}

    private static List<User> userList;
    private static int clientIdCounter;

    static {
        userList = new ArrayList<>();
        clientIdCounter = 0;
    }

    public static synchronized User addUser(String userName, int clientPort, InetAddress clientIp){
        User user = new User(++clientIdCounter, userName, clientPort, clientIp);
        userList.add(user);

        return user;
    }

}
