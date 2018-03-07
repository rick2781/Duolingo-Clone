package e.rick.duolingoclone.Utils;

import com.google.firebase.auth.FirebaseAuth;

/**
 * Created by Rick on 3/7/2018.
 */

public class FirebaseAuthHelper {

    public static FirebaseAuthHelper INSTANCE;
    public FirebaseAuth mAuth;

    public static FirebaseAuthHelper getClassInstance() {

        if (INSTANCE == null) {
            INSTANCE = new FirebaseAuthHelper();
        }

        return INSTANCE;
    }

    public FirebaseAuth getAuthInstance() {

        if (mAuth == null) {
            mAuth = FirebaseAuth.getInstance();
        }

        return mAuth;
    }
}
