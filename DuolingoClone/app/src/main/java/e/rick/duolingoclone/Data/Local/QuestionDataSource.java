package e.rick.duolingoclone.Data.Local;

import java.util.ArrayList;
import java.util.Random;

import e.rick.duolingoclone.Data.DataSource;
import e.rick.duolingoclone.Model.PairModel;
import e.rick.duolingoclone.Model.QuestionModel;

/**
 * Created by Rick on 3/2/2018.
 */

public class QuestionDataSource implements DataSource.Local {

    private static QuestionDataSource INSTANCE;

    QuestionModel questionModel;

    ArrayList<String> question = new ArrayList<>();
    ArrayList<String> answer = new ArrayList<>();

    Random random = new Random();

    public static QuestionDataSource getInstance() {

        if (INSTANCE == null) {

            INSTANCE = new QuestionDataSource();
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

    @Override
    public ArrayList<PairModel> getPairs() {

        ArrayList<PairModel> pairs = new ArrayList<>();

        pairs.add(new PairModel("manzanas", "apple"));
        pairs.add(new PairModel("leche", "milk"));
        pairs.add(new PairModel("bread", "pan"));
        pairs.add(new PairModel("boy", "nino"));
        pairs.add(new PairModel("ella", "she"));
        pairs.add(new PairModel("a", "un"));
        pairs.add(new PairModel("he", "el"));
        pairs.add(new PairModel("girl", "nina"));
        pairs.add(new PairModel("la", "the"));
        pairs.add(new PairModel("tu", "you"));
        pairs.add(new PairModel("bebes", "drink"));
        pairs.add(new PairModel("agua", "water"));

        return pairs;
    }

    @Override
    public ArrayList<String> getAnswer() {
        return answer;
    }
}
