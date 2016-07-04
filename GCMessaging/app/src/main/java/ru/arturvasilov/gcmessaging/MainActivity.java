package ru.arturvasilov.gcmessaging;

import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText mGcmApiKeyEdit;
    private EditText mGcmRegIdEdit;
    private EditText mJsonDataEdit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mGcmApiKeyEdit = (EditText) findViewById(R.id.gcmApiKeyEdit);
        mGcmRegIdEdit = (EditText) findViewById(R.id.gcmRegIdEdit);
        mJsonDataEdit = (EditText) findViewById(R.id.jsonDataEdit);
        Button pushButton = (Button) findViewById(R.id.button);
        pushButton.setOnClickListener(this);
        restore();
    }

    @Override
    protected void onPause() {
        save();
        super.onPause();
    }

    @Override
    public void onClick(View v) {
        String apiKey = mGcmApiKeyEdit.getText().toString();
        String regId = mGcmRegIdEdit.getText().toString();
        String jsonData = mJsonDataEdit.getText().toString();

        GcmPushService.push(MainActivity.this, apiKey, regId, jsonData);
    }

    private void save() {
        String apiKey = mGcmApiKeyEdit.getText().toString();
        String regId = mGcmRegIdEdit.getText().toString();
        String jsonData = mJsonDataEdit.getText().toString();

        getPrefs().edit()
                .putString("apiKey", apiKey)
                .putString("regId", regId)
                .putString("jsonData", jsonData)
                .apply();
    }

    private void restore() {
        SharedPreferences prefs = getPrefs();
        String apiKey = prefs.getString("apiKey", "");
        String regId = prefs.getString("regId", "");
        String jsonData = prefs.getString("jsonData", "{\"key1\":\"value1\",\"key2\":\"value2\"}");

        mGcmApiKeyEdit.setText(apiKey);
        mGcmRegIdEdit.setText(regId);
        mJsonDataEdit.setText(jsonData);
    }

    @NonNull
    private SharedPreferences getPrefs() {
        return getSharedPreferences("gcmPrefs", MODE_PRIVATE);
    }
}
