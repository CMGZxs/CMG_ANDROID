package common.callback;

import android.support.annotation.Nullable;


import java.util.List;

import brvah.BaseQuickAdapter;
import brvah.BaseViewHolder;
import common.model.ShareModel;

public class ShareAdapter extends BaseQuickAdapter<ShareModel, BaseViewHolder> {
    public ShareAdapter(int layoutResId, @Nullable List<ShareModel> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, ShareModel item) {

    }
}
