package ru.samples.itis.githubclient.api.body;

import android.support.annotation.NonNull;

import com.google.gson.annotations.SerializedName;

import ru.samples.itis.githubclient.BuildConfig;

/**
 * @author Artur Vasilov
 */
public class AuthParams {

    @SerializedName("client_id")
    private String mClientId;

    @SerializedName("client_secret")
    private String mClientSecret;

    @SerializedName("code")
    private String mAccessCode;

    public AuthParams(@NonNull String clientId, @NonNull String clientSecret,
                      @NonNull String accessCode) {
        mClientId = clientId;
        mClientSecret = clientSecret;
        mAccessCode = accessCode;
    }

    @NonNull
    public static AuthParams create(@NonNull String accessCode) {
        return new AuthParams(BuildConfig.CLIENT_ID, BuildConfig.CLIENT_SECRET, accessCode);
    }
}
