package in.fitbilla;

import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;
import com.github.mikephil.charting.listener.OnChartValueSelectedListener;

import java.math.BigDecimal;
import java.util.Calendar;

import butterknife.Bind;
import butterknife.ButterKnife;

public class WorkoutActivity extends AppCompatActivity implements OnChartValueSelectedListener {

    /**
     * The {@link android.support.v4.view.PagerAdapter} that will provide
     * fragments for each of the sections. We use a
     * {@link FragmentPagerAdapter} derivative, which will keep every
     * loaded fragment in memory. If this becomes too memory intensive, it
     * may be best to switch to a
     * {@link android.support.v4.app.FragmentStatePagerAdapter}.
     */
    private SectionsPagerAdapter mSectionsPagerAdapter;

    private static long activityStartTime;
    private static boolean isActivityInProgress;

    /**
     * The {@link ViewPager} that will host the section contents.
     */
    private ViewPager mViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_workout);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        // Create the adapter that will return a fragment for each of the three
        // primary sections        of the activity.
        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

        // Set up the ViewPager with the sections adapter.
        mViewPager = (ViewPager) findViewById(R.id.container);
        mViewPager.setAdapter(mSectionsPagerAdapter);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(mViewPager);

        final FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(!isActivityInProgress) {
                    Snackbar.make(view, "Running Started. Never Give Up..", Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show();
                    fab.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(R.color.colorAccent)));
                    fab.setImageResource(R.drawable.ic_pause_24dp);
                    activityStartTime = Calendar.getInstance().getTimeInMillis()/1000;
                    isActivityInProgress = true;
                } else {
                    fab.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(R.color.colorGreen)));
                    fab.setImageResource(R.drawable.ic_stop_24dp);
                    long activityDurationInSecs = (Calendar.getInstance().getTimeInMillis()/1000) - activityStartTime;

                    Snackbar.make(view, "Whoa! Your session is complete"+activityDurationInSecs, Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show();
                    isActivityInProgress = false;

                }
            }
        });
        ButterKnife.bind(this);

    }
    public static int[] splitToComponentTimes(BigDecimal biggy)
    {
        long longVal = biggy.longValue();
        int hours = (int) longVal / 3600;
        int remainder = (int) longVal - hours * 3600;
        int mins = remainder / 60;
        remainder = remainder - mins * 60;
        int secs = remainder;

        int[] ints = {hours , mins , secs};
        return ints;
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_workout, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    /**
     * Called when a value has been selected inside the chart.
     *
     * @param e            The selected Entry.
     * @param dataSetIndex The index in the datasets array of the data object
     *                     the Entrys DataSet is in.
     * @param h            the corresponding highlight object that contains information
     */
    @Override
    public void onValueSelected(Entry e, int dataSetIndex, Highlight h) {

    }

    /**
     * Called when nothing has been selected or an "un-select" has been made.
     */
    @Override
    public void onNothingSelected() {

    }

    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends Fragment {
        /**
         * The fragment argument representing the section number for this
         * fragment.
         */
        private static final String ARG_SECTION_NUMBER = "section_number";

        //private static CircleTimerView ctvDuration;

        public PlaceholderFragment() {
        }

        @Bind(R.id.chart1)
        private LineChart mChart;

        private TextView textView;

        @Bind(R.id.imageView)
        private ImageView view;

        /**
         * Returns a new instance of this fragment for the given section
         * number.
         */
        public static PlaceholderFragment newInstance(int sectionNumber) {
            PlaceholderFragment fragment = new PlaceholderFragment();
            Bundle args = new Bundle();
            args.putInt(ARG_SECTION_NUMBER, sectionNumber);
            fragment.setArguments(args);
            return fragment;
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {

            View rootView = inflater.inflate(R.layout.fragment_workout, container, false);
            ButterKnife.bind(this, rootView);
            switch (getArguments().getInt(ARG_SECTION_NUMBER)){

                case 0:
                    view.setImageResource(R.drawable.coverpic);
                    view.setVisibility(View.VISIBLE);
                    mChart.setVisibility(View.GONE);
                    break;
                case 1:
                    view.setImageResource(R.drawable.coverpic);
                    view.setVisibility(View.VISIBLE);
                    mChart.setVisibility(View.GONE);
                    break;

                case 2:
                    view.setVisibility(View.GONE);
                    mChart.setVisibility(View.VISIBLE);
                    break;


            }
            mChart.setDrawGridBackground(false);
            mChart.setDescription("");

            // add an empty data object
            mChart.setData(new LineData());
//        mChart.getXAxis().setDrawLabels(false);
//        mChart.getXAxis().setDrawGridLines(false);

            mChart.invalidate();


            return rootView;

        }

        private void addEntry() {

            LineData data = mChart.getData();

            if(data != null) {

                ILineDataSet set = data.getDataSetByIndex(0);
                // set.addEntry(...); // can be called as well

                if (set == null) {
                    set = createSet();
                    data.addDataSet(set);
                }

                // add a new x-value first
                data.addXValue(set.getEntryCount() + "");

                // choose a random dataSet
                int randomDataSetIndex = (int) (Math.random() * data.getDataSetCount());

                data.addEntry(new Entry((float) (Math.random() * 10) + 50f, set.getEntryCount()), randomDataSetIndex);

                // let the chart know it's data has changed
                mChart.notifyDataSetChanged();

                mChart.setVisibleXRangeMaximum(6);
                mChart.setVisibleYRangeMaximum(15, YAxis.AxisDependency.LEFT);
                // this automatically refreshes the chart (calls invalidate())
                mChart.moveViewTo(data.getXValCount()-7, 50f, YAxis.AxisDependency.LEFT);
            }
        }



        private LineDataSet createSet() {

            LineDataSet set = new LineDataSet(null, "DataSet 1");
            set.setLineWidth(2.5f);
            set.setCircleRadius(4.5f);
            set.setColor(Color.rgb(240, 99, 99));
            set.setCircleColor(Color.rgb(240, 99, 99));
            set.setHighLightColor(Color.rgb(190, 190, 190));
            set.setAxisDependency(YAxis.AxisDependency.LEFT);
            set.setValueTextSize(10f);

            return set;
        }


    }

    /**
     * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
     * one of the sections/tabs/pages.
     */
    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            // getItem is called to instantiate the fragment for the given page.
            // Return a PlaceholderFragment (defined as a static inner class below).
            return PlaceholderFragment.newInstance(position + 1);
        }

        @Override
        public int getCount() {
            // Show 3 total pages.
            return 3 ;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            switch (position) {
                case 0:
                    return "GOAL";
                case 1:
                    return "PLAN";
                case 2:
                    return "TRACK";
            }
            return null;
        }
    }
}
