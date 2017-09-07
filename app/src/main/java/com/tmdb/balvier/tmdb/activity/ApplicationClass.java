package com.tmdb.balvier.tmdb.activity;

import android.app.Application;
import android.content.Context;

import com.tmdb.balvier.tmdb.R;

/**
 * Created by Balvier on 9/7/2017.
 */

public class ApplicationClass extends Application {

    private static Context CONTEXT;

    @Override
    public void onCreate() {
        super.onCreate();
        this.CONTEXT = this;
    }

    public static Context getApplicationConotext() {
        return CONTEXT;
    }

    public static boolean isTablet() {
        return CONTEXT.getResources().getBoolean(R.bool.isTablet);
    }
}
