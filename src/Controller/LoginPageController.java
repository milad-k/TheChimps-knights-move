package Controller;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

import Model.SysData;
import Model.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class LoginPageController implements Initializable {
    boolean flag = false;
    @FXML
    private Text invalidText;

    @FXML
    private Button loginButton;

   /* @FXML
    private PasswordField passwordField; */

    @FXML
    private TextField usernameField;

    @FXML
    void exit(ActionEvent event) {
        System.exit(1);
    }

    @FXML
    void login(ActionEvent event) {
        String username = usernameField.getText();
        //String password = passwordField.getText();
        /* Here we check if the fields are empty/null and if the username/password fields are empty/null we get an invalid alert pop-up. */
        if(username == null || username.isEmpty() /* || password == null || password.isEmpty() */) {
            usernameField.setStyle("-fx-background-radius: 8px");
            /* passwordField.setStyle("-fx-background-radius: 8px"); */
            usernameField.setStyle("-fx-border-color: red");
            /* passwordField.setStyle("-fx-border-color: red"); */
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Login");
            alert.setHeaderText("Invalid input");
            alert.setContentText("Please try again");
            alert.showAndWait();
        }

        /* Here we check if the fields are match the Manager's username and password and if there is a match then the program will login to the manager's account. */
        else if(username.equals("admin") || !(SysData.getInstance().checkUsernameExistince(username))/* && password.equals("admin")*/) {
            SysData.getInstance().addUser(new User(username));
            try {
                Parent root = FXMLLoader.load(getClass().getResource("../View/HomeScreen.fxml"));
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
        else {

            usernameField.setStyle("-fx-background-radius: 8px");
            /* passwordField.setStyle("-fx-background-radius: 8px"); */
            usernameField.setStyle("-fx-border-color: red");
            /* passwordField.setStyle("-fx-border-color: red"); */
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Login");
            alert.setHeaderText("Verification Failed");
            alert.setContentText("Your username is already exist, do you want to continue your game?");
            Optional<ButtonType> result = alert.showAndWait();
            ButtonType button = result.orElse(ButtonType.OK);

            if(button == ButtonType.OK){
                try {
                    Parent root = FXMLLoader.load(getClass().getResource("../View/HomeScreen.fxml"));
                    root.setStyle("-fx-background-image: url('Images/1.png');" + "-fx-background-size:cover");
                    Scene adminScene = new Scene(root);
                    Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
                    window.setScene(adminScene);
                    window.show();
                } catch(IOException e) {
                    Alert alert1 = new Alert(Alert.AlertType.ERROR);
                    alert1.setTitle("FXML");
                    alert1.setHeaderText("Load failure");
                    alert1.setContentText("Failed to load the FXML file.");
                    alert1.showAndWait();
                }
            }
            else{
                try {
                    Parent root = FXMLLoader.load(getClass().getResource("../View/LoginPage.fxml"));
                    root.setStyle("-fx-background-image: url('Images/backgroundWallpaper.jpeg');" + "-fx-background-size:cover");
                    Scene customerScene = new Scene(root);
                    Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
                    window.setScene(customerScene);
                    window.show();
                } catch (IOException e) {
                    Alert alert2 = new Alert(Alert.AlertType.ERROR);
                    alert2.setTitle("FXML");
                    alert2.setHeaderText("Load failure");
                    alert2.setContentText("Failed to load the FXML file.");
                    alert2.showAndWait();
                }
            }
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        usernameField.textProperty().addListener((arg2, oldValue, newValue) -> {
            if(newValue.length() > 0 && flag) {
                usernameField.setStyle("");
                /* passwordField.setStyle(""); */
                invalidText.setVisible(false);
                flag = false;
            }
        });
        /* passwordField.textProperty().addListener((arg2, oldValue, newValue) -> {
            if(newValue.length() > 0 && flag) {
                usernameField.setStyle("");
                passwordField.setStyle("");
                invalidText.setVisible(false);
                flag = false;
            }
        }); */
    }
}
