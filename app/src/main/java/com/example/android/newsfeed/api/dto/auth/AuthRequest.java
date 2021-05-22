package com.example.android.newsfeed.api.dto.auth;

import com.google.gson.annotations.SerializedName;

public class AuthRequest {

    @SerializedName("fbId")
    private String facebookId;

    @SerializedName("username")
    private String username;

    public AuthRequest(String facebookId, String username) {
        this.facebookId = facebookId;
        this.username = username;
    }

    public String getFacebookId() {
        return facebookId;
    }

    public String getUsername() {
        return username;
    }
}
