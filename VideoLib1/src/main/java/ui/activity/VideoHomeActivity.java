package ui.activity;

import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.SpannableStringBuilder;
import android.text.TextUtils;
import android.text.method.LinkMovementMethod;
import android.text.style.ImageSpan;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;

//import com.chad.library.adapter.base.BaseQuickAdapter;
//import com.chad.library.adapter.base.entity.MultiItemEntity;
//import com.example.zhouwei.library.CustomPopWindow;
import com.pasc.lib.videolib.R;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.cache.CacheMode;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;
//import com.scwang.smart.refresh.layout.SmartRefreshLayout;
//import com.scwang.smart.refresh.layout.api.RefreshLayout;
//import com.scwang.smart.refresh.layout.listener.OnRefreshListener;

import com.tencent.rtmp.TXLiveConstants;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import adpter.CommentPopRvAdapter;
import adpter.XkshVideoAdapter;
import brvah.BaseQuickAdapter;
import brvah.entity.MultiItemEntity;
import common.callback.JsonCallback;
import common.callback.SdkInteractiveParam;
import common.callback.VideoInteractiveParam;
import common.constants.Constants;
import common.http.ApiConstants;
import common.manager.BuriedPointModelManager;
import common.manager.ContentBuriedPointManager;
import common.manager.OnViewPagerListener;
import common.manager.ViewPagerLayoutManager;
import common.model.CategoryModel;
import common.model.CollectionLabelModel;
import common.model.CommentLv1Model;
import common.model.ContentStateModel;
import common.model.DataDTO;
import common.model.RecommendModel;
import common.model.ReplyLv2Model;
import common.model.TrackingUploadModel;
import common.model.VideoChannelModel;
import common.model.VideoDetailModel;
import common.model.VideoOneModel;
import common.utils.ButtonSpan;
import common.utils.DateUtils;
import common.utils.KeyboardUtils;
import common.utils.NumberFormatTool;
import common.utils.PersonInfoManager;
import common.utils.SPUtils;
import common.utils.ScreenUtils;
import common.utils.SoftKeyBoardListener;
import common.utils.ToastUtils;
import common.utils.AppInit;
import custompop.CustomPopWindow;
import event.SzrmRecommend;
import flyco.tablayout.SlidingTabLayout;
import model.bean.ActivityRuleBean;
import smartrefresh.layout.SmartRefreshLayout;
import smartrefresh.layout.api.RefreshLayout;
import smartrefresh.layout.listener.OnRefreshListener;
import tencent.liteav.demo.superplayer.SuperPlayerDef;
import tencent.liteav.demo.superplayer.SuperPlayerModel;
import tencent.liteav.demo.superplayer.SuperPlayerView;
import tencent.liteav.demo.superplayer.contants.Contants;
import tencent.liteav.demo.superplayer.model.SuperPlayerImpl;
import tencent.liteav.demo.superplayer.model.utils.SystemUtils;
import tencent.liteav.demo.superplayer.ui.player.WindowPlayer;
import tencent.liteav.demo.superplayer.ui.view.PointSeekBar;
import utils.NetworkUtil;
import utils.VideoScaleUtils;
import widget.LoadingView;
import widget.NetBroadcastReceiver;
import widget.YALikeAnimationView;


import org.json.JSONException;
import org.json.JSONObject;

import static common.callback.VideoInteractiveParam.param;
import static common.constants.Constants.CLICK_INTERVAL_TIME;
import static common.constants.Constants.TRACKINGUPLOAD;
import static common.constants.Constants.VIDEOTAG;
import static common.constants.Constants.success_code;
import static common.constants.Constants.token_error;
import static common.utils.AppInit.appId;
import static common.utils.SPUtils.isVisibleNoWifiView;
import static common.utils.ShareUtils.toShare;
import static tencent.liteav.demo.superplayer.SuperPlayerView.instance;
import static tencent.liteav.demo.superplayer.SuperPlayerView.mTargetPlayerMode;
import static tencent.liteav.demo.superplayer.ui.player.AbsPlayer.formattedTime;
import static tencent.liteav.demo.superplayer.ui.player.WindowPlayer.mDuration;
import static tencent.liteav.demo.superplayer.ui.player.WindowPlayer.mProgress;
import static ui.activity.WebActivity.szrmLoginRequest;
import static utils.NetworkUtil.setDataWifiState;
import android.support.v7.widget.RecyclerView;


public class VideoHomeActivity extends AppCompatActivity implements View.OnClickListener {
    private ImageView videoBack;
    private SuperPlayerView playerView;

    private TranslateAnimation translateAniLeftShow, translateAniLeftHide;
    public CustomPopWindow noLoginTipsPop;
    private View noLoginTipsView;
    private TextView noLoginTipsCancel;
    private TextView noLoginTipsOk;
    public String contentId;
    public static double maxPercent = 0; //记录最大百分比
//    public static long lsDuration = 0; //每一次上报临时保存的播放时长
    private NetBroadcastReceiver netWorkStateReceiver;
    private String categoryName;
    public static boolean isPause;
    private List<CategoryModel.DataDTO> categoryModelList = new ArrayList<>();
    private RecyclerView videoDetailRv;
    public XkshVideoAdapter adapter;
    private CommentPopRvAdapter commentPopRvAdapter;
    //视频列表数据
    public List<DataDTO> mDatas = new ArrayList<>();
    //评论列表数据
    private List<MultiItemEntity> mCommentPopRvData;
    private List<CommentLv1Model.DataDTO.RecordsDTO> mCommentPopDtoData;
    private ImageView videoStaticBg;
    private ImageView startPlay;

    //    public RelativeLayout videoDetailCommentBtn;
    //评论列表弹窗
    public CustomPopWindow popupWindow;

    private View contentView;
    private View chooseContentView;
    private RelativeLayout dismissPop;
    private RecyclerView commentPopRv;
    private TextView commentEdtInput;
    private RelativeLayout commentPop;
    //附着在软键盘上的输入弹出窗
    public CustomPopWindow inputAndSendPop;
    private View sendPopContentView;
    private View rootView;
    private LinearLayout edtParent;
    private EditText edtInput;
    private TextView tvSend;
    //选择集数弹窗
    public CustomPopWindow choosePop;

    public ViewPagerLayoutManager xkshManager;
    private ImageView choosePopDismiss;
    public SmartRefreshLayout refreshLayout;
    private String transformationToken = "";
    private String panelCode = "";
    private String recordContentId;//记录的内容id
    private boolean initialize = true;
    private int mVideoSize = 15; //每页视频多少条
    private int mPageIndex = 1; //评论列表页数
    private int mPageSize = 10; //评论列表每页多少条
    public String myContentId = ""; //记录当前视频id
    public int currentIndex = 0; //记录当前视频列表的位置
    private TextView commentPopCommentTotal;

    private String videoType; //视频类型
    public String playUrl;
    private String videoTag = "videoTag";
    private String recommendTag = "recommend";
    private boolean isLoadComplate = false;
    //    private BaseQuickAdapter.RequestLoadMoreListener requestLoadMoreListener;
    public View decorView;
    private SoftKeyBoardListener softKeyBoardListener;
    public CustomPopWindow sharePop;
    private View sharePopView;
    private ImageView shareWxBtn;
    private ImageView shareCircleBtn;
    private ImageView shareQqBtn;
    public DataDTO mDataDTO;
    private List<RecommendModel.DataDTO.RecordsDTO> recommondList;
    public ViewGroup rlLp;
    private VideoChannelModel videoChannelModel;

