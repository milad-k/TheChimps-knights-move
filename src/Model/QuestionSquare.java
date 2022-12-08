package Model;

public class QuestionSquare extends Square{

    Question question;

    public QuestionSquare(int x, int y, Question question) {
        super(x, y);
        this.question = question;
        this.type = "Question Square";
    }



    public Question getQuestion() {
        return question;
    }

    public void setQuestion(Question question) {
        this.question = question;
    }

    @Override
    public String toString() {
        return "QuestionSquare{" +
                "question=" + question +
                '}';
    }
}
