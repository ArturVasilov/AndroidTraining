package com.elegion.android.content;

import android.support.annotation.NonNull;

import com.google.gson.annotations.SerializedName;

import droidkit.annotation.SQLiteColumn;
import droidkit.annotation.SQLiteObject;
import droidkit.annotation.SQLitePk;

/**
 * @author Artur Vasilov
 */
@SQLiteObject(value = "repositories", uniqueOn = {Repository.NAME_COLUMN})
public class Repository {

    public static final String NAME_COLUMN = "name";
    public static final String DESCRIPTION_COLUMN = "description";
    public static final String LANGUAGE_COLUMN = "language";
    public static final String STARS_COLUMN = "stars";
    public static final String FORKS_COLUMN = "forks";
    public static final String WATCHERS_COLUMN = "watchers";

    @SQLitePk
    private long mId;

    @SerializedName("name")
    @SQLiteColumn(NAME_COLUMN)
    private String mName;

    @SerializedName("description")
    @SQLiteColumn(DESCRIPTION_COLUMN)
    private String mDescription;

    @SerializedName("language")
    @SQLiteColumn(LANGUAGE_COLUMN)
    private String mLanguage;

    @SerializedName("stargazers_count")
    @SQLiteColumn(STARS_COLUMN)
    private int mStarsCount;

    @SerializedName("forks_count")
    @SQLiteColumn(FORKS_COLUMN)
    private int mForksCount;

    @SerializedName("watchers_count")
    @SQLiteColumn(WATCHERS_COLUMN)
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

}
