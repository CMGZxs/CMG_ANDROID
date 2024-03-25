package adpter;




import android.support.annotation.Nullable;


import com.pasc.lib.videolib.R;

import java.util.List;

import brvah.BaseQuickAdapter;
import brvah.BaseViewHolder;

public class VideoDetailKeyWordAdapter extends BaseQuickAdapter<String, BaseViewHolder> {
    public VideoDetailKeyWordAdapter(int layoutResId, @Nullable List<String> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, String item) {
        helper.setText(R.id.keyword_tv, item);
    }
}
