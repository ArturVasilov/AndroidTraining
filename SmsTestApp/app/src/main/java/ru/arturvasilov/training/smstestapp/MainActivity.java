package ru.arturvasilov.training.smstestapp;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements SmsAdapter.OnItemClick, View.OnClickListener {

    private static final int MAIN_ACTIVITY_CONSTANT = Math.abs(Math.class.getSimpleName().hashCode()) % 200;
    private static final int READ_SMS_PERMISSION = MAIN_ACTIVITY_CONSTANT + 1;
    private static final int SEND_SMS_PERMISSION = MAIN_ACTIVITY_CONSTANT + 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scrolling);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        assert fab != null;
        fab.setOnClickListener(this);

        if (PermissionUtils.checkPermissions(this, Manifest.permission.READ_SMS)) {
            setupRecycler();
        } else {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_SMS}, READ_SMS_PERMISSION);
        }
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.fab) {
            if (PermissionUtils.checkPermissions(this, Manifest.permission.SEND_SMS)) {
               sendSms();
            } else {
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.SEND_SMS}, SEND_SMS_PERMISSION);
            }
        }
    }

    @Override
    public void onClick(@NonNull Sms sms) {
        //Do nothing
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == READ_SMS_PERMISSION) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                setupRecycler();
            }
        } else if (requestCode == SEND_SMS_PERMISSION) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                sendSms();
            }
        }
    }

    private void setupRecycler() {
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.sms_recycler);
        assert recyclerView != null;
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.addItemDecoration(new DividerItemDecoration(this));
        recyclerView.setAdapter(new SmsAdapter(this, this));
    }

    private void sendSms() {
        String toPhoneNumber = "89178734837";
        String smsMessage = "Hello from test app";
        try {
            SmsManager smsManager = SmsManager.getDefault();
            smsManager.sendTextMessage(toPhoneNumber, null, smsMessage, null, null);
            Toast.makeText(getApplicationContext(), "SMS sent", Toast.LENGTH_LONG).show();
        } catch (Exception e) {
            Toast.makeText(getApplicationContext(), "Sending SMS failed", Toast.LENGTH_LONG).show();
            e.printStackTrace();
        }
    }


}
