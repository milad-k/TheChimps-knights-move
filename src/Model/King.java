package Model;

import java.util.ArrayList;

public class King extends Piece {

    public King(String color, int posX, int posY) {
        super(color, posX, posY);
        this.type = "King";
        setImage();
    }

    @Override
    public void getAllPossibleMovesForAdvancedLevels() {
        getAllPossibleMoves();
    }

    @Override
    public void getAllPossibleMoves() {
        int x = this.posX;
        int y = this.posY;
        ArrayList<String> moves = new ArrayList<>();
        this.possibleMoves = new ArrayList<>();

        moves.add("Square" + (x) + (y-1));
        moves.add("Square" + (x+1) + (y-1));
        moves.add("Square" + (x+1) + (y));
        moves.add("Square" + (x+1) + (y+1));
        moves.add("Square" + (x) + (y+1));
        moves.add("Square" + (x-1) + (y+1));
        moves.add("Square" + (x-1) + (y));
        moves.add("Square" + (x-1) + (y-1));
        moves.add("Square" + (x) + (y+7));
        moves.add("Square" + (x+7) + (y));
        moves.add("Square" + (x) + (y-7));
        moves.add("Square" + (x-7) + (y));




        for(String move : moves) {
            if((getSquareByName(move) != null) && (!getSquareByName(move).type.equals("Blocking Square"))) {
                if(getSquareByName(move).occupied && getPieceByName(move).getColor().equals(Game.currentPlayer))
                    continue;
                possibleMoves.add(move);
            }
        }

    }
}
