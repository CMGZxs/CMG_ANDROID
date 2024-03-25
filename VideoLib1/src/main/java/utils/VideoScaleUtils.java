package utils;

import android.app.Activity;
import android.text.TextUtils;
import android.view.animation.AnimationSet;
import android.view.animation.ScaleAnimation;
import android.view.animation.TranslateAnimation;

import common.utils.ScreenUtils;
import tencent.liteav.demo.superplayer.SuperPlayerView;


public class VideoScaleUtils {
    private long animationDuration = 400l;

    public static VideoScaleUtils videoScaleUtils;

    private VideoScaleUtils() {
    }

    public static VideoScaleUtils getInstance() {
        if (videoScaleUtils == null) {
            synchronized (VideoScaleUtils.class) {
                if (videoScaleUtils == null) {
                    videoScaleUtils = new VideoScaleUtils();
                }
            }
        }
        return videoScaleUtils;
    }

    /**
     * 视频缩放
     *
     * @param state      0为评论弹窗消失，1为评论弹窗弹出
     * @param playerView
     */
    public void videoTranslationScale(Activity activity, String videoLx, int state, final SuperPlayerView playerView) {
        float scale = 0.7f;
        float halfScale = 0.5f;

        if (state == 1) {
            if (TextUtils.equals("2", videoLx)) {
                setTranslationAnimation(0, -(ScreenUtils.getScreenHeight(activity) * scale), playerView);
            } else if (TextUtils.equals("1", videoLx)) {
                setScaleAndTranslationAnimation(0, ScreenUtils.getScreenWidth(activity) / 4,
                        0, -(ScreenUtils.getScreenHeight(activity) * 0.2f),
                        1f, halfScale, 1f, halfScale, playerView);
            } else {
                setScaleAndTranslationAnimation(0, ScreenUtils.getScreenWidth(activity) / 4,
                        0, -(ScreenUtils.getScreenHeight(activity) * 0.2f),
                        1f, halfScale, 1f, halfScale, playerView);
            }
            ScreenUtils.setStatusBarVisibility(activity, false);
        } else {
            if (TextUtils.equals("2", videoLx)) {
                setTranslationAnimation(-(ScreenUtils.getScreenHeight(activity) * 0.7f), 0, playerView);
            } else if (TextUtils.equals("1", videoLx)) {
                setScaleAndTranslationAnimation(ScreenUtils.getScreenWidth(activity) / 4, 0, -(ScreenUtils.getScreenHeight(activity) * 0.2f),
                        0, halfScale, 1f, halfScale, 1f, playerView);
            } else {
                setScaleAndTranslationAnimation(ScreenUtils.getScreenWidth(activity) / 4, 0, -(ScreenUtils.getScreenHeight(activity) * 0.2f),
                        0, halfScale, 1f, halfScale, 1f, playerView);
            }
            ScreenUtils.setStatusBarVisibility(activity, true);
        }
    }

    public void setTranslationAnimation(float fromYDelta, float toDelta, SuperPlayerView playerView) {
        playerView.clearAnimation();
        TranslateAnimation translateAnimation = new TranslateAnimation(0, 0, fromYDelta, toDelta);
        translateAnimation.setFillAfter(true);
        translateAnimation.setDuration(animationDuration);
        playerView.setAnimation(translateAnimation);
    }

    public void setScaleAndTranslationAnimation(float translateFromX, float translateToX, float translateFromY, float translateToY,
                                                float scaleFromX, float scaleToX, float scaleFromY, float scaleToY, SuperPlayerView playerView) {
        TranslateAnimation translateAnimation = new TranslateAnimation(translateFromX, translateToX, translateFromY, translateToY);
        ScaleAnimation scaleAnimation = new ScaleAnimation(scaleFromX, scaleToX, scaleFromY, scaleToY);
        AnimationSet animationSet = new AnimationSet(true);
        animationSet.addAnimation(scaleAnimation);
        animationSet.addAnimation(translateAnimation);
        animationSet.setDuration(animationDuration);
        animationSet.setFillAfter(true);
        playerView.startAnimation(animationSet);
    }
}
