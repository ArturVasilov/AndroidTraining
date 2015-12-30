package ru.samples.itis.githubclient.content.auth;

import android.accounts.AbstractAccountAuthenticator;
import android.accounts.Account;
import android.accounts.AccountAuthenticatorResponse;
import android.accounts.AccountManager;
import android.accounts.NetworkErrorException;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;

import javax.inject.Inject;

import ru.samples.itis.githubclient.GithubApplication;
import ru.samples.itis.githubclient.activity.LogInActivity;
import ru.samples.itis.githubclient.api.GithubService;
import ru.samples.itis.githubclient.content.Authorization;
import rx.Subscriber;

/**
 * @author Artur Vasilov
 */
public class GithubAuthenticator extends AbstractAccountAuthenticator {

    @Inject
    GithubService mService;

    private Context mContext;

    public GithubAuthenticator(Context context) {
        super(context);
        mContext = context;
        GithubApplication.injector(mContext).injectAuthenticator(this);
    }

    @Override
    public Bundle editProperties(AccountAuthenticatorResponse response, String accountType) {
        return null;
    }

    @Override
    public Bundle addAccount(AccountAuthenticatorResponse response, String accountType, String authTokenType, String[] requiredFeatures, Bundle options) throws NetworkErrorException {
        final Intent intent = new Intent(mContext, LogInActivity.class);
        final Bundle bundle = new Bundle();
        if (options != null) {
            bundle.putAll(options);
        }
        bundle.putParcelable(AccountManager.KEY_INTENT, intent);
        return bundle;
    }

    @Override
    public Bundle confirmCredentials(AccountAuthenticatorResponse response, Account account, Bundle options) throws NetworkErrorException {
        return null;
    }

    @Override
    public Bundle getAuthToken(AccountAuthenticatorResponse response, Account account, String authTokenType, Bundle options) throws NetworkErrorException {
        AccountManager accountManager = AccountManager.get(mContext);
        String authToken = accountManager.peekAuthToken(account, authTokenType);

        final Bundle bundle = new Bundle();

        if (TextUtils.isEmpty(authToken)) {
            String password = accountManager.getPassword(account);
            if (!TextUtils.isEmpty(password)) {
                String authorization = AuthorizationUtils.createAuthorizationString(account.name, password);
                mService.authorize(authorization, AuthorizationUtils.createAuthorizationParam())
                        .subscribe(new Subscriber<Authorization>() {
                            @Override
                            public void onCompleted() {
                            }

                            @Override
                            public void onError(Throwable e) {
                                Intent intent = new Intent(mContext, LogInActivity.class);
                                Bundle bundle = new Bundle();
                                bundle.putParcelable(AccountManager.KEY_INTENT, intent);
                            }

                            @Override
                            public void onNext(Authorization authorization) {
                                bundle.putString(AccountManager.KEY_ACCOUNT_NAME, account.name);
                                bundle.putString(AccountManager.KEY_ACCOUNT_TYPE, account.type);
                                bundle.putString(AccountManager.KEY_AUTHTOKEN, authorization.getToken());

                                Intent intent = new Intent(mContext, LogInActivity.class);
                                bundle.putParcelable(AccountManager.KEY_INTENT, intent);
                            }
                        });
            }
        } else {
            bundle.putString(AccountManager.KEY_AUTHTOKEN, authToken);
        }
        return bundle;
    }

    @Override
    public String getAuthTokenLabel(String authTokenType) {
        return null;
    }

    @Override
    public Bundle updateCredentials(AccountAuthenticatorResponse response, Account account, String authTokenType, Bundle options) throws NetworkErrorException {
        return null;
    }

    @Override
    public Bundle hasFeatures(AccountAuthenticatorResponse response, Account account, String[] features) throws NetworkErrorException {
        return null;
    }
}
