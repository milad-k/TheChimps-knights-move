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

public class UpdateQuestionCustomerController implements Initializable {

    ToggleGroup group = new ToggleGroup();
    @FXML
    private Button backButton;

    @FXML
    private ComboBox<Difficulty> editDifficulty = new ComboBox<Difficulty>();

    @FXML
    private TextField editanswer1 = new TextField();

    @FXML
    private TextField editanswer2 = new TextField();

    @FXML
    private TextField editanswer3 = new TextField();

    @FXML
    private TextField editanswer4 = new TextField();

    @FXML
    private TextArea editques = new TextArea();

    @FXML
    private RadioButton redit1 = new RadioButton();

    @FXML
    private RadioButton redit2 = new RadioButton();

    @FXML
    private RadioButton redit3 = new RadioButton();

    @FXML
    private RadioButton redit4 = new RadioButton();

    @FXML
    private Button saveEdit;

    @FXML
    void back(ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("../View/ManageQuestionsCustomer.fxml"));
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
    void updateQuestion(ActionEvent event) {
        String quest = editques.getText();
        String answer1 = editanswer1.getText();
        String answer2 = editanswer2.getText();
        String answer3 = editanswer3.getText();
        String answer4 = editanswer4.getText();
        String correctAnswer = null;
        int flag = 0;
        Difficulty d = editDifficulty.getValue();
        if(redit1.isSelected()) {
            correctAnswer = answer1;
        } else if (redit2.isSelected()) {
            correctAnswer = answer2;
        } else if (redit3.isSelected()) {
            correctAnswer = answer3;
        } else if (redit4.isSelected()) {
            correctAnswer = answer4;
        } else {
            flag = 1;
        }

        if(quest.isEmpty() || flag == 1 || editDifficulty.getValue() == null || editanswer1.getText().isEmpty() || editanswer2.getText().isEmpty() || editanswer3.getText().isEmpty() || editanswer4.getText().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Missing fields");
            alert.setContentText("You must fill all the fields");
            alert.show();
        } else {
            Question q1 = new Question(quest, answer1, answer2, answer3, answer4, correctAnswer, d, "Chimp");
            if(answer1.equals(answer2) || answer1.equals(answer3) || answer1.equals(answer4) || answer2.equals(answer3) || answer2.equals(answer4) || answer3.equals(answer4)) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Same fields");
                alert.setContentText("You must enter different answers");
                alert.show();
            } else {
                q1.setAnswer1(answer1);
                q1.setAnswer2(answer2);
                q1.setAnswer3(answer3);
                q1.setAnswer4(answer4);
                boolean isAdded = SysData.getInstance().updateQuestion(QuestionsController.updatedQ, q1);
                System.out.println(isAdded);
                if(!isAdded) {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Same question exists");
                    alert.setContentText("You must enter a different question");
                    alert.show();
                } else {
                    SysData.getInstance().observableMethod();
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Done");
                    alert.setContentText("Your question was updated successfully!");
                    alert.show();
                }
            }
        }

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Question q = QuestionsController.updatedQ;
        editques.setText(q.getText());
        editanswer1.setText(q.getAnswer1());
        editanswer2.setText(q.getAnswer2());
        editanswer3.setText(q.getAnswer3());
        editanswer4.setText(q.getAnswer4());
        String correctAnswer = q.getCorrect_ans();
        if(correctAnswer.equals("1")) {
            redit1.setSelected(true);
        } else if (correctAnswer.equals("2")) {
            redit2.setSelected(true);
        } else if (correctAnswer.equals("3")) {
            redit3.setSelected(true);
        } else {
            redit4.setSelected(true);
        }
        editDifficulty.setValue(q.getLevel());
        ObservableList<Difficulty> editList = FXCollections.observableArrayList(Difficulty.values());
        editDifficulty.setItems(editList);
        redit1.setToggleGroup(group);
        redit2.setToggleGroup(group);
        redit3.setToggleGroup(group);
        redit4.setToggleGroup(group);
    }
}
