package ru.arturvasilov.gcmtest;

import android.content.Intent;

import com.google.android.gms.iid.InstanceIDListenerService;

/**
 * @author Artur Vasilov
 */
public class GCMInstanceIDListenerService extends InstanceIDListenerService {

    @Override
    public void onTokenRefresh() {
        startService(new Intent(this, GCMRegistrationService.class));
    }
}
