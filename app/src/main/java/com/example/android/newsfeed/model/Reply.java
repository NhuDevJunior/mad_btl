package com.example.android.newsfeed.model;

import com.google.gson.annotations.SerializedName;

public class Reply {

    @SerializedName("id")
    private int id;

    @SerializedName("username")
    private String username;

    @SerializedName("content")
    private String content;

    public Reply(int id, String username, String content) {
        this.id = id;
        this.username = username;
        this.content = content;
    }

    public int getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getContent() {
        return content;
    }
}
