package e.rick.duolingoclone.Presentation.Activity.LessonListActivity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;
import e.rick.duolingoclone.R;
import e.rick.duolingoclone.Utils.ActivityNavigation;
import e.rick.duolingoclone.Utils.CustomProgressBar;

/**
 * Created by Rick on 3/10/2018.
 */

public class LessonListActivity extends AppCompatActivity {

    @BindView(R.id.basics1_bar)
    CustomProgressBar basic1Bar;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lesson_list);

        ButterKnife.bind(this);

        basic1Bar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                ActivityNavigation.getInstance(LessonListActivity.this).takeToRandomTask();
            }
        });
    }
}
