package com.example.android.newsfeed.api.dto.comment;

import com.google.gson.annotations.SerializedName;

public class GetCommentsRequest {
    @SerializedName("postId")
    private int newsId;

    public GetCommentsRequest(int newsId) {
        this.newsId = newsId;
    }
}
