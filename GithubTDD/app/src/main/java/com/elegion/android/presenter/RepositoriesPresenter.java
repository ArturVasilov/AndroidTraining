package com.elegion.android.presenter;

import android.app.LoaderManager;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.VisibleForTesting;

import com.elegion.android.R;
import com.elegion.android.base.BasePresenter;
import com.elegion.android.content.Repository;
import com.elegion.android.rx.RxLoader;
import com.elegion.android.rx.ShowLoadingFinallyDo;
import com.elegion.android.rx.ShowLoadingOnSubscribe;
import com.elegion.android.view.RepositoriesView;

import java.util.List;

import droidkit.sqlite.SQLite;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

/**
 * @author Artur Vasilov
 */
public class RepositoriesPresenter extends BasePresenter<RepositoriesView> {

    public RepositoriesPresenter(@NonNull Context context, @NonNull LoaderManager manager, @NonNull RepositoriesView view) {
        super(context, manager, view);
    }

    public void init() {
        RxLoader.create(getContext(), getLoaderManager(), R.id.local_repositories_loader,
                SQLite.where(Repository.class).asObservable())
                .restart(new Action1<List<Repository>>() {
                    @Override
                    public void call(List<Repository> repositories) {
                        showRepositories(repositories);
                    }
                });

        loadRepositories();
    }

    @VisibleForTesting
    void showRepositories(@NonNull List<Repository> repositories) {
        if (repositories.isEmpty()) {
            getView().showEmpty();
        } else {
            getView().showRepositories(repositories);
        }
    }

    private void loadRepositories() {
        RxLoader.create(getContext(), getLoaderManager(), R.id.repositories_loader,
                getService().repositories()
                        .doOnNext(new Action1<List<Repository>>() {
                            @Override
                            public void call(List<Repository> repositories) {
                                SQLite.beginTransaction();
                                SQLite.removeAll(Repository.class);
                                SQLite.saveAll(repositories);
                                SQLite.endTransaction();
                            }
                        })
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .doOnSubscribe(new ShowLoadingOnSubscribe(getView()))
                        .finallyDo(new ShowLoadingFinallyDo(getView()))
        ).restart(new Action1<List<Repository>>() {
            @Override
            public void call(List<Repository> repositories) {
                showRepositories(repositories);
            }
        });

    }
}
