package ru.samples.itis.githubclient.fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import ru.samples.itis.githubclient.GithubApplication;
import ru.samples.itis.githubclient.di.Graphs;
import ru.samples.itis.githubclient.di.graph.FragmentGraph;

/**
 * @author Artur Vasilov
 */
public abstract class BaseFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(getLayoutId(), container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        attachViews(view);
        onViewsAttached();
    }

    @Override
    public void onResume() {
        super.onResume();
        attachListeners();
    }

    @Override
    public void onPause() {
        detachListeners();
        super.onPause();
    }

    protected void attachViews(View root) {
    }

    protected void onViewsAttached() {
    }

    protected void attachListeners() {
    }

    protected void detachListeners() {
    }

    @NonNull
    protected FragmentGraph graph() {
        return Graphs.fragmentGraph(getActivity());
    }

    @NonNull
    protected GithubApplication getApplication() {
        return GithubApplication.get(getActivity());
    }

    @LayoutRes
    protected abstract int getLayoutId();

}
