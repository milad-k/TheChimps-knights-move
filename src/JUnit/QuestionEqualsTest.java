package JUnit;
import Model.Question;
import Utils.Difficulty;
import org.junit.Test;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;



public class QuestionEqualsTest {
    Question q = new Question("q","answer1","answer2","answer3","answer4","answer2", Difficulty.HARD, "Chimp");
    Question q2 = new Question("q","answer1","answer2","answer3","answer4","answer4", Difficulty.HARD, "Tiger");
    Question q5 = new Question("q","answer1","answer2","answer3","answer4","answer2", Difficulty.HARD, "Chimp");

    @Test
    public void CompareBetweenTwoEqualQuestions(){
        assertTrue(q.equals(q5));

    }
    @Test
    public void CompareBetweenTwoNotEqualQuestions(){
        assertFalse(q.equals(q2));

    }

}