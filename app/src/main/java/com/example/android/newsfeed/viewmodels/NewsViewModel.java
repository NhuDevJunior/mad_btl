package com.example.android.newsfeed.viewmodels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.android.newsfeed.model.News;
import com.example.android.newsfeed.repositories.NewsRepository;

import java.util.List;

public class NewsViewModel extends ViewModel {

    private NewsRepository newsRepository;
    private LiveData<News> news;
    private LiveData<List<News>> listNews;
    private LiveData<List<News>> listHighlightsNews;

    public NewsViewModel() {
        newsRepository = new NewsRepository();
        news = newsRepository.getNews();
        listNews = newsRepository.getListNews();
        listHighlightsNews = newsRepository.getListHighlightsNews();
    }

    /**
     * Get the news by id
     * @param id
     */
    public void getNewsById(int id) {
        newsRepository.getNewsById(id);
    }

    /**
     * Get list news in the page
     * @param page
     */
    public void getNewestListNews(int page) {
        newsRepository.getListNewsByPage(page);
    }

    /**
     * Get list high light news
     */
    public void getListHighLightNewsFromApi() {
        newsRepository.getListHighLightNewsFromApi();
    }

    public LiveData<News> getNews() {
        return news;
    }

    public LiveData<List<News>> getListNews() {
        return listNews;
    }

    public LiveData<List<News>> getListHighlightsNews() {
        return listHighlightsNews;
    }
}
