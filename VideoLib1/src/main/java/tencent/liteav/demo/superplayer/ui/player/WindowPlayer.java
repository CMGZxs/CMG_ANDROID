package tencent.liteav.demo.superplayer.ui.player;

import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Build;
import android.util.AttributeSet;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;


import com.pasc.lib.videolib.R;

import common.constants.Constants;
import common.manager.BuriedPointModelManager;
import common.manager.ViewPagerLayoutManager;
import common.model.DataDTO;
import common.utils.NoScrollViewPager;
import tencent.liteav.demo.superplayer.SuperPlayerDef;
import tencent.liteav.demo.superplayer.contants.Contants;
import tencent.liteav.demo.superplayer.model.utils.VideoGestureDetector;
import tencent.liteav.demo.superplayer.ui.player.AbsPlayer;
import tencent.liteav.demo.superplayer.ui.view.PointSeekBar;
import tencent.liteav.demo.superplayer.ui.view.VideoProgressLayout;
import tencent.liteav.demo.superplayer.ui.view.VolumeBrightnessProgressLayout;
import common.model.VideoCollectionModel.DataDTO.RecordsDTO;

/**
 * 窗口模式播放控件
 * <p>
 * 除基本播放控制外，还有手势控制快进快退、手势调节亮度音量等
 * <p>
 * 1、点击事件监听{@link #onClick(View)}
 * <p>
 * 2、触摸事件监听{@link #onTouchEvent(MotionEvent)}
 * <p>
 * 2、进度条事件监听{@link #onProgressChanged(PointSeekBar, int, boolean)}
 * {@link #onStartTrackingTouch(PointSeekBar)}
 * {@link #onStopTrackingTouch(PointSeekBar)}
 */
public class WindowPlayer extends AbsPlayer implements View.OnClickListener {

    // UI控件
    private LinearLayout mLayoutTop;                             // 顶部标题栏布局
    public LinearLayout mLayoutBottom;                          // 底部进度条所在布局
    public ImageView mIvPause;                               // 暂停播放按钮
    public ImageView mIvFullScreen;                          // 全屏按钮
    private TextView mTvTitle;                               // 视频名称文本
    private TextView mTvBackToLive;                          // 返回直播文本
    private ImageView mBackground;                            // 背景
    private ImageView mIvWatermark;                           // 水印
    private TextView mTvCurrent;                             // 当前进度文本
    private TextView mTvDuration;                            // 总时长文本
    public PointSeekBar mSeekBarProgress;                       // 播放进度条
    public PointSeekBar xSeekBarProgress;                       //细的播放进度条
    public ProgressBar mLoadBar;
    public LinearLayout mLayoutReplay;                          // 重播按钮所在布局
    private ProgressBar mPbLiveLoading;                         // 加载圈
    private VolumeBrightnessProgressLayout mGestureVolumeBrightnessProgressLayout; // 音量亮度调节布局
    public VideoProgressLayout mGestureVideoProgressLayout;            // 手势快进提示布局

    public GestureDetector mGestureDetector;                       // 手势检测监听器
    private VideoGestureDetector mVideoGestureDetector;                      // 手势控制工具

    private boolean isShowing;                              // 自身是否可见
    private boolean mIsChangingSeekBarProgress;             // 进度条是否正在拖动，避免SeekBar由于视频播放的update而跳动
    public SuperPlayerDef.PlayerType mPlayType = SuperPlayerDef.PlayerType.VOD;                          // 当前播放视频类型
    public SuperPlayerDef.PlayerState mCurrentPlayState = SuperPlayerDef.PlayerState.END;                 // 当前播放状态
    public long mLivePushDuration;                      // 直播推流总时长
    public static long mDuration;                              // 视频总时长
    public static long mProgress;                              // 当前播放进度
    public double percent;                              // 当前播放进度百分比
    public long reportDuration = 0;                       //记录上报埋点时的进度
    public long videoDetailReportDuration = 0;          //详情页上报埋点记录的进度

