package common.model;

import android.text.TextUtils;

import android.support.annotation.Keep;

import java.util.ArrayList;
import java.util.List;

@Keep
public class RecommendModel {

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
        if (null == detail) {
            return new Object();
        }
        return detail;
    }

    public void setDetail(String detail) {
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
        private String total;
        private List<RecordsDTO> records;
        private String pageIndex;
        private String pageSize;

        public String getTotal() {
            if (TextUtils.isEmpty(total)){
                return "";
            }
            return total;
        }

        public void setTotal(String total) {
            this.total = total;
        }

        public List<RecordsDTO> getRecords() {
            return records;
        }

        public void setRecords(List<RecordsDTO> records) {
            this.records = records;
        }

        public String getPageIndex() {
            if (TextUtils.isEmpty(pageIndex)){
                return "";
            }
            return pageIndex;
        }

        public void setPageIndex(String pageIndex) {
            this.pageIndex = pageIndex;
        }

        public String getPageSize() {
            if (TextUtils.isEmpty(pageSize)){
                return "";
            }
            return pageSize;
        }

        public void setPageSize(String pageSize) {
            this.pageSize = pageSize;
        }

        @Keep
        public static class RecordsDTO {
            private String title;
            private String url;
            private String thumbnailUrl;
            private String id;
            private String type;
            private String isExternal;
            private String externalUrl;
            private String liveStatus;

            public String getTitle() {
                if (TextUtils.isEmpty(title)){
                    return "";
                }
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public String getUrl() {
                if (TextUtils.isEmpty(url)){
                    return "";
                }
                return url;
            }

            public void setUrl(String url) {
                this.url = url;
            }

            public String getThumbnailUrl() {
                if (TextUtils.isEmpty(thumbnailUrl)){
                    return "";
                }
                return thumbnailUrl;
            }

            public void setThumbnailUrl(String thumbnailUrl) {
                this.thumbnailUrl = thumbnailUrl;
            }

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getType() {
                if (TextUtils.isEmpty(type)){
                    return "";
                }
                return type;
            }

            public void setType(String type) {
                this.type = type;
            }

            public String getIsExternal() {
                if (TextUtils.isEmpty(isExternal)){
                    return "";
                }
                return isExternal;
            }

            public void setIsExternal(String isExternal) {
                this.isExternal = isExternal;
            }

            public String getExternalUrl() {
                if (TextUtils.isEmpty(externalUrl)){
                    return "";
                }
                return externalUrl;
            }

            public void setExternalUrl(String externalUrl) {
                this.externalUrl = externalUrl;
            }

            public String getLiveStatus() {
                if (TextUtils.isEmpty(liveStatus)){
                    return "";
                }
                return liveStatus;
            }

            public void setLiveStatus(String liveStatus) {
                this.liveStatus = liveStatus;
            }
        }
    }
}
