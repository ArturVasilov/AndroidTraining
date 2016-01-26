package com.elegion.android;

import android.app.Application;
import android.os.StrictMode;
import android.support.annotation.NonNull;

import com.elegion.android.app.Lifecycler;
import com.elegion.android.app.Settings;

import droidkit.content.TypedPrefs;

/**
 * @author Artur Vasilov
 */
public class AppDelegate extends Application {

    private Settings mSettings;

    @Override
    public void onCreate() {
        super.onCreate();
        if (BuildConfig.DEBUG) {
            StrictMode.enableDefaults();
        }
        registerActivityLifecycleCallbacks(new Lifecycler());
    }

    @NonNull
    public Settings getSettings() {
        if (mSettings == null) {
            mSettings = TypedPrefs.from(this, Settings.class);
        }
        return mSettings;
    }
}
