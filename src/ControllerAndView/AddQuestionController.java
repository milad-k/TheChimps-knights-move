package ControllerAndView;

import Model.Question;
import Model.SysData;
import Utils.Difficulty;
import Utils.Team;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;

public class AddQuestionController {
    @FXML
    private TextField questionField;
    @FXML
    private TextField answerOneField;
    @FXML
    private TextField answerTwoField;
    @FXML
    private TextField answerThreeField;
    @FXML
    private TextField answerFourField;
    @FXML
    private ComboBox correctAnswerBox;
    @FXML
    private ComboBox difficultyBox;
    @FXML
    private ComboBox teamBox;
    @FXML
    private Button addQuestionButton;
    @FXML
    private Button backButton;


    ObservableList<Integer> correctAnswerList = FXCollections.observableArrayList(1,2,3,4);
    ObservableList<Difficulty> difficultyList = FXCollections.observableArrayList(Difficulty.HARD,Difficulty.MEDIUM,Difficulty.EASY);
    ObservableList<Team> teamList = FXCollections.observableArrayList(Team.Giraffe,Team.Husky,Team.Spider,Team.Tiger,Team.Rabbit,Team.Jellyfish,Team.Zebra,Team.Panther,Team.Chimp,Team.Shark,Team.Panda,Team.Viper,Team.Sloth,Team.Scorpion,Team.Lion,Team.Hawk,Team.Wolf,Team.Python);


    @FXML
    void back(ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("Questions.fxml"));
            root.setStyle("-fx-background-image: url('Images/backgroundWallpaper.jpeg');" + "-fx-background-size:cover");
            Scene customerScene = new Scene(root);
            Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
            window.setScene(customerScene);
            window.show();
        } catch (IOException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("FXML");
            alert.setHeaderText("Load failure");
            alert.setContentText("Failed to load the FXML file.");
            alert.showAndWait();
        }
    }

    @FXML
    void addQuestion(ActionEvent event) {
        String question = questionField.getText();
        String answer1 = answerOneField.getText();
        String answer2 = answerTwoField.getText();
        String answer3 = answerThreeField.getText();
        String answer4 = answerFourField.getText();
        int correct = (int) correctAnswerBox.getValue();
        Difficulty difficulty = (Difficulty) difficultyBox.getValue();
        Team team = (Team) teamBox.getValue();
        ArrayList<String> answers = new ArrayList<>();
        answers.add(answer1);
        answers.add(answer2);
        answers.add(answer3);
        answers.add(answer4);

        if(question == null || question.isEmpty() || answer1 == null || answer1.isEmpty() || answer2 == null || answer2.isEmpty() || answer3 == null || answer4.isEmpty() || answer4 == null || answer4.isEmpty()) {
            questionField.setStyle("-fx-background-radius: 8px");
            questionField.setStyle("-fx-border-color: red");
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Invalid input");
            alert.setContentText("Please try again");
            alert.showAndWait();
        }
        else{
            Question q = new Question(question,correct, difficulty,team);
            SysData.getInstance().addQuestion(q);
            q.setAnswers(answers);


            Alert alert1 = new Alert(Alert.AlertType.INFORMATION);
            alert1.setTitle("success");
            alert1.setHeaderText("your question was added successfully");
            alert1.showAndWait();
        }
    }
    @FXML
    private void initialize(){
        correctAnswerBox.setValue(1);
        correctAnswerBox.setItems(correctAnswerList);

        difficultyBox.setValue(Difficulty.EASY);
        difficultyBox.setItems(difficultyList);

        teamBox.setValue(Team.Chimp);
        teamBox.setItems(teamList);
    }


}
