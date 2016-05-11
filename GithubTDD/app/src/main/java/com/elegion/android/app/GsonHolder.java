package com.elegion.android.app;

import android.support.annotation.NonNull;

import com.google.gson.Gson;

/**
 * @author Artur Vasilov
 */
public final class GsonHolder {

    private GsonHolder() {
    }

    private static Gson sGson;

    @NonNull
    public static Gson getGson() {
        Gson gson = sGson;
        if (gson == null) {
            synchronized (GsonHolder.class) {
                gson = sGson;
                if (gson == null) {
                    gson = sGson = new Gson();
                }
            }
        }
        return gson;
    }
}
