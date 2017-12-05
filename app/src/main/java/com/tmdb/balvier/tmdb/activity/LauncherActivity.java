package com.tmdb.balvier.tmdb.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Keep;
import android.support.v7.app.AppCompatActivity;

@Keep
public class LauncherActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        MovieActivity.ACTIVITY = this;
        startActivity(new Intent(this, MovieActivity.class));
    }

    public static void killMe(final Activity activity) {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if (activity != null)
                    activity.finish();
            }
        }, 500);

    }
}
