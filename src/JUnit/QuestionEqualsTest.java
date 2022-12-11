package JUnit;

import Model.Question;
import Utils.Difficulty;
import Utils.Team;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;



public class QuestionEqualsTest {
    Question q = new Question("q",2, Difficulty.HARD, Team.Chimp);
    Question q1 = new Question("q",2, Difficulty.EASY, Team.Chimp);
    Question q2 = new Question("q",2, Difficulty.HARD, Team.Tiger);
    Question q3 = new Question("q",3, Difficulty.HARD, Team.Chimp);
    Question q4 = new Question("q1",2, Difficulty.HARD, Team.Chimp);
    Question q5 = new Question("q",2, Difficulty.HARD, Team.Chimp);

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
