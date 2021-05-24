package com.example.android.newsfeed.api;

import com.example.android.newsfeed.api.dto.auth.AuthRequest;
import com.example.android.newsfeed.api.dto.auth.AuthResponse;
import com.example.android.newsfeed.api.dto.news.NewsRequest;
import com.example.android.newsfeed.model.News;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface MyApiService {
    @POST("user/login")
    Call<AuthResponse> authUser(@Body AuthRequest authRequest);

    @POST("post/details/{postId}")
    Call<News> getNewsById(@Path("postId") int postId);

    @GET("post/news")
    Call<List<News>> getListNews(@Query("page") int page);
    @GET("post/category/{category}")
    Call<List<News>> getListNewsByCategory(@Path("category") String category);
    @GET("post/search")
    Call<List<News>> getListNewsByKeyword(@Query("keyword") String keyword);
    @POST("post/hight-light")
    Call<List<News>> getListHighLightNews();
}
