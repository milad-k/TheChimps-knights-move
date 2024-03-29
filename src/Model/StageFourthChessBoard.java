package Model;

import Utils.Stage;
import javafx.geometry.Insets;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;

import java.util.Random;

public class StageFourthChessBoard extends ChessBoard{

    public StageFourthChessBoard(GridPane chessBoard, String theme) {
        super(chessBoard, theme);
        this.stage = Stage.Fourth;
        makeBoard(this.chessBoard, theme);

    }

    @Override
    void makeBoard(GridPane chessBoard, String theme) {
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                Square square = new Square(i, j);
                square.setName("Square" + i + j);
                square.setPrefHeight(100);
                square.setPrefWidth(100);
                square.setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));
                setTheme(square, theme, i, j);
                chessBoard.add(square, i, j, 1, 1);
                squares.add(square);
            }
        }
        addPieces();
        addBlockingSquares();
        addRandomQuestionsSquares();
    }

    @Override
    void addPieces() {
        for (Square square : squares) {
            if (square.occupied)
                continue;
            if (square.y == 0) {
                if (square.x == 7) {
                    addPiece(square, new King("black", square.x, square.y));
                }
                if (square.x == 0) {
                    addPiece(square, new Knight("white", square.x, square.y));
                }
            }
        }
    }

    private void addBlockingSquares() {

        Color color1 = Color.web("#FF0000");
        int i = 0;
        while(i < 8) {
            Random rand = new Random();
            int int_rand = rand.nextInt(64);
            if(!squares.get(int_rand).getType().equals("Normal Square") || ((squares.get(int_rand).getX() == 0) && (squares.get(int_rand).getY() == 0)) || ((squares.get(int_rand).getX() == 7) && (squares.get(int_rand).getY() == 0))){
                
            }
            else {
                squares.get(int_rand).setType("Blocking Square");
                squares.get(int_rand).setBackground(new Background(new BackgroundFill(color1, CornerRadii.EMPTY, Insets.EMPTY)));
            i++;
            }
        }

    }
}
