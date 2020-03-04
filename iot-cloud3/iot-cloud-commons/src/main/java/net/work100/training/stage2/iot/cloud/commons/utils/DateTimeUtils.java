package net.work100.training.stage2.iot.cloud.commons.utils;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;

/**
 * <p>Title: DateTimeUtils</p>
 * <p>Description: </p>
 * <p>Url: http://www.work100.net/tools/code-java/datetime-utils.html</p>
 *
 * @author liuxiaojun
 * @date 2020-02-02 20:26
 * ------------------- History -------------------
 * <date>      <author>       <desc>
 * 2020-02-02   liuxiaojun     初始创建
 * -----------------------------------------------
 */
public class DateTimeUtils {
    private static final String DEFAULT_DATE_PATTERN = "yyyy-MM-dd";
    private static final String DEFAULT_TIME_PATTERN = "yyyy-MM-dd HH:mm:ss";


    /**
     * 获取当前时间字符串
     *
     * @return
     */
    public static String currentTime() {
        return currentTime("");
    }

    /**
     * 获取当前时间字符串
     *
     * @param pattern 格式，默认：yyyy-MM-dd HH:mm:ss
     * @return
     */
    public static String currentTime(String pattern) {
        if (isEmpty(pattern)) {
            pattern = DEFAULT_TIME_PATTERN;
        }
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern);
        return LocalDateTime.now(ZoneOffset.of("+8")).format(formatter);
    }

    /**
     * 获取当前日期字符串
     *
     * @return
     */
    public static String currentDate() {
        return currentDate(DEFAULT_DATE_PATTERN);
    }

    /**
     * 获取当前日期字符串
     *
     * @param pattern 格式，默认：yyyy-MM-dd
     * @return
     */
    public static String currentDate(String pattern) {
        if (isEmpty(pattern)) {
            pattern = DEFAULT_DATE_PATTERN;
        }
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern);
        return LocalDateTime.now(ZoneOffset.of("+8")).format(formatter);
    }


    /**
     * 获取当前周的日期集合
     *
     * @return
     */
    public static String[] currentWeekDays() {
        return currentWeekDays(DEFAULT_DATE_PATTERN);
    }

    /**
     * 获取当前周的日期集合
     *
     * @param pattern 格式，默认：yyyy-MM-dd
     * @return
     */
    public static String[] currentWeekDays(String pattern) {
        if (isEmpty(pattern)) {
            pattern = DEFAULT_DATE_PATTERN;
        }
        String[] weekDates = {"", "", "", "", "", "", ""};
        int dayOfWeek = dayOfWeek();
        switch (dayOfWeek) {
            case 1:
                weekDates[0] = currentDate();
                weekDates[1] = plusDays(1, pattern);
                weekDates[2] = plusDays(2, pattern);
                weekDates[3] = plusDays(3, pattern);
                weekDates[4] = plusDays(4, pattern);
                weekDates[5] = plusDays(5, pattern);
                weekDates[6] = plusDays(6, pattern);
                break;
            case 2:
                weekDates[0] = minusDays(1, pattern);
                weekDates[1] = currentDate();
                weekDates[2] = plusDays(1, pattern);
                weekDates[3] = plusDays(2, pattern);
                weekDates[4] = plusDays(3, pattern);
                weekDates[5] = plusDays(4, pattern);
                weekDates[6] = plusDays(5, pattern);
                break;
            case 3:
                weekDates[0] = minusDays(2, pattern);
                weekDates[1] = minusDays(1, pattern);
                weekDates[2] = currentDate();
                weekDates[3] = plusDays(1, pattern);
                weekDates[4] = plusDays(2, pattern);
                weekDates[5] = plusDays(3, pattern);
                weekDates[6] = plusDays(4, pattern);
                break;
            case 4:
                weekDates[0] = minusDays(3, pattern);
                weekDates[1] = minusDays(2, pattern);
                weekDates[2] = minusDays(1, pattern);
                weekDates[3] = currentDate();
                weekDates[4] = plusDays(1, pattern);
                weekDates[5] = plusDays(2, pattern);
                weekDates[6] = plusDays(3, pattern);
                break;
            case 5:
                weekDates[0] = minusDays(4, pattern);
                weekDates[1] = minusDays(3, pattern);
                weekDates[2] = minusDays(2, pattern);
                weekDates[3] = minusDays(1, pattern);
                weekDates[4] = currentDate();
                weekDates[5] = plusDays(1, pattern);
                weekDates[6] = plusDays(2, pattern);
                break;
            case 6:
                weekDates[0] = minusDays(5, pattern);
                weekDates[1] = minusDays(4, pattern);
                weekDates[2] = minusDays(3, pattern);
                weekDates[3] = minusDays(2, pattern);
                weekDates[4] = minusDays(1, pattern);
                weekDates[5] = currentDate();
                weekDates[6] = plusDays(1, pattern);
                break;
            case 7:
                weekDates[0] = minusDays(6, pattern);
                weekDates[1] = minusDays(5, pattern);
                weekDates[2] = minusDays(4, pattern);
                weekDates[3] = minusDays(3, pattern);
                weekDates[4] = minusDays(2, pattern);
                weekDates[5] = minusDays(1, pattern);
                weekDates[6] = currentDate();
                break;
        }
        return weekDates;
    }

    /**
     * 今天是周几(第一天为周日)
     *
     * @return
     */
    public static int dayOfWeek() {
        return dayOfWeek(true);
    }

    /**
     * 今天是周几
     *
     * @param firstDayIsSunday 周日为开始日
     * @return
     */
    public static int dayOfWeek(boolean firstDayIsSunday) {
        try {
            SimpleDateFormat format = new SimpleDateFormat(DEFAULT_DATE_PATTERN);
            Date today = format.parse(currentDate());
            Calendar c = Calendar.getInstance();
            c.setTime(today);
            int weekDay = c.get(Calendar.DAY_OF_WEEK);
            if (firstDayIsSunday) {
                return weekDay;
            }
            return weekDay + 1;
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    /**
     * 在当前时间基础上增加分钟数
     *
     * @param minutes 分钟数
     * @param pattern 格式，默认：yyyy-MM-dd HH:mm:ss
     * @return
     */
    public static String plusMinutes(int minutes, String pattern) {
        if (isEmpty(pattern)) {
            pattern = DEFAULT_TIME_PATTERN;
        }
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern);
        return LocalDateTime.now(ZoneOffset.of("+8")).plusMinutes(minutes).format(formatter);
    }

    /**
     * 在当前时间基础上减少分钟数，并转为时间戳
     *
     * @param minutes 分钟数
     * @param pattern 格式，默认：yyyy-MM-dd HH:mm:ss
     * @return
     */
    public static long plusMinutesToTimestamp(int minutes, String pattern) {
        return timeToTimestamp(minusDays(minutes, pattern), pattern);
    }

    /**
     * 在当前时间基础上减少分钟
     *
     * @param minutes 分钟数
     * @param pattern 格式，默认：yyyy-MM-dd HH:mm:ss
     * @return
     */
    public static String minusMinutes(int minutes, String pattern) {
        if (isEmpty(pattern)) {
            pattern = DEFAULT_TIME_PATTERN;
        }
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern);
        return LocalDateTime.now(ZoneOffset.of("+8")).minusMinutes(minutes).format(formatter);
    }

    /**
     * 在当前时间基础上减少分钟，并转为时间戳
     *
     * @param minutes 分钟数
     * @param pattern 格式，默认：yyyy-MM-dd HH:mm:ss
     * @return
     */
    public static long minusMinutesToTimestamp(int minutes, String pattern) {
        return timeToTimestamp(minusMinutes(minutes, pattern), pattern);
    }

    /**
     * 增加天数
     *
     * @param days    天数
     * @param pattern 格式，默认：yyyy-MM-dd HH:mm:ss
     * @return
     */
    public static String plusDays(int days, String pattern) {
        if (isEmpty(pattern)) {
            pattern = DEFAULT_TIME_PATTERN;
        }
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern);
        return LocalDateTime.now(ZoneOffset.of("+8")).plusDays(days).format(formatter);
    }

    /**
     * 增加天数并转化为时间戳
     *
     * @param days    天数
     * @param pattern 格式，默认：yyyy-MM-dd HH:mm:ss
     * @return
     */
    public static long plusDaysToTimestamp(int days, String pattern) {
        return timeToTimestamp(plusDays(days, pattern), pattern);
    }

    /**
     * 减少天数
     *
     * @param days    天数
     * @param pattern 格式，默认：yyyy-MM-dd HH:mm:ss
     * @return
     */
    public static String minusDays(int days, String pattern) {
        if (isEmpty(pattern)) {
            pattern = DEFAULT_TIME_PATTERN;
        }
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern);
        return LocalDateTime.now(ZoneOffset.of("+8")).minusDays(days).format(formatter);
    }

    /**
     * 减少天数并转化为时间戳
     *
     * @param days    天数
     * @param pattern 格式，默认：yyyy-MM-dd HH:mm:ss
     * @return
     */
    public static long minusDaysToTimestamp(int days, String pattern) {
        return timeToTimestamp(minusDays(days, pattern), pattern);
    }

    /**
     * 获取当前时间戳
     *
     * @return
     */
    public static long currentTimestamp() {
        return timeToTimestamp(currentTime());
    }

    /**
     * 获取当前时间戳
     *
     * @param pattern 格式，默认：yyyy-MM-dd HH:mm:ss
     * @return
     */
    public static long currentTimestamp(String pattern) {
        return timeToTimestamp(currentTime(pattern));
    }

    /**
     * 时间戳转时间字符串
     *
     * @param timestamp long类型时间戳
     * @return
     */
    public static String timestampToTime(long timestamp) {
        return timestampToTime(timestamp, "");
    }

    /**
     * 时间戳转时间字符串
     *
     * @param ts      long类型时间戳
     * @param pattern 格式，默认：yyyy-MM-dd HH:mm:ss
     * @return
     */
    public static String timestampToTime(long ts, String pattern) {
        if (isEmpty(pattern)) {
            pattern = DEFAULT_TIME_PATTERN;
        }
        SimpleDateFormat format = new SimpleDateFormat(pattern);
        return format.format(ts);
    }

    /**
     * 时间字符串转时间戳
     *
     * @param time 时间字符串
     * @return
     */
    public static long timeToTimestamp(String time) {
        return timeToTimestamp(time, "");
    }

    /**
     * 时间字符串转时间戳
     *
     * @param time    时间字符串
     * @param pattern 格式，默认：yyyy-MM-dd HH:mm:ss
     * @return
     */
    public static long timeToTimestamp(String time, String pattern) {
        try {
            if (isEmpty(pattern)) {
                pattern = DEFAULT_TIME_PATTERN;
            }
            SimpleDateFormat format = new SimpleDateFormat(pattern);
            return format.parse(time).getTime();
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
    }

    /**
     * 获取某月的最后一天
     *
     * @param firstDayOfMonth 第一天
     * @param pattern         格式，默认：yyyy-MM-dd
     * @return
     */
    public static String getLastDayOfMonth(String firstDayOfMonth, String pattern) {
        try {
            if (isEmpty(pattern)) {
                pattern = DEFAULT_DATE_PATTERN;
            }

            SimpleDateFormat format = new SimpleDateFormat(pattern);
            Date date = format.parse(firstDayOfMonth);

            Calendar cal = Calendar.getInstance();
            cal.setTime(date);

            // 获取某月最大天数
            int lastDay = cal.getActualMaximum(Calendar.DAY_OF_MONTH);
            // 设置日历中月份的最大天数
            cal.set(Calendar.DAY_OF_MONTH, lastDay);
            //格式化日期
            return format.format(cal.getTime());
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 获取某月天数
     *
     * @param firstDayOfMonth 第一天
     * @param pattern         格式，默认：yyyy-MM-dd
     * @return
     */
    public static int getDaysOfMonth(String firstDayOfMonth, String pattern) {
        try {
            if (isEmpty(pattern)) {
                pattern = DEFAULT_DATE_PATTERN;
            }

            SimpleDateFormat formatter = new SimpleDateFormat(pattern);
            Date date = formatter.parse(firstDayOfMonth);

            Calendar cal = Calendar.getInstance();
            cal.setTime(date);

            // 获取某月最小天数和最大天数
            int firstDay = cal.getActualMinimum(Calendar.DAY_OF_MONTH);
            int lastDay = cal.getActualMaximum(Calendar.DAY_OF_MONTH);
            return lastDay - firstDay + 1;
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
    }

    /**
     * 格式化日期
     *
     * @param date    日期
     * @param pattern 格式，默认：yyyy-MM-dd
     * @return
     */
    public static String formatDate(String date, String pattern) {
        try {
            if (isEmpty(pattern)) {
                pattern = DEFAULT_DATE_PATTERN;
            }

            DateFormat formatter = new SimpleDateFormat(pattern);
            return formatter.format(date);
        } catch (Exception e) {
            e.printStackTrace();
            return date;
        }
    }

    private static boolean isEmpty(Object str) {
        return str == null || "".equals(str);
    }
}
