package in.fitbilla.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.widget.SeekBar;
import android.widget.TextView;

import butterknife.OnClick;
import in.fitbilla.R;

public class SelectDimensionActivity extends BaseActivity {

    private SeekBar seekBar;
    private TextView textView;

    private SeekBar seekBar1;
    private TextView textView1;

    @Override
    public void onCreate(Bundle savedInstanceState, PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
        initializeVariables();
        textView.setText(seekBar.getProgress());

        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {

            int progress = 0;

            @Override
            public void onProgressChanged(SeekBar seekBar, int progresValue, boolean fromUser) {
                progress = progresValue;
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                textView.setText(progress);
            }
        });

        textView1.setText(seekBar1.getProgress());
        seekBar1.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {

            int progress = 0;

            @Override
            public void onProgressChanged(SeekBar seekBar, int progresValue, boolean fromUser) {
                progress = progresValue;
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                textView1.setText(progress);
            }
        });
    }

    private void initializeVariables() {
        seekBar = (SeekBar) findViewById(R.id.seekBar1);
        textView = (TextView) findViewById(R.id.weight_value);
        seekBar1 = (SeekBar) findViewById(R.id.seekBar2);
        textView1 = (TextView) findViewById(R.id.height_value);
    }

    @OnClick(R.id.next_button)
    public void clickNext() {
        Intent intent = new Intent(this, ProfileActivity.class);
        startActivity(intent);
        finish();
    }
}
