package com.example.android.newsfeed.api.dto.comment;

import com.google.gson.annotations.SerializedName;

public class NewReplyRequest {
    @SerializedName("replyContent")
    private String content;

    @SerializedName("commentId")
    private int commentId;

    @SerializedName("token")
    private String token;

    public NewReplyRequest(String content, int commentId, String token) {
        this.content = content;
        this.commentId = commentId;
        this.token = token;
    }
}
