package common.utils;

import android.text.TextUtils;

import java.math.BigDecimal;
import java.text.DecimalFormat;

public class NumberFormatTool {
    public static boolean isNumeric(String str) {
        if (null == str || TextUtils.isEmpty(str)) {
            return false;
        }

        for (int i = str.length(); --i >= 0; ) {
            if (!Character.isDigit(str.charAt(i))) {
                return false;
            }
        }
        return true;
    }

    public static String getNumStr(String str){
        if (isNumeric(str)) {
            return str;
        } else {
            return "0";
        }
    }

    public static StringBuffer formatNum(int num, Boolean b) {
        if (!isNumeric(String.valueOf(num))) {
            StringBuffer sb = new StringBuffer();
            sb.append("");
            return sb;
        }
        StringBuffer sb = new StringBuffer();
        BigDecimal b0 = new BigDecimal("100");
        BigDecimal b1 = new BigDecimal("10000");
        BigDecimal b2 = new BigDecimal("100000000");
        BigDecimal b3 = new BigDecimal(num);

        String formatNumStr = "";
        String unit = "";

        // 以百为单位处理
        if (b) {
            if (b3.compareTo(b0) == 0 || b3.compareTo(b0) == 1) {
                return sb.append("99+");
            }
            return sb.append(num);
        }

        // 以万为单位处理
        if (b3.compareTo(b1) == -1) {
            formatNumStr = b3.toString();
        } else if ((b3.compareTo(b1) == 0 && b3.compareTo(b1) == 1)
                || b3.compareTo(b2) == -1) {
            unit = "万";

            formatNumStr = b3.divide(b1).toString();
        } else if (b3.compareTo(b2) == 0 || b3.compareTo(b2) == 1) {
            unit = "亿";
            formatNumStr = b3.divide(b2).toString();

        }
        if (!"".equals(formatNumStr)) {
            int i = formatNumStr.indexOf(".");
            if (i == -1) {
                sb.append(formatNumStr).append(unit);
            } else {
                i = i + 1;
                String v = formatNumStr.substring(i, i + 1);
                if (!v.equals("0")) {
                    sb.append(formatNumStr.substring(0, i + 1)).append(unit);
                } else {
                    sb.append(formatNumStr.substring(0, i - 1)).append(unit);
                }
            }
        }
        if (sb.length() == 0) {
            return sb.append("0");
        }
        return sb;
    }

    public static StringBuffer formatNum(long num, Boolean b) {
        if (!isNumeric(String.valueOf(num))) {
            StringBuffer sb = new StringBuffer();
            sb.append("");
            return sb;
        }
        StringBuffer sb = new StringBuffer();
        BigDecimal b0 = new BigDecimal("100");
        BigDecimal b1 = new BigDecimal("10000");
        BigDecimal b2 = new BigDecimal("100000000");
        BigDecimal b3 = new BigDecimal(num);

        String formatNumStr = "";
        String unit = "";

        // 以百为单位处理
        if (b) {
            if (b3.compareTo(b0) == 0 || b3.compareTo(b0) == 1) {
                return sb.append("99+");
            }
            return sb.append(num);
        }

        // 以万为单位处理
        if (b3.compareTo(b1) == -1) {
            formatNumStr = b3.toString();
        } else if ((b3.compareTo(b1) == 0 && b3.compareTo(b1) == 1)
                || b3.compareTo(b2) == -1) {
            unit = "万";

            formatNumStr = b3.divide(b1).toString();
        } else if (b3.compareTo(b2) == 0 || b3.compareTo(b2) == 1) {
            unit = "亿";
            formatNumStr = b3.divide(b2).toString();

        }
        if (!"".equals(formatNumStr)) {
            int i = formatNumStr.indexOf(".");
            if (i == -1) {
                sb.append(formatNumStr).append(unit);
            } else {
                i = i + 1;
                String v = formatNumStr.substring(i, i + 1);
                if (!v.equals("0")) {
                    sb.append(formatNumStr.substring(0, i + 1)).append(unit);
                } else {
                    sb.append(formatNumStr.substring(0, i - 1)).append(unit);
                }
            }
        }
        if (sb.length() == 0) {
            return sb.append("0");
        }
        return sb;
    }

    /**
     * DecimalFormat转换最简便
     */
    public static String save2Num(double f) {
        DecimalFormat df = new DecimalFormat("#.00");
        return df.format(f);
    }

    /**
     * @return double
     * @description 求两个double数据类型相除，并解决科学计数法问题
     */
    public static String division(double v1, double v2, int scale) {
        if (scale < 0) {
            throw new IllegalArgumentException(
                    "The scale must be a positive integer or zero");
        }
        BigDecimal b1 = new BigDecimal(Double.toString(v1));
        BigDecimal b2 = new BigDecimal(Double.toString(v2));
        double resultValue = b1.divide(b2, scale, BigDecimal.ROUND_HALF_UP).doubleValue();
        System.out.println("出现科学计数法:" + resultValue);
        //防止出现科学计数法
        BigDecimal bigDecimal1 = new BigDecimal(resultValue);
        String finishResult = bigDecimal1.setScale(5, BigDecimal.ROUND_HALF_UP).toString();
        System.out.println("解决科学计算法:" + finishResult);
        return finishResult;
    }

    public static String division(double num) {
        //防止出现科学计数法
        BigDecimal bigDecimal = new BigDecimal(num * 100);//因为埋点需要乘以100 0-100百分比
        String finishResult = formatToNumber(bigDecimal.setScale(6, BigDecimal.ROUND_HALF_EVEN));
        return finishResult;
    }

    /**
     * @return
     * @desc 1.0~1之间的BigDecimal小数，格式化后失去前面的0,则前面直接加上0。
     * 2.传入的参数等于0，则直接返回字符串"0.00"
     * 3.大于1的小数，直接格式化返回字符串
     */
    public static String formatToNumber(BigDecimal obj) {
        DecimalFormat df = new DecimalFormat("#.000000");
        if (obj.compareTo(BigDecimal.ZERO) == 0) {
            return "0.00";
        } else if (obj.compareTo(BigDecimal.ZERO) > 0 && obj.compareTo(new BigDecimal(1)) < 0) {
            return "0" + df.format(obj);
        } else {
            return df.format(obj);
        }
    }
}
