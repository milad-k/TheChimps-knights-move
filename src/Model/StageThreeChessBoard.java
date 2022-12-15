package Model;

import Utils.Stage;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;

import java.util.Random;

public class StageThreeChessBoard extends ChessBoard{


    public StageThreeChessBoard(GridPane chessBoard, String theme) {
        super(chessBoard, theme);
        this.stage = Stage.Third;
    }

    private void addForgetfulAndRandomJumpSquares() {

        Color color1 = Color.web("black");
        for (int i = 0; i < 2; i++) {
            Random rand = new Random();
            int int_rand = rand.nextInt(64);
            squares.get(int_rand).setType("Forgetful Square");

        }
        for (int i = 0; i < 2; i++) {
            Random rand = new Random();
            int int_rand = rand.nextInt(64);
            squares.get(int_rand).setType("Random Jump Square");

        }

    }
}
