package ru.arturvasilov.training.services;

import android.app.IntentService;
import android.content.Intent;
import android.os.SystemClock;

/**
 * @author Artur Vasilov
 */
public class SomeService extends IntentService {

    public SomeService() {
        super(SomeService.class.getName());
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        try {
            SystemClock.sleep(10_000);
        } finally {
            ServiceReceiver.completeWakefulIntent(intent);
        }
    }
}
