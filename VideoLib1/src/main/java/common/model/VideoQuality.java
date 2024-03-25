package common.model;

import android.support.annotation.Keep;

/**
 * Created by yuejiaoli on 2018/7/7.
 * <p>
 * 清晰度
 */
@Keep
public class VideoQuality {

    public int index;
    public int bitrate;
    public String name;
    public String title;
    public String url;

    public VideoQuality() {
    }

    public VideoQuality(int index, String title, String url) {
        this.index = index;
        this.title = title;
        this.url = url;
    }
}
