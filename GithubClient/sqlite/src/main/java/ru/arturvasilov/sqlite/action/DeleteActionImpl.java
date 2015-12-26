package ru.arturvasilov.sqlite.action;

import android.content.Context;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

/**
 * @author Artur Vasilov
 */
public class DeleteActionImpl implements DeleteAction {

    private final Context mContext;
    private final Uri mUri;

    private String mWhere;
    private String[] mArgs;

    public DeleteActionImpl(Context context, @NonNull Uri uri) {
        mContext = context;
        mUri = uri;
    }

    @NonNull
    @Override
    public DeleteAction where(@Nullable String where) {
        mWhere = where;
        return this;
    }

    @NonNull
    @Override
    public DeleteAction whereArgs(@Nullable String[] args) {
        mArgs = args;
        return this;
    }

    @Override
    public int execute() {
        return mContext.getContentResolver().delete(mUri, mWhere, mArgs);
    }
}
