package common.utils;


import static common.utils.AppInit.mIsDebug;

import android.text.TextUtils;


public class DebugLogUtils {
    public static void DebugLog(String str) {
        if (TextUtils.isEmpty(str)) {
            return;
        }
        if (mIsDebug) {
            ToastUtils.showShort(str);
        }
    }

    public static void DebugLog(Integer str) {
        if (null == str) {
            return;
        }
        if (mIsDebug) {
            ToastUtils.showShort(str);
        }
    }
}
