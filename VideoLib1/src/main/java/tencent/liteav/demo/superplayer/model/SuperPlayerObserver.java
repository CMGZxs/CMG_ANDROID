package tencent.liteav.demo.superplayer.model;

import com.tencent.rtmp.TXLivePlayer;
import com.tencent.rtmp.TXVodPlayer;

import java.util.List;

import common.model.PlayImageSpriteInfo;
import common.model.PlayKeyFrameDescInfo;
import common.model.VideoQuality;
import tencent.liteav.demo.superplayer.SuperPlayerDef;

public abstract class SuperPlayerObserver {

    /**
     * 开始播放
     * @param name 当前视频名称
     */
    public void onPlayBegin(String name) {}

    /**
     * 播放暂停
     */
    public void onPlayPause() {}

    /**
     * 播放器停止
     */
    public void onPlayStop() {}

    /**
     * 播放器进入Loading状态
     */
    public void onPlayLoading(String name) {}

    /**
     * 播放进度回调
     *
     * @param current
     * @param duration
     */
    public void onPlayProgress(long current, long duration) {}

    public void onSeek(int position) {}

    public void onSwitchStreamStart(boolean success, SuperPlayerDef.PlayerType playerType, VideoQuality quality){}

    public void onSwitchStreamEnd(boolean success, SuperPlayerDef.PlayerType playerType, VideoQuality quality){}

    public void onError(int code, String message) {}

    public void onPlayerTypeChange(SuperPlayerDef.PlayerType playType) {}

    public void onPlayTimeShiftLive(TXLivePlayer player, String url) {}

    public void onPlayTimeShiftVod(TXVodPlayer player, String url) {}

    public void onVideoQualityListChange(List<VideoQuality> videoQualities, VideoQuality defaultVideoQuality) {}

    public void onVideoImageSpriteAndKeyFrameChanged(PlayImageSpriteInfo info, List<PlayKeyFrameDescInfo> list) {}
}
