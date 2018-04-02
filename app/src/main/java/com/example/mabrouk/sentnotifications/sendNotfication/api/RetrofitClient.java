package com.example.mabrouk.sentnotifications.sendNotfication.api;

import android.content.Context;
import android.util.Log;

import com.jakewharton.picasso.OkHttp3Downloader;

import java.io.File;

import okhttp3.Cache;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Mohammad mabrouk
 * 0201152644726
 * on 3/27/2018.  time :15:32
 */
public class RetrofitClient {
    private static Retrofit mRetrofit;
    private static  String BASEURL="https://fcm.googleapis.com";


    public static Retrofit getmRetrofit(){
        if (mRetrofit==null){
            synchronized (RetrofitClient.class){
                mRetrofit=new Retrofit.Builder()
                        .client(getOkHttpClient())
                        .baseUrl(BASEURL)
                        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();
            }
        }
        return mRetrofit;
    }

    public static OkHttpClient getOkHttpClient(){
        return new OkHttpClient()
                .newBuilder()
                .addInterceptor(getHttpLoggingInterceptor())
                .build();
    }

    public static OkHttpClient getOkHttpClientWithCach(Context mContext,int cacheSize){
        File cacheFile = new File(mContext.getCacheDir(), "HttpCache");
        cacheFile.mkdirs();
        Cache cache = new Cache(cacheFile, cacheSize * 1000 * 1000); //10 MB
        return new OkHttpClient()
                .newBuilder()
                .addInterceptor(getHttpLoggingInterceptor())
                .cache(cache)
                .build();
    }

    public static OkHttp3Downloader getOkHttp3Downloader(Context mContext,int cacheSize){
        return new OkHttp3Downloader(getOkHttpClientWithCach(mContext,cacheSize));
    }

    public static HttpLoggingInterceptor getHttpLoggingInterceptor(){

        HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor(new HttpLoggingInterceptor.Logger() {
            @Override
            public void log(String message) {
                Log.d("httpLoggingInterceptor", message + "");
            }

        });
        httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        return httpLoggingInterceptor;
    }

    public static Retrofit getmRetrofitWithCache(Context mContext,int cacheSize){
        if (mRetrofit==null){
            synchronized (RetrofitClient.class){
                mRetrofit=new Retrofit.Builder()
                        .baseUrl(BASEURL)
                        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                        .addConverterFactory(GsonConverterFactory.create())
                        .client(getOkHttpClientWithCach(mContext,cacheSize))
                        .build();
            }
        }
        return mRetrofit;
    }
}
