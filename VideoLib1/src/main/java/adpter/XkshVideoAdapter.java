package adpter;

import android.app.Activity;
import android.content.Context;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.ViewFlipper;


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
import common.utils.AppInit;
import common.utils.NumberFormatTool;
import common.utils.PersonInfoManager;
import common.utils.ScreenUtils;
import smartrefresh.layout.SmartRefreshLayout;
import tencent.liteav.demo.superplayer.SuperPlayerDef;
import tencent.liteav.demo.superplayer.SuperPlayerView;
import ui.activity.VideoHomeActivity;
import utils.GlideUtil;
import widget.EllipsizeTextView;

import static common.callback.VideoInteractiveParam.param;
import static common.constants.Constants.BLUE_V;
import static common.constants.Constants.YELLOW_V;
import static common.utils.SPUtils.isVisibleNoWifiView;
import static ui.activity.VideoHomeActivity.videoIsNormal;

import android.support.annotation.Keep;
import android.support.annotation.Nullable;

@Keep
public class XkshVideoAdapter extends BaseQuickAdapter<DataDTO, BaseViewHolder> {
    private Context mContext;
    private List<DataDTO> mDatas;
    private List<RecommendModel.DataDTO.RecordsDTO> recommendList = new ArrayList<>();
    private boolean mIsAuto;
    private String brief;
    private SuperPlayerView superPlayerView;
    private ToAddPlayerViewClick click;
    private FollowViewClick followViewClick;
    private SmartRefreshLayout mRefreshlayout;
    private ViewPagerLayoutManager mVideoDetailmanager;
    private String spaceStr = "";
    //    private String topicNameStr;
    private boolean isClick = true;
    private boolean isWifiBord;
    private String logoUrl;

    public XkshVideoAdapter(int layoutResId, @Nullable List<DataDTO> data, Context context,
                            SuperPlayerView playerView, SmartRefreshLayout refreshLayout, ViewPagerLayoutManager videoDetailmanager
            , String mLogoUrl) {
        super(layoutResId, data);
        this.mContext = context;
        this.mDatas = data;
        this.superPlayerView = playerView;
        this.mRefreshlayout = refreshLayout;
        this.mVideoDetailmanager = videoDetailmanager;
        this.logoUrl = mLogoUrl;
    }

