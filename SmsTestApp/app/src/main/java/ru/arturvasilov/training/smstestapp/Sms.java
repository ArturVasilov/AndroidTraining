package ru.arturvasilov.training.smstestapp;

import android.database.Cursor;
import android.support.annotation.NonNull;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Artur Vasilov
 */
public class Sms {

    private final String mAddress;
    private final String mMessage;

    public Sms(@NonNull String address, @NonNull String message) {
        mAddress = address;
        mMessage = message;
    }

    @NonNull
    public String getAddress() {
        return mAddress;
    }

    @NonNull
    public String getMessage() {
        return mMessage;
    }

    @NonNull
    public static List<Sms> fromCursor(@NonNull Cursor cursor) {
        List<Sms> smsList = new ArrayList<>();
        if (cursor.isClosed()) {
            return smsList;
        }
        if (!cursor.moveToFirst()) {
            cursor.close();
            return smsList;
        }

        int indexAddress = cursor.getColumnIndex("address");
        int indexBody = cursor.getColumnIndex("body");

        do {
            String address = cursor.getString(indexAddress);
            String message = cursor.getString(indexBody);
            Sms sms = new Sms(address, message);
            smsList.add(sms);
        } while (cursor.moveToNext());

        cursor.close();

        return smsList;
    }
}
