package e.rick.duolingoclone.Utils;

import android.content.Context;
import android.content.Intent;

import java.util.ArrayList;
import java.util.Random;

import e.rick.duolingoclone.Tasks.TranslateSentenceTask.TSTaskActivity;
import e.rick.duolingoclone.Tasks.WordTask.WordTaskActivity;

/**
 * Created by Rick on 3/2/2018.
 */

public class ActivityNavigation {

    static ActivityNavigation INSTANCE;

    Context context;

    int progressBarValue;

    ArrayList<Class> activities = new ArrayList<>();

    Random random = new Random();

    public ActivityNavigation(Context context, int progressBarValue) {

        this.context = context;
        this.progressBarValue = progressBarValue;

        initData();
    }

    public static ActivityNavigation getInstance(Context context, int progressBarValue) {

        if (INSTANCE == null) {

            INSTANCE = new ActivityNavigation(context, progressBarValue);
        }

        return INSTANCE;
    }

    private void initData() {

        activities.add(WordTaskActivity.class);
        activities.add(TSTaskActivity.class);
    }

    public void takeToRandomTask() {

        int randomIndex = random.nextInt(activities.size());

        Intent intent = new Intent(context, activities.get(randomIndex));
        intent.putExtra("progressBarValue", progressBarValue);
        context.startActivity(intent);
    }
}
