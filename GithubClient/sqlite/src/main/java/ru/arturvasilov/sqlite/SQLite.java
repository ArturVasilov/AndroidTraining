package ru.arturvasilov.sqlite;

import android.content.Context;
import android.support.annotation.NonNull;

import ru.arturvasilov.sqlite.action.DeleteAction;
import ru.arturvasilov.sqlite.action.DeleteActionImpl;
import ru.arturvasilov.sqlite.action.InsertAction;
import ru.arturvasilov.sqlite.action.InsertActionImpl;
import ru.arturvasilov.sqlite.action.UpdateAction;
import ru.arturvasilov.sqlite.action.UpdateActionImpl;
import ru.arturvasilov.sqlite.query.Query;
import ru.arturvasilov.sqlite.query.QueryImpl;
import ru.arturvasilov.sqlite.table.Table;

/**
 * @author Artur Vasilov
 */
public class SQLite {

    private final Context mContext;

    private static SQLite sSQLite;

    private SQLite(Context context) {
        mContext = context;
    }

    @NonNull
    public static SQLite get(Context context) {
        SQLite sqLite = sSQLite;
        if (sqLite == null) {
            synchronized (SQLite.class) {
                sqLite = sSQLite;
                if (sqLite == null) {
                    sqLite = sSQLite = new SQLite(context);
                }
            }
        }
        return sqLite;
    }

    @NonNull
    public Query query(@NonNull Table table) {
        return new QueryImpl(mContext, table.getUri());
    }

    @NonNull
    public <T> InsertAction<T> insert(@NonNull Table<T> table) {
        return new InsertActionImpl<>(table, mContext);
    }

    @NonNull
    public DeleteAction delete(@NonNull Table table) {
        return new DeleteActionImpl(mContext, table.getUri());
    }

    @NonNull
    public <T> UpdateAction<T> update(@NonNull Table<T> table) {
        return new UpdateActionImpl<>(table, mContext);
    }
}
