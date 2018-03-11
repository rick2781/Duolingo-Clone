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

public class LessonCompletedActivity extends AppCompatActivity {

    @BindView(R.id.main_layout)
    RelativeLayout mainLayout;

    @BindView(R.id.user_progress_bar)
    CustomProgressBar dailyProgressBar;

    @BindView(R.id.continue_button)
    Button continueButton;

    Repository repository;

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

        repository.getDailyXp();

        continueButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(LessonCompletedActivity.this, LessonListActivity.class);
                startActivity(intent);
            }
        });

        setupProgressBar();
    }

    private void setupProgressBar() {

        long dailyGoal = Hawk.get("dailyGoal");
        long dailyXp;

        if (Hawk.get("dailyXp") != null) {

            dailyXp = Hawk.get("dailyXp");

            dailyXp += 10;

            repository.setDailyXp((int) dailyXp);

        } else {

            dailyXp = 10;

            repository.setDailyXp((int) dailyXp);
        }

        dailyProgressBar.setMax((int) dailyGoal);

        dailyProgressBar.setProgressWithAnimation((int) dailyXp);


    }
}
