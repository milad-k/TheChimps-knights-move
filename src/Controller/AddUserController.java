package Controller;

import Model.Question;
import Model.SysData;
import Model.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class AddUserController {

    @FXML
    private Button addUserButton;

    @FXML
    private Button backButton;

    @FXML
    private TextField userIdField;

    @FXML
    private TextField usernameField;

    @FXML
    void addUser(ActionEvent event) {
        String userId = userIdField.getText();
        String username = usernameField.getText();
        if(userId.isEmpty() || username.isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Type user information");
            alert.setContentText("You must type user's information");
            alert.show();
        } else {
            User u = new User(userId, username);
            boolean isAdded = SysData.getInstance().addUser(u);
            System.out.println(isAdded);
            if(!isAdded) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Same User exists");
                alert.setContentText("You must enter different User");
                alert.show();
            } else {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Done");
                alert.setContentText("User Was Added Succeccfully!");
                alert.show();
                SysData.getInstance().usersObservableMethod();
            }
        }
    }

    @FXML
    void back(ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("../View/Users.fxml"));
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
