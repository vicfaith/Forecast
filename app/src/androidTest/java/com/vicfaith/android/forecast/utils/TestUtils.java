package com.vicfaith.android.forecast.utils;

import android.content.Context;

import com.vicfaith.android.forecast.loader.WeatherForecastLoader;
import com.vicfaith.android.forecast.models.WeatherForecast;

import org.json.JSONException;

import java.io.IOException;
import java.io.InputStream;
import java.util.Scanner;

/**
 * Created by dkang on 5/12/15.
 */
public class TestUtils {
    public static WeatherForecast loadWeatherForecastFromAsset(Context context, String fileName) throws
            JSONException {
        String json = loadFromAsset(context, fileName);
        return new WeatherForecastLoader(context, null).parseJson(json);
    }

    public static String loadFromAsset(Context context, String fileName) {
        try {
            InputStream raw = context.getAssets().open(fileName);
            return new Scanner(raw).useDelimiter("\\A").next();
        } catch (IOException e) {
            return null;
        }
    }
}
