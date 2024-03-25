package model.bean;


import android.text.TextUtils;

import android.support.annotation.Keep;

import java.util.List;

@Keep
public class SZContentModel {

    public int code;
    public boolean success;
    public String message;
    public Object detail;
    public List<DataDTO> data;
    public String time;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getMessage() {
        if (TextUtils.isEmpty(message)) {
            return "系统异常";
        }
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getDetail() {
        return detail;
    }

    public void setDetail(Object detail) {
        this.detail = detail;
    }

    public List<DataDTO> getData() {
        return data;
    }

    public void setData(List<DataDTO> data) {
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
        public String id;
        public String categoryId;
        public String name;
        public String code;
        public String typeName;
        public String typeCode;
        public ConfigDTO config;
        public String limitCount;
        public boolean isCategoryPanel;
        public List<ContentsDTO> contents;
        public Object subCategories;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getCategoryId() {
            return categoryId;
        }

        public void setCategoryId(String categoryId) {
            this.categoryId = categoryId;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getCode() {
            return code;
        }

        public void setCode(String code) {
            this.code = code;
        }

        public String getTypeName() {
            return typeName;
        }

        public void setTypeName(String typeName) {
            this.typeName = typeName;
        }

        public String getTypeCode() {
            return typeCode;
        }

        public void setTypeCode(String typeCode) {
            this.typeCode = typeCode;
        }

        public ConfigDTO getConfig() {
            return config;
        }

        public void setConfig(ConfigDTO config) {
            this.config = config;
        }

        public String getLimitCount() {
            return limitCount;
        }

        public void setLimitCount(String limitCount) {
            this.limitCount = limitCount;
        }

        public boolean isIsCategoryPanel() {
            return isCategoryPanel;
        }

        public void setIsCategoryPanel(boolean isCategoryPanel) {
            this.isCategoryPanel = isCategoryPanel;
        }

        public List<ContentsDTO> getContents() {
            return contents;
        }

        public void setContents(List<ContentsDTO> contents) {
            this.contents = contents;
        }

        public Object getSubCategories() {
            return subCategories;
        }

        public void setSubCategories(Object subCategories) {
            this.subCategories = subCategories;
        }

        @Keep
        public static class ConfigDTO {
            public String name;
            public String code;
            public String typeName;
            public String typeCode;
            public List<String> contentTypes;
            public boolean disableJoinToVolcEngine;

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getCode() {
                return code;
            }

            public void setCode(String code) {
                this.code = code;
            }

            public String getTypeName() {
                return typeName;
            }

            public void setTypeName(String typeName) {
                this.typeName = typeName;
            }

            public String getTypeCode() {
                return typeCode;
            }

            public void setTypeCode(String typeCode) {
                this.typeCode = typeCode;
            }

            public List<String> getContentTypes() {
                return contentTypes;
            }

            public void setContentTypes(List<String> contentTypes) {
                this.contentTypes = contentTypes;
            }

            public boolean isDisableJoinToVolcEngine() {
                return disableJoinToVolcEngine;
            }

            public void setDisableJoinToVolcEngine(boolean disableJoinToVolcEngine) {
                this.disableJoinToVolcEngine = disableJoinToVolcEngine;
            }
        }

        @Keep
        public static class ContentsDTO {
            public String shareTitle;
            public String shareUrl;
            public String shareImageUrl;
            public String shareBrief;
            public String timeDif;
            public String issueTimeStamp;
            public String startTime;
            public String id;
            public String createBy;
            public String readCount;
            public String commentCountShow;
            public String likeCountShow;
            public String favorCountShow;
            public String viewCountShow;
            public String type;
            public String subType;
            public String title;
            public String thumbnailUrl;
            public String brief;
            public String detailUrl;
            public String externalUrl;
            public boolean isExternal;
            public String playUrl;
            public String showTime;
            public String source;
            public Object keywords;
            public Object tags;
            public String classification;
            public String imagesUrl;
            public String playDuration;
            public String status;
            public String liveStatus;
            public String liveStartTime;
            public String issuerId;
            public String listStyle;
            public String issuerName;
            public String issuerImageUrl;
            public boolean disableComment;
            public String label;
            public String orientation;
            public boolean whetherLike;
            public boolean whetherFavor;
            public boolean whetherFollow;
            public String isTop;
            public String leftTag;
            public ExtendDTO extend;
            public String vernier;
            public String advert;
            public String newsId;
            public String belongActivityId;
            public String belongActivityName;
            public String belongTopicId;
            public String belongTopicName;
            public String width;
            public String height;
            public String creatorUsername;
            public String creatorNickname;
            public String creatorHead;
            public String creatorGender;
            public String creatorCertMark;
            public String creatorCertDomain;
            public String rejectReason;
            public String thirdPartyId;
            public String thirdPartyCode;
            public String endTime;
            public String url;
            public String volcCategory;
            public String commentMannerVos;
            public String requestId;
            public String crowdPackage;
            public String createTime;
            public String idShow;
            public String topicContentId;
            public String totalComment;
            public List<String> keywordsShow;
            public List<String> tagsShow;
            public String contentUrl;
            public boolean isOriginal;
            public String pid;

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

            public Object getReadCount() {
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

            public boolean isIsExternal() {
                return isExternal;
            }

            public void setIsExternal(boolean isExternal) {
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

            public Object getKeywords() {
                return keywords;
            }

            public void setKeywords(Object keywords) {
                this.keywords = keywords;
            }

            public Object getTags() {
                return tags;
            }

            public void setTags(Object tags) {
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

            public boolean isDisableComment() {
                return disableComment;
            }

            public void setDisableComment(boolean disableComment) {
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

            public boolean isWhetherLike() {
                return whetherLike;
            }

            public void setWhetherLike(boolean whetherLike) {
                this.whetherLike = whetherLike;
            }

            public boolean isWhetherFavor() {
                return whetherFavor;
            }

            public void setWhetherFavor(boolean whetherFavor) {
                this.whetherFavor = whetherFavor;
            }

            public boolean isWhetherFollow() {
                return whetherFollow;
            }

            public void setWhetherFollow(boolean whetherFollow) {
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
                if (TextUtils.isEmpty(vernier)) {
                    return "";
                }
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

            public String getTopicContentId() {
                return topicContentId;
            }

            public void setTopicContentId(String topicContentId) {
                this.topicContentId = topicContentId;
            }

            public String getTotalComment() {
                return totalComment;
            }

            public void setTotalComment(String totalComment) {
                this.totalComment = totalComment;
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

            public String getContentUrl() {
                return contentUrl;
            }

            public void setContentUrl(String contentUrl) {
                this.contentUrl = contentUrl;
            }

            public boolean isIsOriginal() {
                return isOriginal;
            }

            public void setIsOriginal(boolean isOriginal) {
                this.isOriginal = isOriginal;
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
