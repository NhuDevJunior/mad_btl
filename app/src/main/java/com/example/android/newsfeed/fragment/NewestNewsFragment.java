package com.example.android.newsfeed.fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import com.example.android.newsfeed.adapter.MyNewsAdapter;
import com.example.android.newsfeed.model.News;

import java.util.List;

public class NewestNewsFragment extends BaseListNewsFragment {

    private int page = 0;

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        binding.recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
            }
        });
    }

    @Override
    protected List<News> getListNews() {
        return newsViewModel.getListNews().getValue();
    }

    @Override
    protected void observeListNews() {
        newsViewModel.getListNews().observe(getActivity(), news -> {
            if (news != null) {
                newsAdapter.addAll(news);
                binding.swipeRefresh.setRefreshing(false);
                binding.loadingIndicator.setVisibility(View.GONE);
            }
        });
    }

    @Override
    protected void initiateRefresh() {
        newsAdapter.clearAll();
        page = 0;
        newsViewModel.getNewestListNews(page);
    }
}
