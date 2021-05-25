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
import android.widget.Toast;

import com.example.android.newsfeed.adapter.CommentAdapter;
import com.example.android.newsfeed.api.dto.comment.GetCommentsRequest;
import com.example.android.newsfeed.api.dto.comment.NewCommentRequest;
import com.example.android.newsfeed.databinding.ActivityCommentBinding;
import com.example.android.newsfeed.utils.Constants;
import com.example.android.newsfeed.viewmodels.CommentViewModel;

import java.util.ArrayList;

public class CommentActivity extends AppCompatActivity {

    private static final String LOG_TAG = CommentActivity.class.getName();

    private ActivityCommentBinding binding;
    private CommentViewModel commentViewModel;
    private CommentAdapter commentAdapter;
    private int newsId;
    private String token;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityCommentBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        // Get token from shared preferences
        SharedPreferences sharedPreferences = getSharedPreferences(getString(R.string.share_preference_file_key), MODE_PRIVATE);
        token = sharedPreferences.getString(Constants.SHARE_PREFERENCE_KEY_TOKEN, null);

        // Token is null if the user has not logged in. Then we won't show the user the comment section by default
        if (token != null) {
            binding.commentInputContainer.setVisibility(View.VISIBLE);

            // Set up submit comment button on click
            binding.commentSubmitButton.setOnClickListener(view -> {
                // Get the comment content
                String commentContent = binding.commentInputView.getText().toString();

                // Only submit the comment if it's not empty
                if (!commentContent.equals("")) {
                    commentViewModel.addNewComment(new NewCommentRequest(commentContent, newsId, token));
                    binding.commentInputView.setText("");
                }
            });
        }

        newsId = getIntent().getIntExtra(Constants.INTENT_EXTRA_NEWS_ID, -1);

        // Set up view model
        commentViewModel = new ViewModelProvider(this).get(CommentViewModel.class);
        observeCommentList();

        commentAdapter = new CommentAdapter(this, new ArrayList<>(), commentViewModel);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        binding.recyclerViewComment.setLayoutManager(layoutManager);
        binding.recyclerViewComment.setAdapter(commentAdapter);

        // Set up swipe refresh
        binding.swipeRefreshComment.setColorSchemeColors(getResources().getColor(R.color.swipe_color_1),
                getResources().getColor(R.color.swipe_color_2),
                getResources().getColor(R.color.swipe_color_3),
                getResources().getColor(R.color.swipe_color_4));

        // Set up OnRefreshListener that is invoked when the user performs a swipe-to-refresh gesture.
        binding.swipeRefreshComment.setOnRefreshListener(() -> {
            Log.i(LOG_TAG, "onRefresh called from SwipeRefreshLayout");
            // restart the loader
            initiateRefresh(newsId);
            Toast.makeText(this, getString(R.string.updated_just_now),
                    Toast.LENGTH_SHORT).show();
        });

        // Get the comments on first load
        initiateRefresh(newsId);
    }

    /**
     * Get the comments of the news
     *
     * @param newsId
     */
    private void initiateRefresh(int newsId) {
        commentAdapter.clearAll();
        commentViewModel.getNewsComment(new GetCommentsRequest(newsId));
    }

    /**
     * Set up observer for the commentViewModel of the current activity
     */
    private void observeCommentList() {
        commentViewModel.getComments().observe(this, comments -> {
            if (comments != null) {
                commentAdapter.addAll(comments);
                binding.swipeRefreshComment.setRefreshing(false);
                binding.loadingIndicatorComment.setVisibility(View.GONE);
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