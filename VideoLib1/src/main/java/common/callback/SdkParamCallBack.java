package common.callback;

import common.model.SdkUserInfo;
import common.model.ShareInfo;
import common.model.ThirdUserInfo;

public interface SdkParamCallBack {

    /**
     * 获取用户信息
     */
    ThirdUserInfo setThirdUserInfo();

    /**
     * 分享事件
     */
    void shared(ShareInfo shareInfo);

    /**
     * 去登录
     */
    void toLogin();

    /**
     * 获取是否同意收集信息协议标识 --- 0不同意  1同意
     */
    String setIsAgreePrivacy();

}
