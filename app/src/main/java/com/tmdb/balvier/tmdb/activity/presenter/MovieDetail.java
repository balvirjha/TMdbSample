package com.tmdb.balvier.tmdb.activity.presenter;

import android.util.Log;

import com.tmdb.balvier.tmdb.activity.ApplicationClass;
import com.tmdb.balvier.tmdb.activity.modal.MovieDetailResponse;
import com.tmdb.balvier.tmdb.activity.restservices.MovieAPI;
import com.tmdb.balvier.tmdb.activity.restservices.RetrofitClient;

import okhttp3.Cache;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Balvier on 9/7/2017.
 */

public class MovieDetail implements MoviePresenter.MovieDetailRequestCallback, Callback<MovieDetailResponse.MovieDetailClass> {
    MoviePresenter.MovieDetailResponseCallback movieDetailResponseCallback;

    @Override
    public void getMovieDetail(MoviePresenter.MovieDetailResponseCallback movieDetailResponseCallback, String movie_id) {
        this.movieDetailResponseCallback = movieDetailResponseCallback;
        RetrofitClient.getClient(new Cache(ApplicationClass.getApplicationConotext().getCacheDir(), 10 * 1024 * 1024)).create(MovieAPI.class).getMovieDetails(movie_id, "b7cd3340a794e5a2f35e3abb820b497f").enqueue(this);
    }

    @Override
    public void errorRequestingMovieDetail(String errormessage) {

    }

    @Override
    public void onResponse(Call<MovieDetailResponse.MovieDetailClass> call, Response<MovieDetailResponse.MovieDetailClass> response) {
        Log.e("bvc", "onResponse called ");
        Log.e("bvc", "" + response.code());
        Log.e("bvc", "" + response.body());
        if (response.code() == 200 && movieDetailResponseCallback != null) {
            movieDetailResponseCallback.getMovieDetailSuccess(response, response.code());
        } else if (movieDetailResponseCallback != null) {
            movieDetailResponseCallback.errorGettingMovieDetail(response.message());
        } else {
            Log.e("bvc", "bradcast calling");
           /* Intent intent = new Intent("com.tmdb.balvier.tmdb.activity.receivingData");
            // You can also include some extra data.
            intent.putExtra("message", "This is my message!");
            Bundle bundle = new Bundle();
            bundle.putSerializable("movieList", response.body());
            intent.putExtras(bundle);
            ApplicationClass.getApplicationConotext().sendBroadcast(intent);*/
        }
    }

    @Override
    public void onFailure(Call<MovieDetailResponse.MovieDetailClass> call, Throwable t) {
        Log.e("bvc", "onFailure called ");
        t.printStackTrace();
        if (movieDetailResponseCallback != null) {
            movieDetailResponseCallback.errorGettingMovieDetail(t.getMessage());
        }
    }
}
