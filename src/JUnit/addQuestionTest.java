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


public class addQuestionTest {
    @Test
    public void test(){
        SysData.getInstance().loadQuestions(null);
        HashMap<Difficulty, ArrayList<Question>> questions = SysData.getInstance().getQuestions();
        ArrayList<Question> arrayList = questions.get(Difficulty.MEDIUM);

        Question question = null;
        Question question1 = null;

        for(Question q : arrayList) {
            if(q.getText().equals("q2")) {
                question = q;
            }
        }
        Question q3 = new Question("q","answer1","answer2","answer3","answer4", "answer1", Difficulty.EASY, "Chimp");
        assertTrue(SysData.getInstance().addQuestion(q3));
        assertFalse(SysData.getInstance().addQuestion(question));
        assertFalse(SysData.getInstance().addQuestion(question1));



    }
}
