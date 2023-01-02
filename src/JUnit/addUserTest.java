package JUnit;

import Model.SysData;
import Model.User;
import Utils.Theme;
import org.junit.Test;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class addUserTest {
    SysData sysData = SysData.getInstance();
    User u = new User("maryam", "avatar1.png", "Sandcastle");
    User u3 = new User("maria", "avatar2.png", "Sandcastle");


    @Test
    public void addingNotExistingUser() {
        assertTrue(sysData.addUser(u3));



    }
    @Test
    public void addingExistingUser() {
        sysData.getUsers().add(u);
        assertFalse(sysData.addUser(u));



    }
}