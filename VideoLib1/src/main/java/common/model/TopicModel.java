package common.model;

import android.text.TextUtils;

import android.support.annotation.Keep;

import java.util.ArrayList;
import java.util.List;
@Keep
public class TopicModel {


    private Integer code;
    private Boolean success;
    private String message;
    private Object detail;
    private DataDTO data;
    private String time;

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public Boolean getSuccess() {
        if (null == success) {
            return false;
        }
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public String getMessage() {
        if (TextUtils.isEmpty(message)) {
            return "";
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

    public DataDTO getData() {
        if (null == data) {
            return new DataDTO();
        }
        return data;
    }

    public void setData(DataDTO data) {
        this.data = data;
    }

    public String getTime() {
        if (TextUtils.isEmpty(message)) {
            return "";
        }
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
    @Keep
    public static class DataDTO {
        private Integer total;
        private List<RecordsDTO> records;
        private Integer pageIndex;
        private Integer pageSize;

        public Integer getTotal() {
            if (null == total) {
                return 0;
            }
            return total;
        }

        public void setTotal(Integer total) {
            this.total = total;
        }

        public List<RecordsDTO> getRecords() {
            if (null == records) {
                List<RecordsDTO> list = new ArrayList<>();
                return list;
            }
            return records;
        }

        public void setRecords(List<RecordsDTO> records) {
            this.records = records;
        }

        public Integer getPageIndex() {
            if (null == pageIndex) {
                return 0;
            }
            return pageIndex;
        }

        public void setPageIndex(Integer pageIndex) {
            this.pageIndex = pageIndex;
        }

        public Integer getPageSize() {
            if (null == pageSize) {
                return 0;
            }
            return pageSize;
        }

        public void setPageSize(Integer pageSize) {
            this.pageSize = pageSize;
        }
        @Keep
        public static class RecordsDTO {
            private String shareTitle;
            private String shareUrl;
            private String shareImageUrl;
            private String shareBrief;
            private String timeDif;
            private String issueTimeStamp;
            private String startTime;
            private Integer id;
            private Integer createBy;
            private Object readCount;
            private Integer commentCountShow;
            private Integer likeCountShow;
            private Integer favorCountShow;
            private Integer viewCountShow;
            private String type;
            private Object subType;
            private String title;
            private String thumbnailUrl;
            private String brief;
            private String detailUrl;
            private Object externalUrl;
            private Boolean isExternal;
            private String playUrl;
            private Object source;
            private Object keywords;
            private Object tags;
            private Object classification;
            private Object imagesUrl;
            private Integer playDuration;
            private Integer status;
            private Object liveStatus;
            private Object liveStartTime;
            private Object issuerId;
            private Integer listStyle;
            private Object issuerName;
            private Object issuerImageUrl;
            private Boolean disableComment;
            private Object label;
            private Object orientation;
            private Boolean whetherLike;
            private Boolean whetherFavor;
            private Boolean whetherFollow;
            private Object isTop;
            private Object leftTag;
            private ExtendDTO extend;
            private Object vernier;
            private Object advert;
            private Object newsId;
            private Integer belongActivityId;
            private String belongActivityName;
            private Object belongTopicId;
            private Object belongTopicName;
            private Integer width;
            private Integer height;
            private String creatorUsername;
            private String creatorNickname;
            private String creatorHead;
            private Integer creatorGender;
            private Object creatorCertMark;
            private Object creatorCertDomain;
            private String rejectReason;
            private Object thirdPartyId;
            private Object thirdPartyCode;
            private Object pid;

            public String getShareTitle() {
                if (TextUtils.isEmpty(shareTitle)) {
                    return "";
                }
                return shareTitle;
            }

            public void setShareTitle(String shareTitle) {
                this.shareTitle = shareTitle;
            }

            public String getShareUrl() {
                if (TextUtils.isEmpty(shareUrl)) {
                    return "";
                }
                return shareUrl;
            }

            public void setShareUrl(String shareUrl) {
                this.shareUrl = shareUrl;
            }

            public String getShareImageUrl() {
                if (TextUtils.isEmpty(shareImageUrl)) {
                    return "";
                }
                return shareImageUrl;
            }

            public void setShareImageUrl(String shareImageUrl) {
                this.shareImageUrl = shareImageUrl;
            }

            public String getShareBrief() {
                if (TextUtils.isEmpty(shareBrief)) {
                    return "";
                }
                return shareBrief;
            }

            public void setShareBrief(String shareBrief) {
                this.shareBrief = shareBrief;
            }

            public String getTimeDif() {
                if (TextUtils.isEmpty(timeDif)) {
                    return "";
                }
                return timeDif;
            }

            public void setTimeDif(String timeDif) {
                this.timeDif = timeDif;
            }

            public String getIssueTimeStamp() {
                if (TextUtils.isEmpty(issueTimeStamp)) {
                    return "";
                }
                return issueTimeStamp;
            }

            public void setIssueTimeStamp(String issueTimeStamp) {
                this.issueTimeStamp = issueTimeStamp;
            }

            public String getStartTime() {
                if (TextUtils.isEmpty(startTime)) {
                    return "";
                }
                return startTime;
            }

            public void setStartTime(String startTime) {
                this.startTime = startTime;
            }

            public Integer getId() {
                return id;
            }

            public void setId(Integer id) {
                this.id = id;
            }

            public Integer getCreateBy() {
                if (null == createBy) {
                    return 0;
                }
                return createBy;
            }

            public void setCreateBy(Integer createBy) {
                this.createBy = createBy;
            }

            public Object getReadCount() {
                return readCount;
            }

            public void setReadCount(Object readCount) {
                this.readCount = readCount;
            }

            public Integer getCommentCountShow() {
                if (null == commentCountShow) {
                    return 0;
                }
                return commentCountShow;
            }

            public void setCommentCountShow(Integer commentCountShow) {
                this.commentCountShow = commentCountShow;
            }

            public Integer getLikeCountShow() {
                if (null == likeCountShow) {
                    return 0;
                }
                return likeCountShow;
            }

            public void setLikeCountShow(Integer likeCountShow) {
                this.likeCountShow = likeCountShow;
            }

            public Integer getFavorCountShow() {
                if (null == favorCountShow) {
                    return 0;
                }
                return favorCountShow;
            }

            public void setFavorCountShow(Integer favorCountShow) {
                this.favorCountShow = favorCountShow;
            }

            public Integer getViewCountShow() {
                if (null == viewCountShow) {
                    return 0;
                }
                return viewCountShow;
            }

            public void setViewCountShow(Integer viewCountShow) {
                this.viewCountShow = viewCountShow;
            }

            public String getType() {
                if (TextUtils.isEmpty(type)) {
                    return "";
                }
                return type;
            }

            public void setType(String type) {
                this.type = type;
            }

            public Object getSubType() {
                return subType;
            }

            public void setSubType(Object subType) {
                this.subType = subType;
            }

            public String getTitle() {
                if (TextUtils.isEmpty(title)) {
                    return "";
                }
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public String getThumbnailUrl() {
                if (TextUtils.isEmpty(thumbnailUrl)) {
                    return "";
                }
                return thumbnailUrl;
            }

            public void setThumbnailUrl(String thumbnailUrl) {
                this.thumbnailUrl = thumbnailUrl;
            }

            public String getBrief() {
                if (TextUtils.isEmpty(brief)) {
                    return "";
                }
                return brief;
            }

            public void setBrief(String brief) {
                this.brief = brief;
            }

            public String getDetailUrl() {
                if (TextUtils.isEmpty(detailUrl)) {
                    return "";
                }
                return detailUrl;
            }

            public void setDetailUrl(String detailUrl) {
                this.detailUrl = detailUrl;
            }

            public Object getExternalUrl() {
                return externalUrl;
            }

            public void setExternalUrl(Object externalUrl) {
                this.externalUrl = externalUrl;
            }

            public Boolean getIsExternal() {
                if (null == isExternal) {
                    return false;
                }
                return isExternal;
            }

            public void setIsExternal(Boolean isExternal) {
                this.isExternal = isExternal;
            }

            public String getPlayUrl() {
                if (TextUtils.isEmpty(playUrl)) {
                    return "";
                }
                return playUrl;
            }

            public void setPlayUrl(String playUrl) {
                this.playUrl = playUrl;
            }

            public Object getSource() {
                return source;
            }

            public void setSource(Object source) {
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

            public Object getClassification() {
                return classification;
            }

            public void setClassification(Object classification) {
                this.classification = classification;
            }

            public Object getImagesUrl() {
                return imagesUrl;
            }

            public void setImagesUrl(Object imagesUrl) {
                this.imagesUrl = imagesUrl;
            }

            public Integer getPlayDuration() {
                if (null == playDuration) {
                    return 0;
                }
                return playDuration;
            }

            public void setPlayDuration(Integer playDuration) {
                this.playDuration = playDuration;
            }

            public Integer getStatus() {
                if (null == status) {
                    return 0;
                }
                return status;
            }

            public void setStatus(Integer status) {
                this.status = status;
            }

            public Object getLiveStatus() {
                return liveStatus;
            }

            public void setLiveStatus(Object liveStatus) {
                this.liveStatus = liveStatus;
            }

            public Object getLiveStartTime() {
                return liveStartTime;
            }

            public void setLiveStartTime(Object liveStartTime) {
                this.liveStartTime = liveStartTime;
            }

            public Object getIssuerId() {
                return issuerId;
            }

            public void setIssuerId(Object issuerId) {
                this.issuerId = issuerId;
            }

            public Integer getListStyle() {
                return listStyle;
            }

            public void setListStyle(Integer listStyle) {
                this.listStyle = listStyle;
            }

            public Object getIssuerName() {
                return issuerName;
            }

            public void setIssuerName(Object issuerName) {
                this.issuerName = issuerName;
            }

            public Object getIssuerImageUrl() {
                return issuerImageUrl;
            }

            public void setIssuerImageUrl(Object issuerImageUrl) {
                this.issuerImageUrl = issuerImageUrl;
            }

            public Boolean getDisableComment() {
                return disableComment;
            }

            public void setDisableComment(Boolean disableComment) {
                this.disableComment = disableComment;
            }

            public Object getLabel() {
                return label;
            }

            public void setLabel(Object label) {
                this.label = label;
            }

            public Object getOrientation() {
                return orientation;
            }

            public void setOrientation(Object orientation) {
                this.orientation = orientation;
            }

            public Boolean getWhetherLike() {
                return whetherLike;
            }

            public void setWhetherLike(Boolean whetherLike) {
                this.whetherLike = whetherLike;
            }

            public Boolean getWhetherFavor() {
                return whetherFavor;
            }

            public void setWhetherFavor(Boolean whetherFavor) {
                this.whetherFavor = whetherFavor;
            }

            public Boolean getWhetherFollow() {
                return whetherFollow;
            }

            public void setWhetherFollow(Boolean whetherFollow) {
                this.whetherFollow = whetherFollow;
            }

            public Object getIsTop() {
                return isTop;
            }

            public void setIsTop(Object isTop) {
                this.isTop = isTop;
            }

            public Object getLeftTag() {
                return leftTag;
            }

            public void setLeftTag(Object leftTag) {
                this.leftTag = leftTag;
            }

            public ExtendDTO getExtend() {
                return extend;
            }

            public void setExtend(ExtendDTO extend) {
                this.extend = extend;
            }

            public Object getVernier() {
                return vernier;
            }

            public void setVernier(Object vernier) {
                this.vernier = vernier;
            }

            public Object getAdvert() {
                return advert;
            }

            public void setAdvert(Object advert) {
                this.advert = advert;
            }

            public Object getNewsId() {
                return newsId;
            }

            public void setNewsId(Object newsId) {
                this.newsId = newsId;
            }

            public Integer getBelongActivityId() {
                return belongActivityId;
            }

            public void setBelongActivityId(Integer belongActivityId) {
                this.belongActivityId = belongActivityId;
            }

            public String getBelongActivityName() {
                return belongActivityName;
            }

            public void setBelongActivityName(String belongActivityName) {
                this.belongActivityName = belongActivityName;
            }

            public Object getBelongTopicId() {
                return belongTopicId;
            }

            public void setBelongTopicId(Object belongTopicId) {
                this.belongTopicId = belongTopicId;
            }

            public Object getBelongTopicName() {
                return belongTopicName;
            }

            public void setBelongTopicName(Object belongTopicName) {
                this.belongTopicName = belongTopicName;
            }

            public Integer getWidth() {
                return width;
            }

            public void setWidth(Integer width) {
                this.width = width;
            }

            public Integer getHeight() {
                return height;
            }

            public void setHeight(Integer height) {
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

            public Integer getCreatorGender() {
                return creatorGender;
            }

            public void setCreatorGender(Integer creatorGender) {
                this.creatorGender = creatorGender;
            }

            public Object getCreatorCertMark() {
                return creatorCertMark;
            }

            public void setCreatorCertMark(Object creatorCertMark) {
                this.creatorCertMark = creatorCertMark;
            }

            public Object getCreatorCertDomain() {
                return creatorCertDomain;
            }

            public void setCreatorCertDomain(Object creatorCertDomain) {
                this.creatorCertDomain = creatorCertDomain;
            }

            public String getRejectReason() {
                return rejectReason;
            }

            public void setRejectReason(String rejectReason) {
                this.rejectReason = rejectReason;
            }

            public Object getThirdPartyId() {
                return thirdPartyId;
            }

            public void setThirdPartyId(Object thirdPartyId) {
                this.thirdPartyId = thirdPartyId;
            }

            public Object getThirdPartyCode() {
                return thirdPartyCode;
            }

            public void setThirdPartyCode(Object thirdPartyCode) {
                this.thirdPartyCode = thirdPartyCode;
            }

            public Object getPid() {
                return pid;
            }

            public void setPid(Object pid) {
                this.pid = pid;
            }
            @Keep
            public static class ExtendDTO {
            }
        }
    }
}