    private VideoChannelModel channelModel;
    private Bundle args;
    private SlidingTabLayout mVideoTab;
    private LoadingView loadingProgress;
    private RelativeLayout.LayoutParams lp;
    public LinearLayout fullLin;
    public double pointPercent;// 每一次记录的节点播放百分比
    //    private long everyOneDuration; //每一次记录需要上报的播放时长 用来分段上报埋点
    public ActivityRuleBean activityRuleBean;
    private RelativeLayout.LayoutParams playViewParams;
    private View footView;
    public long xkshOldSystemTime;
    public long xkshReportTime;
    private boolean isReply = false;
    private String replyId;
    private List<CollectionLabelModel.DataDTO.ListDTO> collectionList;
    private List<String> collectionTvList;
    private List<String> collectionStrList;
    private View footerView;
    private String logoUrl;
    private String appName;
    public String videoLx; //当前视频的类型
    private boolean likeIsRequesting;
    private Handler handler;
    private YALikeAnimationView loveIcon;
    private long beforeClickTime = 0;
    private View commentEmptyView;
    private TextView commentListTips;
    private String topicName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);
        SystemUtils.setNavbarColor(this, R.color.video_black);
        setContentView(R.layout.activity_video_main);
        initView();
        getOneVideo();
    }

    private void initView() {
        param = VideoInteractiveParam.getInstance();
        contentId = getIntent().getStringExtra("contentId");
        categoryName = getIntent().getStringExtra("category_name");
        logoUrl = getIntent().getStringExtra("logoUrl");
        appName = getIntent().getStringExtra("appName");
        videoBack = findViewById(R.id.video_back);
        videoBack.setOnClickListener(this);
        noLoginTipsView = View.inflate(this, R.layout.no_login_tips, null);
        noLoginTipsCancel = noLoginTipsView.findViewById(R.id.no_login_tips_cancel);
        noLoginTipsOk = noLoginTipsView.findViewById(R.id.no_login_tips_ok);
        refreshLayout = findViewById(R.id.refreshLayout);
        loveIcon = findViewById(R.id.love_icon);
        noLoginTipsCancel.setOnClickListener(this);
        noLoginTipsOk.setOnClickListener(this);
        playerView = SuperPlayerView.getInstance(this, getWindow().getDecorView(), true);
        footerView = View.inflate(this, R.layout.footer_view, null);
        commentEmptyView = View.inflate(this, R.layout.comment_list_empty, null);
        commentListTips = commentEmptyView.findViewById(R.id.comment_list_tips);

        if (NetworkUtil.isWifi(this)) {
            SPUtils.getInstance().put("net_state", "0");
        } else {
            SPUtils.getInstance().put("net_state", "1");
        }

        if (netWorkStateReceiver == null) {
            netWorkStateReceiver = new NetBroadcastReceiver();
        }
        IntentFilter filter = new IntentFilter();
        filter.addAction(ConnectivityManager.CONNECTIVITY_ACTION);
        registerReceiver(netWorkStateReceiver, filter);

        //全屏进度条监听
        if (null != playerView && null != playerView.mFullScreenPlayer) {
            playerView.mFullScreenPlayer.mSeekBarProgress.setOnSeekBarChangeListener(new PointSeekBar.OnSeekBarChangeListener() {
                @Override
                public void onProgressChanged(PointSeekBar seekBar, int progress, boolean fromUser) {

                    if (playerView.mFullScreenPlayer.mGestureVideoProgressLayout != null && fromUser) {
                        playerView.mFullScreenPlayer.mGestureVideoProgressLayout.show();
                        float percentage = ((float) progress) / seekBar.getMax();
                        float currentTime = (mDuration * percentage);
                        playerView.mFullScreenPlayer.mGestureVideoProgressLayout.setTimeText(formattedTime((long) currentTime) + " / " + formattedTime((long) mDuration));
                        playerView.mFullScreenPlayer.mGestureVideoProgressLayout.setProgress(progress);
                    }
                }

                @Override
                public void onStartTrackingTouch(PointSeekBar seekBar) {
                    if (null == playerView) {
                        return;
                    }
                    if (null == playerView.mFullScreenPlayer) {
                        return;
                    }
                    playerView.mFullScreenPlayer.removeCallbacks(playerView.mFullScreenPlayer.mHideViewRunnable);
                }

                @Override
                public void onStopTrackingTouch(PointSeekBar seekBar) {
                    int curProgress = seekBar.getProgress();
                    int maxProgress = seekBar.getMax();

                    switch (playerView.mFullScreenPlayer.mPlayType) {
                        case VOD:
                            if (curProgress >= 0 && curProgress <= maxProgress) {
                                // 关闭重播按钮
                                playerView.mFullScreenPlayer.toggleView(playerView.mFullScreenPlayer.mLayoutReplay, false);
                                float percentage = ((float) curProgress) / maxProgress;
                                long duration = (long) (percentage * mDuration);
                                if (percentage > maxPercent) {
                                    maxPercent = percentage;
                                }

                                int position = (int) (mDuration * percentage);

                                if (playerView.mFullScreenPlayer.mControllerCallback != null) {
                                    playerView.mFullScreenPlayer.mControllerCallback.onSeekTo(position);
                                    playerView.mFullScreenPlayer.mControllerCallback.onResume();
                                }
                            }
                            break;
                    }
                    playerView.mFullScreenPlayer.postDelayed(playerView.mFullScreenPlayer.mHideViewRunnable, Contants.delayMillis);
                }
            });
        }

        if (null != playerView && null != playerView.mWindowPlayer) {
            //窗口进度条
            playerView.mWindowPlayer.mSeekBarProgress.setOnSeekBarChangeListener(new PointSeekBar.OnSeekBarChangeListener() {
                @Override
                public void onProgressChanged(PointSeekBar seekBar, int progress, boolean fromUser) {
                    if (null == playerView) {
                        return;
                    }
                    if (null == playerView.mWindowPlayer) {
                        return;
                    }
                    if (playerView.mWindowPlayer.mGestureVideoProgressLayout != null && fromUser) {
                        playerView.mWindowPlayer.mGestureVideoProgressLayout.show();
                        float percentage = ((float) progress) / seekBar.getMax();
                        float currentTime = (mDuration * percentage);
                        playerView.mWindowPlayer.mGestureVideoProgressLayout.setTimeText(formattedTime((long) currentTime) + " / " + formattedTime((long) mDuration));
                        playerView.mWindowPlayer.mGestureVideoProgressLayout.setProgress(progress);
                    }
                }

                @Override
                public void onStartTrackingTouch(PointSeekBar seekBar) {
                    if (null == playerView) {
                        return;
                    }
                    if (null == playerView.mWindowPlayer) {
                        return;
                    }
                    playerView.mWindowPlayer.removeCallbacks(playerView.mWindowPlayer.mHideViewRunnable);
                    xkshManager.setCanScoll(false);
                }

                @Override
                public void onStopTrackingTouch(PointSeekBar seekBar) {
                    int curProgress = seekBar.getProgress();
                    int maxProgress = seekBar.getMax();
                    if (mTargetPlayerMode == SuperPlayerDef.PlayerMode.WINDOW) {
                        xkshManager.setCanScoll(true);
                    } else {
                        xkshManager.setCanScoll(false);
                    }

                    switch (playerView.mWindowPlayer.mPlayType) {
                        case VOD:
                            if (curProgress >= 0 && curProgress <= maxProgress) {
                                // 关闭重播按钮
                                playerView.mWindowPlayer.toggleView(playerView.mWindowPlayer.mLayoutReplay, false);
                                float percentage = ((float) curProgress) / maxProgress;
                                long duration = (long) (percentage * mDuration);
                                if (percentage > maxPercent) {
                                    maxPercent = percentage;
                                }
                                int position = (int) (mDuration * percentage);

                                if (playerView.mWindowPlayer.mControllerCallback != null) {
                                    playerView.mWindowPlayer.mControllerCallback.onSeekTo(position);
                                    playerView.mWindowPlayer.mControllerCallback.onResume();
                                }
                            }
                            break;
                    }
                    playerView.mWindowPlayer.postDelayed(playerView.mWindowPlayer.mHideViewRunnable, Contants.delayMillis);
                }
            });
        }

        /**
         * 监听播放器播放窗口变化回调
         */
        playerView.playModeCallBack = new SuperPlayerView.PlayModeCallBack() {
            @Override
            public void getPlayMode(SuperPlayerDef.PlayerMode playerMode) {
                LinearLayout fullLin = (LinearLayout) adapter.getViewByPosition(currentIndex, R.id.superplayer_iv_fullscreen);
                if (playerMode.equals(SuperPlayerDef.PlayerMode.FULLSCREEN)) {
                    xkshManager.setCanScoll(false);
                    refreshLayout.setEnableRefresh(false);
//                    adapter.setEnableLoadMore(false);

                    videoBack.setVisibility(View.GONE);
                    if (null != popupWindow) {
                        popupWindow.dissmiss();
                    }


                    if (null != inputAndSendPop) {
                        inputAndSendPop.dissmiss();
                    }

                    if (null != choosePop) {
                        choosePop.dissmiss();
                    }

                    if (null != noLoginTipsPop) {
                        noLoginTipsPop.dissmiss();
                    }

                    if (null != sharePop) {
                        sharePop.dissmiss();
                    }

                    if (null != adapter.getViewByPosition(currentIndex, R.id.introduce_lin)) {
                        adapter.getViewByPosition(currentIndex, R.id.introduce_lin).setVisibility(View.GONE);
                    }

                    if (null != adapter.getViewByPosition(currentIndex, R.id.user_todo)) {
                        adapter.getViewByPosition(currentIndex, R.id.user_todo).setVisibility(View.GONE);
                    }

                    if (null != fullLin) {
                        fullLin.setVisibility(View.GONE);
                    }


                    if (null != adapter.getViewByPosition(currentIndex, R.id.horizontal_video_wdcs_logo)) {
                        adapter.getViewByPosition(currentIndex, R.id.horizontal_video_wdcs_logo).setVisibility(View.GONE);
                    }

                    if (null != adapter.getViewByPosition(currentIndex, R.id.cover_picture)) {
                        adapter.getViewByPosition(currentIndex, R.id.cover_picture).setVisibility(View.GONE);
                    }

                    KeyboardUtils.hideKeyboard(getWindow().getDecorView());
                } else if (playerMode.equals(SuperPlayerDef.PlayerMode.WINDOW)) {
                    xkshManager.setCanScoll(true);
                    refreshLayout.setEnableRefresh(true);
                    //先注释，不加载更多
//                    adapter.setEnableLoadMore(true);
                    videoBack.setVisibility(View.VISIBLE);

                    if (null != fullLin) {
                        fullLin.setVisibility(View.VISIBLE);
                    }

                    if (null != adapter.getViewByPosition(currentIndex, R.id.introduce_lin)) {
                        adapter.getViewByPosition(currentIndex, R.id.introduce_lin).setVisibility(View.VISIBLE);
                    }

                    if (null != adapter.getViewByPosition(currentIndex, R.id.user_todo)) {
                        adapter.getViewByPosition(currentIndex, R.id.user_todo).setVisibility(View.VISIBLE);
                    }

                    if (null != adapter.getViewByPosition(currentIndex, R.id.horizontal_video_wdcs_logo)) {
                        adapter.getViewByPosition(currentIndex, R.id.horizontal_video_wdcs_logo).setVisibility(View.VISIBLE);
                    }

                    if (null != adapter.getViewByPosition(currentIndex, R.id.cover_picture)) {
                        adapter.getViewByPosition(currentIndex, R.id.cover_picture).setVisibility(View.VISIBLE);
                    }

                }
            }
        };

        //开始播放回调
        SuperPlayerImpl.setReadPlayCallBack(new SuperPlayerImpl.ReadPlayCallBack() {
            @Override
            public void ReadPlayCallback() {
                if (null == playerView.buriedPointModel.getXksh_renew() || TextUtils.equals("false", playerView.buriedPointModel.getXksh_renew())) {
//                    //不为重播
                    xkshOldSystemTime = DateUtils.getTimeCurrent();
                    String event;
                    if (TextUtils.equals(mDataDTO.getIsAutoReportEvent(), "1")) {
                        event = Constants.CMS_VIDEO_PLAY;
                    } else {
                        event = Constants.CMS_VIDEO_PLAY_AUTO;
                    }
                    String contentId = "";
                    if (TextUtils.isEmpty(mDataDTO.getThirdPartyId())) {
                        contentId = String.valueOf(mDataDTO.getId());
                    } else {
                        contentId = mDataDTO.getThirdPartyId();
                    }
                    uploadBuriedPoint(ContentBuriedPointManager.setContentBuriedPoint(VideoHomeActivity.this, contentId, "", "", event, mDataDTO.getVolcCategory(), mDataDTO.getRequestId(), appName), event);
                }

            }
        });

        //自动播放/拖动进度条 播放结束回调
        SuperPlayerImpl.setAutoPlayOverCallBack(new SuperPlayerImpl.AutoPlayOverCallBack() {
            @Override
            public void AutoPlayOverCallBack() {
                if (!isPause) {
                    Log.e("yqh_yqh", "重播地址：" + SuperPlayerImpl.mCurrentPlayVideoURL);
                    playerView.mSuperPlayer.reStart();
                }
            }
        });
        translateAnimation();

        mDataDTO = new DataDTO();
        recommondList = new ArrayList<>();
        loadingProgress = findViewById(R.id.xksh_loading_progress);
        loadingProgress.setVisibility(View.VISIBLE);
        videoDetailRv = findViewById(R.id.video_detail_rv);
        videoDetailRv.setHasFixedSize(true);

        xkshManager = new ViewPagerLayoutManager(this);
        videoDetailRv.setLayoutManager(xkshManager);
        footView = View.inflate(this, R.layout.footer_view, null);
        setSoftKeyBoardListener();
        xkshManager.setOnViewPagerListener(new OnViewPagerListener() {


            @Override
            public void onInitComplete() {
                try {
                    if (initialize) {
                        return;
                    }
                    initialize = true;

                    if (mDatas.isEmpty()) {
                        return;
                    }
                    mDataDTO = mDatas.get(0);

                    if (null != adapter.getViewByPosition(0, R.id.superplayer_iv_fullscreen)) {
                        if (TextUtils.equals("2", videoIsNormal(Integer.parseInt(NumberFormatTool.getNumStr(mDatas.get(0).getWidth())),
                                Integer.parseInt(NumberFormatTool.getNumStr(mDatas.get(0).getHeight()))))) {
                            adapter.getViewByPosition(0, R.id.superplayer_iv_fullscreen).setVisibility(View.VISIBLE);
                        } else {
                            adapter.getViewByPosition(0, R.id.superplayer_iv_fullscreen).setVisibility(View.GONE);
                        }
                    }

//                playerView = SuperPlayerView.getInstance(getActivity(), decorView);
                    playerView.mWindowPlayer.setDataDTO(mDataDTO, mDataDTO);
                    playerView.mWindowPlayer.setIsTurnPages(false);
                    playerView.mWindowPlayer.setManager(xkshManager);
                    playerView.mFullScreenPlayer.setDataDTO(mDataDTO);
                    myContentId = String.valueOf(mDatas.get(0).getId());
                    addPageViews(myContentId);
                    OkGo.getInstance().cancelTag("contentState");
                    getContentState(myContentId);
                    playerView.mCurrentPlayVideoURL = mDatas.get(0).getPlayUrl();

                    if (isVisibleNoWifiView(VideoHomeActivity.this)) {
                        playerView.setOrientation(false);
                    } else {
                        playerView.setOrientation(true);
                    }
                    currentIndex = 0;
                    mPageIndex = 1;
                    if (mDatas.get(0).getDisableComment()) {
                        commentListTips.setText("当前页面评论功能已关闭");
                        commentPop.setEnabled(false);
                        commentEdtInput.setHint("当前页面评论功能已关闭");
                    } else {
                        commentListTips.setText("暂无任何评论，快来抢沙发吧！");
                        commentPop.setEnabled(true);
                        commentEdtInput.setHint("写评论...");
                    }
                    commentPopRvAdapter.setEmptyView(commentEmptyView);
                    getCommentList("1", String.valueOf(mPageSize), true);
                    videoType = mDatas.get(0).getType();
                    rlLp = (ViewGroup) xkshManager.findViewByPosition(0);
//                    OkGo.getInstance().cancelTag(recommendTag);
                    //获取推荐列表
                    getRecommend(myContentId, 0);
//                initChoosePop();
                } catch (NumberFormatException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onPageRelease(boolean isNext, int position) {
            }

            @Override
            public void onPageSelected(final int position, boolean isBottom) {
                try {
                    if (null == playerView) {
                        return;
                    }

//                    if (null != playerView.getTag() && position == (Integer) playerView.getTag()) {
//                        return;
//                    }
                    //避免越界addOrCancelLike
                    if (mDatas.isEmpty()) {
                        return;
                    }
                    if (null == mDatas.get(position)) {
                        return;
                    }

                    if (mDatas.size() <= position) {
                        return;
                    }

                    playerView.mWindowPlayer.hide();

                    finderPoint();

                    mDataDTO = mDatas.get(position);

                    if (isVisibleNoWifiView(VideoHomeActivity.this)) {
                        playerView.setOrientation(false);
                    } else {
                        playerView.setOrientation(true);
                        setWifiVisible(true);
                    }

                    if (null != adapter.getViewByPosition(position, R.id.superplayer_iv_fullscreen)) {
                        if (TextUtils.equals("2", videoIsNormal(Integer.parseInt(NumberFormatTool.getNumStr(mDatas.get(position).getWidth())),
                                Integer.parseInt(NumberFormatTool.getNumStr(mDatas.get(position).getHeight()))))) {
                            adapter.getViewByPosition(position, R.id.superplayer_iv_fullscreen).setVisibility(View.VISIBLE);
                        } else {
                            adapter.getViewByPosition(position, R.id.superplayer_iv_fullscreen).setVisibility(View.GONE);
                        }
                    }


                    //重置重播标识
                    if (null != playerView && null != playerView.buriedPointModel) {
                        playerView.buriedPointModel.setXksh_renew("false");
                    }
                    //滑动下一条或者上一条视频
                    playerView.mWindowPlayer.setRecordDuration(0);
                    maxPercent = 0;
                    playerView.mCurrentPlayVideoURL = mDatas.get(position).getPlayUrl();
                    playUrl = mDatas.get(position).getPlayUrl();
                    playerView.mWindowPlayer.setDataDTO(mDataDTO, mDatas.get(currentIndex));
                    playerView.mFullScreenPlayer.setDataDTO(mDataDTO);
                    playerView.mWindowPlayer.setIsTurnPages(true);
                    playerView.mFullScreenPlayer.setIsTurnPages(true);
                    currentIndex = position;
                    reset();
                    myContentId = String.valueOf(mDatas.get(position).getId());

                    addPageViews(myContentId);
                    videoType = mDatas.get(position).getType();
                    mPageIndex = 1;
                    if (mDatas.get(position).getDisableComment()) {
                        commentListTips.setText("当前页面评论功能已关闭");
                        commentPop.setEnabled(false);
                        commentEdtInput.setHint("当前页面评论功能已关闭");
                    } else {
                        commentListTips.setText("暂无任何评论，快来抢沙发吧！");
                        commentPop.setEnabled(true);
                        commentEdtInput.setHint("写评论...");
                    }
                    commentPopRvAdapter.setEmptyView(commentEmptyView);
                    getCommentList("1", String.valueOf(mPageSize), true);
                    getContentState(myContentId);
                    String localUserId = PersonInfoManager.getInstance().getUserId();
                    String userId = mDataDTO.getCreateBy();

                    rlLp = (ViewGroup) xkshManager.findViewByPosition(position);
//                    OkGo.getInstance().cancelTag(recommendTag);
                    getRecommend(myContentId, position);

                    if (!"1".equals(playerView.mFullScreenPlayer.strSpeed)) {
                        playerView.mFullScreenPlayer.mVodMoreView.mCallback.onSpeedChange(1.0f);
                        playerView.mFullScreenPlayer.superplayerSpeed.setText("倍速");
                        playerView.mFullScreenPlayer.mRbSpeed1.setChecked(true);
                    }
                } catch (NumberFormatException e) {
                    e.printStackTrace();
                }
            }
        });

        initSmartRefresh();

        contentView = View.inflate(this, R.layout.fragment_video_comment_pop, null);
        sendPopContentView = View.inflate(this, R.layout.layout_input_window, null);
        commentPopCommentTotal = contentView.findViewById(R.id.comment_pop_comment_total);
        edtParent = sendPopContentView.findViewById(R.id.edt_parent);
        edtInput = sendPopContentView.findViewById(R.id.edtInput);
        tvSend = sendPopContentView.findViewById(R.id.tvSend);

        noLoginTipsView = View.inflate(this, R.layout.no_login_tips, null);
        noLoginTipsCancel = noLoginTipsView.findViewById(R.id.no_login_tips_cancel);
        noLoginTipsOk = noLoginTipsView.findViewById(R.id.no_login_tips_ok);
        noLoginTipsCancel.setOnClickListener(this);
        noLoginTipsOk.setOnClickListener(this);

        sharePopView = View.inflate(this, R.layout.share_pop_view, null);
        shareWxBtn = sharePopView.findViewById(R.id.share_wx_btn);
        shareWxBtn.setOnClickListener(this);
        shareCircleBtn = sharePopView.findViewById(R.id.share_circle_btn);
        shareCircleBtn.setOnClickListener(this);
        shareQqBtn = sharePopView.findViewById(R.id.share_qq_btn);
        shareQqBtn.setOnClickListener(this);

        /**
         * 发送评论
         */
        tvSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (TextUtils.isEmpty(edtInput.getText())) {
                    ToastUtils.showShort("请输入评论");
                } else {
                    if (isReply) {
                        toReply(replyId);
                    } else {
                        toComment(edtInput.getText().toString(), myContentId);
                    }
                    edtInput.setText("");
                }
            }
        });

        rootView = findViewById(R.id.root);

        dismissPop = contentView.findViewById(R.id.dismiss_pop);
        dismissPop.setOnClickListener(this);
        commentPopRv = contentView.findViewById(R.id.comment_pop_rv);
        commentEdtInput = contentView.findViewById(R.id.comment_edtInput);
        commentPop = contentView.findViewById(R.id.comment_pop_rl);
        commentPop.setOnClickListener(this);
        initCommentPopRv();
        adapter = new XkshVideoAdapter(R.layout.xksh_video_item_layout, mDatas, this,
                playerView, refreshLayout, xkshManager, logoUrl);
        adapter.bindToRecyclerView(videoDetailRv);
