package common.callback;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;


import common.model.JumpToNativePageModel;
import common.model.SdkUserInfo;
import common.model.ShareInfo;
import common.model.ThirdUserInfo;
import event.SingleLiveEvent;
import ui.fragment.WebFragment;

public class SdkInteractiveParam {
    private String TAG = "SdkInteractiveParam";
    public SdkParamCallBack callBack;
    public static SdkInteractiveParam sdkParam;
    public SingleLiveEvent<ThirdUserInfo> userInfoEvent = new SingleLiveEvent<>();

    private SdkInteractiveParam() {
    }

    public static SdkInteractiveParam getInstance() {
        if (sdkParam == null) {
            synchronized (SdkInteractiveParam.class) {
                if (sdkParam == null) {
                    sdkParam = new SdkInteractiveParam();
                }
            }
        }
        return sdkParam;
    }

    public void setSdkCallBack(SdkParamCallBack callBack) {
        this.callBack = callBack;
    }

    /**
     * 传递分享对象
     *
     * @param shareInfo 分享要素
     */
    public void shared(ShareInfo shareInfo) {
        if (callBack == null) {
            Log.e(TAG, "获取参数失败，请重试");
        } else {
            callBack.shared(shareInfo);
        }
    }

    /**
     * Sdk去登录
     */
    public void toLogin() {
        if (callBack == null) {
            Log.e(TAG, "获取参数失败，请重试");
        } else {
            callBack.toLogin();
        }
    }

    /**
     * 获取用户信息
     */
    public ThirdUserInfo getUserInfo() {
        if (callBack == null) {
            Log.e(TAG, "获取参数失败，请重试");
            return null;
        } else {
            return callBack.setThirdUserInfo();
        }
    }

    public String getIsAgreePrivacy() {
        if (callBack == null) {
            Log.e(TAG, "获取参数失败，请重试");
            return "0";
        } else {
            return callBack.setIsAgreePrivacy();
        }
    }

    /**
     * 获取webFragment实例
     *
     * @param context
     * @param widgetId
     */
    public WebFragment getWebFragment(Context context, JumpToNativePageModel param,
                                      String intent, ShareInfo shareInfo, int widgetId, boolean toolbarIsShow) {
        FragmentTransaction transaction = ((FragmentActivity) context).getSupportFragmentManager().beginTransaction();
        WebFragment webFragment = WebFragment.newInstance(param, intent, shareInfo, toolbarIsShow);
        transaction.add(widgetId, webFragment);
        transaction.commitAllowingStateLoss();
        return webFragment;
    }

    public void clearFragment(Context context, Fragment fragment) {
        FragmentTransaction transaction = ((FragmentActivity) context).getSupportFragmentManager().beginTransaction();
        transaction.remove(fragment);
        transaction.commitAllowingStateLoss();
    }
}
