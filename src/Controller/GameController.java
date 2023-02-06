package Controller;

import Model.Game;
import Model.SysData;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.Window;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.*;
import java.util.concurrent.TimeUnit;

public class GameController implements Initializable {

    public static long min, sec, hr, totalSec = 0;
    @FXML
    private ImageView avatarImage;
    @FXML
    private Button backButton;
    @FXML
    private Button settingsButton;
    @FXML
    private Text messageText;
    public static Text staticmessage;
    @FXML
    private Button pauseButton;
    public static GridPane staticChessBoard;
    @FXML
    private GridPane chessBoard;
    @FXML
    private Text timerLabel;
    public static Text staticTimerLabel;
    @FXML
    private Text pointsField;
    @FXML
    private Text pointsField1;
    public static Text staticPoints;

    public static MediaPlayer mediaPlayer;
    private File directory;
    private File[] files;
    private ArrayList<File> sounds;
    private int soundNumber;

    @FXML
    private Media media;
    public static Text staticTotalPoints;
    @FXML
    private Text usernameField;
    public static Text staticStage;
    @FXML
    private Text StageField;

    @FXML
    private Button continueButton;
    Timer timer = new Timer();
    @FXML
    void continueButton(ActionEvent event) {
        System.out.println(totalSec);
        Stage stage = (Stage) continueButton.getScene().getWindow();
        stage.close();
    }

    public void resetSomeTimer() {
        TimerTask timerTask = new TimerTask() {

            @Override
            public void run() {
                System.out.println("updating timer");
            }
        };
        timer.cancel();
        timer = new Timer();
        timer.schedule(timerTask, 1000);

    }
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

    private void setTimer(long givenTotalSec) {

        totalSec = givenTotalSec;


        TimerTask timerTask = new TimerTask() {
            @Override
            public void run() {
                Platform.runLater(new Runnable() {
                    @Override
                    public void run() {
                        //System.out.println(totalSec);
                        convertTime();
                        if(totalSec <= 0) {
                            if(Integer.parseInt(staticPoints.getText().toString()) < 2) {
                                timer.cancel();
                                timerLabel.setText("00:00:00");
                                Stage stage4 = (Stage) Window.getWindows().get(0).getScene().getWindow();
                                stage4.close();
                                try {
                                    FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/View/LoserScreen.fxml"));
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
                                    FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/View/GameLevel2.fxml"));
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
                    }
                });
            }
        };
        timer.schedule(timerTask, 0, 1000);
    }

    @FXML
    void back(ActionEvent event) {
        timer.cancel();
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Exit");
        alert.setHeaderText("Are you sure you want to exit?");
        alert.setContentText("YOU WILL LOSE YOUR SCORE!");
        Optional<ButtonType> result = alert.showAndWait();
        ButtonType button = result.orElse(ButtonType.OK);
        if(button == ButtonType.OK) {
            timer.cancel();
            try {
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/View/HomeScreen.fxml"));
                Parent root1 = (Parent) fxmlLoader.load();
                //Stage stage = new Stage();
                //stage.setScene(new Scene(root1));
                //stage.show();
                Scene adminScene = new Scene(root1);
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                stage.setScene(adminScene);
                stage.show();

            } catch (IOException e) {
                Alert alert2 = new Alert(Alert.AlertType.ERROR);
                alert2.setTitle("FXML");
                alert2.setHeaderText("Load failure");
                alert2.setContentText("Failed to load the FXML file.");
                alert2.showAndWait();
            }
        } else {
            resetSomeTimer();
            setTimer(totalSec);
        }
    }
    @FXML
    void pause(ActionEvent event) throws IOException {
        timer.cancel();
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/View/PausePopUp.fxml"));
            Parent root1 = (Parent) fxmlLoader.load();
            Stage stage = new Stage();
            stage.setScene(new Scene(root1));
            stage.showAndWait();
        } catch (IOException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("FXML");
            alert.setHeaderText("Load failure");
            alert.setContentText("Failed to load the FXML file.");
            alert.showAndWait();
        }
        resetSomeTimer();
        setTimer(totalSec);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        String username = SysData.getInstance().getCurrentUser().getUsername();
        String selectedAvatar = SysData.getInstance().getCurrentUser().getSelectedAvatar();
        String selectedTheme = SysData.getInstance().getCurrentUser().getSelectedTheme().toString();
        usernameField.setText(username);
        avatarImage.setImage(new Image("Controller/Images/" + selectedAvatar));
        staticTotalPoints = pointsField;
        staticPoints = pointsField1;
        staticStage = StageField;
        staticChessBoard = chessBoard;
        staticmessage = messageText;
        staticTotalPoints.setText("0");
        staticPoints.setText("0");
        totalSec = 60;
        staticTimerLabel = timerLabel;
        SysData.getInstance().getCurrentUser().setScore(0);
        Game game = new Game(chessBoard, selectedTheme, SysData.getInstance().getCurrentUser(), "First Stage ChessBoard", Utils.Stage.First);
        SysData.getInstance().getGames().add(game);
        setTimer(totalSec);

    }
}
