package e.rick.duolingoclone.Data;

import android.support.annotation.NonNull;

import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

import e.rick.duolingoclone.Model.PairModel;
import e.rick.duolingoclone.Model.QuestionModel;
import e.rick.duolingoclone.Model.UserData;

/**
 * Created by Rick on 3/6/2018.
 */

public class Repository implements DataSource.Local, DataSource.Remote {

    private static Repository INSTANCE;

    private final DataSource.Local dataSourceLocal;
    private final DataSource.Remote dataSourceRemote;

    private Repository(
            @NonNull DataSource.Local questionDataSource,
            @NonNull DataSource.Remote dataSourceRemote) {
        this.dataSourceLocal = questionDataSource;
        this.dataSourceRemote = dataSourceRemote;

    }

    public static Repository getInstance(
            DataSource.Local dataSourceLocal,
            DataSource.Remote dataSourceRemote) {

        if (INSTANCE == null) {

            INSTANCE = new Repository(dataSourceLocal, dataSourceRemote);
            dataSourceRemote.getDatabaseInstance();
        }

        return INSTANCE;
    }

    @Override
    public FirebaseDatabase getDatabaseInstance() {
        return dataSourceRemote.getDatabaseInstance();
    }

    @Override
    public ArrayList<PairModel> getPairs() {
        return dataSourceLocal.getPairs();
    }

    @Override
    public QuestionModel getRandomQuestionObj() {
        return dataSourceLocal.getRandomQuestionObj();
    }

    @Override
    public ArrayList<String> getAnswer() {
        return dataSourceLocal.getAnswer();
    }

    @Override
    public void setNewLanguage(String language) {
        dataSourceRemote.setNewLanguage(language);
    }

    @Override
    public void setDailyXp(int xp) {
        dataSourceRemote.setDailyXp(xp);
    }

    @Override
    public void setUserTotalXp(int xp) {
        dataSourceRemote.setUserTotalXp(xp);
    }

    @Override
    public void setLastTimeVisited() {
        dataSourceRemote.setLastTimeVisited();
    }

    @Override
    public void setDailyGoal(int dailyGoal) {
        dataSourceRemote.setDailyGoal(dailyGoal);
    }

    @Override
    public void setUserInfo(UserData userData) {
        dataSourceRemote.setUserInfo(userData);
    }

    @Override
    public void setLessonComplete(String lesson, boolean completeness) {
        dataSourceRemote.setLessonComplete(lesson, completeness);
    }

    @Override
    public void setLessonCompleteDate(String lesson) {
        dataSourceRemote.setLessonCompleteDate(lesson);
    }

    @Override
    public void getDailyGoal() {
        dataSourceRemote.getDailyGoal();
    }

    @Override
    public void getDailyXp() {
        dataSourceRemote.getDailyXp();
    }

    @Override
    public void getWeekXp() {
        dataSourceRemote.getWeekXp();
    }

    @Override
    public void getLessonCompleted() {
        dataSourceRemote.getLessonCompleted();
    }
}
