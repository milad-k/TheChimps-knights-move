package JUnit;

import Model.Question;
import Model.SysData;
import Utils.Difficulty;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;

import static org.junit.Assert.assertTrue;

public class LoadQuestionTest{

    @Test
    public void test() {
        SysData.getInstance().loadQuestions("src/JSON/QuestionsFormat.json");
        HashMap<Difficulty, ArrayList<Question>> questions = SysData.getInstance().getQuestions();
        SysData.getInstance().loadQuestions("src/JSON/QuestionsFormat.json");
        HashMap<Difficulty, ArrayList<Question>> questions2 = SysData.getInstance().getQuestions();

        assertTrue("Successful", questions.equals(questions2));
    }
}