package e.rick.duolingoclone.Tasks.TranslateSentenceTask;

import android.content.Context;
import android.content.Intent;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;
import e.rick.duolingoclone.R;
import e.rick.duolingoclone.Tasks.WordTask.CustomWord;
import e.rick.duolingoclone.Tasks.WordTask.QuestionModel;
import e.rick.duolingoclone.Tasks.WordTask.WordTaskActivity;
import e.rick.duolingoclone.Utils.ActivityNavigation;
import e.rick.duolingoclone.Utils.QuestionAnswer;

/**
 * Created by Rick on 3/2/2018.
 */

public class TSTaskActivity extends AppCompatActivity{

    @BindView(R.id.question)
    TextView tvQuestion;

    @BindView(R.id.check_button)
    Button checkButton;

    @BindView(R.id.user_answer)
    EditText etUserAnswer;

    @BindView(R.id.task_progress_bar)
    ProgressBar progressBar;

    QuestionModel questionModel;

    int progressBarValue;

    Context context = TSTaskActivity.this;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.translate_sentence_activity);

        ButterKnife.bind(this);

        initData();
    }

    private void initData() {

        checkButton.setEnabled(false);
        checkButton.setTextColor(getResources().getColor(R.color.white_text));

        questionModel = QuestionAnswer.getInstance().getRandomQuestionObj();

        Intent intent = getIntent();

        progressBarValue = 0;

        if (intent.getExtras() != null) {

            progressBarValue = intent.getExtras().getInt("progressBarValue");

            progressBar.setProgress(progressBarValue);
        }

        tvQuestion.setText(questionModel.getQuestion());

        checkAnswer();
        enableButton();
    }

    private void checkAnswer() {

        checkButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String userAnswer = etUserAnswer.getText().toString();

                if (checkButton.getText().equals("check")) {

                    if (questionModel.getAnswer().equals(userAnswer)) {

                        Toast.makeText(context, "You Are Correct!", Toast.LENGTH_SHORT).show();

                        progressBarValue += 10;

                        progressBar.setProgress(progressBarValue);
                        checkButton.setText("continue");

                        lockEditText();

                    } else {

                        Toast.makeText(context, "That's not correct!" + questionModel.getAnswer(), Toast.LENGTH_SHORT).show();

                        progressBarValue -= 10;

                        progressBar.setProgress(progressBarValue);
                        checkButton.setText("continue");

                        lockEditText();
                    }

                } else if (checkButton.getText().equals("continue")) {

                    ActivityNavigation.getInstance(context, progressBarValue).takeToRandomTask();

                }
            }
        });
    }

    private void lockEditText() {

        etUserAnswer.setEnabled(false);
    }

    private void enableButton() {

        etUserAnswer.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                if (i1 > 0) {

                    checkButton.getBackground().setColorFilter(
                            ContextCompat.getColor(context, R.color.green_button),
                            PorterDuff.Mode.MULTIPLY);

                    checkButton.setEnabled(true);

                } else {

                    checkButton.getBackground().setColorFilter(
                            ContextCompat.getColor(context, R.color.grey_button),
                            PorterDuff.Mode.MULTIPLY);

                    checkButton.setEnabled(false);
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
    }
}
