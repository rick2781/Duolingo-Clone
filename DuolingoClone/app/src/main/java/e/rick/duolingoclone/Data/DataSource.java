package e.rick.duolingoclone.Data;

import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

import e.rick.duolingoclone.Model.PairModel;
import e.rick.duolingoclone.Model.QuestionModel;
import e.rick.duolingoclone.Model.UserData;

/**
 * Created by Rick on 3/6/2018.
 */

public interface DataSource {

    interface Local{

        ArrayList<PairModel> getPairs();

        QuestionModel getRandomQuestionObj();

        ArrayList<String> getAnswer();
    }

    interface Remote {

        FirebaseDatabase getDatabaseInstance();

        void setNewLanguage(String language);

        void setDailyXp(int xp);

        void setUserTotalXp(int xp);

        void setLastTimeVisited();

        void setDailyGoal(int dailyGoal);

        void setUserInfo(UserData userData);

        void setLessonProgress(String subject, String lesson, boolean completeness);

        void setLessonCompleteDate(String subject, String lesson);

        void getDailyGoal();

        void getDailyXp();

        void getWeekXp();
    }
}
