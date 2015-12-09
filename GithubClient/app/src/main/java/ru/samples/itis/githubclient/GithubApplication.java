package ru.samples.itis.githubclient;

import android.app.Application;
import android.content.Context;
import android.support.annotation.NonNull;

import ru.samples.itis.githubclient.di.DaggerComponent;
import ru.samples.itis.githubclient.di.DaggerGraph;

/**
 * @author Artur Vasilov
 */
public class GithubApplication extends Application {

    private DaggerGraph mGraph;

    @Override
    public void onCreate() {
        super.onCreate();
        buildComponentAndInject();
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
