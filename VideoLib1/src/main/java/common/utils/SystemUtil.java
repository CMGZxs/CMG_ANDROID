package common.utils;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.provider.Settings;
import android.telephony.TelephonyManager;

import java.io.UnsupportedEncodingException;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.Locale;

/**
 * 系统工具类 
 * Created by zhuwentao on 2016-07-18. 
 */  
public class SystemUtil {  
  
    /** 
     * 获取当前手机系统语言。 
     * 
     * @return 返回当前系统语言。例如：当前设置的是“中文-中国”，则返回“zh-CN” 
     */  
    public static String getSystemLanguage() {  
        return Locale.getDefault().getLanguage();
    }  
  
    /** 
     * 获取当前系统上的语言列表(Locale列表) 
     * 
     * @return  语言列表 
     */  
    public static Locale[] getSystemLanguageList() {
        return Locale.getAvailableLocales();  
    }  
  
    /** 
     * 获取当前手机系统版本号 
     * 
     * @return  系统版本号 
     */  
    public static String getSystemVersion() {  
        return android.os.Build.VERSION.RELEASE;  
    }  
  
    /** 
     * 获取手机型号 
     * 
     * @return  手机型号 
     */  
    public static String getSystemModel() {  
        return android.os.Build.MODEL;  
    }  
  
    /** 
     * 获取手机厂商 
     * 
     * @return  手机厂商 
     */  
    public static String getDeviceBrand() {  
        return android.os.Build.BRAND;  
    }  
  
    /** 
     * 获取手机IMEI(需要“android.permission.READ_PHONE_STATE”权限) 
     * 
     * @return  手机IMEI 
     */  
    @SuppressLint("MissingPermission")
    public static String getIMEI() {
        try{
            TelephonyManager tm = (TelephonyManager) AppInit.getContext().getSystemService(Activity.TELEPHONY_SERVICE);
            if (tm != null) {
                return tm.getDeviceId();
            }
        }catch (Exception e){
            return null;
        }

        return null;  
    }

    public static String getVersionName(Context context) {

        //获取包管理器
        PackageManager pm = context.getPackageManager();
        //获取包信息
        try {
            PackageInfo packageInfo = pm.getPackageInfo(context.getPackageName(), 0);
            //返回版本号
            return packageInfo.versionName;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }

        return null;

    }

    public static String getANDROID_ID(){
        String ANDROID_ID = Settings.System.getString(AppInit.getContext().getContentResolver(), Settings.System.ANDROID_ID);
        return ANDROID_ID;
    }

    /**
     * 获取版本号
     *
     * @param context 上下文
     *
     * @return 版本号
     */
    public static int getVersionCode(Context context) {

        //获取包管理器
        PackageManager pm = context.getPackageManager();
        //获取包信息
        try {
            PackageInfo packageInfo = pm.getPackageInfo(context.getPackageName(), 0);
            //返回版本号
            return packageInfo.versionCode;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }

        return 0;

    }

    /**
     * 获取App的名称
     *
     * @param context 上下文
     *
     * @return 名称
     */
    public static String getAppName(Context context) {
        PackageManager pm = context.getPackageManager();
        //获取包信息
        try {
            PackageInfo packageInfo = pm.getPackageInfo(context.getPackageName(), 0);
            //获取应用 信息
            ApplicationInfo applicationInfo = packageInfo.applicationInfo;
            //获取albelRes
            int labelRes = applicationInfo.labelRes;
            //返回App的名称
            return context.getResources().getString(labelRes);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }

        return null;
    }

    public static String getProvidersName(){
        String ProvidersName = "";
        String IMSI = "unKnow";
        if( IMSI == null){
            return "unknow";
        }

        if(IMSI.startsWith("46000") || IMSI.startsWith("46002")){
            ProvidersName = "中国移动";
        }else if(IMSI.startsWith("46001")){
            ProvidersName = "中国联通";
        }else if (IMSI.startsWith("46003")) {
            ProvidersName = "中国电信";
        }

        try {
            ProvidersName = URLEncoder.encode(""+ProvidersName, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        return ProvidersName;
    }

    public static String getIpAddress(Context context){
        NetworkInfo info = ((ConnectivityManager) context
                .getSystemService(Context.CONNECTIVITY_SERVICE)).getActiveNetworkInfo();
        if (info != null && info.isConnected()) {
            // 3/4g网络
            if (info.getType() == ConnectivityManager.TYPE_MOBILE) {
                try {
                    for (Enumeration<NetworkInterface> en = NetworkInterface.getNetworkInterfaces(); en.hasMoreElements(); ) {
                        NetworkInterface intf = en.nextElement();
                        for (Enumeration<InetAddress> enumIpAddr = intf.getInetAddresses(); enumIpAddr.hasMoreElements(); ) {
                            InetAddress inetAddress = enumIpAddr.nextElement();
                            if (!inetAddress.isLoopbackAddress() && inetAddress instanceof Inet4Address) {
                                return inetAddress.getHostAddress();
                            }
                        }
                    }
                } catch (SocketException e) {
                    e.printStackTrace();
                }

            } else if (info.getType() == ConnectivityManager.TYPE_WIFI) {
                //  wifi网络
                WifiManager wifiManager = (WifiManager) context.getSystemService(Context.WIFI_SERVICE);
                WifiInfo wifiInfo = wifiManager.getConnectionInfo();
                String ipAddress = intIP2StringIP(wifiInfo.getIpAddress());
                return ipAddress;
            }  else if (info.getType() == ConnectivityManager.TYPE_ETHERNET){
                // 有限网络
                return getLocalIp();
            }
        }
        return null;
    }

    private static String intIP2StringIP(int ip) {
        return (ip & 0xFF) + "." +
                ((ip >> 8) & 0xFF) + "." +
                ((ip >> 16) & 0xFF) + "." +
                (ip >> 24 & 0xFF);
    }


    // 获取有限网IP
    private static String getLocalIp() {
        try {
            for (Enumeration<NetworkInterface> en = NetworkInterface
                    .getNetworkInterfaces(); en.hasMoreElements(); ) {
                NetworkInterface intf = en.nextElement();
                for (Enumeration<InetAddress> enumIpAddr = intf
                        .getInetAddresses(); enumIpAddr.hasMoreElements(); ) {
                    InetAddress inetAddress = enumIpAddr.nextElement();
                    if (!inetAddress.isLoopbackAddress()
                            && inetAddress instanceof Inet4Address) {
                        return inetAddress.getHostAddress();
                    }
                }
            }
        } catch (SocketException ex) {

        }
        return "0.0.0.0";

    }
    public static boolean isAvilible(Context context, String packageName){
        final PackageManager packageManager = context.getPackageManager();//获取packagemanager
        List< PackageInfo> pinfo = packageManager.getInstalledPackages(0);//获取所有已安装程序的包信息
        List<String> pName = new ArrayList<String>();//用于存储所有已安装程序的包名
        //从pinfo中将包名字逐一取出，压入pName list中
        if(pinfo != null){
            for(int i = 0; i < pinfo.size(); i++){
                String pn = pinfo.get(i).packageName;
                pName.add(pn);
            }
        }
        return pName.contains(packageName);//判断pName中是否有目标程序的包名，有TRUE，没有FALSE
    }

} 