package com.example.news;

import androidx.lifecycle.MutableLiveData;

import com.example.news.API.ApiUtils;
import com.example.news.API.RetroFitInstance;
import com.example.news.data.HeadLine;
import com.example.news.data.NewsResponse;

import java.util.List;


import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;

public class NewsModel {
    private String key = "3054ff50617e45d3bf990b28bcf27bcf";
    public MutableLiveData<NewsResponse> allHeadLinesLiveData = new MutableLiveData<>();
    public MutableLiveData<String>  allHeadLinesErrorLiveData = new MutableLiveData<>();
    public void getAllHeadLines(){
        Call<NewsResponse> allHeadLines = RetroFitInstance.getRetroFitInstance().getAllHeadLines("us", key);
        ApiUtils.enqueueToLiveData(allHeadLines, allHeadLinesLiveData, allHeadLinesErrorLiveData);
    }

}
