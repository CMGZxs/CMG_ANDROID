package model.bean;

import android.text.TextUtils;

import android.support.annotation.Keep;

@Keep
public class ResponseBean {

    private String code;
    private String success;
    private String message;
    private String detail;
    private String data;
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

    public String getData() {
        if (TextUtils.isEmpty(data)) {
            return "";
        }
        return data;
    }

    public void setData(String data) {
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
}