//        adapter.setLoadMoreView(new CustomLoadMoreView());
//        adapter.setPreLoadNumber(2);
//        adapter.openLoadAnimation();
//        adapter.setOnLoadMoreListener(requestLoadMoreListener, videoDetailRv);

        handler = new Handler() {
            @Override
            public void dispatchMessage(@NonNull Message msg) {
                switch (msg.what) {
                    case 1:
                        //单击
                        playerView.mWindowPlayer.togglePlayState();
                        break;
                    case 2:
                        //双击
                        if (TextUtils.isEmpty(PersonInfoManager.getInstance().getTransformationToken())) {
                            noLoginTipsPop();
                        } else {
                            loveIcon.startAnimation();
                            if (TextUtils.equals("false", playerView.contentStateModel.getWhetherLike()) && !likeIsRequesting) {
                                likeIsRequesting = true;
                                ImageView likeImage = (ImageView) adapter.getViewByPosition(currentIndex, R.id.video_detail_likes_image);
                                TextView likeNum = (TextView) adapter.getViewByPosition(currentIndex, R.id.likes_num);
                                addOrCancelLike(myContentId, videoType, likeImage, likeNum);
                            }
                        }
                        break;
                    case 3:
                        ImageView coverPicture = (ImageView) adapter.getViewByPosition(currentIndex, R.id.cover_picture);
                        if (null != coverPicture) {
                            coverPicture.setVisibility(View.VISIBLE);
                        }
                        break;
                }
            }
        };
        adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(final BaseQuickAdapter adapter, View view, int position) {
                if (System.currentTimeMillis() - beforeClickTime < CLICK_INTERVAL_TIME) {
                    handler.removeMessages(1);
                    handler.sendEmptyMessage(2);
                } else {
                    if (!handler.hasMessages(2) && !handler.hasMessages(1))
                        handler.sendEmptyMessageDelayed(1, CLICK_INTERVAL_TIME);
                }
                beforeClickTime = System.currentTimeMillis();
            }
        });

        /**
         * 无wifi 继续播放点击
         */
        adapter.setToAddPlayerViewClick(new XkshVideoAdapter.ToAddPlayerViewClick() {
            @Override
            public void clickNoWifi(int position) {
                SPUtils.getInstance().put(Constants.AGREE_NETWORK, "1");
                for (int i = 0; i < mDatas.size(); i++) {
                    if (null != mDatas.get(i)) {
                        mDatas.get(i).setWifi(true);
                    }
                }

                for (int i = 0; i < mDatas.size(); i++) {
                    if (null != mDatas.get(i)) {
                        mDatas.get(i).setWifi(true);
                    }
                }
                addPlayView(position);
                adapter.notifyDataSetChanged();
            }
        });

        //点赞
        adapter.setlikeListener(new XkshVideoAdapter.LikeListener() {
            @Override
            public void likeClick(View view, DataDTO item, ImageView likeImage, TextView likeNum) {
                if (TextUtils.isEmpty(PersonInfoManager.getInstance().getTransformationToken())) {
                    noLoginTipsPop();
                } else {
                    addOrCancelLike(myContentId, videoType, likeImage, likeNum);
                }
            }
        });

        //评论
        adapter.setCommentListener(new XkshVideoAdapter.CommentListener() {
            @Override
            public void commentClick(View view, DataDTO item, TextView commentNum) {
                showCommentPopWindow();
            }
        });

        //转发
        adapter.setShareListener(new XkshVideoAdapter.ShareListener() {
            @Override
            public void shareClick(View view, DataDTO item) {
                sharePop();
            }
        });

        //窗口视频双击点击监听
        if (null != playerView) {
            playerView.mWindowPlayer.setOnDoubleClickxksh(new WindowPlayer.DoubleClickxksh() {
                @Override
                public void onDoubleClickxksh(DataDTO item) {
                    if (System.currentTimeMillis() - beforeClickTime < CLICK_INTERVAL_TIME) {
                        handler.removeMessages(1);
                        handler.sendEmptyMessage(2);
                    } else {
                        if (!handler.hasMessages(2) && !handler.hasMessages(1))
                            handler.sendEmptyMessageDelayed(1, CLICK_INTERVAL_TIME);
                    }
                    beforeClickTime = System.currentTimeMillis();
                }
            });
        }

        videoDetailRv.setAdapter(adapter);

    }

    //位移动画
    private void translateAnimation() {
        //向左位移显示动画
        translateAniLeftShow = new TranslateAnimation(
                Animation.RELATIVE_TO_SELF,//RELATIVE_TO_SELF表示操作自身
                0,//fromXValue表示开始的X轴位置
                Animation.RELATIVE_TO_SELF,
                1,//fromXValue表示结束的X轴位置
                Animation.RELATIVE_TO_SELF,
                0,//fromXValue表示开始的Y轴位置
                Animation.RELATIVE_TO_SELF,
                0);//fromXValue表示结束的Y轴位置
        translateAniLeftShow.setRepeatMode(Animation.REVERSE);
        translateAniLeftShow.setDuration(500);

        //向左位移隐藏动画
        translateAniLeftHide = new TranslateAnimation(
                Animation.RELATIVE_TO_SELF,//RELATIVE_TO_SELF表示操作自身
                1,//fromXValue表示开始的X轴位置
                Animation.RELATIVE_TO_SELF,
                0,//fromXValue表示结束的X轴位置
                Animation.RELATIVE_TO_SELF,
                0,//fromXValue表示开始的Y轴位置
                Animation.RELATIVE_TO_SELF,
                0);//fromXValue表示结束的Y轴位置
        translateAniLeftHide.setRepeatMode(Animation.REVERSE);
        translateAniLeftHide.setDuration(500);
    }

    /**
     * WIFi按钮是否显示
     *
     * @param isVisible
     */
    private void setWifiVisible(boolean isVisible) {
        for (int i = 0; i < mDatas.size(); i++) {
            mDatas.get(i).setWifi(isVisible);
        }
        if (null != adapter) {
            adapter.notifyDataSetChanged();
        }
    }

    /**
     * 在视频playview位置上添加各种view
     *
     * @param position
     */
    public void addPlayView(final int position) {
        try {
            if (isVisibleNoWifiView(this)) {
                return;
            }

            if (mDatas.size() <= position) {
                return;
            }

            if (null != playerView.mWindowPlayer && null != playerView.mWindowPlayer.mLayoutBottom && null != playerView.mWindowPlayer.mLayoutBottom.getParent()) {
                ((ViewGroup) playerView.mWindowPlayer.mLayoutBottom.getParent()).removeView(playerView.mWindowPlayer.mLayoutBottom);
            }

            if (null != playerView && null != playerView.getParent()) {
                ((ViewGroup) playerView.getParent()).removeView(playerView);
            }
            LinearLayout linearLayout = (LinearLayout) adapter.getViewByPosition(currentIndex, R.id.introduce_lin);
//        RelativeLayout.LayoutParams mLayoutBottomParams = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            RelativeLayout itemRelativelayout = (RelativeLayout) adapter.getViewByPosition(position, R.id.item_relativelayout);

            String videoType = videoIsNormal(Integer.parseInt(NumberFormatTool.getNumStr(mDatas.get(position).getWidth())),
                    Integer.parseInt(NumberFormatTool.getNumStr(mDatas.get(position).getHeight())));
            videoLx = videoType;
            if (TextUtils.equals("0", videoType)) {
                double percent = Double.parseDouble(mDatas.get(position).getWidth()) / Double.parseDouble(mDatas.get(position).getHeight());
                double mHeight;
                mHeight = getResources().getDisplayMetrics().widthPixels / percent;
                playViewParams = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, (int) mHeight);
                playViewParams.removeRule(RelativeLayout.ALIGN_PARENT_TOP);
                playViewParams.addRule(RelativeLayout.CENTER_IN_PARENT);
                playViewParams.setMargins(0, 0, 0, 0);
                playerView.mSuperPlayer.setRenderMode(TXLiveConstants.RENDER_MODE_FULL_FILL_SCREEN);

                //            int height = (int) (Integer.parseInt(mDatas.get(position).getWidth()) / Constants.Portrait_Proportion);


                playerView.setOrientation(false);
                if (null != itemRelativelayout) {
                    itemRelativelayout.addView(playerView.mWindowPlayer.mLayoutBottom);
                }
            } else if (TextUtils.equals("1", videoType)) {
                int height = (int) (ScreenUtils.getPhoneWidth(this) / Constants.Portrait_Proportion);
                playViewParams = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, height);
                if (phoneIsNormal()) {
                    playViewParams.removeRule(RelativeLayout.ALIGN_PARENT_TOP);
                    playViewParams.addRule(RelativeLayout.CENTER_IN_PARENT);
                    playerView.mSuperPlayer.setRenderMode(TXLiveConstants.RENDER_MODE_ADJUST_RESOLUTION);
                    playerView.setOrientation(false);
                } else {
                    playViewParams.addRule(RelativeLayout.ALIGN_PARENT_TOP);
                    playerView.mSuperPlayer.setRenderMode(TXLiveConstants.RENDER_MODE_FULL_FILL_SCREEN);
                    playerView.setOrientation(false);
                }
                if (null != itemRelativelayout) {
                    itemRelativelayout.addView(playerView.mWindowPlayer.mLayoutBottom);
                }
            } else {
                int height = (int) (ScreenUtils.getPhoneWidth(this) / Constants.Horizontal_Proportion);
                playViewParams = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, height);
                playViewParams.addRule(RelativeLayout.CENTER_IN_PARENT);
                playerView.mSuperPlayer.setRenderMode(TXLiveConstants.RENDER_MODE_ADJUST_RESOLUTION);
                playerView.setOrientation(true);
                if (null != itemRelativelayout) {
                    itemRelativelayout.addView(playerView.mWindowPlayer.mLayoutBottom);
                }
            }
            playerView.setLayoutParams(playViewParams);
            playerView.setTag(position);
            if (rlLp != null) {
                rlLp.addView(playerView, 1);
                //露出即上报
                if (!TextUtils.isEmpty(mDataDTO.getVolcCategory())) {
                    uploadBuriedPoint(ContentBuriedPointManager.setContentBuriedPoint(this, mDataDTO.getThirdPartyId(), "", "", Constants.CMS_CLIENT_SHOW, mDataDTO.getVolcCategory(), mDataDTO.getRequestId(), appName), Constants.CMS_CLIENT_SHOW);
                }
                play(mDatas.get(position).getPlayUrl(), mDatas.get(position).getTitle());
            }
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
    }

    private void initSmartRefresh() {
        refreshLayout = findViewById(R.id.refreshLayout);
        refreshLayout.setEnableScrollContentWhenLoaded(true);
        refreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                if (playerView.mSuperPlayer.getPlayerState() == SuperPlayerDef.PlayerState.PLAYING) {
                    playerView.mSuperPlayer.pause();
                }
                isLoadComplate = false;
//                adapter.setOnLoadMoreListener(requestLoadMoreListener, videoDetailRv);
                getOneVideo();
                //重置重播标识
                if (null != playerView && null != playerView.buriedPointModel) {
                    playerView.buriedPointModel.setXksh_renew("false");
                }
            }
        });
