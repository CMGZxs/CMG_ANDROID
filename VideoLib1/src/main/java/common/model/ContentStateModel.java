package common.model;

import android.text.TextUtils;

import android.support.annotation.Keep;

@Keep
public class ContentStateModel {

    private String code;
    private String success;
    private String message;
    private String detail;
    private DataDTO data;
    private String time;

    public String getCode() {
        if (TextUtils.isEmpty(code)){
            return "";
        }
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getSuccess() {
        if (TextUtils.isEmpty(success)){
            return "";
        }
        return success;
    }

    public void setSuccess(String success) {
        this.success = success;
    }

    public String getMessage() {
        if (TextUtils.isEmpty(message)){
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

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public DataDTO getData() {
        if (null == data){
            return new DataDTO();
        }
        return data;
    }

    public void setData(DataDTO data) {
        this.data = data;
    }

    public String getTime() {
        if (TextUtils.isEmpty(time)){
            return "";
        }
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    @Keep
    public static class DataDTO {
        private String id;
        private String commentCountShow;
        private String likeCountShow;
        private String favorCountShow;
        private String viewCountShow;
        private String playCountShow;
        private String contentId;
        private String whetherLike;
        private String whetherFavor;
        private String whetherFollow;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getCommentCountShow() {
            if (TextUtils.isEmpty(commentCountShow)){
                return "";
            }
            return commentCountShow;
        }

        public void setCommentCountShow(String commentCountShow) {
            this.commentCountShow = commentCountShow;
        }

        public String getLikeCountShow() {
            if (TextUtils.isEmpty(likeCountShow)){
                return "";
            }
            return likeCountShow;
        }

        public void setLikeCountShow(String likeCountShow) {
            this.likeCountShow = likeCountShow;
        }

        public String getFavorCountShow() {
            if (TextUtils.isEmpty(favorCountShow)){
                return "";
            }
            return favorCountShow;
        }

        public void setFavorCountShow(String favorCountShow) {
            this.favorCountShow = favorCountShow;
        }

        public String getViewCountShow() {
            if (TextUtils.isEmpty(viewCountShow)){
                return "";
            }
            return viewCountShow;
        }

        public void setViewCountShow(String viewCountShow) {
            this.viewCountShow = viewCountShow;
        }

        public String getPlayCountShow() {
            if (TextUtils.isEmpty(playCountShow)){
                return "";
            }
            return playCountShow;
        }

        public void setPlayCountShow(String playCountShow) {
            this.playCountShow = playCountShow;
        }

        public String getContentId() {
            if (TextUtils.isEmpty(contentId)){
                return "";
            }
            return contentId;
        }

        public void setContentId(String contentId) {
            this.contentId = contentId;
        }

        public String getWhetherLike() {
            if (TextUtils.isEmpty(whetherLike)){
                return "";
            }
            return whetherLike;
        }

        public void setWhetherLike(String whetherLike) {
            this.whetherLike = whetherLike;
        }

        public String getWhetherFavor() {
            if (TextUtils.isEmpty(whetherFavor)){
                return "";
            }
            return whetherFavor;
        }

        public void setWhetherFavor(String whetherFavor) {
            this.whetherFavor = whetherFavor;
        }

        public String getWhetherFollow() {
            if (TextUtils.isEmpty(whetherFollow)){
                return "";
            }
            return whetherFollow;
        }

        public void setWhetherFollow(String whetherFollow) {
            this.whetherFollow = whetherFollow;
        }
    }
}
