package ru.samples.itis.githubclient.fragment;

import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import ru.arturvasilov.sqlite.SQLite;
import ru.samples.itis.githubclient.R;
import ru.samples.itis.githubclient.api.RepositoriesRequest;
import ru.samples.itis.githubclient.api.RequestsService;
import ru.samples.itis.githubclient.content.tables.RepositoryTable;
import ru.samples.itis.githubclient.widget.RepositoriesAdapter;

/**
 * @author Artur Vasilov
 */
public class RepositoriesFragment extends BaseFragment {

    private RepositoriesAdapter mAdapter;

    @NonNull
    public static RepositoriesFragment getInstance() {
        return new RepositoriesFragment();
    }

    @Override
    protected void attachViews(View root) {
        mAdapter = new RepositoriesAdapter();

        RecyclerView recyclerView = (RecyclerView) root;
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(mAdapter);
    }

    @Override
    protected void attachListeners() {
        SQLite.get().registerObserver(RepositoryTable.TABLE, mAdapter);
    }

    @Override
    protected void detachListeners() {
        SQLite.get().unregisterObserver(mAdapter);
    }

    @Override
    protected void onViewsAttached() {
        RequestsService.executeRequest(getActivity(), new RepositoriesRequest());
    }

    @LayoutRes
    @Override
    protected int getLayoutId() {
        return R.layout.repositories_fragment;
    }
}
