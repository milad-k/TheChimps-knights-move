package Controller;

import Model.Game;
import Model.SysData;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;

import javax.management.Notification;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.TimeUnit;

public class GameLevel3Controller implements Initializable {

//    private static final Integer STARTTIME = 60;
//    private Timeline timeline;
//    private IntegerProperty timeSeconds = new SimpleIntegerProperty(STARTTIME);

    private long min, sec, hr, totalSec = 0;
    @FXML
    private ImageView avatarImage;
    @FXML
    private Button backButton;
    @FXML
    private Button settingsButton;
    @FXML
    private Button pauseButton;
    public static GridPane staticChessBoard3;
    @FXML
    private GridPane chessBoard;
    @FXML
    private Text timerLabel;
    @FXML
    private Text pointsField;
    public static Text staticPoints3;
    @FXML
    private Text usernameField;
    public static Text staticStage3;
    @FXML
    private Text StageField;

    private String format(long value) {
        if(value < 10) {
            return 0 + "" + value;
        }
        return value + "";
    }

    public void convertTime() {

        min = TimeUnit.SECONDS.toMinutes(totalSec);
        sec = totalSec - (min * 60);
        hr = TimeUnit.MINUTES.toHours(min);
        min = min - (hr * 60);

        timerLabel.setText(format(hr) + ":" + format(min) + ":" + format(sec));
        totalSec--;

    }

    private void setTimer() {
        totalSec = 60;

        Timer timer = new Timer();
        TimerTask timerTask = new TimerTask() {
            @Override
            public void run() {
                Platform.runLater(new Runnable() {
                    @Override
                    public void run() {
                        System.out.println(totalSec);
                        convertTime();
                        if(totalSec <= 0) {
                            if (Integer.parseInt(staticPoints3.getText().toString()) < 15) {
                                timer.cancel();
                                timerLabel.setText("00:00:00");
                                try {
                                    FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../View/LoserScreen.fxml"));
                                    Parent root1 = (Parent) fxmlLoader.load();
                                    Stage stage = new Stage();
                                    stage.setScene(new Scene(root1));
                                    stage.show();

                                } catch (IOException e) {
                                    Alert alert1 = new Alert(Alert.AlertType.ERROR);
                                    alert1.setTitle("FXML");
                                    alert1.setHeaderText("Load failure");
                                    alert1.setContentText("Failed to load the FXML file.");
                                    alert1.showAndWait();
                                }
                            }
                        }
                        else{
                            timer.cancel();
                            timerLabel.setText("00:00:00");
                            try {
                                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../View/GameLevel4.fxml"));
                                Parent root1 = (Parent) fxmlLoader.load();
                                Stage stage = new Stage();
                                stage.setScene(new Scene(root1));
                                stage.show();

                            } catch (IOException e) {
                                Alert alert1 = new Alert(Alert.AlertType.ERROR);
                                alert1.setTitle("FXML");
                                alert1.setHeaderText("Load failure");
                                alert1.setContentText("Failed to load the FXML file.");
                                alert1.showAndWait();
                            }
                        }

                    }
                });
            }
        };
        timer.schedule(timerTask, 0, 1000);
    }

    @FXML
    void back(ActionEvent event) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../View/ExitPopUp.fxml"));
            Parent root1 = (Parent) fxmlLoader.load();
            Stage stage = new Stage();
            stage.setScene(new Scene(root1));
            stage.show();

        } catch (IOException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("FXML");
            alert.setHeaderText("Load failure");
            alert.setContentText("Failed to load the FXML file.");
            alert.showAndWait();
        }
    }
    @FXML
    void pause(ActionEvent event) throws IOException {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../View/PausePopUp.fxml"));
            Parent root1 = (Parent) fxmlLoader.load();
            Stage stage = new Stage();
            stage.setScene(new Scene(root1));
            stage.show();

        } catch (IOException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("FXML");
            alert.setHeaderText("Load failure");
            alert.setContentText("Failed to load the FXML file.");
            alert.showAndWait();
        }
    }

    public void settings(ActionEvent actionEvent)  throws IOException {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../View/SettingPopUp.fxml"));
            Parent root1 = (Parent) fxmlLoader.load();
            Stage stage = new Stage();
            stage.setScene(new Scene(root1));
            stage.show();

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
        String username = SysData.getInstance().getCurrentUser().getUsername();
        String selectedAvatar = SysData.getInstance().getCurrentUser().getSelectedAvatar();
        String selectedTheme = SysData.getInstance().getCurrentUser().getSelectedTheme().toString();
        usernameField.setText(username);
        avatarImage.setImage(new Image("Controller/Images/" + selectedAvatar));
        staticPoints3 = pointsField;
        staticStage3 = StageField;
        staticChessBoard3 = chessBoard;
        Game game = new Game(chessBoard, selectedTheme, SysData.getInstance().getCurrentUser(), "Third Stage ChessBoard", Utils.Stage.Third);
        setTimer();
    }
}
