package adpter;

import android.content.Context;
import android.text.SpannableStringBuilder;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.ViewFlipper;

import android.support.annotation.Nullable;

import com.bumptech.glide.Glide;
import com.pasc.lib.videolib.R;

import java.util.ArrayList;
import java.util.List;

import brvah.BaseQuickAdapter;
import brvah.BaseViewHolder;
import common.constants.Constants;
import common.manager.ViewPagerLayoutManager;
import common.model.DataDTO;
import common.model.RecommendModel;
import common.utils.NumberFormatTool;
import common.utils.ScreenUtils;
import smartrefresh.layout.SmartRefreshLayout;
import tencent.liteav.demo.superplayer.SuperPlayerDef;
import tencent.liteav.demo.superplayer.SuperPlayerView;
import ui.activity.VideoHomeActivity;
import utils.GlideUtil;
import widget.EllipsizeTextView;

import static common.constants.Constants.BLUE_V;
import static common.constants.Constants.YELLOW_V;
import static common.utils.SPUtils.isVisibleNoWifiView;
import static common.callback.VideoInteractiveParam.param;
import static ui.activity.VideoHomeActivity.videoIsNormal;

public class VideoDetailAdapter extends BaseQuickAdapter<DataDTO, BaseViewHolder> {
    private Context mContext;
    private List<DataDTO> mDatas;
    private List<RecommendModel.DataDTO.RecordsDTO> recommendList = new ArrayList<>();
    private boolean mIsAuto;
    private String brief;
    private String spaceStr = "";
    private SuperPlayerView superPlayerView;
    private ToAddPlayerViewClick click;
    private SmartRefreshLayout mRefreshlayout;
    private RelativeLayout mVideoDetailCommentBtn;
    private ViewPagerLayoutManager mVideoDetailmanager;
    private String topicNameStr;

    public VideoDetailAdapter(int layoutResId, @Nullable List<DataDTO> data, Context context,
                              SuperPlayerView playerView, SmartRefreshLayout refreshLayout, RelativeLayout videoDetailCommentBtn, ViewPagerLayoutManager videoDetailmanager) {
        super(layoutResId, data);
        this.mContext = context;
        this.mDatas = data;
        this.superPlayerView = playerView;
        this.mRefreshlayout = refreshLayout;
        this.mVideoDetailCommentBtn = videoDetailCommentBtn;
        this.mVideoDetailmanager = videoDetailmanager;
    }


    @Override
    protected void convert(final BaseViewHolder helper, final DataDTO item) {
        if (null == item) {
            return;
        }
        RelativeLayout itemRootView = helper.getView(R.id.item_relativelayout);
        LinearLayout introduceLin = helper.getView(R.id.introduce_lin);
        final RelativeLayout noWifiLl = helper.getView(R.id.agree_nowifi_play);
        TextView continuePlay = helper.getView(R.id.continue_play);
        LinearLayout fullLin = helper.getView(R.id.superplayer_iv_fullscreen);
        ImageView publisherHeadimg = helper.getView(R.id.publisher_headimg);
        TextView publisherName = helper.getView(R.id.publisher_name);
        ImageView officialCertificationImg = helper.getView(R.id.official_certification_img);
        TextView watched = helper.getView(R.id.watched);
        final EllipsizeTextView foldTextView = helper.getView(R.id.fold_text);
        final TextView expendText = helper.getView(R.id.expend_text);
        ImageView verticalVideoWdcsLogo = helper.getView(R.id.vertical_video_wdcs_logo);
        ImageView horizontalVideoWdcsLogo = helper.getView(R.id.horizontal_video_wdcs_logo);
        ImageView coverPicture = helper.getView(R.id.cover_picture);
        final TextView ellipsisTv = helper.getView(R.id.ellipsis_tv);


        RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) coverPicture.getLayoutParams();
        DisplayMetrics outMetrics = new DisplayMetrics();
        ((VideoHomeActivity) mContext).getWindowManager().getDefaultDisplay().getRealMetrics(outMetrics);
        double widthPixel = outMetrics.widthPixels;
        double heightPixel = outMetrics.heightPixels;
        if (TextUtils.equals("2", videoIsNormal(Integer.parseInt(NumberFormatTool.getNumStr(item.getWidth())),
                Integer.parseInt(NumberFormatTool.getNumStr(item.getHeight()))))) {
            //横板标准视频
            verticalVideoWdcsLogo.setVisibility(View.GONE);
            horizontalVideoWdcsLogo.setVisibility(View.VISIBLE);
            layoutParams.removeRule(RelativeLayout.ALIGN_PARENT_TOP);
            layoutParams.addRule(RelativeLayout.CENTER_IN_PARENT);
            layoutParams.width = (int) widthPixel - 1;
            layoutParams.height = (int) (widthPixel / Constants.Horizontal_Proportion);
            if (null != mContext && !((VideoHomeActivity) mContext).isFinishing()
                    && !((VideoHomeActivity) mContext).isDestroyed()) {
                Glide.with(mContext)
                        .load(item.getImagesUrl())
                        .into(coverPicture);
            }
        } else if (TextUtils.equals("1", videoIsNormal(Integer.parseInt(NumberFormatTool.getNumStr(item.getWidth())),
                Integer.parseInt(NumberFormatTool.getNumStr(item.getHeight()))))) {
            //竖版视频
            verticalVideoWdcsLogo.setVisibility(View.VISIBLE);
            horizontalVideoWdcsLogo.setVisibility(View.GONE);

            if (phoneIsNormal()) {
                layoutParams.removeRule(RelativeLayout.ALIGN_PARENT_TOP);
                layoutParams.addRule(RelativeLayout.CENTER_IN_PARENT);
                layoutParams.setMargins(0, 0, 0, 0);
            } else {
                layoutParams.addRule(RelativeLayout.ALIGN_PARENT_TOP);
            }
            layoutParams.width = (int) widthPixel - 1;
            layoutParams.height = (int) (widthPixel / Constants.Portrait_Proportion);
            if (null != mContext && !((VideoHomeActivity) mContext).isFinishing()
                    && !((VideoHomeActivity) mContext).isDestroyed()) {
                Glide.with(mContext)
                        .load(item.getImagesUrl())
                        .into(coverPicture);
            }
        } else {
            //非标准视频
            //非标准视频
            verticalVideoWdcsLogo.setVisibility(View.VISIBLE);
            horizontalVideoWdcsLogo.setVisibility(View.GONE);
            layoutParams.width = (int) widthPixel - 1;
            layoutParams.removeRule(RelativeLayout.ALIGN_PARENT_TOP);
            layoutParams.addRule(RelativeLayout.CENTER_IN_PARENT);
            layoutParams.setMargins(0, 0, 0, 0);

            double percent = Double.parseDouble(item.getWidth()) / Double.parseDouble(item.getHeight());
            double mHeight;
            mHeight = layoutParams.width / percent;
            layoutParams.height = (int) mHeight;

            if (null != mContext && !((VideoHomeActivity) mContext).isFinishing()
                    && !((VideoHomeActivity) mContext).isDestroyed()) {
                Glide.with(mContext)
                        .load(item.getImagesUrl())
                        .into(coverPicture);
            }
        }

