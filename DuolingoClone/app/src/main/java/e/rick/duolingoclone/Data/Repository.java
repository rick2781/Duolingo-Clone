package e.rick.duolingoclone.Data;

import android.support.annotation.NonNull;

import java.util.ArrayList;

import e.rick.duolingoclone.Model.PairModel;
import e.rick.duolingoclone.Model.QuestionModel;

/**
 * Created by Rick on 3/6/2018.
 */

public class Repository implements DataSource {

    private static Repository INSTANCE;

    private final DataSource mQuestionDataSource;

    private Repository(@NonNull DataSource questionDataSource) {
        this.mQuestionDataSource = questionDataSource;

    }

    public static Repository getInstance(DataSource mQuestionDataSource) {

        if (INSTANCE == null) {

            INSTANCE = new Repository(mQuestionDataSource);
        }

        return INSTANCE;
    }

    @Override
    public ArrayList<PairModel> getPairs() {
        return mQuestionDataSource.getPairs();
    }

    @Override
    public QuestionModel getRandomQuestionObj() {
        return mQuestionDataSource.getRandomQuestionObj();
    }

    @Override
    public ArrayList<String> getAnswer() {
        return mQuestionDataSource.getAnswer();
    }
}
