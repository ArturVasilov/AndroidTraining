package ru.arturvasilov.passwordstorage.data;

import android.content.Context;
import android.support.annotation.NonNull;

import com.google.gson.Gson;

import java.util.List;

/**
 * @author Artur Vasilov
 */
public final class AuthStorage {

    private static final String SP_FILE_NAME = "cajpBJ9nxMX:XSOmhLcacy9md1p9dnmgacpvOANhgolbdqfca";
    private static final String SP_AUTH_KEY = "Pmtp9xnaxAObknnAm90ncNOCtgkc$2k71";

    private AuthStorage() {
    }

    @NonNull
    public static List<AuthData> getAuthData(@NonNull Context context, @NonNull String masterPassword) {
        String result = decrypt(
                context.getSharedPreferences(SP_FILE_NAME, Context.MODE_PRIVATE).getString(SP_AUTH_KEY, "{}"),
                masterPassword);

        return new Gson().fromJson(result, AuthDataCollection.class).getAuthData();
    }

    public static void saveAuthData(@NonNull Context context, @NonNull AuthData authData, @NonNull String masterPassword) {
        List<AuthData> authDataList = getAuthData(context, masterPassword);
        authDataList.add(authData);

        String result = encrypt(new Gson().toJson(new AuthDataCollection(authDataList)), masterPassword);
        context.getSharedPreferences(SP_FILE_NAME, Context.MODE_PRIVATE)
                .edit()
                .putString(SP_AUTH_KEY, result)
                .apply();
    }

    @NonNull
    private static String encrypt(@NonNull String text, @NonNull String key) {
        return text;
    }

    @NonNull
    private static String decrypt(@NonNull String text, @NonNull String key) {
        return text;
    }

}