        //非wifi状态下布局是否显示
        if (item.isWifi()) {
            noWifiLl.setVisibility(View.INVISIBLE);
//            //全屏按钮是否显示
//            if (item.isFullBtnIsShow()) {
//                fullLin.setVisibility(View.VISIBLE);
//            } else {
//                fullLin.setVisibility(View.GONE);
//            }
        } else {
            noWifiLl.setVisibility(View.VISIBLE);
        }


        //无wifi时继续播放按钮
        continuePlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                noWifiLl.setVisibility(View.GONE);
                click.clickNoWifi(helper.getAdapterPosition());
                if (null != superPlayerView) {
                    superPlayerView.setOrientation(true);
                }
            }
        });

        //全屏按钮
        fullLin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isVisibleNoWifiView(mContext)) {
                    return;
                }
                if (superPlayerView.mWindowPlayer.mControllerCallback != null) {
                    superPlayerView.mWindowPlayer.mControllerCallback.onSwitchPlayMode(SuperPlayerDef.PlayerMode.FULLSCREEN);
                }
            }
        });
        if (null != mContext && !((VideoHomeActivity) mContext).isFinishing()
                && !((VideoHomeActivity) mContext).isDestroyed()) {
            if (null != mContext && !((VideoHomeActivity) mContext).isFinishing()
                    && !((VideoHomeActivity) mContext).isDestroyed()) {
                GlideUtil.displayCircle(publisherHeadimg, item.getIssuerImageUrl(), true, mContext);
//                Glide.with(mContext)
//                        .load(item.getIssuerImageUrl())
//                        .into(publisherHeadimg);
            }
            publisherName.setText(item.getIssuerName());
        }

        publisherHeadimg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                //跳转H5头像TA人主页  视频模块不需要跳转
//                try {
//                    param.recommendUrl(Constants.HEAD_OTHER + item.getCreateBy());
//                } catch (Exception e) {
//                    e.printStackTrace();
//                }
            }
        });

        if (TextUtils.isEmpty(item.getCreatorCertMark())) {
            officialCertificationImg.setVisibility(View.GONE);
        } else {
            officialCertificationImg.setVisibility(View.VISIBLE);
            if (TextUtils.equals(item.getCreatorCertMark(), BLUE_V)) {
                officialCertificationImg.setImageResource(R.drawable.szrm_sdk_official_certification);
            } else if (TextUtils.equals(item.getCreatorCertMark(), YELLOW_V)) {
                officialCertificationImg.setImageResource(R.drawable.szrm_sdk_yellow_v);
            }
        }

        //观看人数
        watched.setText(NumberFormatTool.formatNum(item.getViewCountShow(), false));

        if (TextUtils.isEmpty(item.getBelongTopicName()) || null == item.getBelongTopicName()) {
            topicNameStr = "";
        } else {
            topicNameStr = "#" + item.getBelongTopicName();
        }

