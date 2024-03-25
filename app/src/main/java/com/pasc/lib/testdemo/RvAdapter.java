package com.pasc.lib.testdemo;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.TextView;

import java.util.List;

import brvah.BaseQuickAdapter;
import brvah.BaseViewHolder;
import event.SzrmRecommend;
import model.bean.SZContentModel;

public class RvAdapter extends BaseQuickAdapter<SZContentModel.DataDTO.ContentsDTO, BaseViewHolder> {
    private Context context;

    public RvAdapter(Context context, int layoutResId, @Nullable List<SZContentModel.DataDTO.ContentsDTO> data) {
        super(layoutResId, data);
        this.context = context;
    }

    @Override
    protected void convert(@NonNull BaseViewHolder helper, SZContentModel.DataDTO.ContentsDTO item) {
        TextView rvItemTV = helper.getView(R.id.rv_item_tv);
        rvItemTV.setText(item.getTitle());
        rvItemTV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SzrmRecommend.getInstance().routeToDetailPage(context, item);
            }
        });
    }
}
