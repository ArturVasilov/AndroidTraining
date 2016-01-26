package com.elegion.android.fragment;

import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.widget.TextView;

import com.elegion.android.R;
import com.elegion.android.adapter.DividerItemDecoration;
import com.elegion.android.adapter.RepositoriesAdapter;
import com.elegion.android.base.BaseFragment;
import com.elegion.android.content.Repository;
import com.elegion.android.presenter.RepositoriesPresenter;
import com.elegion.android.view.RepositoriesView;
import com.elegion.android.widget.EmptyRecyclerView;

import java.util.List;

import droidkit.annotation.InjectView;

/**
 * @author Artur Vasilov
 */
public class RepositoriesFragment extends BaseFragment implements RepositoriesView {

    @InjectView(R.id.recyclerView)
    private EmptyRecyclerView mRecyclerView;

    @InjectView(R.id.empty)
    private TextView mEmptyView;

    private RepositoriesAdapter mAdapter;

    private RepositoriesPresenter mPresenter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPresenter = new RepositoriesPresenter(getActivity(), getLoaderManager(), this);
        mAdapter = new RepositoriesAdapter();
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRecyclerView.addItemDecoration(new DividerItemDecoration(getActivity()));
        mRecyclerView.setEmptyView(mEmptyView);
        mAdapter.attachToRecyclerView(mRecyclerView);

        mPresenter.init();
    }

    @Override
    public void showRepositories(@NonNull List<Repository> repositories) {
        mAdapter.setNewValues(repositories);
    }

    @Override
    public void showEmpty() {
        mRecyclerView.checkIfEmpty();
    }

    @LayoutRes
    @Override
    protected int getLayoutId() {
        return R.layout.repositories_fragment;
    }
}
