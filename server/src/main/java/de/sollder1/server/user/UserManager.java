package de.sollder1.server.user;

import java.net.InetAddress;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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

    public static synchronized Optional<User> getUser(String clientId) {

        int parsedClientId;
        try {
            parsedClientId =  Integer.parseInt(clientId);
        }catch (Exception e){
            return Optional.empty();
        }

        for (User user: userList) {
            if(user.getClientId() == parsedClientId){
                return Optional.of(user);
            }
        }

        return Optional.empty();

    }
}
