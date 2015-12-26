package ru.arturvasilov.sqlite.query;

import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

import ru.arturvasilov.sqlite.SQLiteUtils;

/**
 * @author Artur Vasilov
 */
public class QueryListImpl<T> implements QueryList<T> {

    private final Context mContext;
    private final Uri mUri;
    private final Class<T> mClass;

    private String mQuery;
    private String[] mQueryArgs;

    public QueryListImpl(Context context, @NonNull Uri uri, Class<T> clazz) {
        mContext = context;
        mUri = uri;
        mClass = clazz;
    }

    @NonNull
    @Override
    public QueryList<T> where(@Nullable String query) {
        mQuery = query;
        return this;
    }

    @NonNull
    @Override
    public QueryList<T> whereArgs(@Nullable String[] args) {
        mQueryArgs = args;
        return this;
    }

    @NonNull
    @Override
    public List<T> execute() {
        List<T> list = new ArrayList<>();
        Cursor cursor = mContext.getContentResolver()
                .query(mUri, null, mQuery, mQueryArgs, null);
        try {
            if (SQLiteUtils.isEmptyCursor(cursor)) {
                return list;
            }
            do {
                T t = SQLiteUtils.fromCursor(cursor, mClass);
                list.add(t);
            } while (cursor.moveToNext());
            return list;
        } finally {
            SQLiteUtils.safeCloseCursor(cursor);
        }
    }


}
