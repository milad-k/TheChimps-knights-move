package Model;

import Utils.Stage;
import javafx.geometry.Insets;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Color;

import java.util.Stack;

import static Controller.GameController.staticPoints;
import static Controller.GameController.staticTotalPoints;
import static Controller.GameLevel2Controller.staticPoints2;
import static Controller.GameLevel3Controller.staticPoints3;
import static Controller.GameLevel4Controller.staticPoints4;

public class Move {
    private static int moveCounter;
    private int numberOfMove;
    private Square finalSquare;
    private int pointsOfMove;

    public Move(Square finalSquare, int pointsOfMove) {
        this.finalSquare = finalSquare;
        this.pointsOfMove = pointsOfMove;
        this.numberOfMove = moveCounter++;
    }


    public int getNumberOfMove() {
        return numberOfMove;
    }

    public void setNumberOfMove(int numberOfMove) {
        this.numberOfMove = numberOfMove;
    }

    public Square getFinalSquare() {
        return finalSquare;
    }

    public void setFinalSquare(Square finalSquare) {
        this.finalSquare = finalSquare;
    }

    public int getPointsOfMove() {
        return pointsOfMove;
    }

    public void setPointsOfMove(int pointsOfMove) {
        this.pointsOfMove = pointsOfMove;
    }

    public void changeSquareStatus(Square square){
    square.setOccupied(false);
    }
    public void changeSquareColor(Square square, String theme){
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

        if ((square.x + square.y) % 2 == 0) {
            square.setBackground(new Background(new BackgroundFill(color1, CornerRadii.EMPTY, Insets.EMPTY)));
        } else {
            square.setBackground(new Background(new BackgroundFill(color2, CornerRadii.EMPTY, Insets.EMPTY)));
        }


    }
    public void changePoints(Square square, Stage stage){
        if(this.pointsOfMove > 0){
            if(stage == Stage.First) {
                SysData.getInstance().getCurrentUser().setScore(SysData.getInstance().getCurrentUser().getScore() - 1);
                staticPoints.setText(String.valueOf(SysData.getInstance().getCurrentUser().getScore()));
                staticTotalPoints.setText(String.valueOf(SysData.getInstance().getCurrentUser().getScore()));
            }
            else if(stage == Stage.Second){
                SysData.getInstance().getCurrentUser().setScore(SysData.getInstance().getCurrentUser().getScore() - 1);
                int i = Integer.parseInt(staticPoints2.getText().toString());
                i--;
                staticPoints2.setText(String.valueOf(i));
                staticTotalPoints.setText(String.valueOf(SysData.getInstance().getCurrentUser().getScore()));

            }
            else if(stage == Stage.Third){
                SysData.getInstance().getCurrentUser().setScore(SysData.getInstance().getCurrentUser().getScore() - 1);
                int i = Integer.parseInt(staticPoints3.getText().toString());
                i--;
                staticPoints3.setText(String.valueOf(i));
                staticTotalPoints.setText(String.valueOf(SysData.getInstance().getCurrentUser().getScore()));

            }
            else if(stage == Stage.Fourth){
                SysData.getInstance().getCurrentUser().setScore(SysData.getInstance().getCurrentUser().getScore() - 1);
                int i = Integer.parseInt(staticPoints4.getText().toString());
                i--;
                staticPoints4.setText(String.valueOf(i));
                staticTotalPoints.setText(String.valueOf(SysData.getInstance().getCurrentUser().getScore()));

            }

        }
        else{
            if(stage.equals(Stage.First)) {
                SysData.getInstance().getCurrentUser().setScore(SysData.getInstance().getCurrentUser().getScore() + 1);
                staticPoints.setText(String.valueOf(SysData.getInstance().getCurrentUser().getScore()));
                staticTotalPoints.setText(String.valueOf(SysData.getInstance().getCurrentUser().getScore()));
            }
            else if(stage == Stage.Second){
                SysData.getInstance().getCurrentUser().setScore(SysData.getInstance().getCurrentUser().getScore() + 1);
                int i = Integer.parseInt(staticPoints2.getText().toString());
                i++;
                staticPoints2.setText(String.valueOf(i));
                staticTotalPoints.setText(String.valueOf(SysData.getInstance().getCurrentUser().getScore()));


            }
            else if(stage == Stage.Third){
                SysData.getInstance().getCurrentUser().setScore(SysData.getInstance().getCurrentUser().getScore() + 1);
                int i = Integer.parseInt(staticPoints3.getText().toString());
                i++;
                staticPoints3.setText(String.valueOf(i));
                staticTotalPoints.setText(String.valueOf(SysData.getInstance().getCurrentUser().getScore()));

            }
            else if(stage == Stage.Fourth){
                SysData.getInstance().getCurrentUser().setScore(SysData.getInstance().getCurrentUser().getScore() + 1);
                int i = Integer.parseInt(staticPoints4.getText().toString());
                i++;
                staticPoints4.setText(String.valueOf(i));
                staticTotalPoints.setText(String.valueOf(SysData.getInstance().getCurrentUser().getScore()));

            }
        }

    }
    public void removingLast3Moves(Stack<Move> moves, Square square, Stage stage, String theme){
        for(int i = 0; i<3;i++){
            moves.peek().changeSquareStatus(moves.peek().getFinalSquare());
            moves.peek().changeSquareColor(moves.peek().getFinalSquare(),theme);
            moves.peek().changePoints(moves.peek().getFinalSquare(), stage);
            moves.pop();
        }

    }
}
