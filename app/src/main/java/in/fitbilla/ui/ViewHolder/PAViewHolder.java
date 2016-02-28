package in.fitbilla.ui.ViewHolder;

import android.support.v7.widget.RecyclerView;

import in.fitbilla.ui.view.PAView;


public class PAViewHolder extends RecyclerView.ViewHolder {

    PAView mPAView;

    public PAViewHolder(PAView mPAView) {
        super(mPAView);

        this.mPAView = mPAView;
    }

    public PAView getPAView() {
        return mPAView;
    }

    public void setPAView(PAView paView) {
        this.mPAView = paView;
    }
}
