/*
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS HEADER.
 *
 * Copyright (C) 2014 RayBow and/or its affiliates. All rights reserved.
 */
package com.jzfq.rms.product.constant;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author 大连桔子分期科技有限公司
 * @date 2017年9月05日 20:04:55
 */
public class DateConstants {

    public static final String DATE_FORMAT = "yyyy-MM-dd";

    public static final String DATETIME_FORMAT = "yyyy-MM-dd HH:mm";

    public static final String TIMESTAMP_FORMAT = "yyyy-MM-dd HH:mm:ss";

    public static final String TIME_RANGE_FORMAT = "HH:mm";

    public static final String TIME_MSEC_FORMAT = "yyyy-MM-dd HH:mm:ss.SSS";

    public static final String TIME_APM_FORMAT = "HH:mm a";
    //拉新手机号过滤排除日期
    public static final String FILTER_DATE_FORMAT = "yyyy-MM-dd 00:00:00";

    public static final String REMINDER_DATE_FORMAT0 = "yyyy-MM-dd 00:00:00";
    public static final String REMINDER_DATE_FORMAT1 = "yyyy-MM-dd 23:59:59";
    public static final String REMINDER_MTIME_FORMAT = "mm";
    public static final String REMINDER_HTIME_FORMAT = "HH";
    public static final String REMINDER_YMDTIME_FORMAT = "yyyyMMddHH";
    public static final String REMINDER_TIMESTAMP_FORMAT = "yyyyMMddHHmmss";

    private DateConstants() {
        //private ctor
    }

    public static String dateFormat(Date date) {
        return new SimpleDateFormat(DATE_FORMAT).format(date);
    }

    public static String datetimeFormat(Date date) {
        return new SimpleDateFormat(DATETIME_FORMAT).format(date);
    }

    public static String timestampFormat(Date date) {
        return new SimpleDateFormat(TIMESTAMP_FORMAT).format(date);
    }

    public static String timeRangeFormat(Date date) {
        return new SimpleDateFormat(TIME_RANGE_FORMAT).format(date);
    }

    public static String reminderYmdtimeFormat(Date date) {
        return new SimpleDateFormat(REMINDER_YMDTIME_FORMAT).format(date);
    }

    public static Date dateFormat(String input) throws ParseException {
        return new SimpleDateFormat(DATE_FORMAT).parse(input);
    }

}
