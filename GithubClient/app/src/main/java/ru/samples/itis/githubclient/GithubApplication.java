package ru.samples.itis.githubclient;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.multidex.MultiDex;

import ru.samples.itis.githubclient.content.auth.GithubAccount;
import ru.samples.itis.githubclient.di.DaggerComponent;
import ru.samples.itis.githubclient.di.DaggerGraph;
import ru.samples.itis.githubclient.network.NetworkApplication;

/**
 * @author Artur Vasilov
 */
public class GithubApplication extends NetworkApplication {

    private DaggerGraph mGraph;

    @Override
    public void onCreate() {
        super.onCreate();
        GithubAccount.setup(this);
        buildComponentAndInject();
    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);
    }

    @NonNull
    public DaggerGraph graph() {
        return mGraph;
    }

    @NonNull
    public static DaggerGraph injector(Context context) {
        return get(context).graph();
    }

    @NonNull
    public static GithubApplication get(Context context) {
        return (GithubApplication) context.getApplicationContext();
    }

    public void buildComponentAndInject() {
        mGraph = DaggerComponent.Initializer.init(this);
    }
}
