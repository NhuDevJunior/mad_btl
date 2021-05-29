package com.example.android.newsfeed;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.ColorStateList;
import android.os.Build;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.util.TypedValue;
import android.view.MenuItem;

import com.example.android.newsfeed.api.dto.likes.AddLikeNewsRequest;
import com.example.android.newsfeed.api.dto.likes.GetLikeNewsRequest;
import com.example.android.newsfeed.databinding.ActivityReadNewsBinding;
import com.example.android.newsfeed.model.News;
import com.example.android.newsfeed.utils.Constants;
import com.example.android.newsfeed.viewmodels.LikeNewsViewModel;

import org.sufficientlysecure.htmltextview.HtmlHttpImageGetter;

import java.util.List;

public class ReadNewsActivity extends AppCompatActivity {

    private News news;
    private ActivityReadNewsBinding binding;
    private LikeNewsViewModel likeNewsViewModel;
    private String token;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityReadNewsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        // Get the news from the intent
        news = (News) getIntent().getSerializableExtra(Constants.INTENT_EXTRA_NEWS);

        setTextSize();

        // Set title of the news
        binding.newsTitle.setText(news.getTitle());

        // Set content of the news to the TextView
        binding.newsContent.setHtml(news.getContent(), new HtmlHttpImageGetter(binding.newsContent, null, true));

        // Set up comment button
        binding.commentButton.setOnClickListener(view -> {
            Intent commentIntent = new Intent(ReadNewsActivity.this, CommentActivity.class);
            commentIntent.putExtra(Constants.INTENT_EXTRA_NEWS_ID, news.getId());
            startActivity(commentIntent);
        });

        likeNewsViewModel = new ViewModelProvider(this).get(LikeNewsViewModel.class);
        SharedPreferences sharedPreferences = getSharedPreferences(getString(R.string.share_preference_file_key), MODE_PRIVATE);
        token = sharedPreferences.getString(Constants.SHARE_PREFERENCE_KEY_TOKEN, null);
        if (token != null) {
            Log.i(ReadNewsActivity.class.getName(), "Loaded");
            likeNewsViewModel.getLikeNewsList().observe(this, likedNews -> {
                binding.likeButton.show();
                // If the user has liked the news then disable it
                if (likedNews.contains(news)) {
                    binding.likeButton.setImageResource(R.drawable.ic_favorite_24);
                } else {
                    // Set up like button
                    binding.likeButton.setOnClickListener(view -> {
                        likeNewsViewModel.addLikeNews(new AddLikeNewsRequest(news.getId(), token));
                        binding.likeButton.setImageResource(R.drawable.ic_favorite_24);
                    });
                }
            });
            likeNewsViewModel.getLikeNews(new GetLikeNewsRequest(token));
        }
    }

    private void setTextSize() {
        // Get the text size string from SharedPreferences and check for the value associated with the key
        String textSize = PreferenceManager.getDefaultSharedPreferences(this).getString(
                getString(R.string.settings_text_size_key),
                getString(R.string.settings_text_size_default));

        // Change text size of TextView by using the user's stored preferences
        if (textSize.equals(getString(R.string.settings_text_size_medium_value))) {
            binding.newsTitle.setTextSize(TypedValue.COMPLEX_UNIT_PX,
                    getResources().getDimension(R.dimen.sp22));
            binding.newsContent.setTextSize(TypedValue.COMPLEX_UNIT_PX,
                    getResources().getDimension(R.dimen.sp16));
        } else if (textSize.equals(getString(R.string.settings_text_size_small_value))) {
            binding.newsTitle.setTextSize(TypedValue.COMPLEX_UNIT_PX,
                    getResources().getDimension(R.dimen.sp20));
            binding.newsContent.setTextSize(TypedValue.COMPLEX_UNIT_PX,
                    getResources().getDimension(R.dimen.sp14));
        } else if (textSize.equals(getString(R.string.settings_text_size_large_value))) {
            binding.newsTitle.setTextSize(TypedValue.COMPLEX_UNIT_PX,
                    getResources().getDimension(R.dimen.sp24));
            binding.newsContent.setTextSize(TypedValue.COMPLEX_UNIT_PX,
                    getResources().getDimension(R.dimen.sp18));
        }
    }

    // Go back to the MainActivity when up button in action bar is clicked on.
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}