package com.example.android.newsfeed.api.dto.news;

import com.google.gson.annotations.SerializedName;

public class NewsResponse {

    @SerializedName("id")
    private int id;

    @SerializedName("publisher_name")
    private String publisherName;

    @SerializedName("category")
    private String category;

}
