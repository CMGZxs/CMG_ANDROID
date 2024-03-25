package utils;

import android.os.Build;
import android.text.TextUtils;

import common.utils.SPUtils;

public class UUIDUtils {
    private static String sUUId;//先创一个uuid字符串

    /**
     * 设备唯一id
     */
    public static String deviceUUID() {
        if (TextUtils.isEmpty(sUUId)) {//判空,如果是空的才新建一个
            try {//用build获取机器设备的信息
                //一共13位  如果位数不够可以继续添加其他信息
                sUUId = "" + Build.BOARD.length() % 10 + Build.BRAND.length() % 10 +
                        Build.CPU_ABI.length() % 10 + Build.DEVICE.length() % 10 +
                        Build.DISPLAY.length() % 10 + Build.HOST.length() % 10 +
                        Build.ID.length() % 10 + Build.MANUFACTURER.length() % 10 +
                        Build.MODEL.length() % 10 + Build.PRODUCT.length() % 10 +
                        Build.TAGS.length() % 10 + Build.TYPE.length() % 10 +
                        Build.USER.length() % 10;
            } catch (Exception e) {
                sUUId = null;//有异常就设置为空
            }
        }

        //如果获取不到系统的设备id,根据时间戳随机生成一个,并且保存起来,但是这样会导致每次卸载应用再安装产生新的设备id
        if (TextUtils.isEmpty(sUUId)) {//如果是空的
            final String preferenceKey = "uuid";//缓存字段为uuid
            sUUId = SPUtils.getInstance().getString(preferenceKey, null);//从缓存里面再获取一次
            if (TextUtils.isEmpty(sUUId)) {//实在没有就重新获取了
                sUUId = MD5Utils.digest(String.valueOf(System.currentTimeMillis()));//根据时间戳,且用md5加密获得新的sUUId
                SPUtils.getInstance().put(preferenceKey, sUUId);//放进缓存
            }
        }
        return sUUId;
    }
}
