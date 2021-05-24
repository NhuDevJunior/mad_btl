package com.example.android.newsfeed.api;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class WeatherApiServiceImpl {
    private WeatherApiService service;

    private static final String BASE_URL = "https://community-open-weather-map.p.rapidapi.com";
    private static WeatherApiServiceImpl instance;

    private WeatherApiServiceImpl() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        service = retrofit.create(WeatherApiService.class);
    }

    public static WeatherApiServiceImpl getInstance() {
        if (instance == null) {
            instance = new WeatherApiServiceImpl();
        }
        return instance;
    }

    public WeatherApiService getService() {
        return service;
    }
}
