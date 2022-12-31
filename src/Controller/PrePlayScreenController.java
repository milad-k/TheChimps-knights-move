package Controller;

import Model.SysData;
import Model.User;
import Utils.Theme;
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
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

public class PrePlayScreenController implements Initializable {

    ToggleGroup avatarGroup = new ToggleGroup();


    ToggleGroup modeGroup = new ToggleGroup();

    boolean flag = false;
    @FXML
    private Text invalidText;
    @FXML
    private Button backButton;

    @FXML
    private Button startButton;

    @FXML
    private ToggleButton avatarButton1;

    @FXML
    private ToggleButton avatarButton2;

    @FXML
    private ToggleButton avatarButton3;

    @FXML
    private ToggleButton avatarButton4;

    @FXML
    private ToggleButton avatarButton5;

    @FXML
    private ToggleButton avatarButton6;

    @FXML
    private ComboBox<Theme> themeBox = new ComboBox<Theme>();

    @FXML
    private TextField usernameField;

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
    void start(ActionEvent event) {
        String username = usernameField.getText();
        String selectedAvatar;
        if(avatarButton1.isSelected()) {
            selectedAvatar = "avatar1.png";
        } else if(avatarButton2.isSelected()) {
            selectedAvatar = "avatar2.png";
        } else if(avatarButton3.isSelected()) {
            selectedAvatar = "avatar3.png";
        } else if(avatarButton4.isSelected()) {
            selectedAvatar = "avatar4.png";
        } else if(avatarButton5.isSelected()) {
            selectedAvatar = "avatar5.png";
        } else if(avatarButton6.isSelected()) {
            selectedAvatar = "avatar6.png";
        } else {
            selectedAvatar = "";
        }
        Theme theme = themeBox.getValue();
        /* Here we check if the fields are empty/null then we get an invalid alert pop-up. */
        if(username == null || username.isEmpty()) {
            usernameField.setStyle("-fx-background-radius: 8px");
            usernameField.setStyle("-fx-border-color: red");
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Login");
            alert.setHeaderText("Invalid input");
            alert.setContentText("Please enter a username");
            alert.showAndWait();
        }

        else if (username.trim().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Login");
            alert.setHeaderText("Invalid input");
            alert.setContentText("Please enter letters/numbers");
            alert.showAndWait();
        }

        else if(selectedAvatar.equals("")) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Avatar");
            alert.setHeaderText("Select avatar");
            alert.setContentText("Please choose an avatar");
            alert.showAndWait();
        }

        else if(themeBox.getSelectionModel().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Theme");
            alert.setHeaderText("Select a theme");
            alert.setContentText("Please choose a theme for the game");
            alert.showAndWait();
        }

        /* function that check if the username is exists */
        else if(!(SysData.getInstance().checkUsernameExistince(username))) {
            SysData.getInstance().setCurrentUser(new User(username, selectedAvatar, theme.toString()));
            SysData.getInstance().addUser(new User(username, selectedAvatar, theme.toString()));
            try {
                Parent root = FXMLLoader.load(getClass().getResource("../View/Game.fxml"));
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
            usernameField.setStyle("-fx-border-color: red");
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Login");
            alert.setHeaderText("Verification Failed");
            alert.setContentText("Your username is already exist, do you want to continue your game?");
            Optional<ButtonType> result = alert.showAndWait();
            ButtonType button = result.orElse(ButtonType.OK);

            if(button == ButtonType.OK) {
                SysData.getInstance().setCurrentUser(SysData.getInstance().getUserByUserName(username));
                SysData.getInstance().updateUser(SysData.getInstance().getCurrentUser(), new User(username, selectedAvatar, theme.toString()));
                try {
                    Parent root = FXMLLoader.load(getClass().getResource("../View/Game.fxml"));
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
            else {
                try {
                    Parent root = FXMLLoader.load(getClass().getResource("../View/Game.fxml"));
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
        ObservableList<Theme> list = FXCollections.observableArrayList(Theme.values());
        themeBox.setItems(list);
        usernameField.textProperty().addListener((arg2, oldValue, newValue) -> {
            if(newValue.length() > 0 && flag) {
                usernameField.setStyle("");
                invalidText.setVisible(false);
                flag = false;
            }
        });
        avatarButton1.setToggleGroup(avatarGroup);
        avatarButton2.setToggleGroup(avatarGroup);
        avatarButton3.setToggleGroup(avatarGroup);
        avatarButton4.setToggleGroup(avatarGroup);
        avatarButton5.setToggleGroup(avatarGroup);
        avatarButton6.setToggleGroup(avatarGroup);
    }
}
