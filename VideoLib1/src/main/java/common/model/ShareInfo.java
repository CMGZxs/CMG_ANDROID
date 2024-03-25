package common.model;

import android.support.annotation.Keep;

import java.io.Serializable;

/**
 * 分享四要素
 */
@Keep
public class ShareInfo implements Serializable {
    /**
     * @param shareH5 分享地址
     * @param shareImageUrl 图片地址
     * @param shareBrief 简介
     * @param shareTitle 分享标题
     * @param platform 分享平台
     */
    public String link, imgUrl, content, title, platform;

    public static ShareInfo getInstance(String link, String imgUrl, String content, String title, String platform) {
        return new ShareInfo(link, imgUrl, content, title, platform);
    }

    public ShareInfo() {

    }

    public ShareInfo(String link, String imgUrl, String content, String title, String platform) {
        this.link = link;
        this.imgUrl = imgUrl;
        this.content = content;
        this.title = title;
        this.platform = platform;
    }

    public ShareInfo(String link, String imgUrl, String content, String title) {
        this.link = link;
        this.imgUrl = imgUrl;
        this.content = content;
        this.title = title;
    }

    public String getShareUrl() {
        return link;
    }

    public void setShareUrl(String link) {
        this.link = link;
    }

    public String getShareImage() {
        return imgUrl;
    }

    public void setShareImage(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public String getShareBrief() {
        return content;
    }

    public void setShareBrief(String content) {
        this.content = content;
    }

    public String getShareTitle() {
        return title;
    }

    public void setShareTitle(String title) {
        this.title = title;
    }

    public String getPlatform() {
        return platform;
    }

    public void setPlatform(String platform) {
        this.platform = platform;
    }
}
