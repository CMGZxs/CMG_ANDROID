package common.model;


import android.support.annotation.Keep;

import java.util.List;

@Keep
public class CollectionLabelModel {

    private String code;
    private String success;
    private String message;
    private String detail;
    private DataDTO data;
    private String time;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String isSuccess() {
        return success;
    }

    public void setSuccess(String success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public DataDTO getData() {
        return data;
    }

    public void setData(DataDTO data) {
        this.data = data;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    @Keep
    public static class DataDTO {
        private List<ListDTO> list;
        private String topicName;

        public List<ListDTO> getList() {
            return list;
        }

        public void setList(List<ListDTO> list) {
            this.list = list;
        }

        public String getTopicName() {
            return topicName;
        }

        public void setTopicName(String topicName) {
            this.topicName = topicName;
        }

        @Keep
        public static class ListDTO {
            private String shareTitle;
            private String shareUrl;
            private String shareImageUrl;
            private String shareBrief;
            private String timeDif;
            private String issueTimeStamp;
            private String startTime;
            private String id;
            private String createBy;
            private String readCount;
            private String commentCountShow;
            private String likeCountShow;
            private String favorCountShow;
            private String viewCountShow;
            private String type;
            private String subType;
            private String title;
            private String thumbnailUrl;
            private String brief;
            private String detailUrl;
            private String externalUrl;
            private String isExternal;
            private String playUrl;
            private String showTime;
            private String source;
            private String keywords;
            private String tags;
            private String classification;
            private String imagesUrl;
            private String playDuration;
            private String status;
            private String liveStatus;
            private String liveStartTime;
            private String issuerId;
            private String listStyle;
            private String issuerName;
            private String issuerImageUrl;
            private String disableComment;
            private String label;
            private String orientation;
            private String whetherLike;
            private String whetherFavor;
            private String whetherFollow;
            private String isTop;
            private String leftTag;
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
            private String creatorUsername;
            private String creatorNickname;
            private String creatorHead;
            private String creatorGender;
            private String creatorCertMark;
            private String creatorCertDomain;
            private String rejectReason;
            private String thirdPartyId;
            private String thirdPartyCode;
            private String endTime;
            private String url;
            private String volcCategory;
            private String commentMannerVos;
            private String requestId;
            private String crowdPackage;
            private String createTime;
            private String idShow;
            private String keywordsShow;
            private String tagsShow;
            private String contentUrl;
            private String pid;

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

            public String getShareImageUrl() {
                return shareImageUrl;
            }

            public void setShareImageUrl(String shareImageUrl) {
                this.shareImageUrl = shareImageUrl;
            }

            public String getShareBrief() {
                return shareBrief;
            }

            public void setShareBrief(String shareBrief) {
                this.shareBrief = shareBrief;
            }

            public String getTimeDif() {
                return timeDif;
            }

            public void setTimeDif(String timeDif) {
                this.timeDif = timeDif;
            }

            public String getIssueTimeStamp() {
                return issueTimeStamp;
            }

            public void setIssueTimeStamp(String issueTimeStamp) {
                this.issueTimeStamp = issueTimeStamp;
            }

            public String getStartTime() {
                return startTime;
            }

            public void setStartTime(String startTime) {
                this.startTime = startTime;
            }

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getCreateBy() {
                return createBy;
            }

            public void setCreateBy(String createBy) {
                this.createBy = createBy;
            }

            public String getReadCount() {
                return readCount;
            }

            public void setReadCount(String readCount) {
                this.readCount = readCount;
            }

            public String getCommentCountShow() {
                return commentCountShow;
            }

            public void setCommentCountShow(String commentCountShow) {
                this.commentCountShow = commentCountShow;
            }

            public String getLikeCountShow() {
                return likeCountShow;
            }

            public void setLikeCountShow(String likeCountShow) {
                this.likeCountShow = likeCountShow;
            }

            public String getFavorCountShow() {
                return favorCountShow;
            }

            public void setFavorCountShow(String favorCountShow) {
                this.favorCountShow = favorCountShow;
            }

            public String getViewCountShow() {
                return viewCountShow;
            }

            public void setViewCountShow(String viewCountShow) {
                this.viewCountShow = viewCountShow;
            }

            public String getType() {
                return type;
            }

            public void setType(String type) {
                this.type = type;
            }

            public String getSubType() {
                return subType;
            }

            public void setSubType(String subType) {
                this.subType = subType;
            }

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public String getThumbnailUrl() {
                return thumbnailUrl;
            }

            public void setThumbnailUrl(String thumbnailUrl) {
                this.thumbnailUrl = thumbnailUrl;
            }

            public String getBrief() {
                return brief;
            }

            public void setBrief(String brief) {
                this.brief = brief;
            }

            public String getDetailUrl() {
                return detailUrl;
            }

            public void setDetailUrl(String detailUrl) {
                this.detailUrl = detailUrl;
            }

            public String getExternalUrl() {
                return externalUrl;
            }

            public void setExternalUrl(String externalUrl) {
                this.externalUrl = externalUrl;
            }

            public String isIsExternal() {
                return isExternal;
            }

            public void setIsExternal(String isExternal) {
                this.isExternal = isExternal;
            }

            public String getPlayUrl() {
                return playUrl;
            }

            public void setPlayUrl(String playUrl) {
                this.playUrl = playUrl;
            }

            public String getShowTime() {
                return showTime;
            }

            public void setShowTime(String showTime) {
                this.showTime = showTime;
            }

            public String getSource() {
                return source;
            }

            public void setSource(String source) {
                this.source = source;
            }

            public String getKeywords() {
                return keywords;
            }

            public void setKeywords(String keywords) {
                this.keywords = keywords;
            }

            public String getTags() {
                return tags;
            }

            public void setTags(String tags) {
                this.tags = tags;
            }

            public String getClassification() {
                return classification;
            }

            public void setClassification(String classification) {
                this.classification = classification;
            }

            public String getImagesUrl() {
                return imagesUrl;
            }

            public void setImagesUrl(String imagesUrl) {
                this.imagesUrl = imagesUrl;
            }

            public String getPlayDuration() {
                return playDuration;
            }

            public void setPlayDuration(String playDuration) {
                this.playDuration = playDuration;
            }

            public String getStatus() {
                return status;
            }

            public void setStatus(String status) {
                this.status = status;
            }

            public String getLiveStatus() {
                return liveStatus;
            }

            public void setLiveStatus(String liveStatus) {
                this.liveStatus = liveStatus;
            }

            public String getLiveStartTime() {
                return liveStartTime;
            }

            public void setLiveStartTime(String liveStartTime) {
                this.liveStartTime = liveStartTime;
            }

            public String getIssuerId() {
                return issuerId;
            }

            public void setIssuerId(String issuerId) {
                this.issuerId = issuerId;
            }

            public String getListStyle() {
                return listStyle;
            }

            public void setListStyle(String listStyle) {
                this.listStyle = listStyle;
            }

            public String getIssuerName() {
                return issuerName;
            }

            public void setIssuerName(String issuerName) {
                this.issuerName = issuerName;
            }

            public String getIssuerImageUrl() {
                return issuerImageUrl;
            }

            public void setIssuerImageUrl(String issuerImageUrl) {
                this.issuerImageUrl = issuerImageUrl;
            }

            public String isDisableComment() {
                return disableComment;
            }

            public void setDisableComment(String disableComment) {
                this.disableComment = disableComment;
            }

            public String getLabel() {
                return label;
            }

            public void setLabel(String label) {
                this.label = label;
            }

            public String getOrientation() {
                return orientation;
            }

            public void setOrientation(String orientation) {
                this.orientation = orientation;
            }

            public String isWhetherLike() {
                return whetherLike;
            }

            public void setWhetherLike(String whetherLike) {
                this.whetherLike = whetherLike;
            }

            public String isWhetherFavor() {
                return whetherFavor;
            }

            public void setWhetherFavor(String whetherFavor) {
                this.whetherFavor = whetherFavor;
            }

            public String isWhetherFollow() {
                return whetherFollow;
            }

            public void setWhetherFollow(String whetherFollow) {
                this.whetherFollow = whetherFollow;
            }

            public String getIsTop() {
                return isTop;
            }

            public void setIsTop(String isTop) {
                this.isTop = isTop;
            }

            public String getLeftTag() {
                return leftTag;
            }

            public void setLeftTag(String leftTag) {
                this.leftTag = leftTag;
            }

            public ExtendDTO getExtend() {
                return extend;
            }

            public void setExtend(ExtendDTO extend) {
                this.extend = extend;
            }

            public String getVernier() {
                return vernier;
            }

            public void setVernier(String vernier) {
                this.vernier = vernier;
            }

            public String getAdvert() {
                return advert;
            }

            public void setAdvert(String advert) {
                this.advert = advert;
            }

            public String getNewsId() {
                return newsId;
            }

            public void setNewsId(String newsId) {
                this.newsId = newsId;
            }

            public String getBelongActivityId() {
                return belongActivityId;
            }

            public void setBelongActivityId(String belongActivityId) {
                this.belongActivityId = belongActivityId;
            }

            public String getBelongActivityName() {
                return belongActivityName;
            }

            public void setBelongActivityName(String belongActivityName) {
                this.belongActivityName = belongActivityName;
            }

            public String getBelongTopicId() {
                return belongTopicId;
            }

            public void setBelongTopicId(String belongTopicId) {
                this.belongTopicId = belongTopicId;
            }

            public String getBelongTopicName() {
                return belongTopicName;
            }

            public void setBelongTopicName(String belongTopicName) {
                this.belongTopicName = belongTopicName;
            }

            public String getWidth() {
                return width;
            }

            public void setWidth(String width) {
                this.width = width;
            }

            public String getHeight() {
                return height;
            }

            public void setHeight(String height) {
                this.height = height;
            }

            public String getCreatorUsername() {
                return creatorUsername;
            }

            public void setCreatorUsername(String creatorUsername) {
                this.creatorUsername = creatorUsername;
            }

            public String getCreatorNickname() {
                return creatorNickname;
            }

            public void setCreatorNickname(String creatorNickname) {
                this.creatorNickname = creatorNickname;
            }

            public String getCreatorHead() {
                return creatorHead;
            }

            public void setCreatorHead(String creatorHead) {
                this.creatorHead = creatorHead;
            }

            public String getCreatorGender() {
                return creatorGender;
            }

            public void setCreatorGender(String creatorGender) {
                this.creatorGender = creatorGender;
            }

            public String getCreatorCertMark() {
                return creatorCertMark;
            }

            public void setCreatorCertMark(String creatorCertMark) {
                this.creatorCertMark = creatorCertMark;
            }

            public String getCreatorCertDomain() {
                return creatorCertDomain;
            }

            public void setCreatorCertDomain(String creatorCertDomain) {
                this.creatorCertDomain = creatorCertDomain;
            }

            public String getRejectReason() {
                return rejectReason;
            }

            public void setRejectReason(String rejectReason) {
                this.rejectReason = rejectReason;
            }

            public String getThirdPartyId() {
                return thirdPartyId;
            }

            public void setThirdPartyId(String thirdPartyId) {
                this.thirdPartyId = thirdPartyId;
            }

            public String getThirdPartyCode() {
                return thirdPartyCode;
            }

            public void setThirdPartyCode(String thirdPartyCode) {
                this.thirdPartyCode = thirdPartyCode;
            }

            public String getEndTime() {
                return endTime;
            }

            public void setEndTime(String endTime) {
                this.endTime = endTime;
            }

            public String getUrl() {
                return url;
            }

            public void setUrl(String url) {
                this.url = url;
            }

            public String getVolcCategory() {
                return volcCategory;
            }

            public void setVolcCategory(String volcCategory) {
                this.volcCategory = volcCategory;
            }

            public String getCommentMannerVos() {
                return commentMannerVos;
            }

            public void setCommentMannerVos(String commentMannerVos) {
                this.commentMannerVos = commentMannerVos;
            }

            public String getRequestId() {
                return requestId;
            }

            public void setRequestId(String requestId) {
                this.requestId = requestId;
            }

            public String getCrowdPackage() {
                return crowdPackage;
            }

            public void setCrowdPackage(String crowdPackage) {
                this.crowdPackage = crowdPackage;
            }

            public String getCreateTime() {
                return createTime;
            }

            public void setCreateTime(String createTime) {
                this.createTime = createTime;
            }

            public String getIdShow() {
                return idShow;
            }

            public void setIdShow(String idShow) {
                this.idShow = idShow;
            }

            public String getKeywordsShow() {
                return keywordsShow;
            }

            public void setKeywordsShow(String keywordsShow) {
                this.keywordsShow = keywordsShow;
            }

            public String getTagsShow() {
                return tagsShow;
            }

            public void setTagsShow(String tagsShow) {
                this.tagsShow = tagsShow;
            }

            public String getContentUrl() {
                return contentUrl;
            }

            public void setContentUrl(String contentUrl) {
                this.contentUrl = contentUrl;
            }

            public String getPid() {
                return pid;
            }

            public void setPid(String pid) {
                this.pid = pid;
            }

            @Keep
            public static class ExtendDTO {
            }
        }
    }
}
