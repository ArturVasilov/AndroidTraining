package com.elegion.android.activity;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.test.InstrumentationRegistry;
import android.support.test.espresso.intent.Intents;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.view.View;

import com.elegion.android.R;
import com.elegion.android.api.ApiFactory;
import com.elegion.android.api.GithubService;
import com.elegion.android.content.Authorization;
import com.elegion.android.content.Repository;
import com.google.gson.JsonObject;

import org.hamcrest.Matcher;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.ArrayList;
import java.util.List;

import droidkit.sqlite.SQLite;
import retrofit2.http.Body;
import retrofit2.http.Header;
import rx.Observable;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.contrib.RecyclerViewActions.actionOnItemAtPosition;
import static android.support.test.espresso.contrib.RecyclerViewActions.scrollToPosition;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static org.hamcrest.CoreMatchers.not;

/**
 * @author Artur Vasilov
 */
@RunWith(AndroidJUnit4.class)
public class RepositoriesActivityTest {

    private static final Matcher<View> RECYCLER_VIEW = withId(R.id.recyclerView);
    private static final Matcher<View> EMPTY_VIEW = withId(R.id.empty);

    @Rule
    public final ActivityTestRule<RepositoriesActivity> mActivityRule = new ActivityTestRule<>(RepositoriesActivity.class, false, false);

    private Context mContext;

    @Before
    public void setUp() throws Exception {
        mContext = InstrumentationRegistry.getContext();
        ApiFactory.setGithubService(new TestServerImpl());
        Intents.init();
        clearRepos();
    }

    @Test
    public void testEmptyView() throws Exception {
        ApiFactory.setGithubService(new TestServerImpl());
        clearRepos();

        mActivityRule.launchActivity(RepositoriesActivity.makeIntent(mContext));
        onView(EMPTY_VIEW).check(matches(isDisplayed()));
        onView(RECYCLER_VIEW).check(matches(not(isDisplayed())));
    }

    @Test
    public void testRepositoriesList() throws Exception {
        List<Repository> repositories = repositories(20);
        ApiFactory.setGithubService(new TestServerImpl(repositories));

        mActivityRule.launchActivity(RepositoriesActivity.makeIntent(mContext));

        onView(EMPTY_VIEW).check(matches(not(isDisplayed())));
        onView(RECYCLER_VIEW).check(matches(isDisplayed()));
    }

    @Test
    public void testClickActions() throws Exception {
        List<Repository> repositories = repositories(50);
        ApiFactory.setGithubService(new TestServerImpl(repositories));
        mActivityRule.launchActivity(RepositoriesActivity.makeIntent(mContext));

        onView(RECYCLER_VIEW).perform(scrollToPosition(30));
        onView(RECYCLER_VIEW).perform(scrollToPosition(2));
        onView(RECYCLER_VIEW).perform(scrollToPosition(45));
        onView(RECYCLER_VIEW).perform(scrollToPosition(16));
        onView(RECYCLER_VIEW).perform(scrollToPosition(25));
        onView(RECYCLER_VIEW).perform(scrollToPosition(31));
        onView(RECYCLER_VIEW).perform(scrollToPosition(49));
        onView(RECYCLER_VIEW).perform(scrollToPosition(40));
        onView(RECYCLER_VIEW).perform(scrollToPosition(9));

        onView(RECYCLER_VIEW).perform(actionOnItemAtPosition(5, click()));
        onView(RECYCLER_VIEW).perform(actionOnItemAtPosition(19, click()));
        onView(RECYCLER_VIEW).perform(actionOnItemAtPosition(22, click()));
        onView(RECYCLER_VIEW).perform(actionOnItemAtPosition(43, click()));
        onView(RECYCLER_VIEW).perform(actionOnItemAtPosition(33, click()));
    }

    @After
    public void tearDown() throws Exception {
        Intents.release();
        ApiFactory.setGithubService(ApiFactory.getDefaultGithubService());
        clearRepos();
    }

    @NonNull
    private List<Repository> repositories(int count) {
        List<Repository> repositories = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            Repository repository = new Repository();
            repository.setName("Repository " + (i + 1));
            repository.setLanguage("Language of repository " + (i + 1));
            repositories.add(repository);
        }
        return repositories;
    }

    private void clearRepos() {
        SQLite.where(Repository.class).clear();
    }

    private class TestServerImpl implements GithubService {

        private final List<Repository> mRepositories;

        public TestServerImpl() {
            this(new ArrayList<Repository>());
        }

        public TestServerImpl(@NonNull List<Repository> repositories) {
            mRepositories = repositories;
        }

        @Override
        public Observable<Authorization> authorize(@Header("Authorization") String authorization, @Body JsonObject params) {
            Authorization auth = new Authorization();
            auth.setId(111);
            auth.setToken("Aaaaa");
            return Observable.just(auth);
        }

        @Override
        public Observable<List<Repository>> repositories() {
            return Observable.just(mRepositories);
        }
    }
}
