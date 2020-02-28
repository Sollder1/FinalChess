package de.sollder1.chess.starter.gui.settings;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class SettingsController implements Initializable {

    @FXML
    HBox durationContainer;

    @FXML
    CheckBox activateChessTimer;

    @FXML
    CheckBox activateKingMarkings;

    @FXML
    ChoiceBox<SettingsPojo.Theme> themeChoiceBox;

    @FXML
    ChoiceBox<SettingsPojo.AiImplementations> aiImplementation;

    private TimeStampInput chessTimeInput;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        themeChoiceBox.getItems().addAll(SettingsPojo.Theme.loadThemes());
        themeChoiceBox.getSelectionModel().select(0);

        aiImplementation.getItems().addAll(SettingsPojo.AiImplementations.SIMPLE_AI);
        aiImplementation.getSelectionModel().select(0);

        activateKingMarkings.selectedProperty().setValue(SettingsPojo.isActivateKingMarkings());
        activateChessTimer.selectedProperty().setValue(SettingsPojo.isUseChessClock());
        chessTimeInput = new TimeStampInput(SettingsPojo.getChessClockTime());
        durationContainer.getChildren().add(chessTimeInput);

    }

    public void saveSettingsEventHandler(ActionEvent event) {
        SettingsPojo.setChessClockTime(chessTimeInput.getStamp());
        SettingsPojo.setUseChessClock(activateChessTimer.isSelected());
        SettingsPojo.setActivateKingMarkings(activateKingMarkings.isSelected());
        SettingsPojo.setCurrentTheme(themeChoiceBox.getValue());
        SettingsPojo.setCurrentAiImplementation(aiImplementation.getValue());
        ((Stage)durationContainer.getScene().getWindow()).close();
    }

    public void abortEventHandler(ActionEvent event) {
        ((Stage)durationContainer.getScene().getWindow()).close();
    }


}
