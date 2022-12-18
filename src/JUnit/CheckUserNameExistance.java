package JUnit;

import Model.SysData;
import Model.User;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class CheckUserNameExistance {

    SysData sysData = SysData.getInstance();
    ArrayList<User> users = new ArrayList<User>();
    User u = new User("maryam");
    User u1 = new User("maram");
    User u2 = new User("milad");
    User u3 = new User("maria");
    User u4 = null;

    @Test
    public void test(){
        sysData.getUsers().add(u);
        sysData.getUsers().add(u);
        sysData.getUsers().add(u1);
        sysData.getUsers().add(u2);
        assertTrue(sysData.checkUsernameExistince("milad"));
        assertTrue(sysData.checkUsernameExistince("maryam"));
        assertTrue(sysData.checkUsernameExistince("maram"));
        assertFalse(sysData.checkUsernameExistince("maria"));
        assertFalse(sysData.checkUsernameExistince(null));

    }
}