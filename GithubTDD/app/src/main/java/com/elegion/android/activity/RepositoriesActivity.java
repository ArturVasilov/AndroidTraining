package com.elegion.android.activity;

import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;

import com.elegion.android.base.BaseActivity;
import com.elegion.android.fragment.RepositoriesFragment;

/**
 * @author Artur Vasilov
 */
public class RepositoriesActivity extends BaseActivity {

    @NonNull
    public static Intent makeIntent(@NonNull Context context) {
        return new Intent(context, RepositoriesActivity.class);
    }

    @NonNull
    @Override
    public Fragment createFragment() {
        return new RepositoriesFragment();
    }
}
