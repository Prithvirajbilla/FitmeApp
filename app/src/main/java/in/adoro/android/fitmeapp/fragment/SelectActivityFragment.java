package in.adoro.android.fitmeapp.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import butterknife.Bind;

import in.adoro.android.fitmeapp.R;

/**
 * Created by akshay on 2/27/16.
 */
public class SelectActivityFragment extends Fragment {

    @Bind(R.id.recycler_view)
    public RecyclerView mFeedRecyclerView;

    /*private SelectActivityAdapter mAdapter;*/
    private GridLayoutManager mGridLayoutManager;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
}
