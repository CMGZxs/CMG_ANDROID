package common.model;


import android.support.annotation.Keep;

import java.util.List;

@Keep
public class ContentBuriedPointModel {
    public static ContentBuriedPointModel contentBuriedPointInstance;
    private HeaderDTO header;
    private UserDTO user;
    private List<EventsDTO> events;
    private IdsDTO ids;

    public static ContentBuriedPointModel getInstance() {
        if (contentBuriedPointInstance == null) {
            synchronized (ContentBuriedPointModel.class) {
                if (contentBuriedPointInstance == null) {
                    contentBuriedPointInstance = new ContentBuriedPointModel();
                }
            }
        }
        return contentBuriedPointInstance;
    }

    public HeaderDTO getHeader() {
        return header;
    }

    public void setHeader(HeaderDTO header) {
        this.header = header;
    }

    public UserDTO getUser() {
        return user;
    }

    public void setUser(UserDTO user) {
        this.user = user;
    }

    public List<EventsDTO> getEvents() {
        return events;
    }

    public void setEvents(List<EventsDTO> events) {
        this.events = events;
    }

    public IdsDTO getIds() {
        return ids;
    }

    public void setIds(IdsDTO ids) {
        this.ids = ids;
    }

    @Keep
    public static class HeaderDTO {
        private String ab_sdk_version;
        private String app_channel;
        private String app_name;
        private String app_package;
        private String app_platform;
        private String app_version;
        private String client_ip;
        private CustomDTO custom;
        private String device_model;
        private String device_brand;
        private String os_name;
        private String os_version;
        private String platform;
        private String traffic_type;

        public String getAb_sdk_version() {
            return ab_sdk_version;
        }

        public void setAb_sdk_version(String ab_sdk_version) {
            this.ab_sdk_version = ab_sdk_version;
        }

        public String getApp_channel() {
            return app_channel;
        }

        public void setApp_channel(String app_channel) {
            this.app_channel = app_channel;
        }

        public String getApp_name() {
            return app_name;
        }

        public void setApp_name(String app_name) {
            this.app_name = app_name;
        }

        public String getApp_package() {
            return app_package;
        }

        public void setApp_package(String app_package) {
            this.app_package = app_package;
        }

        public String getApp_platform() {
            return app_platform;
        }

        public void setApp_platform(String app_platform) {
            this.app_platform = app_platform;
        }

        public String getApp_version() {
            return app_version;
        }

        public void setApp_version(String app_version) {
            this.app_version = app_version;
        }

        public String getClient_ip() {
            return client_ip;
        }

        public void setClient_ip(String client_ip) {
            this.client_ip = client_ip;
        }

        public CustomDTO getCustom() {
            return custom;
        }

        public void setCustom(CustomDTO custom) {
            this.custom = custom;
        }

        public String getDevice_model() {
            return device_model;
        }

        public void setDevice_model(String device_model) {
            this.device_model = device_model;
        }

        public String getDevice_brand() {
            return device_brand;
        }

        public void setDevice_brand(String device_brand) {
            this.device_brand = device_brand;
        }

        public String getOs_name() {
            return os_name;
        }

        public void setOs_name(String os_name) {
            this.os_name = os_name;
        }

        public String getOs_version() {
            return os_version;
        }

        public void setOs_version(String os_version) {
            this.os_version = os_version;
        }

        public String getPlatform() {
            return platform;
        }

        public void setPlatform(String platform) {
            this.platform = platform;
        }

        public String getTraffic_type() {
            return traffic_type;
        }

        public void setTraffic_type(String traffic_type) {
            this.traffic_type = traffic_type;
        }

        public static class CustomDTO {
            private String app_language;
            private String app_region;
            private String language;
            private String region;
            private String timezone;
            private String utm_campaign;
            private String utm_content;
            private String utm_medium;
            private String utm_source;
            private String utm_term;

            public String getApp_language() {
                return app_language;
            }

            public void setApp_language(String app_language) {
                this.app_language = app_language;
            }

            public String getApp_region() {
                return app_region;
            }

            public void setApp_region(String app_region) {
                this.app_region = app_region;
            }

            public String getLanguage() {
                return language;
            }

            public void setLanguage(String language) {
                this.language = language;
            }

            public String getRegion() {
                return region;
            }

            public void setRegion(String region) {
                this.region = region;
            }

            public String getTimezone() {
                return timezone;
            }

            public void setTimezone(String timezone) {
                this.timezone = timezone;
            }

            public String getUtm_campaign() {
                return utm_campaign;
            }

            public void setUtm_campaign(String utm_campaign) {
                this.utm_campaign = utm_campaign;
            }

            public String getUtm_content() {
                return utm_content;
            }

            public void setUtm_content(String utm_content) {
                this.utm_content = utm_content;
            }

            public String getUtm_medium() {
                return utm_medium;
            }

            public void setUtm_medium(String utm_medium) {
                this.utm_medium = utm_medium;
            }

            public String getUtm_source() {
                return utm_source;
            }

            public void setUtm_source(String utm_source) {
                this.utm_source = utm_source;
            }

            public String getUtm_term() {
                return utm_term;
            }

            public void setUtm_term(String utm_term) {
                this.utm_term = utm_term;
            }
        }
    }

    @Keep
    public static class UserDTO {
        private String bddid;
        private String user_unique_id;

        public String getBddid() {
            return bddid;
        }

        public void setBddid(String bddid) {
            this.bddid = bddid;
        }

        public String getUser_unique_id() {
            return user_unique_id;
        }

        public void setUser_unique_id(String user_unique_id) {
            this.user_unique_id = user_unique_id;
        }
    }

    @Keep
    public static class IdsDTO {
        private String device_id;
        private String user_unique_id;
        private String user_id;

        public String getDevice_id() {
            return device_id;
        }

        public void setDevice_id(String device_id) {
            this.device_id = device_id;
        }

        public String getUser_unique_id() {
            return user_unique_id;
        }

        public void setUser_unique_id(String user_unique_id) {
            this.user_unique_id = user_unique_id;
        }

        public String getUser_id() {
            return user_id;
        }

        public void setUser_id(String user_id) {
            this.user_id = user_id;
        }
    }

    @Keep
    public static class EventsDTO {
        private String event;
        private String local_time_ms;
        private String params;

        public String getEvent() {
            return event;
        }

        public void setEvent(String event) {
            this.event = event;
        }

        public String getLocal_time_ms() {
            return local_time_ms;
        }

        public void setLocal_time_ms(String local_time_ms) {
            this.local_time_ms = local_time_ms;
        }

        public String getParams() {
            return params;
        }

        public void setParams(String params) {
            this.params = params;
        }
    }
}
