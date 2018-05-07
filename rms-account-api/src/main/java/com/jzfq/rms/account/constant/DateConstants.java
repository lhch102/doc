/*
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS HEADER.
 *
 * Copyright (C) 2014 RayBow and/or its affiliates. All rights reserved.
 */
package com.jzfq.rms.account.constant;


import java.text.DateFormat;
import java.text.SimpleDateFormat;

/**
 * @author 大连桔子分期科技有限公司
 * @date 2017年9月05日 20:04:55
 */
public class DateConstants {

    public static final DateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd");

    public static final DateFormat DATETIME_FORMAT = new SimpleDateFormat("yyyy-MM-dd HH:mm");

    public static final DateFormat TIMESTAMP_FORMAT = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    public static final DateFormat TIME_RANGE_FORMAT = new SimpleDateFormat("HH:mm");

    public static final DateFormat TIME_MSEC_FORMAT = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");

    public static final DateFormat TIME_APM_FORMAT = new SimpleDateFormat("HH:mm a");
    //拉新手机号过滤排除日期
    public static final DateFormat FILTER_DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd 00:00:00");

    public static final DateFormat REMINDER_DATE_FORMAT0 = new SimpleDateFormat("yyyy-MM-dd 00:00:00");
    public static final DateFormat REMINDER_DATE_FORMAT1 = new SimpleDateFormat("yyyy-MM-dd 23:59:59");
    public static final DateFormat REMINDER_MTIME_FORMAT = new SimpleDateFormat("mm");
    public static final DateFormat REMINDER_HTIME_FORMAT = new SimpleDateFormat("HH");
    public static final DateFormat REMINDER_YMDTIME_FORMAT = new SimpleDateFormat("yyyyMMddHH");
    public static final DateFormat REMINDER_TIMESTAMP_FORMAT = new SimpleDateFormat("yyyyMMddHHmmss");
    private DateConstants() {
        //private ctor
    }
}
