package com.example.android.newsfeed.repositories;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.android.newsfeed.api.MyApiServiceImpl;
import com.example.android.newsfeed.api.dto.likes.AddLikeNewsRequest;
import com.example.android.newsfeed.api.dto.likes.GetLikeNewsRequest;
import com.example.android.newsfeed.model.News;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LikeNewsRepository {

    private static final String LOG_TAG = LikeNewsRepository.class.getName();

    private MyApiServiceImpl service;
    private MutableLiveData<List<News>> likeNewsList;

    public LikeNewsRepository() {
        service = MyApiServiceImpl.getInstance();
        likeNewsList = new MutableLiveData<>();
    }

    /**
     * Add the news to the favorite list
     * @param addLikeNewsRequest
     */
    public void addLikeNews(AddLikeNewsRequest addLikeNewsRequest) {
        service.getService().addLikeNews(addLikeNewsRequest).enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {

            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                Log.e(LOG_TAG, t.getMessage());
            }
        });
    }

    /**
     * Get favorite News of the user
     * @param getLikeNewsRequest
     */
    public void getLikeNews(GetLikeNewsRequest getLikeNewsRequest) {
        service.getService().getLikeNews(getLikeNewsRequest).enqueue(new Callback<List<News>>() {
            @Override
            public void onResponse(Call<List<News>> call, Response<List<News>> response) {
                Log.i(LOG_TAG, response.raw().toString());
                if (response.isSuccessful()) {
                    Log.i(LOG_TAG, response.body().toString());
                    likeNewsList.postValue(response.body());
                }
            }

            @Override
            public void onFailure(Call<List<News>> call, Throwable t) {
                Log.e(LOG_TAG, t.getMessage());
            }
        });
    }

    public LiveData<List<News>> getLikeNewsList() {
        return likeNewsList;
    }
}
