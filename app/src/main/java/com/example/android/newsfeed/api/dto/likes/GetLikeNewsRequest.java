package com.example.android.newsfeed.api.dto.likes;

import com.google.gson.annotations.SerializedName;

public class GetLikeNewsRequest {

    @SerializedName("token")
    private String token;

    public GetLikeNewsRequest(String token) {
        this.token = token;
    }
}
