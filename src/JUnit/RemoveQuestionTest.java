package JUnit;

import Model.Question;
import Model.SysData;
import Utils.Difficulty;
import Utils.Team;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class RemoveQuestionTest {

    @Test
    public void test() {
        SysData.getInstance().loadQuestions(null);
        HashMap<Difficulty, ArrayList<Question>> questions = SysData.getInstance().getQuestions();
        ArrayList<Question> arrayList = questions.get(Difficulty.EASY);

        Question question = null;

        for(Question q : arrayList) {
            if(q.getText().equals("q1")) {
                question = q;
            }
        }
        Question q3 = new Question("q", "answer1","answer2","answer3","answer4","answer3", Difficulty.EASY, "Chimp");
        assertTrue(SysData.getInstance().removeQuestion(question));
        assertFalse(SysData.getInstance().removeQuestion(q3));
    }
}
