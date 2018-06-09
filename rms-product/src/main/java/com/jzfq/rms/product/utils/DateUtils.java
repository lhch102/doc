package com.jzfq.rms.product.utils;


import com.jzfq.rms.product.constant.DateConstants;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;


/**
 * @author 大连桔子分期科技有限公司
 * @date 2017年9月05日 20:04:55
 */
public abstract class DateUtils {

    public static Date now() {
        return new Date();
    }

    public static Long nowTime() {
        return now().getTime();
    }

    public static Date daysBefore(Date time, int days) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(time.getTime());

        calendar.add(Calendar.DATE, -1 * days);

        return calendar.getTime();
    }

    public static String date2str(Date date) {
        if (date == null)
            return null;

        return DateConstants.dateFormat(date);
    }

    public static String date2str(Date date, DateFormat dateFormat) {
        if (date == null)
            return null;

        return dateFormat.format(date);
    }

    public static String date2str(Date date, String format) {
        if (date == null)
            return null;

        return new SimpleDateFormat(format).format(date);
    }

    public static Date str2Date(String input, DateFormat format) {
        try {
            return input == null ? null : format.parse(input);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }

    public static Date str2Date(String input, String format) {
        try {
            return input == null ? null : new SimpleDateFormat(format).parse(input);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }

    public static Date str2Date(String input) {
        try {
            return input == null ? null : DateConstants.dateFormat(input);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }

    public static Date daysAfter(int days) {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DATE, days);

        return calendar.getTime();
    }

    public static Date daysAfter(Date time, int days) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(time.getTime());

        calendar.add(Calendar.DATE, days);

        return calendar.getTime();
    }

    public static Date today() {
        Calendar calendar = new GregorianCalendar();
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        return calendar.getTime();
    }

    public static Date hourAfter(Date time, int count) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(time.getTime());

        calendar.add(Calendar.HOUR, count);

        return calendar.getTime();
    }
    public static Date getMinute(Date time, int count) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(time.getTime());

        calendar.add(Calendar.MINUTE, count);

        return calendar.getTime();
    }

    /*
     * 将时间戳转换为时间
     */
    public static String stampToDate(String s){
        String res;
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Long lt = Long.parseLong(s);
        Date date = new Date(lt);
        res = simpleDateFormat.format(date);
        return res;
    }
    /*
     * 将时间转换为时间戳
     */
    public static String dateToStamp(String s) throws ParseException{
        String res;
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = simpleDateFormat.parse(s);
        long ts = date.getTime();
        res = String.valueOf(ts);
        return res;
    }
    public static void main(String[] args) {
        System.out.println(date2str(DateUtils.hourAfter(now(),1),DateConstants.REMINDER_YMDTIME_FORMAT));
    }
}