//        requestLoadMoreListener = new BaseQuickAdapter.RequestLoadMoreListener() {
//            @Override
//            public void onLoadMoreRequested() {
        //先注释，不加载更多
//                if (!isLoadComplate) {
//                    videoDetailRv.post(new Runnable() {
//                        @Override
//                        public void run() {
//                            if (mDatas.isEmpty()) {
//                                adapter.loadMoreFail();
//                                return;
//                            }
//                            getRandomVideoList();
//                        }
//                    });
//                }
//            }
//        };
    }

    /**
     * 初始化评论列表
     */
    private void initCommentPopRv() {
        mCommentPopRvData = new ArrayList<>();
        mCommentPopDtoData = new ArrayList<>();
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        commentPopRv.setLayoutManager(linearLayoutManager);
        commentPopRvAdapter = new CommentPopRvAdapter(mCommentPopRvData, this);
        commentPopRvAdapter.bindToRecyclerView(commentPopRv);
        commentPopRvAdapter.setEmptyView(R.layout.comment_list_empty);
        commentPopRvAdapter.expandAll();
        commentPopRv.setAdapter(commentPopRvAdapter);
        commentPopRvAdapter.setOnLoadMoreListener(new BaseQuickAdapter.RequestLoadMoreListener() {
            @Override
            public void onLoadMoreRequested() {
                commentPopRv.post(new Runnable() {
                    @Override
                    public void run() {
                        mPageIndex++;
                        getCommentList(String.valueOf(mPageIndex), String.valueOf(mPageSize), false);
                    }
                });
            }
        }, commentPopRv);

        //评论点赞
        commentPopRvAdapter.setLv1CommentLike(new CommentPopRvAdapter.Lv1CommentLikeListener() {
            @Override
            public void lv1CommentLikeClick(Object o, String targetId, ImageView likeIcon, TextView likeNum) {
                CommentLikeOrCancel(o, targetId, likeIcon, likeNum);
            }
        });

        commentPopRvAdapter.setLv2CommentLike(new CommentPopRvAdapter.Lv2CommentLikeListener() {
            @Override
            public void Lv2CommentLikeClick(Object o, String targetId, ImageView likeIcon, TextView likeNum) {
                CommentLikeOrCancel(o, targetId, likeIcon, likeNum);
            }
        });

        commentPopRvAdapter.setReback1Like(new CommentPopRvAdapter.Reback1LikeBtnListener() {
            @Override
            public void reback1LikeClick(Object o, String targetId, ImageView likeIcon, TextView likeNum) {
                CommentLikeOrCancel(o, targetId, likeIcon, likeNum);
            }
        });

        commentPopRvAdapter.setReback2Like(new CommentPopRvAdapter.Reback2LikeBtnListener() {
            @Override
            public void reback2LikeClick(Object o, String targetId, ImageView likeIcon, TextView likeNum) {
                CommentLikeOrCancel(o, targetId, likeIcon, likeNum);
            }
        });
    }


    /**
     * 评论列表弹出框
     */
    private void showCommentPopWindow() {
        if (null == popupWindow) {
            //创建并显示popWindow
            popupWindow = new CustomPopWindow.PopupWindowBuilder(this)
                    .setView(contentView)
                    .setOutsideTouchable(false)
                    .setFocusable(true)
                    .setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN)
                    .size(AppInit.getContext().getResources().getDisplayMetrics().widthPixels, (int) (AppInit.getContext().getResources().getDisplayMetrics().heightPixels * 0.7))
                    .setAnimationStyle(R.style.take_popwindow_anim)
                    .create()
                    .showAtLocation(rootView, Gravity.BOTTOM, 0, 0);
        } else {
            popupWindow.showAtLocation(rootView, Gravity.BOTTOM, 0, 0);
        }
//        SystemUtils.hideBottomUIMenuForPopupWindow(popupWindow);
        popupWindow.getPopupWindow().setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
