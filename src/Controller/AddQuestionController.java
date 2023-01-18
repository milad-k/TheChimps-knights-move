package Controller;

import Model.Question;
import Model.SysData;
import Utils.Difficulty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class AddQuestionController implements Initializable {

    ToggleGroup group = new ToggleGroup();

    @FXML
    private TextArea questionField;

    @FXML
    private TextField answer1;

    @FXML
    private TextField answer2;

    @FXML
    private TextField answer3;

    @FXML
    private TextField answer4;

    @FXML
    private RadioButton ranswer1;

    @FXML
    private RadioButton ranswer2;

    @FXML
    private RadioButton ranswer3;

    @FXML
    private RadioButton ranswer4;

    @FXML
    private ComboBox<Difficulty> difficulty = new ComboBox<Difficulty>();

    @FXML
    private Button addQuestionButton;

    @FXML
    private Button backButton;

    @FXML
    void back(ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/View/Questions.fxml"));
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
        String quest = questionField.getText();
        String a1 = answer1.getText();
        String a2 = answer2.getText();
        String a3 = answer3.getText();
        String a4 = answer4.getText();
        String correctAnswer;
        Difficulty d = difficulty.getValue();

        if(ranswer1.isSelected()) {
            correctAnswer = "1";
        } else if (ranswer2.isSelected()) {
            correctAnswer = "2";
        } else if (ranswer3.isSelected()) {
            correctAnswer = "3";
        } else {
            correctAnswer = "4";
        }

        if(!ranswer1.isSelected() && !ranswer2.isSelected() && !ranswer3.isSelected() && !ranswer4.isSelected()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Correct Answer");
            alert.setContentText("You must select the correct answer");
            alert.show();
        }

        else if (quest.trim().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Empty string");
            alert.setContentText("You must enter letters/numbers.");
            alert.show();
        }

        else {
            if(difficulty.getValue() == null) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Difficulty");
                alert.setContentText("You must select the difficulty of the question");
                alert.show();
            } else {
                if(quest.isEmpty() || a1.isEmpty() || a2.isEmpty() || a3.isEmpty() || a4.isEmpty()) {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Type question");
                    alert.setContentText("You must type a question");
                    alert.show();
                } else {
                    if(a1.equals(a2) || a1.equals(a3) || a1.equals(a4) || a2.equals(a3) || a2.equals(a4) || a3.equals(a4)) {
                        Alert alert = new Alert(Alert.AlertType.ERROR);
                        alert.setTitle("Same answer");
                        alert.setContentText("You must enter different answers");
                        alert.show();
                    } else {

                        Question q = new Question(quest, a1, a2, a3, a4, correctAnswer, d, "Chimp");
                        boolean isAdded = SysData.getInstance().addQuestion(q);
                        System.out.println(isAdded);
                        if(!isAdded) {
                            Alert alert = new Alert(Alert.AlertType.ERROR);
                            alert.setTitle("Same Question exists");
                            alert.setContentText("You must enter different Question");
                            alert.show();
                        } else {
                            Alert alert = new Alert(Alert.AlertType.INFORMATION);
                            alert.setTitle("Done");
                            alert.setContentText("Your Question Was Added Succeccfully !");
                            alert.show();
                            SysData.getInstance().observableMethod();
                        }
                    }
                }
            }

        }
        SysData.getInstance().loadQuestions("QuestionsFormat.json");
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ObservableList<Difficulty> list=FXCollections.observableArrayList(Difficulty.values());
        difficulty.setItems(list);
        ranswer1.setToggleGroup(group);
        ranswer2.setToggleGroup(group);
        ranswer3.setToggleGroup(group);
        ranswer4.setToggleGroup(group);
    }
}
