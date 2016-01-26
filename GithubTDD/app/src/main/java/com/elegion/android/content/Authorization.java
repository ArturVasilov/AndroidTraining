package com.elegion.android.content;

import android.support.annotation.NonNull;

import com.google.gson.annotations.SerializedName;

public class Authorization {

    @SerializedName("id")
    private int mId;

    @SerializedName("token")
    private String mToken;

    public int getId() {
        return mId;
    }

    public void setId(int id) {
        mId = id;
    }

    @NonNull
    public String getToken() {
        return mToken;
    }

    public void setToken(@NonNull String token) {
        mToken = token;
    }
}
