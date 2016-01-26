package com.elegion.android.app;

import android.app.Activity;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;

/**
 * @author Artur Vasilov
 */
@RunWith(JUnit4.class)
public class LifecyclerTest {

    private Lifecycler mLifecycler;

    @Before
    public void setUp() throws Exception {
        mLifecycler = new Lifecycler();
    }

    @Test
    public void testIsAppVisible() throws Exception {
        Activity activity = mock(Activity.class);
        mLifecycler.onActivityStarted(activity);

        assertTrue(Lifecycler.isApplicationVisible());
        mLifecycler.onActivityStarted(activity);
        assertTrue(Lifecycler.isApplicationVisible());
        mLifecycler.onActivityStarted(activity);
        assertTrue(Lifecycler.isApplicationVisible());

        mLifecycler.onActivityStopped(activity);
        assertTrue(Lifecycler.isApplicationVisible());
        mLifecycler.onActivityStopped(activity);
        assertTrue(Lifecycler.isApplicationVisible());

        mLifecycler.onActivityStopped(activity);
        assertFalse(Lifecycler.isApplicationVisible());
    }
}
