package Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class PausePopUpController{
    @FXML
    private Button continueButton;


    @FXML
    void continueButton(ActionEvent event) {
        Stage stage = (Stage) continueButton.getScene().getWindow();
        stage.close();

    }

}
