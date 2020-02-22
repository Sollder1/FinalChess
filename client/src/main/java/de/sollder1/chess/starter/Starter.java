package de.sollder1.chess.starter;

import de.sollder1.chess.starter.gui.StarterView;
import javafx.stage.Stage;

public class Starter {

    public static void startStarterInstance() {

        StarterView starterView = new StarterView();
        try {
            starterView.start(new Stage());
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
