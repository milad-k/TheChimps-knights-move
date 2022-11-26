package ControllerAndView;

import javafx.animation.PathTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.shape.Line;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;
import java.net.URL;
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
    void addQuestion(ActionEvent event) {

    }

    @FXML
    void back(ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("HomeScreen.fxml"));
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

    }

    @FXML
    void updateQuestion(ActionEvent event) {

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
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