//        huati.setText(ButtonSpan.subStrByLen(topicNameStr, 24));
//        huati.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
                //行为埋点 话题点击
                //跳转H5话题详情
//                try {
//                    if (Utils.mIsDebug) {
//                        param.recommendUrl(Constants.TOPIC_DETAILS + item.getBelongTopicId(), null);
//                    } else {
//                        param.recommendUrl(Constants.TOPIC_DETAILS_ZS + item.getBelongTopicId(), null);
//                    }
//
//                } catch (Exception e) {
//                    e.printStackTrace();
//                }


//            }
//        });

//        if (TextUtils.isEmpty(item.getBrief())) {
//            brief = item.getTitle();
//        } else {
//            brief = item.getBrief();
//        }

//        if (TextUtils.isEmpty(brief)) {
//            foldTextView.setVisibility(View.GONE);
//        } else {
//            foldTextView.setVisibility(View.VISIBLE);
//        }

//        if (huati.getText().length() != 0) {
//            int num;
//            if (huati.getText().length() > 13) {
//                num = 12 + 3;
//            } else {
//                num = huati.getText().length() + 1;
//            }
//
//            for (int i = 0; i < num; i++) {
//                spaceStr = spaceStr + "\u3000";
//                item.setSpaceStr(spaceStr);
//            }
//            spaceStr = "";
//        }
        if (TextUtils.isEmpty(item.getBrief())) {
            brief = item.getTitle();
        } else {
            brief = item.getBrief();
        }

        foldTextView.setText(brief);
        if (foldTextView.getLineCount() > 2 && foldTextView.getVisibility() == View.VISIBLE) {
            ellipsisTv.setVisibility(View.VISIBLE);
        } else {
            ellipsisTv.setVisibility(View.GONE);
        }
        foldTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //行为埋点 button_name 展开简介
                if (foldTextView.getVisibility() == View.VISIBLE) {
                    foldTextView.setVisibility(View.GONE);
                    expendText.setVisibility(View.VISIBLE);
                    if (foldTextView.getLineCount() > 2 && foldTextView.getVisibility() == View.VISIBLE) {
                        ellipsisTv.setVisibility(View.VISIBLE);
                    } else {
                        ellipsisTv.setVisibility(View.GONE);
                    }
                }
            }
        });

        expendText.setText(brief);
        expendText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (expendText.getVisibility() == View.VISIBLE) {
                    expendText.setVisibility(View.GONE);
                    foldTextView.setVisibility(View.VISIBLE);
                }
                if (foldTextView.getLineCount() > 2 && foldTextView.getVisibility() == View.VISIBLE) {
                    ellipsisTv.setVisibility(View.VISIBLE);
                } else {
                    ellipsisTv.setVisibility(View.GONE);
                }
            }
        });

    }

    /**
     * 获取首页滚动消息
     */
    public void getViewFlipperData(final List<RecommendModel.DataDTO.RecordsDTO> list,
                                   final ViewFlipper viewFlipper, final DataDTO mItem) {
        if (null == mContext) {
            return;
        }
        for (int i = 0; i < list.size(); i++) {
            String item = list.get(i).getTitle();
            View view = View.inflate(mContext, R.layout.customer_viewflipper_item, null);
            ImageView viewFlipperIcon = view.findViewById(R.id.view_flipper_icon);
            if (null != mContext && !((VideoHomeActivity) mContext).isFinishing()
                    && !((VideoHomeActivity) mContext).isDestroyed()) {
                Glide.with(mContext)
                        .load(list.get(i).getThumbnailUrl())
                        .into(viewFlipperIcon);
            }

            TextView textView = view.findViewById(R.id.view_flipper_content);
            textView.setTextColor(mContext.getResources().getColor(R.color.white));
            textView.setText(item);
            ImageView cancel = view.findViewById(R.id.view_flipper_cancel);
            cancel.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    viewFlipper.stopFlipping();
                    viewFlipper.setVisibility(View.GONE);
                    mItem.setClosed(true);
                }
            });
            viewFlipper.addView(view);
        }

        //viewFlipper的点击事件
        viewFlipper.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //获取子View的id
                int mPosition = viewFlipper.getDisplayedChild();
                try {
                    param.recommendUrl(list.get(mPosition).getUrl(), null);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public interface ToAddPlayerViewClick {
        void clickNoWifi(int position);
    }

    public void setToAddPlayerViewClick(ToAddPlayerViewClick listener) {
        this.click = listener;
    }

    /**
     * 手机是否为16：9
     *
     * @return
     */
    private boolean phoneIsNormal() {
        int phoneWidth = ScreenUtils.getPhoneWidth(mContext);
        int phoneHeight = ScreenUtils.getPhoneHeight(mContext);
        if (phoneHeight * 9 == phoneWidth * 16) {
            return true;
        } else {
            return false;
        }
    }
}
