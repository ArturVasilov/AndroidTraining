package ru.samples.itis.githubclient.api;

import android.content.Context;
import android.support.annotation.NonNull;

import java.util.List;

import ru.arturvasilov.sqlite.SQLite;
import ru.arturvasilov.sqlite.table.Table;
import ru.samples.itis.githubclient.content.Commit;
import ru.samples.itis.githubclient.content.CommitsResponse;
import ru.samples.itis.githubclient.content.auth.GithubAccount;
import ru.samples.itis.githubclient.content.tables.CommitsTable;
import rx.Observable;
import rx.schedulers.Schedulers;

/**
 * @author Artur Vasilov
 */
public class CommitsRequest implements Request {

    private final String mRepoName;

    public CommitsRequest(@NonNull String repoName) {
        mRepoName = repoName;
    }

    @Override
    public void process(Context context, @NonNull GithubService service) {
        String userName = GithubAccount.getLogin(context);
        service.commits(userName, mRepoName)
                .flatMap(Observable::from)
                .map(CommitsResponse::getCommit)
                .map(commit -> {
                    commit.setRepoName(mRepoName);
                    return commit;
                })
                .toList()
                .subscribeOn(Schedulers.io())
                .observeOn(Schedulers.io())
                .subscribe(new BaseSubscriber<List<Commit>>() {
                    @Override
                    public void onNext(@NonNull List<Commit> commits) {
                        Table<Commit> table = CommitsTable.TABLE;

                        SQLite.get()
                                .delete(table)
                                .where(CommitsTable.REPOSITORY_NAME + "=?")
                                .whereArgs(new String[]{mRepoName})
                                .execute();

                        SQLite.get()
                                .insert(table)
                                .insert(commits);

                        SQLite.get().notifyTableChanged(table);
                    }
                });
    }
}
