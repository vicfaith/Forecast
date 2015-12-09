package com.vicfaith.android.forecast.utils;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Locale;
import java.util.TimeZone;

/**
 * Created by dkang on 5/12/15.
 */
public class DateUtils {
    public static String getDateFromTime(long time) {
        Calendar calendar = new GregorianCalendar();
        calendar.setTimeInMillis(time * 1000);
        calendar.setTimeZone(TimeZone.getDefault());
        return calendar.getDisplayName(Calendar.DAY_OF_WEEK, Calendar.SHORT, Locale.getDefault());
    }
}