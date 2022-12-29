package Model;

import Utils.Theme;

import java.util.Objects;

public class User {

    private static int Serial = 0;
    private int id;
    private String username;
    private int score;
    private String selectedAvatar;
    private String selectedTheme;

    public User(String username) {
        super();
        id = ++Serial;
        score = 0;
        selectedAvatar = "avatar1.png";
        selectedTheme = "Sandcastle";
    }

    public User(String username, String selectedAvatar, String selectedTheme) {
        super();
        id = ++Serial;
        score = 0;
        this.selectedTheme = selectedTheme;
        this.selectedAvatar = selectedAvatar;
    }

    public static int getSerial() {
        return Serial;
    }

    public static void setSerial(int serial) {
        Serial = serial;
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

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public void updateScore(int scoreToAdd) {
        score += scoreToAdd;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return id == user.id && score == user.score && Objects.equals(username, user.username) && Objects.equals(selectedAvatar, user.selectedAvatar) && Objects.equals(selectedTheme, user.selectedTheme);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, username, score, selectedAvatar, selectedTheme);
    }

    @Override
    public String toString() {
        return "User{" +
                "username='" + username + '\'' +
                ", score=" + score +
                '}';
    }
}
