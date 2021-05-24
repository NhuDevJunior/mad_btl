package com.example.android.newsfeed.repositories;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.android.newsfeed.api.WeatherApiServiceImpl;
import com.example.android.newsfeed.model.Weather;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class WeatherRepository {
    private static final String LOG_TAG = WeatherRepository.class.getName();
    private static final String LANG = "vi";
    private static final String UNIT = "metric"; // Get the temperature in Celsius

    private WeatherApiServiceImpl service;
    private MutableLiveData<Weather> weather;

    public WeatherRepository() {
        service = WeatherApiServiceImpl.getInstance();
        weather = new MutableLiveData<>();
    }

    /**
     * Get the weather of the current location
     * @param city
     * @param lat
     * @param lon
     */
    public void getCurrentLocationWeather(String city, double lat, double lon) {
        service.getService().getCurrentLocationWeather(city, lat, lon, LANG, UNIT).enqueue(new Callback<Weather>() {
            @Override
            public void onResponse(Call<Weather> call, Response<Weather> response) {
                if (response.isSuccessful()) {
                    weather.postValue(response.body());
                }
            }

            @Override
            public void onFailure(Call<Weather> call, Throwable t) {
                Log.e(LOG_TAG, t.getMessage());
                weather.postValue(null);
            }
        });
    }

    public LiveData<Weather> getWeather() {
        return weather;
    }
}
