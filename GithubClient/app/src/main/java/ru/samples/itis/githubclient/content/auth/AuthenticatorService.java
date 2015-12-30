package ru.samples.itis.githubclient.content.auth;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

/**
 * @author Artur Vasilov
 */
public class AuthenticatorService extends Service {

    private GithubAuthenticator mAuthenticator;

    @Override
    public void onCreate() {
        super.onCreate();
        mAuthenticator = new GithubAuthenticator(getApplicationContext());
    }

    @Override
    public IBinder onBind(Intent intent) {
        return mAuthenticator.getIBinder();
    }
}
