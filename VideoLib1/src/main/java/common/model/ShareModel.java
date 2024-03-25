package common.model;

import android.support.annotation.Keep;

@Keep
public class ShareModel {
    private int shareRes;
    private String shareTip;
    private String shareTitle;
    private String shareUrl;
    private String shareContent;
    private String shareImg;

    public int getShareRes() {
        return shareRes;
    }

    public void setShareRes(int shareRes) {
        this.shareRes = shareRes;
    }

    public String getShareTip() {
        return shareTip;
    }

    public void setShareTip(String shareTip) {
        this.shareTip = shareTip;
    }

    public String getShareTitle() {
        return shareTitle;
    }

    public void setShareTitle(String shareTitle) {
        this.shareTitle = shareTitle;
    }

    public String getShareUrl() {
        return shareUrl;
    }

    public void setShareUrl(String shareUrl) {
        this.shareUrl = shareUrl;
    }

    public String getShareContent() {
        return shareContent;
    }

    public void setShareContent(String shareContent) {
        this.shareContent = shareContent;
    }

    public String getShareImg() {
        return shareImg;
    }

    public void setShareImg(String shareImg) {
        this.shareImg = shareImg;
    }
}
