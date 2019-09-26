package e.rick.duolingoclone.ui.activity.WelcomeActivity;

import android.content.Intent;
import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import e.rick.duolingoclone.ui.activity.SelectLanguageActivity.SelectLanguageActivity;
import e.rick.duolingoclone.ui.activity.SignInActivity.SignInActivity;
import e.rick.duolingoclone.R;

/**
 * Created by Rick on 3/6/2018.
 */

public class WelcomeActivity extends AppCompatActivity {

    @BindView(R.id.get_started_button)
    Button getStartedButton;

    @BindView(R.id.log_in_link)
    TextView logInLink;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        ButterKnife.bind(this);

        initData();
    }

    private void initData() {

        getStartedButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                goToGetStartedScreen();
            }
        });

        logInLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                goToSignInScreen();
            }
        });
    }

    private void goToSignInScreen() {

        Intent intent = new Intent(this, SignInActivity.class);
        startActivity(intent);
    }

    private void goToGetStartedScreen() {

        Intent intent = new Intent(this, SelectLanguageActivity.class);
        startActivity(intent);
    }
}
