package JUnit;

import Model.Question;
import Utils.Difficulty;
import Utils.Team;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class addAnswerTest {
    Question q = new Question("q", 2, Difficulty.MEDIUM, Team.Hawk);
    ArrayList<String> answers = new ArrayList<>();

    @Test
    public void test(){
        answers.add("answer1");
        answers.add("answer3");
        String answer2 = null;
        answers.add(answer2);

        q.setAnswers(answers);

        assertTrue(q.addAnswer("answer5"));
        assertFalse(q.addAnswer("answer1"));
        assertFalse(q.addAnswer("answer3"));
        assertFalse(q.addAnswer(answer2));

    }

}
