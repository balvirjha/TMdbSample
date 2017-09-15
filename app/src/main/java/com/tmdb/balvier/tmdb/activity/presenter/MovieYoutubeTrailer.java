package com.tmdb.balvier.tmdb.activity.presenter;

import android.util.Log;

import com.tmdb.balvier.tmdb.activity.ApplicationClass;
import com.tmdb.balvier.tmdb.activity.modal.youtuberesponse.YoutubeResponse;
import com.tmdb.balvier.tmdb.activity.restservices.MovieAPI;
import com.tmdb.balvier.tmdb.activity.restservices.RetrofitClient;

import okhttp3.Cache;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Balvier on 9/15/2017.
 */

public class MovieYoutubeTrailer implements MoviePresenter.MovieTrailerGoogleRequestCallback,
        Callback<YoutubeResponse> {

    MoviePresenter.MovieTrailerGoogleResponseCallback movieTrailerGoogleResponseCallback;

    @Override
    public void getMovieGoogleTrailer(MoviePresenter.MovieTrailerGoogleResponseCallback movieTrailerGoogleResponseCallback, String movie_id) {
        this.movieTrailerGoogleResponseCallback = movieTrailerGoogleResponseCallback;
        RetrofitClient.getYoutubeClient(new Cache(ApplicationClass.getApplicationConotext().getCacheDir(), 500 * 1024 * 1024)).create(MovieAPI.class).getMovieTrailersFromGoogle("snippet,contentDetails,statistics", movie_id, ApplicationClass.getDeveloperKey()).enqueue(this);
    }

    @Override
    public void errorRequestingMovieGoogleTrailer(String errormessage) {

    }

    @Override
    public void onResponse(Call<YoutubeResponse> call, Response<YoutubeResponse> response) {
        Log.e("bvc", "YoutubeResponse onResponse called ");
        Log.e("bvc", "" + response.code());
        Log.e("bvc", "" + response.body());
        if (response.code() == 200 && movieTrailerGoogleResponseCallback != null) {
            movieTrailerGoogleResponseCallback.getMovieGoogleTrailerSuccess(response, response.code());
        } else if (movieTrailerGoogleResponseCallback != null) {
            movieTrailerGoogleResponseCallback.errorGettingMovieGoogleTrailer(response.message());
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
    public void onFailure(Call<YoutubeResponse> call, Throwable t) {
        Log.e("bvc", "onFailure called ");
        t.printStackTrace();
        if (movieTrailerGoogleResponseCallback != null) {
            movieTrailerGoogleResponseCallback.errorGettingMovieGoogleTrailer(t.getMessage());
        }
    }
}
