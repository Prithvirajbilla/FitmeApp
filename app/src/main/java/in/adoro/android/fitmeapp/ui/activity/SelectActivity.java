package in.adoro.android.fitmeapp.ui.activity;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.FrameLayout;

import in.adoro.android.fitmeapp.R;
import in.adoro.android.fitmeapp.ui.fragment.SelectActivityFragment;


public class SelectActivity extends BaseActivity {

    public FrameLayout container;

    public SelectActivityFragment mSelectActivityFragment;

    @Override
    public void onCreate(Bundle savedInstanceState, PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
        setContentView(R.layout.activity_select_activity);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

    private void setUpFeedFragment() {
        if (container != null) {
            container.setPadding(container.getPaddingLeft(), 0, container.getPaddingRight(), container.getPaddingBottom());
        }
        FragmentManager fragmentManager = getSupportFragmentManager();
        mSelectActivityFragment = new SelectActivityFragment();
        fragmentManager.beginTransaction()
                .replace(R.id.container, mSelectActivityFragment)
                .commit();
    }
}
