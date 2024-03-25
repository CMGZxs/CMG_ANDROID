package common.utils;

import android.text.TextUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

public enum DateUtils {
    INSTANCE;
    private SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm");//初始化Formatter的转换格式。

    private SimpleDateFormat formatterYmd = new SimpleDateFormat("yyyy-MM-dd");//初始化Formatter的转换格式。

    private SimpleDateFormat formatterMd = new SimpleDateFormat("MM-dd");//初始化Formatter的转换格式。

    private SimpleDateFormat formatterMs = new SimpleDateFormat("mm:ss");//初始化Formatter的转换格式。

    private SimpleDateFormat formatterHMS = new SimpleDateFormat("HH:mm:ss");//初始化Formatter的转换格式。

    private SimpleDateFormat formatterYMD = new SimpleDateFormat("yyyy年MM月dd日");//初始化Formatter的转换格式。

    private static SimpleDateFormat formatterYMDHMS = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//初始化Formatter的转换格式。


    public static <T> List<List<T>> averageAssign(List<T> source, int n) {
        List<List<T>> result = new ArrayList<List<T>>();
        int remaider = source.size() % n;  //(先计算出余数)
        int number = source.size() / n;  //然后是商
        int offset = 0;//偏移量
        for (int i = 0; i < n; i++) {
            List<T> value = null;
            if (remaider > 0) {
                value = source.subList(i * number + offset, (i + 1) * number + offset + 1);
                remaider--;
                offset++;
            } else {
                value = source.subList(i * number + offset, (i + 1) * number + offset);
            }
            result.add(value);
        }
        return result;
    }

    public static <T> List<List<T>> splitList(List<T> list, int len) {
        if (list == null || list.size() == 0 || len < 1) {
            return null;
        }
        //返回结果
        List<List<T>> result = new ArrayList<List<T>>();
        //传入集合长度
        int size = list.size();
        //分隔后的集合个数
        int count = (size + len - 1) / len;
        for (int i = 0; i < count; i++) {
            List<T> subList = list.subList(i * len, ((i + 1) * len > size ? size : len * (i + 1)));
            result.add(subList);
        }
        return result;
    }

    /**
     * 日期格式字符串转换成时间戳
     *
     * @param dateStr 字符串日期
     * @param format  如：yyyy-MM-dd HH:mm:ss
     * @return
     */
    public static String date2TimeStamp(String dateStr, String format) {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat(format);
            return String.valueOf(sdf.parse(dateStr).getTime());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "0";
    }

    /**
     * 获取当前日期后几天的日期
     */
    public static String getAfterCurrentDays(int days){
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DATE, days);
        String afterDays = formatterYMDHMS.format(calendar.getTime());
        return afterDays;
    }

    /**
     * 根据,号分割字符串
     *
     * @param str
     * @return
     */
    public static List<String> splitStr(String str) {
        List<String> list = Arrays.asList(str.split(","));
        return list;
    }

    /**
     * 时间戳转换成日期格式字符串
     *
     * @param mSeconds
     * @param format
     * @return
     */
    public static String timeStamp2Date(String mSeconds, String format) {
        if (mSeconds == null || mSeconds.isEmpty() || mSeconds.equals("null")) {
            return "";
        }
        if (format == null || format.isEmpty()) {
            format = "yyyy-MM-dd";
        }
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        return sdf.format(new Date(Long.valueOf(mSeconds)));
    }

    /**
     * UTC时间 ---> 当地时间
     * @param utcTime   UTC时间
     * @return
     */
    public static String utc2Local(String utcTime) {
        if (null == utcTime || TextUtils.isEmpty(utcTime)) {
            return "";
        }
        SimpleDateFormat utcFormater = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS");//UTC时间格式
        utcFormater.setTimeZone(TimeZone.getTimeZone("UTC"));
        Date gpsUTCDate = null;
        try {
            gpsUTCDate = utcFormater.parse(utcTime);
            if (null == gpsUTCDate) {
                return "";
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
        SimpleDateFormat localFormater = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//当地时间格式
        localFormater.setTimeZone(TimeZone.getDefault());
        String localTime = localFormater.format(gpsUTCDate.getTime());
        return localTime;
    }

    /**
     * 获取当前时间戳
     */
    public static long getTimeCurrent(){
        long timecurrentTimeMillis = System.currentTimeMillis();
        return timecurrentTimeMillis;
    }
}
