package Controller;

import Model.SysData;
import Model.User;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class HistoryScreenController {

    @FXML
    private TableColumn<User, String> avatarHistory;

    @FXML
    private Button backButton1;

    @FXML
    private TableView<User> historyTable;

    @FXML
    private TableColumn<User, Integer> scoreHistory;

    @FXML
    private TableColumn<User, String> userNameHistory;

    private ArrayList<User> Players;

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

    public void initialize(URL location, ResourceBundle resources) {
        userNameHistory.setCellValueFactory(new PropertyValueFactory<>("nickname"));
        avatarHistory.setCellValueFactory(new PropertyValueFactory<>("avatar"));
        scoreHistory.setCellValueFactory(new PropertyValueFactory<>("GameHighScore"));
        setHistoryTable();
    }

    private void setHistoryTable() {
        Players = SysData.getInstance().getUsers();
        ObservableList<User> qs = FXCollections.observableArrayList(Players);
        historyTable.setItems(qs);
        historyTable.refresh();
    }


}
