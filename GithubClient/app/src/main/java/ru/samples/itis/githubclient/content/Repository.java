package ru.samples.itis.githubclient.content;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.annotation.NonNull;

import com.google.gson.annotations.SerializedName;

import ru.arturvasilov.sqlite.table.BaseTable;
import ru.arturvasilov.sqlite.table.Table;
import ru.arturvasilov.sqlite.table.TableBuilder;

/**
 * @author Artur Vasilov
 */
public class Repository {

    public static final Table<Repository> TABLE = new RepositoryTable();

    @SerializedName("name")
    private String mName;

    @SerializedName("description")
    private String mDescription;

    @SerializedName("language")
    private String mLanguage;

    @SerializedName("stargazers_count")
    private int mStarsCount;

    @SerializedName("forks_count")
    private int mForksCount;

    @SerializedName("watchers_count")
    private int mWatchersCount;

    public Repository() {
    }

    @NonNull
    public String getName() {
        return mName;
    }

    public void setName(String name) {
        mName = name == null ? "" : name;
    }

    @NonNull
    public String getDescription() {
        return mDescription;
    }

    public void setDescription(String description) {
        mDescription = description == null ? "" : description;
    }

    @NonNull
    public String getLanguage() {
        return mLanguage;
    }

    public void setLanguage(String language) {
        mLanguage = language == null ? "" : language;
    }

    public int getStarsCount() {
        return mStarsCount;
    }

    public void setStarsCount(int starsCount) {
        mStarsCount = starsCount < 0 ? 0 : starsCount;
    }

    public int getForksCount() {
        return mForksCount;
    }

    public void setForksCount(int forksCount) {
        mForksCount = forksCount < 0 ? 0 : forksCount;
    }

    public int getWatchersCount() {
        return mWatchersCount;
    }

    public void setWatchersCount(int watchersCount) {
        mWatchersCount = watchersCount < 0 ? 0 : watchersCount;
    }

    private static class RepositoryTable extends BaseTable<Repository> {

        private static final String NAME_COLUMN = "name";
        private static final String DESCRIPTION_COLUMN = "description";
        private static final String LANGUAGE_COLUMN = "language";
        private static final String STARS_COLUMN = "stars";
        private static final String FORKS_COLUMN = "forks";
        private static final String WATCHERS_COLUMN = "watchers";

        @Override
        public void onCreate(@NonNull SQLiteDatabase database) {
            TableBuilder.create(this)
                    .stringColumn(NAME_COLUMN)
                    .stringColumn(DESCRIPTION_COLUMN)
                    .stringColumn(LANGUAGE_COLUMN)
                    .intColumn(STARS_COLUMN)
                    .intColumn(FORKS_COLUMN)
                    .intColumn(WATCHERS_COLUMN)
                    .execute(database);
        }

        @Override
        public int getLastUpgradeVersion() {
            return 1;
        }

        @NonNull
        @Override
        public ContentValues toValues(@NonNull Repository object) {
            ContentValues values = new ContentValues();
            values.put(NAME_COLUMN, object.getName());
            values.put(DESCRIPTION_COLUMN, object.getDescription());
            values.put(LANGUAGE_COLUMN, object.getLanguage());
            values.put(STARS_COLUMN, object.getStarsCount());
            values.put(FORKS_COLUMN, object.getForksCount());
            values.put(WATCHERS_COLUMN, object.getWatchersCount());
            return values;
        }

        @NonNull
        @Override
        public Repository fromCursor(@NonNull Cursor cursor) {
            Repository repository = new Repository();
            repository.setName(cursor.getString(cursor.getColumnIndex(NAME_COLUMN)));
            repository.setDescription(cursor.getString(cursor.getColumnIndex(DESCRIPTION_COLUMN)));
            repository.setLanguage(cursor.getString(cursor.getColumnIndex(LANGUAGE_COLUMN)));
            repository.setStarsCount(cursor.getInt(cursor.getColumnIndex(STARS_COLUMN)));
            repository.setForksCount(cursor.getInt(cursor.getColumnIndex(FORKS_COLUMN)));
            repository.setWatchersCount(cursor.getInt(cursor.getColumnIndex(WATCHERS_COLUMN)));
            return repository;
        }
    }
}
