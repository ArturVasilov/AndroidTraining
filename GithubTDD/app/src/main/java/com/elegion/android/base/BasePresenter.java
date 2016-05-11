package com.elegion.android.base;

import android.app.LoaderManager;
import android.content.Context;
import android.support.annotation.NonNull;

import com.elegion.android.AppDelegate;
import com.elegion.android.api.ApiFactory;
import com.elegion.android.api.GithubService;
import com.elegion.android.app.Settings;

/**
 * @author Artur Vasilov
 */
public class BasePresenter<View extends BaseView> {

    private final Context mContext;
    private final LoaderManager mLoaderManager;
    private final View mView;

    public BasePresenter(@NonNull Context context, @NonNull LoaderManager manager, @NonNull View view) {
        mContext = context;
        mLoaderManager = manager;
        mView = view;
    }

    @NonNull
    public Context getContext() {
        return mContext;
    }

    @NonNull
    public LoaderManager getLoaderManager() {
        return mLoaderManager;
    }

    @NonNull
    public View getView() {
        return mView;
    }

    @NonNull
    public AppDelegate getAppDelegate() {
        return (AppDelegate) mContext.getApplicationContext();
    }

    @NonNull
    public Settings getSettings() {
        return getAppDelegate().getSettings();
    }

    @NonNull
    public GithubService getService() {
        return ApiFactory.getGithubService();
    }
}
