package e.rick.duolingoclone.Presentation.Activity.SelectLanguageActivity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;
import e.rick.duolingoclone.R;

/**
 * Created by Rick on 3/8/2018.
 */

public class SelectLanguageActivity extends AppCompatActivity {

    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_course);

        ButterKnife.bind(this);

        initData();
    }

    private void initData() {

        LanguageAdapter languageAdapter = new LanguageAdapter();
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        RvDividerItemDecoration itemDecoration = new RvDividerItemDecoration(getDrawable(R.drawable.recycler_view_divider));

        recyclerView.setAdapter(languageAdapter);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.addItemDecoration(itemDecoration);

    }

    public void toastonActivity(int position) {

        Toast.makeText(this, "it worked" + position, Toast.LENGTH_SHORT).show();
    }
}
