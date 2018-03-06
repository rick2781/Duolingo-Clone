package e.rick.duolingoclone.Utils;

import android.content.Context;
import android.content.Intent;

import java.util.ArrayList;
import java.util.Random;

import e.rick.duolingoclone.Presentation.Activity.LessonCompletedActivity.LessonCompletedActivity;
import e.rick.duolingoclone.Presentation.Tasks.TapPairTask.TapPairActivity;
import e.rick.duolingoclone.Presentation.Tasks.TranslateSentenceTask.TSTaskActivity;
import e.rick.duolingoclone.Presentation.Tasks.WordTask.WordTaskActivity;

/**
 * Created by Rick on 3/2/2018.
 */

public class ActivityNavigation {

    static ActivityNavigation INSTANCE;

    Context context;

    ArrayList<Class> activities = new ArrayList<>();

    Random random = new Random();

    public ActivityNavigation(Context context) {

        this.context = context;

        initData();
    }

    public static ActivityNavigation getInstance(Context context) {

        if (INSTANCE == null) {

            INSTANCE = new ActivityNavigation(context);
        }

        return INSTANCE;
    }

    private void initData() {

        activities.add(WordTaskActivity.class);
        activities.add(TSTaskActivity.class);
        activities.add(TapPairActivity.class);
    }

    public void takeToRandomTask() {

        int randomIndex = random.nextInt(activities.size());

        Intent intent = new Intent(context, activities.get(randomIndex));
        context.startActivity(intent);
    }

    public void lessonCompleted() {

        Intent intent = new Intent(context, LessonCompletedActivity.class);
        context.startActivity(intent);
    }
}
