package JUnit;

import Model.Question;
import Utils.Difficulty;
import Utils.Team;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;



public class QuestionEqualsTest {
    Question q = new Question("q","answer1","answer2","answer3","answer4","answer2", Difficulty.HARD, "Chimp");
    Question q1 = new Question("q","answer1","answer2","answer3","answer4","answer2", Difficulty.EASY, "Chimp");
    Question q2 = new Question("q","answer1","answer2","answer3","answer4","answer4", Difficulty.HARD, "Tiger");
    Question q3 = new Question("q","answer1","answer2","answer3","answer4","answer3", Difficulty.HARD, "Chimp");
    Question q4 = new Question("q1","answer1","answer2","answer3","answer4","answer2", Difficulty.HARD, "Chimp");
    Question q5 = new Question("q","answer1","answer2","answer3","answer4","answer2", Difficulty.HARD, "Chimp");

    @Test
    public void test(){
        assertTrue(q.equals(q5));
        assertFalse(q.equals(q2));
        assertFalse(q.equals(q3));
        assertFalse(q.equals(q4));
        assertFalse(q5.equals(q1));
        assertFalse(q5.equals(q2));
        assertFalse(q5.equals(q3));




    }

}