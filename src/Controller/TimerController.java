//package Controller;
//
//import javafx.event.ActionEvent;
//import javafx.fxml.FXML;
//import javafx.fxml.FXMLLoader;
//import javafx.fxml.Initializable;
//import javafx.scene.Parent;
//import javafx.scene.Scene;
//import javafx.scene.control.Alert;
//import javafx.scene.control.Button;
//import javafx.scene.control.Label;
//import javafx.stage.Stage;
//
//import java.io.IOException;
//import java.net.URL;
//import java.util.ResourceBundle;
//import java.util.Timer;
//import java.util.TimerTask;
//import java.util.concurrent.TimeUnit;
//
//public class TimerController implements Initializable {
//
//
//
//    private static String format(long value) {
//        if(value < 10) {
//            return 0 + "" + value;
//        }
//        return value + "";
//    }
//
//    public static void convertTime() {
//
//        min = TimeUnit.SECONDS.toMinutes(totalSec);
//        sec = totalSec - (min * 60);
//        hr = TimeUnit.MINUTES.toHours(min);
//        min = min - (hr * 60);
//
//        System.out.println(format(hr) + ":" + format(min) + ":" + format(sec));
//        totalSec--;
//
//    }
//    @FXML
//    private Button backButton;
//    @FXML
//    private Label timerLabel;
//
//    @FXML
//    void back(ActionEvent event) {
//        try {
//            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../View/HomeScreen.fxml"));
//            Parent root1 = (Parent) fxmlLoader.load();
//            Stage stage = new Stage();
//            stage.setScene(new Scene(root1));
//            stage.show();
//
//        } catch (IOException e) {
//            Alert alert = new Alert(Alert.AlertType.ERROR);
//            alert.setTitle("FXML");
//            alert.setHeaderText("Load failure");
//            alert.setContentText("Failed to load the FXML file.");
//            alert.showAndWait();
//        }
//    }
//
//    @Override
//    public void initialize(URL url, ResourceBundle resourceBundle) {
//
//        totalSec = 75;
//
//
//    }
//}
