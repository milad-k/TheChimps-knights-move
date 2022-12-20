package Controller;

import Model.SysData;
import Model.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class UpdateUserController implements Initializable {

    @FXML
    private Button backButton;

    @FXML
    private TextField editUserIdField = new TextField();

    @FXML
    private TextField editUsernameField = new TextField();

    @FXML
    private Button updateUserButton;

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

    @FXML
    void updateUser(ActionEvent event) {
        String userID = editUserIdField.getText();
        String username = editUsernameField.getText();

        if(userID.isEmpty() || username.isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Missing fields");
            alert.setContentText("You must fill all the fields");
            alert.show();
        } else {
            User u1 = new User(userID, username);
            u1.setId(userID);
            u1.setUsername(username);
            boolean isAdded = SysData.getInstance().updateUser(UsersController.updatedU, u1);
            System.out.println(isAdded);
            if(!isAdded) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Same User exists");
                alert.setContentText("You must enter a different Username");
                alert.show();
            } else {
                SysData.getInstance().usersObservableMethod();
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Done");
                alert.setContentText("User was updated successfully!");
                alert.show();
            }
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        User u = UsersController.updatedU;
        editUserIdField.setText(u.getId());
        editUsernameField.setText(u.getUsername());
    }
}
