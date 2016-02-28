package in.fitbilla;

import android.content.Intent;
import android.os.Bundle;
import android.widget.SeekBar;
import android.widget.TextView;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import in.fitbilla.ui.activity.BaseActivity;

public class SelectDimensionActivity extends BaseActivity {

    @Bind(R.id.seekBar1)
    public SeekBar seekBar;
    @Bind(R.id.weight_value)
    public TextView textView;

    @Bind(R.id.seekBar2)
    public SeekBar seekBar1;
    @Bind(R.id.height_value)
    public TextView textView1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dimension);
        ButterKnife.bind(this);
        textView.setText(String.valueOf(seekBar.getProgress()));

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
                textView.setText(String.valueOf(progress));
            }
        });

        textView1.setText(String.valueOf(seekBar1.getProgress()));
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
                textView1.setText(String.valueOf(progress));
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
        Intent intent = new Intent(this, InjuryActivity.class);
        startActivity(intent);
        finish();
    }
}
