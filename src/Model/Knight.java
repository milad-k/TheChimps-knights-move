package Model;

import java.util.ArrayList;

public class Knight extends Piece{

    public Knight(String color, int posX, int posY) {
        super(color, posX, posY);
        this.type = "Knight";
        setImage();
    }

    @Override
    public void getAllPossibleMovesForAdvancedLevels() {
        int x = this.posX;
        int y = this.posY;
        ArrayList<String> moves = new ArrayList<>();
        this.possibleMoves = new ArrayList<>();



        moves.add("Square" + (x-2) + (y-3));
        moves.add("Square" + (x-1) + (y-3));
        moves.add("Square" + (x+1) + (y-3));
        moves.add("Square" + (x+2) + (y-3));

        moves.add("Square" + (x-3) + (y-2));
        moves.add("Square" + (x+3) + (y-2));

        moves.add("Square" + (x-3) + (y-1));
        moves.add("Square" + (x+3) + (y-1));

        moves.add("Square" + (x-3) + (y+1));
        moves.add("Square" + (x+3) + (y+1));

        moves.add("Square" + (x-3) + (y+2));
        moves.add("Square" + (x+3) + (y+2));

        moves.add("Square" + (x-2) + (y+3));
        moves.add("Square" + (x-1) + (y+3));
        moves.add("Square" + (x+1) + (y+3));
        moves.add("Square" + (x+2) + (y+3));

        moves.add("Square" + (x-5) + (y-2));
        moves.add("Square" + (x-5) + (y-1));
        moves.add("Square" + (x-5) + (y+1));
        moves.add("Square" + (x-5) + (y+2));

        moves.add("Square" + (x-2) + (y+5));
        moves.add("Square" + (x-1) + (y+5));
        moves.add("Square" + (x+1) + (y+5));
        moves.add("Square" + (x+2) + (y+5));

        moves.add("Square" + (x+5) + (y-2));
        moves.add("Square" + (x+5) + (y-1));
        moves.add("Square" + (x+5) + (y+1));
        moves.add("Square" + (x+5) + (y+2));

        moves.add("Square" + (x-2) + (y-5));
        moves.add("Square" + (x-1) + (y-5));
        moves.add("Square" + (x+1) + (y-5));
        moves.add("Square" + (x+2) + (y-5));


        for(String move : moves) {
            if((getSquareByName(move) != null) && (!getSquareByName(move).type.equals("Blocking Square"))) {
                if(getSquareByName(move).occupied && getPieceByName(move).getColor().equals(Game.currentPlayer))
                    continue;
                possibleMoves.add(move);
            }
        }




    }

    @Override
    public void getAllPossibleMoves() {
        int x = this.posX;
        int y = this.posY;
        ArrayList<String> moves = new ArrayList<>();
        this.possibleMoves = new ArrayList<>();



        moves.add("Square" + (x+2) + (y+1));
        moves.add("Square" + (x+2) + (y-1));
        moves.add("Square" + (x+1) + (y+2));
        moves.add("Square" + (x-1) + (y+2));
        moves.add("Square" + (x-2) + (y+1));
        moves.add("Square" + (x-2) + (y-1));
        moves.add("Square" + (x+1) + (y-2));
        moves.add("Square" + (x-1) + (y-2));
        moves.add("Square" + (x+1) + (y+6));
        moves.add("Square" + (x-1) + (y-6));
        moves.add("Square" + (x-7) + (y-2));
        moves.add("Square" + (x+7) + (y+6));
        moves.add("Square" + (x+6) + (y+6));


        moves.add("Square" + (x-6) + (y-1));
        moves.add("Square" + (x-6) + (y+1));
        moves.add("Square" + (x-1) + (y-6));
        moves.add("Square" + (x+1) + (y-6));
        moves.add("Square" + (x-1) + (y+6));
        moves.add("Square" + (x+1) + (y+6));
        moves.add("Square" + (x+7) + (y-1));
        moves.add("Square" + (x+7) + (y+1));

        moves.add("Square" + (x-6) + (y-1));
        moves.add("Square" + (x-7) + (y-6));
        moves.add("Square" + (x-6) + (y-7));
        moves.add("Square" + (x-2) + (y-7));

        moves.add("Square" + (x-6) + (y+1));
        moves.add("Square" + (x-7) + (y+2));
        moves.add("Square" + (x-7) + (y+6));
        moves.add("Square" + (x-6) + (y+7));
        moves.add("Square" + (x-1) + (y+6));
        moves.add("Square" + (x-2) + (y+7));

        moves.add("Square" + (x+6) + (y+1));
        moves.add("Square" + (x+7) + (y+2));
        moves.add("Square" + (x+1) + (y+6));
        moves.add("Square" + (x+2) + (y+7));
        moves.add("Square" + (x+6) + (y+7));

        moves.add("Square" + (x+1) + (y-6));
        moves.add("Square" + (x+2) + (y-7));
        moves.add("Square" + (x+6) + (y-7));
        moves.add("Square" + (x+7) + (y-6));
        moves.add("Square" + (x+6) + (y-1));
        moves.add("Square" + (x+7) + (y-2));





        for(String move : moves) {
            if((getSquareByName(move) != null) && (!getSquareByName(move).type.equals("Blocking Square"))) {
                if(getSquareByName(move).occupied && getPieceByName(move).getColor().equals(Game.currentPlayer))
                    continue;
                possibleMoves.add(move);
            }
        }

    }
}
