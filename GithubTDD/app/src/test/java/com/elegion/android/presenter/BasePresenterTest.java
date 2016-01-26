package com.elegion.android.presenter;

import android.app.Application;
import android.app.LoaderManager;
import android.content.Context;

import com.elegion.android.AppDelegate;
import com.elegion.android.TestUtils;
import com.elegion.android.app.Settings;
import com.elegion.android.base.BasePresenter;
import com.elegion.android.base.BaseView;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * @author Artur Vasilov
 */
@RunWith(JUnit4.class)
public class BasePresenterTest {

    private Context mContext;
    private LoaderManager mLoaderManager;
    private TestView mTestView;

    @Before
    public void setUp() throws Exception {
        mContext = mock(Context.class);
        mLoaderManager = mock(LoaderManager.class);
        mTestView = mock(TestView.class);
    }

    @Test
    public void testConstructor() throws Exception {
        BasePresenter<TestView> presenter = new BasePresenter<>(mContext, mLoaderManager, mTestView);
        assertNotNull(presenter);
    }

    @Test
    public void testFields() throws Exception {
        BasePresenter<TestView> presenter = new BasePresenter<>(mContext, mLoaderManager, mTestView);
        assertTrue(mContext == presenter.getContext());
        assertTrue(mLoaderManager == presenter.getLoaderManager());
    }

    @Test
    public void testApplicationGetter() throws Exception {
        Context context = mock(Context.class);
        AppDelegate mockedApp = mock(AppDelegate.class);
        when(context.getApplicationContext()).thenReturn(mockedApp);

        BasePresenter<TestView> presenter = new BasePresenter<>(context, mLoaderManager, mTestView);
        Application application = presenter.getAppDelegate();
        assertTrue(application == mockedApp);
    }

    @Test
    public void testSettingsGetter() throws Exception {
        Settings settings = mock(Settings.class);
        Context context = TestUtils.mockedContextWithSettings(settings);

        BasePresenter<TestView> presenter = new BasePresenter<>(context, mLoaderManager, mTestView);
        assertTrue(settings == presenter.getSettings());
    }

    @Test
    public void testViewGetter() throws Exception {
        BasePresenter<TestView> presenter = new BasePresenter<>(mContext, mLoaderManager, mTestView);
        assertTrue(mTestView == presenter.getView());
    }

    public interface TestView extends BaseView {
    }
}
