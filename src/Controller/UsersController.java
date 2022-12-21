package Controller;

import Model.SysData;
import Model.User;
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
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class UsersController implements Initializable {

    @FXML
    private Button addUserButton;

    @FXML
    private Button backButton;

    @FXML
    private Button deleteUserButton;

    @FXML
    private ListView<User> list = new ListView<User>();;

    @FXML
    private Button updateUserButton;

    private ArrayList<User> users;

    SysData sysData = SysData.getInstance();

    static User updatedU;

    public ListView<User> getList() {
        return list;
    }

    public void setList(ListView<User> list) {
        this.list = list;
    }

    @FXML
    void addUser(ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("../View/AddUser.fxml"));
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
            Parent root = FXMLLoader.load(getClass().getResource("../View/AdminHomeScreen.fxml"));
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
    void deleteUser(ActionEvent event) {
        User u = list.getSelectionModel().getSelectedItem();
        if(!list.getItems().isEmpty() && u != null) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Remove User");
            alert.setHeaderText("Remove User?");
            alert.setContentText("Are you sure you want to remove this user?");
            alert.showAndWait();
            if(alert.getResult().equals(ButtonType.OK)) {
                list.getItems().remove(u);
                SysData.getInstance().removeUser(u);
                SysData.getInstance().usersObservableMethod();
            }
        }
        else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("No user selected");
            alert.setHeaderText("Select user");
            alert.setContentText("Please select a user to delete.");
            alert.showAndWait();
        }
    }

    @FXML
    @SuppressWarnings("unlikely-arg-type")
    void updateUser(ActionEvent event) {
        updatedU = list.getSelectionModel().getSelectedItem();
        if(updatedU == null) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("No User");
            alert.setContentText("You must select a user first");
            alert.show();
            return;
        }
        try {
            Parent root = FXMLLoader.load(getClass().getResource("../View/UpdateUser.fxml"));
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

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        SysData.getInstance().loadUsers("src/JSON/UsersFormat.json");

        users = sysData.getUsers();

        ObservableList<User> users1 = FXCollections.observableArrayList(users);
        list.setItems(users1);
    }
}
