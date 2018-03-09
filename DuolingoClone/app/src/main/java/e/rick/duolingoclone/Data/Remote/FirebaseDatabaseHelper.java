package e.rick.duolingoclone.Data.Remote;

import android.util.Log;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Calendar;

import e.rick.duolingoclone.Utils.Injection;

/**
 * Created by Rick on 3/8/2018.
 */

public class FirebaseDatabaseHelper {

    private static final String TAG = "FirebaseDatabaseHelper";

    public static FirebaseDatabaseHelper INSTANCE;
    public static FirebaseDatabase mDatabase;
    public static DatabaseReference myRef;

    public static FirebaseDatabaseHelper getHelperInstance() {

        if (INSTANCE == null) {
            INSTANCE = new FirebaseDatabaseHelper();
        }

        return INSTANCE;
    }

    public FirebaseDatabase getDatabaseInstance() {

        if (mDatabase == null) {
            mDatabase = FirebaseDatabase.getInstance();
        }

        if (myRef == null) {

            myRef = mDatabase.getReference();
        }

        return mDatabase;
    }

    public void setNewLanguage(String language) {

        String userID = Injection.providesAuthHelper().getAuthInstance().getCurrentUser().getUid();

        myRef.child("user")
                .child(userID)
                .child("course")
                .child(language)
                .child("overall_progress")
                .setValue(0)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {

                        Log.d(TAG, "New language has been set successfully");
                    }
                });
    }


    //TODO AUTOMATE LANGUAGE RECOGNITION
    public void setDailyXp(String language, int xp) {

        String userID = Injection.providesAuthHelper().getAuthInstance().getCurrentUser().getUid();

        //TODO FIX CALENDAR HERE
        Calendar calendar = Calendar.getInstance();

        myRef.child("user")
                .child(userID)
                .child("course")
                .child(language)
                .child("week_xp")
                .child(dayOfWeek)
                .setvalue(xp)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {

                        Log.d(TAG, "XP for day " + dayOfWeek + " has been settled properly");
                    }
                });
    }

    public void setUserTotalXp(String language, int xp) {

        String userID = Injection.providesAuthHelper().getAuthInstance().getCurrentUser().getUid();

        myRef.child("user")
                .child(userID)
                .child("user_xp")
                .setValue(xp)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {

                        Log.d(TAG, "Total user's xp updated");
                    }
                });
    }

    public void setLastTimeVisited() {

        String userID = Injection.providesAuthHelper().getAuthInstance().getCurrentUser().getUid();

        //TODO FIX CALENDAR
        Calendar calendar = Calendar.getInstance();

        myRef.child("user")
                .child(userID)
                .child("last_visited")
                .setValue(lastTimeVisited)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {

                        Log.d(TAG, "Last time user visited has been updated");
                    }
                });
    }

    public void setDailyGoal(int dailyGoal) {

        String userID = Injection.providesAuthHelper().getAuthInstance().getCurrentUser().getUid();

        myRef.child("user")
                .child(userID)
                .child("daily_goal")
                .setValue(dailyGoal)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {

                        Log.d(TAG, "User's daily goal has been updated");
                    }
                });
    }

    public void setUserInfo(UserData userData) {

        String userID = Injection.providesAuthHelper().getAuthInstance().getCurrentUser().getUid();

        myRef.child("user")
                .child(userID)
                .child("user_data")
                .setValue(userData)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {

                        Log.d(TAG, "User's data has been updated");
                    }
                });
    }

    public void setLessonProgress(String language, String subject, String lesson, boolean completeness) {

        String userID = Injection.providesAuthHelper().getAuthInstance().getCurrentUser().getUid();

        myRef.child("user")
                .child(userID)
                .child("course")
                .child(language)
                .child("subjects")
                .child(subject)
                .child(lesson)
                .setValue(completeness)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {

                        Log.d(TAG, "User's data has been updated");
                    }
                });
    }

    public void setLessonCompleteDate(String language, String subject, String lesson) {

        String userID = Injection.providesAuthHelper().getAuthInstance().getCurrentUser().getUid();

        //TODO FIX CALENDAR
        Calendar calendar = Calendar.getInstance();

        myRef.child("user")
                .child(userID)
                .child("course")
                .child(language)
                .child("lessons")
                .child(subject)
                .child(completeness)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {

                        Log.d(TAG, "User's data has been updated");
                    }
                });
    }
}
