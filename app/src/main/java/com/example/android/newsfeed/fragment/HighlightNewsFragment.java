package com.example.android.newsfeed.fragment;

import android.util.Log;
import android.view.View;

import com.example.android.newsfeed.adapter.MyNewsAdapter;
import com.example.android.newsfeed.model.News;

import java.util.List;

public class HighlightNewsFragment extends BaseListNewsFragment {
    @Override
    protected List<News> getListNews() {
        return newsViewModel.getListHighlightsNews().getValue();
    }

    @Override
    protected void observeListNews() {
        newsViewModel.getListHighlightsNews().observe(getActivity(), news -> {
            if (news != null) {
                newsAdapter.addAll(news);
                binding.loadingIndicator.setVisibility(View.GONE);
            }
        });
    }

    @Override
    protected void initiateRefresh() {
        newsAdapter.clearAll();
        newsViewModel.getListHighLightNewsFromApi();
    }
}
