package e.rick.duolingoclone.Data;

import java.util.ArrayList;

import e.rick.duolingoclone.Model.PairModel;
import e.rick.duolingoclone.Model.QuestionModel;

/**
 * Created by Rick on 3/6/2018.
 */

public interface DataSource {

    ArrayList<PairModel> getPairs();

    QuestionModel getRandomQuestionObj();

    ArrayList<String> getAnswer();
}
