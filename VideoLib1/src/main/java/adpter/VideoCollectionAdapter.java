package adpter;


import static common.callback.VideoInteractiveParam.param;
import static common.constants.Constants.BLUE_V;
import static common.constants.Constants.YELLOW_V;
import static common.utils.SPUtils.isVisibleNoWifiView;
import static ui.activity.VideoHomeActivity.videoIsNormal;

import android.content.Context;
import android.graphics.Color;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.TextUtils;
import android.text.style.ForegroundColorSpan;
import android.text.style.ImageSpan;
import android.util.DisplayMetrics;
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
import common.model.RecommendModel;
import common.model.VideoCollectionModel;
import common.utils.AppInit;
import common.utils.NumberFormatTool;
import common.utils.PersonInfoManager;
import common.utils.ScreenUtils;
import smartrefresh.layout.SmartRefreshLayout;
import tencent.liteav.demo.superplayer.SuperPlayerDef;
import tencent.liteav.demo.superplayer.SuperPlayerView;
import ui.activity.VideoDetailActivity;
import utils.GlideUtil;
import widget.EllipsizeTextView;
import common.model.VideoCollectionModel.DataDTO.RecordsDTO;

public class VideoCollectionAdapter extends BaseQuickAdapter<RecordsDTO, BaseViewHolder> {
    private Context mContext;
    private List<RecordsDTO> mDatas;
    private List<RecommendModel.DataDTO.RecordsDTO> recommendList = new ArrayList<>();
    private boolean mIsAuto;
    private String brief;
    private String spaceStr = "";
    private SuperPlayerView superPlayerView;
    private VideoDetailAdapter.ToAddPlayerViewClick click;
    private SmartRefreshLayout mRefreshlayout;
    private ViewPagerLayoutManager mVideoDetailmanager;
    private String topicNameStr;
    private String mClassName;
    private boolean isClick = true;
    private FollowViewClick followViewClick;

    public VideoCollectionAdapter(int layoutResId, @Nullable List<RecordsDTO> data, Context context,
                                  SuperPlayerView playerView, SmartRefreshLayout refreshLayout, ViewPagerLayoutManager videoDetailmanager,
                                  String className) {
        super(layoutResId, data);
        this.mContext = context;
        this.mDatas = data;
        this.superPlayerView = playerView;
        this.mRefreshlayout = refreshLayout;
        this.mVideoDetailmanager = videoDetailmanager;
        this.mClassName = className;
    }


