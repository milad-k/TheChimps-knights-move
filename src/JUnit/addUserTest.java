package JUnit;

import Model.SysData;
import Model.User;
import org.junit.Test;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class addUserTest {
    SysData sysData = SysData.getInstance();
    User u = new User("maryam");
    User u3 = new User("maria");


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