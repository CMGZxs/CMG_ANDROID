package common.utils;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class GetTimeAgo {
    private static final Long SECOND_MILLIS = Long.valueOf(1000);
    private static final Long MINUTE_MILLIS = 60 * SECOND_MILLIS;

    private static final Long HOUR_MILLIS = 60 * MINUTE_MILLIS;
    private static final Long DAY_MILLIS = 24 * HOUR_MILLIS;
    private static final Long MONTH_MILLIS = 30 * DAY_MILLIS;
    private static final Long YEAR_MILLIS = 12 * MONTH_MILLIS;

    /**
     * 按照毫秒来存储
     *
     * @param time
     * @return
     */
    public static String getTimeAgo(long time) {
        if (time < 1000000000000L) {
            // if timestamp given in seconds, convert to millis
            time *= 1000;
        }
        long now = System.currentTimeMillis();
        if (time > now || time <= 0) {
            return "未知时间";
        }

        final long diff = now - time;
        if (diff < MINUTE_MILLIS) {
            return "刚刚";
        } else if (diff < 2 * MINUTE_MILLIS) {
            return "1分钟前";
        } else if (diff < 50 * MINUTE_MILLIS) {
            return diff / MINUTE_MILLIS + "分钟前";
        } else if (diff < 90 * MINUTE_MILLIS) {
            return "1小时前";
        } else if (diff < 24 * HOUR_MILLIS) {
            return diff / HOUR_MILLIS + "小时前";
        } else if (diff < 48 * HOUR_MILLIS) {
            return "昨天";
        } else if (diff / DAY_MILLIS < 30) {
            return diff / DAY_MILLIS + "天前";
        } else if (diff < 2 * MONTH_MILLIS) {
            return "1个月前";
        } else if (diff < 12 * MONTH_MILLIS) {
            return   diff/MONTH_MILLIS + "个月前";
        } else if (diff < 2 * YEAR_MILLIS) {
            return "1年前";
        } else {
            return diff / YEAR_MILLIS + "年前";
        }
    }

    public static String timetodate(String time) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(Long.valueOf(time));
        SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");//这里的格式可换"yyyy年-MM月dd日-HH时mm分ss秒"等等格式
        String date = sf.format(calendar.getTime());
        return date;

    }
}
