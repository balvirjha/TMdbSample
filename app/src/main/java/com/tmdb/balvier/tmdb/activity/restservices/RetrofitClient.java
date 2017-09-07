package com.tmdb.balvier.tmdb.activity.restservices;

import okhttp3.Cache;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Balvier on 9/6/2017.
 */

public class RetrofitClient {

    public static final String BASE_URL = "https://api.themoviedb.org/3/";
    public static final String IMAGE_BASE_URL = "https://image.tmdb.org/t/p/w45";
    public static final String IMAGE_BASE_URL_BACKDROP = "https://image.tmdb.org/t/p/w500";
    private static Retrofit retrofit = null;
    private static Cache cacheStatic;
    private static OkHttpClient okHttpClient;

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
}
