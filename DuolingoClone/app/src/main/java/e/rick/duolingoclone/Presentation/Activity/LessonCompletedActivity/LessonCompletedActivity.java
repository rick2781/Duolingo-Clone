package e.rick.duolingoclone.Presentation.Activity.LessonCompletedActivity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;

import com.orhanobut.hawk.Hawk;

import java.security.InvalidKeyException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;
import e.rick.duolingoclone.Data.Repository;
import e.rick.duolingoclone.Presentation.Activity.LessonListActivity.LessonListActivity;
import e.rick.duolingoclone.R;
import e.rick.duolingoclone.Utils.CustomProgressBar;
import e.rick.duolingoclone.Utils.Injection;

/**
 * Created by Rick on 3/4/2018.
 */

public class LessonCompletedActivity extends AppCompatActivity{

    @BindView(R.id.main_layout)
    RelativeLayout mainLayout;

    @BindView(R.id.user_progress_bar)
    CustomProgressBar dailyProgressBar;

    @BindView(R.id.continue_button)
    Button continueButton;

    @BindView(R.id.monday_bar)
    CustomProgressBar mondayBar;

    @BindView(R.id.tuesday_bar)
    CustomProgressBar tuesdayBar;

    @BindView(R.id.wednesday_bar)
    CustomProgressBar wednesdayBar;

    @BindView(R.id.thursday_bar)
    CustomProgressBar thursdayBar;

    @BindView(R.id.friday_bar)
    CustomProgressBar fridayBar;

    @BindView(R.id.saturday_bar)
    CustomProgressBar saturdayBar;

    @BindView(R.id.sunday_bar)
    CustomProgressBar sundayBar;

    Repository repository;

    int mondayProgress,
            tuesdayProgress,
            wednesdayProgress,
            thursdayProgress,
            fridayProgress,
            saturdayProgress,
            sundayProgress;

    int dailyGoal;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lesson_completed);

        ButterKnife.bind(this);

        initData();
    }

    private void initData() {

        Hawk.init(this).build();

        repository = Injection.provideRepository();

        dailyGoal = Hawk.get("dailyGoal");

        continueButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(LessonCompletedActivity.this, LessonListActivity.class);
                startActivity(intent);
            }
        });

        setupProgressBar();
        setupWeekBar();
    }

    private void setupProgressBar() {

        int dailyXp;

        String lesson = Hawk.get("lesson");

        repository.setLessonComplete(lesson, true);
        repository.getWeekXp();

        if (Hawk.get("dailyXp") != null) {

            dailyXp = Hawk.get("dailyXp");

            dailyXp += 10;

            Hawk.put("dailyXp", dailyXp);
            repository.setDailyXp(dailyXp);

        } else {

            dailyXp = 10;

            Hawk.put("dailyXp", dailyXp);
            repository.setDailyXp(dailyXp);
        }

        dailyProgressBar.setMax(dailyGoal);

        dailyProgressBar.setProgressWithAnimation(dailyXp, 2000);
    }

    private void setupWeekBar() {

        if (Hawk.get("mondayXp") != null) {
            mondayProgress = (int) Hawk.get("mondayXp");
        } else {
            mondayProgress = 0;
        }
        if (Hawk.get("tuesdayXp") != null) {
            tuesdayProgress = (int) Hawk.get("tuesdayXp");
        } else {
            tuesdayProgress = 0;
        }
        if (Hawk.get("wednesdayXp") != null) {
            wednesdayProgress = (int) Hawk.get("wednesdayXp");
        } else {
            wednesdayProgress = 0;
        }
        if (Hawk.get("thursdayXp") != null) {
            thursdayProgress = (int) Hawk.get("thursdayXp");
        } else {
            thursdayProgress = 0;
        }
        if (Hawk.get("fridayXp") != null) {
            fridayProgress = (int) Hawk.get("fridayXp");
        } else {
            fridayProgress = 0;
        }
        if (Hawk.get("saturdayXp") != null) {
            saturdayProgress = (int) Hawk.get("saturdayXp");
        } else {
            saturdayProgress = 0;
        }
        if (Hawk.get("sundayXp") != null) {
            sundayProgress = (int) Hawk.get("sundayXp");
        } else {
            sundayProgress = 0;
        }

        mondayBar.setProgress(mondayProgress);
        tuesdayBar.setProgress(tuesdayProgress);
        wednesdayBar.setProgress(wednesdayProgress);
        thursdayBar.setProgress(thursdayProgress);
        fridayBar.setProgress(fridayProgress);
        saturdayBar.setProgress(saturdayProgress);
        sundayBar.setProgress(sundayProgress);

        mondayBar.setMax(dailyGoal);
        tuesdayBar.setMax(dailyGoal);
        wednesdayBar.setMax(dailyGoal);
        thursdayBar.setMax(dailyGoal);
        fridayBar.setMax(dailyGoal);
        saturdayBar.setMax(dailyGoal);
        sundayBar.setMax(dailyGoal);
    }
}
