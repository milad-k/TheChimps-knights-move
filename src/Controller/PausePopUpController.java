package Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import static Controller.GameController.totalSec;
public class PausePopUpController{
    @FXML
    private Button continueButton;


    @FXML
    void continueButton(ActionEvent event) {
        System.out.println(totalSec);

        Stage stage = (Stage) continueButton.getScene().getWindow();
        stage.close();
    }

}