    @Override
    protected void convert(final BaseViewHolder helper, final DataDTO item) {
        RelativeLayout itemRootView = helper.getView(R.id.item_relativelayout);
        LinearLayout introduceLin = helper.getView(R.id.introduce_lin);
        final RelativeLayout noWifiLl = helper.getView(R.id.agree_nowifi_play);
        TextView continuePlay = helper.getView(R.id.continue_play);
        LinearLayout fullLin = helper.getView(R.id.superplayer_iv_fullscreen);
        ImageView publisherHeadimg = helper.getView(R.id.publisher_headimg);
        TextView publisherName = helper.getView(R.id.publisher_name);
        ImageView officialCertificationImg = helper.getView(R.id.official_certification_img);
        TextView watched = helper.getView(R.id.watched);
        final TextView foldTextView = helper.getView(R.id.fold_text);
        final TextView expendText = helper.getView(R.id.expend_text);
        ImageView verticalVideoWdcsLogo = helper.getView(R.id.vertical_video_wdcs_logo);
        ImageView horizontalVideoWdcsLogo = helper.getView(R.id.horizontal_video_wdcs_logo);
        ImageView coverPicture = helper.getView(R.id.cover_picture);
        LinearLayout videoDetailLikes = helper.getView(R.id.video_detail_likes);
        final ImageView videoDetailLikesImage = helper.getView(R.id.video_detail_likes_image);
        final TextView likesNum = helper.getView(R.id.likes_num);
        LinearLayout videoDetailCommentLl = helper.getView(R.id.video_detail_comment_ll);
        final TextView commentNum = helper.getView(R.id.comment_num);
        LinearLayout share = helper.getView(R.id.share);

        if (null != mContext && !((VideoHomeActivity) mContext).isFinishing()
                && !((VideoHomeActivity) mContext).isDestroyed()) {
            Glide.with(mContext)
                    .load(logoUrl)
                    .into(verticalVideoWdcsLogo);
        }

        if (null != mContext && !((VideoHomeActivity) mContext).isFinishing()
                && !((VideoHomeActivity) mContext).isDestroyed()) {
            Glide.with(mContext)
                    .load(logoUrl)
                    .into(horizontalVideoWdcsLogo);
        }

        RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) coverPicture.getLayoutParams();
        DisplayMetrics outMetrics = new DisplayMetrics();
        ((Activity) mContext).getWindowManager().getDefaultDisplay().getRealMetrics(outMetrics);
        double widthPixel = outMetrics.widthPixels;
        if (TextUtils.equals("2", videoIsNormal(Integer.parseInt(NumberFormatTool.getNumStr(item.getWidth())),
                Integer.parseInt(NumberFormatTool.getNumStr(item.getHeight()))))) {
            if (!isWifiBord) {
                //横板标准视频
                verticalVideoWdcsLogo.setVisibility(View.GONE);
                horizontalVideoWdcsLogo.setVisibility(View.VISIBLE);
                layoutParams.removeRule(RelativeLayout.ALIGN_PARENT_TOP);
                layoutParams.addRule(RelativeLayout.CENTER_IN_PARENT);
                layoutParams.width = (int) widthPixel - 1;
                layoutParams.height = (int) (widthPixel / Constants.Horizontal_Proportion);

                if (null != mContext && !((Activity) mContext).isFinishing()
                        && !((Activity) mContext).isDestroyed()) {
                    Glide.with(mContext)
                            .load(item.getImagesUrl())
                            .into(coverPicture);
                }
            }

        } else if (TextUtils.equals("1", videoIsNormal(Integer.parseInt(NumberFormatTool.getNumStr(item.getWidth())),
                Integer.parseInt(NumberFormatTool.getNumStr(item.getHeight()))))) {
            //竖版视频
            if (!isWifiBord) {
                if (phoneIsNormal()) {
                    layoutParams.removeRule(RelativeLayout.ALIGN_PARENT_TOP);
                    layoutParams.addRule(RelativeLayout.CENTER_IN_PARENT);
                } else {
                    layoutParams.addRule(RelativeLayout.ALIGN_PARENT_TOP);
                }
                verticalVideoWdcsLogo.setVisibility(View.VISIBLE);
                horizontalVideoWdcsLogo.setVisibility(View.GONE);

                layoutParams.setMargins(0, 0, 0, 0);
                layoutParams.width = (int) widthPixel - 1;
                layoutParams.height = (int) (widthPixel / Constants.Portrait_Proportion);

                if (null != mContext && !((Activity) mContext).isFinishing()
                        && !((Activity) mContext).isDestroyed()) {
                    Glide.with(mContext)
                            .load(item.getImagesUrl())
                            .into(coverPicture);
                }
            }

        } else {
            if (!isWifiBord) {
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

                if (null != mContext && !((Activity) mContext).isFinishing()
                        && !((Activity) mContext).isDestroyed()) {
                    Glide.with(mContext)
                            .load(item.getImagesUrl())
                            .into(coverPicture);
                }
            }
        }
        if (!isWifiBord) {
            coverPicture.setLayoutParams(layoutParams);
        }

        if (item.isWifi()) {
            noWifiLl.setVisibility(View.INVISIBLE);
        } else {
            noWifiLl.setVisibility(View.VISIBLE);
        }

//        //全屏按钮是否显示
//        if (item.isFullBtnIsShow()) {
//            fullLin.setVisibility(View.VISIBLE);
//        } else {
//            fullLin.setVisibility(View.GONE);
//        }

        final String localUserId = PersonInfoManager.getInstance().getUserId();
        final String userId = item.getCreateBy();

        //无wifi时继续播放按钮
        continuePlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (helper.getAdapterPosition() == -1) {
                    return;
                }
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

