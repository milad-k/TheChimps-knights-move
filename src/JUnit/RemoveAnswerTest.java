package JUnit;

import Model.Question;
import Utils.Difficulty;
import Utils.Team;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class RemoveAnswerTest {
    Question q = new Question("q", 2, Difficulty.MEDIUM, Team.Hawk);
    ArrayList<String> answers = new ArrayList<>();

    @Test
    public void test(){
        answers.add("answer1");
        answers.add("answer3");
        String answer2 = null;
        answers.add(answer2);

        q.setAnswers(answers);

        assertFalse(q.removeAnswer("answer5"));
        assertTrue(q.removeAnswer("answer1"));
        assertTrue(q.removeAnswer("answer3"));
        assertFalse(q.removeAnswer(answer2));
    }
}
