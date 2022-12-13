package Controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import Model.Question;
import Model.SysData;
import Utils.Difficulty;
import Utils.Team;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;

public class UpdateQuestionController implements Initializable  {


    @FXML
    private TextArea text = new TextArea();

    @FXML
    private TextField ans1 = new TextField();

    @FXML
    private TextField ans2 = new TextField();

    @FXML
    private TextField ans3 = new TextField();

    @FXML
    private TextField ans4 = new TextField();

    @FXML
    private ComboBox<Difficulty> diff = new ComboBox<Difficulty>();

    @FXML
    private ComboBox<Team> team = new ComboBox<Team>();

    @FXML
    private Button update;

    @FXML
    private RadioButton r1 = new RadioButton();

    @FXML
    private ToggleGroup right;

    @FXML
    private RadioButton r2 = new RadioButton();

    @FXML
    private RadioButton r3 = new RadioButton();

    @FXML
    private RadioButton r4 = new RadioButton();

    public void closeWindow() {
        ((Stage) r3.getScene().getWindow()).close();
    }

    @FXML
    void back(ActionEvent event) throws Exception {
        closeWindow();
        Stage primaryStage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("/View/Questions.fxml"));
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.setTitle("questions");
        primaryStage.show();
    }


    @FXML
    void update(ActionEvent event) {
        String ques = text.getText();
        String answer1 = ans1.getText();
        String answer2 = ans2.getText();
        String answer3 = ans3.getText();
        String answer4 = ans4.getText();
        int rightAnswer = 0;
        int flag=0;
        Difficulty d = diff.getValue();
        Team t = team.getValue();
        if (r1.isSelected()) {
            rightAnswer = 1;
        } else if (r2.isSelected()) {
            rightAnswer = 2;
        } else if (r3.isSelected()) {
            rightAnswer = 3;
        } else if (r4.isSelected()){
            rightAnswer = 4;
        }
        else
        {
            flag=1;
        }

        if(ques.isEmpty()||flag==1||diff.getValue()==null ||team.getValue()==null
                || ans1.getText().isEmpty()||ans2.getText().isEmpty()|| ans3.getText().isEmpty()||
                ans4.getText().isEmpty())
        {
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("missing fields");
            alert.setContentText("You must fill all the fields!");
            alert.show();

        }
        else
        {
            Question q1 = new Question(ques, rightAnswer, d, t);
            if(q1.getAnswers().contains(answer1) ||q1.getAnswers().contains(answer2) ||q1.getAnswers().contains(answer3)
                    ||q1.getAnswers().contains(answer4) )
            {
                Alert alert = new Alert(AlertType.ERROR);
                alert.setTitle("same answer");
                alert.setContentText("You must enter different answers!");
                alert.show();
            }
            else
            {
                q1.addAnswer(answer1);
                q1.addAnswer(answer2);
                q1.addAnswer(answer3);
                q1.addAnswer(answer4);

                System.out.println(QuestionsController.updatedQ.getText());
                SysData.getInstance().addQuestion(q1);
                SysData.getInstance().removeQuestion(QuestionsController.updatedQ);
                //SysData.getInstance().saveQuestions(null);
                ((Stage) ans3.getScene().getWindow()).close();
                try {
                    Parent root = FXMLLoader.load(getClass().getResource("../View/Questions.fxml"));
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
        }
    }



    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {


        Question q = QuestionsController.updatedQ;
        //Question q = list.getSelectionModel().getSelectedItem();
        text.setText(q.getText());
        System.out.println(text.getText());
        ans1.setText(q.getAnswers().get(0));
        ans2.setText(q.getAnswers().get(1));
        ans3.setText(q.getAnswers().get(2));
        ans4.setText(q.getAnswers().get(3));
        int rightAnswer = q.getCorrectAnswer();
        if (rightAnswer == 1) {
            r1.setSelected(true);
        } else if (rightAnswer == 2) {
            r2.setSelected(true);
        } else if (rightAnswer == 3) {
            r3.setSelected(true);
        } else {
            r4.setSelected(true);
        }
        diff.setValue(q.getDifficulty());
        team.setValue(q.getTeam());
        ObservableList<Difficulty> list=FXCollections.observableArrayList(Difficulty.values());
        diff.setItems(list);
        ObservableList<Team> list2=FXCollections.observableArrayList(Team.values());
        team.setItems(list2);

    }

}
