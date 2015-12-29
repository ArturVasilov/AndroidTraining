package ru.samples.itis.githubclient.activity;

import android.app.LoaderManager;
import android.content.Context;
import android.content.Loader;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import ru.samples.itis.githubclient.GithubApplication;
import ru.samples.itis.githubclient.R;
import ru.samples.itis.githubclient.network.Executor;
import ru.samples.itis.githubclient.network.Response;
import ru.samples.itis.githubclient.network.RxLoader;

public class LogInActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        GithubApplication.injector(this).injectLogInActivity(this);
    }

    @Override
    protected void onStart() {
        super.onStart();

    }

    private class Callback implements LoaderManager.LoaderCallbacks<Response> {

        private final Context mContext;
        private final Executor mExecutor;

        public Callback(Context context, @NonNull Executor executor) {
            mContext = context;
            mExecutor = executor;
        }

        @Override
        public Loader<Response> onCreateLoader(int id, Bundle args) {
            return new RxLoader(mContext, mExecutor);
        }

        @Override
        public void onLoadFinished(Loader<Response> loader, Response data) {

        }

        @Override
        public void onLoaderReset(Loader<Response> loader) {

        }
    }
}
