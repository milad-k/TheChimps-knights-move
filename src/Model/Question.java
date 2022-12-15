package Model;

import Utils.Difficulty;
import Utils.Team;

import java.awt.*;
import java.util.ArrayList;
import java.util.Objects;

public class Question {

    private String text;
    private String answer1;
    private String answer2;
    private String answer3;
    private String answer4;

    private String correct_ans;
    public Difficulty level;
    private String team;

    public Question(String text, String answer1, String answer2, String answer3, String answer4, String correct_ans, Difficulty level, String team) {
        this.text = text;
        this.answer1 = answer1;
        this.answer2 = answer2;
        this.answer3 = answer3;
        this.answer4 = answer4;
        this.correct_ans = correct_ans;
        this.level = level;
        this.team = team;
    }

    public Question() {

    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getAnswer1() {
        return answer1;
    }

    public void setAnswer1(String answer1) {
        this.answer1 = answer1;
    }

    public String getAnswer2() {
        return answer2;
    }

    public void setAnswer2(String answer2) {
        this.answer2 = answer2;
    }

    public String getAnswer3() {
        return answer3;
    }

    public void setAnswer3(String answer3) {
        this.answer3 = answer3;
    }

    public String getAnswer4() {
        return answer4;
    }

    public void setAnswer4(String answer4) {
        this.answer4 = answer4;
    }

    public String getCorrect_ans() {
        return correct_ans;
    }

    public void setCorrect_ans(String correct_ans) {
        this.correct_ans = correct_ans;
    }

    public Difficulty getLevel() {
        return level;
    }

    public void setLevel(Difficulty level) {
        this.level = level;
    }

    public String getTeam() {
        return team;
    }

    public void setTeam(String team) {
        this.team = team;
    }

    @Override
    public String toString() {
        return "Question: {" +
                "text='" + text + '\'' +
                ", correct_ans=" + correct_ans +
                ", level=" + level +
                ", team='" + team + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Question question = (Question) o;

        if (!Objects.equals(text, question.text)) return false;
        if (!Objects.equals(answer1, question.answer1)) return false;
        if (!Objects.equals(answer2, question.answer2)) return false;
        if (!Objects.equals(answer3, question.answer3)) return false;
        if (!Objects.equals(answer4, question.answer4)) return false;
        if (!Objects.equals(correct_ans, question.correct_ans))
            return false;
        if (level != question.level) return false;
        return Objects.equals(team, question.team);
    }

    @Override
    public int hashCode() {
        int result = text != null ? text.hashCode() : 0;
        result = 31 * result + (answer1 != null ? answer1.hashCode() : 0);
        result = 31 * result + (answer2 != null ? answer2.hashCode() : 0);
        result = 31 * result + (answer3 != null ? answer3.hashCode() : 0);
        result = 31 * result + (answer4 != null ? answer4.hashCode() : 0);
        result = 31 * result + (correct_ans != null ? correct_ans.hashCode() : 0);
        result = 31 * result + (level != null ? level.hashCode() : 0);
        result = 31 * result + (team != null ? team.hashCode() : 0);
        return result;
    }

    public Boolean checkCorrect(String answer) {
        if(answer.equals(this.correct_ans))
            return true;
        return false;
    }

}
