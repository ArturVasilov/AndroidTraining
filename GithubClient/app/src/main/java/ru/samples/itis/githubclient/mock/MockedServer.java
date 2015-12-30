package ru.samples.itis.githubclient.mock;

import com.google.gson.JsonObject;

import java.util.List;

import retrofit.http.Body;
import retrofit.http.Header;
import ru.samples.itis.githubclient.api.GithubService;
import ru.samples.itis.githubclient.content.Authorization;
import ru.samples.itis.githubclient.content.Repository;
import rx.Observable;

/**
 * @author Artur Vasilov
 */
public class MockedServer implements GithubService {

    @Override
    public Observable<Authorization> authorize(@Header("Authorization") String authorization, @Body JsonObject params) {
        return null;
    }

    @Override
    public Observable<List<Repository>> repositories(@Header("Authorization") String token) {
        return null;
    }
}
