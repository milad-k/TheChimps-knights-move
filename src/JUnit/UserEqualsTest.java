package JUnit;
import Model.Question;
import Model.User;
import Utils.Difficulty;
import Utils.Theme;
import org.junit.Test;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class UserEqualsTest {
    User q = new User("u", "avatar1.png", "Sandcastle");
    User q3 = new User("u", "avatar1.png", "Sandcastle");
    Question q4 = new Question("q","answer1","answer2","answer3","answer4","answer2", Difficulty.HARD,"Chimp");

    @Test
    public void test1(){
        assertTrue(q.equals(q3));

    }
    @Test
    public void test2(){
        assertFalse(q.equals(q4));

    }




}
