package common.model;

import android.support.annotation.Keep;

import java.io.Serializable;
import java.util.concurrent.CopyOnWriteArrayList;
@Keep
public class LiveSceneBean implements Serializable {

    private String listThumUrl;
    private String isLookBack;
    private String sceneStartTime;
    private String sceneThumUrl;
    private String sceneTitle;
    private String sceneUrl;
    private String sceneType;
    private String shareH5;
    private String shareImg;
    private String shareText;
    private String shareTitle;



    private CopyOnWriteArrayList<LiveSceneBean> ls;

    public String getListThumUrl() {
        return listThumUrl;
    }

    public void setListThumUrl(String listThumUrl) {
        this.listThumUrl = listThumUrl;
    }



    public String getSceneStartTime() {
        return sceneStartTime;
    }

    public void setSceneStartTime(String sceneStartTime) {
        this.sceneStartTime = sceneStartTime;
    }

    public String getSceneThumUrl() {
        return sceneThumUrl;
    }

    public void setSceneThumUrl(String sceneThumUrl) {
        this.sceneThumUrl = sceneThumUrl;
    }

    public String getSceneTitle() {
        return sceneTitle;
    }

    public void setSceneTitle(String sceneTitle) {
        this.sceneTitle = sceneTitle;
    }

    public String getSceneUrl() {
        return sceneUrl;
    }

    public void setSceneUrl(String sceneUrl) {
        this.sceneUrl = sceneUrl;
    }

    public CopyOnWriteArrayList<LiveSceneBean> getLs() {
        return ls;
    }

    public void setLs(CopyOnWriteArrayList<LiveSceneBean> ls) {
        this.ls = ls;
    }

    public String getIsLookBack() {
        return isLookBack;
    }

    public void setIsLookBack(String isLookBack) {
        this.isLookBack = isLookBack;
    }

    public String getSceneType() {
        return sceneType;
    }

    public void setSceneType(String sceneType) {
        this.sceneType = sceneType;
    }

    public String getShareH5() {
        return shareH5;
    }

    public void setShareH5(String shareH5) {
        this.shareH5 = shareH5;
    }

    public String getShareImg() {
        return shareImg;
    }

    public void setShareImg(String shareImg) {
        this.shareImg = shareImg;
    }

    public String getShareText() {
        return shareText;
    }

    public void setShareText(String shareText) {
        this.shareText = shareText;
    }

    public String getShareTitle() {
        return shareTitle;
    }

    public void setShareTitle(String shareTitle) {
        this.shareTitle = shareTitle;
    }
}
