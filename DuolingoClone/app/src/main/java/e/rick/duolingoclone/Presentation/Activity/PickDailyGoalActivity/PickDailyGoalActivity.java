package e.rick.duolingoclone.Presentation.Activity.PickDailyGoalActivity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.RadioButton;

import butterknife.BindView;
import butterknife.ButterKnife;
import e.rick.duolingoclone.R;

/**
 * Created by Rick on 3/9/2018.
 */

public class PickDailyGoalActivity extends AppCompatActivity {

    @BindView(R.id.back_button)
    ImageView backButton;

    @BindView(R.id.casual_goal)
    RadioButton casualGoal;

    @BindView(R.id.regular_goal)
    RadioButton regularGoal;

    @BindView(R.id.serious_goal)
    RadioButton seriousGoal;

    @BindView(R.id.insane_goal)
    RadioButton insaneGoal;

    @BindView(R.id.continue_button)
    RadioButton continueButton;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pick_daily_goal);

        ButterKnife.bind(this);
    }
}
