package com.vicfaith.android.forecast.utils;

/**
 * Created by dkang on 5/12/15.
 */
public class DegreeFormatter {

    public static String getDegree(double temp) {
        return Math.round(temp) + "\u00b0";
    }
}