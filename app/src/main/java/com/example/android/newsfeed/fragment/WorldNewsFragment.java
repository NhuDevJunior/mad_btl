package com.example.android.newsfeed.fragment;

import android.view.View;

import com.example.android.newsfeed.model.News;
import com.example.android.newsfeed.utils.Constants;

import java.util.List;

public class WorldNewsFragment extends BaseListNewsFragment{
    @Override
    protected List<News> getListNews() {
        return newsViewModel.getListNewsByCategoryWorld().getValue();
    }

    @Override
    protected void observeListNews() {
        newsViewModel.getListNewsByCategoryWorld().observe(getViewLifecycleOwner(), news -> {
            if (news != null) {
                newsAdapter.addAll(news);
                binding.loadingIndicator.setVisibility(View.GONE);
            }
        });
    }

    @Override
    protected void initiateRefresh() {
        newsAdapter.clearAll();
        newsViewModel.getNewsByCategoryWorld(Constants.CATEGORY_WORLD);
    }
}
