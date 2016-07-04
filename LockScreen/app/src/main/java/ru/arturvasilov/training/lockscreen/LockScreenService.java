package ru.arturvasilov.training.lockscreen;

import android.app.Notification;
import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.IBinder;
import android.support.v7.app.NotificationCompat;

/**
 * @author Artur Vasilov
 */
public class LockScreenService extends Service {

    private BroadcastReceiver mReceiver;

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        IntentFilter filter = new IntentFilter(Intent.ACTION_SCREEN_ON);
        filter.addAction(Intent.ACTION_SCREEN_OFF);
        mReceiver = new LockScreenReceiver();
        registerReceiver(mReceiver, filter);
        startForeground();
        return START_STICKY;
    }

    private void startForeground() {
        Notification notification = new NotificationCompat.Builder(this)
                .setContentTitle(getResources().getString(R.string.app_name))
                .setTicker(getResources().getString(R.string.app_name))
                .setContentText("Scree")
                .setSmallIcon(R.mipmap.ic_launcher)
                .setContentIntent(null)
                .setOngoing(true)
                .build();
        startForeground(9999, notification);
    }

    // Unregister receiver
    @Override
    public void onDestroy() {
        super.onDestroy();
        unregisterReceiver(mReceiver);
    }
}
