package com.elegion.android.presenter;

import android.app.LoaderManager;
import android.content.Context;

import com.elegion.android.TestUtils;
import com.elegion.android.api.ApiFactory;
import com.elegion.android.api.GithubService;
import com.elegion.android.app.Settings;
import com.elegion.android.content.Authorization;
import com.elegion.android.content.Repository;
import com.elegion.android.view.LogInView;
import com.google.gson.JsonObject;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

import java.util.List;

import droidkit.content.StringValue;
import retrofit2.http.Body;
import retrofit2.http.Header;
import rx.Observable;

import static org.junit.Assert.assertNotNull;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * @author Artur Vasilov
 */
@RunWith(JUnit4.class)
public class LogInPresenterTest {

    private static final String TEST_TOKEN = "abcdefghj";

    private LogInPresenter mPresenter;
    private LogInView mView;

    @Before
    public void setUp() throws Exception {
        Context context = mock(Context.class);

        LoaderManager lm = mock(LoaderManager.class);
        mView = mock(LogInView.class);
        mPresenter = spy(new LogInPresenter(context, lm, mView));
        doNothing().when(mPresenter).logIn();
    }

    @Test
    public void testLogIn() throws Exception {
        LoaderManager lm = mock(LoaderManager.class);
        Settings settings = mock(Settings.class);

        StringValue value = mock(StringValue.class);
        doNothing().when(value).set(anyString());
        when(settings.token()).thenReturn(value);

        Context context = TestUtils.mockedContextWithSettings(settings);

        Authorization authorization = new Authorization();
        authorization.setId(111);
        authorization.setToken(TEST_TOKEN);

        mPresenter = spy(new LogInPresenter(context, lm, mView));
        mPresenter.handleAuthorized(settings, authorization);
        verify(mView).hideError();
        verify(mPresenter).logIn();
    }

    @Test
    public void testGithubObservable() throws Exception {
        GithubService service = new MockedServer();

        LogInPresenter presenter = mock(LogInPresenter.class);
        //noinspection unchecked
        when(presenter.populateObservable(any(Observable.class))).thenAnswer(new Answer<Observable<Authorization>>() {
            @Override
            public Observable<Authorization> answer(InvocationOnMock invocation) throws Throwable {
                //noinspection unchecked
                return (Observable<Authorization>) invocation.getArguments()[0];
            }
        });
        assertNotNull(presenter.populateObservable(service.authorize("aa", new JsonObject())));
    }

    @Test
    public void testError() throws Exception {
        Context context = mock(Context.class);
        LoaderManager lm = mock(LoaderManager.class);
        mPresenter = spy(new LogInPresenter(context, lm, mView));
        mPresenter.handleError(new RuntimeException());
        verify(mView).showError();
    }

    @After
    public void tearDown() throws Exception {
        ApiFactory.setGithubService(ApiFactory.getDefaultGithubService());
    }

    private class MockedServer implements GithubService {

        @Override
        public Observable<Authorization> authorize(@Header("Authorization") String authorization, @Body JsonObject params) {
            Authorization authorization1 = new Authorization();
            authorization1.setId(111);
            authorization1.setToken(TEST_TOKEN);
            return Observable.just(authorization1);
        }

        @Override
        public Observable<List<Repository>> repositories() {
            Repository repository = new Repository();
            repository.setName("TestRepo");
            repository.setDescription("Aaaa");
            repository.setLanguage("Java");
            repository.setForksCount(5);
            repository.setStarsCount(25);
            repository.setWatchersCount(35);
            return Observable.just(repository).toList();
        }
    }
}
