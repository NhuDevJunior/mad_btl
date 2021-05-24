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
    private MutableLiveData<List<News>> listNewsByCategory;
    private MutableLiveData<List<News>> listNewsByCategoryCuisine;
    private MutableLiveData<List<News>> listNewsByCategoryNewsTime;
    private MutableLiveData<List<News>> listNewsByCategoryFootballVN;
    private MutableLiveData<List<News>> listNewsByCategoryFootballInter;
    private MutableLiveData<List<News>> listNewsByCategoryEntertainment;
    private MutableLiveData<List<News>> listNewsByCategoryLaw;
    private MutableLiveData<List<News>> listNewsByCategoryWorld;
    private MutableLiveData<List<News>> listNewsByCategoryTravel;
    private MutableLiveData<List<News>> listNewsByKeyword;
    public NewsRepository() {
        service = MyApiServiceImpl.getInstance();
        news = new MutableLiveData<>();
        listNews = new MutableLiveData<>();
        listHighlightsNews = new MutableLiveData<>();
        listNewsByCategory = new MutableLiveData<>();
        listNewsByCategoryCuisine = new MutableLiveData<>();
        listNewsByCategoryNewsTime = new MutableLiveData<>();
        listNewsByCategoryFootballVN = new MutableLiveData<>();
        listNewsByCategoryFootballInter = new MutableLiveData<>();
        listNewsByCategoryEntertainment = new MutableLiveData<>();
        listNewsByCategoryLaw = new MutableLiveData<>();
        listNewsByCategoryWorld = new MutableLiveData<>();
        listNewsByCategoryTravel = new MutableLiveData<>();
        listNewsByKeyword = new MutableLiveData<>();
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
    /*
    * Get news by category
    *
    * */
    public void getNewsByCategory(String category)
    {
        service.getService().getListNewsByCategory(category).enqueue(new Callback<List<News>>() {
            @Override
            public void onResponse(Call<List<News>> call, Response<List<News>> response) {
                if (response.isSuccessful()) {
                    listNewsByCategory.postValue(response.body());
                }
            }

            @Override
            public void onFailure(Call<List<News>> call, Throwable t) {
                listNews.postValue(null);
                Log.e(LOG_TAG, t.getMessage());
            }
        });
    }
    public void getNewsByCategoryCuisine(String categoryCuisine)
    {
        service.getService().getListNewsByCategory(categoryCuisine).enqueue(new Callback<List<News>>() {
            @Override
            public void onResponse(Call<List<News>> call, Response<List<News>> response) {
                if (response.isSuccessful()) {
                    listNewsByCategoryCuisine.postValue(response.body());
                }
            }

            @Override
            public void onFailure(Call<List<News>> call, Throwable t) {
                listNews.postValue(null);
                Log.e(LOG_TAG, t.getMessage());
            }
        });
    }
    public void getNewsByCategoryNewsTime(String categoryNewsTime)
    {
        service.getService().getListNewsByCategory(categoryNewsTime).enqueue(new Callback<List<News>>() {
            @Override
            public void onResponse(Call<List<News>> call, Response<List<News>> response) {
                if (response.isSuccessful()) {
                    listNewsByCategoryNewsTime.postValue(response.body());
                }
            }

            @Override
            public void onFailure(Call<List<News>> call, Throwable t) {
                listNews.postValue(null);
                Log.e(LOG_TAG, t.getMessage());
            }
        });
    }
    public void getNewsByCategoryFootballVN(String categoryFootballVN)
    {
        service.getService().getListNewsByCategory(categoryFootballVN).enqueue(new Callback<List<News>>() {
            @Override
            public void onResponse(Call<List<News>> call, Response<List<News>> response) {
                if (response.isSuccessful()) {
                    listNewsByCategoryFootballVN.postValue(response.body());
                }
            }

            @Override
            public void onFailure(Call<List<News>> call, Throwable t) {
                listNews.postValue(null);
                Log.e(LOG_TAG, t.getMessage());
            }
        });
    }
    public void getNewsByCategoryFootballInter(String categoryFootballInter)
    {
        service.getService().getListNewsByCategory(categoryFootballInter).enqueue(new Callback<List<News>>() {
            @Override
            public void onResponse(Call<List<News>> call, Response<List<News>> response) {
                if (response.isSuccessful()) {
                    listNewsByCategoryFootballInter.postValue(response.body());
                }
            }

            @Override
            public void onFailure(Call<List<News>> call, Throwable t) {
                listNews.postValue(null);
                Log.e(LOG_TAG, t.getMessage());
            }
        });
    }
    public void getNewsByCategoryEntertainment(String categoryEntertainment)
    {
        service.getService().getListNewsByCategory(categoryEntertainment).enqueue(new Callback<List<News>>() {
            @Override
            public void onResponse(Call<List<News>> call, Response<List<News>> response) {
                if (response.isSuccessful()) {
                    listNewsByCategoryEntertainment.postValue(response.body());
                }
            }

            @Override
            public void onFailure(Call<List<News>> call, Throwable t) {
                listNews.postValue(null);
                Log.e(LOG_TAG, t.getMessage());
            }
        });
    }
    public void getNewsByCategoryLaw(String categoryLaw)
    {
        service.getService().getListNewsByCategory(categoryLaw).enqueue(new Callback<List<News>>() {
            @Override
            public void onResponse(Call<List<News>> call, Response<List<News>> response) {
                if (response.isSuccessful()) {
                    listNewsByCategoryLaw.postValue(response.body());
                }
            }

            @Override
            public void onFailure(Call<List<News>> call, Throwable t) {
                listNews.postValue(null);
                Log.e(LOG_TAG, t.getMessage());
            }
        });
    }
    public void getNewsByCategoryWorld(String categoryWorld)
    {
        service.getService().getListNewsByCategory(categoryWorld).enqueue(new Callback<List<News>>() {
            @Override
            public void onResponse(Call<List<News>> call, Response<List<News>> response) {
                if (response.isSuccessful()) {
                    listNewsByCategoryWorld.postValue(response.body());
                }
            }

            @Override
            public void onFailure(Call<List<News>> call, Throwable t) {
                listNews.postValue(null);
                Log.e(LOG_TAG, t.getMessage());
            }
        });
    }
    public void getNewsByCategoryTravel(String categoryTravel)
    {
        service.getService().getListNewsByCategory(categoryTravel).enqueue(new Callback<List<News>>() {
            @Override
            public void onResponse(Call<List<News>> call, Response<List<News>> response) {
                if (response.isSuccessful()) {
                    listNewsByCategoryTravel.postValue(response.body());
                }
            }

            @Override
            public void onFailure(Call<List<News>> call, Throwable t) {
                listNews.postValue(null);
                Log.e(LOG_TAG, t.getMessage());
            }
        });
    }
    public void getNewsByKeyword(String keyword)
    {
        service.getService().getListNewsByKeyword(keyword).enqueue(new Callback<List<News>>() {
            @Override
            public void onResponse(Call<List<News>> call, Response<List<News>> response) {
                if (response.isSuccessful()) {
                    listNewsByKeyword.postValue(response.body());
                }
            }

            @Override
            public void onFailure(Call<List<News>> call, Throwable t) {
                listNews.postValue(null);
                Log.e(LOG_TAG, t.getMessage());
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
    /*
    *
    * get list category = thoi su;
    * */

    public LiveData<News> getNews() {
        return news;
    }

    public LiveData<List<News>> getListNews() {
        return listNews;
    }

    public LiveData<List<News>> getListHighlightsNews() {
        return listHighlightsNews;
    }
    public LiveData<List<News>> getListNewsByCategory() {
        return listNewsByCategory;
    }
    public LiveData<List<News>> getListNewsByCategoryCuisine() {
        return listNewsByCategoryCuisine;
    }
    public LiveData<List<News>> getListNewsByCategoryNewsTime() {
        return listNewsByCategoryNewsTime;
    }
    public LiveData<List<News>> getListNewsByCategoryFootballVN() {
        return listNewsByCategoryFootballVN;
    }
    public LiveData<List<News>> getListNewsByCategoryFootballInter() {
        return listNewsByCategoryFootballInter;
    }
    public LiveData<List<News>> getListNewsByCategoryEntertainment() {
        return listNewsByCategoryEntertainment;
    }
    public LiveData<List<News>> getListNewsByCategoryLaw() {
        return listNewsByCategoryLaw;
    }
    public LiveData<List<News>> getListNewsByCategoryWorld() {
        return listNewsByCategoryWorld;
    }
    public LiveData<List<News>> getListNewsByCategoryTravel() {
        return listNewsByCategoryTravel;
    }
    public LiveData<List<News>> getListNewsByKeyword() {
        return listNewsByKeyword;
    }
}
