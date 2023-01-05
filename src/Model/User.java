package Model;

import Utils.Theme;

import java.util.Objects;

public class User {

    private String username;
    private int score;
    private String selectedAvatar;
    private String selectedTheme;

    private int lastScore;

    public User(String username) {
        super();
        score = 0;
        selectedAvatar = "avatar1.png";
        selectedTheme = "Sandcastle";
    }

    public User(String username, String selectedAvatar, String selectedTheme) {
        super();
        this.username = username;
        score = 0;
        this.selectedTheme = selectedTheme;
        this.selectedAvatar = selectedAvatar;
    }

    public User(String username, Integer score, String avatar) {
        super();
        this.username = username;
        this.lastScore = score;
        this.selectedAvatar = avatar;
    }

    public int getLastScore() {
        return lastScore;
    }

    public void setLastScore(int score) {
        this.lastScore = score;
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
        return score == user.score && Objects.equals(username, user.username) && Objects.equals(selectedAvatar, user.selectedAvatar) && Objects.equals(selectedTheme, user.selectedTheme);
    }

    @Override
    public int hashCode() {
        return Objects.hash(username, score, selectedAvatar, selectedTheme);
    }

    @Override
    public String toString() {
        return "Player: " +
                "'" + username + '\'' +
                ", Score: " + score;
    }
}
