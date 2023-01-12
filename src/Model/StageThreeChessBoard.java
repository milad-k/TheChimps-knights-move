package Model;

import Utils.Stage;
import javafx.geometry.Insets;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;

import java.util.Random;

public class StageThreeChessBoard extends ChessBoard{


    public StageThreeChessBoard(GridPane chessBoard, String theme) {
        super(chessBoard, theme);
        this.stage = Stage.Third;
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
        addForgetfulAndRandomJumpSquares();
        addRandomQuestionsSquares();
        addSwapWall();
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

    private void addForgetfulAndRandomJumpSquares() {

        Color color1 = Color.web("black");
        int i = 0;
        while(i < 2) {
            Random rand = new Random();
            int int_rand = rand.nextInt(64);
            if (!squares.get(int_rand).getType().equals("Normal Square")) {

            }
            else {
                squares.get(int_rand).setType("Forgetful Square");
                //squares.get(int_rand).setBackground(new Background(new BackgroundFill(color1, CornerRadii.EMPTY, Insets.EMPTY)));
                i++;
            }
            int j = 0;
            while(j < 2) {
                Random rand2 = new Random();
                int int_rand2 = rand2.nextInt(64);
                if (!squares.get(int_rand2).getType().equals("Normal Square")) {


                } else {
                    squares.get(int_rand2).setType("Random Jump Square");
                    //squares.get(int_rand).setBackground(new Background(new BackgroundFill(color1, CornerRadii.EMPTY, Insets.EMPTY)));
                    j++;
                }
            }

        }

    }
    private void addSwapWall(){
        Color color1 = Color.web("red");
        int i = 0;
        while(i < 2) {
            Random rand1 = new Random();
            int int_randCase = rand1.nextInt(2);
            if(int_randCase == 0) {
                Random rand = new Random();
                int int_rand = rand.nextInt(63) + 1;
                if(!squares.get(int_rand).getType().equals("Normal Square") || !squares.get(int_rand + 1).getType().equals("Normal Square") || !squares.get(int_rand - 1).getType().equals("Normal Square") || !(squares.get(int_rand).getY() < 7) || !(squares.get(int_rand).getY() > 0) || (int_rand == 57) || (int_rand == 1)){

                }
                else {
                    squares.get(int_rand).setType("Wall Square");
                    squares.get(int_rand + 1).setType("Wall Square");
                    squares.get(int_rand - 1).setType("Wall Square");
                    squares.get(int_rand).setBackground(new Background(new BackgroundFill(color1, CornerRadii.EMPTY, Insets.EMPTY)));
                    squares.get(int_rand + 1).setBackground(new Background(new BackgroundFill(color1, CornerRadii.EMPTY, Insets.EMPTY)));
                    squares.get(int_rand - 1).setBackground(new Background(new BackgroundFill(color1, CornerRadii.EMPTY, Insets.EMPTY)));
                    i++;
                }
                }
            if(int_randCase == 1){
                Random rand = new Random();
                int int_rand = rand.nextInt(64);
                if(!squares.get(int_rand).getType().equals("Normal Square") || !squares.get(int_rand + 8).getType().equals("Normal Square") || !squares.get(int_rand - 8).getType().equals("Normal Square")  || !(squares.get(int_rand).getX() < 7) || !(squares.get(int_rand).getX() > 0) || (int_rand == 8) || (int_rand == 48)){

                }
                else {
                    squares.get(int_rand).setType("Wall Square");
                    squares.get(int_rand + 8).setType("Wall Square");
                    squares.get(int_rand - 8).setType("Wall Square");
                    squares.get(int_rand).setBackground(new Background(new BackgroundFill(color1, CornerRadii.EMPTY, Insets.EMPTY)));
                    squares.get(int_rand + 8).setBackground(new Background(new BackgroundFill(color1, CornerRadii.EMPTY, Insets.EMPTY)));
                    squares.get(int_rand - 8).setBackground(new Background(new BackgroundFill(color1, CornerRadii.EMPTY, Insets.EMPTY)));
                    i++;
                }
                }
            }

        }
}
