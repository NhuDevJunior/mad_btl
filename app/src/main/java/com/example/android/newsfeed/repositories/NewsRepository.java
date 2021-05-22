package com.example.android.newsfeed.repositories;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.android.newsfeed.api.MyApiServiceImpl;
import com.example.android.newsfeed.model.News;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NewsRepository {

    private static final String LOG_TAG = NewsRepository.class.getName();

    private MyApiServiceImpl service;
    private MutableLiveData<News> news;
    private MutableLiveData<List<News>> listNews;
    private MutableLiveData<List<News>> listHighlightsNews;

    public NewsRepository() {
        service = MyApiServiceImpl.getInstance();
        news = new MutableLiveData<>();
        listNews = new MutableLiveData<>();
        listHighlightsNews = new MutableLiveData<>();
    }

    /**
     * Get news by id
     * @param id
     */
    public void getNewsById(int id) {
        service.getService().getNewsById(id).enqueue(new Callback<com.example.android.newsfeed.model.News>() {
            @Override
            public void onResponse(Call<com.example.android.newsfeed.model.News> call, Response<com.example.android.newsfeed.model.News> response) {
                if (response.isSuccessful()) {
                    news.postValue(response.body());
                }
            }

            @Override
            public void onFailure(Call<com.example.android.newsfeed.model.News> call, Throwable t) {
                Log.e(LOG_TAG, t.getMessage());
                news.postValue(null);
            }
        });
    }

    /**
     * Get list news in the specified page
     * @param page
     */
    public void getListNewsByPage(int page) {
        service.getService().getListNews(page).enqueue(new Callback<List<News>>() {
            @Override
            public void onResponse(Call<List<News>> call, Response<List<News>> response) {
                if (response.isSuccessful()) {
                    listNews.postValue(response.body());
                }
            }

            @Override
            public void onFailure(Call<List<News>> call, Throwable t) {
                listNews.postValue(null);
                Log.i(LOG_TAG, call.request().url().toString());
                Log.e(LOG_TAG, t.getMessage());
            }
        });
    }

    /**
     * Get list hight light news
     */
    public void getListHighLightNewsFromApi() {
        service.getService().getListHighLightNews().enqueue(new Callback<List<News>>() {
            @Override
            public void onResponse(Call<List<News>> call, Response<List<News>> response) {
                if (response.isSuccessful()) {
                    listHighlightsNews.postValue(response.body());
                }
            }

            @Override
            public void onFailure(Call<List<News>> call, Throwable t) {
                listHighlightsNews.postValue(null);
                Log.e(LOG_TAG, t.getMessage());
            }
        });
    }

    public LiveData<News> getNews() {
        return news;
    }

    public MutableLiveData<List<News>> getListNews() {
        return listNews;
    }

    public LiveData<List<News>> getListHighlightsNews() {
        return listHighlightsNews;
    }
}
