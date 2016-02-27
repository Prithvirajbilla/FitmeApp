package in.adoro.android.fitmeapp.ui.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import butterknife.Bind;
import butterknife.ButterKnife;
import in.adoro.android.fitmeapp.R;
import in.adoro.android.fitmeapp.core.PA;

/**
 * Created by akshay on 2/27/16.
 */
public class PAView extends RelativeLayout {

    Context mContext;

    @Bind(R.id.img_activity)
    public ImageView imgViewActivityBg;

    @Bind(R.id.txt_category)
    public TextView txtViewCategoryTitle;

    public PAView(Context context) {
        super(context);
        init(context,null,0);
    }

    public PAView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs, 0);
    }

    public PAView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs, defStyleAttr);
    }

    private void init(Context context,AttributeSet attrs, int defStyle) {
        mContext = context;
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.card_physical_activity, this);

        ButterKnife.bind(this);
    }

    public void setViewParams(PA activity) {
        setTxtViewCategoryTitle(activity.getActivityName());
    }

    public String getTxtViewCategoryTitle() {
        return (String) txtViewCategoryTitle.getText();
    }

    public void setTxtViewCategoryTitle(String txtCategoryTitle) {
        this.txtViewCategoryTitle.setText(txtCategoryTitle);
    }

}
