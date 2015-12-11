package ru.samples.itis.githubclient;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.multidex.MultiDexApplication;

import ru.samples.itis.githubclient.di.DaggerComponent;
import ru.samples.itis.githubclient.di.DaggerGraph;

/**
 * @author Artur Vasilov
 */
public class GithubApplication extends MultiDexApplication {

    private DaggerGraph mGraph;

    @Override
    public void onCreate() {
        super.onCreate();
        buildComponentAndInject();
    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
    }

    @NonNull
    public DaggerGraph graph() {
        return mGraph;
    }

    @NonNull
    public static DaggerGraph injector(Context context) {
        return ((GithubApplication) context.getApplicationContext()).graph();
    }

    public void buildComponentAndInject() {
        mGraph = DaggerComponent.Initializer.init(this);
    }
}
