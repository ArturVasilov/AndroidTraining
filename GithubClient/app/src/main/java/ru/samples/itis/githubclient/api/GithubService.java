package ru.samples.itis.githubclient.api;

import retrofit.http.Body;
import retrofit.http.GET;
import retrofit.http.Headers;
import retrofit.http.POST;
import retrofit.http.Query;
import ru.samples.itis.githubclient.api.body.AuthParams;
import ru.samples.itis.githubclient.content.Repository;
import rx.Observable;

/**
 * @author Artur Vasilov
 */
public interface GithubService {

    @Headers("Accept: application/json")
    @POST("/login/oauth/authorize/access_token")
    Observable<String> sendAccessCodeData(@Body AuthParams params);

    @GET("user/repos")
    Observable<Repository> getRepositories(@Query("page") int pageNumber);

    /*
    @GET("repos/{owner_name}/{repository_name}/commits")
    Call<ArrayList<CommitDataModel>> getCommits(
            @Path("owner_name") String ownerName,
            @Path("repository_name") String repositoryName,
            @Query("page") int pageNumber
    );*/

}
