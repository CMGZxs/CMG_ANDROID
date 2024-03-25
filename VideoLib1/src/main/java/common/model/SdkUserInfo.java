package common.model;

import android.support.annotation.Keep;

import java.io.Serializable;

/**
 * Sdk 用户信息
 */
@Keep
public class SdkUserInfo implements Serializable {
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
    public static class DataDTO implements Serializable {
        private String token;
        private LoginSysUserVoDTO loginSysUserVo;
        private String gdyToken;
        private String mycsUserId;
        private String certificated;
        private String asideCode;
        private String thirdPartyUserId;
        private String appId;

        public String getToken() {
            return token;
        }

        public void setToken(String token) {
            this.token = token;
        }

        public LoginSysUserVoDTO getLoginSysUserVo() {
            return loginSysUserVo;
        }

        public void setLoginSysUserVo(LoginSysUserVoDTO loginSysUserVo) {
            this.loginSysUserVo = loginSysUserVo;
        }

        public String getGdyToken() {
            return gdyToken;
        }

        public void setGdyToken(String gdyToken) {
            this.gdyToken = gdyToken;
        }

        public String getMycsUserId() {
            return mycsUserId;
        }

        public void setMycsUserId(String mycsUserId) {
            this.mycsUserId = mycsUserId;
        }

        public String isCertificated() {
            return certificated;
        }

        public void setCertificated(String certificated) {
            this.certificated = certificated;
        }

        public String getAsideCode() {
            return asideCode;
        }

        public void setAsideCode(String asideCode) {
            this.asideCode = asideCode;
        }

        public String getThirdPartyUserId() {
            return thirdPartyUserId;
        }

        public void setThirdPartyUserId(String thirdPartyUserId) {
            this.thirdPartyUserId = thirdPartyUserId;
        }

        public String getAppId() {
            return appId;
        }

        public void setAppId(String appId) {
            this.appId = appId;
        }

        @Keep
        public static class LoginSysUserVoDTO implements Serializable{
            private String id;
            private String tenantId;
            private String orgId;
            private String orgName;
            private String username;
            private String nickname;
            private String cardName;
            private String head;
            private String gender;
            private String state;
            private String enableRecommend;
            private String permissionCodes;
            private String phone;
            private String cardNo;

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getTenantId() {
                return tenantId;
            }

            public void setTenantId(String tenantId) {
                this.tenantId = tenantId;
            }

            public String getOrgId() {
                return orgId;
            }

            public void setOrgId(String orgId) {
                this.orgId = orgId;
            }

            public String getOrgName() {
                return orgName;
            }

            public void setOrgName(String orgName) {
                this.orgName = orgName;
            }

            public String getUsername() {
                return username;
            }

            public void setUsername(String username) {
                this.username = username;
            }

            public String getNickname() {
                return nickname;
            }

            public void setNickname(String nickname) {
                this.nickname = nickname;
            }

            public String getCardName() {
                return cardName;
            }

            public void setCardName(String cardName) {
                this.cardName = cardName;
            }

            public String getHead() {
                return head;
            }

            public void setHead(String head) {
                this.head = head;
            }

            public String getGender() {
                return gender;
            }

            public void setGender(String gender) {
                this.gender = gender;
            }

            public String getState() {
                return state;
            }

            public void setState(String state) {
                this.state = state;
            }

            public String isEnableRecommend() {
                return enableRecommend;
            }

            public void setEnableRecommend(String enableRecommend) {
                this.enableRecommend = enableRecommend;
            }

            public String getPermissionCodes() {
                return permissionCodes;
            }

            public void setPermissionCodes(String permissionCodes) {
                this.permissionCodes = permissionCodes;
            }

            public String getPhone() {
                return phone;
            }

            public void setPhone(String phone) {
                this.phone = phone;
            }

            public String getCardNo() {
                return cardNo;
            }

            public void setCardNo(String cardNo) {
                this.cardNo = cardNo;
            }
        }
    }

}
