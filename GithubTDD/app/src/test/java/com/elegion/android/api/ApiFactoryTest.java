package com.elegion.android.api;

import com.elegion.android.content.Authorization;
import com.elegion.android.content.Repository;
import com.google.gson.JsonObject;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.util.List;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import retrofit2.http.Body;
import retrofit2.http.Header;
import rx.Observable;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

/**
 * @author Artur Vasilov
 */
@RunWith(JUnit4.class)
public class ApiFactoryTest {

    private static final int CONNECT_TIMEOUT = 15 * 1000;
    private static final int READ_TIMEOUT = 30 * 1000;
    private static final int WRITE_TIMEOUT = 30 * 1000;

    @Test
    public void testClient() throws Exception {
        OkHttpClient client = ApiFactory.getClient();
        assertEquals(CONNECT_TIMEOUT, client.connectTimeoutMillis());
        assertEquals(READ_TIMEOUT, client.readTimeoutMillis());
        assertEquals(WRITE_TIMEOUT, client.writeTimeoutMillis());

        //logging and token interceptors
        assertEquals(2, client.interceptors().size());
    }

    @Test
    public void testLoggingInterceptor() throws Exception {
        Interceptor interceptor = ApiFactory.getLoggingInterceptor();
        assertNotNull(interceptor);
    }

    @Test
    public void testTokenInterceptor() throws Exception {
        Interceptor interceptor = ApiFactory.getTokenInterceptor();
        assertNotNull(interceptor);
    }

    @Test
    public void testDefaultService() throws Exception {
        GithubService service = ApiFactory.getDefaultGithubService();
        assertNotNull(service);
    }

    @Test
    public void testGithubService() throws Exception {
        GithubService service = ApiFactory.getGithubService();
        assertNotNull(service);
    }

    @Test
    public void testMockedService() throws Exception {
        GithubService service = new GithubService() {
            @Override
            public Observable<Authorization> authorize(@Header("Authorization") String authorization, @Body JsonObject params) {
                return Observable.just(new Authorization());
            }

            @Override
            public Observable<List<Repository>> repositories() {
                return Observable.just(new Repository()).toList();
            }
        };

        ApiFactory.setGithubService(service);

        GithubService githubService = ApiFactory.getGithubService();
        assertTrue(githubService == service);
    }
}
