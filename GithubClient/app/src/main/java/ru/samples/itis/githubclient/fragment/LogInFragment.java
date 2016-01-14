package ru.samples.itis.githubclient.fragment;

import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import javax.inject.Inject;

import ru.samples.itis.githubclient.R;
import ru.samples.itis.githubclient.activity.auth.LogInActivity;
import ru.samples.itis.githubclient.api.GithubService;
import ru.samples.itis.githubclient.content.auth.Authorization;
import ru.samples.itis.githubclient.content.auth.AuthorizationUtils;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * @author Artur Vasilov
 */
public class LogInFragment extends BaseFragment implements View.OnClickListener {

    private EditText mLoginEdit;
    private EditText mPasswordEdit;
    private Button mLogInButton;

    @Inject
    GithubService mService;

    @NonNull
    public static LogInFragment getInstance() {
        return new LogInFragment();
    }

    @LayoutRes
    @Override
    protected int getLayoutId() {
        return R.layout.log_in_fragment;
    }

    @Override
    protected void attachViews(View root) {
        mLoginEdit = (EditText) root.findViewById(R.id.loginEdit);
        mPasswordEdit = (EditText) root.findViewById(R.id.passwordEdit);
        mLogInButton = (Button) root.findViewById(R.id.logInButton);

        graph().injectLogInFragment(this);
    }

    @Override
    protected void attachListeners() {
        mLogInButton.setOnClickListener(this);
    }

    @Override
    protected void detachListeners() {
        mLogInButton.setOnClickListener(null);
    }

    @Override
    public void onClick(View v) {
        final String login = mLoginEdit.getText().toString();
        final String password = mPasswordEdit.getText().toString();
        String authorization = AuthorizationUtils.createAuthorizationString(login, password);
        mService.authorize(authorization, AuthorizationUtils.createAuthorizationParam())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<Authorization>() {
                    @Override
                    public void onCompleted() {
                    }

                    @Override
                    public void onError(Throwable e) {
                    }

                    @Override
                    public void onNext(Authorization authorization) {
                        String token = authorization.getToken();
                        ((LogInActivity) getActivity()).onLogIn(login, password, token);
                    }
                });
    }
}
