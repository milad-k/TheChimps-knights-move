package JUnit;

import Model.SysData;
import Model.User;
import Utils.Theme;
import org.junit.Test;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class CheckUserNameExistance {

    SysData sysData = SysData.getInstance();
    User u2 = new User("milad", "avatar4.png", "Sandcastle");

    @Test
    public void checkingUserNameThatIsExist(){
        sysData.getUsers().add(u2);
        assertTrue(sysData.checkUsernameExistince("milad"));

    }
    @Test
    public void checkingUserNameThatIsnotExist(){

        assertFalse(sysData.checkUsernameExistince("maria"));

    }
}