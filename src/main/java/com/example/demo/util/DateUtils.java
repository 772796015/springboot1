package com.example.demo.util;

import org.apache.commons.lang.StringUtils;
import org.joda.time.DateTime;
import org.joda.time.LocalDate;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import java.sql.Time;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 日期处理
 *
 *
 */
public class DateUtils {

    /** 时间格式(yyyy-MM-dd) */
    public final static String DATE_PATTERN      = "yyyy-MM-dd";
    /** 时间格式(yyyy-MM-dd HH:mm:ss) */
    public final static String DATE_TIME_PATTERN = "yyyy-MM-dd HH:mm:ss";
    /** 时间格式(yyyyMMdd) */
    public final static String DATE_T_PATTERN    = "yyyyMMdd";
    /** 时间格式(HH:mm:ss) */
    public final static String DATE_HOUR_PATTERN = "HH:mm:ss";

    /**
     * 	得到当前的时间，年月日  例如：2020-12-14
     */
    public static String getCurrentDate() {
        return new SimpleDateFormat(DATE_PATTERN).format(new Date());
    }

    /**
     * 得到当前的时间 例如：2020-12-14 10:11:41
     */
    public static String getCurrDateTime() {
        return new SimpleDateFormat(DATE_TIME_PATTERN).format(new Date());
    }

    /**
     * 获取当前日期，格式为yyyyMMdd
     * @return
     */
    public static String getCurrentTDate() {
        return new SimpleDateFormat(DATE_T_PATTERN).format(new Date());
    }

    /**
     * 获取当前时分秒信息,字符串消息
     *
     * @return 时分秒
     */
    public static String getCurrTime() {
        return new Time(new Date().getTime()).toString();
    }

    /**
     * 日期格式化 日期格式为：yyyy-MM-dd
     * @param date  日期
     * @return  返回yyyy-MM-dd格式日期
     */
    public static String format(Date date) {
        return format(date, DATE_PATTERN);
    }

    /**
     * 日期格式化 日期格式为：yyyy-MM-dd
     * @param date  日期
     * @param pattern  格式，如：DateUtils.DATE_TIME_PATTERN
     * @return  返回yyyy-MM-dd格式日期
     */
    public static String format(Date date, String pattern) {
        if (date != null) {
            SimpleDateFormat df = new SimpleDateFormat(pattern);
            return df.format(date);
        }
        return null;
    }

    /**
     * 字符串转换成日期
     * @param strDate 日期字符串
     * @param pattern 日期的格式，如：DateUtils.stringToDate("2020-10-1", "yyyy-MM-dd")，或者DateUtils.stringToDate("2020-10-1 10:50:15", "yyyy-MM-dd HH:mm:ss")等上面常量四种格式
     */
    public static Date stringToDate(String strDate, String pattern) {
        if (StringUtils.isBlank(strDate)) {
            return null;
        }

        DateTimeFormatter fmt = DateTimeFormat.forPattern(pattern);
        return fmt.parseLocalDateTime(strDate).toDate();
    }

    /**
     * 根据周数，获取开始日期、结束日期
     * @param week  周期  0本周，-1上周，-2上上周，1下周，2下下周
     * @return  返回date[0]开始日期、date[1]结束日期
     */
    public static Date[] getWeekStartAndEnd(int week) {
        DateTime dateTime = new DateTime();
        LocalDate date = new LocalDate(dateTime.plusWeeks(week));

        date = date.dayOfWeek().withMinimumValue();
        Date beginDate = date.toDate();
        Date endDate = date.plusDays(6).toDate();
        return new Date[] { beginDate, endDate };
    }

    /**
     * 对日期的【秒】进行加/减
     *
     * @param date 日期
     * @param seconds 秒数，负数为减
     * @return 加/减几秒后的日期
     */
    public static Date addDateSeconds(Date date, int seconds) {
        DateTime dateTime = new DateTime(date);
        return dateTime.plusSeconds(seconds).toDate();
    }

    /**
     * 对日期的【分钟】进行加/减
     *
     * @param date 日期
     * @param minutes 分钟数，负数为减
     * @return 加/减几分钟后的日期
     */
    public static Date addDateMinutes(Date date, int minutes) {
        DateTime dateTime = new DateTime(date);
        return dateTime.plusMinutes(minutes).toDate();
    }

    /**
     * 对日期的【小时】进行加/减
     *
     * @param date 日期
     * @param hours 小时数，负数为减
     * @return 加/减几小时后的日期
     */
    public static Date addDateHours(Date date, int hours) {
        DateTime dateTime = new DateTime(date);
        return dateTime.plusHours(hours).toDate();
    }

