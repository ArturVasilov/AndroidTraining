package ru.samples.itis.githubclient.activity;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import ru.samples.itis.githubclient.R;
import ru.samples.itis.githubclient.di.Graphs;
import ru.samples.itis.githubclient.di.graph.ActivityGraph;

/**
 * @author Artur Vasilov
 */
public abstract class BaseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_single);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        if (savedInstanceState == null) {
            getFragmentManager().beginTransaction()
                    .replace(R.id.content_frame, getFragment())
                    .commit();
        }
    }

    @NonNull
    protected ActivityGraph graph() {
        return Graphs.activityGraph(this);
    }

    @NonNull
    protected abstract Fragment getFragment();
}

