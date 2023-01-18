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

import static Controller.GameController.staticTotalPoints;

public class WinScreenController implements Initializable {

    @FXML
    private Button homeButton;

    @FXML
    private Button playagainButton;
    @FXML
    private Text totalScoreText;

    public void Home(ActionEvent actionEvent) {
        Stage stage = (Stage) homeButton.getScene().getWindow();
        stage.close();
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/View/HomeScreen.fxml"));
            root.setStyle("-fx-background-image: url('Images/backgroundWallpaper.jpeg');" + "-fx-background-size:cover");
            Scene customerScene = new Scene(root);
            Stage window = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
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

    public void playAgain(ActionEvent actionEvent) {
        Stage stage = (Stage) playagainButton.getScene().getWindow();
        stage.close();
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/View/Game.fxml"));
            root.setStyle("-fx-background-image: url('Images/backgroundWallpaper.jpeg');" + "-fx-background-size:cover");
            Scene customerScene = new Scene(root);
            Stage window = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
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

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        totalScoreText.setText(staticTotalPoints.getText());
    }
}