    private Bitmap mBackgroundBmp;                         // 背景图
    private Bitmap mWaterMarkBmp;                          // 水印图
    private float mWaterMarkBmpX;                         // 水印x坐标
    private float mWaterMarkBmpY;                         // 水印y坐标
    private long mLastClickTime;                         // 上次点击事件的时间
    private DataDTO item;
    private RecordsDTO recordsDTO;
    private double mReportVodStartTime = -1;  //播放状态记录的当前时间戳
    private double mReportVodNoPlayingTime = -1; //非播放状态纪录当前时间戳
    private boolean mIsTurnPage; //是否为翻页
    private boolean mIsVideoDetailTurnPage; //是否为翻页
    private DataDTO mPreviousDTO;
    private RecordsDTO mPreviousRecordsDTO;
    private boolean isShowSelfProgress;
    private ImageView zdyIvPause;
    private ViewPagerLayoutManager myManager;
    private NoScrollViewPager mViewpager;
    private boolean mIsFragmentShow;
    private RelativeLayout windowPlayerRoot;

    public WindowPlayer(Context context) {
        super(context);
        initialize(context);
    }

    public WindowPlayer(Context context, AttributeSet attrs) {
        super(context, attrs);
        initialize(context);
    }

    public WindowPlayer(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initialize(context);
    }

    public DoubleClick mDoubleClick;

    public interface DoubleClick {
        void onDoubleClick(DataDTO item);
    }

    public void setOnDoubleClick(DoubleClick doubleClick) {
        this.mDoubleClick = doubleClick;
    }

    public DoubleClickxksh mDoubleClickxksh;

    public interface DoubleClickxksh {
        void onDoubleClickxksh(DataDTO item);
    }

    public void setOnDoubleClickxksh(DoubleClickxksh doubleClick) {
        this.mDoubleClickxksh = doubleClick;
    }

    public DoubleClickDetail mDoubleClickDetail;

    public interface DoubleClickDetail {
        void onDoubleClickDetail(DataDTO item);
    }

    public void setOnDoubleClickDetail(DoubleClickDetail doubleClick) {
        this.mDoubleClickDetail = doubleClick;
    }


    /**
     * 初始化控件、手势检测监听器、亮度/音量/播放进度的回调
     */
    private void initialize(Context context) {
        initView(context);
        mGestureDetector = new GestureDetector(getContext(), new GestureDetector.SimpleOnGestureListener() {
            @Override
            public boolean onDoubleTap(MotionEvent e) {
                if (mCurrentPlayState == SuperPlayerDef.PlayerState.LOADING) {
                    return false;
                }
//                togglePlayState();
////                show();
//                if (mHideViewRunnable != null) {
//                    removeCallbacks(mHideViewRunnable);
//                    postDelayed(mHideViewRunnable, Contants.delayMillis);
//                }
                return true;
            }

            @Override
            public boolean onSingleTapConfirmed(MotionEvent e) {
                if (mCurrentPlayState == SuperPlayerDef.PlayerState.LOADING) {
                    return false;
                }

                togglePlayState();
//                toggle();
//                togglePlayState();
//                show();
//                if (mHideViewRunnable != null) {
//                    removeCallbacks(mHideViewRunnable);
//                    postDelayed(mHideViewRunnable, Contants.delayMillis);
//                }

//                if (isShowSelfProgress) {
//                    toggle();
//                }

                return true;
            }

            @Override
            public boolean onScroll(MotionEvent downEvent, MotionEvent moveEvent, float distanceX, float distanceY) {
//                if (downEvent == null || moveEvent == null) {
//                    return false;
//                }
//                if (mVideoGestureDetector != null && mGestureVolumeBrightnessProgressLayout != null) {
//                    mVideoGestureDetector.check(mGestureVolumeBrightnessProgressLayout.getHeight(), downEvent, moveEvent, distanceX, distanceY);
//                }
                return true;
            }

            @Override
            public boolean onDown(MotionEvent e) {
//                if (mVideoGestureDetector != null) {
//                    mVideoGestureDetector.reset(getWidth(), mSeekBarProgress.getProgress());
//                }
                return true;
            }

        });
        mGestureDetector.setIsLongpressEnabled(false);

        mVideoGestureDetector = new VideoGestureDetector(getContext());
        mVideoGestureDetector.setVideoGestureListener(new VideoGestureDetector.VideoGestureListener() {
            @Override
            public void onBrightnessGesture(float newBrightness) {
                if (mGestureVolumeBrightnessProgressLayout != null) {
                    mGestureVolumeBrightnessProgressLayout.setProgress((int) (newBrightness * 100));
                    mGestureVolumeBrightnessProgressLayout.setImageResource(R.drawable.superplayer_ic_light_max);
                    mGestureVolumeBrightnessProgressLayout.show();
                }
            }

            @Override
            public void onVolumeGesture(float volumeProgress) {
                if (mGestureVolumeBrightnessProgressLayout != null) {
                    mGestureVolumeBrightnessProgressLayout.setImageResource(R.drawable.superplayer_ic_volume_max);
                    mGestureVolumeBrightnessProgressLayout.setProgress((int) volumeProgress);
                    mGestureVolumeBrightnessProgressLayout.show();
                }
            }

            @Override
            public void onSeekGesture(int progress) {
                mIsChangingSeekBarProgress = true;
                if (mGestureVideoProgressLayout != null) {

                    if (progress > mSeekBarProgress.getMax()) {
                        progress = mSeekBarProgress.getMax();
                    }
                    if (progress < 0) {
                        progress = 0;
                    }
                    mGestureVideoProgressLayout.setProgress(progress);
                    mGestureVideoProgressLayout.show();

                    float percentage = ((float) progress) / mSeekBarProgress.getMax();
                    float currentTime = (mDuration * percentage);
                    if (mPlayType == SuperPlayerDef.PlayerType.LIVE || mPlayType == SuperPlayerDef.PlayerType.LIVE_SHIFT) {
                        if (mLivePushDuration > MAX_SHIFT_TIME) {
                            currentTime = (int) (mLivePushDuration - MAX_SHIFT_TIME * (1 - percentage));
                        } else {
                            currentTime = mLivePushDuration * percentage;
                        }
                        mGestureVideoProgressLayout.setTimeText(formattedTime((long) currentTime));
                    } else {
                        mGestureVideoProgressLayout.setTimeText(formattedTime((long) currentTime) + " / " + formattedTime((long) mDuration));
                    }

                }
                if (mSeekBarProgress != null) {
                    mSeekBarProgress.setProgress(progress);
                }
            }
        });
    }

