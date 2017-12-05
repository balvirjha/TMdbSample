package com.tmdb.balvier.tmdb.activity.downloadmoviewdata;

import android.content.Intent;
import android.support.v4.content.LocalBroadcastManager;
import android.util.Log;

import com.firebase.jobdispatcher.JobParameters;
import com.firebase.jobdispatcher.JobService;
import com.tmdb.balvier.tmdb.activity.ApplicationClass;
import com.tmdb.balvier.tmdb.activity.modal.MovieListResponse;
import com.tmdb.balvier.tmdb.activity.presenter.MovieList;
import com.tmdb.balvier.tmdb.activity.presenter.MoviePresenter;

import retrofit2.Response;

/**
 * Created by Balvier on 9/6/2017.
 */

public class MovieListDownloadJobService extends JobService {
    @Override
    public boolean onStartJob(JobParameters job) {
        Log.e("bvc", "MovieListDownloadJobService called ");
        new MovieList().getMovieList(new MoviePresenter.MovieListResponseCallback() {
            @Override
            public void getMovieListSuccess(Response<MovieListResponse> response, int responseCode) {
                Log.e("bvc", "job finished success");
                jobFinished(job, true);
                LocalBroadcastManager.getInstance(ApplicationClass.getActivityConotext())
                        .sendBroadcast(new Intent("custom-event-name").putExtra("data", response.body()));
                Log.e("bvc", "bradcast calling");
            }

            @Override
            public void errorGettingMovieList(String errormessage) {
                Log.e("bvc", "job finished faiulure");
                jobFinished(job, true);
            }
        });
        return true;
    }

    @Override
    public boolean onStopJob(JobParameters job) {
        Log.e("bvc", "  on stop job called");
        return true;
    }
}
