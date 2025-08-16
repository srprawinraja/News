package com.example.news.API;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

import com.example.news.data.*;

import java.util.List;

public interface NewsInterface{

    @GET("everything")
    Call<List<News>> getAllNews (
            @Query("q") String query,
            @Query("apiKey") String apiKey
    );

    @GET("top-headlines")
        Call<NewsResponse> getAllHeadLines(
        @Query("country") String country,
        @Query("apiKey") String apiKey
    );

}


