package ru.samples.itis.githubclient.fragment;

import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.view.View;

import java.util.List;

import javax.inject.Inject;

import ru.arturvasilov.sqlite.SQLite;
import ru.samples.itis.githubclient.R;
import ru.samples.itis.githubclient.api.GithubService;
import ru.samples.itis.githubclient.content.Repository;
import ru.samples.itis.githubclient.content.tables.RepositoryTable;
import rx.Subscriber;
import rx.schedulers.Schedulers;

/**
 * @author Artur Vasilov
 */
public class RepositoriesFragment extends BaseFragment {

    @Inject
    GithubService mService;

    @NonNull
    public static RepositoriesFragment getInstance() {
        return new RepositoriesFragment();
    }

    @Override
    protected void attachViews(View root) {
        graph().injectRepositoriesFragment(this);
    }

    @Override
    protected void onViewsAttached() {
        mService.repositories()
                .subscribeOn(Schedulers.io())
                .subscribe(new Subscriber<List<Repository>>() {
                    @Override
                    public void onCompleted() {
                    }

                    @Override
                    public void onError(Throwable e) {
                    }

                    @Override
                    public void onNext(List<Repository> repositories) {
                        SQLite.get()
                                .delete(RepositoryTable.TABLE)
                                .execute();

                        SQLite.get()
                                .insert(RepositoryTable.TABLE)
                                .insert(repositories);
                    }
                });
    }

    @LayoutRes
    @Override
    protected int getLayoutId() {
        return R.layout.repositories_fragment;
    }
}
