package ru.samples.itis.githubclient.content.auth;

import android.accounts.Account;

import ru.samples.itis.githubclient.BuildConfig;

/**
 * @author Artur Vasilov
 */
public class GithubAccount extends Account {

    public static final Creator<Account> CREATOR = Account.CREATOR;

    public GithubAccount(String name) {
        super(name, BuildConfig.ACCOUNT_TYPE);
    }
}
