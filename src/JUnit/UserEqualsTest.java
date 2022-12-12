package JUnit;

import Model.Question;
import Model.User;
import Utils.Difficulty;
import Utils.Team;
import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class UserEqualsTest {
    User q = new User("u");
    User q1 = new User(null);
    User q2 = new User("m");
    User q3 = new User("u");
    Question q4 = new Question("q",2, Difficulty.HARD, Team.Chimp);

    @Test
    public void test(){
        assertFalse(q.equals(q1));
        assertFalse(q.equals(q2));
        assertFalse(q.equals(q3));
        assertFalse(q.equals(q4));
        assertTrue(q.equals(q));




    }





}