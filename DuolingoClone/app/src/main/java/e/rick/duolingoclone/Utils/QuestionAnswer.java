package e.rick.duolingoclone.Utils;

import java.util.ArrayList;
import java.util.Random;

import e.rick.duolingoclone.Tasks.WordTask.QuestionModel;

/**
 * Created by Rick on 3/2/2018.
 */

public class QuestionAnswer {

    static QuestionAnswer INSTANCE;

    QuestionModel questionModel;

    ArrayList<String> question = new ArrayList<>();
    ArrayList<String> answer = new ArrayList<>();

    Random random = new Random();

    public static QuestionAnswer getInstance() {

        if (INSTANCE == null) {

            INSTANCE = new QuestionAnswer();
        }

        return INSTANCE;
    }

    public QuestionModel getRandomQuestionObj() {

        //Question
        question.add("Ella come manzanas");
        question.add("El come");
        question.add("Usted es una mujer");
        question.add("Tu eres un nino");
        question.add("Que paso");
        question.add("Yo soy un nino");

        //Answer
        answer.add("She eats apple");
        answer.add("He eats");
        answer.add("You are a woman");
        answer.add("You are a boy");
        answer.add("What happened");
        answer.add("I am a boy");

        int randomIndex = random.nextInt(question.size());

        questionModel = new QuestionModel(
                question.get(randomIndex),
                answer.get(randomIndex));

        return questionModel;
    }

    public ArrayList<String> getAnswer() {
        return answer;
    }
}
