package common.utils;


import static common.constants.Constants.success_code;

import android.annotation.SuppressLint;
import android.app.Application;
import android.content.Context;
import android.support.annotation.NonNull;
import android.text.TextUtils;



import com.alibaba.fastjson.JSON;
import com.pasc.lib.videolib.R;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.Response;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

import common.callback.JsonCallback;
import common.constants.Constants;
import common.http.ApiConstants;
import common.model.MechanismModel;


/**
 * 常用工具类
 */
public final class AppInit {
    private static SimpleDateFormat formatterYmd = new SimpleDateFormat("MM-dd");//初始化Formatter的转换格式。
    private static SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm");//初始化Formatter的转换格式。
    public static boolean mIsDebug;
    public static String appId;
    @SuppressLint("StaticFieldLeak")
    public static Context mContext;

    private AppInit() {
        throw new UnsupportedOperationException("u can't instantiate me...");
    }

    /**
     * 初始化工具类
     *
     * @param context 上下文
     */
    public static void init(@NonNull final Context context, Boolean isDebug, String mAppId) {
        AppInit.mContext = context.getApplicationContext();
//        if (isDebug) {
//            appId = Constants.YUEYANG_JGH;
//        } else {
//            appId = Constants.LIUYANG_JGH;
//        }
        appId = mAppId;
        OkGoUtils.initOkGo((Application) AppInit.mContext, appId);
        mIsDebug = isDebug;

        if (mIsDebug) {
            //UAT测试环境
            ApiConstants.getInstance().setBaseUrl("https://uat-fuse-api-gw.zhcs.csbtv.com/");
            ApiConstants.getInstance().setAdminBaseUrl("https://uat-fuse-admin.zhcs.csbtv.com/");
            ApiConstants.getInstance().setH5BaseUrl("https://uat-h5.zhcs.csbtv.com/");
        } else {
            //正式环境
            ApiConstants.getInstance().setBaseUrl("https://fuse-api-gw.zhcs.csbtv.com/");
            ApiConstants.getInstance().setAdminBaseUrl("https://fuse-admin.zhcs.csbtv.com/");
            ApiConstants.getInstance().setH5BaseUrl("https://h5.zhcs.csbtv.com/");
        }

        getCfg();
    }

    /**
     * 获取机构
     */
    private static void getCfg() {
        OkGo.<MechanismModel>get(ApiConstants.getInstance().getCfg())
                .tag("cfg")
                .params("appId", appId)
                .execute(new JsonCallback<MechanismModel>(MechanismModel.class) {
                    @Override
                    public void onSuccess(Response<MechanismModel> response) {
                        if (null == response.body()) {
                            ToastUtils.showShort(R.string.data_err);
                            return;
                        }
                        if (null == response.body().getCode()) {
                            ToastUtils.showShort(response.body().getMessage());
                            return;
                        }

                        if(null!=response.body().getCode()&&response.body().getCode().equals(success_code)) {
                            MechanismModel.DataDTO model = response.body().getData();
                            if (null != model) {
                                PersonInfoManager.getInstance().setPanId(model.getConfig().getPanId());
                                PersonInfoManager.getInstance().setCfgStr(JSON.toJSONString(model));
                                PersonInfoManager.getInstance().setLogoUrl(model.getLogo());
                                PersonInfoManager.getInstance().setIntentUrl(model.getConfig().getListUrl());
                                PersonInfoManager.getInstance().setMechanismId(model.getId());
                                PersonInfoManager.getInstance().setAppName(model.getConfig().getAppName());
                                PersonInfoManager.getInstance().setCategoryCode(model.getConfig().getCategoryCode());
//                                cfgStr = JSON.toJSONString(model);
//                                logoUrl = model.getLogo();
//                                intentUrl = model.getConfig().getListUrl();
//                                appName = model.getConfig().getAppName();
                            }
                        } else {
                            ToastUtils.showShort(response.body().getMessage());
                        }

                    }

                    @Override
                    public void onError(Response<MechanismModel> response) {
                        super.onError(response);
                        if (null != response.body()) {
                            ToastUtils.showShort(response.body().getMessage());
                            return;
                        }
                        ToastUtils.showShort( R.string.net_err);
                    }
                });
    }


    /**
     * 获取ApplicationContext
     *
     * @return ApplicationContext
     */
    public static Context getContext() {
        if (mContext != null) {
            return mContext;
        }
        throw new NullPointerException("should be initialized in application");
    }


    /**
     * 秒转换为指定格式的日期
     *
     * @param second
     * @param patten
     * @return
     */
    public static String secondToDate(long second, String patten) {

        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(second);//转换为毫秒
        Date date = calendar.getTime();
        SimpleDateFormat format = new SimpleDateFormat(patten);
        String dateString = format.format(date);
        return dateString;
    }


    /**
     * @param time 单位秒
     * @return
     */
    public static String getTimeTip(String time, String timeTag) {
        String tip = "0";

        if (TextUtils.isEmpty(time)) {
            return tip;
        }
        int s = Integer.parseInt(time);
        if (s > 60 && s < 3600) {
            tip = s / 60 + "分钟前";
        } else if (s > 3600 && s < 3600 * 24) {
            tip = s / 3600 + "小时前";
        } else if (s < 60) {
            tip = "刚刚";
        } else {
            tip = getTimeStr(timeTag);
        }
        return tip;
    }

    /**
     * @param time 单位秒
     * @return
     */
    public static String getCommentTimeTip(String time, String timeTag) {
        String tip = "0";

        if (TextUtils.isEmpty(time)) {
            return tip;
        }
        int s = Integer.parseInt(time);
        if (s > 60 && s < 3600) {
            tip = s / 60 + "分钟前";
        } else if (s > 3600 && s < 3600 * 24) {
            tip = s / 3600 + "小时前";
        } else if (s < 60) {
            tip = "刚刚";
        } else {
            tip = getTimeHMSStr(timeTag);
        }
        return tip;
    }


    public static String getTimeHMSStr(String time) {
        if (TextUtils.isEmpty(time)) {
            return "0";
        }
        formatter.setTimeZone(TimeZone.getTimeZone("GMT+8"));
        return formatter.format(new Date(Long.valueOf(time + "000")));
    }

    public static String getTimeStr(String time) {
        if (TextUtils.isEmpty(time)) {
            return "0";
        }
        formatterYmd.setTimeZone(TimeZone.getTimeZone("GMT+8"));
        return formatterYmd.format(new Date(Long.valueOf(time + "000")));
    }

    public static String getViewCountFormat(String s) {
        if (TextUtils.isEmpty(s)) {
            return "0";
        }
        int viewCount = Integer.parseInt(s);
        if (viewCount < 10000) {
            return viewCount + "";
        }
        DecimalFormat df = new DecimalFormat("0.00");//格式化小数
        String num = df.format((float) Integer.parseInt(s) / 10000);//返回的是String类型
        return num + "万";
    }
}