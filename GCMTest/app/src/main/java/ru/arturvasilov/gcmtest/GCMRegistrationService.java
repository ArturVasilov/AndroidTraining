package ru.arturvasilov.gcmtest;

import android.app.IntentService;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.Log;

import com.google.android.gms.gcm.GoogleCloudMessaging;
import com.google.android.gms.iid.InstanceID;

import java.io.IOException;
import java.util.logging.Logger;

/**
 * @author Artur Vasilov
 */
public class GCMRegistrationService extends IntentService {

    private static final String TAG = GCMRegistrationService.class.getName();

    /**
     * Creates an IntentService.  Invoked by your subclass's constructor.
     */
    public GCMRegistrationService() {
        super(TAG);
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        try {
            InstanceID instanceID = InstanceID.getInstance(this);
            String token = instanceID.getToken(
                    BuildConfig.GCM_SENDER_ID, GoogleCloudMessaging.INSTANCE_ID_SCOPE, null);
            // save token on server.
            sendRegistrationToServer(token);
        } catch (IOException e) {
            Log.e(TAG, e.getMessage());
        }
    }

    private void sendRegistrationToServer(@Nullable final String regId) {
        if (TextUtils.isEmpty(regId)) {
            return;
        }

        Log.i(TAG, "registration id = " + regId);
    }
}
