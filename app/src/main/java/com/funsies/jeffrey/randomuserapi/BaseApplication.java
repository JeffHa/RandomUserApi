package com.funsies.jeffrey.randomuserapi;

import android.app.Application;

import com.squareup.leakcanary.LeakCanary;

import timber.log.Timber;

/**
 * Created by Jeffrey on 2/9/2017.
 */

public class BaseApplication extends Application {

    @Override public void onCreate() {
        setupLeakCanary();
        Timber.plant(new Timber.DebugTree());
    }

    private void setupLeakCanary() {
        if (LeakCanary.isInAnalyzerProcess(this)) {
            return;
        }
        LeakCanary.install(this);
    }
}
