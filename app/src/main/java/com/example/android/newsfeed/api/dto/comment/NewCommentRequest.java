package com.example.android.newsfeed.api.dto.comment;

import com.google.gson.annotations.SerializedName;

public class NewCommentRequest {
    @SerializedName("commentContent")
    private String content;

    @SerializedName("postId")
    private int newsId;

    @SerializedName("token")
    private String token;

    public NewCommentRequest(String content, int newsId, String token) {
        this.content = content;
        this.newsId = newsId;
        this.token = token;
    }
}
