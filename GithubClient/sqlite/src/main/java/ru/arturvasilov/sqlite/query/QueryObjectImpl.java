package ru.arturvasilov.sqlite.query;

import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import ru.arturvasilov.sqlite.SQLiteUtils;

/**
 * @author Artur Vasilov
 */
public class QueryObjectImpl<T> implements QueryObject<T> {

    private final Context mContext;
    private final Uri mUri;
    private final Class<T> mClass;

    private String mQuery;
    private String[] mQueryArgs;

    public QueryObjectImpl(Context context, @NonNull Uri uri, Class<T> clazz) {
        mContext = context;
        mUri = uri;
        mClass = clazz;
    }

    @NonNull
    @Override
    public QueryObject<T> where(@Nullable String query) {
        mQuery = query;
        return this;
    }

    @NonNull
    @Override
    public QueryObject<T> whereArgs(@Nullable String[] args) {
        mQueryArgs = args;
        return this;
    }

    @Nullable
    @Override
    public T execute() {
        Cursor cursor = mContext.getContentResolver()
                .query(mUri, null, mQuery, mQueryArgs, null);
        try {
            if (SQLiteUtils.isEmptyCursor(cursor)) {
                return null;
            }
            return SQLiteUtils.fromCursor(cursor, mClass);
        } finally {
            SQLiteUtils.safeCloseCursor(cursor);
        }
    }
}
