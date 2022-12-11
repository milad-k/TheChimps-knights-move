package Model;

import javafx.geometry.Insets;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;

import java.util.ArrayList;
import java.util.Random;

public class ChessBoard {

    GridPane chessBoard;
    String theme;

    public ArrayList<Square> squares = new ArrayList<>();

    public ChessBoard(GridPane chessBoard, String theme) {
        this.chessBoard = chessBoard;
        this.theme = theme;
        makeBoard(this.chessBoard, theme);
    }

    private void makeBoard(GridPane chessBoard, String theme) {
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
        addRandomJumpSquares();
    }

    private void setTheme(Square square, String theme, int i, int j) {
        Color color1 = Color.web("#ffffff00");
        Color color2 = Color.web("#ffffff00");

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

        if ((i + j) % 2 == 0) {
            square.setBackground(new Background(new BackgroundFill(color1, CornerRadii.EMPTY, Insets.EMPTY)));
        } else {
            square.setBackground(new Background(new BackgroundFill(color2, CornerRadii.EMPTY, Insets.EMPTY)));
        }
    }

    private void addPiece(Square square, Piece piece) {
        square.getChildren().add(piece);
        square.occupied = true;
    }

    private void addPieces() {
        for (Square square : squares) {
            if (square.occupied)
                continue;
            if (square.y == 0) {
                /*if(square.x == 7){
                    addPiece(square, new King("white", square.x, square.y));
                }*/
                if (square.x == 7) {
                    addPiece(square, new Queen("white", square.x, square.y));
                }
                if (square.x == 0) {
                    addPiece(square, new Knight("white", square.x, square.y));
                }
            }
        }
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

        private void addForgetfulSquares() {

            Color color1 = Color.web("black");
            for (int i = 0; i < 3; i++) {
                Random rand = new Random();
                int int_rand = rand.nextInt(64);
                squares.get(int_rand).setType("Forgetful Square");

            }

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
    private void addBlockingSquares() {

        Color color1 = Color.web("black");
        for (int i = 0; i < 8; i++) {
            Random rand = new Random();
            int int_rand = rand.nextInt(64);
            squares.get(int_rand).setType("Blocking Square");

        }

    }
    }

