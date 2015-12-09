package ru.samples.itis.githubclient.di.module;

import android.content.Context;
import android.content.res.Resources;
import android.support.annotation.NonNull;

import dagger.Module;
import dagger.Provides;
import ru.samples.itis.githubclient.GithubApplication;

/**
 * @author Artur Vasilov
 */
@Module
public class MainModule {

    private GithubApplication mApplication;

    public MainModule(@NonNull GithubApplication application) {
        mApplication = application;
    }

    @Provides
    protected Context context() {
        return mApplication;
    }

    @Provides
    protected Resources resources() {
        return mApplication.getResources();
    }
}
