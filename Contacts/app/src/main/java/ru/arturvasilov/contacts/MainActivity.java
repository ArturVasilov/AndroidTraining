package ru.arturvasilov.contacts;

import android.Manifest;
import android.content.ContentProviderOperation;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements ActivityCompat.OnRequestPermissionsResultCallback, View.OnClickListener {

    private static final int MAIN_ACTIVITY_CONSTANT = Math.abs(Math.class.getSimpleName().hashCode()) % 200;
    private static final int READ_CONTACTS_PERMISSION = MAIN_ACTIVITY_CONSTANT + 1;
    private static final int WRITE_CONTACTS_PERMISSION = MAIN_ACTIVITY_CONSTANT + 2;

    private FloatingActionButton mFab;

    @Nullable
    private Contact mContact;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mFab = (FloatingActionButton) findViewById(R.id.fab);

        if (PermissionUtils.checkPermissions(this, Manifest.permission.READ_CONTACTS)) {
            setupRecycler();
        } else {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_CONTACTS}, READ_CONTACTS_PERMISSION);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        mFab.setOnClickListener(this);
    }

    @Override
    protected void onPause() {
        mFab.setOnClickListener(null);
        super.onPause();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == READ_CONTACTS_PERMISSION) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                setupRecycler();
            }
        } else if (requestCode == WRITE_CONTACTS_PERMISSION) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                insertContact();
            }
        }
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.fab) {
            mContact = new Contact(111, "ArturVasilovTest", "79111111111");
            if (PermissionUtils.checkPermissions(this, Manifest.permission.WRITE_CONTACTS)) {
                insertContact();
            } else {
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_CONTACTS}, WRITE_CONTACTS_PERMISSION);
            }
        }
    }

    private void insertContactHard() {
        if (mContact == null) {
            return;
        }

        Uri rawContactUri = getContentResolver().insert(ContactsContract.RawContacts.CONTENT_URI, new ContentValues());
        long rawContactId =  ContentUris.parseId(rawContactUri);
        ContentValues values = new ContentValues();
        values.put(ContactsContract.Data.RAW_CONTACT_ID, rawContactId);
        values.put(ContactsContract.Data.MIMETYPE, ContactsContract.CommonDataKinds.StructuredName.CONTENT_ITEM_TYPE);
        values.put(ContactsContract.CommonDataKinds.StructuredName.DISPLAY_NAME, mContact.getName());
        getContentResolver().insert(ContactsContract.Data.CONTENT_URI, values);

        values.put(ContactsContract.Data.RAW_CONTACT_ID, rawContactId);
        values.put(ContactsContract.Data.MIMETYPE, ContactsContract.CommonDataKinds.Phone.CONTENT_ITEM_TYPE);
        values.put(ContactsContract.CommonDataKinds.Phone.NUMBER, mContact.getPhoneNumber());
        values.put(ContactsContract.CommonDataKinds.Phone.TYPE, ContactsContract.CommonDataKinds.Phone.TYPE_MOBILE);

        getContentResolver().insert(ContactsContract.Data.CONTENT_URI, values);
    }
    
    private void insertContact() {
        if (mContact == null) {
            return;
        }
        ArrayList<ContentProviderOperation> operations = new ArrayList<>();

        operations.add(ContentProviderOperation.newInsert(ContactsContract.RawContacts.CONTENT_URI)
                .withValue(ContactsContract.RawContacts.ACCOUNT_TYPE, null)
                .withValue(ContactsContract.RawContacts.ACCOUNT_NAME, null)
                .build());

        operations.add(ContentProviderOperation.newInsert(ContactsContract.Data.CONTENT_URI)
                .withValueBackReference(ContactsContract.Data.RAW_CONTACT_ID, 0)
                .withValue(ContactsContract.Data.MIMETYPE, ContactsContract.CommonDataKinds.StructuredName.CONTENT_ITEM_TYPE)
                .withValue(ContactsContract.CommonDataKinds.StructuredName.DISPLAY_NAME, mContact.getName())
                .build());
        operations.add(ContentProviderOperation.newInsert(ContactsContract.Data.CONTENT_URI)
                .withValueBackReference(ContactsContract.Data.RAW_CONTACT_ID, 0)
                .withValue(ContactsContract.Data.MIMETYPE, ContactsContract.CommonDataKinds.Phone.CONTENT_ITEM_TYPE)
                .withValue(ContactsContract.CommonDataKinds.Phone.NUMBER, mContact.getPhoneNumber())
                .withValue(ContactsContract.CommonDataKinds.Phone.TYPE, ContactsContract.CommonDataKinds.Phone.TYPE_MOBILE)
                .build());

        try {
            getContentResolver().applyBatch(ContactsContract.AUTHORITY, operations);
        } catch (Exception e) {
            Log.e("Exception: ", e.getMessage());
        }
    }

    private void setupRecycler() {
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.contactsRecycler);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.addItemDecoration(new DividerItemDecoration(this));
        recyclerView.setAdapter(new ContactsAdapter(this));
    }
}
