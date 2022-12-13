package Controller;

import Model.Question;
import Model.SysData;
import Utils.Difficulty;
import javafx.animation.PathTransition;
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
import javafx.scene.control.ButtonType;
import javafx.scene.control.ListView;
import javafx.scene.image.ImageView;
import javafx.scene.shape.Line;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.ResourceBundle;

public class QuestionsController implements Initializable {

    @FXML
    private Button addQuestionButton;

    @FXML
    private Button backButton;

    @FXML
    private Button deleteQuestionButton;

    @FXML
    private Button updateQuestionButton;

    @FXML
    private ImageView image;

    @FXML
    private ListView<Question> list;

    static Question updatedQ;

    public ListView<Question> getList() {
        return list;
    }

    public void setList(ListView<Question> list) {
        this.list = list;
    }

    private HashMap<Difficulty, ArrayList<Question>> questions;
    SysData sysData = SysData.getInstance();
    private ArrayList<Question> level = new ArrayList<Question>();

    @FXML
    void addQuestion(ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("../View/AddQuestion.fxml"));
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
    void back(ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("../View/HomeScreen.fxml"));
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
    void deleteQuestion(ActionEvent event) {
        Question q = list.getSelectionModel().getSelectedItem();
        if(!list.getItems().isEmpty() && q != null) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Remove Question");
            alert.setHeaderText("Remove Question?");
            alert.setContentText("Are you sure you want to remove this question?");
            alert.showAndWait();
            if(alert.getResult().equals(ButtonType.OK)) {
                list.getItems().remove(q);
                SysData.getInstance().removeQuestion(q);
            }
        }
        else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("No question selected");
            alert.setHeaderText("Select Question");
            alert.setContentText("Please select a question to delete.");
            alert.showAndWait();
        }
    }

    @FXML
    void updateQuestion(ActionEvent event) throws IOException {
        updatedQ = list.getSelectionModel().getSelectedItem();
        closeWindow();
        Stage primaryStage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("/View/UpdateQuestion.fxml"));
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.setTitle("update question");
        primaryStage.show();
    }
    public void closeWindow() {
        ((Stage) backButton.getScene().getWindow()).close();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        SysData.getInstance().loadQuestions(null);

        questions = sysData.getQuestions();
        for(Difficulty d : questions.keySet()) {
            for(Question q : questions.get(d)) {
                if(!level.contains(q))
                    level.add(q);
            }
        }
        ObservableList<Question> question = FXCollections.observableArrayList(level);
        list.setItems(question);
        
        Line l = new Line(3,50, 150, 50);

        PathTransition transition = new PathTransition();
        transition.setNode(image);
        transition.setDuration(Duration.seconds(2));
        transition.setPath(l);
        transition.setCycleCount(PathTransition.INDEFINITE);
        transition.setAutoReverse(true);
        transition.play();
    }
}
