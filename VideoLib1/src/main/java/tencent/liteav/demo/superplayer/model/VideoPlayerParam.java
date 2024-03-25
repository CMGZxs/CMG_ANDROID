package tencent.liteav.demo.superplayer.model;


import tencent.liteav.demo.superplayer.ui.player.VideoPlayerCallback;

public class VideoPlayerParam {
    public VideoPlayerCallback callBack;
    public static VideoPlayerParam param;

    private VideoPlayerParam() {
    }

    public static VideoPlayerParam getInstance() {
        if (param == null) {
            synchronized (VideoPlayerParam.class) {
                if (param == null) {
                    param = new VideoPlayerParam();
                }
            }
        }
        return param;
    }

    public void setCallBack(VideoPlayerCallback callBack) {
        this.callBack = callBack;
    }

    /**
     * 更新播放状态的时候传递播放状态
     */
    public void setVideoPlayStates(boolean states) throws Exception {
        if (callBack == null) {
            throw new Exception("获取失败,请重试");
        } else {
            callBack.setVideoPlayStates(states);
        }
    }
}
