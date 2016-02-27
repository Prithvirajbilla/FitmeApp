package in.adoro.android.fitmeapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import butterknife.Bind;

public class ProfileActivity extends BaseActivity {

    @Bind(R.id.random_text)
    public TextView txtRandom;

    /*@Bind(R.id.fitChart)
    public FitChart fitChart;*/

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();

                fillFitChart();
            }
        });

        /*final FitChart fitChart = (FitChart)findViewById(R.id.fitChart);
        fitChart.setMinValue(0f);
        fitChart.setMaxValue(100f);
        */


    }

    private void fillFitChart(){

        Intent intent = new Intent(getApplicationContext(), PieChartActivity.class);
        this.startActivity(intent);


        /*final FitChart fitChart = (FitChart)findViewById(R.id.fitChart);

        Collection<FitChartValue> values = new ArrayList<>();
        values.add(new FitChartValue(30f, 0x2d4302));
        values.add(new FitChartValue(20f, 0x75a80d));
        values.add(new FitChartValue(15f, 0x8fc026));
        values.add(new FitChartValue(10f, 0xB5CC84));
        fitChart.setValues(values);*/

    }
}
