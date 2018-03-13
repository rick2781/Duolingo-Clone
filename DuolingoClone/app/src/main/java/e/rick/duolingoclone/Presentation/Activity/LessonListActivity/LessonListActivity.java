package e.rick.duolingoclone.Presentation.Activity.LessonListActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.orhanobut.hawk.Hawk;

import butterknife.BindView;
import butterknife.ButterKnife;
import e.rick.duolingoclone.Data.Repository;
import e.rick.duolingoclone.Presentation.Activity.SignInActivity.SignInActivity;
import e.rick.duolingoclone.R;
import e.rick.duolingoclone.Utils.ActivityNavigation;
import e.rick.duolingoclone.Utils.CustomProgressBar;
import e.rick.duolingoclone.Utils.Injection;

/**
 * Created by Rick on 3/10/2018.
 */

public class LessonListActivity extends AppCompatActivity {

    private static final String TAG = "LessonListActivity";

    @BindView(R.id.basic_bar)
    CustomProgressBar basic1Bar;

    @BindView(R.id.phrases_bar)
    CustomProgressBar phrasesBar;

    @BindView(R.id.greeting_bar)
    CustomProgressBar greetingBar;

    @BindView(R.id.animal_bar)
    CustomProgressBar animalBar;

    @BindView(R.id.food_bar)
    CustomProgressBar foodBar;

    @BindView(R.id.clothing_bar)
    CustomProgressBar clothingBar;

    @BindView(R.id.shortcut_button)
    Button shortcutButton;

    @BindView(R.id.current_language)
    TextView currentLanguage;

    Repository repository;

    Handler handler;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lesson_list);

        ButterKnife.bind(this);

        checkUserValid();

        initData();
    }

    private void initData() {

        Hawk.init(this).build();

        repository = Injection.provideRepository();

        handler = new Handler();

        repository.getDailyGoal();
        repository.getDailyXp();
        repository.getWeekXp();
        repository.getLessonCompleted();

        setupBarListener();
        setupLessonBar();

        Hawk.put("currentLanguage", currentLanguage.getText().toString().toLowerCase());
    }

    private void setupBarListener() {

        basic1Bar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Hawk.put("lesson", "basic");
                ActivityNavigation.getInstance(LessonListActivity.this).takeToRandomTask();
            }
        });

        phrasesBar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Hawk.put("lesson", "phrases");
                ActivityNavigation.getInstance(LessonListActivity.this).takeToRandomTask();
            }
        });

        greetingBar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Hawk.put("lesson", "greeting");
                ActivityNavigation.getInstance(LessonListActivity.this).takeToRandomTask();
            }
        });

        foodBar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Hawk.put("lesson", "food");
                ActivityNavigation.getInstance(LessonListActivity.this).takeToRandomTask();
            }
        });

        animalBar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Hawk.put("lesson", "animal");
                ActivityNavigation.getInstance(LessonListActivity.this).takeToRandomTask();
            }
        });

        clothingBar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Hawk.put("lesson", "clothing");
                ActivityNavigation.getInstance(LessonListActivity.this).takeToRandomTask();
            }
        });

        shortcutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Hawk.put("lesson", "shortcut");
                ActivityNavigation.getInstance(LessonListActivity.this).takeToRandomTask();
            }
        });
    }

    private void setupLessonBar() {

        handler.postDelayed(new Runnable() {
            @Override
            public void run() {

                if (Hawk.get("basic") != null) {
                    basic1Bar.setProgress(100);
                }

                if (Hawk.get("phrases") != null) {
                    phrasesBar.setProgress(100);
                }

                if (Hawk.get("greeting") != null) {
                    greetingBar.setProgress(100);
                }

                if (Hawk.get("food") != null) {
                    foodBar.setProgress(100);
                }

                if (Hawk.get("animal") != null) {
                    animalBar.setProgress(100);
                }

                if (Hawk.get("clothing") != null) {
                    clothingBar.setProgress(100);
                }
            }
        }, 2000);
    }

    private boolean checkUserValid() {

        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

        if (user == null) {

            Intent intent = new Intent(this, SignInActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
        }

        return true;
    }
}
