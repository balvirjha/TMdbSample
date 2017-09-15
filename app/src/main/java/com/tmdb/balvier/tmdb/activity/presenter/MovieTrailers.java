package com.tmdb.balvier.tmdb.activity.presenter;

import android.util.Log;

import com.tmdb.balvier.tmdb.activity.ApplicationClass;
import com.tmdb.balvier.tmdb.activity.modal.MovieTrailersResponse;
import com.tmdb.balvier.tmdb.activity.restservices.MovieAPI;
import com.tmdb.balvier.tmdb.activity.restservices.RetrofitClient;

import okhttp3.Cache;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Balvier on 9/15/2017.
 */

public class MovieTrailers implements Callback<MovieTrailersResponse>, MoviePresenter.MovieTrailerRequestCallback {
    MoviePresenter.MovieTrailerResponseCallback movieTrailerResponseCallback;

    @Override
    public void onResponse(Call<MovieTrailersResponse> call, Response<MovieTrailersResponse> response) {
        Log.e("bvc", " MovieTrailersResponse onResponse called ");
        Log.e("bvc", "" + response.code());
        Log.e("bvc", "" + response.body());
        if (response.code() == 200 && movieTrailerResponseCallback != null) {
            Log.e("bvc", "onResponse called trailer " + (response.body().getId()));
            movieTrailerResponseCallback.getMovieTrailerSuccess(response, response.code());
        } else if (movieTrailerResponseCallback != null) {
            movieTrailerResponseCallback.errorGettingMovieTrailer(response.message());
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
    public void onFailure(Call<MovieTrailersResponse> call, Throwable t) {
        t.printStackTrace();
        Log.e("bvc", "onFailure");
    }

    @Override
    public void getMovieTrailer(MoviePresenter.MovieTrailerResponseCallback movieTrailerResponseCallback, String movie_id) {
        this.movieTrailerResponseCallback = movieTrailerResponseCallback;
        RetrofitClient.getClient(new Cache(ApplicationClass.getApplicationConotext().getCacheDir(), 500 * 1024 * 1024))
                .create(MovieAPI.class).getMovieTrailers(movie_id, "bc0ef9cc6b8a9f9fc6588ed2c678f1af").enqueue(this);
    }

    @Override
    public void errorRequestingMovieTrailer(String errormessage) {
        Log.e("bvc", "errorRequestingMovieTrailer : " + errormessage);
    }
}
