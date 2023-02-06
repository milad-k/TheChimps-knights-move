package Controller;

import Model.Game;
import Model.SysData;
import javafx.application.Platform;
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
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.Window;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.TimeUnit;

import static Controller.GameController.staticStage;
import static Controller.GameController.staticmessage;

public class GameLevel3Controller implements Initializable {

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
    private GridPane chessBoard3;
    @FXML
    private Text messageText3;
    @FXML
    private Text timerLabel;
    @FXML
    private Text pointsField;
    @FXML
    private Text pointsField1;
    public static Text staticPoints3;
    @FXML
    private Text usernameField;
    public static Text staticStage3;
    @FXML
    private Text StageField3;
    Timer timer = new Timer();

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
                        if (totalSec <= 0) {
                            if (Integer.parseInt(staticPoints3.getText().toString()) < 2) {
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
                            else {
                            timer.cancel();
                            timerLabel.setText("00:00:00");
                            Stage stage4 = (Stage) Window.getWindows().get(0).getScene().getWindow();
                            stage4.close();
                            try {
                                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/View/GameLevel4.fxml"));
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
        String username3 = SysData.getInstance().getCurrentUser().getUsername();
        String selectedAvatar3 = SysData.getInstance().getCurrentUser().getSelectedAvatar();
        String selectedTheme3 = SysData.getInstance().getCurrentUser().getSelectedTheme().toString();
        Game game3 = new Game(chessBoard3, selectedTheme3, SysData.getInstance().getCurrentUser(), "Third Stage ChessBoard", Utils.Stage.Third);

        usernameField.setText(username3);
        avatarImage.setImage(new Image("Controller/Images/" + selectedAvatar3));
        int i;

        i = Integer.parseInt(GameController.staticTotalPoints.getText().toString());
        pointsField.setText(String.valueOf(i));
        GameController.staticTotalPoints = pointsField;
        staticPoints3 = pointsField1;
        staticStage = StageField3;
        staticChessBoard3 = chessBoard3;
        staticmessage = messageText3;
        staticPoints3.setText("0");
        staticStage.setText("3");
        setTimer(60);
    }
}
