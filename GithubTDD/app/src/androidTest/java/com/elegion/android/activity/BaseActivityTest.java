package com.elegion.android.activity;

import android.support.test.espresso.intent.Intents;
import android.support.test.espresso.matcher.ViewMatchers;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.view.View;

import com.elegion.android.R;

import org.hamcrest.Matcher;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.withEffectiveVisibility;
import static android.support.test.espresso.matcher.ViewMatchers.withId;

/**
 * @author Artur Vasilov
 */
@RunWith(AndroidJUnit4.class)
public class BaseActivityTest {

    private static final Matcher<View> CONTENT_FRAME = withId(R.id.content_frame);
    private static final Matcher<View> TOOLBAR = withId(R.id.toolbar);

    @Rule
    public ActivityTestRule<LogInActivity> mActivityRule = new ActivityTestRule<>(LogInActivity.class);

    @Before
    public void setUp() throws Exception {
        Intents.init();
    }

    @Test
    public void testBaseElementsVisible() throws Exception {
        onView(CONTENT_FRAME).check(matches(withEffectiveVisibility(ViewMatchers.Visibility.VISIBLE)));
        onView(TOOLBAR).check(matches(withEffectiveVisibility(ViewMatchers.Visibility.VISIBLE)));
    }

    @After
    public void tearDown() throws Exception {
        Intents.release();
    }
}