    @Override
    protected void convert(final BaseViewHolder helper, final RecordsDTO item) {
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
        LinearLayout videoDetailLikes = helper.getView(R.id.video_detail_likes);
        final ImageView videoDetailLikesImage = helper.getView(R.id.video_detail_likes_image);
        final TextView likesNum = helper.getView(R.id.likes_num);
        LinearLayout videoDetailCommentLl = helper.getView(R.id.video_detail_comment_ll);
        final TextView commentNum = helper.getView(R.id.comment_num);
        LinearLayout share = helper.getView(R.id.share);

        RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) coverPicture.getLayoutParams();
        DisplayMetrics outMetrics = new DisplayMetrics();
        ((VideoDetailActivity) mContext).getWindowManager().getDefaultDisplay().getRealMetrics(outMetrics);
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
            if (null != mContext && !((VideoDetailActivity) mContext).isFinishing()
                    && !((VideoDetailActivity) mContext).isDestroyed()) {
                Glide.with(mContext)
                        .load(item.getImagesUrl())
                        .into(coverPicture);
            }
        } else if (TextUtils.equals("1", videoIsNormal(Integer.parseInt(NumberFormatTool.getNumStr(item.getWidth())),
                Integer.parseInt(NumberFormatTool.getNumStr(item.getHeight()))))) {
            //竖版视频
            verticalVideoWdcsLogo.setVisibility(View.VISIBLE);
            horizontalVideoWdcsLogo.setVisibility(View.GONE);

//            if (phoneIsNormal()) {
            layoutParams.removeRule(RelativeLayout.ALIGN_PARENT_TOP);
            layoutParams.addRule(RelativeLayout.CENTER_IN_PARENT);
//            } else {
//                layoutParams.removeRule(RelativeLayout.ALIGN_PARENT_TOP);
//                layoutParams.addRule(RelativeLayout.CENTER_IN_PARENT);
//            }
            layoutParams.width = (int) widthPixel - 1;
            layoutParams.height = (int) (widthPixel / Constants.Portrait_Proportion);
            if (null != mContext && !((VideoDetailActivity) mContext).isFinishing()
                    && !((VideoDetailActivity) mContext).isDestroyed()) {
                Glide.with(mContext)
                        .load(item.getImagesUrl())
                        .into(coverPicture);
            }
        } else {
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

            if (null != mContext && !((VideoDetailActivity) mContext).isFinishing()
                    && !((VideoDetailActivity) mContext).isDestroyed()) {
                Glide.with(mContext)
                        .load(item.getImagesUrl())
                        .into(coverPicture);
            }
        }

        //非wifi状态下布局是否显示
        if (item.isWifi()) {
            noWifiLl.setVisibility(View.INVISIBLE);
        } else {
            noWifiLl.setVisibility(View.VISIBLE);
        }


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

        final String localUserId = PersonInfoManager.getInstance().getUserId();
        final String userId = item.getCreateBy();



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
        if (null != mContext && !((VideoDetailActivity) mContext).isFinishing()
                && !((VideoDetailActivity) mContext).isDestroyed()) {
            if (null != mContext && !((VideoDetailActivity) mContext).isFinishing()
                    && !((VideoDetailActivity) mContext).isDestroyed()) {
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
        //观看人数
        if (null == item.getViewCountShow()) {
            watched.setText(NumberFormatTool.formatNum(0, false));
        } else {
            watched.setText(NumberFormatTool.formatNum(item.getViewCountShow(), false));
        }



        if (TextUtils.isEmpty(item.getBelongTopicName()) || null == item.getBelongTopicName()) {
            topicNameStr = "";
        } else {
            topicNameStr = "#" + item.getBelongTopicName();
        }

        if (TextUtils.isEmpty(item.getBrief())) {
            brief = item.getTitle();
        } else {
            brief = item.getBrief();
        }

        if (TextUtils.isEmpty(mClassName)) {
            foldTextView.setText(brief);
            expendText.setText(brief);
        } else {
            SpannableString sp = new SpannableString(mClassName);
            ImageSpan imgSpan = new ImageSpan(mContext,
                    R.drawable.szrm_sdk_collection_image,
                    ImageSpan.ALIGN_BASELINE);
            sp.setSpan(imgSpan, 0, 1, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
            sp.setSpan(new ForegroundColorSpan(Color.WHITE), 0, mClassName.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
            SpannableStringBuilder builder = new SpannableStringBuilder();
            builder.append(sp);
            builder.append(" " + brief);
            foldTextView.setText(builder);
            expendText.setText(builder);
        }


//        foldTextView.setText(brief);
//        if (foldTextView.getLineCount() > 2 && foldTextView.getVisibility() == View.VISIBLE) {
//            ellipsisTv.setVisibility(View.VISIBLE);
//        } else {
//            ellipsisTv.setVisibility(View.GONE);
//        }
        foldTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // 展开简介
                if (foldTextView.getVisibility() == View.VISIBLE) {
                    foldTextView.setVisibility(View.GONE);
                    expendText.setVisibility(View.VISIBLE);
                }
            }
        });


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


    }

    public interface FollowViewClick {
        void followClick(int position);
    }

    public void setFollowViewClick(FollowViewClick mFollow) {
        this.followViewClick = mFollow;
    }

    public LikeListener mLikeListener;

    public interface LikeListener {
        void likeClick(View view, RecordsDTO item, ImageView likeImage, TextView likeNum);
    }

    public void setlikeListener(LikeListener likeListener) {
        this.mLikeListener = likeListener;
    }

    public CollectionListener mCollectionListener;

    public interface CollectionListener {
        void collectionClick(View view, RecordsDTO item, ImageView collectionImage, TextView collectionNum);
    }

    public void setCollectionListener(CollectionListener collectionListener) {
        this.mCollectionListener = collectionListener;
    }

    public CommentListener mCommentListener;

    public interface CommentListener {
        void commentClick(View view, RecordsDTO item, TextView commentNum);
    }

    public void setCommentListener(CommentListener commentListener) {
        this.mCommentListener = commentListener;
    }

    public PublishWorksListener mPublishWorksListener;

    public interface PublishWorksListener {
        void publishWorksClick(View view, RecordsDTO item);
    }

    public void setPublishWorksListener(PublishWorksListener publishWorksListener) {
        this.mPublishWorksListener = publishWorksListener;
    }


    public ShareListener mShareListener;

    public interface ShareListener {
        void shareClick(View view, RecordsDTO item);
    }

    public void setShareListener(ShareListener shareListener) {
        this.mShareListener = shareListener;
    }

    /**
     * 获取首页滚动消息
     */
    public void getViewFlipperData(final List<RecommendModel.DataDTO.RecordsDTO> list,
                                   final ViewFlipper viewFlipper, final RecordsDTO mItem) {
        if (null == mContext) {
            return;
        }
        for (int i = 0; i < list.size(); i++) {
            String item = list.get(i).getTitle();
            View view = View.inflate(mContext, R.layout.customer_viewflipper_item, null);
            ImageView viewFlipperIcon = view.findViewById(R.id.view_flipper_icon);
            if (null != mContext && !((VideoDetailActivity) mContext).isFinishing()
                    && !((VideoDetailActivity) mContext).isDestroyed()) {
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

    public void setTopicClick(boolean isClick) {
        this.isClick = isClick;
    }

    public interface ToAddPlayerViewClick {
        void clickNoWifi(int position);
    }

    public void setToAddPlayerViewClick(VideoDetailAdapter.ToAddPlayerViewClick listener) {
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
