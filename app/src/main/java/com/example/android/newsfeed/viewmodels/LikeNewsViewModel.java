package com.example.android.newsfeed.viewmodels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.android.newsfeed.api.dto.likes.AddLikeNewsRequest;
import com.example.android.newsfeed.api.dto.likes.GetLikeNewsRequest;
import com.example.android.newsfeed.model.News;
import com.example.android.newsfeed.repositories.LikeNewsRepository;

import java.util.List;

public class LikeNewsViewModel extends ViewModel {

    private LikeNewsRepository likeNewsRepository;
    private LiveData<List<News>> likeNewsList;

    public LikeNewsViewModel() {
        likeNewsRepository = new LikeNewsRepository();
        likeNewsList = likeNewsRepository.getLikeNewsList();
    }

    /**
     * Add news to favorite list
     * @param addLikeNewsRequest
     */
    public void addLikeNews(AddLikeNewsRequest addLikeNewsRequest) {
        likeNewsRepository.addLikeNews(addLikeNewsRequest);
    }

    /**
     * Get user's favorite lists
     * @param getLikeNewsRequest
     */
    public void getLikeNews(GetLikeNewsRequest getLikeNewsRequest) {
        likeNewsRepository.getLikeNews(getLikeNewsRequest);
    }

    public LiveData<List<News>> getLikeNewsList() {
        return likeNewsList;
    }
}
