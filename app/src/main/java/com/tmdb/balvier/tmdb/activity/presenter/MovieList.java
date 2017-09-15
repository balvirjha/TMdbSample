package com.tmdb.balvier.tmdb.activity.presenter;

import android.content.Intent;
import android.support.v4.content.LocalBroadcastManager;
import android.util.Log;

import com.tmdb.balvier.tmdb.activity.ApplicationClass;
import com.tmdb.balvier.tmdb.activity.modal.MovieListResponse;
import com.tmdb.balvier.tmdb.activity.restservices.MovieAPI;
import com.tmdb.balvier.tmdb.activity.restservices.RetrofitClient;

import okhttp3.Cache;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Balvier on 9/6/2017.
 */

public class MovieList implements MoviePresenter.MovieListRequestCallback, Callback<MovieListResponse> {

    private MoviePresenter.MovieListResponseCallback movieListResponseCallback;

    @Override
    public void getMovieList(MoviePresenter.MovieListResponseCallback movieListResponseCallback) {
        Log.e("bvc", "getMovieList called ");
        this.movieListResponseCallback = movieListResponseCallback;
        RetrofitClient.getClient(new Cache(ApplicationClass.getApplicationConotext().getCacheDir(), 500 * 1024 * 1024)).create(MovieAPI.class).getMovieList("b7cd3340a794e5a2f35e3abb820b497f").enqueue(this);
    }

    @Override
    public void errorRequestingMovieList(String errormessage) {
        if (movieListResponseCallback != null) {
            movieListResponseCallback.errorGettingMovieList("No network.");
        }
    }

    @Override
    public void onResponse(Call<MovieListResponse> call, Response<MovieListResponse> response) {
        Log.e("bvc", "onResponse called ");
        Log.e("bvc", "" + response.code());
        Log.e("bvc", "" + response.body());
        if (response.code() == 200 && movieListResponseCallback != null) {
            if (ApplicationClass.getActivityConotext() != null) {
                LocalBroadcastManager.getInstance(ApplicationClass.getActivityConotext())
                        .sendBroadcast(new Intent("custom-event-name").putExtra("data", response.body()));
            }
            movieListResponseCallback.getMovieListSuccess(response, response.code());
        } else if (movieListResponseCallback != null) {
            movieListResponseCallback.errorGettingMovieList(response.message());
        } else {
            if (ApplicationClass.getActivityConotext() != null) {
                LocalBroadcastManager.getInstance(ApplicationClass.getActivityConotext())
                        .sendBroadcast(new Intent("custom-event-name").putExtra("data", response.body()));
                Log.e("bvc", "bradcast calling");
            }
        }
    }

    @Override
    public void onFailure(Call<MovieListResponse> call, Throwable t) {
        Log.e("bvc", "onFailure called ");
        t.printStackTrace();
        if (movieListResponseCallback != null) {
            movieListResponseCallback.errorGettingMovieList(t.getMessage());
        }
    }
}
