package Model;

import javafx.event.EventHandler;
import javafx.scene.effect.Glow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;

import java.util.ArrayList;

abstract class Piece extends ImageView {
    abstract void getAllPossibleMoves();
    String type;
    String color;
    int posX, posY;
    ArrayList<String> possibleMoves;

    //Template method
    public final void getPossibleMoves(){
        getAllPossibleMoves();
    }

    public Piece(String color, int posX, int posY) {
        this.color = color;
        this.posX = posX;
        this.posY = posY;
        addEventHandler();
    }

    public String getColor() {
        return this.color;
    }

    public void setPiece(Image image) {
        this.setImage(image);
    }

    public void setImage() {
        this.setPiece(new Image("Model/pieces/" + this.color + "" + this.type +".png"));
    }

    private void addEventHandler() {
        this.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                getAllPossibleMoves();
            }
        });
    }



    public void showAllPossibleMoves(boolean val) {
        if(val) {
            Glow glow = new Glow();
            glow.setLevel(0.8);
            for(String move : possibleMoves) {
                Square square = getSquareByName(move);
                square.setEffect(glow);

                Piece piece = getPieceByName(move);
                if(piece == null)
                    continue;
                if(piece.type.equals("King")) {
                    square.setBorder(new Border(new BorderStroke(Color.DARKRED, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, new BorderWidths(1.5))));
                } else {
                    square.setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, new BorderWidths(1.2))));
                }
            }
        } else {
            for(String move : possibleMoves) {
                Square square = getSquareByName(move);
                square.setEffect(null);
                square.setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));
            }
        }
    }

    public Square getSquareByName(String name) {
        for(Square square : Game.cb.squares) {
            if(square.name.equals(name)) {
                return square;
            }
        }
        return null;
    }

    public Piece getPieceByName(String name) {
        for(Square square : Game.cb.squares) {
            if(square.getChildren().size() == 0)
                continue;
            if(square.name.equals(name))
                return (Piece) square.getChildren().get(0);
        }
        return null;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return this.color + " " + this.type;
    }

    public abstract void getAllPossibleMovesForAdvancedLevels();
}
