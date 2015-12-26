package ru.samples.itis.githubclient;

import android.content.ContentValues;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import ru.arturvasilov.sqlite.SQLite;
import ru.samples.itis.githubclient.content.Repository;

public class LogInActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        GithubApplication.injector(this).injectLogInActivity(this);

        ContentValues values = new ContentValues();
        values.put("name", "aaaa");
        values.put("description", "aaaa");
        values.put("language", "aaa");
        values.put("stars", 5);
        values.put("forks", 5);
        values.put("watchers", 6);

        getContentResolver().delete(Repository.TABLE.getUri(), null, null);

        SQLite.get(this).delete(Repository.TABLE).execute();
    }
}
