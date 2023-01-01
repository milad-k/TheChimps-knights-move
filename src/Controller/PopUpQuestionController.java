package Controller;

import Model.Question;
import Model.SysData;
import Utils.Difficulty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

import static Controller.GameController.staticStage;
import static Controller.GameController.staticTotalPoints;
import static Controller.GameLevel2Controller.staticPoints2;
import static Controller.GameLevel3Controller.staticPoints3;
import static Controller.GameLevel4Controller.staticPoints4;
import static Utils.Difficulty.gettingLoosingPoints;
import static Utils.Difficulty.gettingWinningPoints;

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

       Question question = SysData.getInstance().getQuestionByText(questionText.getText(), difficultyText.getText());
        if(userAnswer1.isSelected() && userAnswer1.getText().equals(question.getCorrect_ans())){
            int additionalPoints = gettingWinningPoints(question);
            if(staticStage.getText().equals("1")){
                int g =Integer.parseInt(staticPoints.getText()) + additionalPoints;
                staticPoints.setText(String.valueOf(g));
            }
            if(staticStage.getText().equals("2")){
                int g =Integer.parseInt(staticPoints2.getText()) + additionalPoints;
                staticPoints2.setText(String.valueOf(g));
            }
            if(staticStage.getText().equals("3")){
                int g =Integer.parseInt(staticPoints3.getText()) + additionalPoints;
                staticPoints3.setText(String.valueOf(g));
            }
            if(staticStage.getText().equals("4")){
                int g =Integer.parseInt(staticPoints4.getText()) + additionalPoints;
                staticPoints4.setText(String.valueOf(g));
            }
        }
        if(userAnswer1.isSelected() && !userAnswer1.getText().equals(question.getCorrect_ans())){
            int additionalPoints = gettingLoosingPoints(question);
            if(staticStage.getText().equals("1")){
                int g =Integer.parseInt(staticPoints.getText()) + additionalPoints;
                staticPoints.setText(String.valueOf(g));
            }
            if(staticStage.getText().equals("2")){
                int g =Integer.parseInt(staticPoints2.getText()) + additionalPoints;
                staticPoints2.setText(String.valueOf(g));
            }
            if(staticStage.getText().equals("3")){
                int g =Integer.parseInt(staticPoints3.getText()) + additionalPoints;
                staticPoints3.setText(String.valueOf(g));
            }
            if(staticStage.getText().equals("4")){
                int g =Integer.parseInt(staticPoints4.getText()) + additionalPoints;
                staticPoints4.setText(String.valueOf(g));
            }
        }



        if(userAnswer2.isSelected() && userAnswer2.getText().equals(question.getCorrect_ans())){
            int additionalPoints = gettingWinningPoints(question);
            if(staticStage.getText().equals("1")){
                int g =Integer.parseInt(staticPoints.getText()) + additionalPoints;
                staticPoints.setText(String.valueOf(g));
            }
            if(staticStage.getText().equals("2")){
                int g =Integer.parseInt(staticPoints2.getText()) + additionalPoints;
                staticPoints2.setText(String.valueOf(g));
            }
            if(staticStage.getText().equals("3")){
                int g =Integer.parseInt(staticPoints3.getText()) + additionalPoints;
                staticPoints3.setText(String.valueOf(g));
            }
            if(staticStage.getText().equals("4")){
                int g =Integer.parseInt(staticPoints4.getText()) + additionalPoints;
                staticPoints4.setText(String.valueOf(g));
            }
        }
        if(userAnswer2.isSelected() && !userAnswer2.getText().equals(question.getCorrect_ans())){
            int additionalPoints = gettingLoosingPoints(question);
            if(staticStage.getText().equals("1")){
                int g =Integer.parseInt(staticPoints.getText()) + additionalPoints;
                staticPoints.setText(String.valueOf(g));
            }
            if(staticStage.getText().equals("2")){
                int g =Integer.parseInt(staticPoints2.getText()) + additionalPoints;
                staticPoints2.setText(String.valueOf(g));
            }
            if(staticStage.getText().equals("3")){
                int g =Integer.parseInt(staticPoints3.getText()) + additionalPoints;
                staticPoints3.setText(String.valueOf(g));
            }
            if(staticStage.getText().equals("4")){
                int g =Integer.parseInt(staticPoints4.getText()) + additionalPoints;
                staticPoints4.setText(String.valueOf(g));
            }
        }



        if(userAnswer3.isSelected() && userAnswer3.getText().equals(question.getCorrect_ans())){
            int additionalPoints = gettingWinningPoints(question);
            if(staticStage.getText().equals("1")){
                int g =Integer.parseInt(staticPoints.getText()) + additionalPoints;
                staticPoints.setText(String.valueOf(g));
            }
            if(staticStage.getText().equals("2")){
                int g =Integer.parseInt(staticPoints2.getText()) + additionalPoints;
                staticPoints2.setText(String.valueOf(g));
            }
            if(staticStage.getText().equals("3")){
                int g =Integer.parseInt(staticPoints3.getText()) + additionalPoints;
                staticPoints3.setText(String.valueOf(g));
            }
            if(staticStage.getText().equals("4")){
                int g =Integer.parseInt(staticPoints4.getText()) + additionalPoints;
                staticPoints4.setText(String.valueOf(g));
            }
        }
        if(userAnswer3.isSelected() && !userAnswer3.getText().equals(question.getCorrect_ans())){
            int additionalPoints = gettingLoosingPoints(question);
            if(staticStage.getText().equals("1")){
                int g =Integer.parseInt(staticPoints.getText()) + additionalPoints;
                staticPoints.setText(String.valueOf(g));
            }
            if(staticStage.getText().equals("2")){
                int g =Integer.parseInt(staticPoints2.getText()) + additionalPoints;
                staticPoints2.setText(String.valueOf(g));
            }
            if(staticStage.getText().equals("3")){
                int g =Integer.parseInt(staticPoints3.getText()) + additionalPoints;
                staticPoints3.setText(String.valueOf(g));
            }
            if(staticStage.getText().equals("4")){
                int g =Integer.parseInt(staticPoints4.getText()) + additionalPoints;
                staticPoints4.setText(String.valueOf(g));
            }
        }


        if(userAnswer4.isSelected() && userAnswer4.getText().equals(question.getCorrect_ans())){
            int additionalPoints = gettingWinningPoints(question);
            if(staticStage.getText().equals("1")){
                int g =Integer.parseInt(staticPoints.getText()) + additionalPoints;
                staticPoints.setText(String.valueOf(g));
            }
            if(staticStage.getText().equals("2")){
                int g =Integer.parseInt(staticPoints2.getText()) + additionalPoints;
                staticPoints2.setText(String.valueOf(g));
            }
            if(staticStage.getText().equals("3")){
                int g =Integer.parseInt(staticPoints3.getText()) + additionalPoints;
                staticPoints3.setText(String.valueOf(g));
            }
            if(staticStage.getText().equals("4")){
                int g =Integer.parseInt(staticPoints4.getText()) + additionalPoints;
                staticPoints4.setText(String.valueOf(g));
            }
        }
        if(userAnswer4.isSelected() && !userAnswer4.getText().equals(question.getCorrect_ans())){
            int additionalPoints = gettingLoosingPoints(question);
            if(staticStage.getText().equals("1")){
                int g =Integer.parseInt(staticPoints.getText()) + additionalPoints;
                staticPoints.setText(String.valueOf(g));
            }
            if(staticStage.getText().equals("2")){
                int g =Integer.parseInt(staticPoints2.getText()) + additionalPoints;
                staticPoints2.setText(String.valueOf(g));
            }
            if(staticStage.getText().equals("3")){
                int g =Integer.parseInt(staticPoints3.getText()) + additionalPoints;
                staticPoints3.setText(String.valueOf(g));
            }
            if(staticStage.getText().equals("4")){
                int g =Integer.parseInt(staticPoints4.getText()) + additionalPoints;
                staticPoints4.setText(String.valueOf(g));
            }
        }

        javafx.stage.Stage stage = (Stage) answerButton.getScene().getWindow();
        stage.close();
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
