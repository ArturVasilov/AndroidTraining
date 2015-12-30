package ru.samples.itis.githubclient.content.auth;

import android.accounts.Account;
import android.accounts.AccountManager;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import ru.samples.itis.githubclient.BuildConfig;

/**
 * @author Artur Vasilov
 */
public class GithubAccount extends Account {

    public static final Creator<Account> CREATOR = Account.CREATOR;

    private static GithubAccount sInstance;

    private String mAuthToken;

    public GithubAccount(@NonNull String name) {
        super(name, BuildConfig.ACCOUNT_TYPE);
    }

    @NonNull
    public static GithubAccount getInstance() {
        return sInstance;
    }

    public static void setup(Context context) {
        final Account account = getUserAccount(context);
        if (account != null) {
            AccountManager accountManager = AccountManager.get(context);
            String authToken = accountManager.peekAuthToken(account, BuildConfig.ACCOUNT_TYPE);
            sInstance = new GithubAccount(account.name);
            sInstance.mAuthToken = authToken;
        }
    }

    @NonNull
    public String getAuthToken() {
        return mAuthToken;
    }

    @Nullable
    private static Account getUserAccount(Context context) {
        AccountManager accountManager = AccountManager.get(context);
        Account[] accounts = accountManager.getAccountsByType(BuildConfig.ACCOUNT_TYPE);
        if (accounts == null || accounts.length < 1) {
            return null;
        }
        return accounts[0];
    }
}
