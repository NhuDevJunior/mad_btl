package com.example.android.newsfeed.api.dto.comment;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.google.gson.annotations.JsonAdapter;
import com.google.gson.annotations.SerializedName;

import java.lang.reflect.Type;

public class NewReplyResponse {
    @SerializedName("comment")
    @JsonAdapter(CommentIdDeserializer.class)
    private int commentId;

    @SerializedName("id")
    private int id;

    @SerializedName("content")
    private String content;

    @SerializedName("username")
    private String username;

    public int getCommentId() {
        return commentId;
    }

    public int getId() {
        return id;
    }

    public String getContent() {
        return content;
    }

    public String getUsername() {
        return username;
    }
}

class CommentIdDeserializer implements JsonDeserializer<Integer> {

    @Override
    public Integer deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        return json.getAsJsonObject().get("id").getAsInt();
    }
}
