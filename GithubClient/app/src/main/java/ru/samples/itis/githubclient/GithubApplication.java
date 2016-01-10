package ru.samples.itis.githubclient;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.multidex.MultiDex;

import ru.samples.itis.githubclient.content.auth.GithubAccount;
import ru.samples.itis.githubclient.di.Graphs;
import ru.samples.itis.githubclient.network.NetworkApplication;

/**
 * @author Artur Vasilov
 */
public class GithubApplication extends NetworkApplication {

    private Graphs mGraphs;

    @Override
    public void onCreate() {
        super.onCreate();
        mGraphs = new Graphs(this);
        GithubAccount.setup(this);
    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);
    }

    @NonNull
    public static GithubApplication get(Context context) {
        return (GithubApplication) context.getApplicationContext();
    }

    @NonNull
    public static Graphs graphs(Context context) {
        return get(context).mGraphs;
    }
}