//                SystemUtils.hideSystemUI(decorView);
                VideoScaleUtils.getInstance().videoTranslationScale(VideoHomeActivity.this, videoLx, 0, playerView);
                controlShow();
            }
        });
        VideoScaleUtils.getInstance().videoTranslationScale(this, videoLx, 1, playerView);
        controlHide();
    }

    private void controlHide() {
        ImageView coverPicture = (ImageView) adapter.getViewByPosition(currentIndex, R.id.cover_picture);
        if (null != coverPicture) {
            coverPicture.setVisibility(View.GONE);
        }
        if (TextUtils.equals(videoLx, "2")) {
            ImageView horizontalVideoWdcsLogo = (ImageView) adapter.getViewByPosition(currentIndex, R.id.horizontal_video_wdcs_logo);
            if (null != horizontalVideoWdcsLogo) {
                horizontalVideoWdcsLogo.setVisibility(View.GONE);
            }
        } else {
            ImageView verticalVideoWdcsLogo = (ImageView) adapter.getViewByPosition(currentIndex, R.id.vertical_video_wdcs_logo);
            if (null != verticalVideoWdcsLogo) {
                verticalVideoWdcsLogo.setVisibility(View.GONE);
            }
        }
    }

    private void controlShow() {
        if (TextUtils.equals(videoLx, "2")) {
            ImageView horizontalVideoWdcsLogo = (ImageView) adapter.getViewByPosition(currentIndex, R.id.horizontal_video_wdcs_logo);
            if (null != horizontalVideoWdcsLogo) {
                horizontalVideoWdcsLogo.setVisibility(View.VISIBLE);
            }
        } else {
            ImageView verticalVideoWdcsLogo = (ImageView) adapter.getViewByPosition(currentIndex, R.id.vertical_video_wdcs_logo);
            if (null != verticalVideoWdcsLogo) {
                verticalVideoWdcsLogo.setVisibility(View.VISIBLE);
            }
        }
        handler.sendEmptyMessageDelayed(3, 400);
    }

    /**
     * 播放视频
     *
     * @param playUrl
     */
    public void play(String playUrl, String title) {
//        if (null == playUrl || TextUtils.isEmpty(playUrl)) {
//            ToastUtils.showShort("当前播放地址(" + playUrl + "),是一个无效地址");
//            return;
//        }
        if (null != playerView) {
//            videoStaticBg.setVisibility(View.GONE);
            SuperPlayerModel model = new SuperPlayerModel();
            model.url = playUrl;
            model.title = title;
            model.contentId = myContentId;
            playerView.playWithModel(model);
        }
    }

    public void stop() {
        if (null != playerView && null != playerView.getParent()) {
            ((ViewGroup) playerView.getParent()).removeView(playerView);
        }
        playerView.stopPlay();
    }

    public void reset() {
        if (null != playerView && null != playerView.getParent()) {
            ((ViewGroup) playerView.getParent()).removeView(playerView);
        }

        if (null != playerView) {
            playerView.resetPlayer();
        }
    }

    /**
     * 添加软键盘监听
     */
    private void setSoftKeyBoardListener() {
        softKeyBoardListener = new SoftKeyBoardListener(this);
        //软键盘状态监听
        softKeyBoardListener.setListener(new SoftKeyBoardListener.OnSoftKeyBoardChangeListener() {
            @Override
            public void keyBoardShow(int height) {
                //软键盘已经显示，做逻辑
                Log.e("yqh", "软键盘已经显示,做逻辑");
            }

            @Override
            public void keyBoardHide(int height) {
                //软键盘已经隐藏,做逻辑
//                SystemUtils.hideSystemUI(decorView);
                if (null != inputAndSendPop) {
                    inputAndSendPop.getPopupWindow().dismiss();
                }
                Log.e("yqh", "软键盘已经隐藏,做逻辑");
            }
        });
    }


    /**
     * 视频是否是16：9
     * 0 :  竖版视频非16：9
     * 1 ：  竖版视频16：9
     * 2 ：  横板视频
     */
    public static String videoIsNormal(int videoWidth, int videoHeight) {
        if (videoWidth == 0 && videoHeight == 0) {
            return "2";
        }
        if (videoWidth > videoHeight) {
            //横板
            if (videoWidth * 9 == videoHeight * 16) {
                return "2";
            } else {
                return "0";
            }
        } else {
            //竖版
            if (videoHeight * 9 == videoWidth * 16) {
                return "1";
            } else {
                return "0";
            }
        }
    }

    /**
     * 手机是否为16：9
     *
     * @return
     */
    private boolean phoneIsNormal() {
        int phoneWidth = ScreenUtils.getPhoneWidth(this);
        int phoneHeight = ScreenUtils.getPhoneHeight(this);
        if (phoneHeight * 9 == phoneWidth * 16) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (playerView != null && null != mDataDTO && !SPUtils.isVisibleNoWifiView(this)) {
            SuperPlayerImpl.mCurrentPlayVideoURL = mDataDTO.getPlayUrl();
            if (playerView.homeVideoIsLoad) {
                playerView.mSuperPlayer.resume();
            } else {
                playerView.mSuperPlayer.reStart();
            }
        }
        isPause = false;
        xkshOldSystemTime = DateUtils.getTimeCurrent();
        if (!TextUtils.isEmpty(myContentId)) {
            getContentState(myContentId);
        }

        if (PersonInfoManager.getInstance().isRequestSzrmLogin()) {
            try {
                szrmLoginRequest(false);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        if (null != playerView && playerView.mSuperPlayer.getPlayerMode() == SuperPlayerDef.PlayerMode.FULLSCREEN) {
            SystemUtils.hideSystemUI(decorView);
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (playerView == null) {
            return;
        }
        playerView.mSuperPlayer.pause();
        isPause = true;
        if (null == mDataDTO) {
            return;
        }
        finderPoint();
    }

    public void finderPoint() {
        playerView.mSuperPlayer.pause();
        if (!TextUtils.isEmpty(mDataDTO.getVolcCategory())) {
            if (playerView.mWindowPlayer.mCurrentPlayState != SuperPlayerDef.PlayerState.END) {

                /**
                 * 上报内容埋点 视频播放时长
                 */
                double currentPercent = 0;
                xkshReportTime = DateUtils.getTimeCurrent() - xkshOldSystemTime;
                if (mDuration != 0) {
                    //这里算拖动到哪里  算最高的播放百分比，用来看完播率
                    if (mProgress == 0) {
                        currentPercent = (xkshReportTime * 1.0 / (mDuration * 1000.0));
                    } else {
                        currentPercent = playerView.mSuperPlayer.getVodPlay().getCurrentPlaybackTime() * 1.0 / mDuration;
                    }

                }
                double uploadPercent = 0;
                if (null == playerView.buriedPointModel.getIs_renew() || TextUtils.equals("false", playerView.buriedPointModel.getIs_renew())) {
//                      //不为重播
                    if (currentPercent > maxPercent) {
                        uploadPercent = currentPercent;
                        maxPercent = currentPercent;
                    } else {
                        uploadPercent = maxPercent;
                    }
                } else {
                    uploadPercent = 1;
                }

                String pointPercentTwo = NumberFormatTool.division(uploadPercent);
//                lsDuration = mProgress;
                String event;
                if (TextUtils.equals(mDataDTO.getIsAutoReportEvent(), "1")) {
                    event = Constants.CMS_VIDEO_OVER;
                } else {
                    event = Constants.CMS_VIDEO_OVER_AUTO;
                }
                //上报埋点
                uploadBuriedPoint(ContentBuriedPointManager.setContentBuriedPoint(this, mDataDTO.getThirdPartyId(), String.valueOf(xkshReportTime), pointPercentTwo, event, mDataDTO.getVolcCategory(), mDataDTO.getRequestId(), appName), event);
                Log.e("video_md", "埋点事件：" + event + "播放时长:" + xkshReportTime + "---" + "播放百分比:" + pointPercentTwo);
            }
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (instance != null) {
            instance.release();
            instance.mSuperPlayer.destroy();
            instance = null;
        }
        OkGo.getInstance().cancelAll();
        maxPercent = 0;
        unregisterReceiver(netWorkStateReceiver);
//        OkGo.getInstance().cancelTag(VIDEOTAG);
    }

    /**
     * 获取视频
     */
    private void getOneVideo() {
        if (null != playerView && null != playerView.getParent()) {
            ((ViewGroup) playerView.getParent()).removeView(playerView);
        }
        mDatas.clear();
        OkGo.<VideoOneModel>get(ApiConstants.getInstance().getVideoDetailUrl() + contentId)
                .cacheMode(CacheMode.NO_CACHE)
                .execute(new JsonCallback<VideoOneModel>(VideoOneModel.class) {
                    @Override
                    public void onSuccess(Response<VideoOneModel> response) {
                        if (null == response.body()) {
                            ToastUtils.showShort(R.string.data_err);
                            return;
                        }

                        if(null!=response.body().getCode()&&response.body().getCode().equals(success_code)) {
                            if (null == response.body().getData()) {
                                ToastUtils.showShort(R.string.data_err);
                                return;
                            }

                            mDatas.add(response.body().getData());
                            for (int i = 0; i < mDatas.size(); i++) {
                                String videoType = videoIsNormal(Integer.parseInt(NumberFormatTool.getNumStr(mDatas.get(i).getWidth())),
                                        Integer.parseInt(NumberFormatTool.getNumStr(mDatas.get(i).getHeight())));
                                mDatas.get(i).setLogoType(videoType);
                            }
                            setDataWifiState(mDatas, VideoHomeActivity.this);
                            adapter.setNewData(mDatas);
                            if (mDatas.size() > 0) {
                                initialize = false;
                                recordContentId = String.valueOf(mDatas.get(mDatas.size() - 1).getId());
                            }
                        } else {
                            if (TextUtils.isEmpty(response.body().getMessage())) {
                                return;
                            }
                            ToastUtils.showShort(response.body().getMessage());
                        }
                        if (null != refreshLayout) {
                            refreshLayout.finishRefresh();
                        }
                    }

                    @Override
                    public void onError(Response<VideoOneModel> response) {
                        if (null != response.body()) {
                            ToastUtils.showShort(response.body().getMessage());
                            return;
                        }
                        ToastUtils.showShort(R.string.net_err);
                    }

                    @Override
                    public void onFinish() {
                        super.onFinish();
                    }
                });
    }

    /**
     * 获取随机视频
     */
    private void getRandomVideoList() {
        OkGo.<VideoDetailModel>get(ApiConstants.getInstance().queryRandomVideoList())
                .cacheMode(CacheMode.NO_CACHE)
                .params("pageSize", "15")
                .params("org_id", appId)
                .execute(new JsonCallback<VideoDetailModel>(VideoDetailModel.class) {
                    @Override
                    public void onSuccess(Response<VideoDetailModel> response) {
                        if (null == response.body()) {
                            ToastUtils.showShort(R.string.data_err);
                            return;
                        }

                        if(null!=response.body().getCode()&&response.body().getCode().equals(success_code)) {
                            if(null!=response.body().getCode()&&response.body().getCode().equals(success_code)) {
                                if (null == response.body().getData()) {
                                    isLoadComplate = true;
                                    ToastUtils.showShort(R.string.data_err);
                                    return;
                                }

                                if (response.body().getData().size() == 0) {
                                    adapter.loadMoreEnd(false);
//                                    adapter.setOnLoadMoreListener(null, videoDetailRv);
//                                    if (null != footerView && null != footerView.getParent()) {
//                                        ((ViewGroup) footerView.getParent()).removeView(footerView);
//                                    }
//                                    adapter.addFooterView(footerView);

                                    isLoadComplate = true;
                                } else {
//                                    adapter.setOnLoadMoreListener(requestLoadMoreListener, videoDetailRv);
                                    isLoadComplate = false;
                                    mDatas.addAll(response.body().getData());
                                    setDataWifiState(mDatas, VideoHomeActivity.this);
//                                    adapter.loadMoreComplete();
                                }
//                            adapter.setNewData(mDatas);
//                                adapter.loadMoreComplete();

                            } else {
                                adapter.loadMoreFail();
                            }
                        } else {

                        }
                    }

                    @Override
                    public void onError(Response<VideoDetailModel> response) {
                        super.onError(response);
                        if (null != response.body()) {
                            ToastUtils.showShort(response.body().getMessage());
                            return;
                        }
                        ToastUtils.showShort(R.string.net_err);
                    }
                });
    }


//    /**
//     * 获取更多数据
//     */
//    private void loadMoreData(String url, String contentId, String panelCode, String removeFirst, String refreshType) {
//        String deviceId = "";
//        try {
//            deviceId = param.getDeviceId();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        OkGo.<VideoOneModel>get(url)
//                .tag(VIDEOTAG)
//                .params("contentId", contentId)
//                .params("pageSize", 10)
//                .params("panelCode", panelCode)
//                .params("removeFirst", removeFirst)
//                .params("ssid", deviceId)
//                .params("refreshType", refreshType)
//                .params("type", "")
//                .cacheMode(CacheMode.NO_CACHE)
//                .execute(new JsonCallback<VideoOneModel>(VideoOneModel.class) {
//                    @Override
//                    public void onSuccess(Response<VideoOneModel> response) {
//                        if (null == response.body()) {
//                            isLoadComplate = true;
//                            ToastUtils.showShort(R.string.data_err);
//                            return;
//                        }
//
//                        if(null!=response.body().getCode()&&response.body().getCode().equals(success_code)) {
//                            if (null == response.body().getData()) {
//                                isLoadComplate = true;
//                                ToastUtils.showShort(R.string.data_err);
//                                return;
//                            }
//
////                            if (response.body().getData() == 0) {
////                                Log.e("loadMoreData", "没有更多视频了");
////                                adapter.loadMoreComplete();
////                                adapter.setOnLoadMoreListener(null, videoDetailRv);
////                                if (null != footView && null != footView.getParent()) {
////                                    ((ViewGroup) footView.getParent()).removeView(footView);
////                                }
////                                adapter.addFooterView(footView);
////                                isLoadComplate = true;
////                                return;
////                            } else {
////                                adapter.setOnLoadMoreListener(requestLoadMoreListener, videoDetailRv);
////                                isLoadComplate = false;
////                            }
//                            mDatas.add(response.body().getData());
//                            setDataWifiState(mDatas, VideoHomeActivity.this);
////                            adapter.setNewData(mDatas);
//                            recordContentId = String.valueOf(mDatas.get(mDatas.size() - 1).getId());
//                            Log.e("loadMoreData", "loadMoreData========" + mDatas.size());
//                            adapter.loadMoreComplete();
//                        } else {
//                            adapter.loadMoreFail();
//                        }
//                    }
//
//                    @Override
//                    public void onError(Response<VideoOneModel> response) {
//                        if (null != response.body()) {
//                            ToastUtils.showShort(response.body().getMessage());
//                            return;
//                        }
//                        ToastUtils.showShort(R.string.net_err);
//                    }
//
//                    @Override
//                    public void onFinish() {
//                        super.onFinish();
//                        refreshLayout.setEnableRefresh(true);
//                    }
//                });
//    }

    /**
     * 点赞/取消点赞
     */
    private void addOrCancelLike(String targetId, String type, final ImageView likeImage, final TextView likeNum) {
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("targetId", targetId);
            jsonObject.put("type", type);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        OkGo.<String>post(ApiConstants.getInstance().addOrCancelLike())
                .tag(VIDEOTAG)
                .headers("token", PersonInfoManager.getInstance().getTransformationToken())
                .upJson(jsonObject)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        if (null == response.body()) {
                            ToastUtils.showShort(R.string.data_err);
                            return;
                        }

                        if (null == playerView) {
                            return;
                        }

                        try {
                            JSONObject json = new JSONObject(response.body());
                            if (null != json && json.get("code").toString().equals("200")) {
                                if (!mDatas.isEmpty() && mDatas.size() > currentIndex) {
                                    String jsonString = BuriedPointModelManager.getLikeAndFavorBuriedPointData(myContentId, mDatas.get(currentIndex).getTitle(),
                                            "", "", "", "", mDatas.get(currentIndex).getIssueTimeStamp(),
                                            Constants.CONTENT_TYPE);
                                    Log.e("埋点", "埋点：点赞---" + jsonString);
                                }

                                if (json.get("data").toString().equals("1")) {
                                    int num = 0;
                                    if (null != likeImage) {
                                        likeImage.setImageResource(R.drawable.szrm_sdk_favourite_select);
                                    }
                                    if (null != likeNum) {
                                        num = Integer.parseInt(NumberFormatTool.getNumStr(likeNum.getText().toString()));
                                        num++;
                                        likeNum.setText(NumberFormatTool.formatNum(num, false));
                                    }

                                    mDataDTO.setWhetherLike(true);
                                    playerView.contentStateModel.setWhetherLike("true");
                                    playerView.contentStateModel.setLikeCountShow(NumberFormatTool.formatNum(num, false).toString());
                                } else {
                                    int num = 0;
                                    if (null != likeImage) {
                                        likeImage.setImageResource(R.drawable.szrm_sdk_favourite);
                                    }
                                    if (null != likeNum) {
                                        num = Integer.parseInt(NumberFormatTool.getNumStr(likeNum.getText().toString()));
                                        if (num > 0) {
                                            num--;
                                        }
                                        if (num == 0) {
                                            likeNum.setText("赞");
                                        } else {
                                            likeNum.setText(NumberFormatTool.formatNum(num, false));
                                        }
                                    }
                                    mDataDTO.setWhetherLike(false);
                                    playerView.contentStateModel.setWhetherLike("false");
                                    playerView.contentStateModel.setLikeCountShow(NumberFormatTool.formatNum(num, false).toString());
                                }
                                if (null != playerView.contentStateModel) {
                                    playerView.setContentStateModel(myContentId, videoType);
                                }
                            } else if (json.get("code").toString().equals(token_error)) {
                                Log.e("addOrCancelLike", "无token,跳转登录");
                                try {
                                    param.toLogin();
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                            } else {
                                if (null != json.get("message").toString()) {
                                    ToastUtils.showShort(json.get("message").toString());
                                } else {
                                    ToastUtils.showShort("点赞失败");
                                }
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                            ToastUtils.showShort("点赞失败");
                        }

                    }

                    @Override
                    public void onError(Response<String> response) {
                        ToastUtils.showShort("点赞失败");
                    }

                    @Override
                    public void onFinish() {
                        super.onFinish();
                        likeIsRequesting = false;
                    }
                });
    }

    /**
     * 评论点赞/取消点赞
     */
    private void CommentLikeOrCancel(final Object o, String targetId, final ImageView likeImage, final TextView likeNum) {
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("targetId", targetId);
            jsonObject.put("type", "comment");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        OkGo.<String>post(ApiConstants.getInstance().addOrCancelLike())
                .tag(VIDEOTAG)
                .headers("token", PersonInfoManager.getInstance().getTransformationToken())
                .upJson(jsonObject)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        if (null == response.body()) {
                            ToastUtils.showShort(R.string.data_err);
                            return;
                        }

                        try {
                            JSONObject json = new JSONObject(response.body());
                            if (null != json && json.get("code").toString().equals("200")) {
                                if (!mDatas.isEmpty() && mDatas.size() > currentIndex) {
                                    String jsonString = BuriedPointModelManager.getLikeAndFavorBuriedPointData(myContentId, mDatas.get(currentIndex).getTitle(),
                                            "", "", "", "", mDatas.get(currentIndex).getIssueTimeStamp(),
                                            Constants.CONTENT_TYPE);
                                    Log.e("埋点", "埋点：点赞---" + jsonString);
                                }


                                if (json.get("data").toString().equals("1")) {
                                    int num = 0;
                                    if (null != likeImage) {
                                        likeImage.setImageResource(R.drawable.szrm_sdk_comment_like);
                                    }
                                    if (null != likeNum) {
                                        num = Integer.parseInt(NumberFormatTool.getNumStr(likeNum.getText().toString()));
                                        num++;
                                        likeNum.setText(NumberFormatTool.formatNum(num, false));
                                        likeNum.setTextColor(getResources().getColor(R.color.bz_red));
                                    }
                                    if (o instanceof CommentLv1Model.DataDTO.RecordsDTO) {
                                        ((CommentLv1Model.DataDTO.RecordsDTO) o).setWhetherLike(true);
                                        ((CommentLv1Model.DataDTO.RecordsDTO) o).setLikeCount(num);
                                    } else if (o instanceof ReplyLv2Model.ReplyListDTO) {
                                        ((ReplyLv2Model.ReplyListDTO) o).setWhetherLike(true);
                                        ((ReplyLv2Model.ReplyListDTO) o).setLikeCount(num);
                                    }
//                                    mDataDTO.setWhetherLike(true);
//                                    playerView.contentStateModel.setWhetherLike("true");
//                                    playerView.contentStateModel.setLikeCountShow(NumberFormatTool.formatNum(num, false).toString());
                                } else {
                                    int num = 0;
                                    if (null != likeImage) {
                                        likeImage.setImageResource(R.drawable.szrm_sdk_comment_unlike);
                                    }
                                    if (null != likeNum) {
                                        num = Integer.parseInt(NumberFormatTool.getNumStr(likeNum.getText().toString()));
                                        if (num > 0) {
                                            num--;
                                        }
                                        if (num == 0) {
                                            likeNum.setText("");
                                        } else {
                                            likeNum.setText(NumberFormatTool.formatNum(num, false));
                                        }
                                        likeNum.setTextColor(getResources().getColor(R.color.video_c9));

                                        if (o instanceof CommentLv1Model.DataDTO.RecordsDTO) {
                                            ((CommentLv1Model.DataDTO.RecordsDTO) o).setWhetherLike(false);
                                            ((CommentLv1Model.DataDTO.RecordsDTO) o).setLikeCount(num);
                                        } else if (o instanceof ReplyLv2Model.ReplyListDTO) {
                                            ((ReplyLv2Model.ReplyListDTO) o).setWhetherLike(false);
                                            ((ReplyLv2Model.ReplyListDTO) o).setLikeCount(num);
                                        }
                                    }
//                                    mDataDTO.setWhetherLike(false);
//                                    playerView.contentStateModel.setWhetherLike("false");
//                                    playerView.contentStateModel.setLikeCountShow(NumberFormatTool.formatNum(num, false).toString());
                                }
//                                if (null != playerView.contentStateModel) {
//                                    playerView.setContentStateModel(myContentId, videoType);
//                                }
                            } else if (json.get("code").toString().equals(token_error)) {
                                Log.e("addOrCancelLike", "无token,跳转登录");
                                try {
                                    param.toLogin();
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                            } else {
                                if (null != json.get("message").toString()) {
                                    ToastUtils.showShort(json.get("message").toString());
                                } else {
                                    ToastUtils.showShort("点赞失败");
                                }
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                            ToastUtils.showShort("点赞失败");
                        }

                    }

                    @Override
                    public void onError(Response<String> response) {
                        ToastUtils.showShort("点赞失败");
                    }

                    @Override
                    public void onFinish() {
                        super.onFinish();
//                        likeIsRequesting = false;
                    }
                });
    }


    /**
     * 浏览量+1
     */
    private void addPageViews(String contentId) {
        OkGo.<String>post(ApiConstants.getInstance().addViews() + contentId)
                .tag(VIDEOTAG)
                .cacheMode(CacheMode.NO_CACHE)
                .execute(new StringCallback() {

                    @Override
                    public void onSuccess(Response<String> response) {
                        if (null == response.body()) {
                            ToastUtils.showShort(R.string.data_err);
                            return;
                        }
                        try {
                            JSONObject jsonObject = new JSONObject(response.body());
                            Log.e("yqh", jsonObject.getString("message"));
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void onError(Response<String> response) {
                        if (null == response.body()) {
                            ToastUtils.showShort(R.string.data_err);
                            return;
                        }
                        try {
                            JSONObject jsonObject = new JSONObject(response.body());
                            ToastUtils.showShort(jsonObject.getString("message"));
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                });

    }

    /**
     * 获取收藏点赞状态
     */
    public void getContentState(String contentId) {
        OkGo.<ContentStateModel>get(ApiConstants.getInstance().queryStatsData())
                .tag(VIDEOTAG)
                .headers("token", PersonInfoManager.getInstance().getTransformationToken())
                .params("contentId", contentId)
                .cacheMode(CacheMode.NO_CACHE)
                .execute(new JsonCallback<ContentStateModel>(ContentStateModel.class) {
                    @Override
                    public void onSuccess(Response<ContentStateModel> response) {
                        if (null == response.body()) {
                            ToastUtils.showShort(R.string.data_err);
                            return;
                        }

                        try {
                            if(null!=response.body().getCode()&&response.body().getCode().equals(success_code)) {
                                if (null == response.body().getData()) {
                                    ToastUtils.showShort(R.string.data_err);
                                    return;
                                }

                                playerView.contentStateModel = response.body().getData();
                                if (null != playerView.contentStateModel) {
                                    setLikeCollection(playerView.contentStateModel);
                                    playerView.setContentStateModel(myContentId, videoType);
                                }
                            } else {
                                ToastUtils.showShort(response.body().getMessage());
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void onError(Response<ContentStateModel> response) {
                        if (null != response.body()) {
                            ToastUtils.showShort(response.body().getMessage());
                            return;
                        }
                        ToastUtils.showShort(R.string.net_err);
                    }
                });
    }

    public void setLikeCollection(ContentStateModel.DataDTO contentStateModel) {
        ImageView likeImage = (ImageView) adapter.getViewByPosition(currentIndex, R.id.video_detail_likes_image);
        TextView likeNum = (TextView) adapter.getViewByPosition(currentIndex, R.id.likes_num);

        if (null != likeImage) {
            if (contentStateModel.getWhetherLike().equals("true")) {
                likeImage.setImageResource(R.drawable.szrm_sdk_favourite_select);
            } else {
                likeImage.setImageResource(R.drawable.szrm_sdk_favourite);
            }
        }

        if (null != likeNum) {
            if (contentStateModel.getLikeCountShow().equals("0")) {
                likeNum.setText("赞");
            } else {
                likeNum.setText(NumberFormatTool.formatNum(Long.parseLong(NumberFormatTool.getNumStr(contentStateModel.getLikeCountShow())), false));
            }
        }
    }

    /**
     * 获取专题合集标签
     */
    public void getThematicCollection(String contentId) {
        String belongTopicId = mDataDTO.getBelongTopicId();
        if (TextUtils.isEmpty(belongTopicId)) {
            belongTopicId = "0";
        }
        OkGo.<CollectionLabelModel>get(ApiConstants.getInstance().getCollectToVideo() + contentId + "/" + belongTopicId)
                .tag(VIDEOTAG)
                .headers("token", PersonInfoManager.getInstance().getTransformationToken())
                .cacheMode(CacheMode.NO_CACHE)
                .execute(new JsonCallback<CollectionLabelModel>(CollectionLabelModel.class) {
                    @Override
                    public void onSuccess(Response<CollectionLabelModel> response) {
                        if (null == response.body()) {
                            ToastUtils.showShort(R.string.data_err);
                            return;
                        }

                        try {
                            if(null!=response.body().getCode()&&response.body().getCode().equals(success_code)) {
                                if (null == response.body().getData()) {
                                    return;
                                }
                                topicName = response.body().getData().getTopicName();
                                collectionList = new ArrayList<>();
                                collectionTvList = new ArrayList<>();
                                collectionStrList = new ArrayList<>();
                                String collectionStr = "";
                                if (null == response.body().getData().getList()) {
                                    List<CollectionLabelModel.DataDTO.ListDTO> listDTO = new ArrayList<>();
                                    collectionList.addAll(listDTO);
                                } else {
                                    collectionList.addAll(response.body().getData().getList());
                                }

                                if (!TextUtils.isEmpty(topicName)) {
                                    CollectionLabelModel.DataDTO.ListDTO listDTO = new CollectionLabelModel.DataDTO.ListDTO();
                                    listDTO.setTitle("#" + topicName);
                                    listDTO.setId("");
                                    collectionList.add(listDTO);
                                }

                                for (int i = 0; i < collectionList.size(); i++) {
                                    collectionStr = collectionStr + collectionList.get(i).getTitle();
                                    collectionStrList.add(collectionList.get(i).getTitle());
                                    if (collectionList.size() == 1) {
                                        collectionTvList.add("  " + collectionList.get(i).getTitle());
                                    } else {
                                        if (i == 0) {
                                            collectionTvList.add("  " + collectionList.get(i).getTitle() + "｜");
                                        } else {
                                            if (i == collectionList.size() - 1) {
                                                collectionTvList.add(collectionList.get(i).getTitle());
                                            } else {
                                                collectionTvList.add(collectionList.get(i).getTitle() + "｜");
                                            }
                                        }
                                    }
                                }
                                TextView foldTextView = (TextView) adapter.getViewByPosition(currentIndex, R.id.fold_text);
                                TextView expendTextView = (TextView) adapter.getViewByPosition(currentIndex, R.id.expend_text);
                                String brief = "";
                                String spaceStr = "";
                                DataDTO item = adapter.getItem(currentIndex);
                                if (null == item) {
                                    return;
                                }
                                if (TextUtils.isEmpty(adapter.getItem(currentIndex).getBrief())) {
                                    brief = item.getTitle();
                                } else {
                                    brief = item.getBrief();
                                }
                                SpannableStringBuilder builder = new SpannableStringBuilder();
                                if (collectionList.isEmpty()) {
                                    return;
                                } else {
                                    for (int i = 0; i < collectionList.size(); i++) {
                                        ImageSpan imgSpan = null;
                                        if (!TextUtils.isEmpty(collectionList.get(i).getId())) {
                                            imgSpan = new ImageSpan(VideoHomeActivity.this,
                                                    R.drawable.szrm_sdk_collection_image,
                                                    ImageSpan.ALIGN_BASELINE);
                                        }

                                        final String str = collectionTvList.get(i);
                                        final String strChun = collectionStrList.get(i);
                                        SpannableString sp = new SpannableString(str);
                                        if (i == 0 && !TextUtils.isEmpty(collectionList.get(i).getId())) {
                                            sp.setSpan(imgSpan, 0, 1, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                                        }
                                        final String classId = String.valueOf(collectionList.get(i).getId());
//                                        /**
//                                         * 每一个合集标签点击事件
//                                         */
//                                        sp.setSpan(new CollectionClickble(new View.OnClickListener() {
//                                            @Override
//                                            public void onClick(View v) {
//                                                //合集标签点击事件
//                                                if (TextUtils.isEmpty(classId) && str.contains("#")) {
//                                                    adapter.setTopicClick(false);
//                                                    Log.e("topic", "点击的话题");
//                                                } else {
//                                                    adapter.setTopicClick(true);
//                                                    Intent intent = new Intent(VideoHomeActivity.this, VideoDetailActivity.class);
//                                                    intent.putExtra("classId", classId);
//                                                    intent.putExtra("className", strChun.trim());
//                                                    startActivity(intent);
//                                                }
//                                            }
//                                        }, VideoHomeActivity.this), 0, sp.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                                        if (i == collectionList.size() - 1) {
                                            builder.append(sp);
                                            builder.append("  " + brief);
                                        } else {
                                            builder.append(sp);
                                        }
                                    }
                                    if (null != foldTextView && null != expendTextView) {
                                        foldTextView.setMovementMethod(LinkMovementMethod.getInstance());
                                        foldTextView.setText(builder);
                                        expendTextView.setMovementMethod(LinkMovementMethod.getInstance());
                                        expendTextView.setText(builder);
                                    }
                                }
                            } else {
                                ToastUtils.showShort(response.body().getMessage());
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void onError(Response<CollectionLabelModel> response) {
                        super.onError(response);
                        if (null != response.body()) {
                            ToastUtils.showShort(response.body().getMessage());
                            return;
                        }
                        ToastUtils.showShort(R.string.net_err);
                    }

                    @Override
                    public void onFinish() {
                        super.onFinish();
                    }
                });
    }

    /**
     * 获取评论列表
     */
    public void getCommentList(String pageIndex, String pageSize, final boolean isRefresh) {
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("contentId", myContentId);
            jsonObject.put("pageIndex", pageIndex);
            jsonObject.put("pageSize", pageSize);
            jsonObject.put("pcommentId", "0");
        } catch (JSONException e) {
            e.printStackTrace();
        }

        OkGo.<CommentLv1Model>post(ApiConstants.getInstance().getCommentWithReply())
                .tag(VIDEOTAG)
                .upJson(jsonObject)
                .headers("token", PersonInfoManager.getInstance().getTransformationToken())
                .cacheMode(CacheMode.NO_CACHE)
                .execute(new JsonCallback<CommentLv1Model>(CommentLv1Model.class) {
                    @Override
                    public void onSuccess(Response<CommentLv1Model> response) {
                        if (null == response.body()) {
                            ToastUtils.showShort(R.string.data_err);
                            return;
                        }

                        try {
                            if(null!=response.body().getCode()&&response.body().getCode().equals("200")) {
                                if (null == response.body().getData()) {
                                    ToastUtils.showShort(R.string.data_err);
                                    return;
                                }

                                if (isRefresh) {
                                    mCommentPopRvData.clear();
                                    mCommentPopDtoData.clear();
                                }

                                //评论集合
                                List<CommentLv1Model.DataDTO.RecordsDTO> lv1List = response.body().getData().getRecords();
                                for (int i = 0; i < lv1List.size(); i++) {
                                    CommentLv1Model.DataDTO.RecordsDTO lv1Model = lv1List.get(i);
                                    lv1Model.setPosition(i);
                                    lv1Model.setShow(true);
                                    List<ReplyLv2Model.ReplyListDTO> lv2List = lv1Model.getReply().getReplyList();
                                    for (int j = 0; j < lv2List.size(); j++) {
                                        ReplyLv2Model.ReplyListDTO lv2Model = lv2List.get(j);
                                        lv2Model.setPosition(j);
                                        lv2Model.setParentPosition(i);
                                        lv1Model.addSubItem(lv2Model);
                                    }
                                    mCommentPopRvData.add(lv1Model);
                                }

                                mCommentPopDtoData.addAll(lv1List);
                                commentPopRvAdapter.setContentId(myContentId);
                                commentPopRvAdapter.setSrc(mCommentPopRvData);
                                commentPopRvAdapter.setNewData(mCommentPopRvData);

                                //第一级评论点击
                                commentPopRvAdapter.setLv1CommentClick(new CommentPopRvAdapter.Lv1CommentClick() {
                                    @Override
                                    public void Lv1Comment(String id, String replyName) {
                                        toSetHint(id, replyName);
                                    }
                                });

                                //第一级评论第一条回复点击
                                commentPopRvAdapter.setLv1No1Click(new CommentPopRvAdapter.Lv1No1Click() {
                                    @Override
                                    public void lv1No1Click(String id, String replyName) {
                                        toSetHint(id, replyName);
                                    }
                                });

                                //第一级评论第二条回复点击
                                commentPopRvAdapter.setLv1No2Click(new CommentPopRvAdapter.Lv1No2Click() {
                                    @Override
                                    public void lv1No2Click(String id, String replyName) {
                                        toSetHint(id, replyName);
                                    }
                                });

                                //第二级回复点击
                                commentPopRvAdapter.setLv2ReplyClick(new CommentPopRvAdapter.Lv2ReplyClick() {
                                    @Override
                                    public void Lv2ReplyClick(String id, String replyName) {
                                        toSetHint(id, replyName);
                                    }
                                });

                                TextView commentNum = (TextView) adapter.getViewByPosition(currentIndex, R.id.comment_num);
                                if (mCommentPopDtoData.isEmpty()) {
                                    if (null != commentNum) {
                                        commentNum.setText("评论");
                                    }
                                    commentPopCommentTotal.setText("(0)");
                                } else {
                                    if (null != commentNum) {
                                        commentNum.setText(response.body().getData().getTotal());
                                    }
                                    commentPopCommentTotal.setText("(" + response.body().getData().getTotal() + ")");
                                }

                                if (response.body().getData().getRecords().size() == 0) {
                                    commentPopRvAdapter.loadMoreEnd();
                                } else {
                                    commentPopRvAdapter.loadMoreComplete();
                                }

                            } else {
                                commentPopRvAdapter.loadMoreFail();
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }

                    }

                    @Override
                    public void onError(Response<CommentLv1Model> response) {
                        commentPopRvAdapter.loadMoreFail();
                    }
                });
    }

    private void toSetHint(String id, String replyName) {
        if (TextUtils.isEmpty(PersonInfoManager.getInstance().getTransformationToken())) {
            try {
                noLoginTipsPop();
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            KeyboardUtils.toggleSoftInput(getWindow().getDecorView());
            showInputEdittextAndSend();
            edtInput.setHint("回复@" + replyName);
            isReply = true;
            replyId = id;
        }
    }

    /**
     * 弹出发送评论弹出窗
     */
    private void showInputEdittextAndSend() {
        //创建并显示popWindow
        if (null == inputAndSendPop) {
            inputAndSendPop = new CustomPopWindow.PopupWindowBuilder(this)
                    .setView(sendPopContentView)
                    .setOutsideTouchable(false)
                    .setFocusable(true)
                    .setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE)
                    .setAnimationStyle(R.style.take_popwindow_anim)
                    .size(AppInit.getContext().getResources().getDisplayMetrics().widthPixels, ViewGroup.LayoutParams.WRAP_CONTENT)
                    .create()
                    .showAtLocation(rootView, Gravity.BOTTOM, 0, 0);
        } else {
            inputAndSendPop.showAtLocation(rootView, Gravity.BOTTOM, 0, 0);
        }
        edtInput.setFocusable(true);
        edtInput.setFocusableInTouchMode(true);
        edtInput.requestFocus();
    }

    /**
     * 评论
     */
    private void toComment(String content, String contentId) {
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("contentId", contentId);
            jsonObject.put("content", content);
            jsonObject.put("title", "");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        OkGo.<String>post(ApiConstants.getInstance().addComment())
                .tag(VIDEOTAG)
                .headers("token", PersonInfoManager.getInstance().getTransformationToken())
                .upJson(jsonObject)
                .cacheMode(CacheMode.NO_CACHE)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        if (null == response.body()) {
                            ToastUtils.showShort(R.string.data_err);
                            return;
                        }
                        try {
                            JSONObject mJsonObject = new JSONObject(response.body());
                            String code = mJsonObject.get("code").toString();

                            if (code.equals(success_code)) {
                                if (!mDatas.isEmpty() && mDatas.size() > currentIndex) {
                                    String jsonString = BuriedPointModelManager.getVideoComment(myContentId, mDatas.get(currentIndex).getTitle(), "", "",
                                            "", "", mDatas.get(currentIndex).getIssueTimeStamp(), Constants.CONTENT_TYPE);
                                    Log.e("埋点", "埋点：评论---" + jsonString);
                                }
                                ToastUtils.showShort("评论已提交，请等待审核通过！");
                                if (null != inputAndSendPop) {
                                    inputAndSendPop.dissmiss();
                                }
                                KeyboardUtils.hideKeyboard(getWindow().getDecorView());
                                mPageIndex = 1;
                                getCommentList("1", String.valueOf(mPageSize), true);
                            } else if (code.equals(token_error)) {
                                Log.e("addComment", "无token 去跳登录");
                                try {
                                    param.toLogin();
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                            } else {
                                if (null != mJsonObject.getString("message")) {
                                    ToastUtils.showShort(mJsonObject.getString("message"));
                                } else {
                                    ToastUtils.showShort("评论失败");
                                }
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                            ToastUtils.showShort("评论失败");
                        }
                    }

                    @Override
                    public void onError(Response<String> response) {
                        super.onError(response);
                        ToastUtils.showShort("评论失败");
                    }

                    @Override
                    public void onFinish() {
                        super.onFinish();
                        edtInput.setText("");
                    }
                });
    }

    /**
     * 回复
     */
    private void toReply(String id) {
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("id", id);
            jsonObject.put("reply", edtInput.getText().toString());
        } catch (JSONException e) {
            e.printStackTrace();
        }
        OkGo.<String>post(ApiConstants.getInstance().addUserReply())
                .tag(VIDEOTAG)
                .headers("token", PersonInfoManager.getInstance().getTransformationToken())
                .upJson(jsonObject)
                .cacheMode(CacheMode.NO_CACHE)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        if (null == response.body()) {
                            ToastUtils.showShort(R.string.data_err);
                            return;
                        }
                        try {
                            JSONObject mJsonObject = new JSONObject(response.body());
                            String code = mJsonObject.get("code").toString();

                            if (code.equals(success_code)) {
                                ToastUtils.showShort("回复已提交，请等待审核通过！");
                                if (null != inputAndSendPop) {
                                    inputAndSendPop.dissmiss();
                                }
                                mPageIndex = 1;
                                KeyboardUtils.hideKeyboard(getWindow().getDecorView());
                                getCommentList("1", String.valueOf(mPageSize), true);
                            } else if (code.equals(token_error)) {
                                try {
                                    param.toLogin();
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                            } else {
                                if (null != mJsonObject.getString("message")) {
                                    ToastUtils.showShort(mJsonObject.getString("message"));
                                } else {
                                    ToastUtils.showShort("回复失败");
                                }
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                            ToastUtils.showShort("回复失败");
                        }
                    }

                    @Override
                    public void onError(Response<String> response) {
                        super.onError(response);
                    }

                    @Override
                    public void onFinish() {
                        super.onFinish();
                    }
                });
    }

    /**
     * 获取推荐列表数据
     */
    private void getRecommend(String contentId, final int position) {
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("current", "1");
            jsonObject.put("pageSize", "999");
            jsonObject.put("contentId", contentId + "");
            jsonObject.put("pageIndex", "1");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        OkGo.<RecommendModel>post(ApiConstants.getInstance().recommendList())
                .tag(recommendTag)
                .headers("appId", appId)
                .upJson(jsonObject)
                .execute(new JsonCallback<RecommendModel>() {
                    @Override
                    public void onSuccess(Response<RecommendModel> response) {
                        if (null == response.body().getData()) {
                            ToastUtils.showShort(R.string.data_err);
                            return;
                        }
                        if(null!=response.body().getCode()&&response.body().getCode().equals("200")) {
                            recommondList.clear();
                            recommondList.addAll(response.body().getData().getRecords());
                            if (recommondList.size() > 1) {
                                adapter.setRecommendList(recommondList, true);
                                mDatas.get(position).setRecommendVisible(true);
                            } else if (recommondList.size() == 1) {
                                adapter.setRecommendList(recommondList, false);
                                mDatas.get(position).setRecommendVisible(true);
                            } else if (recommondList.size() == 0) {
                                adapter.setRecommendList(recommondList, false);
                                mDatas.get(position).setRecommendVisible(false);
                            }
                            adapter.notifyDataSetChanged();
                        } else {
                            ToastUtils.showShort(response.body().getMessage());
                        }

                    }

                    @Override
                    public void onError(Response<RecommendModel> response) {
                        super.onError(response);
                        if (null != response.body()) {
                            ToastUtils.showShort(response.body().getMessage());
                            return;
                        }
                        ToastUtils.showShort(R.string.net_err);
                    }

                    @Override
                    public void onFinish() {
                        super.onFinish();
                        if (isVisibleNoWifiView(VideoHomeActivity.this)) {
                            SPUtils.getInstance().put(Constants.AGREE_NETWORK, "0");
                        }
                        loadingProgress.setVisibility(View.GONE);
                        addPlayView(position);
                        getThematicCollection(myContentId);
                    }
                });
    }

    /**
     * 上报埋点
     *
     * @param jsonObject
     */
    public static void uploadBuriedPoint(JSONObject jsonObject, final String trackingType) {
        if (null == jsonObject) {
            return;
        }

        if (TextUtils.equals("0", SdkInteractiveParam.getInstance().getIsAgreePrivacy())) {
            return;
        }

        OkGo.<TrackingUploadModel>post(ApiConstants.getInstance().trackingUpload())
                .tag(TRACKINGUPLOAD)
                .headers("token", PersonInfoManager.getInstance().getTransformationToken())
                .upJson(jsonObject)
                .execute(new JsonCallback<TrackingUploadModel>() {
                    @Override
                    public void onSuccess(Response<TrackingUploadModel> response) {
                        Log.e("上报埋点", "上报事件" + trackingType);
                    }

                    @Override
                    public void onError(Response<TrackingUploadModel> response) {
                        super.onError(response);
                        if (null == response.body()) {
                            ToastUtils.showShort(R.string.data_err);
                            return;
                        }
                        ToastUtils.showShort(response.body().getMessage());
                    }

                    @Override
                    public void onFinish() {
                        super.onFinish();
                    }
                });
    }

    /**
     * 没有登录情况下 点击点赞收藏评论 提示登录的提示框
     */
    private void noLoginTipsPop() {
        if (null == noLoginTipsPop) {
            noLoginTipsPop = new CustomPopWindow.PopupWindowBuilder(this)
                    .setView(noLoginTipsView)
                    .enableBackgroundDark(true)
                    .setOutsideTouchable(true)
                    .setFocusable(true)
                    .setAnimationStyle(R.style.AnimCenter)
                    .size(getResources().getDisplayMetrics().widthPixels, getResources().getDisplayMetrics().heightPixels)
                    .create()
                    .showAtLocation(getWindow().getDecorView(), Gravity.CENTER, 0, 0);
        } else {
            noLoginTipsPop.showAtLocation(getWindow().getDecorView(), Gravity.CENTER, 0, 0);
        }
    }

    /**
     * 分享弹窗
     */
    private void sharePop() {
        if (null == sharePop) {
            sharePop = new CustomPopWindow.PopupWindowBuilder(this)
                    .setView(sharePopView)
                    .setOutsideTouchable(true)
                    .setFocusable(true)
                    .size(AppInit.getContext().getResources().getDisplayMetrics().widthPixels, ButtonSpan.dip2px(150))
                    .setAnimationStyle(R.style.take_popwindow_anim)
                    .create()
                    .showAtLocation(rootView, Gravity.BOTTOM, 0, 0);
        } else {
            sharePop.showAtLocation(rootView, Gravity.BOTTOM, 0, 0);
        }
    }

    @Override
    public void onClick(View view) {
        int id = view.getId();
        if (id == R.id.video_back) {
            finish();
        } else if (id == R.id.dismiss_pop) {
            if (popupWindow != null) {
                popupWindow.dissmiss();
            }
        } else if (id == R.id.comment_pop_rl) {
            if (TextUtils.isEmpty(PersonInfoManager.getInstance().getTransformationToken())) {
                try {
                    noLoginTipsPop();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } else {
                KeyboardUtils.toggleSoftInput(getWindow().getDecorView());
                edtInput.setHint("留下你的精彩评论");
                isReply = false;
                showInputEdittextAndSend();
            }
        } else if (id == R.id.no_login_tips_cancel) {
            if (null != noLoginTipsPop) {
                noLoginTipsPop.dissmiss();
            }
        } else if (id == R.id.no_login_tips_ok) {
            if (null != noLoginTipsPop) {
                noLoginTipsPop.dissmiss();
            }
            try {
                SdkInteractiveParam.getInstance().toLogin();
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if (id == R.id.share_wx_btn) {
            if (mDatas.isEmpty()) {
                return;
            }
            toShare(mDataDTO, Constants.SHARE_WX);
        } else if (id == R.id.share_circle_btn) {
            if (mDatas.isEmpty()) {
                return;
            }
            toShare(mDataDTO, Constants.SHARE_CIRCLE);
        } else if (id == R.id.share_qq_btn) {
            if (mDatas.isEmpty()) {
                return;
            }
            toShare(mDataDTO, Constants.SHARE_QQ);
        }
    }
}