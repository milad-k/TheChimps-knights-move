package Model;

import Utils.Difficulty;

import java.util.ArrayList;
import java.util.HashMap;

public class SysData {

    private static SysData SysData;
    private HashMap<Difficulty, ArrayList<Question>> questions;
    private ArrayList<Game> games;
    private ArrayList<Game> pausedGames;
    private ArrayList<String> rules;

    private String questionJSONPath = "src/JSON/QuestionsFormat.txt";
    private String originalPath = questionJSONPath;

    public static SysData getInstance() {
        if(SysData == null)
            SysData = new SysData();
        return SysData;
    }

    private SysData() {
        questions = new HashMap<Difficulty, ArrayList<Question>>();
        games = new ArrayList<Game>();
        pausedGames = new ArrayList<Game>();
        rules = new ArrayList<>();
    }

    public HashMap<Difficulty, ArrayList<Question>> getQuestions() {
        return questions;
    }

    public void setQuestions(HashMap<Difficulty, ArrayList<Question>> questions) {
        this.questions = questions;
    }

    public ArrayList<Game> getGames() {
        return games;
    }

    public void setGames(ArrayList<Game> games) {
        this.games = games;
    }

    public ArrayList<Game> getPausedGames() {
        return pausedGames;
    }

    public void setPausedGames(ArrayList<Game> pausedGames) {
        this.pausedGames = pausedGames;
    }

    public ArrayList<String> getRules() {
        return rules;
    }

    public void setRules(ArrayList<String> rules) {
        this.rules = rules;
    }

}
