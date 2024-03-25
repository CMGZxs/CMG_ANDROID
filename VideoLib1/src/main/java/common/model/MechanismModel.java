package common.model;

import android.support.annotation.Keep;

@Keep
public class MechanismModel {

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

    public Object getDetail() {
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
        private String id;
        private String logo;
        private ConfigDTO config;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getLogo() {
            return logo;
        }

        public void setLogo(String logo) {
            this.logo = logo;
        }

        public ConfigDTO getConfig() {
            return config;
        }

        public void setConfig(ConfigDTO config) {
            this.config = config;
        }

        @Keep
        public static class ConfigDTO {
            private String panId;
            private String listUrl;
            private String categoryCode;
            private String detailUrl;
            private String generateType;
            private String sourceName;
            private String toVolcengine;
            private String recommendScene;
            private String appKey;
            private String appName;

            public String getPanId() {
                return panId;
            }

            public void setPanId(String panId) {
                this.panId = panId;
            }

            public String getListUrl() {
                return listUrl;
            }

            public void setListUrl(String listUrl) {
                this.listUrl = listUrl;
            }

            public String getCategoryCode() {
                return categoryCode;
            }

            public void setCategoryCode(String categoryCode) {
                this.categoryCode = categoryCode;
            }

            public String getDetailUrl() {
                return detailUrl;
            }

            public void setDetailUrl(String detailUrl) {
                this.detailUrl = detailUrl;
            }

            public String getGenerateType() {
                return generateType;
            }

            public void setGenerateType(String generateType) {
                this.generateType = generateType;
            }

            public String getSourceName() {
                return sourceName;
            }

            public void setSourceName(String sourceName) {
                this.sourceName = sourceName;
            }

            public String getToVolcengine() {
                return toVolcengine;
            }

            public void setToVolcengine(String toVolcengine) {
                this.toVolcengine = toVolcengine;
            }

            public String getRecommendScene() {
                return recommendScene;
            }

            public void setRecommendScene(String recommendScene) {
                this.recommendScene = recommendScene;
            }

            public String getAppKey() {
                return appKey;
            }

            public void setAppKey(String appKey) {
                this.appKey = appKey;
            }

            public String getAppName() {
                return appName;
            }

            public void setAppName(String appName) {
                this.appName = appName;
            }
        }
    }
}
