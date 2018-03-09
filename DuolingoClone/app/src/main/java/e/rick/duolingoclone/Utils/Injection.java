package e.rick.duolingoclone.Utils;

import e.rick.duolingoclone.Data.Local.QuestionDataSource;
import e.rick.duolingoclone.Data.Remote.FirebaseDatabaseHelper;
import e.rick.duolingoclone.Data.Repository;

/**
 * Created by Rick on 3/6/2018.
 */

public class Injection {

    public static Repository provideRepository() {

        return Repository.getInstance(
                QuestionDataSource.getInstance(),
                FirebaseDatabaseHelper.getHelperInstance());
    }

    public static FirebaseAuthHelper providesAuthHelper() {

        return FirebaseAuthHelper.getClassInstance();
    }
}
