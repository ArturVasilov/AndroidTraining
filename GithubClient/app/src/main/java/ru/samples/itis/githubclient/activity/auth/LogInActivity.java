package ru.samples.itis.githubclient.activity.auth;

import android.accounts.Account;
import android.accounts.AccountManager;
import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.NonNull;

import ru.samples.itis.githubclient.activity.RepositoriesActivity;
import ru.samples.itis.githubclient.content.auth.GithubAccount;
import ru.samples.itis.githubclient.fragment.LogInFragment;

public class LogInActivity extends BaseAuthActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (GithubAccount.getUserAccount(this) != null) {
            RepositoriesActivity.start(this);
            finish();
        }
    }

    @NonNull
    @Override
    protected Fragment getFragment() {
        return LogInFragment.getInstance();
    }

    public void onLogIn(@NonNull String login, @NonNull String password, @NonNull String token) {
        Bundle result = new Bundle();
        if (GithubAccount.addAccount(this, login, password)) {
            AccountManager manager = AccountManager.get(this);
            Account account = GithubAccount.getUserAccount(this);
            if (account != null) {
                result.putString(AccountManager.KEY_ACCOUNT_NAME, account.name);
                result.putString(AccountManager.KEY_ACCOUNT_TYPE, account.type);
                result.putString(AccountManager.KEY_AUTHTOKEN, token);
                manager.setAuthToken(account, account.type, token);
            }
        }
        setAccountAuthenticatorResult(result);
        setResult(RESULT_OK);
        finish();
    }
}
