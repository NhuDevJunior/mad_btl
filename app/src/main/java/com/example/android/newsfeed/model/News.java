package com.example.android.newsfeed.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class News implements Serializable {

    @SerializedName("id")
    private int id;

    @SerializedName("publisher_name")
    private String publisherName;

    @SerializedName("category")
    private String category;

    @SerializedName("avatar")
    private String avatar;

    @SerializedName("title")
    private String title;

    @SerializedName("description")
    private String description;

    @SerializedName("content")
    private String content;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPublisherName() {
        return publisherName;
    }

    public void setPublisherName(String publisherName) {
        this.publisherName = publisherName;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
