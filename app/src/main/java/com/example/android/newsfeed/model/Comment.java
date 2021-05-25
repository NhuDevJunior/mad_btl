package com.example.android.newsfeed.model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class Comment {
    @SerializedName("id")
    private int id;

    @SerializedName("username")
    private String username;

    @SerializedName("content")
    private String content;

    @SerializedName("replies")
    private List<Reply> replies;

    public Comment(int id, String username, String content) {
        this.id = id;
        this.username = username;
        this.content = content;
        replies = new ArrayList<>();
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

    public List<Reply> getReplies() {
        return replies;
    }
}
