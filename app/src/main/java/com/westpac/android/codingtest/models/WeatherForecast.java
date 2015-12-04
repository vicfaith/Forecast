package com.westpac.android.codingtest.models;

import java.util.List;

/**
 * Created by dkang on 4/12/15.
 */
public class WeatherForecast {
    double latitude;
    double longitude;
    String timezone;
    int offset;
    CurrentlyForecast currently;

    public static class CurrentlyForecast {
        long time;
        String summary;
        String icon;
        int precipIntensity;
        int precipProbability;
        double temperature;
        double apparentTemperature;
        double dewPoint;
        double humidity;
        double windSpeed;
        double windBearing;
        double cloudCover;
        double pressure;
        double ozone;

        public long getTime() {
            return time;
        }

        public void setTime(long time) {
            this.time = time;
        }

        public String getSummary() {
            return summary;
        }

        public void setSummary(String summary) {
            this.summary = summary;
        }

        public String getIcon() {
            return icon;
        }

        public void setIcon(String icon) {
            this.icon = icon;
        }

        public int getPrecipIntensity() {
            return precipIntensity;
        }

        public void setPrecipIntensity(int precipIntensity) {
            this.precipIntensity = precipIntensity;
        }

        public int getPrecipProbability() {
            return precipProbability;
        }

        public void setPrecipProbability(int precipProbability) {
            this.precipProbability = precipProbability;
        }

        public double getTemperature() {
            return temperature;
        }

        public void setTemperature(double temperature) {
            this.temperature = temperature;
        }

        public double getApparentTemperature() {
            return apparentTemperature;
        }

        public void setApparentTemperature(double apparentTemperature) {
            this.apparentTemperature = apparentTemperature;
        }

        public double getDewPoint() {
            return dewPoint;
        }

        public void setDewPoint(double dewPoint) {
            this.dewPoint = dewPoint;
        }

        public double getHumidity() {
            return humidity;
        }

        public void setHumidity(double humidity) {
            this.humidity = humidity;
        }

        public double getWindSpeed() {
            return windSpeed;
        }

        public void setWindSpeed(double windSpeed) {
            this.windSpeed = windSpeed;
        }

        public double getWindBearing() {
            return windBearing;
        }

        public void setWindBearing(double windBearing) {
            this.windBearing = windBearing;
        }

        public double getCloudCover() {
            return cloudCover;
        }

        public void setCloudCover(double cloudCover) {
            this.cloudCover = cloudCover;
        }

        public double getPressure() {
            return pressure;
        }

        public void setPressure(double pressure) {
            this.pressure = pressure;
        }

        public double getOzone() {
            return ozone;
        }

        public void setOzone(double ozone) {
            this.ozone = ozone;
        }
    }

    HourlyForecast hourly;

    public static class HourlyForecast {
        String summary;
        String icon;
        List<Forecast> data;

        public String getSummary() {
            return summary;
        }

        public void setSummary(String summary) {
            this.summary = summary;
        }

        public String getIcon() {
            return icon;
        }

        public void setIcon(String icon) {
            this.icon = icon;
        }

        public List<Forecast> getData() {
            return data;
        }

        public void setData(List<Forecast> data) {
            this.data = data;
        }
    }

    public static class Forecast {
        long time;
        String summary;
        String icon;
        int precipIntensity;
        int precipProbability;
        double temperature;
        double apparentTemperature;
        double dewPoint;
        double humidity;
        double windSpeed;
        double windBearing;
        double cloudCover;
        double pressure;
        double ozone;

        public long getTime() {
            return time;
        }

        public void setTime(long time) {
            this.time = time;
        }

        public String getSummary() {
            return summary;
        }

        public void setSummary(String summary) {
            this.summary = summary;
        }

        public String getIcon() {
            return icon;
        }

        public void setIcon(String icon) {
            this.icon = icon;
        }

        public int getPrecipIntensity() {
            return precipIntensity;
        }

        public void setPrecipIntensity(int precipIntensity) {
            this.precipIntensity = precipIntensity;
        }

        public int getPrecipProbability() {
            return precipProbability;
        }

        public void setPrecipProbability(int precipProbability) {
            this.precipProbability = precipProbability;
        }

        public double getTemperature() {
            return temperature;
        }

        public void setTemperature(double temperature) {
            this.temperature = temperature;
        }

        public double getDewPoint() {
            return dewPoint;
        }

        public void setDewPoint(double dewPoint) {
            this.dewPoint = dewPoint;
        }

        public double getApparentTemperature() {
            return apparentTemperature;
        }

        public void setApparentTemperature(double apparentTemperature) {
            this.apparentTemperature = apparentTemperature;
        }

        public double getHumidity() {
            return humidity;
        }

        public void setHumidity(double humidity) {
            this.humidity = humidity;
        }

        public double getWindBearing() {
            return windBearing;
        }

        public void setWindBearing(double windBearing) {
            this.windBearing = windBearing;
        }

        public double getWindSpeed() {
            return windSpeed;
        }

        public void setWindSpeed(double windSpeed) {
            this.windSpeed = windSpeed;
        }

        public double getPressure() {
            return pressure;
        }

        public void setPressure(double pressure) {
            this.pressure = pressure;
        }

        public double getCloudCover() {
            return cloudCover;
        }

        public void setCloudCover(double cloudCover) {
            this.cloudCover = cloudCover;
        }

        public double getOzone() {
            return ozone;
        }

        public void setOzone(double ozone) {
            this.ozone = ozone;
        }
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public String getTimezone() {
        return timezone;
    }

    public void setTimezone(String timezone) {
        this.timezone = timezone;
    }

    public int getOffset() {
        return offset;
    }

    public void setOffset(int offset) {
        this.offset = offset;
    }

    public CurrentlyForecast getCurrently() {
        return currently;
    }

    public void setCurrently(CurrentlyForecast currently) {
        this.currently = currently;
    }

    public HourlyForecast getHourly() {
        return hourly;
    }

    public void setHourly(HourlyForecast hourly) {
        this.hourly = hourly;
    }
}
