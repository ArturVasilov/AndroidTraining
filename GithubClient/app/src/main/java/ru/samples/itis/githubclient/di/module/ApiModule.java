package ru.samples.itis.githubclient.di.module;

import android.support.annotation.NonNull;

import dagger.Module;
import dagger.Provides;
import retrofit.Retrofit;
import ru.samples.itis.githubclient.BuildConfig;
import ru.samples.itis.githubclient.api.GithubService;
import ru.samples.itis.githubclient.mock.MockedServer;

/**
 * @author Artur Vasilov
 */
@Module(includes = ServerModule.class)
public class ApiModule {

    @Provides
    GithubService githubService(@NonNull Retrofit retrofit) {
        return BuildConfig.USE_MOCKS ? new MockedServer() : retrofit.create(GithubService.class);
    }

    @Provides
    String endpoint() {
        return BuildConfig.API_ENDPOINT;
    }

}
