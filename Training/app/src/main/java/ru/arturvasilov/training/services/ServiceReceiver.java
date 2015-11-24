package ru.arturvasilov.training.services;

import android.content.Context;
import android.content.Intent;
import android.support.v4.content.WakefulBroadcastReceiver;

/**
 * @author Artur Vasilov
 */
public class ServiceReceiver extends WakefulBroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        Intent serviceIntent = new Intent(context, SomeService.class);
        startWakefulService(context, serviceIntent);
    }
}
