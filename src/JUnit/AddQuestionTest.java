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
import static org.junit.Assert.assertNotNull;


public class AddQuestionTest {
    @Test
    public void test(){
        SysData.getInstance().loadQuestions(null);
        HashMap<Difficulty, ArrayList<Question>> questions = SysData.getInstance().getQuestions();
        ArrayList<Question> arrayList = questions.get(Difficulty.EASY);

        Question question = null;
        Question question1 = null;

        for(Question q : arrayList) {
            if(q.getText().equals("q1")) {
                question = q;
            }
        }
        Question q3 = new Question("q", 1, Difficulty.EASY, Team.Chimp);
        assertTrue(SysData.getInstance().addQuestion(q3));
        assertFalse(SysData.getInstance().addQuestion(question));
        assertFalse(SysData.getInstance().addQuestion(question1));



    }
}
