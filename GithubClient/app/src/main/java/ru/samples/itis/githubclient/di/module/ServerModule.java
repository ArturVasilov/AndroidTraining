package ru.samples.itis.githubclient.di.module;

import android.support.annotation.NonNull;

import com.squareup.okhttp.Interceptor;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;

import java.util.concurrent.TimeUnit;

import dagger.Module;
import dagger.Provides;
import retrofit.GsonConverterFactory;
import retrofit.Retrofit;
import retrofit.RxJavaCallAdapterFactory;
import ru.samples.itis.githubclient.BuildConfig;
import ru.samples.itis.githubclient.content.auth.GithubAccount;

/**
 * @author Artur Vasilov
 */
@Module
public class ServerModule {

    private static final int CONNECT_TIMEOUT = 15;
    private static final int WRITE_TIMEOUT = 60;
    private static final int TIMEOUT = 60;

    @Provides
    OkHttpClient client(@NonNull Interceptor interceptor) {
        OkHttpClient client = new OkHttpClient();
        client.setConnectTimeout(CONNECT_TIMEOUT, TimeUnit.SECONDS);
        client.setWriteTimeout(WRITE_TIMEOUT, TimeUnit.SECONDS);
        client.setReadTimeout(TIMEOUT, TimeUnit.SECONDS);
        client.interceptors().add(interceptor);
        return client;
    }

    @Provides
    Retrofit retrofit() {
        return new Retrofit.Builder()
                .baseUrl(BuildConfig.API_ENDPOINT)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .client(client(tokenInterceptor()))
                .build();
    }

    @Provides
    Interceptor tokenInterceptor() {
        return chain -> {
            String token = GithubAccount.getInstance().getAuthToken();
            Request request = chain.request().newBuilder()
                    .addHeader("Authorization", String.format("%s %s", "token", token))
                    .build();
            return chain.proceed(request);
        };
    }
}
