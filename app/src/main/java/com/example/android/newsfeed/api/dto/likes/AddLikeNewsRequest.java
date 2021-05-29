package com.example.android.newsfeed.api.dto.likes;

import com.google.gson.annotations.SerializedName;

public class AddLikeNewsRequest {

    @SerializedName("postId")
    private int newsId;

    @SerializedName("token")
    private String token;

    public AddLikeNewsRequest(int newsId, String token) {
        this.newsId = newsId;
        this.token = token;
    }
}
