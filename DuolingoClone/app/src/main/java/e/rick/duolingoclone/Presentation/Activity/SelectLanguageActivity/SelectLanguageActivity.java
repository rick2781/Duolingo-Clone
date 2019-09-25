package e.rick.duolingoclone.Presentation.Activity.SelectLanguageActivity;

import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import butterknife.BindView;
import butterknife.ButterKnife;
import e.rick.duolingoclone.R;

/**
 * Created by Rick on 3/8/2018.
 */

public class SelectLanguageActivity extends AppCompatActivity {

    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;

    @BindView(R.id.back_button)
    ImageView backButton;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_course);

        ButterKnife.bind(this);

        initData();
    }

    private void initData() {

        LanguageAdapter languageAdapter = new LanguageAdapter(this);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        RvDividerItemDecoration itemDecoration = new RvDividerItemDecoration(getDrawable(R.drawable.recycler_view_divider));

        recyclerView.setAdapter(languageAdapter);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.addItemDecoration(itemDecoration);

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                onBackPressed();
            }
        });
    }
}
