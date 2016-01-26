package com.elegion.android.activity;

import android.app.Fragment;
import android.support.annotation.NonNull;

import com.elegion.android.base.BaseActivity;
import com.elegion.android.fragment.LogInFragment;

/**
 * @author Artur Vasilov
 */
public class LogInActivity extends BaseActivity {

    @NonNull
    @Override
    public Fragment createFragment() {
        return new LogInFragment();
    }
}
