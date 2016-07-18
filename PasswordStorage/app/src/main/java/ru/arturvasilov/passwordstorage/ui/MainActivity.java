package ru.arturvasilov.passwordstorage.ui;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import ru.arturvasilov.passwordstorage.R;
import ru.arturvasilov.passwordstorage.data.AuthData;
import ru.arturvasilov.passwordstorage.data.AuthStorage;

public class MainActivity extends AppCompatActivity implements AuthDataAdapter.OnItemClickListener {

    private static final String MASTER_PASSWORD_KEY = "master_password_key";

    private AuthDataAdapter mAdapter;

    private String mMasterPassword;

    public static void start(@NonNull Activity activity, @NonNull String masterPassword) {
        Intent intent = new Intent(activity, MainActivity.class);
        intent.putExtra(MASTER_PASSWORD_KEY, masterPassword);
        activity.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mMasterPassword = getIntent().getStringExtra(MASTER_PASSWORD_KEY);

        mAdapter = new AuthDataAdapter(this);
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.addItemDecoration(new DividerItemDecorator(this, R.drawable.divider));
        recyclerView.setAdapter(mAdapter);

        findViewById(R.id.fab).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        mAdapter.changeDataSet(AuthStorage.getAuthData(this, mMasterPassword));
    }

    @Override
    protected void onPause() {
        super.onPause();
        finish();
    }

    @Override
    public void onItemClick(@NonNull AuthData authData) {
        AuthDataActivity.start(this, authData);
    }
}