    /**
     * 初始化view
     */
    private void initView(Context context) {
        LayoutInflater.from(context).inflate(R.layout.superplayer_vod_player_window, this);
        mLayoutTop = (LinearLayout) findViewById(R.id.superplayer_rl_top);
        mLayoutTop.setOnClickListener(this);
        mLayoutBottom = (LinearLayout) findViewById(R.id.superplayer_ll_bottom);
        mLayoutBottom.setOnClickListener(this);
        mLayoutReplay = (LinearLayout) findViewById(R.id.superplayer_ll_replay);
        mTvTitle = (TextView) findViewById(R.id.superplayer_tv_title);
        mIvPause = (ImageView) findViewById(R.id.superplayer_iv_pause);
        zdyIvPause = findViewById(R.id.zdy_iv_pause);
        mTvCurrent = (TextView) findViewById(R.id.superplayer_tv_current);
        mTvDuration = (TextView) findViewById(R.id.superplayer_tv_duration);
        mSeekBarProgress = (PointSeekBar) findViewById(R.id.superplayer_seekbar_progress_crude);
        xSeekBarProgress = findViewById(R.id.superplayer_seekbar_progress);
        mSeekBarProgress.setProgress(0);
        mSeekBarProgress.setMax(100);
        mSeekBarProgress.setAlpha(0);
        xSeekBarProgress.setProgress(0);
        xSeekBarProgress.setMax(100);

        mSeekBarProgress.setMoveEventListener(new PointSeekBar.MoveEventListener() {
            @Override
            public void moveEvent() {
                mSeekBarProgress.setAlpha(1);
                xSeekBarProgress.setAlpha(0);
            }
        });

        mSeekBarProgress.setUpEventListener(new PointSeekBar.UpEventListener() {
            @Override
            public void upEvent() {
                new Thread(){
                    @Override
                    public void run() {
                        try {
                            sleep(300);
                            mSeekBarProgress.setAlpha(0);
                            xSeekBarProgress.setAlpha(1);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }.start();
            }
        });

        mLoadBar = findViewById(R.id.superplayer_loadbar_progress);
        mLoadBar.setProgress(100);
        mLoadBar.setMax(100);
        windowPlayerRoot = findViewById(R.id.window_player_root);

        windowPlayerRoot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //双击点赞
                if (null != mDoubleClick) {
                    mDoubleClick.onDoubleClick(item);
                }

                if (null != mDoubleClickxksh) {
                    mDoubleClickxksh.onDoubleClickxksh(item);
                }

                if (null != mDoubleClickDetail) {
                    mDoubleClickDetail.onDoubleClickDetail(item);
                }
            }
        });

