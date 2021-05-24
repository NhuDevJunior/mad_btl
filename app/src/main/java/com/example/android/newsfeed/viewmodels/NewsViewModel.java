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
    private LiveData<List<News>> listNewsByCategory;
    private LiveData<List<News>> listNewsByCategoryCuisine;
    private LiveData<List<News>> listNewsByCategoryNewsTime;
    private LiveData<List<News>> listNewsByCategoryFootballVN;
    private LiveData<List<News>> listNewsByCategoryFootballInter;
    private LiveData<List<News>> listNewsByCategoryEntertainment;
    private LiveData<List<News>> listNewsByCategoryLaw;
    private LiveData<List<News>> listNewsByCategoryWorld;
    private LiveData<List<News>> listNewsByCategoryTravel;
    private LiveData<List<News>> listNewsByKeyword;

    public NewsViewModel() {
        newsRepository = new NewsRepository();
        news = newsRepository.getNews();
        listNews = newsRepository.getListNews();
        listHighlightsNews = newsRepository.getListHighlightsNews();
        listNewsByCategory = newsRepository.getListNewsByCategory();
        listNewsByCategoryCuisine = newsRepository.getListNewsByCategoryCuisine();
        listNewsByCategoryNewsTime = newsRepository.getListNewsByCategoryNewsTime();
        listNewsByCategoryFootballVN = newsRepository.getListNewsByCategoryFootballVN();
        listNewsByCategoryFootballInter = newsRepository.getListNewsByCategoryFootballInter();
        listNewsByCategoryEntertainment = newsRepository.getListNewsByCategoryEntertainment();
        listNewsByCategoryLaw = newsRepository.getListNewsByCategoryLaw();
        listNewsByCategoryWorld = newsRepository.getListNewsByCategoryWorld();
        listNewsByCategoryTravel = newsRepository.getListNewsByCategoryTravel();
        listNewsByKeyword = newsRepository.getListNewsByKeyword();
    }

    /**
     * Get the news by id
     * @param id
     */
    public void getNewsById(int id) {
        newsRepository.getNewsById(id);
    }
    public void getNewsByCategory(String category) {
        newsRepository.getNewsByCategory(category);
    }
    public void getNewsByCategoryCuisine(String categoryCuisine) {
        newsRepository.getNewsByCategoryCuisine(categoryCuisine);
    }
    public void getNewsByCategoryNewsTime(String categoryNewsTime) {
        newsRepository.getNewsByCategoryNewsTime(categoryNewsTime);
    }
    public void getNewsByCategoryFootballVN(String categoryFootballVN) {
        newsRepository.getNewsByCategoryFootballVN(categoryFootballVN);
    }
    public void getNewsByCategoryFootballInter(String categoryFootballInter) {
        newsRepository.getNewsByCategoryFootballInter(categoryFootballInter);
    }
    public void getNewsByCategoryEntertainment(String categoryEntertainment) {
        newsRepository.getNewsByCategoryEntertainment(categoryEntertainment);
    }
    public void getNewsByCategoryLaw(String categoryLaw) {
        newsRepository.getNewsByCategoryLaw(categoryLaw);
    }
    public void getNewsByCategoryWorld(String categoryWorld) {
        newsRepository.getNewsByCategoryWorld(categoryWorld);
    }
    public void getNewsByCategoryTravel(String categoryTravel) {
        newsRepository.getNewsByCategoryTravel(categoryTravel);
    }
    public void getNewsByKeyword(String keyword) {
        newsRepository.getNewsByKeyword(keyword);
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
