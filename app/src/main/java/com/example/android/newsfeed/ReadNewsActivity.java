package com.example.android.newsfeed;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.TypedValue;
import android.view.MenuItem;
import android.view.View;

import com.bumptech.glide.Glide;
import com.example.android.newsfeed.databinding.ActivityReadNewsBinding;
import com.example.android.newsfeed.model.News;
import com.example.android.newsfeed.utils.Constants;

import org.sufficientlysecure.htmltextview.HtmlHttpImageGetter;

public class ReadNewsActivity extends AppCompatActivity {

    private News news;
    private ActivityReadNewsBinding binding;

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
        binding.commentButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent commentIntent = new Intent(ReadNewsActivity.this, CommentActivity.class);
                commentIntent.putExtra(Constants.INTENT_EXTRA_NEWS_ID, news.getId());
                startActivity(commentIntent);
            }
        });
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