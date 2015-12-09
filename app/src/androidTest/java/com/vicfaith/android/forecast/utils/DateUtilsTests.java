package com.vicfaith.android.forecast.utils;

import junit.framework.TestCase;

/**
 * Created by dkang on 5/12/15.
 */
public class DateUtilsTests extends TestCase {
    public void testShouldDateFromTimestamp() {
        assertEquals("Sat", DateUtils.getDateFromTime(1449234000));
        assertEquals("Sun", DateUtils.getDateFromTime(1449320400));
        assertEquals("Mon", DateUtils.getDateFromTime(1449406800));
        assertEquals("Tue", DateUtils.getDateFromTime(1449493200));
        assertEquals("Wed", DateUtils.getDateFromTime(1449579600));
        assertEquals("Thu", DateUtils.getDateFromTime(1449666000));
        assertEquals("Fri", DateUtils.getDateFromTime(1449752400));
    }
}
