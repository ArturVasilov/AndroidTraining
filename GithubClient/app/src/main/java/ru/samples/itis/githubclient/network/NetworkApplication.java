package ru.samples.itis.githubclient.network;

import android.app.Application;
import android.support.annotation.NonNull;

/**
 * @author Artur Vasilov
 */
public class NetworkApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
    }

    public void execute(@NonNull Executor executor) {

    }
}
