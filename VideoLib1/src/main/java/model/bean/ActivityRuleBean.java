package model.bean;

import android.text.TextUtils;

import android.support.annotation.Keep;

@Keep
public class ActivityRuleBean {

    private String code;
    private String success;
    private String message;
    private String detail;
    private DataDTO data;
    private String time;

    public String getCode() {
        if (TextUtils.isEmpty(code)) {
            return "";
        }
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getSuccess() {
        if (TextUtils.isEmpty(success)) {
            return "";
        }
        return success;
    }

    public void setSuccess(String success) {
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

    public String getDetail() {
        if (TextUtils.isEmpty(detail)) {
            return "";
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
        if (TextUtils.isEmpty(time)) {
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
        private String categoryId;
        private String name;
        private String code;
        private String typeName;
        private String typeCode;
        private ConfigDTO config;
        private String limitCount;
        private String isCategoryPanel;
        private String contents;
        private String subCategories;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getCategoryId() {
            if (TextUtils.isEmpty(categoryId)) {
                return "";
            }
            return categoryId;
        }

        public void setCategoryId(String categoryId) {
            this.categoryId = categoryId;
        }

        public String getName() {
            if (TextUtils.isEmpty(name)) {
                return "";
            }
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getCode() {
            if (TextUtils.isEmpty(code)) {
                return "";
            }
            return code;
        }

        public void setCode(String code) {
            this.code = code;
        }

        public String getTypeName() {
            if (TextUtils.isEmpty(typeName)) {
                return "";
            }
            return typeName;
        }

        public void setTypeName(String typeName) {
            this.typeName = typeName;
        }

        public String getTypeCode() {
            if (TextUtils.isEmpty(typeCode)) {
                return "";
            }
            return typeCode;
        }

        public void setTypeCode(String typeCode) {
            this.typeCode = typeCode;
        }

        public ConfigDTO getConfig() {
            if (null == config) {
                return new ConfigDTO();
            }
            return config;
        }

        public void setConfig(ConfigDTO config) {
            this.config = config;
        }

        public String getLimitCount() {
            if (TextUtils.isEmpty(limitCount)) {
                return "";
            }
            return limitCount;
        }

        public void setLimitCount(String limitCount) {
            this.limitCount = limitCount;
        }

        public String getIsCategoryPanel() {
            if (TextUtils.isEmpty(isCategoryPanel)) {
                return "";
            }
            return isCategoryPanel;
        }

        public void setIsCategoryPanel(String isCategoryPanel) {
            this.isCategoryPanel = isCategoryPanel;
        }

        public String getContents() {
            if (TextUtils.isEmpty(contents)) {
                return "";
            }
            return contents;
        }

        public void setContents(String contents) {
            this.contents = contents;
        }

        public String getSubCategories() {
            if (TextUtils.isEmpty(subCategories)) {
                return "";
            }
            return subCategories;
        }

        public void setSubCategories(String subCategories) {
            this.subCategories = subCategories;
        }

        @Keep
        public static class ConfigDTO {
            private String code;
            private String imageUrl;
            private String name;
            private String typeName;
            private String jumpUrl;
            private String typeCode;
            private String backgroundImageUrl;
            private String volcengineCategoryId;

            public String getCode() {
                if (TextUtils.isEmpty(code)) {
                    return "";
                }
                return code;
            }

            public void setCode(String code) {
                this.code = code;
            }

            public String getImageUrl() {
                if (TextUtils.isEmpty(imageUrl)) {
                    return "";
                }
                return imageUrl;
            }

            public void setImageUrl(String imageUrl) {
                this.imageUrl = imageUrl;
            }

            public String getName() {
                if (TextUtils.isEmpty(name)) {
                    return "";
                }
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getTypeName() {
                if (TextUtils.isEmpty(typeName)) {
                    return "";
                }
                return typeName;
            }

            public void setTypeName(String typeName) {
                this.typeName = typeName;
            }

            public String getJumpUrl() {
                if (TextUtils.isEmpty(jumpUrl)) {
                    return "";
                }
                return jumpUrl;
            }

            public void setJumpUrl(String jumpUrl) {
                this.jumpUrl = jumpUrl;
            }

            public String getTypeCode() {
                if (TextUtils.isEmpty(typeCode)) {
                    return "";
                }
                return typeCode;
            }

            public void setTypeCode(String typeCode) {
                this.typeCode = typeCode;
            }

            public String getBackgroundImageUrl() {
                if (TextUtils.isEmpty(backgroundImageUrl)) {
                    return "";
                }
                return backgroundImageUrl;
            }

            public String getVolcengineCategoryId() {
                if (TextUtils.isEmpty(volcengineCategoryId)) {
                    return "";
                }
                return volcengineCategoryId;
            }

            public void setVolcengineCategoryId(String volcengineCategoryId) {
                this.volcengineCategoryId = volcengineCategoryId;
            }

            public void setBackgroundImageUrl(String backgroundImageUrl) {
                this.backgroundImageUrl = backgroundImageUrl;
            }
        }
    }
}
