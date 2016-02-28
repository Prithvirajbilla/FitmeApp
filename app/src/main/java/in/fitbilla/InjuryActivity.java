package in.fitbilla;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.ImageButton;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import in.fitbilla.ui.activity.ProfileActivity;

public class InjuryActivity extends AppCompatActivity {

    @Bind(R.id.img_injury)
    ImageButton imgInjury;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_injury);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        ButterKnife.bind(this);
    }

    @OnClick(R.id.img_injury)
    public void onInjury(){
        imgInjury.setImageResource(R.drawable.injury);
    }

    @OnClick(R.id.next_button)
    public void onClickNext() {
        Intent intent = new Intent(this, ProfileActivity.class);
        startActivity(intent);
        finish();
    }
}
