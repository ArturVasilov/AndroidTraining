package ru.samples.itis.githubclient.content;

import android.support.annotation.NonNull;

import ru.arturvasilov.sqlite.core.SQLiteConfig;
import ru.arturvasilov.sqlite.core.SQLiteContentProvider;
import ru.arturvasilov.sqlite.core.Schema;
import ru.samples.itis.githubclient.content.tables.RepositoryTable;

/**
 * @author Artur Vasilov
 */
public class SQLiteProvider extends SQLiteContentProvider {

    private static final String DATABASE_NAME = "ru.arturvasilov.training.githubclient.database";
    private static final String CONTENT_AUTHORITY = "ru.arturvasilov.training.githubclient";

    @Override
    protected void prepareConfig(@NonNull SQLiteConfig config) {
        config.setDatabaseName(DATABASE_NAME);
        config.setUri(CONTENT_AUTHORITY);
    }

    @Override
    protected void prepareSchema(@NonNull Schema schema) {
        schema.register(RepositoryTable.TABLE);
    }
}
