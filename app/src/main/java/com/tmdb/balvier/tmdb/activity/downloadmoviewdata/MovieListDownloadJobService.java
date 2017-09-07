package com.tmdb.balvier.tmdb.activity.downloadmoviewdata;

import android.util.Log;

import com.firebase.jobdispatcher.JobParameters;
import com.firebase.jobdispatcher.JobService;

import com.tmdb.balvier.tmdb.activity.presenter.MovieList;

/**
 * Created by Balvier on 9/6/2017.
 */

public class MovieListDownloadJobService extends JobService {
    @Override
    public boolean onStartJob(JobParameters job) {
        Log.e("bvc", "MovieListDownloadJobService called ");
        new MovieList().getMovieList(null);
        return true;
    }

    @Override
    public boolean onStopJob(JobParameters job) {
        return true;
    }
}
