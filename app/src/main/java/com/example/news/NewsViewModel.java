package com.example.news;

import android.content.Context;
import android.content.res.AssetManager;
import android.widget.Toast;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.news.API.ApiUtils;
import com.example.news.API.RetroFitInstance;
import com.example.news.data.NewsResponse;

import android.util.Log;

import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;

import retrofit2.Call;

public class NewsViewModel extends ViewModel {

    public MutableLiveData<NewsResponse> allHeadLinesLiveData = new MutableLiveData<>();
     public MutableLiveData<String> allHeadLinesLiveDataError = new MutableLiveData<>();
    public void fetchHeadLines(String country, String category, Context context){
        try {
            AssetManager assetManager = context.getAssets();
            InputStream is = assetManager.open("key.json");

            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();

            String json = new String(buffer, "UTF-8").trim();

            // Parse JSON
            JSONObject obj = new JSONObject(json);
            String key = obj.getString("API_KEY");
            if (key.endsWith(";")) {
                key = key.substring(0, key.length() - 1);
            }
            key = key.substring(2, key.length() - 1);


            Log.i("got the key", key);
            Call<NewsResponse> allHeadLines = RetroFitInstance.getRetroFitInstance().getAllHeadLines(country, category, key);
            ApiUtils.enqueueToLiveData(allHeadLines, allHeadLinesLiveData, allHeadLinesLiveDataError);

        } catch (Exception e) {
            Log.i("got the key", e.getMessage());
            e.printStackTrace();
            allHeadLinesLiveData.setValue(null);
        }

    }
    public MutableLiveData<NewsResponse>  getAllHeadLinesLiveData() {
        return allHeadLinesLiveData;
    }

}
