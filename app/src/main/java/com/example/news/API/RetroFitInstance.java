package com.example.news.API;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetroFitInstance {
    private static NewsInterface newsInterface;
    public static NewsInterface getRetroFitInstance(){
        if(newsInterface==null){
            OkHttpClient client = new OkHttpClient.Builder()
                    .addInterceptor(chain -> {
                        Request request = chain.request().newBuilder()
                                .header("User-Agent", "Android-App") // add this
                                .build();
                        return chain.proceed(request);
                    })
                    .build();

            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl("https://newsapi.org/v2/")
                    .client(client)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
            newsInterface = retrofit.create(NewsInterface.class);
        }
        return newsInterface;
    }
}
