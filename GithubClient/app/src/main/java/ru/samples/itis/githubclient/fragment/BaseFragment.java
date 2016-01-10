package ru.samples.itis.githubclient.fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

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

    @NonNull
    protected FragmentGraph graph() {
        return Graphs.fragmentGraph(getActivity());
    }

    @LayoutRes
    protected abstract int getLayoutId();

}
