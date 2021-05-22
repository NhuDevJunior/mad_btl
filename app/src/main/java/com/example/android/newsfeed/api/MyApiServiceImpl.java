package com.example.android.newsfeed.api;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MyApiServiceImpl {

    private MyApiService service;

    private static final String BASE_URL = "https://madblt.herokuapp.com";
    private static MyApiServiceImpl instance;

    private MyApiServiceImpl() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        service = retrofit.create(MyApiService.class);
    }

    public static MyApiServiceImpl getInstance() {
        if (instance == null) {
            instance = new MyApiServiceImpl();
        }
        return instance;
    }

    public MyApiService getService() {
        return service;
    }

}
