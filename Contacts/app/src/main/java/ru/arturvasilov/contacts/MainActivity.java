package ru.arturvasilov.contacts;

import android.Manifest;
import android.content.ContentProviderOperation;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.pm.PackageManager;
import android.content.res.AssetFileDescriptor;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.ContactsContract;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class MainActivity extends AppCompatActivity implements ActivityCompat.OnRequestPermissionsResultCallback,
        View.OnClickListener, ContactsAdapter.OnItemClick {

    private static final int MAIN_ACTIVITY_CONSTANT = Math.abs(Math.class.getSimpleName().hashCode()) % 200;
    private static final int READ_CONTACTS_PERMISSION = MAIN_ACTIVITY_CONSTANT + 1;
    private static final int WRITE_CONTACTS_PERMISSION = MAIN_ACTIVITY_CONSTANT + 2;

    private FloatingActionButton mFab;

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
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_save_all) {
            saveAll();
            return true;
        }

        return super.onOptionsItemSelected(item);
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
            if (PermissionUtils.checkPermissions(this, Manifest.permission.WRITE_CONTACTS)) {
                insertContact();
            } else {
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_CONTACTS}, WRITE_CONTACTS_PERMISSION);
            }
        }
    }

    @Override
    public void onClick(@NonNull Contact contact) {
    }

    private void saveAll() {
        String vFile = String.format("Contacts_%s.vcf", new SimpleDateFormat("dd-MM-yyyy").format(new Date()));
        Cursor cursor = getContentResolver().query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI, null,
                null, null, null);
        if (cursor == null || cursor.isClosed()) {
            return;
        }

        if (!cursor.moveToFirst() || cursor.getCount() == 0) {
            cursor.close();
            return;
        }

        String path = Environment.getExternalStorageDirectory().toString() + File.separator + vFile;
        FileOutputStream fileOutputStream;
        try {
            fileOutputStream = new FileOutputStream(path, false);
        } catch (FileNotFoundException e) {
            return;
        }

        do {
            try {
                String lookupKey = cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts.LOOKUP_KEY));
                Uri uri = Uri.withAppendedPath(ContactsContract.Contacts.CONTENT_VCARD_URI, lookupKey);
                AssetFileDescriptor fileDescriptor;
                fileDescriptor = getContentResolver().openAssetFileDescriptor(uri, "r");
                if (fileDescriptor == null) {
                    continue;
                }

                FileInputStream inputStream = fileDescriptor.createInputStream();
                byte[] buf = new byte[(int) fileDescriptor.getDeclaredLength()];
                //noinspection ResultOfMethodCallIgnored
                inputStream.read(buf);

                String vCard = new String(buf);
                fileOutputStream.write(vCard.getBytes());
                inputStream.close();
            } catch (Exception ignored) {
            }
        } while (cursor.moveToNext());

        try {
            fileOutputStream.close();
        } catch (IOException ignored) {
        }
        cursor.close();
    }

    private void insertContactHard() {
        Contact contact = new Contact(111, "ArturVasilovTest", "79111111111");

        Uri rawContactUri = getContentResolver().insert(ContactsContract.RawContacts.CONTENT_URI, new ContentValues());
        long rawContactId = ContentUris.parseId(rawContactUri);
        ContentValues values = new ContentValues();
        values.put(ContactsContract.Data.RAW_CONTACT_ID, rawContactId);
        values.put(ContactsContract.Data.MIMETYPE, ContactsContract.CommonDataKinds.StructuredName.CONTENT_ITEM_TYPE);
        values.put(ContactsContract.CommonDataKinds.StructuredName.DISPLAY_NAME, contact.getName());
        getContentResolver().insert(ContactsContract.Data.CONTENT_URI, values);

        values.put(ContactsContract.Data.RAW_CONTACT_ID, rawContactId);
        values.put(ContactsContract.Data.MIMETYPE, ContactsContract.CommonDataKinds.Phone.CONTENT_ITEM_TYPE);
        values.put(ContactsContract.CommonDataKinds.Phone.NUMBER, contact.getPhoneNumber());
        values.put(ContactsContract.CommonDataKinds.Phone.TYPE, ContactsContract.CommonDataKinds.Phone.TYPE_MOBILE);

        getContentResolver().insert(ContactsContract.Data.CONTENT_URI, values);
    }

    private void insertContact() {
        Contact contact = new Contact(111, "ArturVasilovTest", "79111111111");
        ArrayList<ContentProviderOperation> operations = new ArrayList<>();

        operations.add(ContentProviderOperation.newInsert(ContactsContract.RawContacts.CONTENT_URI)
                .withValue(ContactsContract.RawContacts.ACCOUNT_TYPE, null)
                .withValue(ContactsContract.RawContacts.ACCOUNT_NAME, null)
                .build());

        operations.add(ContentProviderOperation.newInsert(ContactsContract.Data.CONTENT_URI)
                .withValueBackReference(ContactsContract.Data.RAW_CONTACT_ID, 0)
                .withValue(ContactsContract.Data.MIMETYPE, ContactsContract.CommonDataKinds.StructuredName.CONTENT_ITEM_TYPE)
                .withValue(ContactsContract.CommonDataKinds.StructuredName.DISPLAY_NAME, contact.getName())
                .build());
        operations.add(ContentProviderOperation.newInsert(ContactsContract.Data.CONTENT_URI)
                .withValueBackReference(ContactsContract.Data.RAW_CONTACT_ID, 0)
                .withValue(ContactsContract.Data.MIMETYPE, ContactsContract.CommonDataKinds.Phone.CONTENT_ITEM_TYPE)
                .withValue(ContactsContract.CommonDataKinds.Phone.NUMBER, contact.getPhoneNumber())
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
        recyclerView.setAdapter(new ContactsAdapter(this, this));
    }
}
