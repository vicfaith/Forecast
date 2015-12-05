package com.westpac.android.codingtest.models;

import android.test.InstrumentationTestCase;

import com.westpac.android.codingtest.utils.TestUtils;

import org.json.JSONException;

/**
 * Created by dkang on 5/12/15.
 */
public class WeatherForecastTests extends InstrumentationTestCase {
    public void testShouldParseJsonProperly() throws JSONException {
        WeatherForecast forecast = TestUtils.loadWeatherForecastFromAsset(getInstrumentation().getContext(), "sydney_forecast.json");
        assertNotNull(forecast);

        assertNotNull(forecast.getCurrently());
        assertEquals(49, forecast.getHourly().getData().size());
        assertEquals(8, forecast.getDaily().getData().size());
        // ...
    }
}