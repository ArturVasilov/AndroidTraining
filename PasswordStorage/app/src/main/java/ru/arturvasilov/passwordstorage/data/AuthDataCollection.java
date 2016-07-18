package ru.arturvasilov.passwordstorage.data;

import android.support.annotation.NonNull;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Artur Vasilov
 */
public class AuthDataCollection {

    @SerializedName("authItems")
    private List<AuthData> mAuthData;

    public AuthDataCollection() {
    }

    public AuthDataCollection(@NonNull List<AuthData> authData) {
        mAuthData = authData;
    }

    @NonNull
    public List<AuthData> getAuthData() {
        if (mAuthData == null) {
            mAuthData = new ArrayList<>();
        }
        return mAuthData;
    }
}
