package de.sollder1.chess.starter.gui.settings;

import de.sollder1.chess.game.ai.AI;
import de.sollder1.chess.game.gui.TimeStamp;

import java.io.File;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

//Global statisches Feld
public class SettingsPojo {

    private SettingsPojo(){}

    private static boolean useChessClock = false;
    private static boolean activateKingMarkings = true;
    private static TimeStamp chessClockTime = new TimeStamp(5, 0);
    private static AiImplementations currentAiImplementation = AiImplementations.SIMPLE_AI;

    //private static Properties settings;
    //
    //static {
    //    settings = SettingsPersistenceHelper.loadSettings();
    //    if(settings != null){
    //        useChessClock = settings.getProperty("useChessClock").equals("true");
    //        activateKingMarkings = settings.getProperty("activateKingMarkings").equals("true");
    //
    //    }
    //}
    private static Theme currentTheme;

    public static boolean isUseChessClock() {
        return useChessClock;
    }

    public static void setUseChessClock(boolean useChessClock) {
        SettingsPojo.useChessClock = useChessClock;
    }

    public static TimeStamp getChessClockTime() {
        return new TimeStamp(chessClockTime);
    }

    public static void setChessClockTime(TimeStamp chessClockTime) {
        SettingsPojo.chessClockTime = chessClockTime;
    }

    public static boolean isActivateKingMarkings() {
        return activateKingMarkings;
    }

    public static void setActivateKingMarkings(boolean activateKingMarkings) {
        SettingsPojo.activateKingMarkings = activateKingMarkings;
    }

    public static Theme getCurrentTheme() {
        if(currentTheme == null){
            return Theme.loadThemes().get(0);
        }
        return currentTheme;
    }

    public static void setCurrentTheme(Theme currentTheme) {
        SettingsPojo.currentTheme = currentTheme;
    }

    public static AiImplementations getCurrentAiImplementation() {
        return currentAiImplementation;
    }

    public static void setCurrentAiImplementation(AiImplementations currentAiImplementation) {
        SettingsPojo.currentAiImplementation = currentAiImplementation;
    }

    public enum AiImplementations{
        SIMPLE_AI
    }

    public static class Theme{
        String name;
        String pathToCss;

        public Theme(String name, String pathToCss) {
            this.name = name;
            this.pathToCss = pathToCss;
        }

        public static List<Theme> loadThemes(){
            File themeDir = new File(Theme.class.getResource("/themes").getPath());
            List<Theme> themes = new ArrayList<>();
            File []filesList = themeDir.listFiles();

            for (File file: filesList) {
                themes.add(new Theme(file.getName(), "/themes/" + file.getName() + "/style.css"));
            }

            return themes;

        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getPathToCss() {
            return pathToCss;
        }

        public void setPathToCss(String pathToCss) {
            this.pathToCss = pathToCss;
        }

        @Override
        public String toString() {
            return name;
        }
    }
}
