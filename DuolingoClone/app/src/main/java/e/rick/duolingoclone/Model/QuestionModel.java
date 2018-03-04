package e.rick.duolingoclone.Model;

/**
 * Created by Rick on 2/28/2018.
 */

public class QuestionModel {

    String question;
    String answer;

    public QuestionModel(String question, String answer) {
        this.question = question;
        this.answer = answer;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }
}
