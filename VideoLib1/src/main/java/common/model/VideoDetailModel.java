package common.model;

import android.text.TextUtils;

import android.support.annotation.Keep;

import java.util.ArrayList;
import java.util.List;

/**
 * 视频详情model
 */
@Keep
public class VideoDetailModel {

    private String code;
    private Boolean success;
    private String message;
    private String detail;
    private List<DataDTO> data;
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

    public List<DataDTO> getData() {
        if (null == data) {
            List<DataDTO> list = new ArrayList<>();
            return list;
        }
        return data;
    }

    public void setData(List<DataDTO> data) {
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
