package Utils;

import java.util.Random;

public enum Difficulty {

    HARD, MEDIUM, EASY;

    public static Random PRNG = new Random();

    public static Difficulty randomDifficulty()  {
        Difficulty[] difficulties = values();
        int int_rand = PRNG.nextInt(values().length);
        return difficulties[int_rand];
    }

}
