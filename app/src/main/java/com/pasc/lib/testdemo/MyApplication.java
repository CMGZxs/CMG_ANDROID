package com.pasc.lib.testdemo;
import android.app.Application;

import common.utils.AppInit;

public class MyApplication extends Application {

    /**
     * isDebug 是否为测试环境
     * appid 机构号id
     */
    @Override
    public void onCreate() {
        super.onCreate();
        AppInit.init(this, true, "1960205"); //1960205 uat   9111184 生产
    }


}