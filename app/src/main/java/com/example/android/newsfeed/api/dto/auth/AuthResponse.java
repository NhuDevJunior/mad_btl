package com.example.android.newsfeed.api.dto.auth;

import com.google.gson.annotations.SerializedName;

public class AuthResponse {

    @SerializedName("token")
    private String token;

    public AuthResponse(String token) {
        this.token = token;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
