package com.example.android.newsfeed;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;

import com.example.android.newsfeed.adapter.MyNewsAdapter;
import com.example.android.newsfeed.api.dto.likes.GetLikeNewsRequest;
import com.example.android.newsfeed.databinding.ActivityLikedNewsListBinding;
import com.example.android.newsfeed.model.News;
import com.example.android.newsfeed.utils.Constants;
import com.example.android.newsfeed.viewmodels.LikeNewsViewModel;

import java.util.ArrayList;
import java.util.List;

public class LikedNewsListActivity extends AppCompatActivity {

    private ActivityLikedNewsListBinding binding;
    private LikeNewsViewModel likeNewsViewModel;
    private String token;
    private MyNewsAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityLikedNewsListBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        likeNewsViewModel = new ViewModelProvider(this).get(LikeNewsViewModel.class);
        SharedPreferences sharedPreferences = getSharedPreferences(getString(R.string.share_preference_file_key), MODE_PRIVATE);
        token = sharedPreferences.getString(Constants.SHARE_PREFERENCE_KEY_TOKEN, null);

        // Set up list view
        adapter = new MyNewsAdapter(this, new ArrayList<>());
        observeLikeNews();
        likeNewsViewModel.getLikeNews(new GetLikeNewsRequest(token));
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        binding.recyclerViewLike.setLayoutManager(layoutManager);
        binding.recyclerViewLike.setAdapter(adapter);
    }

    /**
     * Observe like news list
     */
    private void observeLikeNews() {
        likeNewsViewModel.getLikeNewsList().observe(this, likedNewsList -> {
            if (likedNewsList != null) {
                adapter.addAll(likedNewsList);
                binding.loadingIndicatorLike.setVisibility(View.GONE);
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}