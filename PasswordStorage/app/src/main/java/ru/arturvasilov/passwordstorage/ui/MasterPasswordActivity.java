package ru.arturvasilov.passwordstorage.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;

import ru.arturvasilov.passwordstorage.R;

/**
 * @author Artur Vasilov
 */
public class MasterPasswordActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText mEditText;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_master_password);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mEditText = (EditText) findViewById(R.id.masterPasswordEdit);
        findViewById(R.id.enterButton).setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.enterButton) {
            String password = mEditText.getText().toString();
            MainActivity.start(this, password);
            finish();
        }
    }
}
