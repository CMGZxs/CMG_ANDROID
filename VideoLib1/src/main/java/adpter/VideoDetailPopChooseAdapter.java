package adpter;

import android.content.Context;
import android.view.View;
import android.widget.RadioButton;

import android.support.annotation.Keep;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;



import java.util.List;

import brvah.BaseQuickAdapter;
import brvah.BaseViewHolder;
import common.model.DataDTO;
import tencent.liteav.demo.superplayer.SuperPlayerView;

@Keep
public class VideoDetailPopChooseAdapter extends BaseQuickAdapter<DataDTO, BaseViewHolder> {
    private List<DataDTO> mDatas;
    private RecyclerView mRecyclerView;
    private Context mContext;
    private String mContentId;
    private int position; //记录点击的下标
    private SuperPlayerView superPlayerView;

    public VideoDetailPopChooseAdapter(int layoutResId, Context context,
                                       @Nullable List<DataDTO> data,
                                       RecyclerView recyclerView, String contentId,
                                       SuperPlayerView superPlayerView) {
        super(layoutResId, data);
        this.mContext = context;
        this.mDatas = data;
        this.mRecyclerView = recyclerView;
        this.mContentId = contentId;
        this.superPlayerView = superPlayerView;
    }

    @Override
    protected void convert(final BaseViewHolder helper, final DataDTO item) {
//        final RadioButton radioButton = helper.getView(R.id.choose_radiobutton);
//        radioButton.setText(helper.getAdapterPosition() + 1 + "");
//        if (mContentId.equals(String.valueOf(item.getId()))) {
//            radioButton.setTextColor(mContext.getResources().getColor(R.color.choose_pop_checked_textcolor));
//            radioButton.setBackgroundResource(R.drawable.choose_pop_rv_item_choose);
//        } else {
//            radioButton.setTextColor(mContext.getResources().getColor(R.color.white));
//            radioButton.setBackgroundResource(R.drawable.choose_pop_rv_item_unchoose);
//        }
//
//        radioButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                mContentId = String.valueOf(mDatas.get(helper.getAdapterPosition()).getId());
//                if (mRecyclerView.isComputingLayout()) {
//                    mRecyclerView.post(new Runnable() {
//                        @Override
//                        public void run() {
//                            for (int i = 0; i < mDatas.size(); i++) {
//                                if (i == helper.getAdapterPosition()) {
//                                    mContentId = String.valueOf(mDatas.get(helper.getAdapterPosition()).getId());
//                                    position = i;
//                                    radioButton.setTextColor(mContext.getResources().getColor(R.color.choose_pop_checked_textcolor));
//                                    radioButton.setBackgroundResource(R.drawable.choose_pop_rv_item_choose);
//                                } else {
//                                    radioButton.setTextColor(mContext.getResources().getColor(R.color.white));
//                                    radioButton.setBackgroundResource(R.drawable.choose_pop_rv_item_unchoose);
//                                }
//                            }
//                            notifyDataSetChanged();
//                        }
//                    });
//                } else {
//                    for (int i = 0; i < mDatas.size(); i++) {
//                        if (i == helper.getAdapterPosition()) {
//                            mContentId = String.valueOf(mDatas.get(helper.getAdapterPosition()).getId());
//                            position = i;
//                            radioButton.setTextColor(mContext.getResources().getColor(R.color.choose_pop_checked_textcolor));
//                            radioButton.setBackgroundResource(R.drawable.choose_pop_rv_item_choose);
//                        } else {
//                            radioButton.setTextColor(mContext.getResources().getColor(R.color.white));
//                            radioButton.setBackgroundResource(R.drawable.choose_pop_rv_item_unchoose);
//                        }
//                    }
//                    notifyDataSetChanged();
//                }
//                if (null != ((VideoDetailActivity) mContext).choosePop && ((VideoDetailActivity) mContext).choosePop.getPopupWindow().isShowing()) {
//                    ((VideoDetailActivity) mContext).choosePop.dissmiss();
//                }
//                ((VideoDetailActivity) mContext).myContentId = String.valueOf(mDatas.get(position).getId());
//
//                if (SPUtils.isVisibleNoWifiView(mContext)) {
//                    SPUtils.getInstance().put(Constants.AGREE_NETWORK, "0");
//                } else {
//                    ((VideoDetailActivity) mContext).play(mDatas.get(position).getPlayUrl(), mDatas.get(position).getTitle());
//                }
//
//                if (!"1".equals(superPlayerView.mFullScreenPlayer.strSpeed)) {
//                    superPlayerView.mFullScreenPlayer.mVodMoreView.mCallback.onSpeedChange(1.0f);
//                    superPlayerView.mFullScreenPlayer.superplayerSpeed.setText("倍速");
//                    superPlayerView.mFullScreenPlayer.mRbSpeed1.setChecked(true);
//                }
//
//                ((VideoDetailActivity) mContext).getCommentList("1", "10", true);
//                ((VideoDetailActivity) mContext).getContentState(String.valueOf(mDatas.get(position).getId()));
//                ((VideoDetailActivity) mContext).mDatas.set(((VideoDetailActivity) mContext).currentIndex, mDatas.get(position));
//                ((VideoDetailActivity) mContext).adapter.notifyDataSetChanged();
//            }
//        });
    }

    public void setContentId(String contentId) {
        this.mContentId = contentId;
    }
}
