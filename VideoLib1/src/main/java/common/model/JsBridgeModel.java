package common.model;

import android.support.annotation.Keep;

@Keep
public class JsBridgeModel {

    private String methodName;
    private DataDTO data;

    public String getMethodName() {
        return methodName;
    }

    public void setMethodName(String methodName) {
        this.methodName = methodName;
    }

    public DataDTO getData() {
        return data;
    }

    public void setData(DataDTO data) {
        this.data = data;
    }

    @Keep
    public static class DataDTO {

    }
}
