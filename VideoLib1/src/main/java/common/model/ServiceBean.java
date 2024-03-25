package common.model;

import android.support.annotation.Keep;

import java.io.Serializable;
import java.util.ArrayList;
@Keep
public class ServiceBean implements Serializable {

    /**
     * children : []
     * servBrief : 实时路况
     * servId : 8
     * servName : 实时路况
     * skipUrl : http://csjj.daxiangw.com/reception/service/Guidance
     * thumUrl :
     */

    private String servBrief;
    private String servId;
    private String servName;
    private String skipUrl;
    private String thumUrl;
    private String showForm = "";
    private String shareH5;
    private String shareImg;
    private String shareText;
    private String shareTitle;

    public String getIsNew() {
        return isNew;
    }

    public void setIsNew(String isNew) {
        this.isNew = isNew;
    }

    private String isNew;

    public String getParamType() {
        return paramType;
    }

    public void setParamType(String paramType) {
        this.paramType = paramType;
    }

    private String paramType;
    private ArrayList<ServiceBean> children;

    public String getServBrief() {
        return servBrief;
    }

    public void setServBrief(String servBrief) {
        this.servBrief = servBrief;
    }

    public String getServId() {
        return servId;
    }

    public void setServId(String servId) {
        this.servId = servId;
    }

    public String getServName() {
        return servName;
    }

    public void setServName(String servName) {
        this.servName = servName;
    }

    public String getSkipUrl() {
        return skipUrl;
    }

    public void setSkipUrl(String skipUrl) {
        this.skipUrl = skipUrl;
    }

    public String getThumUrl() {
        return thumUrl;
    }

    public void setThumUrl(String thumUrl) {
        this.thumUrl = thumUrl;
    }

    public ArrayList<ServiceBean> getChildren() {
        return children;
    }

    public void setChildren(ArrayList<ServiceBean> children) {
        this.children = children;
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

    public String getShowForm() {
        return showForm;
    }

    public void setShowForm(String showForm) {
        this.showForm = showForm;
    }
}
