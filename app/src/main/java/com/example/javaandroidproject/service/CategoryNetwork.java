package com.example.javaandroidproject.service;

import com.example.javaandroidproject.contants.Urls;
import com.example.javaandroidproject.network.CategoryApi;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class CategoryNetwork {
    private static CategoryNetwork mInstance;
    private static final String BASE_URL = Urls.BASE;
    private Retrofit mRetrofit;
    private CategoryNetwork() {
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .connectTimeout(20, TimeUnit.SECONDS)
                .writeTimeout(20, TimeUnit.SECONDS)
                .readTimeout(20, TimeUnit.SECONDS)
                .build();
        mRetrofit = new Retrofit.Builder()
                .client(okHttpClient)
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }
    public static CategoryNetwork getInstance() {
        if(mInstance==null)
            mInstance=new CategoryNetwork();
        return mInstance;
    }
    public CategoryApi getJsonApi()
    {
        return mRetrofit.create(CategoryApi.class);
    }
}
