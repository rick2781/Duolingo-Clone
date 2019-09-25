package e.rick.duolingoclone;

import android.app.Application;
import android.util.Log;

import com.google.firebase.FirebaseApp;

public class DuolingoApp extends Application {

    private static final String TAG = "DuolingoApp";

    @Override
    public void onCreate() {
        super.onCreate();

        FirebaseApp.initializeApp(this);
    }
}
