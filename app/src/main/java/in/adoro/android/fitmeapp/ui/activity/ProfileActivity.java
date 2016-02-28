package in.adoro.android.fitmeapp.ui.activity;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.Toolbar;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.text.style.RelativeSizeSpan;
import android.text.style.StyleSpan;
import android.view.View;
import android.widget.Toast;

import com.github.mikephil.charting.animation.Easing;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.formatter.PercentFormatter;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.txusballesteros.widgets.FitChart;
import com.txusballesteros.widgets.FitChartValue;

import java.util.ArrayList;
import java.util.Collection;

import butterknife.ButterKnife;
import butterknife.OnClick;
import in.adoro.android.fitmeapp.PieChartActivity;
import in.adoro.android.fitmeapp.R;
import in.adoro.android.fitmeapp.WorkoutActivity;

public class ProfileActivity extends BaseActivity {

    /*@Bind(R.id.fitChart)
    public FitChart fitChart;*/
    private PieChart mChart;


    protected String[] mMonths = new String[] {
            "Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Okt", "Nov", "Dec"
    };

    protected String[] mActivites = new String[] {
            "Running", "Gymnasium", "Cycling", "Dancing", "Swimming", "Yoga", "Activity G", "Activity H",
            "Activity I", "Activity J", "Activity K", "Activity L", "Activity M", "Activity N", "Activity O", "Activity P",
            "Activity Q", "Activity R", "Activity S", "Activity T", "Activity U", "Activity V", "Activity W", "Activity X",
            "Activity Y", "Activity Z"
    };


    @OnClick(R.id.activityBtn1)
    public void onActivityNavClick(){
        Toast.makeText(this,"test", Toast.LENGTH_LONG).show();
        navigateToActivityPage(1);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        setTitle("Shobhit, 25, M");//TODO: Replace with profileName
        ButterKnife.bind(this);

/*

        FloatingActionButton fab = (FloatingActionButton) findViewById();
        fab.setOnClickListener(new View.OnClickListener() {
            @Override

        });
*/

        /*final FitChart fitChart = (FitChart)findViewById(R.id.fitChart);
        fitChart.setMinValue(0f);
        fitChart.setMaxValue(100f);
        */
        //fillFitChart();

        fillTodayProfileChart();
    }

    @OnClick(R.id.fab)
    public void onProfilePicClick(View view) {
        Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show();

        fillFitChart();
    }



    public void fillTodayProfileChart(){
        mChart = (PieChart) findViewById(R.id.chartTodayProfile);
        mChart.setUsePercentValues(true);
        mChart.setDescription("");
        mChart.setExtraOffsets(5, 10, 5, 5);

        mChart.setDragDecelerationFrictionCoef(0.95f);

        //tf = Typeface.createFromAsset(getAssets());//, "OpenSans-Regular.ttf");

        //mChart.setCenterTextTypeface(Typeface.createFromAsset(getAssets(), "OpenSans-Light.ttf"));
        mChart.setCenterText(generateCenterSpannableTextToday());

        mChart.setDrawHoleEnabled(true);
        mChart.setHoleColor(Color.TRANSPARENT);

        mChart.setTransparentCircleColor(Color.WHITE);
        mChart.setTransparentCircleAlpha(110);

        mChart.setHoleRadius(58f);
        mChart.setTransparentCircleRadius(61f);

        mChart.setDrawCenterText(true);

        mChart.setRotationAngle(0);
        // enable rotation of the chart by touch
        mChart.setRotationEnabled(true);
        mChart.setHighlightPerTapEnabled(true);

        // mChart.setUnit(" €");
        // mChart.setDrawUnitsInChart(true);

        // add a selection listener
        //mChart.setOnChartValueSelectedListener(this);

        setData(3, 100);

        mChart.animateY(1400, Easing.EasingOption.EaseInOutQuad);
        // mChart.spin(2000, 0, 360);

        Legend l = mChart.getLegend();
        l.setPosition(Legend.LegendPosition.RIGHT_OF_CHART);
        l.setXEntrySpace(7f);
        l.setYEntrySpace(0f);
        l.setYOffset(0f);

    }

    private SpannableString generateCenterSpannableTextToday() {

        SpannableString s = new SpannableString("Today's Burn\nActivities wise break down");
        s.setSpan(new RelativeSizeSpan(1.7f), 0, 12, 0);

        s.setSpan(new StyleSpan(Typeface.NORMAL), 12, s.length() - 10, 0);
        s.setSpan(new ForegroundColorSpan(Color.GRAY), 12, s.length() - 10, 0);
        s.setSpan(new RelativeSizeSpan(.8f), 12, s.length() - 10, 0);

        s.setSpan(new StyleSpan(Typeface.ITALIC), s.length() - 10, s.length(), 0);
        s.setSpan(new ForegroundColorSpan(ColorTemplate.getHoloBlue()), s.length() - 10, s.length(), 0);
        return s;
    }
    private void setData(int count, float range) {

        float mult = range;

        ArrayList<Entry> yVals1 = new ArrayList<Entry>();

        // IMPORTANT: In a PieChart, no values (Entry) should have the same
        // xIndex (even if from different DataSets), since no values can be
        // drawn above each other.
        for (int i = 0; i < count + 1; i++) {
            yVals1.add(new Entry((float) (Math.random() * mult) + mult / 5, i));
        }

        ArrayList<String> xVals = new ArrayList<String>();

        for (int i = 0; i < count + 1; i++)
            xVals.add(mActivites[i % mActivites.length]);

        PieDataSet dataSet = new PieDataSet(yVals1, "Net Calories: 1,000");
        dataSet.setSliceSpace(3f);
        dataSet.setSelectionShift(5f);

        // add a lot of colors

        ArrayList<Integer> colors = new ArrayList<Integer>();

        for (int c : ColorTemplate.VORDIPLOM_COLORS)
            colors.add(c);

        for (int c : ColorTemplate.JOYFUL_COLORS)
            colors.add(c);

        for (int c : ColorTemplate.COLORFUL_COLORS)
            colors.add(c);

        for (int c : ColorTemplate.LIBERTY_COLORS)
            colors.add(c);

        for (int c : ColorTemplate.PASTEL_COLORS)
            colors.add(c);

        colors.add(ColorTemplate.getHoloBlue());

        dataSet.setColors(colors);
        //dataSet.setSelectionShift(0f);

        PieData data = new PieData(xVals, dataSet);
        data.setValueFormatter(new PercentFormatter());
        data.setValueTextSize(13f);
        data.setValueTextColor(Color.WHITE);
        //data.setValueTypeface(tf);
        mChart.setData(data);

        // undo all highlights
        mChart.highlightValues(null);

        mChart.invalidate();
    }


    private void fillFitChart(){

        Collection<FitChartValue> values = new ArrayList<>();
        values.add(new FitChartValue(30f, 0x2d4302));
        values.add(new FitChartValue(20f, 0x75a80d));
        values.add(new FitChartValue(15f, 0x8fc026));
        values.add(new FitChartValue(10f, 0xB5CC84));
        final FitChart fitChart = (FitChart)findViewById(R.id.fitChart);
        fitChart.setMinValue(0f);
        fitChart.setMaxValue(100f);
        fitChart.setValues(values);

    }

    private void navigateToPieChart(){
        Intent intent = new Intent(getApplicationContext(), PieChartActivity.class);
        this.startActivity(intent);
    }

    private void navigateToActivityPage(int i){
        Intent intent = new Intent(getApplicationContext(), WorkoutActivity.class);
        this.startActivity(intent);
    }
}
