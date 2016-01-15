package ru.arturvasilov.contacts;

import android.content.Context;
import android.database.Cursor;
import android.provider.ContactsContract;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Artur Vasilov
 */
public class Contact {

    private final int mId;
    private final String mName;
    private final String mPhoneNumber;

    public Contact(int id, @NonNull String name, @NonNull String phoneNumber) {
        mId = id;
        mName = name;
        mPhoneNumber = phoneNumber;
    }

    public int getId() {
        return mId;
    }

    @NonNull
    public String getName() {
        return mName;
    }

    @NonNull
    public String getPhoneNumber() {
        return mPhoneNumber;
    }

    @NonNull
    public static List<Contact> fromCursor(@NonNull Cursor cursor, Context context) {
        List<Contact> contacts = new ArrayList<>();
        int idColumn = cursor.getColumnIndex(ContactsContract.Contacts._ID);
        int nameColumn = cursor.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME);
        do {
            int id = cursor.getInt(idColumn);
            String name = cursor.getString(nameColumn);
            String phone = getPhoneNumber(id, context);
            if (phone != null) {
                contacts.add(new Contact(id, name, phone));
            }
        } while (cursor.moveToNext());
        cursor.close();

        return contacts;
    }

    @Nullable
    public static String getPhoneNumber(int id, Context context) {
        Cursor cursor = context.getContentResolver().query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI, null,
                ContactsContract.CommonDataKinds.Phone.CONTACT_ID + "=?", new String[]{String.valueOf(id)}, null);
        if (cursor != null && !cursor.isClosed()) {
            if (cursor.moveToFirst()) {
                return cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
            }
            cursor.close();
        }
        return null;
    }
}
