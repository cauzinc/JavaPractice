package com.myMall.util;

import org.apache.commons.lang3.StringUtils;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import java.util.Date;

/**
 *  joda-time 代替 SimpleDateFormatter
 *  构建一个DateTime 对象，可以用toDate() 或者 toString(format) 方法来进行转换
 */
public class DateTimeUtil {
    public static final String DEFAULT_FORMAT = "yyyy-MM-dd HH:mm:ss";

    public static Date strToDate(String dateStr, String format) {
        DateTimeFormatter dateTimeFormatter = DateTimeFormat.forPattern(format);
        DateTime dateTime = dateTimeFormatter.parseDateTime(dateStr);
        return dateTime.toDate();
    }

    public static Date strToDate(String dateStr) {
        DateTimeFormatter dateTimeFormatter = DateTimeFormat.forPattern(DEFAULT_FORMAT);
        DateTime dateTime = dateTimeFormatter.parseDateTime(dateStr);
        return dateTime.toDate();
    }

    public static String dateToStr(Date date, String format) {
        if(date == null) {
            return StringUtils.EMPTY;
        }
        DateTime dateTime = new DateTime(date);
        return dateTime.toString(format);
    }

    public static String dateToStr(Date date) {
        if(date == null) {
            return StringUtils.EMPTY;
        }
        DateTime dateTime = new DateTime(date);
        return dateTime.toString(DEFAULT_FORMAT);
    }

}
