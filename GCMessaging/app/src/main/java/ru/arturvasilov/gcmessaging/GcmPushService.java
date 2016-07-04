package ru.arturvasilov.gcmessaging;

import android.app.IntentService;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.annotations.SerializedName;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * @author Artur Vasilov
 */
public class GcmPushService extends IntentService {

    private static final String API_KEY = "api_key";
    private static final String REG_ID_KEY = "regId";
    private static final String JSON_DATA = "json_data";

    public static void push(@NonNull Context context, @NonNull String apiKey,
                            @NonNull String regId, @NonNull String jsonData) {
        Intent intent = new Intent(context, GcmPushService.class);
        Bundle bundle = new Bundle();
        bundle.putString(API_KEY, apiKey);
        bundle.putString(REG_ID_KEY, regId);
        bundle.putString(JSON_DATA, jsonData);
        intent.putExtras(bundle);
        context.startService(intent);
    }

    public GcmPushService() {
        super(GcmPushService.class.getSimpleName());
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        OkHttpClient client = new OkHttpClient();
        try {
            Response response = client.newCall(getRequest(intent.getExtras())).execute();
            response.body();
        } catch (IOException e) {
            Toast.makeText(this, "Failed to send push due, see logs", Toast.LENGTH_LONG).show();
            e.printStackTrace();
        }
    }

    @NonNull
    private Request getRequest(@NonNull Bundle bundle) {
        MediaType mediaType = MediaType.parse("application/json; charset=utf-8");
        final String apiKey = bundle.getString(API_KEY);
        //noinspection ConstantConditions
        String json = new Gson().toJson(new Content(bundle.getString(REG_ID_KEY), bundle.getString(JSON_DATA)));
        RequestBody body = RequestBody.create(mediaType, json);
        return new Request.Builder()
                .url("https://android.googleapis.com/gcm/send")
                .addHeader("Content-Type", "application/json")
                .addHeader("Authorization", String.format("key=%s", apiKey))
                .post(body)
                .build();
    }

    private class Content {

        @SerializedName("to")
        private String mToId;

        @SerializedName("data")
        private Map<String, String> mJsonData;

        public Content(@NonNull String regId, @NonNull String jsonData) {
            mToId = regId;
            mJsonData = new HashMap<>();
            try {
                JSONObject json = new JSONObject(jsonData);
                Iterator<String> keys = json.keys();
                while (keys.hasNext()) {
                    String key = keys.next();
                    mJsonData.put(key, json.getString(key));
                }
            } catch (JSONException ignored) {
            }
        }
    }

}
