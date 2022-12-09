package Model;

import java.util.Objects;

public class User {

    private static int idCounter = 1;
    private int id;
    private String username;
    //private String password;

    public User(String username/*, String password*/) {
        this.id = idCounter++;
        this.username = username;
        //this.password = password;
    }

    public static int getIdCounter() {
        return idCounter;
    }

    public static void setIdCounter(int idCounter) {
        User.idCounter = idCounter;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    /* public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    } */

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        User user = (User) o;

        if (id != user.id) return false;
        return !Objects.equals(username, user.username);
        //return Objects.equals(password, user.password);
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (username != null ? username.hashCode() : 0);
       // result = 31 * result + (password != null ? password.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                //", password='" + password + '\'' +
                '}';
    }
}
