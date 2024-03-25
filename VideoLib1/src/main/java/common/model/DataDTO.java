package common.model;


import android.support.annotation.Keep;

import java.util.ArrayList;
import java.util.List;


@Keep
public  class DataDTO {
    private String shareTitle;
    private String shareUrl;
    private String shareImageUrl;
    private String shareBrief;
    private String timeDif;
    private String issueTimeStamp;
    private String startTime;
    private Integer id;
    private String idShow;
    private String readCount;
    private String commentCount;
    private Integer commentCountShow;
    private Integer likeCountShow;
    private Integer favorCountShow;
    private Integer viewCountShow;
    private Integer playCountShow;
    private String type;
    private String title;
    private String thumbnailUrl;
    private String brief;
    private String detailUrl;
    private String externalUrl;
    private String source;
    private String keywords;
    private String tags;
    private String classification;
    private String imagesUrl;
    private String playUrl;
    private Integer playDuration;
    private String liveStatus;
    private String liveStartTime;
    private String issuerId;
    private String listStyle;
    private String issuerName;
    private String issuerImageUrl;
    private Boolean disableComment;
    private String label;
    private String orientation;
    private Boolean whetherLike;
    private Boolean whetherFavor;
    private String pId;
    private String isTop;
    private String leftTag;
    private String sort;
    private ExtendDTO extend;
    private String vernier;
    private String advert;
    private String newsId;
    private String belongActivityId;
    private String belongActivityName;
    private String belongTopicId;
    private String belongTopicName;
    private String width;
    private String height;
    private String creatorId;
    private String creatorUsername;
    private String creatorNickname;
    private String creatorHead;
    private String creatorGender;
    private String creatorCertMark;
    private String creatorCertDomain;
    private String rejectReason;
    private String endTime;
    private String attachUrl;
    private String pid;
    private Boolean isWifi;
    private Boolean oneRecommend;
    private Boolean recommendVisible;
    private Boolean isClosed;
    private String videoType;
    private String createBy;
    private boolean fullBtnIsShow;
    private String spaceStr;
    private String thirdPartyId;
    private String  thirdPartyCode;
    private String logoType;
    private String volcCategory;
    private String extendTextVisible;
    private String requestId;
    private List<VideoCollectionModel.DataDTO> collectionList = new ArrayList<>();
    private List<String> keywordsShow;
    private List<String> tagsShow;
    private String createTime;

    /**
     *  0 是自动上报事件  1 是手动上报事件
     */
    private String isAutoReportEvent;

    public String getShareTitle() {
        if (null == shareTitle) {
            return "";
        } else {
            return shareTitle;
        }
    }

    public void setShareTitle(String shareTitle) {
        this.shareTitle = shareTitle;
    }

    public String getShareUrl() {
        if (null == shareUrl) {
            return "";
        } else {
            return shareUrl;
        }
    }

    public void setShareUrl(String shareUrl) {
        this.shareUrl = shareUrl;
    }

    public String getShareImageUrl() {
        if (null == shareImageUrl) {
            return "";
        } else {
            return shareImageUrl;
        }
    }

    public void setShareImageUrl(String shareImageUrl) {
        this.shareImageUrl = shareImageUrl;
    }

    public String getShareBrief() {
        if (null == shareBrief) {
            return "";
        } else {
            return shareBrief;
        }
    }

    public void setShareBrief(String shareBrief) {
        this.shareBrief = shareBrief;
    }

    public String getTimeDif() {
        if (null == timeDif) {
            return "";
        } else {
            return timeDif;
        }
    }

    public void setTimeDif(String timeDif) {
        this.timeDif = timeDif;
    }

    public String getIssueTimeStamp() {
        if (null == issueTimeStamp) {
            return "";
        } else {
            return issueTimeStamp;
        }
    }

    public void setIssueTimeStamp(String issueTimeStamp) {
        this.issueTimeStamp = issueTimeStamp;
    }

