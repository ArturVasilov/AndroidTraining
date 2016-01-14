package ru.samples.itis.githubclient.api;

import com.google.gson.JsonObject;

import java.util.List;

import retrofit.http.Body;
import retrofit.http.GET;
import retrofit.http.Header;
import retrofit.http.POST;
import retrofit.http.Path;
import ru.samples.itis.githubclient.content.CommitsResponse;
import ru.samples.itis.githubclient.content.Repository;
import ru.samples.itis.githubclient.content.auth.Authorization;
import rx.Observable;

/**
 * @author Artur Vasilov
 */
public interface GithubService {

    @POST("/authorizations")
    Observable<Authorization> authorize(@Header("Authorization") String authorization,
                                        @Body JsonObject params);

    @GET("/user/repos")
    Observable<List<Repository>> repositories();

    @GET("/repos/{user}/{repo}/commits")
    Observable<List<CommitsResponse>> commits(@Path("user") String user, @Path("repo") String repo);
}
