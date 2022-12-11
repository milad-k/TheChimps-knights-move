package Controller;

import Model.User;
import Utils.UserType;

public class UserController {

    public static UserType userType;
    private static User user;

    public static UserType getUserType() {
        return userType;
    }

    public static void setUserType(UserType userType) {
        UserController.userType = userType;
    }

    public static User getUser() {
        return user;
    }

    public static void setUser(User user) {
        UserController.user = user;
    }
}
