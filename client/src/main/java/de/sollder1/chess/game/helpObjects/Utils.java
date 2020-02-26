package de.sollder1.chess.game.helpObjects;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import java.io.File;

public final class Utils {

    private Utils(){}

    public static void playMusic(String resourcePath, Class<?> clazz){
        String url = clazz.getResource(resourcePath).getFile();

        Media sound = new Media(new File(url).toURI().toString());
        MediaPlayer mediaPlayer = new MediaPlayer(sound);
        mediaPlayer.play();
    }

}
