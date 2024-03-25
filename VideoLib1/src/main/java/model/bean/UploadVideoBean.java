package model.bean;

import android.text.TextUtils;

import android.support.annotation.Keep;

@Keep
public class UploadVideoBean {

    private String url;
    private String key;
    private String hash;
    private String size;
    private String coverImageUrl;
    private String coverImageKey;
    private String width;
    private String height;
    private String orientation;
    private String duration;
    private String expires;
    private String contentType;
    private String isImage;
    private String isVideo;
    private String status;

    public String getUrl() {
        if (TextUtils.isEmpty(url)) {
            return "";
        }
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getKey() {
        if (TextUtils.isEmpty(key)) {
            return "";
        }
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getHash() {
        if (TextUtils.isEmpty(hash)) {
            return "";
        }
        return hash;
    }

    public void setHash(String hash) {
        this.hash = hash;
    }

    public String getSize() {
        if (TextUtils.isEmpty(size)) {
            return "";
        }
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getCoverImageUrl() {
        if (TextUtils.isEmpty(coverImageUrl)) {
            return "";
        }
        return coverImageUrl;
    }

    public void setCoverImageUrl(String coverImageUrl) {
        this.coverImageUrl = coverImageUrl;
    }

    public String getCoverImageKey() {
        if (TextUtils.isEmpty(coverImageKey)) {
            return "";
        }
        return coverImageKey;
    }

    public void setCoverImageKey(String coverImageKey) {
        this.coverImageKey = coverImageKey;
    }

    public String getWidth() {
        if (TextUtils.isEmpty(width)) {
            return "";
        }
        return width;
    }

    public void setWidth(String width) {
        this.width = width;
    }

    public String getHeight() {
        if (TextUtils.isEmpty(height)) {
            return "";
        }
        return height;
    }

    public void setHeight(String height) {
        this.height = height;
    }

    public String getOrientation() {
        if (TextUtils.isEmpty(orientation)) {
            return "";
        }
        return orientation;
    }

    public void setOrientation(String orientation) {
        this.orientation = orientation;
    }

    public String getDuration() {
        if (TextUtils.isEmpty(duration)) {
            return "";
        }
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public String getExpires() {
        if (TextUtils.isEmpty(expires)) {
            return "";
        }
        return expires;
    }

    public void setExpires(String expires) {
        this.expires = expires;
    }

    public String getContentType() {
        if (TextUtils.isEmpty(contentType)) {
            return "";
        }
        return contentType;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

    public String getIsImage() {
        if (TextUtils.isEmpty(isImage)) {
            return "";
        }
        return isImage;
    }

    public void setIsImage(String isImage) {
        this.isImage = isImage;
    }

    public String getIsVideo() {
        if (TextUtils.isEmpty(isVideo)) {
            return "";
        }
        return isVideo;
    }

    public void setIsVideo(String isVideo) {
        this.isVideo = isVideo;
    }

    public String getStatus() {
        if (TextUtils.isEmpty(status)) {
            return "";
        }
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
