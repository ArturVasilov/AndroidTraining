package ru.samples.itis.githubclient.api;

import android.support.annotation.NonNull;

import java.util.List;

import ru.arturvasilov.sqlite.SQLite;
import ru.arturvasilov.sqlite.table.Table;
import ru.samples.itis.githubclient.content.Repository;
import ru.samples.itis.githubclient.content.tables.RepositoryTable;
import rx.schedulers.Schedulers;

/**
 * @author Artur Vasilov
 */
public class RepositoriesRequest implements Request {

    @Override
    public void process(@NonNull GithubService service) {
        service.repositories()
                .subscribeOn(Schedulers.io())
                .observeOn(Schedulers.io())
                .subscribe(new BaseSubscriber<List<Repository>>() {
                    @Override
                    public void onNext(List<Repository> repositories) {
                        Table<Repository> table = RepositoryTable.TABLE;

                        SQLite.get()
                                .delete(table)
                                .execute();

                        SQLite.get()
                                .insert(table)
                                .insert(repositories);

                        SQLite.get().notifyTableChanged(table);
                    }
                });
    }
}
