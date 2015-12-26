package ru.arturvasilov.sqlite.core;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;

import ru.arturvasilov.sqlite.SQLiteUtils;

/**
 * @author Artur Vasilov
 */
public class SQLiteConfig {

    private static final String PREFS_NAME = "sqlite_config_prefs";

    private static final String DATABASE_NAME_KEY = "database_name";
    private static final String URI_KEY = "uri_name";

    private final Context mContext;

    private String mDatabaseName;
    private String mUri;

    public SQLiteConfig(Context context) {
        mContext = context;

        SharedPreferences prefs = getPrefs();
        mDatabaseName = prefs.getString(DATABASE_NAME_KEY, SQLiteUtils.defaultDatabaseName());
        mUri = prefs.getString(URI_KEY, SQLiteUtils.defaultUri());
    }

    @NonNull
    public String getDatabaseName() {
        return mDatabaseName;
    }

    public void setDatabaseName(@NonNull String databaseName) {
        mDatabaseName = databaseName;
    }

    @NonNull
    public String getUri() {
        return mUri;
    }

    public void setUri(@NonNull String uri) {
        mUri = uri;
    }

    @NonNull
    private SharedPreferences getPrefs() {
        return mContext.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
    }
}
