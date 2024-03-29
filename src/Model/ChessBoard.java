package Model;

import Utils.Stage;
import javafx.geometry.Insets;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;

import java.util.ArrayList;
import java.util.Random;

public abstract class ChessBoard {

    GridPane chessBoard;
    String theme;
    Stage stage;

    public ArrayList<Square> squares = new ArrayList<>();

    public ArrayList<String> QuestionSquares = new ArrayList<>();

    public ChessBoard(GridPane chessBoard, String theme) {
        this.chessBoard = chessBoard;
        this.theme = theme;

    }

    public ArrayList<String> getQuestionSquares() {
        return QuestionSquares;
    }

    public void setQuestionSquares(ArrayList<String> questionSquares) {
        QuestionSquares = questionSquares;
    }

    abstract void makeBoard(GridPane chessBoard, String theme);

    public void setTheme(Square square, String theme, int i, int j) {
        Color color1 = Color.web("#ffffff00");
        Color color2 = Color.web("#ffffff00");
        Color color3 = Color.rgb(181, 101, 118);

        switch (theme) {
            case "Coral" -> {
                color1 = Color.web("#b1e4b9");
                color2 = Color.web("#70a2a3");
            }
            case "Dusk" -> {
                color1 = Color.web("#cbb7ae");
                color2 = Color.web("#716677");
            }
            case "Wheat" -> {
                color1 = Color.web("#eaefce");
                color2 = Color.web("#bbbe65");
            }
            case "Marine" -> {
                color1 = Color.web("#9dacff");
                color2 = Color.web("#6f74d2");
            }
            case "Emerald" -> {
                color1 = Color.web("#adbd90");
                color2 = Color.web("#6e8f72");
            }
            case "Sandcastle" -> {
                color1 = Color.web("#e4c16f");
                color2 = Color.web("#b88b4a");
            }
        }
        if(i == 0 && j == 0){
            square.setBackground(new Background(new BackgroundFill(color3, CornerRadii.EMPTY, Insets.EMPTY)));
        }
        else if ((i + j) % 2 == 0) {
            square.setBackground(new Background(new BackgroundFill(color1, CornerRadii.EMPTY, Insets.EMPTY)));
        } else {
            square.setBackground(new Background(new BackgroundFill(color2, CornerRadii.EMPTY, Insets.EMPTY)));
        }
    }

    public void addPiece(Square square, Piece piece) {
        square.getChildren().add(piece);
        square.occupied = true;
    }

    abstract void addPieces();




    public ArrayList<Square> getSquares() {
        return squares;
    }
    public void setSquares(ArrayList<Square> squares) {
        this.squares = squares;
    }

    public void addRandomQuestionsSquares() {
        Color color1 = Color.rgb(181, 101, 118);
        if (getQuestionSquares().isEmpty()) {
            for (int i = 0; i < 3; i++) {
                Random rand = new Random();
                int int_rand = rand.nextInt(64);
                int columns = int_rand % 8;
                int rows = int_rand / 8;
//            System.out.println(rows + " " + columns);
                if(!(columns == 0 && rows == 0) && !(columns == 0 && rows == 7) && squares.get(int_rand).getType().equals("Normal Square")) {
                    this.QuestionSquares.add(new String("Square" + rows + columns));
                    squares.get(int_rand).setType("Question Square");
                    squares.get(int_rand).setBackground(new Background(new BackgroundFill(color1, CornerRadii.EMPTY, Insets.EMPTY)));
                    squares.get(int_rand).setBackground(new Background(new BackgroundImage(new Image("Model/pieces/questionMark.png"), BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, BackgroundSize.DEFAULT)));
                }
//                System.out.println("Square" + rows + columns);
            }
        } else if (getQuestionSquares().size() == 2) {
            Random rand = new Random();
            int int_rand = rand.nextInt(64);
            int columns = int_rand % 8;
            int rows = int_rand / 8;
//            System.out.println(rows + " " + columns);
            if(!(columns == 0 && rows == 0) && !(columns == 0 && rows == 7) && squares.get(int_rand).getType().equals("Normal Square")) {
                this.QuestionSquares.add(new String("Square" + rows + columns));
                squares.get(int_rand).setType("Question Square");
                squares.get(int_rand).setBackground(new Background(new BackgroundFill(color1, CornerRadii.EMPTY, Insets.EMPTY)));
                squares.get(int_rand).setBackground(new Background(new BackgroundImage(new Image("Model/pieces/questionMark.png"), BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, BackgroundSize.DEFAULT)));
            }
//            System.out.println("Square" + rows + columns);
        }
    }
}

