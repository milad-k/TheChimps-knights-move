package Model;

import Utils.Theme;

import java.util.Objects;

public class User {

    private static int idCounter = 1;
    private int id;
    private String username;
    private String selectedAvatar;
    private String selectedTheme;
    private int points;

    public User(String username) {
        this.id = idCounter++;
        this.username = username;
    }

    public User(String username, String selectedAvatar, String selectedTheme) {
        this.id = idCounter++;
        this.username = username;
        this.selectedAvatar = selectedAvatar;
        this.selectedTheme = selectedTheme;
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

    public String getSelectedAvatar() {
        return selectedAvatar;
    }

    public void setSelectedAvatar(String selectedAvatar) {
        this.selectedAvatar = selectedAvatar;
    }

    public String getSelectedTheme() {
        return selectedTheme;
    }

    public void setSelectedTheme(String selectedTheme) {
        this.selectedTheme = selectedTheme;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return id == user.id && points == user.points && Objects.equals(username, user.username) && Objects.equals(selectedAvatar, user.selectedAvatar) && selectedTheme == user.selectedTheme;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, username, selectedAvatar, selectedTheme, points);
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", selectedAvatar='" + selectedAvatar + '\'' +
                ", selectedTheme=" + selectedTheme +
                ", points=" + points +
                '}';
    }
}
