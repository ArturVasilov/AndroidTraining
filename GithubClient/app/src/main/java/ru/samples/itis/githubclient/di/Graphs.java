package ru.samples.itis.githubclient.di;

import android.content.Context;
import android.support.annotation.NonNull;

import ru.samples.itis.githubclient.GithubApplication;
import ru.samples.itis.githubclient.di.component.ActivityComponent;
import ru.samples.itis.githubclient.di.component.AppComponent;
import ru.samples.itis.githubclient.di.component.FragmentComponent;
import ru.samples.itis.githubclient.di.graph.ActivityGraph;
import ru.samples.itis.githubclient.di.graph.ComponentGraph;
import ru.samples.itis.githubclient.di.graph.FragmentGraph;

/**
 * @author Artur Vasilov
 */
public class Graphs {

    private ActivityGraph mActivityGraph;
    private FragmentGraph mFragmentGraph;
    private ComponentGraph mComponentGraph;

    public Graphs(@NonNull GithubApplication application) {
        mActivityGraph = ActivityComponent.Initializer.init(application);
        mFragmentGraph = FragmentComponent.Initializer.init(application);
        mComponentGraph = AppComponent.Initializer.init(application);
    }

    @NonNull
    public static ActivityGraph activityGraph(Context context) {
        return GithubApplication.graphs(context).mActivityGraph;
    }

    @NonNull
    public static FragmentGraph fragmentGraph(Context context) {
        return GithubApplication.graphs(context).mFragmentGraph;
    }

    @NonNull
    public static ComponentGraph appGraph(Context context) {
        return GithubApplication.graphs(context).mComponentGraph;
    }

}
