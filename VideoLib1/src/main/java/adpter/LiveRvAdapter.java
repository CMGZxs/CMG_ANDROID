package adpter;

import android.content.Context;
import android.text.TextUtils;
import android.widget.ImageView;
import android.widget.TextView;

import android.support.annotation.Nullable;

import com.bumptech.glide.Glide;
import com.pasc.lib.videolib.R;

import java.util.List;

import brvah.BaseQuickAdapter;
import brvah.BaseViewHolder;
import common.model.DataDTO;
import ui.activity.VideoHomeActivity;

public class LiveRvAdapter extends BaseQuickAdapter<DataDTO, BaseViewHolder> {
    private Context mContext;

    public LiveRvAdapter(int layoutResId, @Nullable List<DataDTO> data, Context context) {
        super(layoutResId, data);
        this.mContext = context;
    }

    @Override
    protected void convert(BaseViewHolder helper, DataDTO item) {
        ImageView imageView = helper.getView(R.id.live_item_img);
        ImageView liveState = helper.getView(R.id.item_live_state);
        if (TextUtils.equals(item.getLiveStatus(),"0")) {
            liveState.setImageResource(R.drawable.szrm_sdk_live_preview);
        } else if (TextUtils.equals(item.getLiveStatus(),"1")) {
            liveState.setImageResource(R.drawable.szrm_sdk_living);
        } else {
            liveState.setImageResource(R.drawable.szrm_sdk_live_end);
        }
        TextView textView = helper.getView(R.id.live_item_title);
        if (null != mContext && !((VideoHomeActivity)mContext).isFinishing()
                && !((VideoHomeActivity)mContext).isDestroyed()) {
            Glide.with(mContext)
                    .load(item.getThumbnailUrl())
                    .into(imageView);
        }

        textView.setText(item.getTitle());
    }
}
