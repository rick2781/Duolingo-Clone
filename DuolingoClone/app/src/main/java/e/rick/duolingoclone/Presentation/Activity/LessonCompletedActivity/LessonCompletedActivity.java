package e.rick.duolingoclone.Presentation.Activity.LessonCompletedActivity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import butterknife.BindView;
import butterknife.ButterKnife;
import e.rick.duolingoclone.R;
import e.rick.duolingoclone.Utils.CustomProgressBar;

/**
 * Created by Rick on 3/4/2018.
 */

public class LessonCompletedActivity extends AppCompatActivity {

    @BindView(R.id.main_layout)
    RelativeLayout mainLayout;

    @BindView(R.id.user_progress_bar)
    CustomProgressBar customProgressBar;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lesson_completed);

        ButterKnife.bind(this);

        setupProgressBar();
    }

    private void setupProgressBar() {

        customProgressBar.setProgressWithAnimation(85);

        customProgressBar.setBackgroundProgressThickness(60);
        customProgressBar.setForegroundProgressThickness(60);
    }
}
