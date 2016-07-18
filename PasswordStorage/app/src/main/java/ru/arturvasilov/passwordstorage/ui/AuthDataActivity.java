package ru.arturvasilov.passwordstorage.ui;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import ru.arturvasilov.passwordstorage.data.AuthData;

/**
 * @author Artur Vasilov
 */
public class AuthDataActivity extends AppCompatActivity {

    private static final String AUTH_DATA_KEY = "auth_data_key";

    //TODO : add fingerprinting

    private AuthData mAuthData;

    public static void start(@NonNull Activity activity, @NonNull AuthData authData) {
        Intent intent = new Intent(activity, AuthDataActivity.class);
        intent.putExtra(AUTH_DATA_KEY, authData);
        activity.startActivity(intent);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
}
