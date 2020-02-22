package de.sollder1.common.parsing;

import java.net.InetAddress;
import java.util.Arrays;

public class ParsedMessage {

    private InetAddress ip;
    private String command;
    private String[] dataFields;

    public ParsedMessage(InetAddress ip, String command, String[] dataFields) {
        this.ip = ip;
        this.command = command;
        this.dataFields = dataFields;
    }

    public ParsedMessage(String command, String[] dataFields) {

        this.ip = null;
        this.command = command;
        this.dataFields = dataFields;
    }


    public InetAddress getIp() {
        return ip;
    }

    public void setIp(InetAddress ip) {
        this.ip = ip;
    }

    public String getCommand() {
        return command;
    }

    public void setCommand(String command) {
        this.command = command;
    }

    public String[] getDataFields() {
        return dataFields;
    }

    public void setDataFields(String[] dataFields) {
        this.dataFields = dataFields;
    }

    @Override
    public String toString() {

        String ip = this.ip == null ? "[NOIP]" : this.ip.toString();

        return "ParsedMessage{" +
                "ip='" + ip  + "'" +
                ", command='" + command + '\'' +
                ", dataFields=" + Arrays.toString(dataFields) +
                '}';
    }
}
