package JUnit;

import Model.Question;
import Model.SysData;
import Utils.Difficulty;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

import static org.junit.Assert.assertTrue;

public class LoadQuestionsTest {

    @Test
    public void test() {
        SysData.getInstance().loadQuestions(null);
        HashMap<Difficulty, ArrayList<Question>> questions = SysData.getInstance().getQuestions();
        SysData.getInstance().saveQuestions(null);

        SysData.getInstance().loadQuestions(null);
        HashMap<Difficulty, ArrayList<Question>> questions2 = SysData.getInstance().getQuestions();

        assertTrue("Successful", questions.equals(questions2));
    }
}
