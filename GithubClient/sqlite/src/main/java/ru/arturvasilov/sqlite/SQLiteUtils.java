package ru.arturvasilov.sqlite;

import android.database.Cursor;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import java.lang.reflect.Constructor;

/**
 * @author Artur Vasilov
 */
public class SQLiteUtils {

    public static boolean isEmptyCursor(@Nullable Cursor cursor) {
        if (cursor == null) {
            return true;
        }

        //noinspection SimplifiableIfStatement
        if (cursor.isClosed()) {
            return true;
        }

        return !cursor.moveToFirst();
    }

    public static void safeCloseCursor(@Nullable Cursor cursor) {
        if (cursor == null || cursor.isClosed()) {
            return;
        }

        try {
            cursor.close();
        } catch (Exception ignored) {
        }
    }

    @Nullable
    public static <T> T fromCursor(@NonNull Cursor cursor, Class<T> clazz) {
        try {
            Constructor<T> constructor = clazz.getConstructor(Cursor.class);
            return constructor.newInstance(cursor);
        } catch (Exception e) {
            return null;
        }
    }

    @NonNull
    public static String defaultDatabaseName() {
        return "ru.sqlite.database.database";
    }

    @NonNull
    public static String defaultUri() {
        return "ru.sqlite.database";
    }
}
