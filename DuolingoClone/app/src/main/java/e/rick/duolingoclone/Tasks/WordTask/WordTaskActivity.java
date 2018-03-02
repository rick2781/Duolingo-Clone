package e.rick.duolingoclone.Tasks.WordTask;

import android.graphics.PorterDuff;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.nex3z.flowlayout.FlowLayout;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

import butterknife.BindView;
import butterknife.ButterKnife;
import e.rick.duolingoclone.R;

public class WordTaskActivity extends AppCompatActivity {

    private static final String TAG = "WordTaskActivity";

    @BindView(R.id.sentence_line)
    FlowLayout sentenceLine;

    @BindView(R.id.main_layout)
    RelativeLayout mainLayout;

    @BindView(R.id.check_button)
    Button checkButton;

    @BindView(R.id.question)
    TextView tvQuestion;

    @BindView(R.id.task_progress_bar)
    ProgressBar progressBar;

    private CustomWord customWord;

    private CustomLayout customLayout;

    QuestionModel questionModel;

    ArrayList<String> question = new ArrayList<>();
    ArrayList<String> answer = new ArrayList<>();
    ArrayList<String> words = new ArrayList<>();

    Random random = new Random();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_word_task);

        ButterKnife.bind(this);

        initCustomLayout();
        initData();

        checkAnswer();
    }

    private class TouchListener implements View.OnTouchListener {

        @Override
        public boolean onTouch(View view, MotionEvent motionEvent) {

            if (motionEvent.getAction() == MotionEvent.ACTION_DOWN && !customLayout.empty()) {

                customWord = (CustomWord) view;
                customWord.goToViewGroup(customLayout, sentenceLine);

                checkChildCount();

                return true;
            }

            return false;
        }
    }

    private void initData() {

        checkButton.setEnabled(false);
        checkButton.setTextColor(getResources().getColor(R.color.white_text));

        randomizeInitialData();

        tvQuestion.setText(questionModel.getQuestion());

        randomizeCustomWords();
    }

    private void initCustomLayout() {

        customLayout = new CustomLayout(this);
        customLayout.setGravity(Gravity.CENTER);

        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);

        params.addRule(RelativeLayout.BELOW, R.id.frame_layout);
        params.topMargin = 235;

        mainLayout.addView(customLayout, params);
    }

    private void checkAnswer() {

         checkButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                StringBuilder answer = new StringBuilder();

                if (checkButton.getText().equals("check")) {

                    for (int i = 0; i < sentenceLine.getChildCount(); i++) {

                        customWord = (CustomWord) sentenceLine.getChildAt(i);

                        answer.append(customWord.getText().toString() + " ");
                    }

                    if (answer.toString().equals(questionModel.getAnswer() + " ")) {

                        Toast.makeText(WordTaskActivity.this, "You Are Correct!", Toast.LENGTH_SHORT).show();

                        progressBar.setProgress(progressBar.getProgress()+10);
                        checkButton.setText("continue");

                        lockViews();

                    } else {

                        Toast.makeText(WordTaskActivity.this, "That's Not Correct. \n" + questionModel.getAnswer(), Toast.LENGTH_SHORT).show();

                        progressBar.setProgress(progressBar.getProgress()-10);
                        checkButton.setText("continue");

                        lockViews();
                    }

                } else if (checkButton.getText().equals("continue")) {

                    recreate();
                }
            }
        });
    }

    private void checkChildCount() {

        if (sentenceLine.getChildCount() > 0) {

            checkButton.getBackground().setColorFilter(
                    ContextCompat.getColor(this, R.color.green_button),
                    PorterDuff.Mode.MULTIPLY);

            checkButton.setEnabled(true);

        } else {

            checkButton.getBackground().setColorFilter(
                    ContextCompat.getColor(this, R.color.grey_button),
                    PorterDuff.Mode.MULTIPLY);

            checkButton.setEnabled(false);
        }
    }

    private void lockViews() {

        for (int i = 0; i < sentenceLine.getChildCount(); i++) {

            customWord = (CustomWord) sentenceLine.getChildAt(i);

            customWord.setEnabled(false);
        }

        for (int i = 0; i < customLayout.getChildCount(); i++) {

            customWord = (CustomWord) customLayout.getChildAt(i);

            customWord.setEnabled(false);
        }

    }

    private void randomizeInitialData() {

        //We could do this using Map<String, String> or BiMap for Question and Answer as well

        //Question
        question.add("Ella come manzanas");
        question.add("El come");
        question.add("Usted es una mujer");
        question.add("Tu eres un nino");
        question.add("Que paso");
        question.add("Yo soy un nino");

        //Answer
        answer.add("She eats apple");
        answer.add("He eats");
        answer.add("You are a woman");
        answer.add("You are a boy");
        answer.add("What happened");
        answer.add("I am a boy");

        int randomIndex = random.nextInt(question.size());

        questionModel = new QuestionModel(
                question.get(randomIndex),
                answer.get(randomIndex));
    }

    private void randomizeCustomWords() {

        ArrayList<CustomWord> customWords = new ArrayList<>();

        String[] wordsFromSentence = questionModel.getAnswer().split(" ");

        Collections.addAll(words, wordsFromSentence);

        int sentenceWordsCount = wordsFromSentence.length;

        //Declare how many words left to complete our layout
        int leftSize = 8 - sentenceWordsCount;

        //Pick a random number from "leftSize" and add 2
        int leftRandom = random.nextInt(leftSize) + 2;

        while (words.size() - leftSize < leftRandom) {

            addArrayWords();
        }

        Collections.shuffle(words);

        for (String word : words) {

            CustomWord customWord = new CustomWord(getApplicationContext(), word);

            customWord.setOnTouchListener(new TouchListener());

            customLayout.push(customWord);
        }
    }

    private void addArrayWords() {

        String[] wordsFromAnswerArray = answer.get(random.nextInt(answer.size())).split(" ");

        for (int i = 0; i < 2; i++) {

            String word = wordsFromAnswerArray[random.nextInt(wordsFromAnswerArray.length)];

            if (!words.contains(word)) {

                words.add(word);
            }
        }
    }
}
