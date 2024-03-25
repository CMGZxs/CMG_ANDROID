package common.model;

import android.text.TextUtils;

import android.support.annotation.Keep;

@Keep
public class TokenModel {


    private Integer code;
    private Boolean success;
    private String message;
    private String detail;
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
        private String token;
        private LoginSysUserVoDTO loginSysUserVo;
        private String gdyToken;

        public String getToken() {
            if (TextUtils.isEmpty(token)) {
                return "";
            }
            return token;
        }

        public void setToken(String token) {
            this.token = token;
        }

        public LoginSysUserVoDTO getLoginSysUserVo() {
            if (null == loginSysUserVo) {
                return new LoginSysUserVoDTO();
            }
            return loginSysUserVo;
        }

        public void setLoginSysUserVo(LoginSysUserVoDTO loginSysUserVo) {
            this.loginSysUserVo = loginSysUserVo;
        }

        public String getGdyToken() {
            if (TextUtils.isEmpty(gdyToken)) {
                return "";
            }
            return gdyToken;
        }

        public void setGdyToken(String gdyToken) {
            this.gdyToken = gdyToken;
        }

        @Keep
        public static class LoginSysUserVoDTO {
            private String id;
            private String username;
            private String nickname;
            private String head;
            private String gender;
            private String state;
            private String permissionCodes;

            public String getId() {
                if (TextUtils.isEmpty(id)) {
                    return "";
                }
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getUsername() {
                if (TextUtils.isEmpty(username)) {
                    return "";
                }
                return username;
            }

            public void setUsername(String username) {
                this.username = username;
            }

            public String getNickname() {
                if (TextUtils.isEmpty(nickname)) {
                    return "";
                }
                return nickname;
            }

            public void setNickname(String nickname) {
                this.nickname = nickname;
            }

            public String getHead() {
                if (TextUtils.isEmpty(head)) {
                    return "";
                }
                return head;
            }

            public void setHead(String head) {
                this.head = head;
            }

            public String getGender() {
                if (TextUtils.isEmpty(gender)) {
                    return "";
                }
                return gender;
            }

            public void setGender(String gender) {
                this.gender = gender;
            }

            public String getState() {
                if (TextUtils.isEmpty(state)) {
                    return "";
                }
                return state;
            }

            public void setState(String state) {
                this.state = state;
            }

            public String getPermissionCodes() {
                if (TextUtils.isEmpty(permissionCodes)) {
                    return "";
                }
                return permissionCodes;
            }

            public void setPermissionCodes(String permissionCodes) {
                this.permissionCodes = permissionCodes;
            }
        }
    }
}
