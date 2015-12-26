package ru.arturvasilov.sqlite.query;

import android.content.Context;
import android.net.Uri;
import android.support.annotation.NonNull;

/**
 * @author Artur Vasilov
 */
public class QueryImpl implements Query {

    private final Context mContext;
    private final Uri mUri;

    public QueryImpl(Context context, @NonNull Uri uri) {
        mContext = context;
        mUri = uri;
    }

    @Override
    public <T> QueryList<T> all(Class<T> clazz) {
        return new QueryListImpl<>(mContext, mUri, clazz);
    }

    @Override
    public <T> QueryObject<T> object(Class<T> clazz) {
        return new QueryObjectImpl<>(mContext, mUri, clazz);
    }
}
