package e.rick.duolingoclone.Presentation.Tasks;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.AppCompatTextView;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.LinearLayout;
import com.nex3z.flowlayout.FlowLayout;

import e.rick.duolingoclone.R;
import e.rick.duolingoclone.Presentation.Tasks.WordTask.CustomLayout;

/**
 * Created by Rick on 2/26/2018.
 */

public class CustomWord extends AppCompatTextView {

    private static final String TAG = "CustomWord";

    private String word;

    public CustomWord(Context context, String word) {
        super(context);
        this.word = word;

        setText(word);

        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);

        layoutParams.setMargins(15, 15,15, 0);

        setTextColor(getResources().getColor(R.color.custom_view_text_color));

        setLayoutParams(layoutParams);
        setTextAlignment(TEXT_ALIGNMENT_CENTER);
        setTextSize(20);

        setBackground(ContextCompat.getDrawable(getContext(), R.drawable.custom_word_border));
    }

    public void goToViewGroup(CustomLayout customLayout, FlowLayout sentenceLayout) {

        ViewParent parent = getParent();

        if (parent instanceof CustomLayout) {

            customLayout.removeViewCustomLayout(this);
            sentenceLayout.addView(this);

        } else {

            sentenceLayout.removeView(this);

            customLayout.addViewAt(this);
        }
    }
}
