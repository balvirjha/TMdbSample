package com.tmdb.balvier.tmdb.activity.downloadmoviewdata;

import android.util.Log;

import com.firebase.jobdispatcher.Constraint;
import com.firebase.jobdispatcher.FirebaseJobDispatcher;
import com.firebase.jobdispatcher.GooglePlayDriver;
import com.firebase.jobdispatcher.Job;
import com.firebase.jobdispatcher.Lifetime;
import com.firebase.jobdispatcher.RetryStrategy;
import com.firebase.jobdispatcher.Trigger;
import com.tmdb.balvier.tmdb.activity.ApplicationClass;
import com.tmdb.balvier.tmdb.activity.presenter.MovieList;

/**
 * Created by Balvier on 9/7/2017.
 */

public class DownloadMovieScheduler {
    public DownloadMovieScheduler() {

    }

    public void scheduleJob() {
        Log.e("bvc", "scheduleJob called ");
        new MovieList().getMovieList(null);
        FirebaseJobDispatcher firebaseJobDispatcher = new FirebaseJobDispatcher(new GooglePlayDriver(ApplicationClass.getApplicationConotext()));
        Job myJob = firebaseJobDispatcher.newJobBuilder()
                .setService(MovieListDownloadJobService.class) // the JobService that will be called
                .setTag("my-unique-tag")        // uniquely identifies the job
                .setRecurring(false)
                // don't persist past a device reboot
                .setLifetime(Lifetime.FOREVER)
                // start between 0 and 60 seconds from now
                .setTrigger(Trigger.executionWindow(0, 60))
                // don't overwrite an existing job with the same tag
                .setReplaceCurrent(false)
                // retry with exponential backoff
                .setRetryStrategy(RetryStrategy.DEFAULT_EXPONENTIAL)
                // constraints that need to be satisfied for the job to run
                .setConstraints(
                        // only run on an unmetered network
                        Constraint.ON_ANY_NETWORK
                ).build();

        firebaseJobDispatcher.mustSchedule(myJob);
    }
}
