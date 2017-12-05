package com.tmdb.balvier.tmdb.activity.downloadmoviewdata;

import android.util.Log;
import android.widget.Toast;

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
    private static FirebaseJobDispatcher firebaseJobDispatcher;
    private static DownloadMovieScheduler downloadMovieScheduler = new DownloadMovieScheduler();

    private DownloadMovieScheduler() {

    }

    public static synchronized DownloadMovieScheduler getInstance() {

        if (downloadMovieScheduler == null) {
            downloadMovieScheduler = new DownloadMovieScheduler();
            return downloadMovieScheduler;
        } else {
            return downloadMovieScheduler;
        }

    }

    public void scheduleJob() {
        Log.e("bvc", "scheduleJob called ");
        new MovieList().getMovieList(null);
        firebaseJobDispatcher = new FirebaseJobDispatcher(new GooglePlayDriver(ApplicationClass.getApplicationConotext()));
        Job myJob = firebaseJobDispatcher.newJobBuilder()
                .setService(MovieListDownloadJobService.class)
                .setTag("movie_list_download_job")
                .setConstraints(
                        Constraint.ON_ANY_NETWORK,
                        Constraint.DEVICE_IDLE
                ).setRecurring(true)
                .setTrigger(Trigger.executionWindow(1, 20))
                .setRetryStrategy(RetryStrategy.DEFAULT_LINEAR)
                .setLifetime(Lifetime.FOREVER)
                .build();

        firebaseJobDispatcher.mustSchedule(myJob);
        Toast.makeText(ApplicationClass.getApplicationConotext(), "Job Scheduled..", Toast.LENGTH_SHORT).show();
    }

    public void cancellAllJobs() {
        if (firebaseJobDispatcher != null) {
            firebaseJobDispatcher.cancelAll();
        }
    }
}
