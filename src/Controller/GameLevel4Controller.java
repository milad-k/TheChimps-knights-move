package Controller;

import Model.Game;
import Model.SysData;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.Window;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.TimeUnit;

import static Controller.GameController.staticmessage;

public class GameLevel4Controller implements Initializable {

    private long min, sec, hr, totalSec = 0;
    @FXML
    private ImageView avatarImage;
    @FXML
    private Button backButton;
    @FXML
    private Button settingsButton;
    @FXML
    private Button pauseButton;
    public static GridPane staticChessBoard4;
    @FXML
    private GridPane chessBoard4;
    @FXML
    private Text messageText4;
    @FXML
    private Text timerLabel;
    @FXML
    private Text pointsField;
    @FXML
    private Text pointsField1;
    public static Text staticPoints4;
    @FXML
    private Text usernameField;
    public static Text staticStage4;
    @FXML
    private Text StageField4;

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
                        //System.out.println(totalSec);
                        convertTime();
                        if(totalSec <= 0) {
                            if (Integer.parseInt(staticPoints4.getText().toString()) < 15) {
                                timer.cancel();
                                timerLabel.setText("00:00:00");
                                Stage stage4 = (Stage) Window.getWindows().get(0).getScene().getWindow();
                                stage4.close();
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
                            } else {
                                if(Integer.parseInt(staticPoints4.getText().toString()) >= 200){
                                    timer.cancel();
                                    timerLabel.setText("00:00:00");
                                    Stage stage4 = (Stage) Window.getWindows().get(0).getScene().getWindow();
                                    stage4.close();
                                    try {
                                        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../View/CupScreen.fxml"));
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
                                else{
                                timer.cancel();
                                timerLabel.setText("00:00:00");
                                Stage stage4 = (Stage) Window.getWindows().get(0).getScene().getWindow();
                                stage4.close();
                                try {
                                    FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../View/WinScreen.fxml"));
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
                                }}
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
        int i;
        Game game = new Game(chessBoard4, selectedTheme, SysData.getInstance().getCurrentUser(), "Fourth Stage ChessBoard", Utils.Stage.Fourth);
        i = Integer.parseInt(GameController.staticTotalPoints.getText().toString());
        pointsField.setText(String.valueOf(i));
        GameController.staticTotalPoints = pointsField;
        staticPoints4 = pointsField1;
        staticStage4 = StageField4;
        staticChessBoard4 = chessBoard4;
        staticmessage = messageText4;
        staticPoints4.setText("0");
        setTimer();
    }
}

