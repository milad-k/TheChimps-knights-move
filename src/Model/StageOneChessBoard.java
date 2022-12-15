package Model;

import Utils.Stage;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;

import java.util.Random;

public class StageOneChessBoard extends ChessBoard{


    public StageOneChessBoard(GridPane chessBoard, String theme) {
        super(chessBoard, theme);
        this.stage = Stage.First;

    }

    private void addRandomJumpSquares() {

        Color color1 = Color.web("black");
        for (int i = 0; i < 3; i++) {
            Random rand = new Random();
            int int_rand = rand.nextInt(64);
            squares.get(int_rand).setType("Random Jump Square");
            //squares.get(int_rand).setBackground(new Background(new BackgroundFill(color1, CornerRadii.EMPTY, Insets.EMPTY)));

        }
    }
}
