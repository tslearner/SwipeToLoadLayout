package com.example.tianshuai.swipetoloadlayout;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by tianshuai on 2017/9/5.
 */

public class TimeUtil {
    // 时间格式模板
    /**
     * yyyy-MM-dd
     */
    public static final String TIME_FORMAT_ONE = "yyyy-MM-dd";
    /**
     * yyyy-MM-dd HH:mm
     */
    public static final String TIME_FORMAT_TWO = "yyyy-MM-dd HH:mm";
    /**
     * yyyy-MM-dd HH:mmZ
     */
    public static final String TIME_FORMAT_THREE = "yyyy-MM-dd HH:mmZ";
    /**
     * yyyy-MM-dd HH:mm:ss
     */
    public static final String TIME_FORMAT_FOUR = "yyyy-MM-dd HH:mm:ss";
    /**
     * yyyy-MM-dd HH:mm:ss.SSSZ
     */
    public static final String TIME_FORMAT_FIVE = "yyyy-MM-dd HH:mm:ss.SSSZ";
    /**
     * yyyy-MM-dd'T'HH:mm:ss.SSSZ
     */
    public static final String TIME_FORMAT_SIX = "yyyy-MM-dd'T'HH:mm:ss.SSSZ";
    /**
     * HH:mm:ss
     */
    public static final String TIME_FORMAT_SEVEN = "HH:mm:ss";
    /**
     * HH:mm:ss.SS
     */
    public static final String TIME_FORMAT_EIGHT = "HH:mm:ss.SS";
    /**
     * yyyy.MM.dd
     */
    public static final String TIME_FORMAT_9 = "yyyy.MM.dd";
    /**
     * MM月dd日
     */
//    public static final String TIME_FORMAT_10 = "MM月dd日";
    public static final String TIME_FORMAT_10 = "MM/dd";
    public static final String TIME_FORMAT_11 = "MM-dd HH:mm";
    public static final String TIME_FORMAT_12 = "yyMM";
    /**
     * yyyy年MM月dd日
     */
//    public static final String TIME_FORMAT_13 = "yyyy年MM月dd日";
    public static final String TIME_FORMAT_13 = "yyyy/MM/dd";
    /**
     * HH:mm
     */
    public static final String TIME_FORMAT_14 = "HH:mm";
    //    public static final String TIME_FORMAT_15 = "MM-dd";
//    public static final String TIME_FORMAT_16 = "yy-MM-dd";
    public static final String TIME_FORMAT_15 = "yyyy/MM/dd";
    public static final String TIME_FORMAT_16 = "yyyy/MM/dd";

    public static final String TIME_FORMAT_17 = "yyyy年MM月dd日  HH:mm";
    public static final String TIME_FORMAT_18 = "yy/MM/dd HH:mm";
    public static final String TIME_FORMAT_19 = "yyyy年MM月";
    public static final String TIME_FORMAT_20 = "MM月";
    private static SimpleDateFormat simpleDateFormat = new SimpleDateFormat();
    public static String timeShow(long date) {
        Calendar instance = Calendar.getInstance();
        Calendar time = (Calendar) instance.clone();
        time.setTimeInMillis(date);
        int iWeek = instance.get(Calendar.DAY_OF_YEAR);
        int tWeek = time.get(Calendar.DAY_OF_YEAR);
        int iDay = instance.get(Calendar.DAY_OF_WEEK);
        int tDay = time.get(Calendar.DAY_OF_WEEK);

        int iHour = instance.get(Calendar.HOUR_OF_DAY);
        int tHour = time.get(Calendar.HOUR_OF_DAY);


        if (iWeek - 7 >= tWeek) {
            // 超过一周
            return formatTime(date, TIME_FORMAT_11);
        }

        //  是否一天
        if (iDay != tDay) {
            // 是否昨天
            if (iDay - tDay == 1) {
                return "昨天 " + formatTime(date, TIME_FORMAT_14);
            } else {
                switch (tDay) {
                    case Calendar.MONDAY:
                        return "星期一 " + formatTime(date, TIME_FORMAT_14);
                    case Calendar.TUESDAY:
                        return "星期二 " + formatTime(date, TIME_FORMAT_14);
                    case Calendar.WEDNESDAY:
                        return "星期三 " + formatTime(date, TIME_FORMAT_14);
                    case Calendar.THURSDAY:
                        return "星期四 " + formatTime(date, TIME_FORMAT_14);
                    case Calendar.FRIDAY:
                        return "星期五 " + formatTime(date, TIME_FORMAT_14);
                    case Calendar.SATURDAY:
                        return "星期六 " + formatTime(date, TIME_FORMAT_14);
                    default:
                        return "星期日 " + formatTime(date, TIME_FORMAT_14);
                }

            }
        }

        if (iHour != tHour) {
            return "今日 "+formatTime(date, TIME_FORMAT_14);
        }

        int iMin = instance.get(Calendar.MINUTE);
        int tMin = time.get(Calendar.MINUTE);
        if (iMin == tMin) {
//            return "刚刚"
            return "今日 "+formatTime(date, TIME_FORMAT_14);
        } else {
            return "今日 "+formatTime(date, TIME_FORMAT_14);
        }
    }

    /**
     * 格式化时间
     */
    public static String formatTime(long time, String format) {
        simpleDateFormat.applyPattern(format);
        return simpleDateFormat.format(new Date(time));
    }
}
