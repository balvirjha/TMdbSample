package com.tmdb.balvier.tmdb.activity.restservices;

import okhttp3.Cache;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Balvier on 9/6/2017.
 */

public class RetrofitClient {

    public static final String GOOGLE_BASE_URL = "https://www.googleapis.com/";

    public static final String BASE_URL = "https://api.themoviedb.org/3/";
    public static final String IMAGE_BASE_URL = "https://image.tmdb.org/t/p/w500";
    public static final String IMAGE_BASE_URL_BACKDROP = "https://image.tmdb.org/t/p/w500";
    private static Retrofit retrofit = null, youtuberetrofit = null;
    private static Cache cacheStatic, youtubeCacheStatic;
    private static OkHttpClient okHttpClient, youtubeOkHttpClient;

    public static Retrofit getClient(Cache cache) {
        if (retrofit == null) {
            if (okHttpClient == null) {
                if (cacheStatic == null) {
                    cacheStatic = cache;
                }
                okHttpClient = new OkHttpClient.Builder()
                        .cache(cacheStatic)
                        .build();
            }
            retrofit = new Retrofit.Builder()
                    .client(okHttpClient)
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }

    public static Retrofit getYoutubeClient(Cache cache) {
        if (youtuberetrofit == null) {
            if (youtubeOkHttpClient == null) {
                if (youtubeCacheStatic == null) {
                    youtubeCacheStatic = cache;
                }
                youtubeOkHttpClient = new OkHttpClient.Builder()
                        .cache(youtubeCacheStatic)
                        .build();
            }
            youtuberetrofit = new Retrofit.Builder()
                    .client(youtubeOkHttpClient)
                    .baseUrl(GOOGLE_BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return youtuberetrofit;
    }
}
