package common.model;


import android.text.TextUtils;

import android.support.annotation.Keep;

@Keep
public class ThirdUserInfo {
    /**
     * 头像 昵称 手机号 用户id
     */
    private String headImageUrl;
    private String nickName;
    private String phoneNum;
    private String userId;

    public String getHeadImageUrl() {
        if (TextUtils.isEmpty(headImageUrl)) {
            return "";
        }
        return headImageUrl;
    }

    public void setHeadImageUrl(String headImageUrl) {
        this.headImageUrl = headImageUrl;
    }

    public String getNickName() {
        if (TextUtils.isEmpty(nickName)) {
            return "";
        }
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getPhoneNum() {
        if (TextUtils.isEmpty(phoneNum)) {
            return "";
        }
        return phoneNum;
    }

    public void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum;
    }

    public String getUserId() {
        if (TextUtils.isEmpty(userId)) {
            return "";
        }
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}
