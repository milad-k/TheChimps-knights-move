package Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class HomeScreenController implements Initializable {

    @FXML
    private Button exitButton;

    @FXML
    private Button gamesHistoryButton;

    @FXML
    private Button questionsButton;

    @FXML
    private Button rulesButton;

    @FXML
    private Button startGameButton;

    @FXML
    private Button usersButton;

    @FXML
    private Button timerButton;


    @FXML
    void gamesHistory(ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("../View/HistoryScreen.fxml"));
            root.setStyle("-fx-background-image: url('Images/1.png');" + "-fx-background-size:cover");
            Scene adminScene = new Scene(root);
            Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
            window.setScene(adminScene);
            window.show();
        } catch(IOException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("FXML");
            alert.setHeaderText("Load failure");
            alert.setContentText("Failed to load the FXML file.");
            alert.showAndWait();
        }

    }

    @FXML
    void exit(ActionEvent event) {
        System.exit(1);
    }

    @FXML
    void startGame(ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("../View/PrePlayScreen.fxml"));
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
    void questions(ActionEvent event) {
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

    @FXML
    void rules(ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("../View/RulesScreen.fxml"));
            root.setStyle("-fx-background-image: url('Images/1.png');" + "-fx-background-size:cover");
            Scene adminScene = new Scene(root);
            Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
            window.setScene(adminScene);
            window.show();
        } catch(IOException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("FXML");
            alert.setHeaderText("Load failure");
            alert.setContentText("Failed to load the FXML file.");
            alert.showAndWait();
        }
    }

    @FXML
    void timer(ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("../View/Timer.fxml"));
            root.setStyle("-fx-background-image: url('Images/1.png');" + "-fx-background-size:cover");
            Scene adminScene = new Scene(root);
            Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
            window.setScene(adminScene);
            window.show();
        } catch(IOException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("FXML");
            alert.setHeaderText("Load failure");
            alert.setContentText("Failed to load the FXML file.");
            alert.showAndWait();
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
