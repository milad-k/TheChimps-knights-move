package Utils;

import java.util.Random;

public enum Difficulty {

    HARD, MEDIUM, EASY;


    public static Difficulty randomDifficulty()  {
         Random PRNG = new Random();

        Difficulty[] difficulties = values();
        int int_rand = PRNG.nextInt(3);
        return difficulties[int_rand];
    }

}
