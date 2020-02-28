package de.sollder1.chess.starter.gui.settings;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

//TODO: https://www.baeldung.com/java-properties
public class SettingsPersistenceHelper {

    private static String path = SettingsPersistenceHelper.class.getResource("settings.properties").getPath();

    public static Properties loadSettings(){

        try {
            Properties settings = new Properties();
            settings.load(new FileInputStream(path));
            return settings;
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    public static Properties saveSettings(){
        return null;
    }

}
