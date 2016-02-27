package in.adoro.android.fitmeapp.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.Bind;

import butterknife.ButterKnife;
import in.adoro.android.fitmeapp.R;
import in.adoro.android.fitmeapp.ui.adapter.SelectActivityAdapter;


public class SelectActivityFragment extends Fragment {

    @Bind(R.id.recycler_view)
    public RecyclerView mRecyclerView;

    private SelectActivityAdapter mAdapter;
    private GridLayoutManager mGridLayoutManager;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_select_activity, container, false);
        ButterKnife.bind(this, rootView);
        return rootView;
    }

}
