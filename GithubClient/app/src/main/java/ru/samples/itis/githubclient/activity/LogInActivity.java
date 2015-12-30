package ru.samples.itis.githubclient.activity;

import android.app.Fragment;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import ru.samples.itis.githubclient.GithubApplication;
import ru.samples.itis.githubclient.R;
import ru.samples.itis.githubclient.fragment.LogInFragment;

public class LogInActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        GithubApplication.injector(this).injectLogInActivity(this);

        Fragment fragment = new LogInFragment();
        getFragmentManager().beginTransaction()
                .replace(R.id.content_frame, fragment)
                .commit();
    }

    /*
        String authorization = AuthorizationUtils.createAuthorizationString("login", "password");
        mService.createNewAuthorization(authorization, AuthorizationUtils.createAuthorizationParam())
                .subscribeOn(Schedulers.io())
                .observeOn(Schedulers.io())
                .subscribe(authorization1 -> {
                    token = authorization1.getToken();
                    mService.repositories("token " + token).subscribe(new Subscriber<List<Repository>>() {
                        @Override
                        public void onCompleted() {
                            int b = 6;
                        }

                        @Override
                        public void onError(Throwable e) {
                            int b = 6;
                        }

                        @Override
                        public void onNext(List<Repository> repositories) {
                            int b = 6;
                        }
                    });
                });
     */
}
