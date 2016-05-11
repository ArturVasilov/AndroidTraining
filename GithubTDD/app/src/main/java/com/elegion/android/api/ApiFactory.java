package com.elegion.android.api;

import android.support.annotation.NonNull;

import com.elegion.android.BuildConfig;
import com.elegion.android.app.Settings;

import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.GsonConverterFactory;
import retrofit2.Retrofit;
import retrofit2.RxJavaCallAdapterFactory;

/**
 * @author Artur Vasilov
 */
public final class ApiFactory {

    private static final int CONNECT_TIMEOUT = 15;
    private static final int READ_TIMEOUT = 30;
    private static final int WRITE_TIMEOUT = 30;

    private static String sToken = "";

    private static GithubService sService;

    private ApiFactory() {
    }

    public static void setup(@NonNull Settings settings) {
        sToken = settings.token().get();
        sService = getGithubService();
    }

    @NonNull
    public static OkHttpClient getClient() {
        return new OkHttpClient.Builder()
                .connectTimeout(CONNECT_TIMEOUT, TimeUnit.SECONDS)
                .readTimeout(READ_TIMEOUT, TimeUnit.SECONDS)
                .writeTimeout(WRITE_TIMEOUT, TimeUnit.SECONDS)
                .addInterceptor(getLoggingInterceptor())
                .addInterceptor(getTokenInterceptor())
                .build();
    }

    @NonNull
    public static Interceptor getLoggingInterceptor() {
        return new HttpLoggingInterceptor().setLevel(BuildConfig.DEBUG
                ? HttpLoggingInterceptor.Level.BODY
                : HttpLoggingInterceptor.Level.NONE);
    }

    @NonNull
    public static Interceptor getTokenInterceptor() {
        return TokenInterceptor.getInstance(sToken);
    }

    @NonNull
    public static GithubService getGithubService() {
        GithubService service = sService;
        if (service == null) {
            synchronized (ApiFactory.class) {
                service = sService;
                if (service == null) {
                    service = sService = getDefaultGithubService();
                }
            }
        }
        return service;
    }

    @NonNull
    public static GithubService getDefaultGithubService() {
        sService = new Retrofit.Builder()
                .baseUrl(BuildConfig.API_ENDPOINT)
                .client(getClient())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(GithubService.class);
        return sService;
    }

    public static void setGithubService(@NonNull GithubService service) {
        sService = service;
    }
}
