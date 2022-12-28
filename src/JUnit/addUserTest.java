package JUnit;

import Model.SysData;
import Model.User;
import org.junit.Test;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class addUserTest {
    SysData sysData = SysData.getInstance();
    User u = new User("maryam", "avatar1.png");
    User u3 = new User("maria", "avatar2.png");


    @Test
    public void test1() {
        assertTrue(sysData.addUser(u3));



    }
    @Test
    public void test2() {
        sysData.getUsers().add(u);
        assertFalse(sysData.addUser(u));



    }
}