        mIvFullScreen = (ImageView) findViewById(R.id.superplayer_iv_fullscreen);
        mTvBackToLive = (TextView) findViewById(R.id.superplayer_tv_back_to_live);
        mPbLiveLoading = (ProgressBar) findViewById(R.id.superplayer_pb_live);

        mTvBackToLive.setOnClickListener(this);
        mIvPause.setOnClickListener(this);
        zdyIvPause.setOnClickListener(this);
        mIvFullScreen.setOnClickListener(this);
        mLayoutTop.setOnClickListener(this);
        mLayoutReplay.setOnClickListener(this);

//        mSeekBarProgress.setOnSeekBarChangeListener(this);

        mGestureVolumeBrightnessProgressLayout = (VolumeBrightnessProgressLayout) findViewById(R.id.superplayer_gesture_progress);

        mGestureVideoProgressLayout = (VideoProgressLayout) findViewById(R.id.superplayer_video_progress_layout);

        mBackground = (ImageView) findViewById(R.id.superplayer_small_iv_background);
        setBackground(mBackgroundBmp);

        mIvWatermark = (ImageView) findViewById(R.id.superplayer_small_iv_water_mark);
    }

    public void setDataDTO(DataDTO mItem, DataDTO previousDTO) {
        this.item = mItem;
        this.mPreviousDTO = previousDTO;
    }

    public DataDTO getDataDTO() {
        return item;
    }

    public void setIsTurnPages(boolean isTurnPages) {
        this.mIsTurnPage = isTurnPages;
    }

    public void setIsVideoDetailTurnPage(boolean isVideoDetailTurnPage) {
        this.mIsVideoDetailTurnPage = isVideoDetailTurnPage;
    }


    public void setManager(ViewPagerLayoutManager manager) {
        this.myManager = manager;
    }

    public void setViewpager(NoScrollViewPager viewpager) {
        this.mViewpager = viewpager;
    }

    /**
     * 记录上报时的播放时长
     *
     * @param duration
     */
    public void setRecordDuration(long duration) {
        this.reportDuration = duration;
    }

    public long getRecordDuration() {
        return reportDuration;
    }

    public void setVideoDetailReportDuration(long duration) {
        this.videoDetailReportDuration = duration;
    }

    public long getVideoDetailReportDuration() {
        return videoDetailReportDuration;
    }


    /**
     * 切换播放状态
     * <p>
     * 双击和点击播放/暂停按钮会触发此方法
     */
    public void togglePlayState() {
        switch (mCurrentPlayState) {
            case PAUSE:
            case END:
                if (mControllerCallback != null) {
                    mControllerCallback.onResume();
                }
                break;
            case PLAYING:
            case LOADING:
                if (mControllerCallback != null) {
                    mControllerCallback.onPause();
                }
                mLayoutReplay.setVisibility(View.GONE);
                break;
        }
        show();
    }

    /**
     * 切换自身的可见性
     */
    public void toggle() {
        if (isShowing) {
            hide();
        } else {
            show();
            if (mHideViewRunnable != null) {
                removeCallbacks(mHideViewRunnable);
                postDelayed(mHideViewRunnable, Contants.delayMillis);
            }
        }
    }

    /**
     * 设置本身的进度条展示与否
     */
    public void setIsSelfProgress(boolean isSelfProgress) {
        this.isShowSelfProgress = isSelfProgress;
    }

    /**
     * 设置水印
     *
     * @param bmp 水印图
     * @param x   水印的x坐标
     * @param y   水印的y坐标
     */
    @Override
    public void setWatermark(final Bitmap bmp, float x, float y) {
        mWaterMarkBmp = bmp;
        mWaterMarkBmpX = x;
        mWaterMarkBmpY = y;
        if (bmp != null) {
            this.post(new Runnable() {
                @Override
                public void run() {
                    int width = WindowPlayer.this.getWidth();
                    int height = WindowPlayer.this.getHeight();

                    int x = (int) (width * mWaterMarkBmpX) - bmp.getWidth() / 2;
                    int y = (int) (height * mWaterMarkBmpY) - bmp.getHeight() / 2;

                    mIvWatermark.setX(x);
                    mIvWatermark.setY(y);

                    mIvWatermark.setVisibility(VISIBLE);
                    setBitmap(mIvWatermark, bmp);
                }
            });
        } else {
            mIvWatermark.setVisibility(GONE);
        }
    }

    /**
     * 显示控件
     */
    @Override
    public void show() {
        isShowing = true;
//        mLayoutTop.setVisibility(View.VISIBLE);
//        mLayoutBottom.setVisibility(View.VISIBLE);

        if (mPlayType == SuperPlayerDef.PlayerType.LIVE_SHIFT) {
            mTvBackToLive.setVisibility(View.VISIBLE);
        }
    }

    /**
     * 隐藏控件
     */
    @Override
    public void hide() {
        isShowing = false;
//        mLayoutTop.setVisibility(View.GONE);
//        mLayoutBottom.setVisibility(View.GONE);

        if (mPlayType == SuperPlayerDef.PlayerType.LIVE_SHIFT) {
            mTvBackToLive.setVisibility(View.GONE);
        }
    }

    public UpdatePlayStateCallback updatePlayStateCallback;

    public interface UpdatePlayStateCallback {
        void UpdatePlayStateCallback(SuperPlayerDef.PlayerState playState);
    }

    public void setUpdatePlayStateCallback(UpdatePlayStateCallback callback) {
        this.updatePlayStateCallback = callback;
    }

    @Override
    public void updatePlayState(SuperPlayerDef.PlayerState playState) {
        if (null != updatePlayStateCallback) {
            updatePlayStateCallback.UpdatePlayStateCallback(playState);
        }

        switch (playState) {
            case PLAYING:
                mReportVodStartTime = System.currentTimeMillis();
                mIvPause.setImageResource(R.drawable.superplayer_ic_vod_pause_normal);
                zdyIvPause.setVisibility(GONE);
                toggleView(mPbLiveLoading, false);
                toggleView(mLayoutReplay, false);
                break;
            case LOADING:
                if (null != item) {
                    if (mIsTurnPage) {
                        BuriedPointModelManager.reportPlayTime(mReportVodStartTime, mPreviousDTO.getId() + "", mPreviousDTO.getTitle(), "",
                                "", "", "", mPreviousDTO.getIssueTimeStamp());

                    } else {
                        BuriedPointModelManager.reportPlayTime(mReportVodStartTime, item.getId() + "", item.getTitle(), "",
                                "", "", "", item.getIssueTimeStamp());
                    }
                } else if (null != recordsDTO) {
                    if (mIsVideoDetailTurnPage) {
                        BuriedPointModelManager.reportPlayTime(mReportVodStartTime, mPreviousRecordsDTO.getId() + "", mPreviousRecordsDTO.getTitle(), "",
                                "", "", "", mPreviousRecordsDTO.getIssueTimeStamp());

                    } else {
                        BuriedPointModelManager.reportPlayTime(mReportVodStartTime, recordsDTO.getId() + "", recordsDTO.getTitle(), "",
                                "", "", "", recordsDTO.getIssueTimeStamp());
                    }
                }

                mIsTurnPage = false;
                mReportVodStartTime = -1;
                mIvPause.setImageResource(R.drawable.superplayer_ic_vod_pause_normal);
                zdyIvPause.setVisibility(GONE);
//                toggleView(mPbLiveLoading, true);
                toggleView(mLayoutReplay, false);
                break;
            case PAUSE:
                if (null != item) {
                    BuriedPointModelManager.reportPlayTime(mReportVodStartTime, item.getId() + "", item.getTitle(), "",
                            "", "", "", item.getIssueTimeStamp());
                } else if (null != recordsDTO) {
                    BuriedPointModelManager.reportPlayTime(mReportVodStartTime, recordsDTO.getId() + "", recordsDTO.getTitle(), "",
                            "", "", "", recordsDTO.getIssueTimeStamp());
                }

                mIsTurnPage = false;
                mReportVodStartTime = -1;
                mIvPause.setImageResource(R.drawable.superplayer_ic_vod_play_normal);
                zdyIvPause.setVisibility(VISIBLE);
                toggleView(mPbLiveLoading, false);
                toggleView(mLayoutReplay, false);
                break;
            case END:
                if (null != item) {
                    if (mIsTurnPage) {
                        double reportEndTime = System.currentTimeMillis();
                        double diff = (reportEndTime - mReportVodStartTime) / 1000;
                        if (null != mPreviousDTO) {
                            String jsonString = BuriedPointModelManager.getVideoPlayComplate(mPreviousDTO.getId() + "", mPreviousDTO.getTitle(), "", "", "", ""
                                    , mPreviousDTO.getIssueTimeStamp(), Constants.CONTENT_TYPE, diff + "");
                        }
                    } else {
                        if (null != item) {
                            String jsonString = BuriedPointModelManager.getVideoPlayComplate(item.getId() + "", item.getTitle(), "", "", "", ""
                                    , item.getIssueTimeStamp(), Constants.CONTENT_TYPE, mDuration + "");
                        }
                    }
                }

                mIsTurnPage = false;
                mIvPause.setImageResource(R.drawable.superplayer_ic_vod_play_normal);
                zdyIvPause.setVisibility(GONE);
                toggleView(mPbLiveLoading, false);
                toggleView(mLayoutReplay, true);
                break;
        }
        mCurrentPlayState = playState;
    }


    /**
     * 更新视频名称
     *
     * @param title 视频名称
     */
    @Override
    public void updateTitle(String title) {
        mTvTitle.setText(title);
    }

    /**
     * 更新视频播放进度
     *
     * @param current  当前进度(秒)
     * @param duration 视频总时长(秒)
     */
    @Override
    public void updateVideoProgress(long current, long duration) {
        mProgress = current < 0 ? 0 : current;
        mDuration = duration < 0 ? 0 : duration;
        if (mDuration != 0) {
            percent = mProgress / mDuration;  //需要记录每一次上报时的播放进度和播放进度百分比
        }

        mTvCurrent.setText(formattedTime(mProgress));
        float percentage = mDuration > 0 ? ((float) mProgress / (float) mDuration) : 1.0f;
        if (mProgress == 0) {
            mLivePushDuration = 0;
            percentage = 0;
        }
        if (mPlayType == SuperPlayerDef.PlayerType.LIVE || mPlayType == SuperPlayerDef.PlayerType.LIVE_SHIFT) {
            mLivePushDuration = mLivePushDuration > mProgress ? mLivePushDuration : mProgress;
            long leftTime = mDuration - mProgress;
            mDuration = mDuration > MAX_SHIFT_TIME ? MAX_SHIFT_TIME : mDuration;
            percentage = 1 - (float) leftTime / (float) mDuration;
        }

        if (percentage >= 0 && percentage <= 1) {
            int progress = Math.round(percentage * mSeekBarProgress.getMax());
            if (!mIsChangingSeekBarProgress) {
                if (mPlayType == SuperPlayerDef.PlayerType.LIVE) {
                    mSeekBarProgress.setProgress(mSeekBarProgress.getMax());
                    xSeekBarProgress.setProgress(xSeekBarProgress.getMax());
                } else {
                    mSeekBarProgress.setProgress(progress);
                    xSeekBarProgress.setProgress(progress);
                }
            }
            mTvDuration.setText(formattedTime(mDuration));
        }
    }

    @Override
    public void updatePlayType(SuperPlayerDef.PlayerType type) {
        mPlayType = type;
        switch (type) {
            case VOD:
                mTvBackToLive.setVisibility(View.GONE);
//                mTvDuration.setVisibility(View.VISIBLE);
                break;
            case LIVE:
                mTvBackToLive.setVisibility(View.GONE);
//                mTvDuration.setVisibility(View.GONE);
                mSeekBarProgress.setProgress(100);
                xSeekBarProgress.setProgress(100);
                break;
            case LIVE_SHIFT:
                if (mLayoutBottom.getVisibility() == VISIBLE) {
                    mTvBackToLive.setVisibility(View.VISIBLE);
                }
//                mTvDuration.setVisibility(View.GONE);
                break;
        }
    }

    /**
     * 设置背景
     *
     * @param bitmap 背景图
     */
    @Override
    public void setBackground(final Bitmap bitmap) {
        this.post(new Runnable() {
            @Override
            public void run() {
                if (bitmap == null) {
                    return;
                }
                if (mBackground == null) {
                    mBackgroundBmp = bitmap;
                } else {
                    setBitmap(mBackground, mBackgroundBmp);
                }
            }
        });
    }

    /**
     * 设置目标ImageView显示的图片
     */
    private void setBitmap(ImageView view, Bitmap bitmap) {
        if (view == null || bitmap == null) return;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            view.setBackground(new BitmapDrawable(getContext().getResources(), bitmap));
        } else {
            view.setBackgroundDrawable(new BitmapDrawable(getContext().getResources(), bitmap));
        }
    }

    /**
     * 显示背景
     */
    @Override
    public void showBackground() {
        post(new Runnable() {
            @Override
            public void run() {
                ValueAnimator alpha = ValueAnimator.ofFloat(0.0f, 1);
                alpha.setDuration(500);
                alpha.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                    @Override
                    public void onAnimationUpdate(ValueAnimator animation) {
                        float value = (Float) animation.getAnimatedValue();
                        mBackground.setAlpha(value);
                        if (value == 1) {
                            mBackground.setVisibility(VISIBLE);
                        }
                    }
                });
                alpha.start();
            }
        });
    }

    /**
     * 隐藏背景
     */
    @Override
    public void hideBackground() {
        post(new Runnable() {
            @Override
            public void run() {
                if (mBackground.getVisibility() != View.VISIBLE) {
                    return;
                }
                ValueAnimator alpha = ValueAnimator.ofFloat(1.0f, 0.0f);
                alpha.setDuration(500);
                alpha.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                    @Override
                    public void onAnimationUpdate(ValueAnimator animation) {
                        float value = (Float) animation.getAnimatedValue();
                        mBackground.setAlpha(value);
                        if (value == 0) {
                            mBackground.setVisibility(GONE);
                        }
                    }
                });
                alpha.start();
            }
        });
    }

    /**
     * 重写触摸事件监听，实现手势调节亮度、音量以及播放进度
     */
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (mGestureDetector != null) {
            mGestureDetector.onTouchEvent(event);
        }

        if (event.getAction() == MotionEvent.ACTION_UP && mVideoGestureDetector != null && mVideoGestureDetector.isVideoProgressModel()) {
            int progress = mVideoGestureDetector.getVideoProgress();
            if (progress > mSeekBarProgress.getMax()) {
                progress = mSeekBarProgress.getMax();
            }
            if (progress < 0) {
                progress = 0;
            }
            mSeekBarProgress.setProgress(progress);
            xSeekBarProgress.setProgress(progress);

            int seekTime;
            float percentage = progress * 1.0f / mSeekBarProgress.getMax();
            if (mPlayType == SuperPlayerDef.PlayerType.LIVE || mPlayType == SuperPlayerDef.PlayerType.LIVE_SHIFT) {
                if (mLivePushDuration > MAX_SHIFT_TIME) {
                    seekTime = (int) (mLivePushDuration - MAX_SHIFT_TIME * (1 - percentage));
                } else {
                    seekTime = (int) (mLivePushDuration * percentage);
                }
            } else {
                seekTime = (int) (percentage * mDuration);
            }
            if (mControllerCallback != null) {
                mControllerCallback.onSeekTo(seekTime);
            }
            mIsChangingSeekBarProgress = false;
        }

        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            removeCallbacks(mHideViewRunnable);
        } else if (event.getAction() == MotionEvent.ACTION_UP) {
            postDelayed(mHideViewRunnable, Contants.delayMillis);
        }
        return true;
    }

    public IsReplayClick isReplayClick;

    public interface IsReplayClick {
        void getReplayClick();
    }

    public void setIsReplayClick(IsReplayClick callBack) {
        this.isReplayClick = callBack;
    }


    /**
     * 设置点击事件监听
     */
    @Override
    public void onClick(View view) {
        if (System.currentTimeMillis() - mLastClickTime < 300) { //限制点击频率
            return;
        }
        mLastClickTime = System.currentTimeMillis();
        int id = view.getId();
        if (id == R.id.superplayer_rl_top) { //顶部标题栏
            if (mControllerCallback != null) {
                mControllerCallback.onBackPressed(SuperPlayerDef.PlayerMode.WINDOW);
            }
        } else if (id == R.id.superplayer_iv_pause || id == R.id.zdy_iv_pause) { //暂停\播放按钮
            togglePlayState();
            if (mHideViewRunnable != null) {
                removeCallbacks(mHideViewRunnable);
                postDelayed(mHideViewRunnable, Contants.delayMillis);
            }
        } else if (id == R.id.superplayer_iv_fullscreen) { //全屏按钮
            if (mControllerCallback != null) {
                mControllerCallback.onSwitchPlayMode(SuperPlayerDef.PlayerMode.FULLSCREEN);
            }
        } else if (id == R.id.superplayer_ll_replay) { //重播按钮
//            isReplayClick.getReplayClick();
            if (mControllerCallback != null) {
                mControllerCallback.onResume();
            }
        } else if (id == R.id.superplayer_tv_back_to_live) { //返回直播按钮
            if (mControllerCallback != null) {
                mControllerCallback.onResumeLive();
            }
        }
    }

