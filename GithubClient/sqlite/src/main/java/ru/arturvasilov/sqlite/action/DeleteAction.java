package ru.arturvasilov.sqlite.action;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

/**
 * @author Artur Vasilov
 */
public interface DeleteAction {

    @NonNull
    DeleteAction where(@Nullable String where);

    @NonNull
    DeleteAction whereArgs(@Nullable String[] args);

    int execute();

}
