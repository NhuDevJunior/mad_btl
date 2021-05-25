package com.example.android.newsfeed.repositories;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.android.newsfeed.api.MyApiServiceImpl;
import com.example.android.newsfeed.api.dto.comment.GetCommentsRequest;
import com.example.android.newsfeed.api.dto.comment.NewCommentRequest;
import com.example.android.newsfeed.api.dto.comment.NewReplyRequest;
import com.example.android.newsfeed.api.dto.comment.NewReplyResponse;
import com.example.android.newsfeed.model.Comment;
import com.example.android.newsfeed.model.Reply;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CommentRepository {
    private static final String LOG_TAG = CommentRepository.class.getName();

    private MyApiServiceImpl service;
    private MutableLiveData<List<Comment>> comments;

    public CommentRepository() {
        service = MyApiServiceImpl.getInstance();
        comments = new MutableLiveData<>();
    }

    /**
     * Get all comments of the news from the API
     *
     * @param getCommentsRequest
     */
    public void getNewsComments(GetCommentsRequest getCommentsRequest) {
        service.getService().getNewsComments(getCommentsRequest).enqueue(new Callback<List<Comment>>() {
            @Override
            public void onResponse(Call<List<Comment>> call, Response<List<Comment>> response) {
                if (response.isSuccessful()) {
                    comments.postValue(response.body());
                }
            }

            @Override
            public void onFailure(Call<List<Comment>> call, Throwable t) {
                Log.e(LOG_TAG, t.getMessage());
            }
        });
    }

    /**
     * Add new comment to the news
     *
     * @param newCommentRequest
     */
    public void addNewComment(NewCommentRequest newCommentRequest) {
        service.getService().addNewComment(newCommentRequest).enqueue(new Callback<Comment>() {
            @Override
            public void onResponse(Call<Comment> call, Response<Comment> response) {
                if (response.isSuccessful()) {
                    List<Comment> currentCommentList = comments.getValue();
                    Comment responseComment = response.body();
                    currentCommentList.add(new Comment(responseComment.getId(), responseComment.getUsername(), responseComment.getContent()));
                    comments.postValue(currentCommentList);
                }
            }

            @Override
            public void onFailure(Call<Comment> call, Throwable t) {
                Log.e(LOG_TAG, t.getMessage());
            }
        });
    }

    /**
     * Add new reply to the comment
     *
     * @param newReplyRequest
     */
    public void addNewReply(NewReplyRequest newReplyRequest) {
        service.getService().addNewReply(newReplyRequest).enqueue(new Callback<NewReplyResponse>() {
            @Override
            public void onResponse(Call<NewReplyResponse> call, Response<NewReplyResponse> response) {
                if (response.isSuccessful()) {
                    int commentId = response.body().getCommentId();
                    NewReplyResponse replyResponse = response.body();
                    List<Comment> currentCommentList = comments.getValue();
                    for (Comment comment : currentCommentList) {
                        if (comment.getId() == commentId) {
                            comment.getReplies().add(new Reply(replyResponse.getId(), replyResponse.getUsername(), replyResponse.getContent()));
                            break;
                        }
                    }
                    comments.postValue(currentCommentList);
                }
            }

            @Override
            public void onFailure(Call<NewReplyResponse> call, Throwable t) {
                Log.e(LOG_TAG, t.getMessage());
            }
        });
    }

    public LiveData<List<Comment>> getComments() {
        return comments;
    }
}
