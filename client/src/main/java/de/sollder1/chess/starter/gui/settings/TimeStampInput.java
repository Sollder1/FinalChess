package de.sollder1.chess.starter.gui.settings;

import de.sollder1.chess.game.uielements.other.TimeStamp;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;

import java.util.regex.Pattern;

public class TimeStampInput extends HBox {

    private TextField input = new TextField();
    private Label result = new Label("");
    private TimeStamp data;

    public TimeStampInput(TimeStamp startValue) {

        this.data = startValue;
        setSpacing(5);
        setAlignment(Pos.CENTER_LEFT);
        input.setPromptText("hh:mm:ss");
        input.setText(data.toString());

        input.textProperty().addListener((observable, oldValue, newValue) -> {

            if(Pattern.matches("^\\d\\d?:[0-5]?\\d:[0-5]?\\d$", newValue)){
                data.set(newValue);
                result.setText("Eingabe korrekt!");
            }else {
                data.set(oldValue);
                result.setText("Eingabe fehlerhaft");
            }

        });

        getChildren().addAll(input, result);


    }

    public TimeStamp getStamp() {
        return data;
    }

}
