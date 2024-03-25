package model.bean;

import android.support.annotation.Keep;

import java.util.List;

@Keep
public class SZContentLoadMoreModel {

    private int code;
    private boolean success;
    private String message;
    private Object detail;
    private List<SZContentModel.DataDTO.ContentsDTO> data;
    private String time;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
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

    public void setDetail(Object detail) {
        this.detail = detail;
    }

    public List<SZContentModel.DataDTO.ContentsDTO> getData() {
        return data;
    }

    public void setData(List<SZContentModel.DataDTO.ContentsDTO> data) {
        this.data = data;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
