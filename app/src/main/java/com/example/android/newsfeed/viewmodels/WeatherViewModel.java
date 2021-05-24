package com.example.android.newsfeed.viewmodels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.android.newsfeed.model.Weather;
import com.example.android.newsfeed.repositories.WeatherRepository;

public class WeatherViewModel extends ViewModel {

    private WeatherRepository weatherRepository;
    private LiveData<Weather> weather;

    public WeatherViewModel() {
        weatherRepository = new WeatherRepository();
        weather = weatherRepository.getWeather();
    }

    /**
     * Get the weather of the current location
     * @param city
     * @param lat
     * @param lon
     */
    public void getCurrentLocationWeather(String city, double lat, double lon) {
        weatherRepository.getCurrentLocationWeather(city, lat, lon);
    }

    public LiveData<Weather> getWeather() {
        return weather;
    }
}
