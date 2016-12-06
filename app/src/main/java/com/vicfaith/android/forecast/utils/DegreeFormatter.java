package com.vicfaith.android.forecast.utils;

/**
 * Created by dkang on 5/12/15.
 */
public class DegreeFormatter {

    // convert fahrenheit  to celsius
    public static String getDegree(double temp) {
        temp = ((temp - 32) * 5) / 9;
        return Math.round(temp) + "\u00b0C";
    }
}