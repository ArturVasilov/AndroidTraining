package ru.samples.itis.githubclient.activity;

import android.app.Activity;
import android.app.Fragment;
import android.content.Intent;
import android.support.annotation.NonNull;

import ru.samples.itis.githubclient.fragment.RepositoriesFragment;

/**
 * @author Artur Vasilov
 */
public class RepositoriesActivity extends BaseActivity {

    public static void start(@NonNull Activity activity) {
        Intent intent = new Intent(activity, RepositoriesActivity.class);
        activity.startActivity(intent);
    }

    @NonNull
    @Override
    protected Fragment getFragment() {
        return RepositoriesFragment.getInstance();
    }
}
