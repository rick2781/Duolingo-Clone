package e.rick.duolingoclone.Presentation.Activity.PickDailyGoalActivity;

import android.graphics.drawable.StateListDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.RadioButton;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import e.rick.duolingoclone.Data.Repository;
import e.rick.duolingoclone.R;
import e.rick.duolingoclone.Utils.Injection;

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
    Button continueButton;

    ArrayList<RadioButton> radioButtonArray = new ArrayList<>();

    int checkedButton;

    Repository repository;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pick_daily_goal);

        ButterKnife.bind(this);

        repository = Injection.provideRepository();

        setRadioButton();
        regularGoal.setChecked(true);

        continueListener();
    }

    private void setRadioButton() {

        radioButtonArray.add(casualGoal);
        radioButtonArray.add(regularGoal);
        radioButtonArray.add(seriousGoal);
        radioButtonArray.add(insaneGoal);

        for (int i = 0; i < radioButtonArray.size(); i++) {

            final int finalIndex = i;

            radioButtonArray.get(i).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    checkedButton = finalIndex;

                    ArrayList<Integer> buttonIdx = new ArrayList<>();

                    buttonIdx.add(0);
                    buttonIdx.add(1);
                    buttonIdx.add(2);
                    buttonIdx.add(3);

                    buttonIdx.remove(finalIndex);

                    radioButtonArray.get(finalIndex).setChecked(true);

                    for (int index : buttonIdx) {

                        radioButtonArray.get(index).setChecked(false);
                    }
                }
            });
        }
    }

    private void continueListener() {

        continueButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                int dailyGoal = 2;

                if(checkedButton == 3) dailyGoal = 50; else dailyGoal = (checkedButton + 1) * 10;

                repository.setDailyGoal(dailyGoal);
            }
        });
    }
}
