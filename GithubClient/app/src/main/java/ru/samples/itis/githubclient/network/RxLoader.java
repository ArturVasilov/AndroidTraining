package ru.samples.itis.githubclient.network;

import android.content.Context;
import android.content.Loader;
import android.support.annotation.NonNull;

import ru.samples.itis.githubclient.GithubApplication;

/**
 * @author Artur Vasilov
 */
public class RxLoader extends Loader<Response> {

    private final Executor mExecutor;

    public RxLoader(Context context, @NonNull Executor executor) {
        super(context);
        mExecutor = executor;
    }

    @Override
    protected void onStartLoading() {
        super.onStartLoading();
        GithubApplication.get(getContext()).execute(mExecutor);
    }

    @Override
    protected void onStopLoading() {
        super.onStopLoading();
    }
}