    public String getStartTime() {
        if (null == startTime) {
            return "";
        } else {
            return startTime;
        }
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public Integer getId() {
        if (null == id) {
            return 0;
        } else {
            return id;
        }
    }

    public String getIdShow() {
        return idShow;
    }

    public void setIdShow(String idShow) {
        this.idShow = idShow;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getReadCount() {
        if (null == readCount) {
            return "";
        } else {
            return readCount;
        }
    }

    public void setReadCount(String readCount) {
        this.readCount = readCount;
    }

    public String getCommentCount() {
        if (null == commentCount) {
            return "";
        } else {
            return commentCount;
        }
    }

    public void setCommentCount(String commentCount) {
        this.commentCount = commentCount;
    }

    public Integer getCommentCountShow() {
        if (null == commentCountShow) {
            return 0;
        } else {
            return commentCountShow;
        }
    }

    public void setCommentCountShow(Integer commentCountShow) {
        this.commentCountShow = commentCountShow;
    }

    public Integer getLikeCountShow() {
        if (null == likeCountShow) {
            return 0;
        } else {
            return likeCountShow;
        }
    }

    public void setLikeCountShow(Integer likeCountShow) {
        this.likeCountShow = likeCountShow;
    }

    public Integer getFavorCountShow() {
        if (null == favorCountShow) {
            return 0;
        } else {
            return favorCountShow;
        }
    }

    public void setFavorCountShow(Integer favorCountShow) {
        this.favorCountShow = favorCountShow;
    }

    public Integer getViewCountShow() {
        if (null == viewCountShow) {
            return 0;
        } else {
            return viewCountShow;
        }
    }

    public void setViewCountShow(Integer viewCountShow) {
        this.viewCountShow = viewCountShow;
    }

    public Integer getPlayCountShow() {
        if (null == playCountShow) {
            return 0;
        } else {
            return playCountShow;
        }
    }

    public void setPlayCountShow(Integer playCountShow) {
        this.playCountShow = playCountShow;
    }

    public String getType() {
        if (null == type) {
            return "";
        } else {
            return type;
        }
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getTitle() {
        if (null == title) {
            return "";
        } else {
            return title;
        }
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getThumbnailUrl() {
        if (null == thumbnailUrl) {
            return "";
        } else {
            return thumbnailUrl;
        }
    }

    public void setThumbnailUrl(String thumbnailUrl) {
        this.thumbnailUrl = thumbnailUrl;
    }

    public String getBrief() {
        if (null == brief) {
            return "";
        } else {
            return brief;
        }
    }

    public void setBrief(String brief) {
        this.brief = brief;
    }

    public String getDetailUrl() {
        if (null == detailUrl) {
            return "";
        } else {
            return detailUrl;
        }
    }

    public void setDetailUrl(String detailUrl) {
        this.detailUrl = detailUrl;
    }

    public String getExternalUrl() {
        if (null == externalUrl) {
            return "";
        } else {
            return externalUrl;
        }
    }

    public void setExternalUrl(String externalUrl) {
        this.externalUrl = externalUrl;
    }

    public String getSource() {
        if (null == source) {
            return "";
        } else {
            return source;
        }
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getKeywords() {
        if (null == keywords) {
            return "";
        } else {
            return keywords;
        }
    }

    public void setKeywords(String keywords) {
        this.keywords = keywords;
    }

    public String getTags() {
        if (null == tags) {
            return "";
        } else {
            return tags;
        }
    }

    public void setTags(String tags) {
        this.tags = tags;
    }

    public String getClassification() {
        if (null == classification) {
            return "";
        } else {
            return classification;
        }
    }

    public void setClassification(String classification) {
        this.classification = classification;
    }

    public String getImagesUrl() {
        if (null == imagesUrl) {
            return "";
        } else {
            return imagesUrl;
        }
    }

    public void setImagesUrl(String imagesUrl) {
        this.imagesUrl = imagesUrl;
    }

    public String getPlayUrl() {
        if (null == playUrl) {
            return "";
        } else {
            return playUrl;
        }
    }

    public void setPlayUrl(String playUrl) {
        this.playUrl = playUrl;
    }

    public Integer getPlayDuration() {
        if (null == playDuration) {
            return 0;
        } else {
            return playDuration;
        }
    }

    public void setPlayDuration(Integer playDuration) {
        this.playDuration = playDuration;
    }

    public String getLiveStatus() {
        if (null == liveStatus) {
            return "";
        } else {
            return liveStatus;
        }
    }

    public void setLiveStatus(String liveStatus) {
        this.liveStatus = liveStatus;
    }

    public String getLiveStartTime() {
        if (null == liveStartTime) {
            return "";
        } else {
            return liveStartTime;
        }
    }

    public void setLiveStartTime(String liveStartTime) {
        this.liveStartTime = liveStartTime;
    }

    public String getIssuerId() {
        if (null == issuerId) {
            return "";
        } else {
            return issuerId;
        }
    }

    public void setIssuerId(String issuerId) {
        this.issuerId = issuerId;
    }

    public String getListStyle() {
        if (null == listStyle) {
            return "";
        } else {
            return listStyle;
        }
    }

    public void setListStyle(String listStyle) {
        this.listStyle = listStyle;
    }

    public String getIssuerName() {
        if (null == issuerName) {
            return "";
        } else {
            return issuerName;
        }
    }

    public void setIssuerName(String issuerName) {
        this.issuerName = issuerName;
    }

    public String getIssuerImageUrl() {
        if (null == issuerImageUrl) {
            return "";
        } else {
            return issuerImageUrl;
        }
    }

    public void setIssuerImageUrl(String issuerImageUrl) {
        this.issuerImageUrl = issuerImageUrl;
    }

    public Boolean getDisableComment() {
        if (null == disableComment) {
            return false;
        } else {
            return disableComment;
        }
    }

    public void setDisableComment(Boolean disableComment) {
        this.disableComment = disableComment;
    }

    public String getLabel() {
        if (null == label) {
            return "";
        } else {
            return label;
        }
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getOrientation() {
        if (null == orientation) {
            return "";
        } else {
            return orientation;
        }
    }

    public void setOrientation(String orientation) {
        this.orientation = orientation;
    }

    public Boolean getWhetherLike() {
        if (null == whetherLike) {
            return false;
        } else {
            return whetherLike;
        }
    }

    public void setWhetherLike(Boolean whetherLike) {
        this.whetherLike = whetherLike;
    }

    public Boolean getWhetherFavor() {
        if (null == whetherFavor) {
            return false;
        } else {
            return whetherFavor;
        }
    }

    public void setWhetherFavor(Boolean whetherFavor) {
        this.whetherFavor = whetherFavor;
    }

    public String getPId() {
        if (null == pId) {
            return "";
        } else {
            return pId;
        }
    }

    public void setPId(String pId) {
        this.pId = pId;
    }

    public String getIsTop() {
        if (null == isTop) {
            return "";
        } else {
            return isTop;
        }
    }

    public void setIsTop(String isTop) {
        this.isTop = isTop;
    }

    public String getSort() {
        if (null == sort) {
            return "";
        } else {
            return sort;
        }
    }

    public void setSort(String sort) {
        this.sort = sort;
    }

    public ExtendDTO getExtend() {
        return extend;
    }

    public void setExtend(ExtendDTO extend) {
        this.extend = extend;
    }

    public String getEndTime() {
        if (null == endTime) {
            return "";
        } else {
            return endTime;
        }
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public String getAttachUrl() {
        if (null == attachUrl) {
            return "";
        } else {
            return attachUrl;
        }
    }

    public void setAttachUrl(String attachUrl) {
        this.attachUrl = attachUrl;
    }

    public String getPid() {
        if (null == pid) {
            return "";
        } else {
            return pid;
        }
    }

    public void setPid(String pid) {
        this.pid = pid;
    }

    public Boolean isWifi() {
        if (null == isWifi) {
            return false;
        } else {
            return isWifi;
        }
    }

    public void setWifi(boolean wifi) {
        isWifi = wifi;
    }

    public boolean isOneRecommend() {
        if (null == oneRecommend) {
            return false;
        } else {
            return oneRecommend;
        }
    }

    public void setOneRecommend(boolean oneRecommend) {
        this.oneRecommend = oneRecommend;
    }

    public boolean isRecommendVisible() {
        if (null == recommendVisible) {
            return false;
        } else {
            return recommendVisible;
        }
    }

    public void setRecommendVisible(boolean recommendVisible) {
        this.recommendVisible = recommendVisible;
    }

    public boolean isClosed() {
        if (null == isClosed) {
            return false;
        } else {
            return isClosed;
        }
    }

    public void setClosed(boolean closed) {
        isClosed = closed;
    }

    public String getLeftTag() {
        if (null == leftTag) {
            return "";
        } else {
            return leftTag;
        }
    }

    public void setLeftTag(String leftTag) {
        this.leftTag = leftTag;
    }

    public String getVernier() {
        if (null == vernier) {
            return "";
        } else {
            return vernier;
        }
    }

    public void setVernier(String vernier) {
        this.vernier = vernier;
    }

    public String getAdvert() {
        if (null == advert) {
            return "";
        } else {
            return advert;
        }
    }

    public void setAdvert(String advert) {
        this.advert = advert;
    }

    public String getNewsId() {
        if (null == newsId) {
            return "";
        } else {
            return newsId;
        }
    }

    public void setNewsId(String newsId) {
        this.newsId = newsId;
    }

    public String getBelongActivityId() {
        if (null == belongActivityId) {
            return "";
        } else {
            return belongActivityId;
        }
    }

    public void setBelongActivityId(String belongActivityId) {
        this.belongActivityId = belongActivityId;
    }

    public String getBelongActivityName() {
        if (null == belongActivityName) {
            return "";
        } else {
            return belongActivityName;
        }
    }

    public void setBelongActivityName(String belongActivityName) {
        this.belongActivityName = belongActivityName;
    }

    public String getBelongTopicId() {
        if (null == belongTopicId) {
            return "";
        } else {
            return belongTopicId;
        }
    }

    public void setBelongTopicId(String belongTopicId) {
        this.belongTopicId = belongTopicId;
    }

    public String getBelongTopicName() {
        if (null == belongTopicName) {
            return "";
        } else {
            return belongTopicName;
        }
    }

    public void setBelongTopicName(String belongTopicName) {
        this.belongTopicName = belongTopicName;
    }

    public String getWidth() {
        if (null == width) {
            return "";
        } else {
            return width;
        }
    }

    public void setWidth(String width) {
        this.width = width;
    }

    public String getHeight() {
        if (null == height) {
            return "";
        } else {
            return height;
        }
    }

    public void setHeight(String height) {
        this.height = height;
    }

    public String getCreatorId() {
        if (null == creatorId) {
            return "";
        } else {
            return creatorId;
        }
    }

    public void setCreatorId(String creatorId) {
        this.creatorId = creatorId;
    }

    public String getCreatorUsername() {
        if (null == creatorUsername) {
            return "";
        } else {
            return creatorUsername;
        }
    }

    public void setCreatorUsername(String creatorUsername) {
        this.creatorUsername = creatorUsername;
    }

    public String getCreatorNickname() {
        if (null == creatorNickname) {
            return "";
        } else {
            return creatorNickname;
        }
    }

    public void setCreatorNickname(String creatorNickname) {
        this.creatorNickname = creatorNickname;
    }

    public String getCreatorHead() {
        if (null == creatorHead) {
            return "";
        } else {
            return creatorHead;
        }
    }

    public void setCreatorHead(String creatorHead) {
        this.creatorHead = creatorHead;
    }

    public String getCreatorGender() {
        if (null == creatorGender) {
            return "";
        } else {
            return creatorGender;
        }
    }

    public void setCreatorGender(String creatorGender) {
        this.creatorGender = creatorGender;
    }

    public String getCreatorCertMark() {
        if (null == creatorCertMark) {
            return "";
        } else {
            return creatorCertMark;
        }
    }

    public void setCreatorCertMark(String creatorCertMark) {
        this.creatorCertMark = creatorCertMark;
    }

    public String getCreatorCertDomain() {
        if (null == creatorCertDomain) {
            return "";
        } else {
            return creatorCertDomain;
        }
    }

    public void setCreatorCertDomain(String creatorCertDomain) {
        this.creatorCertDomain = creatorCertDomain;
    }

    public String getRejectReason() {
        if (null == rejectReason) {
            return "";
        } else {
            return rejectReason;
        }
    }

    public void setRejectReason(String rejectReason) {
        this.rejectReason = rejectReason;
    }

    public String getVideoType() {
        if (null == videoType) {
            return "";
        } else {
            return videoType;
        }
    }

    public void setVideoType(String videoType) {
        this.videoType = videoType;
    }

    public String getCreateBy() {
        if (null == createBy) {
            return "";
        } else {
            return createBy;
        }
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }

    public boolean isFullBtnIsShow() {
        return fullBtnIsShow;
    }

    public void setFullBtnIsShow(boolean fullBtnIsShow) {
        this.fullBtnIsShow = fullBtnIsShow;
    }

    public String getSpaceStr() {
        if (null == spaceStr) {
            return "";
        }
        return spaceStr;
    }

    public String getThirdPartyId() {
        if (null == thirdPartyId) {
            return "";
        }
        return thirdPartyId;
    }

    public void setThirdPartyId(String thirdPartyId) {
        this.thirdPartyId = thirdPartyId;
    }

    public void setSpaceStr(String spaceStr) {
        this.spaceStr = spaceStr;
    }

    public String getThirdPartyCode() {
        if (null == thirdPartyCode) {
            return "";
        }
        return thirdPartyCode;
    }

    public void setThirdPartyCode(String thirdPartyCode) {
        this.thirdPartyCode = thirdPartyCode;
    }

    public String getLogoType() {
        if (null == logoType) {
            return "";
        }
        return logoType;
    }

    public void setLogoType(String logoType) {
        this.logoType = logoType;
    }

    public String getVolcCategory() {
        if (null == volcCategory) {
            return "";
        }
        return volcCategory;
    }

    public void setVolcCategory(String volcCategory) {
        this.volcCategory = volcCategory;
    }

    public String getIsAutoReportEvent() {
        if (null == isAutoReportEvent) {
            return "";
        }
        return isAutoReportEvent;
    }

    public void setIsAutoReportEvent(String isAutoReportEvent) {
        this.isAutoReportEvent = isAutoReportEvent;
    }

    public String getExtendTextVisible() {
        if (null == extendTextVisible) {
            return "false";
        }
        return extendTextVisible;
    }

    public void setExtendTextVisible(String extendTextVisible) {
        this.extendTextVisible = extendTextVisible;
    }

    public String getRequestId() {
        if (null == requestId) {
            return "";
        }
        return requestId;
    }

    public List<String> getKeywordsShow() {
        return keywordsShow;
    }

    public void setKeywordsShow(List<String> keywordsShow) {
        this.keywordsShow = keywordsShow;
    }

    public List<String> getTagsShow() {
        return tagsShow;
    }

    public void setTagsShow(List<String> tagsShow) {
        this.tagsShow = tagsShow;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public void setRequestId(String requestId) {
        this.requestId = requestId;
    }

    public List<VideoCollectionModel.DataDTO> getCollectionList() {
        return collectionList;
    }

    public void setCollectionList(List<VideoCollectionModel.DataDTO> collectionList) {
        this.collectionList = collectionList;
    }

    @Keep
    public static class ExtendDTO {
    }
}
