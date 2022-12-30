package Model;

import Utils.Difficulty;
import javafx.scene.layout.StackPane;

import java.util.Random;

public class Square extends StackPane {

    int x, y;
    boolean occupied;
    String name;

    String type;

    public Square(int x, int y) {
        this.x = x;
        this.y = y;
        this.occupied = false;
        this.type = "Normal Square";
    }

    @Override
    public String toString() {
        String status;
        if(this.occupied)
            status = "Occupied";
        else
            status = "Not occupied";
        return "Square";
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public boolean isOccupied() {
        return occupied;
    }

    public void setOccupied(boolean occupied) {
        this.occupied = occupied;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;

    }
}
