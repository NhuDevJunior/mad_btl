package com.example.android.newsfeed.api;

import com.example.android.newsfeed.model.Weather;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Query;

public interface WeatherApiService {

    @GET("weather")
    @Headers({"x-rapidapi-key: 0359a09088mshb850c4af16ad47ep1b00c7jsncb3704fd9919", "x-rapidapi-host: community-open-weather-map.p.rapidapi.com", "useQueryString: true"})
    Call<Weather> getCurrentLocationWeather(@Query("q") String city, @Query("lat") double lat, @Query("lon") double lon, @Query("lang") String lang, @Query("units") String unit);
}
