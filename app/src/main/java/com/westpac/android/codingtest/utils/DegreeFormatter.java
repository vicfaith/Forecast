package com.westpac.android.codingtest.utils;

import java.util.regex.Matcher;

/**
 * Created by dkang on 5/12/15.
 */
public class DegreeFormatter {

    public static String getDegree(double temp) {
        return Math.round(temp) + "\u00b0";
    }
}