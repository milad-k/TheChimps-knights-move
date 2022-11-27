package Model;

import Utils.Difficulty;
import Utils.Team;

import java.util.ArrayList;
import java.util.Objects;

public class Question {

    private String text;
    private ArrayList<String> answers;
    private int correctAnswer;
    private Difficulty difficulty;
    private Team team;

    public Question(String text, int correctAnswer, Difficulty difficulty, Team team) {
        this.text = text;
        this.answers = new ArrayList<String>();
        this.correctAnswer = correctAnswer;
        this.difficulty = difficulty;
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

    public ArrayList<String> getAnswers() {
        return answers;
    }

    public void setAnswers(ArrayList<String> answers) {
        this.answers = answers;
    }

    public int getCorrectAnswer() {
        return correctAnswer;
    }

    public void setCorrectAnswer(int correctAnswer) {
        this.correctAnswer = correctAnswer;
    }

    public Difficulty getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(Difficulty difficulty) {
        this.difficulty = difficulty;
    }

    public Team getTeam() {
        return team;
    }

    public void setTeam(Team team) {
        this.team = team;
    }

    @Override
    public String toString() {
        return "Question{" +
                "text='" + text + '\'' +
                ", answers=" + answers +
                ", correctAnswer=" + correctAnswer +
                ", difficulty=" + difficulty +
                ", team=" + team +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Question question = (Question) o;

        if (correctAnswer != question.correctAnswer) return false;
        if (!Objects.equals(text, question.text)) return false;
        if (!Objects.equals(answers, question.answers)) return false;
        if (difficulty != question.difficulty) return false;
        return team == question.team;
    }

    @Override
    public int hashCode() {
        int result = text != null ? text.hashCode() : 0;
        result = 31 * result + (answers != null ? answers.hashCode() : 0);
        result = 31 * result + correctAnswer;
        result = 31 * result + (difficulty != null ? difficulty.hashCode() : 0);
        result = 31 * result + (team != null ? team.hashCode() : 0);
        return result;
    }

    public boolean addAnswer(String answerToAdd) {

        if(!this.answers.contains(answerToAdd) && answerToAdd != null) {
            this.answers.add(answerToAdd);
            return true;
        }
        return false;

    }

    public boolean removeAnswer(String answerToRemove) {

        if(this.answers.contains(answerToRemove) && answerToRemove != null) {
            this.answers.remove(answerToRemove);
            return true;
        }
        return false;

    }

}