//    @Override
//    public void onProgressChanged(PointSeekBar seekBar, int progress, boolean fromUser) {
//        if (mGestureVideoProgressLayout != null && fromUser) {
//            mGestureVideoProgressLayout.show();
//            float percentage = ((float) progress) / seekBar.getMax();
//            float currentTime = (mDuration * percentage);
//            if (mPlayType == SuperPlayerDef.PlayerType.LIVE || mPlayType == SuperPlayerDef.PlayerType.LIVE_SHIFT) {
//                if (mLivePushDuration > MAX_SHIFT_TIME) {
//                    currentTime = (int) (mLivePushDuration - MAX_SHIFT_TIME * (1 - percentage));
//                } else {
//                    currentTime = mLivePushDuration * percentage;
//                }
//                mGestureVideoProgressLayout.setTimeText(formattedTime((long) currentTime));
//            } else {
//
//                mGestureVideoProgressLayout.setTimeText(formattedTime((long) currentTime) + " / " + formattedTime((long) mDuration));
//            }
//            mGestureVideoProgressLayout.setProgress(progress);
//        }
//    }
//
//
//    @Override
//    public void onStartTrackingTouch(PointSeekBar seekBar) {
//        removeCallbacks(mHideViewRunnable);
//        mViewpager.setScroll(false);
//        myManager.setCanScoll(false);
//        Log.e("Touch", "onStartTrackingTouch------");
//    }
//
//    @Override
//    public void onStopTrackingTouch(PointSeekBar seekBar) {
//        int curProgress = seekBar.getProgress();
//        int maxProgress = seekBar.getMax();
//        Log.e("Touch", "onStopTrackingTouch------");
//        if (mTargetPlayerMode == SuperPlayerDef.PlayerMode.WINDOW) {
//            mViewpager.setScroll(true);
//            myManager.setCanScoll(true);
//        } else {
//            mViewpager.setScroll(false);
//            myManager.setCanScoll(false);
//        }
//
//        switch (mPlayType) {
//            case VOD:
//                if (curProgress >= 0 && curProgress <= maxProgress) {
//                    // 关闭重播按钮
//                    toggleView(mLayoutReplay, false);
//                    float percentage = ((float) curProgress) / maxProgress;
//                    int position = (int) (mDuration * percentage);
//                    if (mControllerCallback != null) {
//                        mControllerCallback.onSeekTo(position);
//                        mControllerCallback.onResume();
//                    }
//                }
//                break;
//            case LIVE:
//            case LIVE_SHIFT:
//                toggleView(mPbLiveLoading, true);
//                int seekTime = (int) (mLivePushDuration * curProgress * 1.0f / maxProgress);
//                if (mLivePushDuration > MAX_SHIFT_TIME) {
//                    seekTime = (int) (mLivePushDuration - MAX_SHIFT_TIME * (maxProgress - curProgress) * 1.0f / maxProgress);
//                }
//                if (mControllerCallback != null) {
//                    mControllerCallback.onSeekTo(seekTime);
//                }
//                break;
//        }
//        postDelayed(mHideViewRunnable, Contants.delayMillis);
//    }
}
