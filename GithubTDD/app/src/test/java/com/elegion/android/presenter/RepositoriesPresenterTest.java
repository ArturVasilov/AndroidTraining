package com.elegion.android.presenter;

import android.app.LoaderManager;
import android.content.Context;

import com.elegion.android.content.Repository;
import com.elegion.android.view.RepositoriesView;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.reset;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.verify;

/**
 * @author Artur Vasilov
 */
@RunWith(JUnit4.class)
public class RepositoriesPresenterTest {

    private RepositoriesView mView;
    private RepositoriesPresenter mPresenter;

    @Before
    public void setUp() throws Exception {
        Context context = mock(Context.class);

        LoaderManager lm = mock(LoaderManager.class);
        mView = mock(RepositoriesView.class);
        mPresenter = spy(new RepositoriesPresenter(context, lm, mView));
    }

    @Test
    public void testEmptyRepositories() throws Exception {
        reset(mPresenter, mView);
        List<Repository> repositories = new ArrayList<>();
        mPresenter.showRepositories(repositories);

        verify(mView).showEmpty();
    }

    @Test
    public void testRepositories() throws Exception {
        reset(mPresenter, mView);
        List<Repository> repositories = new ArrayList<>();
        repositories.add(new Repository());
        mPresenter.showRepositories(repositories);

        verify(mView).showRepositories(repositories);
    }
}
