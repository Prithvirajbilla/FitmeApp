package in.fitbilla.ui.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import in.fitbilla.core.PA;
import in.fitbilla.ui.ViewHolder.PAViewHolder;
import in.fitbilla.ui.view.PAView;


public class SelectActivityAdapter extends RecyclerView.Adapter<PAViewHolder> {
    private List<PA> mCategories;
    private Context mContext;
    private View.OnClickListener mOnClickListener;


    public SelectActivityAdapter(Context mContext, List<PA> mCategories ) {
        this.mContext = mContext;
        this.mCategories = mCategories;
    }


    public void setOnClickListener(View.OnClickListener clickListener){
        mOnClickListener = clickListener;
    }

    @Override
    public PAViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        PAView rowView = new PAView(mContext);
        rowView.setOnClickListener(mOnClickListener);
        PAViewHolder holder = new PAViewHolder(rowView);
        rowView.setTag(holder);
        return holder;
    }


    @Override
    public void onBindViewHolder(PAViewHolder holder, int position) {
        holder.getPAView().setViewParams(mCategories.get(position));
    }


    public void updateList(List<PA> data) {
        this.mCategories.clear();
        this.mCategories.addAll(data);
        notifyDataSetChanged();
    }


    /**
     * Returns the total number of items in the data set hold by the adapter.
     *
     * @return The total number of items in this adapter.
     */
    @Override
    public int getItemCount() {
        if(mCategories!=null)
            return mCategories.size();
        else
            return 0;
    }
}
