package ru.samples.itis.githubclient.fragment;

import android.support.annotation.NonNull;

import ru.samples.itis.githubclient.R;

/**
 * @author Artur Vasilov
 */
public class LogInFragment extends BaseFragment {

    @NonNull
    public static LogInFragment getInstance() {
        return new LogInFragment();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.log_in_fragment;
    }
}
