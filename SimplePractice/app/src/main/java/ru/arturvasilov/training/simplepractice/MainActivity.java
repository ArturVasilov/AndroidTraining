package ru.arturvasilov.training.simplepractice;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.Process;
import android.os.RemoteException;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import ru.arturvasilov.training.simplepractice.gestures.GesturedImage;
import ru.arturvasilov.training.simplepractice.gestures.RotateGestureListener;
import ru.arturvasilov.training.simplepractice.gestures.ScaleGestureListener;
import ru.arturvasilov.training.simplepractice.processes.DifferentProcessService;
import ru.arturvasilov.training.simplepractice.processes.IRemoteService;

public class MainActivity extends AppCompatActivity {

    private IRemoteService mRemoteService;

    private final ServiceConnection mConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            mRemoteService = IRemoteService.Stub.asInterface(service);
            try {
                int id = mRemoteService.getPid();
                Process.killProcess(id);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            mRemoteService = null;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        GesturedImage image = (GesturedImage) findViewById(R.id.image);
        image.addGestureListener(new ScaleGestureListener(this, image));
        image.addGestureListener(new RotateGestureListener());

        serviceConnection();
    }

    private void serviceConnection() {
        bindService(new Intent(this, DifferentProcessService.class), mConnection, Context.BIND_AUTO_CREATE);
    }

}
