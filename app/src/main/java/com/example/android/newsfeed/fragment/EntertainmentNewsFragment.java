package com.example.android.newsfeed.fragment;

import android.view.View;

import com.example.android.newsfeed.model.News;
import com.example.android.newsfeed.utils.Constants;

import java.util.List;

public class EntertainmentNewsFragment extends BaseListNewsFragment{
    @Override
    protected List<News> getListNews() {
        return newsViewModel.getListNewsByCategoryEntertainment().getValue();
    }

    @Override
    protected void observeListNews() {
        newsViewModel.getListNewsByCategoryEntertainment().observe(getViewLifecycleOwner(), news -> {
            if (news != null) {
                newsAdapter.addAll(news);
                binding.loadingIndicator.setVisibility(View.GONE);
            }
        });
    }

    @Override
    protected void initiateRefresh() {
        newsAdapter.clearAll();
        newsViewModel.getNewsByCategoryEntertainment(Constants.CATEGORY_ENTERTAINMENT);
    }
}
