package com.vicfaith.android.forecast.utils;

import junit.framework.TestCase;

/**
 * Created by dkang on 5/12/15.
 */
public class MeteoconsConverterTests extends TestCase {
    public void testShouldConvertMetoconsFromString() {
        assertEquals("B", MeteoconsConverter.from("clear-day"));
        assertEquals("1", MeteoconsConverter.from("clear-night"));
        assertEquals("H", MeteoconsConverter.from("partly-cloudy-day"));
        assertEquals("I", MeteoconsConverter.from("partly-cloudy-night"));
        assertEquals("N", MeteoconsConverter.from("cloudy"));
        assertEquals(")", MeteoconsConverter.from("drizzle"));
        assertEquals(")", MeteoconsConverter.from("sunny"));
    }
}
