package ru.samples.itis.githubclient.api;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import javax.inject.Inject;

import ru.samples.itis.githubclient.di.Graphs;

/**
 * @author Artur Vasilov
 */
public class RequestsService extends Service {

    private static final String REQUEST_KEY = "Request_key";

    @Inject
    GithubService mService;

    public static void executeRequest(Context context, @NonNull Request request) {
        Intent intent = new Intent(context, RequestsService.class);
        intent.putExtra(REQUEST_KEY, request);
        context.startService(intent);
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Graphs.appGraph(this).injectRequestsService(this);
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        if (intent != null) {
            Request request = (Request) intent.getSerializableExtra(REQUEST_KEY);
            request.process(this, mService);
        }
        return super.onStartCommand(intent, flags, startId);
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}
