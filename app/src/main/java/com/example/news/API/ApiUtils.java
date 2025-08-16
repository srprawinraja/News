package com.example.news.API;

import android.content.Context;
import android.widget.Toast;

import androidx.lifecycle.MutableLiveData;

import com.example.news.data.NewsResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
public class ApiUtils {

    // Generic method to enqueue any Retrofit call
    public static void enqueueToLiveData(Call<NewsResponse> call, MutableLiveData<NewsResponse> allHeadLinesLiveData, MutableLiveData<String>  allHeadLinesErrorLiveData) {
        call.enqueue(new Callback<>() {
            @Override
            public void onResponse(Call<NewsResponse> call, Response<NewsResponse> response) {

                if (response.isSuccessful() && response.body() != null) {
                    allHeadLinesLiveData.setValue(response.body());
                } else {
                    allHeadLinesErrorLiveData.setValue(String.valueOf(response.code()));
                }
            }

            @Override
            public void onFailure(Call<NewsResponse> call, Throwable t) {
                allHeadLinesErrorLiveData.setValue("oops");
            }
        });
    }
}
