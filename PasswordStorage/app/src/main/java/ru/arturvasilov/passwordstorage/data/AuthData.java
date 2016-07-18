package ru.arturvasilov.passwordstorage.data;

import android.support.annotation.NonNull;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * @author Artur Vasilov
 */
public class AuthData implements Serializable {

    @SerializedName("description")
    private String mDescription;

    @SerializedName("login")
    private String mLogin;

    @SerializedName("email")
    private String mEmail;

    @SerializedName("password")
    private String mPassword;

    public AuthData() {
    }

    public AuthData(@NonNull String description, @NonNull String login,
                    @NonNull String email, @NonNull String password) {
        mDescription = description;
        mLogin = login;
        mEmail = email;
        mPassword = password;
    }

    @NonNull
    public String getDescription() {
        return mDescription;
    }

    @NonNull
    public String getLogin() {
        return mLogin;
    }

    @NonNull
    public String getEmail() {
        return mEmail;
    }

    @NonNull
    public String getPassword() {
        return mPassword;
    }
}
