package ru.samples.itis.githubclient.activity.auth;

import android.accounts.Account;
import android.accounts.AccountManager;
import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.NonNull;

import ru.samples.itis.githubclient.content.auth.GithubAccount;
import ru.samples.itis.githubclient.fragment.LogInFragment;

public class LogInActivity extends BaseAuthActivity {

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

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @NonNull
    @Override
    protected Fragment getFragment() {
        return LogInFragment.getInstance();
    }

    public void finishLogin() {
        AccountManager accountManager = AccountManager.get(this);
        Account account = GithubAccount.getUserAccount(this);
        if (account != null) {
            final Bundle result = new Bundle();
            result.putString(AccountManager.KEY_ACCOUNT_NAME, account.name);
            result.putString(AccountManager.KEY_ACCOUNT_TYPE, account.type);
            result.putString(AccountManager.KEY_AUTHTOKEN, accountManager.peekAuthToken(account, account.type));

            setAccountAuthenticatorResult(result);
            setResult(RESULT_OK);
        }
        finish();
    }
}
