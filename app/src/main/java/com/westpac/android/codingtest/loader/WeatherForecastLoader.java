package com.westpac.android.codingtest.loader;

import android.content.Context;
import android.support.v4.content.AsyncTaskLoader;

import com.westpac.android.codingtest.models.AsyncTaskResult;
import com.westpac.android.codingtest.models.WeatherForecast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Created by dkang on 4/12/15.
 */
public class WeatherForecastLoader extends AsyncTaskLoader<AsyncTaskResult<WeatherForecast>> {
    private String mUrl;

    public WeatherForecastLoader(Context context, String url) {
        super(context);
        mUrl = url;
        onForceLoad();
    }

    @Override
    public AsyncTaskResult<WeatherForecast> loadInBackground() {
        AsyncTaskResult<WeatherForecast> result = new AsyncTaskResult();

        try {
            URL url = new URL(mUrl);
            URLConnection connection = url.openConnection();
            String contents = getContent(connection.getInputStream());
            result.setResult(parseJson(contents));
        } catch (IOException e) {
            result.setError(e);
            stopLoading();
        } catch (JSONException e) {
            result.setError(e);
        }
        return result;
    }

    private WeatherForecast parseJson(String json) throws JSONException {
        WeatherForecast weatherForecast = new WeatherForecast();

        JSONObject jsonObject = new JSONObject(json);
        JSONObject currentlyObject = jsonObject.getJSONObject("currently");
        WeatherForecast.CurrentlyForecast currentlyForecast = new WeatherForecast.CurrentlyForecast();
        currentlyForecast.setTime(currentlyObject.getLong("time"));
        currentlyForecast.setSummary(currentlyObject.getString("summary"));
        currentlyForecast.setIcon(currentlyObject.getString("icon"));
        currentlyForecast.setPrecipIntensity(currentlyObject.getInt("precipIntensity"));
        currentlyForecast.setPrecipProbability(currentlyObject.getInt("precipProbability"));
        currentlyForecast.setTemperature(currentlyObject.getDouble("temperature"));
        currentlyForecast.setApparentTemperature(currentlyObject.getLong("apparentTemperature"));
        currentlyForecast.setDewPoint(currentlyObject.getLong("dewPoint"));
        currentlyForecast.setHumidity(currentlyObject.getLong("humidity"));
        currentlyForecast.setWindSpeed(currentlyObject.getLong("windSpeed"));
        currentlyForecast.setWindBearing(currentlyObject.getLong("windBearing"));
        currentlyForecast.setCloudCover(currentlyObject.getLong("cloudCover"));
        currentlyForecast.setPressure(currentlyObject.getLong("pressure"));
        currentlyForecast.setOzone(currentlyObject.getLong("ozone"));
        weatherForecast.setCurrently(currentlyForecast);

        WeatherForecast.HourlyForecast hourlyForecast = new WeatherForecast.HourlyForecast();
        JSONObject hourlyObject = jsonObject.getJSONObject("hourly");
        JSONArray hourlyArray = hourlyObject.getJSONArray("data");

        ArrayList<WeatherForecast.Forecast> forecastList = new ArrayList<>();
        for (int i = 0; i < hourlyArray.length(); i++) {
            JSONObject item = (JSONObject) hourlyArray.get(i);
            WeatherForecast.Forecast forecast = new WeatherForecast.Forecast();
            forecast.setTime(item.getInt("time"));
            forecast.setSummary(item.getString("summary"));
            forecast.setIcon(item.getString("icon"));
            forecast.setPrecipProbability(item.getInt("precipIntensity"));
            forecast.setPrecipIntensity(item.getInt("precipProbability"));
            forecast.setTemperature(item.getDouble("temperature"));
            forecast.setApparentTemperature(item.getDouble("apparentTemperature"));
            forecast.setDewPoint(item.getDouble("dewPoint"));
            forecast.setHumidity(item.getDouble("humidity"));
            forecast.setWindSpeed(item.getDouble("windSpeed"));
            forecast.setWindBearing(item.getDouble("windBearing"));
            forecast.setCloudCover(item.getDouble("cloudCover"));
            forecast.setPressure(item.getDouble("pressure"));
            forecast.setOzone(item.getDouble("ozone"));
            forecastList.add(forecast);
        }
        hourlyForecast.setData(forecastList);
        weatherForecast.setHourly(hourlyForecast);

        return weatherForecast;
    }

    private String getContent(InputStream raw) {
        return new Scanner(raw).useDelimiter("\\A").next();
    }
}
