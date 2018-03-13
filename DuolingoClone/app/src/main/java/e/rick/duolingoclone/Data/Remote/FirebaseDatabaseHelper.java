package e.rick.duolingoclone.Data.Remote;

import android.util.Log;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.orhanobut.hawk.Hawk;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import e.rick.duolingoclone.Data.DataSource;
import e.rick.duolingoclone.Model.UserData;
import e.rick.duolingoclone.Utils.Injection;

/**
 * Created by Rick on 3/8/2018.
 */

public class FirebaseDatabaseHelper implements DataSource.Remote {

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
            myRef = mDatabase.getReference();
        }

        return mDatabase;
    }

    @Override
    public void setNewLanguage(String language) {


        if (Injection.providesAuthHelper().getAuthInstance().getCurrentUser() != null) {

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
    }


    //TODO AUTOMATE LANGUAGE RECOGNITION
    @Override
    public void setDailyXp(int xp) {

        if (Injection.providesAuthHelper().getAuthInstance().getCurrentUser() != null) {
            String userID = Injection.providesAuthHelper().getAuthInstance().getCurrentUser().getUid();

            String language = Hawk.get("currentLanguage");

            Date date = Calendar.getInstance().getTime();

            SimpleDateFormat dateFormat = new SimpleDateFormat("EEEE", Locale.US);
            final String dayOfWeek = dateFormat.format(date);

            myRef.child("user")
                    .child(userID)
                    .child("course")
                    .child(language)
                    .child("week_xp")
                    .child(dayOfWeek)
                    .setValue(xp)
                    .addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void aVoid) {

                            Log.d(TAG, "XP for day " + dayOfWeek + " has been settled properly");
                        }
                    });
        }
    }

    @Override
    public void setUserTotalXp(int xp) {

        if (Injection.providesAuthHelper().getAuthInstance().getCurrentUser() != null) {

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
    }

    @Override
    public void setLastTimeVisited() {

        if (Injection.providesAuthHelper().getAuthInstance().getCurrentUser() != null) {

            String userID = Injection.providesAuthHelper().getAuthInstance().getCurrentUser().getUid();

            Date date = Calendar.getInstance().getTime();

            SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/YYYY", Locale.US);
            String lastVisited = dateFormat.format(date);

            myRef.child("user")
                    .child(userID)
                    .child("last_visited")
                    .setValue(lastVisited)
                    .addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void aVoid) {

                            Log.d(TAG, "Last time user visited has been updated");
                        }
                    });
        }
    }

    @Override
    public void setDailyGoal(int dailyGoal) {

        if (Injection.providesAuthHelper().getAuthInstance().getCurrentUser() != null) {

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
    }

    @Override
    public void setUserInfo(UserData userData) {

        if (Injection.providesAuthHelper().getAuthInstance().getCurrentUser() != null) {

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
    }

    @Override
    public void setLessonComplete(String lesson, boolean completeness) {

        if (Injection.providesAuthHelper().getAuthInstance().getCurrentUser() != null) {

            String userID = Injection.providesAuthHelper().getAuthInstance().getCurrentUser().getUid();

            String language = Hawk.get("currentLanguage");

            myRef.child("user")
                    .child(userID)
                    .child("course")
                    .child(language)
                    .child("lessons")
                    .child(lesson)
                    .setValue(completeness)
                    .addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void aVoid) {

                            Log.d(TAG, "User has completed this lesson");
                        }
                    });
        }
    }

    @Override
    public void setLessonCompleteDate(String lesson) {

        if (Injection.providesAuthHelper().getAuthInstance().getCurrentUser() != null) {

            String userID = Injection.providesAuthHelper().getAuthInstance().getCurrentUser().getUid();

            String language = Hawk.get("currentLanguage");

            Date date = Calendar.getInstance().getTime();

            SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/YYYY", Locale.US);
            String completedDate = dateFormat.format(date);

            myRef.child("user")
                    .child(userID)
                    .child("course")
                    .child(language)
                    .child("lessons")
                    .child(lesson.toLowerCase())
                    .child("completed_date")
                    .setValue(completedDate)
                    .addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void aVoid) {

                            Log.d(TAG, "User's data has been updated");
                        }
                    });
        }
    }

    @Override
    public void getDailyGoal(){

        if (Injection.providesAuthHelper().getAuthInstance().getCurrentUser() != null) {

            String userID = Injection.providesAuthHelper().getAuthInstance().getCurrentUser().getUid();

            myRef.child("user")
                    .child(userID)
                    .child("daily_goal")
                    .addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {

                            int dailyGoal;

                            if (dataSnapshot.getValue() != null) {

                                dailyGoal = dataSnapshot.getValue(Integer.class);

                            } else {

                                dailyGoal = 20;
                            }

                            Hawk.put("dailyGoal", dailyGoal);
                        }

                        @Override
                        public void onCancelled(DatabaseError databaseError) {

                        }
                    });
        }
    }

    @Override
    public void getDailyXp() {

        if (Injection.providesAuthHelper().getAuthInstance().getCurrentUser() != null) {

            String userID = Injection.providesAuthHelper().getAuthInstance().getCurrentUser().getUid();

            String language = Hawk.get("currentLanguage");

            Date date = Calendar.getInstance().getTime();

            SimpleDateFormat dateFormat = new SimpleDateFormat("EEEE", Locale.US);
            final String dayOfWeek = dateFormat.format(date);

            myRef.child("user")
                    .child(userID)
                    .child("course")
                    .child(language)
                    .child("week_xp")
                    .child(dayOfWeek)
                    .addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {

                            int dailyXp = 0;

                            if (dataSnapshot.getValue() != null) {

                                dailyXp = Math.toIntExact(dataSnapshot.getValue(Long.class));
                            }

                            Hawk.put("dailyXp", dailyXp);
                        }

                        @Override
                        public void onCancelled(DatabaseError databaseError) {

                        }
                    });
        }
    }

    @Override
    public void getWeekXp() {

        if (Injection.providesAuthHelper().getAuthInstance().getCurrentUser() != null) {

            String userID = Injection.providesAuthHelper().getAuthInstance().getCurrentUser().getUid();

            String language = Hawk.get("currentLanguage");

            myRef.child("user")
                    .child(userID)
                    .child("course")
                    .child(language)
                    .child("week_xp")
                    .addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {

                            for (DataSnapshot weekDays : dataSnapshot.getChildren()) {

                                int dailyXp = Math.toIntExact(weekDays.getValue(Long.class));

                                switch (weekDays.getKey()) {

                                    case "Monday":
                                        Hawk.put("mondayXp", dailyXp);
                                        break;

                                    case "Tuesday":
                                        Hawk.put("tuesdayXp", dailyXp);
                                        break;

                                    case "Wednesday":
                                        Hawk.put("wednesdayXp", dailyXp);
                                        break;

                                    case "Thursday":
                                        Hawk.put("thursdayXp", dailyXp);
                                        break;

                                    case "Friday":
                                        Hawk.put("fridayXp", dailyXp);
                                        break;

                                    case "Saturday":
                                        Hawk.put("saturdayXp", dailyXp);
                                        break;

                                    case "Sunday":
                                        Hawk.put("sundayXp", dailyXp);
                                        break;
                                }
                            }
                        }

                        @Override
                        public void onCancelled(DatabaseError databaseError) {

                        }
                    });
        }
    }

    @Override
    public void getLessonCompleted() {

        if (Injection.providesAuthHelper().getAuthInstance().getCurrentUser() != null) {

            String userID = Injection.providesAuthHelper().getAuthInstance().getCurrentUser().getUid();

            String language = Hawk.get("currentLanguage");

            myRef.child("user")
                    .child(userID)
                    .child("course")
                    .child(language)
                    .child("lessons")
                    .addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {

                            for (DataSnapshot ds : dataSnapshot.getChildren()) {

                                Hawk.put(ds.getKey().toString(), ds.getValue());
                            }


                        }

                        @Override
                        public void onCancelled(DatabaseError databaseError) {

                        }
                    });
        }
    }
}
