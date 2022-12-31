package Model;

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
}