        if (null != mContext && !((Activity) mContext).isFinishing()
                && !((Activity) mContext).isDestroyed()) {
            if (null != mContext && !((Activity) mContext).isFinishing()
                    && !((Activity) mContext).isDestroyed()) {
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
                if (TextUtils.isEmpty(item.getIssuerId()) || TextUtils.equals(localUserId, userId)) {
                    return;
                }

                //跳转H5头像TA人主页
                try {
                    if (AppInit.mIsDebug) {
                        param.recommendUrl(Constants.HEAD_OTHER + item.getCreateBy(), null);
                    } else {
                        param.recommendUrl(Constants.HEAD_OTHER_ZS + item.getCreateBy(), null);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
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

        watched.setText(NumberFormatTool.formatNum(item.getViewCountShow(), false));

        if (TextUtils.isEmpty(item.getBrief())) {
            brief = item.getTitle();
        } else {
            brief = item.getBrief();
        }

        foldTextView.setText(brief);
        foldTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (foldTextView.getVisibility() == View.VISIBLE) {
                    foldTextView.setVisibility(View.GONE);
                    expendText.setVisibility(View.VISIBLE);
                }
            }
        });

        expendText.setText(brief);
        expendText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isClick) {
                    if (expendText.getVisibility() == View.VISIBLE) {
                        expendText.setVisibility(View.GONE);
                        foldTextView.setVisibility(View.VISIBLE);
                    }
                }
                isClick = true;
            }
        });


        /**
         * 推荐服务
         */
        ViewFlipper viewFlipper = helper.getView(R.id.video_flipper);

        if (item.isRecommendVisible() && !item.isClosed()) {
            viewFlipper.setVisibility(View.VISIBLE);
        } else {
            viewFlipper.setVisibility(View.GONE);
        }

        getViewFlipperData(recommendList, viewFlipper, item);

        //点赞
        videoDetailLikes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mLikeListener.likeClick(v, item, videoDetailLikesImage, likesNum);
            }
        });


        //评论
        videoDetailCommentLl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mCommentListener.commentClick(v, item, commentNum);
            }
        });

        //转发
        share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mShareListener.shareClick(v, item);
            }
        });

        isWifiBord = false;
    }

    public void setTopicClick(boolean isClick) {
        this.isClick = isClick;
    }

    public LikeListener mLikeListener;

    public interface LikeListener {
        void likeClick(View view, DataDTO item, ImageView likeImage, TextView likeNum);
    }

    public void setlikeListener(LikeListener likeListener) {
        this.mLikeListener = likeListener;
    }

    public CommentListener mCommentListener;

    public interface CommentListener {
        void commentClick(View view, DataDTO item, TextView commentNum);
    }

    public void setCommentListener(CommentListener commentListener) {
        this.mCommentListener = commentListener;
    }

    public PublishWorksListener mPublishWorksListener;

    public interface PublishWorksListener {
        void publishWorksClick(View view, DataDTO item);
    }

    public void setPublishWorksListener(PublishWorksListener publishWorksListener) {
        this.mPublishWorksListener = publishWorksListener;
    }


    public ShareListener mShareListener;

    public interface ShareListener {
        void shareClick(View view, DataDTO item);
    }

    public void setShareListener(ShareListener shareListener) {
        this.mShareListener = shareListener;
    }

    /**
     * 获取首页滚动消息
     */
    public void getViewFlipperData(final List<RecommendModel.DataDTO.RecordsDTO> list,
                                   final ViewFlipper viewFlipper, final DataDTO mItem) {
        for (int i = 0; i < list.size(); i++) {
            String item = list.get(i).getTitle();
            View view = View.inflate(mContext, R.layout.customer_viewflipper_item, null);
            ImageView viewFlipperIcon = view.findViewById(R.id.view_flipper_icon);
            if (null != mContext && !((Activity) mContext).isFinishing()
                    && !((Activity) mContext).isDestroyed()) {
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


        if (mIsAuto) {
            viewFlipper.startFlipping();
            viewFlipper.setAutoStart(true);
        } else {
            viewFlipper.setAutoStart(false);
        }
    }

    public void setRecommendList(List<RecommendModel.DataDTO.RecordsDTO> mRecommendList,
                                 boolean isAuto) {
        this.recommendList = mRecommendList;
        this.mIsAuto = isAuto;
    }

    public interface ToAddPlayerViewClick {
        void clickNoWifi(int position);
    }

    public void setToAddPlayerViewClick(ToAddPlayerViewClick listener) {
        this.click = listener;
    }

    public interface FollowViewClick {
        void followClick(int position);
    }

    public void setFollowViewClick(FollowViewClick mFollow) {
        this.followViewClick = mFollow;
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

    public void setWifiBord(Boolean isWifiBord) {
        this.isWifiBord = isWifiBord;
    }

    public Boolean getWifiBord() {
        return isWifiBord;
    }
}
