package Model;

import Utils.Difficulty;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.*;
import java.util.*;

public class SysData {

    private static SysData SysData;
    private HashMap<Difficulty, ArrayList<Question>> questions;
    private ArrayList<Game> games;
    private ArrayList<User> users;

    public static SysData getInstance() {
        if(SysData == null)
            SysData = new SysData();
        return SysData;
    }

    public SysData() {
        questions = new HashMap<Difficulty, ArrayList<Question>>();
        games = new ArrayList<Game>();
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

    public ArrayList<User> getUsers() { return users; }

    public void setUsers(ArrayList<User> users) { this.users = users; }

    public boolean addUser(User user) {
        if(user == null) {
            return false;
        }
        if (!users.contains(user)) {
            users.add(user);
            return true;
        }
        return false;
    }

    public boolean checkUsernameExistince(String username) {
        if(username == null) {
            return false;
        }
        for(User u : users) {
            if(u.getUsername().equals(username)) {
                return true;
            }
        }
        return false;
    }

    public boolean loadQuestions(String path) {

        if (path != null) {
            JSONParser parser = new JSONParser();
            try {
                // get question's JSON file
                FileInputStream fis = new FileInputStream(path);

                BufferedReader reader = new BufferedReader(new InputStreamReader(fis));

                Object obj = parser.parse(reader);
                JSONObject jo = (JSONObject) obj;

                // convert question's JSON file to array .
                JSONArray quesArray = (JSONArray) jo.get("questions");

                //if the JSON file is empty and there is no questions
                if (Objects.isNull(quesArray)) {
                    return false;
                }

                // iterate over the values (questions).
                Iterator<JSONObject> quesIterator = quesArray.iterator();
                // get the questions data.
                while (quesIterator.hasNext()) {

                    JSONObject q = quesIterator.next();

                    // get question's content
                    String text = (String) q.get("question");

                    // get correct answer's number.
                    String correctAnswerNum = (String) q.get("correct_ans");

                    // get question's difficulty level.
                    Difficulty level = getQuestionLevel(q.get("level").toString());

                    // get question's created team name.
                    String team = (String) q.get("team");

                    // get question's answers.
                    JSONArray ansArray = (JSONArray) q.get("answer");


                    String a1 = (String) ansArray.get(0);
                    String a2 = (String) ansArray.get(1);
                    String a3 = (String) ansArray.get(2);
                    String a4 = (String) ansArray.get(3);

                    Question questionToAdd = new Question(text,a1,a2,a3,a4, correctAnswerNum, level, team);

                    if(!questions.containsKey(questionToAdd.getLevel())) {
                        questions.put(questionToAdd.getLevel(), new ArrayList<Question>());
                        questions.get(questionToAdd.getLevel()).add(questionToAdd);
                    } else {
                        if(!questions.get(questionToAdd.getLevel()).contains(questionToAdd)) {
                            questions.get(questionToAdd.getLevel()).add(questionToAdd);
                        }
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
                return false;
            }
            return true;
        }
        return false;
    }
    // Helper method to define question's difficulty level
    public Difficulty getQuestionLevel(String level) {

        if (level.equals("EASY"))
            return Difficulty.EASY;
        else if (level.equals("MEDIUM"))
            return Difficulty.MEDIUM;
        else if (level.equals("HARD"))
            return Difficulty.HARD;
        // Default difficulty for the question are MEDIUM
        return Difficulty.MEDIUM;
    }

    public boolean addQuestion(Question question) {

        if(Objects.isNull(question)) {
            return false;
        }
        //checking if nothing is null in the question object
        if(question.getText()!=null&&question.getAnswer1()!=null&&question.getAnswer2()!=null&&question.getAnswer3()!=null&&question.getAnswer4()!=null&&question.getCorrect_ans()!=null&&question.getLevel()!=null&&question.getTeam()!=null) {


            ArrayList<Question> myArray = questions.get(question.getLevel());
            if (myArray == null) {
                myArray = new ArrayList<Question>();
                myArray.add(question);
            } else if (!myArray.contains(question)) {
                myArray.add(question);

            }
            else {
                return false;
            }
            questions.put(question.getLevel(), myArray);
            return true;
        }
        else {
            return false;
        }
    }

    public boolean removeQuestion(Question question) {
        if(question!=null) {
            ArrayList<Question> myArray = questions.get(question.getLevel());
            if (myArray.contains(question)) {
                questions.get(question.getLevel()).remove(question);
                return true;
            }
        }
        return false;
    }

    public boolean updateQuestion(Question question, Question newQuestion) {
        if(newQuestion != null) {
            if(!questions.get(newQuestion.getLevel()).contains(newQuestion)) {
                if(removeQuestion(question)) {
                    addQuestion(newQuestion);
                    return true;
                }
            }
        }
        return false;
    }

    public Boolean writeJSON() {
            try {
                JSONObject JsonObject = new JSONObject();
                JSONArray JsonArray = new JSONArray();

                for (Difficulty key : questions.keySet()) {
                    for (Question qq : questions.get(key)) {
                        Map<String, Object> map = new LinkedHashMap<String, Object>(5);
                        map.put("question", qq.getText());
                        ArrayList<String> array = new ArrayList<String>();
                        array.add((String) qq.getAnswer1());
                        array.add((String) qq.getAnswer2());
                        array.add((String) qq.getAnswer3());
                        array.add((String) qq.getAnswer4());
                        map.put("answer", array);
                        map.put("correct_ans", (String) qq.getCorrect_ans());
                        map.put("level", (String) qq.getLevel().toString());
                        map.put("team", (String) qq.getTeam());
                        JsonArray.add(map);
                    }
                }
                JsonObject.put("questions", JsonArray);
                PrintWriter pw = new PrintWriter("src/JSON/QuestionsFormat.txt");
                pw.write(JsonObject.toJSONString());
                pw.flush();
                pw.close();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
                return false;
            } catch (Exception e) {
                e.printStackTrace();
                return false;
            }
            return true;
    }

    public void observableMethod() {
        writeJSON();
        loadQuestions("src/JSON/QuestionsFormat.txt");
    }

    public void observableMethodForGame() {
        writeJSON();
    }


/*
    public void addPausedGame(Game game) {
        if(!pausedGames.contains(game))
            pausedGames.add(game);
    }

    public void addFinishedGame(Game game) {
        if(!games.contains(game)) {
            games.add(game);
        }
    }
*/
}
