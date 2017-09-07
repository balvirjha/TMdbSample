package com.tmdb.balvier.tmdb.activity.downloadmoviewdata;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

/**
 * Created by Balvier on 9/7/2017.
 */

public class InstallReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        Log.e("bvc", "InstallReceiver BroadcastReceiver called ");
        new DownloadMovieScheduler().scheduleJob();
    }
}
