package Model;

import Utils.Stage;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;

import java.util.Random;

public class StageFourthChessBoard extends ChessBoard{

    public StageFourthChessBoard(GridPane chessBoard, String theme) {
        super(chessBoard, theme);
        this.stage = Stage.Fourth;
    }

    private void addBlockingSquares() {

        Color color1 = Color.web("black");
        for (int i = 0; i < 8; i++) {
            Random rand = new Random();
            int int_rand = rand.nextInt(64);
            squares.get(int_rand).setType("Blocking Square");

        }

    }
}
