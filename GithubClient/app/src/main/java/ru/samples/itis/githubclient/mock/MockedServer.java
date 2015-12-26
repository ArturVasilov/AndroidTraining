package ru.samples.itis.githubclient.mock;

import retrofit.http.Body;
import retrofit.http.Query;
import ru.samples.itis.githubclient.api.GithubService;
import ru.samples.itis.githubclient.api.body.AuthParams;
import ru.samples.itis.githubclient.content.Repository;
import rx.Observable;

/**
 * @author Artur Vasilov
 */
public class MockedServer implements GithubService {

    @Override
    public Observable<String> sendAccessCodeData(@Body AuthParams params) {
        return null;
    }

    @Override
    public Observable<Repository> getRepositories(@Query("page") int pageNumber) {
        return null;
    }
}
