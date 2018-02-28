package e.rick.duolingoclone.Tasks.WordTask;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import com.nex3z.flowlayout.FlowLayout;

import butterknife.BindView;
import butterknife.ButterKnife;
import e.rick.duolingoclone.R;

public class WordTaskActivity extends AppCompatActivity {

    @BindView(R.id.sentence_line)
    FlowLayout sentenceLine;

    @BindView(R.id.main_layout)
    RelativeLayout mainLayout;

    private CustomWord customWord;

    private CustomLayout customLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_word_task);

        ButterKnife.bind(this);

        customLayout = new CustomLayout(this);
        customLayout.setGravity(Gravity.CENTER);

        initData();

        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);

        params.addRule(RelativeLayout.BELOW, R.id.frame_layout);
        params.topMargin = 235;

        mainLayout.addView(customLayout, params);
    }

    private class TouchListener implements View.OnTouchListener {

        @Override
        public boolean onTouch(View view, MotionEvent motionEvent) {

            if (motionEvent.getAction() == MotionEvent.ACTION_DOWN && !customLayout.empty()) {

                customWord = (CustomWord) view;
                customWord.goToViewGroup(customLayout, sentenceLine);

                return true;
            }

            return false;
        }
    }

    private void initData() {

        String sentence = "This is only";

        String[] wordsFromSentence = sentence.split(" ");

        for (String word : wordsFromSentence) {

            CustomWord customWord = new CustomWord(getApplicationContext(), word);

            customWord.setOnTouchListener(new TouchListener());

            customLayout.push(customWord);
        }
    }
}
