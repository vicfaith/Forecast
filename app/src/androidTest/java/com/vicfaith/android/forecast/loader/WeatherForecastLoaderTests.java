package com.vicfaith.android.forecast.loader;

import android.test.AndroidTestCase;

import com.vicfaith.android.forecast.models.AsyncTaskResult;
import com.vicfaith.android.forecast.models.WeatherForecast;

/**
 * Created by dkang on 5/12/15.
 */
public class WeatherForecastLoaderTests extends AndroidTestCase {
    public void testShouldGetForecastFromLoader() {
        WeatherForecastLoader loader = new WeatherForecastLoader(getContext(), null);
        AsyncTaskResult<WeatherForecast> result = loader.loadInBackground();
        assertNotNull(result);
        assertNotNull(result.getError());
        assertNull(result.getResult());
    }
}