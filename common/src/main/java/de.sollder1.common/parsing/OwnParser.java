package de.sollder1.common.parsing;


import java.net.InetAddress;

public class OwnParser implements Parser{

    //Syntax: <IP>;<Befehl>;[ (<data>:)* (<data>)? ]

    @Override
    public ParsedMessage parseMessage(String message) throws Exception{

        String[] components = message.split(";");

        String command = components[0];


        String[] data = components.length == 2 ? components[1].split(":") : new String[0];

        return new ParsedMessage(command, data);

    }

    @Override
    public ParsedMessage parseMessage(String message, InetAddress ip) throws Exception{

        ParsedMessage parsedMessage = parseMessage(message);
        parsedMessage.setIp(ip);

        return parsedMessage;

    }

}
