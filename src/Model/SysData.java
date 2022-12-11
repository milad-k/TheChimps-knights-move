package Model;

import Utils.DataType;
import Utils.Difficulty;
import Utils.JsonParser;
import Utils.Team;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

public class SysData {

    private static SysData SysData;
    private HashMap<Difficulty, ArrayList<Question>> questions;
    private ArrayList<Game> games;
    private ArrayList<Game> pausedGames;
    private ArrayList<String> rules;
    private ArrayList<User> users;

    private String questionJSONPath = "src/JSON/QuestionsFormat.txt";
    private String originalPath = questionJSONPath;

    private String userJSONPath = "src/JSON/usersDb.txt";
    private String originalUsersPath = questionJSONPath;

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
        users = new ArrayList<>();
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

    public ArrayList<User> getUsers() { return users; }

    public void setUsers(ArrayList<User> users) { this.users = users; }

    public boolean addUser(User user){
        if(user == null){
          return false;
        }
        if (!users.contains(user)) {
            users.add(user);
            return true;
        }
        return false;

    }

    public boolean checkUsernameExistince(String username){
        if(username == null){
            return false;
        }
        for(User u : users){
            if(u.getUsername().equals(username)){
                return true;
            }}
    return false;
    }

    public boolean loadQuestions(String externalPath) {

        if(externalPath != null) {
            questionJSONPath = externalPath;
        }

        JSONParser parser = new JSONParser();

        try {
            FileInputStream fis = new FileInputStream(originalPath);
            BufferedReader reader = new BufferedReader(new InputStreamReader(fis));
            Object obj = parser.parse(reader);
            JSONObject jo = (JSONObject) obj;
            JSONArray questionsArray = (JSONArray) jo.get("questions");
            Iterator<JSONObject> questionsIterator = questionsArray.iterator();

            while(questionsIterator.hasNext()) {
                JSONObject q = questionsIterator.next();
                String text = (String) q.get("question");
                int correctAnswerNum = Integer.valueOf(q.get("correct_ans").toString());
                Difficulty level = getQuestionLevel(Integer.valueOf(q.get("level").toString()));
                Team team = Team.valueOf((String) q.get("team"));
                Question questionToAdd = new Question(text, correctAnswerNum, level, team);
                JSONArray answersArray = (JSONArray) q.get("answers");

                for(int i = 0; i < answersArray.size(); i++) {
                    String answer = (String) answersArray.get(i);
                    questionToAdd.addAnswer(answer);
                }

                if(!questions.containsKey(questionToAdd.getDifficulty())) {
                    questions.put(questionToAdd.getDifficulty(), new ArrayList<Question>());
                    questions.get(questionToAdd.getDifficulty()).add(questionToAdd);
                } else {
                    questions.get(questionToAdd.getDifficulty()).add(questionToAdd);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            resetPathToDefault();
            return false;
        }
        resetPathToDefault();
        return true;
    }

    public void saveQuestions(String externalPath) {

        if(externalPath != null) {
            questionJSONPath = externalPath;
        }

        try {
            JSONParser parser = new JSONParser();

            FileInputStream fis = new FileInputStream(originalPath);
            BufferedReader reader = new BufferedReader(new InputStreamReader(fis));
            Object obj = parser.parse(reader);
            JSONObject jo = (JSONObject) obj;
            jo.clear();
            JSONArray JSONQuestions = new JSONArray();
            JSONObject toWrite = new JSONObject();

            for (ArrayList<Question> list : questions.values()) {
                if(list == null)
                    continue;

                for (Question q : list) {
                    JSONObject jo1 = new JSONObject();
                    JSONArray answers = new JSONArray();
                    for (String a : q.getAnswers()) {
                        answers.add(a);
                    }

                    jo1.put("question", q.getText());
                    jo1.put("correct_ans", q.getCorrectAnswer());
                    jo1.put("level", q.getDifficulty());
                    jo1.put("team", q.getTeam().toString());
                    jo1.put("answers", answers);

                    JSONQuestions.add(jo1);
                }
                toWrite.put("questions", JSONQuestions);
            }
            FileWriter file = new FileWriter(originalPath);
            file.write(toWrite.toJSONString());
            file.flush();
            System.out.println("JSON Question was saved successfully");
        } catch (IOException ioe) {
            ioe.printStackTrace();
        } catch (ParseException pe) {
            pe.printStackTrace();
        }
        resetPathToDefault();
    }

    public boolean addQuestion(Question question) {
        if(question == null){
            return false;
        }
        ArrayList<Question> myArray = questions.get(question.getDifficulty());

        if(myArray == null) {
            myArray = new ArrayList<Question>();
            myArray.add(question);
            return true;
        }
        if(myArray.contains(question)){
            return false;
        }
         else if (!myArray.contains(question)) {
            myArray.add(question);
            return true;
        }
        questions.put(question.getDifficulty(), myArray);
        return true;
    }

    public boolean removeQuestion(Question question) {
        if(question == null){
            return false;
        }
        ArrayList<Question> myArray = questions.get(question.getDifficulty());
        if(myArray.contains(question)) {
            questions.get(question.getDifficulty()).remove(question);
            return true;
        }
        return false;
    }

    public boolean editQuestion(Question question, Question newQuestion) {
        if(question == null){
            return false;
        }
        if(newQuestion == null){
            return false;
        }
        if(removeQuestion(question)) {
            addQuestion(newQuestion);
            return true;
        }
        return false;
    }

    public Question popQuestion() {
        Object[] diff = questions.keySet().toArray();
        Difficulty key = (Difficulty) diff[new Random().nextInt(diff.length)];
        ArrayList<Question> myArray = questions.get(key);
        Question q = myArray.get(new Random().nextInt(myArray.size()));
        return q;
    }

    static Difficulty getQuestionLevel(int level) {
        if((level != 1) || (level != 2) || (level != 3)){
            return null;
        }
        if(level == 1)
            return Difficulty.EASY;
        else if (level == 2)
            return Difficulty.MEDIUM;
        else if (level == 3)
            return Difficulty.HARD;
        return Difficulty.MEDIUM;
    }

    private void resetPathToDefault() {
        questionJSONPath = originalPath;
    }

    public static String readFileAsString(String file) throws Exception {
        return new String(Files.readAllBytes(Paths.get(file)));
    }

    public boolean loadData(DataType type) {
        if(type == null)
            return false;

        try {
            if(type.equals(DataType.QUESTIONS)) {
                String file = "src/JSON/QuestionsFormat.txt";
                String json = readFileAsString(file);
                List<Question> questions = JsonParser.getInstance().parseToList(json, new Question());
                if(questions != null) {
                    questions.clear();
                    questions.addAll(questions);
                }
                return true;
            } else
                return false;
        } catch (Exception e) {
            e.printStackTrace();
            resetPathToDefault();
            return false;
        }
    }

    public boolean writeData(DataType type) {
        if(type == null)
            return false;
        FileWriter writer = null;

        try {
            if(type.equals(DataType.QUESTIONS)) {
                String filePath = "src/JSON/QuestionsFormat.txt";
                writer = new FileWriter(filePath);
                String parsedListToJSON = JsonParser.getInstance().parseListToJsonArray(questions, new Question());
                writer.write(parsedListToJSON);
                return true;
            }
            else
                return false;
        } catch (Exception e) {
            e.printStackTrace();
            resetPathToDefault();
            return false;
        } finally {
            try {
                writer.flush();
                writer.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void addPausedGame(Game game) {
        if(!pausedGames.contains(game))
            pausedGames.add(game);
    }

    public void addFinishedGame(Game game) {
        if(!games.contains(game)) {
            games.add(game);
        }
    }

}
