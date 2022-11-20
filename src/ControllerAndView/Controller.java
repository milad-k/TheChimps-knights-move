package ControllerAndView;

import Model.Game;
import javafx.fxml.FXML;
import javafx.scene.layout.GridPane;

public class Controller {

    @FXML
    private GridPane chessBoard;

    public void initialize() {
        Game game = new Game(chessBoard, "Sandcastle");
    }
}
