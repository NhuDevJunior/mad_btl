package com.example.android.newsfeed.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Weather {

    private static final String IMAGE_URL = "https://openweathermap.org/img/w/";
    private static final String IMAGE_SUFFIX = ".png";

    @SerializedName("weather")
    private List<WeatherDescription> weatherDescription;

    @SerializedName("main")
    private WeatherInfo weatherInfo;

    public List<WeatherDescription> getWeatherDescription() {
        return weatherDescription;
    }

    public WeatherInfo getWeatherInfo() {
        return weatherInfo;
    }

    public class WeatherDescription {
        @SerializedName("description")
        private String description;

        @SerializedName("icon")
        private String icon;

        public String getDescription() {
            return description;
        }

        /**
         * Get OpenWeather icon url
         * @return iconUrl
         */
        public String getIconUrl() {
            return IMAGE_URL + icon + IMAGE_SUFFIX;
        }
    }

    public class WeatherInfo {
        @SerializedName("temp_min")
        private double tempMin;

        @SerializedName("temp_max")
        private double tempMax;

        public String getTempMinString() {
            return Math.round(tempMin) + " C";
        }

        public String getTempMaxString() {
            return Math.round(tempMax) + " C";
        }
    }
}
