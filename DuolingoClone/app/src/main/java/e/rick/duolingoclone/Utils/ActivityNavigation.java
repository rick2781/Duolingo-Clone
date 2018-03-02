package e.rick.duolingoclone.Utils;

/**
 * Created by Rick on 3/2/2018.
 */

public class ActivityNavigation {

    static ActivityNavigation INSTANCE;

    public static ActivityNavigation getInstance() {

        if (INSTANCE == null) {

            INSTANCE = new ActivityNavigation();
        }

        return INSTANCE;
    }


}