    /**
     * 对日期的【天】进行加/减
     *
     * @param date 日期
     * @param days 天数，负数为减
     * @return 加/减几天后的日期
     */
    public static Date addDateDays(Date date, int days) {
        DateTime dateTime = new DateTime(date);
        return dateTime.plusDays(days).toDate();
    }

    /**
     * 对日期的【周】进行加/减
     *
     * @param date 日期
     * @param weeks 周数，负数为减
     * @return 加/减几周后的日期
     */
    public static Date addDateWeeks(Date date, int weeks) {
        DateTime dateTime = new DateTime(date);
        return dateTime.plusWeeks(weeks).toDate();
    }

    /**
     * 对日期的【月】进行加/减
     *
     * @param date 日期
     * @param months 月数，负数为减
     * @return 加/减几月后的日期
     */
    public static Date addDateMonths(Date date, int months) {
        DateTime dateTime = new DateTime(date);
        return dateTime.plusMonths(months).toDate();
    }

    /**
     * 对日期的【年】进行加/减
     *
     * @param date 日期
     * @param years 年数，负数为减
     * @return 加/减几年后的日期
     */
    public static Date addDateYears(Date date, int years) {
        DateTime dateTime = new DateTime(date);
        return dateTime.plusYears(years).toDate();
    }

    /**
     * 日期相差月数
     *
     * @param
     * @param
     * @return
     */
    public static int getMonthDiff(String oYM_D, String tYM_D) {
        String oYM = oYM_D.substring(0, 4) + oYM_D.substring(5, 7);
        String tYM = tYM_D.substring(0, 4) + tYM_D.substring(5, 7);
        int tYM_Y = Integer.parseInt(tYM.substring(0, 4));
        int tYM_M = Integer.parseInt(tYM.substring(4, 6));
        int oYM_Y = Integer.parseInt(oYM.substring(0, 4));
        int oYM_M = Integer.parseInt(oYM.substring(4, 6));
        return (tYM_Y - oYM_Y) * 12 + (tYM_M - oYM_M);
    }

    /**
     * 年月相差月数
     *
     * @param
     * @param
     * @return
     */
    public static int getYearMonthDiff(String oYM_D, String tYM_D) {
        int tYM_Y = Integer.parseInt(tYM_D.substring(0, 4));
        int tYM_M = Integer.parseInt(tYM_D.substring(4, 6));
        int oYM_Y = Integer.parseInt(oYM_D.substring(0, 4));
        int oYM_M = Integer.parseInt(oYM_D.substring(4, 6));
        return (tYM_Y - oYM_Y) * 12 + (tYM_M - oYM_M);
    }

    /**
     * 传入两个月份，求这两个月份之间的月份差！<br/>
     * <code>tYM-oYM</code>
     *
     * @param oYM 被减年月
     * @param tYM 年月
     * @return 月份差
     */
    public static int getMonthDiffPlus(String oYM, String tYM) {

        if (StringUtils.isEmpty(oYM) && oYM.length() != 6) {
            throw new RuntimeException(oYM);
        }
        if (StringUtils.isEmpty(tYM) && tYM.length() != 6) {
            throw new RuntimeException(tYM);
        }
        int tYM_Y = Integer.parseInt(tYM.substring(0, 4));
        int tYM_M = Integer.parseInt(tYM.substring(4, 6));
        int oYM_Y = Integer.parseInt(oYM.substring(0, 4));
        int oYM_M = Integer.parseInt(oYM.substring(4, 6));
        return (tYM_Y - oYM_Y) * 12 + (tYM_M - oYM_M);
    }

    //调试
    public static void main(String[] args) {
        //日期处理
        System.out.println(DateUtils.stringToDate("2020-10-1 10:50:15", "yyyy-MM-dd HH:mm:ss"));//转换成yyyy-MM-dd HH:mm:ss日期格式
        System.out.println(DateUtils.stringToDate("2020-10-1", "yyyy-MM-dd"));//转换成yyyy-MM-dd 日期格式
        System.out.println(DateUtils.getYearMonthDiff("201910", "202010"));//年月相减，算出月数


        //空字符串为空判断
        // StringUtils.isEmpty()判断目标字符串是否为空，只有目标字符串是“”和null的时候才为true
        //StringUtils.isBlank()判断目标字符串是否为空，当目标字符串是“”和null和空格（当目标字符串只要有一个字符不为空格就返回false）时为true
        String a="";
        String b=" ";
        System.out.println(StringUtils.isEmpty(a)+"----------"+StringUtils.isEmpty(b));
        System.out.println(StringUtils.isBlank(b));
    }

}
