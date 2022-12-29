package Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.text.Text;

import java.net.URL;
import java.util.ResourceBundle;

public class PopUpQuestionController implements Initializable {
    @FXML
    private Button answerButton;

    @FXML
    private Text questionText;
    @FXML
    private Text difficultyText;
    @FXML
    private Text pointsText;

    @FXML
    public static Text staticQuestion;
    @FXML
    public static Text staticDifficulty;
    @FXML
    public static Text staticPoints;

    @FXML
    private RadioButton userAnswer1;
    @FXML
    private RadioButton userAnswer2;
    @FXML
    private RadioButton userAnswer3;
    @FXML
    private RadioButton userAnswer4;
    @FXML
    public static RadioButton staticAnswer1;
    @FXML
    public static RadioButton staticAnswer2;
    @FXML
    public static RadioButton staticAnswer3;
    @FXML
    public static RadioButton staticAnswer4;




    public void answer(ActionEvent actionEvent) {
    }

    public void userAnswer1pressed(ActionEvent actionEvent) {
    }

    public void userAnswer2pressed(ActionEvent actionEvent) {
    }

    public void userAnswer4pressed(ActionEvent actionEvent) {
    }

    public void userAnswer3pressed(ActionEvent actionEvent) {
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        staticQuestion = questionText;
        staticDifficulty = difficultyText;
        staticPoints = pointsText;
        staticAnswer1 = userAnswer1;
        staticAnswer2 = userAnswer2;
        staticAnswer3 = userAnswer3;
        staticAnswer4 = userAnswer4;

    }
}
