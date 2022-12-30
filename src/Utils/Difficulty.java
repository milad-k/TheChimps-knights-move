package Utils;

import Model.Question;

import java.util.Random;

public enum Difficulty {

    HARD, MEDIUM, EASY;


    public static Difficulty randomDifficulty()  {
         Random PRNG = new Random();

        Difficulty[] difficulties = values();
        int int_rand = PRNG.nextInt(3);
        return difficulties[int_rand];
    }
    public static int gettingWinningPoints(Question q){
        if(q.getLevel().equals(Difficulty.EASY)){
            return 1;
        }
        else if(q.getLevel().equals(Difficulty.MEDIUM)){
            return 2;
        }
        else{
            return 3;
        }
    }
    public static int gettingLoosingPoints(Question q){
        if(q.getLevel().equals(Difficulty.EASY)){
            return -2;
        }
        else if(q.getLevel().equals(Difficulty.MEDIUM)){
            return -3;
        }
        else{
            return -4;
        }
    }

}
